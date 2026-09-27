package org.agentelf.type;

import lombok.Getter;
import org.agentelf.handle.ReferenceTypeHandle;

import java.util.Set;

/**
 * we use this for two use cases:
 * lookup of full qualified names from single names
 * lookup of a java source file based on a fully qualified name
 */
public class TypeRepo {

    @Getter
    private final ReferenceTypeRepo referenceTypeRepo = new ReferenceTypeRepo();

    public Type getForSimpleName(String currentPackage, String name) {
        try{
            if(name.equals(name.toLowerCase())) {
                return NativeType.valueOf(name.toUpperCase());
            }
        } catch (IllegalArgumentException  illegalArgumentException) {

        }
        PackageLookupResult result = referenceTypeRepo.lookup(name);
        return switch (result) {
            case PackageLookupResult.NoPackageFound() ->
                    throw new RuntimeException("no package found for:"+ name);

            case PackageLookupResult.OneResult(var type) ->
                   new ReferenceType(type.packageName(),type.name());

            case PackageLookupResult.MultipleResults(var candidates) ->
                    resolve(currentPackage,candidates,name);
        };
    }

    private ReferenceType resolve(String currentPackage, Set<ReferenceTypeHandle> candidates,String name) {
        for(ReferenceTypeHandle handle : candidates) {
            if(currentPackage.startsWith(handle.packageName())) {
                return new ReferenceType(handle.packageName(),handle.name());
            }
        }
        throw new RuntimeException("too many packages found for:"+ name+ ":" + candidates);
    }

}
