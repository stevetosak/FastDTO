package com.vitosak.processor;

import com.vitosak.annotations.DeclareDTOs;
import com.vitosak.annotations.FieldMapping;
import com.vitosak.annotations.FieldMappings;
import com.vitosak.core.*;
import com.vitosak.exceptions.MultipleMappings;
import com.vitosak.utils.Pair;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import static org.reflections.scanners.Scanners.TypesAnnotated;

public class DTOGenerator {
    private final ConfigRepo configStore;
    private FieldDescriptorBuilder fieldBuilder;

    public DTOGenerator(ConfigRepo configStore) {
        this.configStore = configStore;
        this.fieldBuilder = FieldDescriptorBuilder.builder();
    }

    private Set<Class<?>> findDeclareDTOAnnotatedClasses(String pathPackage) {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .forPackages(pathPackage)
        );
        return reflections.get(TypesAnnotated.with(DeclareDTOs.class)
                                             .asClass());
    }

    ///  Processes the FieldMappings into pendingConfig
    private void processAnnotatedField(Field field, Map<String, List<FieldDescriptor>> pendingConfig) {
        if(field.isAnnotationPresent(FieldMapping.class)) {
            FieldMapping annotation = field.getAnnotation(FieldMapping.class);
            pendingConfig.get(generateFullConfigName(field.getDeclaringClass(),annotation.referencesDTO()))
                   .add(FieldDescriptorFactory.createFieldDescriptor(field, annotation));
           return;
        }
        Set<String> visited = new HashSet<>();
        for (FieldMapping fieldMapping : field.getAnnotation(FieldMappings.class).value()) {
            if (visited.contains(fieldMapping.referencesDTO())) {
                throw new MultipleMappings(field.getDeclaringClass());
            }
            visited.add(fieldMapping.referencesDTO());
            pendingConfig.get(generateFullConfigName(field.getDeclaringClass(), fieldMapping.referencesDTO())).add(
                    FieldDescriptorFactory.createFieldDescriptor(field, fieldMapping));
        }
    }

    public static String generateFullConfigName(Class<?> cls, String partialConfigName) {
        return cls.getSimpleName() + "_" + partialConfigName;
    }

    private Map<String, List<FieldDescriptor>> dtoConfigsToMap(String[] dtoConfigs, Class<?> dtoAnnotatedClass) {
        return Arrays.stream(dtoConfigs)
                     .<Pair<String, List<FieldDescriptor>>>map(
                             partialName -> Pair.of(
                                     generateFullConfigName(dtoAnnotatedClass,partialName),
                                     new ArrayList<>())
                     )
                     .collect(Collectors.toMap(
                             p -> p.first,
                             p -> p.second
                     ));
    }

    private Map<String, List<FieldDescriptor>> processClass(Class<?> cls) throws MultipleMappings {
        String[] dtoConfigs =  cls.getAnnotationsByType(DeclareDTOs.class)[0].dtos();
        Map<String, List<FieldDescriptor>> pendingConfig = dtoConfigsToMap(dtoConfigs, cls);

        Arrays.stream(cls.getFields())
              .filter(field -> field.isAnnotationPresent(FieldMappings.class) || field.isAnnotationPresent(FieldMapping.class))
              .forEach(field -> processAnnotatedField(field, pendingConfig));

        return pendingConfig;
    }

    public void processPackages(String pathToPackage) {
        this.findDeclareDTOAnnotatedClasses(pathToPackage)
            .stream()
            .map(this::processClass)
            .forEach(this.configStore::createConfig);
    }
}