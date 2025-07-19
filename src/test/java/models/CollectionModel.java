package models;

import com.vitosak.annotations.DeclareDTOs;
import com.vitosak.annotations.FieldMapping;

import java.util.*;

@DeclareDTOs(configNames = {"WEB","REGISTER"})
public class CollectionModel {
    @FieldMapping(configName = "WEB")
    List<String> attrs = List.of("t1","t2","t3","t4","t5");
    @FieldMapping(configName = "WEB",referencedConfigName = "WEB")
    List<SimpleModel> simpleModels = List.of(new SimpleModel(),new SimpleModel(),new SimpleModel(),new SimpleModel());
    @FieldMapping(configName = "WEB")
    Integer id = 3;
    @FieldMapping(configName = "WEB")
    Set<Integer> set = Set.of(1,2,3,4);
    @FieldMapping(configName = "WEB")
    List<Map<String,String>> compositeCollection = List.of(Map.of("k1","v1"));
}
