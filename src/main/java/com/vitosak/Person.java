//package com.vitosak;
//
//import com.vitosak.annotations.FieldMapping;
//import com.vitosak.annotations.Flatten;
//import com.vitosak.annotations.FromDTO;
//
//import java.util.HashMap;
//
//public class Person {
//    @FieldMapping
//    String name;
//    @FieldMapping @Flatten
//    Child child;
//
//    @FromDTO
//    public static Person fromDTO(HashMap<String,Object> dto) {
//        Person person = new Person();
//        person.name = (String) dto.get("name");
//        return person;
//    }
//
//    @Override
//    public String toString() {
//        return "Person{" +
//                "name='" + name + '\'' +
//                '}';
//    }
//}
