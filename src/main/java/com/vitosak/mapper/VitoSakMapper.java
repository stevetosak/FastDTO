package com.vitosak.mapper;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class VitoSakMapper {
    public <T> HashMap toDTO(Class<T> object, String dtoConfig) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("It's not implemented");
    }
}
