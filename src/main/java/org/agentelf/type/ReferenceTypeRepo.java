package org.agentelf.type;

import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;
import lombok.extern.slf4j.Slf4j;
import org.agentelf.handle.MethodHandle;
import org.agentelf.handle.ReferenceTypeHandle;
import org.agentelf.inheritancegraph.InheritanceGraph;
import org.agentelf.inheritancegraph.MethodsToBeImplemented;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *  Used for the following tasks:
 *  get full qualified name
 *  lookup classes which needs to be included
 *  methods witch needs to be implemented
 *
 */
@Slf4j
public class ReferenceTypeRepo {

    private final InheritanceGraph inheritanceGraph = new InheritanceGraph();
    private final Map<String, Set<ReferenceTypeHandle>> nameToHandle = new HashMap<>();
    /**
     * used for the lookup of sources for classes which are unknown to the llm
     *
     */
    private final Map<ReferenceTypeHandle, ModelOrSource> handleToSource = new HashMap<>();

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
                       Map<ReferenceTypeHandle, ModelOrSource> putHandleToSource) {
        for(Map.Entry<String, Set<ReferenceTypeHandle>> elem : putNameToHandle.entrySet()) {
            if(nameToHandle.containsKey(elem.getKey())) {
                nameToHandle.get(elem.getKey()).addAll(elem.getValue());
            } else {
                nameToHandle.put(elem.getKey(),elem.getValue());
            }
        }
        handleToSource.putAll(putHandleToSource);
    }

    public void put(ReferenceTypeHandle handle, String source) {
        StaticJavaParser.getParserConfiguration()
                .setLanguageLevel(ParserConfiguration.LanguageLevel.JAVA_21);
        nameToHandle.computeIfAbsent(handle.name(), k -> new HashSet<>()).add(handle);
        if(handleToSource.containsKey(handle)) {
            handleToSource.get(handle).uodate(StaticJavaParser.parse(source));
        } else {
            handleToSource.put(handle, new FromSource(handle,StaticJavaParser.parse(source)));
        }
    }

    public void put(ReferenceTypeHandle handle , ModelOrSource modelOrSource) {
        StaticJavaParser.getParserConfiguration()
                .setLanguageLevel(ParserConfiguration.LanguageLevel.JAVA_21);
        nameToHandle.computeIfAbsent(handle.name(), k -> new HashSet<>()).add(handle);
        handleToSource.put(handle, modelOrSource);
    }

    public void put(ReferenceTypeHandle handle) {
        nameToHandle.computeIfAbsent(handle.name(), k -> new HashSet<>()).add(handle);
    }

    public void addInheritanceRelation(ReferenceTypeHandle superType,
                                       ReferenceTypeHandle subType) {
        inheritanceGraph.addInheritance(superType,subType);
    }

    public ModelOrSource getModelOrSource(ReferenceTypeHandle handle) {
        return handleToSource.get(handle);
    }

    public Map<MethodHandle,String> getAllMethodsToBeImplemented(ReferenceTypeHandle handle) {
        MethodsToBeImplemented methodsToBeImplemented = new MethodsToBeImplemented();
        inheritanceGraph.addToMethodsToBeImplemented(handle,methodsToBeImplemented,this);
        return methodsToBeImplemented.getMethodHandleToPrompt();
    }

}
