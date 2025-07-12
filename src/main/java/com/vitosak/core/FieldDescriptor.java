package com.vitosak.core;

import jakarta.validation.Constraint;

import java.util.Arrays;

public class FieldDescriptor {
    public String originalName;
    public String mappedName;
    public String useDTO = "";
    public boolean flatten = false ;
    public Class type;
    public Constraint[] predicates;

   public FieldDescriptor(){

   }

   public boolean equals(FieldDescriptor other){
       return type.equals(other.type) && originalName.equals(other.originalName) &&
               useDTO.equals(other.useDTO) && flatten == other.flatten && mappedName.equals(other.mappedName) &&
               Arrays.equals(predicates, other.predicates);

   }
}
