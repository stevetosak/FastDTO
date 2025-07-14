package com.vitosak.processor;

import com.vitosak.core.ConfigRepo;
import com.vitosak.core.FieldDescriptor;
import com.vitosak.core.FieldDescriptorBuilder;
import com.vitosak.exceptions.MultipleMappings;
import models.CollectionModel;
import models.EveryThingModel;
import models.MultipleMappingsToSameDTO;
import models.SimpleModel;
import jakarta.validation.Constraint;
import org.junit.jupiter.api.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.*;


public class TestDTOGenerator {


    static ConfigRepo configRepo;
    DTOGenerator dtoGenerator;

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
        System.out.println("===== EXPECTED =====");
        System.out.println(expected);
        System.out.println("===== RESULT =====");
        System.out.println(result);
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
                    throw new AssertionError(expectedFields.get(i) + " != " + resultFields.get(i));
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
                                        FieldDescriptor.builder()
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
                                        FieldDescriptor.builder()
                                                .originalName("attr1")
                                                .type(String.class)
                                                .build(),
                                        FieldDescriptor.builder()
                                                .originalName("attr7")
                                                .type(SimpleModel.class)
                                                .useDTO(
                                                        DTOGenerator.generateFullConfigName(SimpleModel.class,
                                                                "WEB")
                                                )
                                                .build(),
                                        FieldDescriptor.builder().originalName("attr2")
                                                .type(String.class)
                                                .mappedName("first_email")
                                                .build(),
                                        FieldDescriptor.builder()
                                                .originalName("attr5")
                                                .type(String.class)
                                                .build()
                                )
                        ),
                        Map.entry(
                                DTOGenerator.generateFullConfigName(EveryThingModel.class, "MOBILE"),
                                List.of(
                                        FieldDescriptor.builder()
                                                .originalName("attr5")
                                                .type(String.class)
                                                .build()
                                        ,
                                        FieldDescriptor.builder()
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

    @Test
    public void testConfigForCollectionTypes() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, List<FieldDescriptor>> expected = new HashMap<>(
                Map.ofEntries(
                        Map.entry(DTOGenerator.generateFullConfigName(CollectionModel.class, "WEB"),
                                List.of(
                                        FieldDescriptor.builder()
                                                .originalName("attrs")
                                                .isCollection(true)
                                                .type(String.class)
                                                .build(),
                                        FieldDescriptor.builder()
                                                .originalName("simpleModels")
                                                .type(SimpleModel.class)
                                                .isCollection(true)
                                                .useDTO("WEB")
                                                .build()
                                        ,
                                        FieldDescriptor.builder()
                                                .originalName("id")
                                                .type(Integer.class)
                                                .isCollection(false)
                                                .build()
                                        ,
                                        FieldDescriptor.builder()
                                                .originalName("set")
                                                .isCollection(true)
                                                .type(Integer.class)
                                                .build(),

                                        FieldDescriptor.builder()
                                                .originalName("compositeCollection")
                                                .type(Map.class)
                                                .isCollection(true)
                                                .build()

                                )
                        ),
                        Map.entry(
                                DTOGenerator.generateFullConfigName(CollectionModel.class, "REGISTER"),
                                new ArrayList<>()
                        )

                )
        );

        Method processingClassMethod = getProcessingClassMethod(dtoGenerator);
        @SuppressWarnings("unchecked")
        Map<String, List<FieldDescriptor>> result = (Map<String, List<FieldDescriptor>>)
                processingClassMethod.invoke(dtoGenerator, CollectionModel.class);
        checkEqMapConfigs(expected, result);
    }
}

