package com.vitosak.processor;

import com.vitosak.annotations.SimpleDTO;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@SupportedAnnotationTypes("com.vitosak.annotations.SimpleDTO")
public class Processor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        annotations.forEach(annotation -> {
            SimpleDTO simpleDTO = annotation.getAnnotation(SimpleDTO.class);
        })
    }
}
