package org.agentelf.inheritancegraph;


import org.agentelf.handle.ReferenceTypeHandle;
import org.agentelf.type.ReferenceTypeRepo;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.jgrapht.graph.EdgeReversedGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import java.util.Set;

public class InheritanceGraph {

    private final Graph<ReferenceTypeHandle, DefaultEdge> graph =
            new DirectedAcyclicGraph<>(DefaultEdge.class);

    public void addInheritance(
            ReferenceTypeHandle superType,
            ReferenceTypeHandle subType) {
        graph.addVertex(superType);
        graph.addVertex(subType);

        graph.addEdge(superType, subType);
    }


    /**
     * ToDo we should start with the initial class and gor backwards
     * if it is already implemented we do not need do implement it
     * also always use the last prompt.
     * perhaps allow usage of super
     *
     */
    public void addToMethodsToBeImplemented(ReferenceTypeHandle subtype,
                                            MethodsToBeImplemented methodsToBeImplemented,
                                            ReferenceTypeRepo referenceTypeRepo) {

        Set<ReferenceTypeHandle> suoerTypeSet = allSuperTypes(subtype);
        for(ReferenceTypeHandle referenceTypeHandle : suoerTypeSet) {
            referenceTypeRepo.getModelOrSource(referenceTypeHandle).addToMethodsToBeImplemented(methodsToBeImplemented);
        }
    }

    private Set<ReferenceTypeHandle> allSuperTypes(ReferenceTypeHandle subtype) {
        var reversedGraph = new EdgeReversedGraph<>(graph);
        var iterator = new DepthFirstIterator<>(reversedGraph, subtype);

        Set<ReferenceTypeHandle> result = new java.util.HashSet<>();
        iterator.forEachRemaining(result::add);

        result.remove(subtype);
        return result;
    }

}
