package com.vitosak.processor;

import com.vitosak.core.ConfigRepo;
import com.vitosak.core.FieldDescriptor;
import com.vitosak.core.FieldDescriptorBuilder;
import com.vitosak.exceptions.MultipleMappings;
import models.EveryThingModel;
import models.MultipleMappingsToSameDTO;
import models.SimpleModel;
import jakarta.validation.Constraint;
import org.junit.jupiter.api.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.*;


public class TestDTOGenerator {


    static ConfigRepo configRepo;
    DTOGenerator dtoGenerator;
    FieldDescriptorBuilder fieldBuilder = FieldDescriptorBuilder.builder();

    @BeforeAll
    static void setUpConfigRepo() {
        configRepo = mock(ConfigRepo.class);
    }

    @BeforeEach
    void setUp() {
        dtoGenerator = new DTOGenerator(configRepo);
    }

    private Method getProcessingClassMethod(DTOGenerator dtoGenerator) throws NoSuchMethodException {
        Method processClass = dtoGenerator.getClass()
                                          .getDeclaredMethod("processClass", Class.class);
        processClass.setAccessible(true);
        return processClass;
    }

    private boolean checkEqMapConfigs(Map<String, List<FieldDescriptor>> expected, Map<String, List<FieldDescriptor>> result) {
        for (String key : expected.keySet()) {
            if (!expected.containsKey(key)) {
                throw new AssertionError();
            }
            List<FieldDescriptor> expectedFields = expected.get(key);
            List<FieldDescriptor> resultFields = result.get(key);

            if (expectedFields.size() != resultFields.size()) {
                throw new AssertionError();
            }

            for (int i = 0; i < expectedFields.size(); i++) {
                if (!expectedFields.get(i)
                                   .equals(resultFields.get(i))) {
                    throw new AssertionError();
                }
            }
        }
        return true;
    }

    @Test
    void whenMultipleMappings_thenShouldThrowMultipleMappingsExcpetion() throws NoSuchMethodException {
        DTOGenerator dtoGenerator = new DTOGenerator(configRepo);
        Method processingClassMethod = getProcessingClassMethod(dtoGenerator);

        InvocationTargetException targetException = assertThrowsExactly(
                InvocationTargetException.class,
                () -> processingClassMethod.invoke(dtoGenerator, MultipleMappingsToSameDTO.class)
        );

        Assertions.assertEquals(MultipleMappings.class, targetException.getCause()
                                                                       .getClass());
    }

    @Test
    void shouldGenerateSimpleDTO() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, List<FieldDescriptor>> expected = new HashMap<>(
                Map.ofEntries(
                        Map.entry(
                                DTOGenerator.generateFullConfigName(SimpleModel.class, "MOBILE"),
                                Collections.emptyList()
                        ),
                        Map.entry(
                                DTOGenerator.generateFullConfigName(SimpleModel.class, "WEB"),
                                List.of(
                                        fieldBuilder.start()
                                                    .originalName("web")
                                                    .mappedName("web_name")
                                                    .type(String.class)
                                                    .predicates(new Constraint[0])
                                                    .build()
                                ))
                )
        );
        Method processingClassMethod = getProcessingClassMethod(dtoGenerator);
        Map<String, List<FieldDescriptor>> result = (Map<String, List<FieldDescriptor>>)
                processingClassMethod.invoke(dtoGenerator, SimpleModel.class);
        checkEqMapConfigs(expected, result);
    }

    @Test
    void shouldGenerateValidConfig() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, List<FieldDescriptor>> expected = new HashMap<>(
                Map.ofEntries(
                        Map.entry(
                                DTOGenerator.generateFullConfigName(EveryThingModel.class, "WEB"),
                                List.of(
                                        fieldBuilder.start()
                                                    .originalName("attr1")
                                                    .type(String.class)
                                                    .build(),
                                        fieldBuilder.start()
                                                    .originalName("attr7")
                                                    .type(SimpleModel.class)
                                                    .useDTO(
                                                            DTOGenerator.generateFullConfigName(SimpleModel.class,
                                                                                                "WEB")
                                                    )
                                                    .build(),
                                        fieldBuilder.start()
                                                    .originalName("attr2")
                                                    .type(String.class)
                                                    .mappedName("first_email")
                                                    .build(),
                                        fieldBuilder.start()
                                                    .originalName("attr5")
                                                    .type(String.class)
                                                    .build()
                                )
                        ),
                        Map.entry(
                                DTOGenerator.generateFullConfigName(EveryThingModel.class, "MOBILE"),
                                List.of(
                                        fieldBuilder.start()
                                                    .originalName("attr5")
                                                    .type(String.class)
                                                    .build()
                                        ,
                                        fieldBuilder.start()
                                                    .originalName("attr6")
                                                    .type(String.class)
                                                    .build()
                                )
                        ),
                        Map.entry(
                                DTOGenerator.generateFullConfigName(EveryThingModel.class, "DESKTOP"),
                                Collections.emptyList()
                        )
                )
        );

        Method processingClassMethod = getProcessingClassMethod(dtoGenerator);
        Map<String, List<FieldDescriptor>> result = (Map<String, List<FieldDescriptor>>)
                processingClassMethod.invoke(dtoGenerator, EveryThingModel.class);
        checkEqMapConfigs(expected, result);
    }
}

