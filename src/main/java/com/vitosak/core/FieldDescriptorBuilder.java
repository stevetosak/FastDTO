package com.vitosak.core;

import jakarta.validation.Constraint;

import java.util.Map;

public class FieldDescriptorBuilder {
    private FieldDescriptor field;
    private boolean started=false;

    private FieldDescriptorBuilder() {

    }

    private void checkIfStarted() {
        if (!started) {
            throw new IllegalStateException("FieldDescriptorBuilder has not been started");
        }
    }

    public FieldDescriptorBuilder start(){
        started=true;
        field = new FieldDescriptor();

        field.predicates=new Constraint[0];
        field.mappedName="";
        field.flatten=false;

        return this;
    }

    public FieldDescriptorBuilder originalName(String name) {
        this.checkIfStarted();
        this.field.originalName=name;
        return this;
    }

    public FieldDescriptorBuilder mappedName(String name) {
        checkIfStarted();
        this.field.mappedName=name;
        return this;
    }

    public FieldDescriptorBuilder useDTO(String name) {
        checkIfStarted();
        this.field.useDTO=name;
        return this;
    }

    public FieldDescriptorBuilder flatten(boolean flatten) {
        checkIfStarted();
        this.field.flatten=flatten;
        return this;
    }

    public FieldDescriptorBuilder predicates(Constraint[] constraints) {
        checkIfStarted();
        this.field.predicates=constraints;
        return this;
    }

    public FieldDescriptorBuilder type(Class<?> type){
        checkIfStarted();
        this.field.type=type;
        return this;
    }

    public static FieldDescriptorBuilder builder() {
        return new FieldDescriptorBuilder();
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
        checkIfStarted();
        this.checkField();
        this.started=false;

        var tmp = this.field;
        this.field = null;
        return tmp;
    }
}
