package com.vitosak.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigRepo{
    private Map<String, List<FieldDescriptor>> parsingConfigs = new HashMap<>();

    public ConfigRepo() {
    }


    public void createConfig(Map<String, List<FieldDescriptor>> generatedConfig) {
        parsingConfigs.putAll(generatedConfig);
    }
}
