package com.vitosak.core;

import com.vitosak.annotations.FieldMapping;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import com.vitosak.exceptions.ChildDTOConfigurationNotSelected;
import com.vitosak.processor.DTOGenerator;

import javax.lang.model.type.UnknownTypeException;

public class FieldDescriptorFactory {

    private static boolean isUserDefined(Class<?> cls) {
        Package pkg = cls.getPackage();
        if (pkg == null) return true;
        String packageName = pkg.getName();
        return !(packageName.startsWith("java.") || packageName.startsWith("javax."));
    }

    //TODO: Add checks for the existence of the configurations on the child entity
    private static String useDTOString(Field field, FieldMapping fieldMapping) {
        if (!isUserDefined(field.getType())) {
            return fieldMapping.referencedConfigName();
        }
        if (fieldMapping.referencedConfigName().isEmpty()) {
            throw new ChildDTOConfigurationNotSelected(field.getDeclaringClass(), field);
        }
        return DTOGenerator.generateFullConfigName(field.getType(), fieldMapping.referencedConfigName());
    }

    private static Class<?> extractTypeFromGeneric(Field field) {
        Class<?> type;
        if (field.getGenericType() instanceof ParameterizedType parameterizedType) {
            Type typeArg = parameterizedType.getActualTypeArguments()[0];
            if (typeArg instanceof Class<?> clazz) {
                type = clazz; // najcest slucaj primer ko: Collection<String>
            } else if (typeArg instanceof ParameterizedType pt && pt.getRawType() instanceof Class<?> raw) {
                type = raw; // ako e vgnezden primer ko:  Collection<Map<String, String>>
            } else {
                type = Object.class; // ako i gospod ne znet sho e
            }
            return type;
        } else {
            throw new IllegalArgumentException("Invalid generic type: " + field.getGenericType().toString());
        }
    }

    public static FieldDescriptor createFieldDescriptor(Field field, FieldMapping fieldMapping) {
        boolean isCollection = Collection.class.isAssignableFrom(field.getType());
        Class<?> type = isCollection ? extractTypeFromGeneric(field) : field.getType();
        Class<? extends Collection> collectionType = isCollection ? (Class<? extends Collection>) field.getType() : null;
        return FieldDescriptor
                .builder()
                .originalName(field.getName())
                .mappedName(fieldMapping.mapTo())
                .useDTO(useDTOString(field, fieldMapping))
                .flatten(fieldMapping.flatten())
                .collectionType(collectionType)
                .type(type)
                .predicates(fieldMapping.predicates())
                .build();
    }
}
