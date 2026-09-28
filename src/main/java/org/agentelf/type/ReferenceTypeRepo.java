package org.agentelf.type;

import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.agentelf.handle.ReferenceTypeHandle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class ReferenceTypeRepo {

    private final Map<String, Set<ReferenceTypeHandle>> nameToHandle = new HashMap<>();
    /**
     * all classes which are unknown to the llm must be stored here
     */
    private final Map<ReferenceTypeHandle, CompilationUnit> handleToSource = new HashMap<>();

    public PackageLookupResult lookup(String name) {
        Set<ReferenceTypeHandle> result = nameToHandle.get(name);
        if(result == null) {
            return new PackageLookupResult.NoPackageFound();
        }
        if(result.isEmpty()) {
            return new PackageLookupResult.NoPackageFound();
        }
        if(result.size() > 1) {
            return new PackageLookupResult.MultipleResults(result);
        }
        return new PackageLookupResult.OneResult(result.stream().findFirst().get());
    }

    public void putAll(Map<String, Set<ReferenceTypeHandle>>  putNameToHandle,
                       Map<ReferenceTypeHandle, CompilationUnit> putHandleToSource) {
        for(Map.Entry<String, Set<ReferenceTypeHandle>> elem : putNameToHandle.entrySet()) {
            if(nameToHandle.containsKey(elem.getKey())) {
                nameToHandle.get(elem.getKey()).addAll(elem.getValue());
            } else {
                nameToHandle.put(elem.getKey(),elem.getValue());
            }
        }
        handleToSource.putAll(putHandleToSource);
    }

    public void put(ReferenceTypeHandle handle , String source) {
        StaticJavaParser.getParserConfiguration()
                .setLanguageLevel(ParserConfiguration.LanguageLevel.JAVA_21);
        nameToHandle.computeIfAbsent(handle.name(), k -> new HashSet<>()).add(handle);
        handleToSource.put(handle, StaticJavaParser.parse(source));
    }

}
