package com.vitosak.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(SimpleDTOContainer.class)
public @interface SimpleDTO {
    String name();
    String [] excludes() default {};
    String [] includes() default {};
}
