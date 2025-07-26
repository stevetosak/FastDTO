package models;

import com.vitosak.annotations.DeclareDTOs;
import com.vitosak.annotations.FieldMapping;

@DeclareDTOs(configNames = { "WEB", "MOBILE", "DESKTOP" })
public class EveryThingModel{

    @FieldMapping(configName = "WEB")
    public String attr1;

    @FieldMapping(configName = "WEB", referencedConfigName = "WEB")
    public SimpleModel attr7;

    @FieldMapping(configName = "WEB", mapTo = "first_email")
    public String attr2;

    @FieldMapping(configName = "WEB")
    @FieldMapping(configName = "MOBILE")
    public String attr5;


    @FieldMapping(configName = "MOBILE")
    public String attr6;
}
