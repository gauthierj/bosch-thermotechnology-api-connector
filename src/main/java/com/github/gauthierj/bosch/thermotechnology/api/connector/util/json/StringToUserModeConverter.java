package com.github.gauthierj.bosch.thermotechnology.api.connector.util.json;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.UserMode;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.ValueType;

import java.util.Map;

public class StringToUserModeConverter extends StdConverter<String, UserMode> {

    public static final String CLOCK = "clock";
    public static final String MANUAL = "manual";

    private final Map<String, UserMode> conversionMap = Map.of(
            CLOCK, UserMode.CLOCK,
            MANUAL, UserMode.MANUAL);

    @Override
    public UserMode convert(String value) {
        return conversionMap.get(value);
    }
}
