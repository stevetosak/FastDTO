package com.vitosak.core;

import jakarta.validation.Constraint;

import java.util.Collection;
import java.util.Map;


// todo metoda vo FieldDescriptor: builder() so ke ja pret inicijalizacijata

public class FieldDescriptorBuilder {
    private final FieldDescriptor field = new FieldDescriptor();

    FieldDescriptorBuilder() {
        field.collectionType=null;
    }

    public FieldDescriptorBuilder originalName(String name) {
        this.field.originalName=name;
        return this;
    }
    public FieldDescriptorBuilder collectionType(Class<? extends Collection> collection) {
        this.field.collectionType=collection;
        return this;
    }

    public FieldDescriptorBuilder mappedName(String name) {
        this.field.mappedName=name;
        return this;
    }

    public FieldDescriptorBuilder useDTO(String name) {
        this.field.useDTO=name;
        return this;
    }

    public FieldDescriptorBuilder flatten(boolean flatten) {
        this.field.flatten=flatten;
        return this;
    }

    public FieldDescriptorBuilder predicates(Constraint[] constraints) {
        this.field.predicates=constraints;
        return this;
    }

    public FieldDescriptorBuilder type(Class<?> type){
        this.field.type=type;
        return this;
    }

    private void checkField(){
       if(field.originalName==null){
           throw new IllegalArgumentException("Original name is null");
       }
       if(field.type==null){
           throw new IllegalArgumentException("Type is null");
       }
    }

    public FieldDescriptor build() {
        this.checkField();
        return field;
    }
}
