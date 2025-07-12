package com.vitosak.processor.models.fields_tests;

import com.vitosak.annotations.DeclareDTOs;
import com.vitosak.annotations.FieldMapping;

@DeclareDTOs(dtos = { "WEB", "MOBILE", "DESKTOP" })
public class EveryThingModel{

    @FieldMapping(referencesDTO = "WEB")
    public String attr1;

    @FieldMapping(referencesDTO = "WEB",useDTO = "WEB")
    public SimpleModel attr7;

    @FieldMapping(referencesDTO = "WEB",mappedTo = "first_email")
    public String attr2;

    @FieldMapping(referencesDTO = "WEB")
    @FieldMapping(referencesDTO = "MOBILE")
    public String attr5;


    @FieldMapping(referencesDTO = "MOBILE")
    public String attr6;
}
