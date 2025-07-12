package com.vitosak.exceptions;

public class MultipleMappings extends RuntimeException {
    public MultipleMappings(Class<?> cls) {
        super(
                String.format("Multiple mappings for the same DTO config in %s", cls.getName())
        );
    }
}
