package org.agentelf.taskandaction.action;

import lombok.extern.slf4j.Slf4j;
import org.agentelf.api.Action;
import org.agentelf.handle.ReferenceTypeHandle;
import org.agentelf.model.tosource.ToSourceModel;
import org.agentelf.model.tosource.TypeSourceString;
import org.agentelf.model.tosource.TypeToSource;
import org.springframework.stereotype.Component;
import sootup.core.jimple.common.stmt.Stmt;
import sootup.core.model.FieldModifier;
import sootup.core.model.MethodModifier;
import sootup.java.bytecode.frontend.inputlocation.JavaClassPathAnalysisInputLocation;
import sootup.java.core.JavaSootField;
import sootup.java.core.JavaSootMethod;
import sootup.java.core.views.JavaView;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Component
@Slf4j
public class ProcessJar {

    /**
     * load all jar files in a directory
     * process .jar nor source.jar
     * process when volatile field, monitor
     * or call to other concurrent class
     *
     */

    @Action(arguments = {"modelPath"}, returnVariable = "toSourceModel")
    public ToSourceModel loadFunctionalModel(String modelPath) throws IOException {
        Map<String, ReferenceTypeHandle> handles = new HashMap<>();
        try (var paths = Files.walk(Path.of(modelPath))) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".jar"))
                    .filter(path -> path.toString().contains("10.3.2"))
                    .filter(path -> ! path.toString().endsWith("-sources.jar"))
                    .flatMap( ( jar ) -> {
                        log.info(jar.toString());
                        JavaClassPathAnalysisInputLocation inputLocation =
                                new JavaClassPathAnalysisInputLocation(
                                        jar.toAbsolutePath().toString());
                        JavaView view = new JavaView(inputLocation);
                        return view.getClasses();
                    }  ).filter( (cl) -> {
                        if(cl.isAbstract()) {
                            return false;
                        }
                        if(cl.getType().getFullyQualifiedName().contains("test")) {
                            return false;
                        }
                        for(JavaSootMethod  m : cl.getMethods()) {
                            if(m.getModifiers().contains(MethodModifier.NATIVE)) {
                                return false;
                            }
                            if(m.getModifiers().contains(MethodModifier.ABSTRACT)) {
                                return false;
                            }
                            if(m.getModifiers().contains(MethodModifier.SYNCHRONIZED)) {
                                return true;
                            }
                            for(Stmt stmt : m.getBody().getStmts()) {
                                if(stmt.isJEnterMonitorStmt()) {
                                    return true;
                                }
                            }
                        }
                        for(JavaSootField f : cl.getFields()) {
                            if(f.getModifiers().contains(FieldModifier.VOLATILE)) {
                                return true;
                            }
                        }

                        return false;
                    } ).forEach(  cl -> {
                        handles.put(cl.getName(), new ReferenceTypeHandle(cl.getType().getPackageName().getName(),
                                cl.getType().getClassName()));

                    } );
        }

        Map<ReferenceTypeHandle, TypeToSource> typeToModel = new HashMap<>();

        try (var paths = Files.walk(Path.of(modelPath))) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith("-sources.jar"))
                    .filter(path -> path.toString().contains("10.3.2"))
                    .forEach( jarPath -> {
                        try (JarFile jar = new JarFile(jarPath.toFile())) {
                            var entries = jar.entries();

                            while (entries.hasMoreElements()) {
                                JarEntry entry = entries.nextElement();

                                if (entry.isDirectory()) {
                                    continue;
                                }
                                if(entry.getName().endsWith(".java")) {
                                    String normalized = entry.getName().substring(
                                            0, entry.getName().length() - ".java".length()
                                    );
                                    normalized = normalized.replace('/', '.');
                                if(handles.containsKey(normalized)) {
                                    ReferenceTypeHandle handle = handles.get(normalized);

                                    log.info(entry.getName());

                                    try (InputStream in = jar.getInputStream(entry)) {

                                        String content = new String(
                                                in.readAllBytes(),
                                                StandardCharsets.UTF_8
                                        );
                                        TypeToSource typeToSource = new TypeToSource(handle);
                                        typeToSource.setTypeSource(new TypeSourceString(content));
                                        typeToModel.put(handle,typeToSource);

                                    }
                                }
                                }
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
            return new ToSourceModel(typeToModel);
     }
    }

}
