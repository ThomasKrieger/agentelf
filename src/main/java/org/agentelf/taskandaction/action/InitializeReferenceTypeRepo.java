package org.agentelf.taskandaction.action;

import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;
import org.agentelf.api.Action;
import org.agentelf.handle.ReferenceTypeHandle;
import org.agentelf.type.ReferenceTypeRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class InitializeReferenceTypeRepo {

    @Value("${main-target-dir}")
    private String mainSrcDir;
    @Value("${test-target-dir}")
    private String testSrcDir;

    /**
     * Loads all files from mainSrcDir and testSrcDir. Creates a CompilationUnit for
     * each loaded File. Gets the ReferenceTypeHandle for each CompilationUnit and creates
     * a ReferenceTypeRepo with this data.
     *
     * @return an initialized ReferenceTypeRepo
     */
    @Action(arguments = {}, returnVariable = "referenceTypeRepo")
    public ReferenceTypeRepo initializeReferenceTypeRepo() {
        Map<String, List<ReferenceTypeHandle>> nameToHandle = new HashMap<>();
        Map<ReferenceTypeHandle, CompilationUnit> handleToSource = new HashMap<>();

        Stream.of(mainSrcDir, testSrcDir)
                .filter(dir -> dir != null && !dir.isEmpty())
                .map(Paths::get)
                .filter(Files::exists)
                .forEach(path -> {
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
                                            nameToHandle.computeIfAbsent(typeName, k -> new ArrayList<>()).add(handle);
                                            handleToSource.put(handle, cu);
                                        }
                                    } catch (IOException e) {
                                        throw new RuntimeException("Failed to parse Java file: " + p, e);
                                    }
                                });
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to walk directory: " + path, e);
                    }
                });

        return new ReferenceTypeRepo(nameToHandle, handleToSource);
    }
}