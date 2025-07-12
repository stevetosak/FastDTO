package com.vitosak;

import com.vitosak.core.ConfigRepo;
import com.vitosak.processor.DTOGenerator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DTOGenerator dtoGenerator = new DTOGenerator(
                new ConfigRepo()
        );

        dtoGenerator.processPackages("com.vitosak.model");
   }
}