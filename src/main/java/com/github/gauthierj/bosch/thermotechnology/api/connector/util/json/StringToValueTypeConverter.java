package com.github.gauthierj.bosch.thermotechnology.api.connector.util.json;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.Value;

import java.util.Map;

public class StringToValueTypeConverter extends StdConverter<String, Value.ValueType> {

    public static final String STRING_VALUE = "stringValue";
    public static final String FLOAT_VALUE = "floatValue";

    private final Map<String, Value.ValueType> conversionMap = Map.of(
            STRING_VALUE, Value.ValueType.STRING_VALUE,
            FLOAT_VALUE, Value.ValueType.FLOAT_VALUE);

    @Override
    public Value.ValueType convert(String value) {
        return conversionMap.get(value);
    }
}
