package org.agentelf.type;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import org.agentelf.handle.ReferenceTypeHandle;

import java.util.*;

public class UpdateTypeRepoWithClassesFromRuntime {

    public void update(ReferenceTypeRepo referenceTypeRepo) {
        Map<String, Set<ReferenceTypeHandle>> nameToHandle = new HashMap<>();
        try (ScanResult scan = new ClassGraph()
                .enableSystemJarsAndModules()
                .enableClassInfo()
                .enableMethodInfo()
                .acceptPackages("java")
                .scan()) {

            for (ClassInfo classInfo : scan.getAllClasses()) {
                String packageName = classInfo.getPackageName();
                String className = classInfo.getSimpleName();
                ReferenceTypeHandle handle = new ReferenceTypeHandle(packageName, className);
                nameToHandle.computeIfAbsent(className, k -> new HashSet<>()).add(handle);
            }
        }
        referenceTypeRepo.putAll(nameToHandle,new HashMap<>());
    }
}
