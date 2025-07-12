package com.vitosak.core;

import com.vitosak.annotations.FieldMapping;

import java.lang.reflect.Field;

import com.vitosak.core.exceptions.ChildDTOConfigurationNotSelected;
import com.vitosak.processor.DTOGenerator;

public class FieldDescriptorFactory {
    static FieldDescriptorBuilder builder = FieldDescriptorBuilder.builder();

    private static boolean isUserDefined(Class<?> cls){
        Package pkg = cls.getPackage();
        if (pkg == null) return true;
        String packageName = pkg.getName();
        return !(packageName.startsWith("java.") || packageName.startsWith("javax."));
    }

    //TODO: Add checks for the existence of the configurations on the child entity
    private static String useDTOString (Field field, FieldMapping fieldMapping) {
        if(!isUserDefined(field.getType())) {
            return fieldMapping.useDTO();
        }
        if(fieldMapping.useDTO().isEmpty()){
            throw new ChildDTOConfigurationNotSelected(field.getDeclaringClass(),field);
        }
        return DTOGenerator.generateFullConfigName(field.getType(), fieldMapping.useDTO());
    }

    public static FieldDescriptor createFieldDescriptor(Field field, FieldMapping fieldMapping) {
        return builder.start()
                    .originalName(field.getName())
                    .mappedName(fieldMapping.mappedTo())
                    .useDTO(useDTOString(field,fieldMapping))
                    .flatten(fieldMapping.flatten())
                    .type(field.getType())
                    .predicates(fieldMapping.predicates())
                    .build();
    }
}
