package com.vitosak.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// ke sa koristit za site fields da mozis da gi anotiras so validacii
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldMapping {
    String referencesDTO() default ""; // ova morat da e zadolzitelno ako poleto e referenced entity
    //validator
    //predicates
}
