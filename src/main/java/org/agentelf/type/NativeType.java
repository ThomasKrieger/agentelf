package org.agentelf.type;

import org.agentelf.handle.TypeHandle;

public enum NativeType implements Type, TypeHandle {
    ;

    @Override
    public TypeHandle toHandle() {
        return this;
    }
}
