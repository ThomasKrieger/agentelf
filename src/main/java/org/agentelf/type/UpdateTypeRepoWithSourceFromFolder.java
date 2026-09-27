package org.agentelf.type;

import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;
import org.agentelf.handle.ReferenceTypeHandle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class UpdateTypeRepoWithSourceFromFolder {

    public void update(ReferenceTypeRepo referenceTypeRepo, String folder) {
        Map<String, Set<ReferenceTypeHandle>> nameToHandle = new HashMap<>();
        Map<ReferenceTypeHandle, CompilationUnit> handleToSource = new HashMap<>();
        Path path = Paths.get(folder);
        try (Stream<Path> walk = Files.walk(path)) {
            walk.filter(p -> Files.isRegularFile(p) && p.toString().endsWith(".java"))
                    .forEach(p -> {
                        try {
                            StaticJavaParser.getParserConfiguration()
                                    .setLanguageLevel(ParserConfiguration.LanguageLevel.JAVA_21);
                            CompilationUnit cu = StaticJavaParser.parse(p);
                            String packageName = cu.getPackageDeclaration()
                                    .map(pd -> pd.getNameAsString())
                                    .orElse("");
                            for (TypeDeclaration<?> type : cu.getTypes()) {
                                String typeName = type.getNameAsString();
                                ReferenceTypeHandle handle = new ReferenceTypeHandle(packageName, typeName);
                                nameToHandle.computeIfAbsent(typeName, k -> new HashSet<>()).add(handle);
                                handleToSource.put(handle, cu);
                            }
                        } catch (IOException e) {
                            throw new RuntimeException("Failed to parse Java file: " + p, e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException("Failed to walk directory: " + path, e);
        }
        referenceTypeRepo.putAll(nameToHandle,handleToSource);
    }

}
