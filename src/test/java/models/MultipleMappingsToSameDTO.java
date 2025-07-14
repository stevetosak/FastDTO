package models;

import com.vitosak.annotations.DeclareDTOs;
import com.vitosak.annotations.FieldMapping;

@DeclareDTOs(configNames = "WEB")
public class MultipleMappingsToSameDTO{
    @FieldMapping(configName = "WEB")
    @FieldMapping(configName = "WEB")
    public String attr4;
}
