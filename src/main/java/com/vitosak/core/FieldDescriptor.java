package com.vitosak.core;

import jakarta.validation.Constraint;

import java.util.Arrays;
import java.util.Collection;

public class FieldDescriptor {
    public String originalName;
    public String mappedName = "";
    public String useDTO = "";
    public boolean flatten = false ;
    public Class type; // ako e collection: Collection<T> togas type = T
    public Class<? extends Collection> collectionType = null;
    public Constraint[] predicates = new Constraint[0];

   public FieldDescriptor(){

   }

    @Override
    public String toString() {
        return "FieldDescriptor{" +
                "originalName='" + originalName + '\'' +
                ", mappedName='" + mappedName + '\'' +
                ", useDTO='" + useDTO + '\'' +
                ", flatten=" + flatten +
                ", type=" + type +
                ", isCollection=" + collectionType +
                ", predicates=" + Arrays.toString(predicates) +
                '}';
    }

    public boolean equals(FieldDescriptor other){
       return type.equals(other.type) && originalName.equals(other.originalName) &&
               useDTO.equals(other.useDTO) && flatten == other.flatten && mappedName.equals(other.mappedName) &&
               Arrays.equals(predicates, other.predicates);

   }

   public static FieldDescriptorBuilder builder(){
       return new FieldDescriptorBuilder();
   }
}
