package com.vitosak.annotations;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

/// Specifying a string="" is the same as defining it as it were not to exists at all
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Repeatable(FieldMappings.class)
public @interface FieldMapping {
    String referencesDTO();
    String mappedTo() default "";
    String useDTO() default "";
    boolean flatten() default false;
    //TODO: vidi dali imat nekoj nachin da gi zamenish ovie lajnava i da klajme predikati
    Constraint[] predicates() default {};
}

