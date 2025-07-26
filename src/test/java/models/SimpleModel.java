package models;

import com.vitosak.annotations.DeclareDTOs;
import com.vitosak.annotations.FieldMapping;

@DeclareDTOs(configNames = {"WEB","MOBILE"})
public class SimpleModel {
    @FieldMapping(configName = "WEB", mapTo = "web_name")
    public String web;
}
