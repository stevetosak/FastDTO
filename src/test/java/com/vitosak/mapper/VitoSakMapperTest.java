package com.vitosak.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class VitoSakMapperTest {

    VitoSakMapper mapper = new VitoSakMapper();
//    static DTOGenerator dtoGenerator = new DTOGenerator(
//            new ClassInspector(),
//    );

    @BeforeAll
    public static void genDTOClasses() {
//       dtoGenerator.processPackages("test_models.*");
    }

    @Test
    public void shouldReturnJson(){

    }

    @Test
    public void whenGiven2Config_then2DifferentJson(){

    }

    @Test
    public void whenGivenMappedToInFieldMapping_thenJsonWithDifferentName(){

    }

    //TODO: Test when given predicates
    //TODO: Testing the background infrastructure
}
