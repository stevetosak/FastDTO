package models;

import com.vitosak.annotations.DeclareDTOs;
import com.vitosak.annotations.FieldMapping;

@DeclareDTOs(dtos = "WEB")
public class MultipleMappingsToSameDTO{
    @FieldMapping(referencesDTO = "WEB")
    @FieldMapping(referencesDTO = "WEB")
    public String attr4;
}
