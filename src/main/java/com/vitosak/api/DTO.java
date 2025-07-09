//package com.vitosak.api;
//
//import com.vitosak.annotations.Flatten;
//import com.vitosak.annotations.SimpleDTO;
//import com.vitosak.enums.FieldFilterMode;
//
//import java.lang.reflect.Field;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Set;
//
//private static final Set<Class<?>> ALLOWED_TYPES = Set.of(
//        Boolean.class,
//        Byte.class,
//        Character.class,
//        Short.class,
//        Integer.class,
//        Long.class,
//        Float.class,
//        Double.class,
//        String.class
//);
//
//
//// vo procesorot da provervam dali e primitive type ako ne e anotiran so Flatten
//
//public interface DTO {
//    static HashMap<String,String> toDTO(DTO obj,String name){
//        SimpleDTO[] annotations = obj.getClass().getAnnotationsByType(SimpleDTO.class);
//        SimpleDTO target = Arrays.stream(annotations)
//                .filter(annotation -> annotation.name().equals(name))
//                .findFirst().orElse(null);
//        assert target != null;
//        assert (target.excludes().length == 0 || target.includes().length == 0);// morat ili excludes ili includes ne dvete zaedno
//        FieldFilterMode mode;
//        if(target.excludes().length == 0 && target.includes().length == 0){
//            mode = FieldFilterMode.NONE;
//        } else {
//            mode = target.excludes().length > target.includes().length ? FieldFilterMode.EXCLUDE : FieldFilterMode.INCLUDE;
//        }
//
//        Field[] fields = target.getClass().getFields();
//
//        for (Field field : fields) {
//            if(target.)
//        }
//    }
//
//
//vv
//    private static HashMap<String,String> processNone(FieldFilterMode mode, Field[] fields,DTO obj) throws IllegalAccessException {
//        HashMap<String,String> map = new HashMap<>();
//        for(Field field : fields){
//            Flatten flattenAnno = field.getAnnotation(Flatten.class);
//            if(flattenAnno != null){
//
//            }
//            Object value = field.get(obj);
//            map.put(field.getName(),field.get(obj));
//        }
//
//
//    }
//
