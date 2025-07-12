package com.vitosak.core.exceptions;

import java.lang.reflect.Field;

public class ChildDTOConfigurationNotSelected extends RuntimeException {
    public ChildDTOConfigurationNotSelected(Class<?> parent, Field child) {
        super(
                String.format("In the class %s you haven't specified a configuration to use for %s",parent.getName(), child.getName())
        );
    }
}
