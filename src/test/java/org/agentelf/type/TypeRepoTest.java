package org.agentelf.type;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeRepoTest {

    @Test
    public void nativeType() {
        Type type = new TypeRepo().getForSimpleName("xyz", "int");
        assertEquals(NativeType.INT, type);
    }

}
