package com.vitosak.annotations;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(SimpleDTO.class)
public @interface DTOConfig {
    String name();
}
