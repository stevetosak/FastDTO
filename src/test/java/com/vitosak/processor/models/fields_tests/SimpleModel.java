package com.vitosak.processor.models.fields_tests;

import com.vitosak.annotations.DeclareDTOs;
import com.vitosak.annotations.FieldMapping;

@DeclareDTOs(dtos = {"WEB","MOBILE"})
public class SimpleModel {
    @FieldMapping(referencesDTO = "WEB",mappedTo = "web_name")
    public String web;
}
