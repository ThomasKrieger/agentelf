package org.agentelf.type;

import org.agentelf.handle.ReferenceTypeHandle;

import java.util.Set;

public sealed interface PackageLookupResult
        permits PackageLookupResult.NoPackageFound,
        PackageLookupResult.OneResult,
        PackageLookupResult.MultipleResults {

    record NoPackageFound() implements PackageLookupResult {}

    record OneResult(ReferenceTypeHandle type)
            implements PackageLookupResult {}

    record MultipleResults(Set<ReferenceTypeHandle> candidates)
            implements PackageLookupResult {}
}
