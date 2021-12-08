package com.github.gauthierj.bosch.thermotechnology.api.connector.util.json;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.ValueType;

import java.util.Map;

public class StringToValueTypeConverter extends StdConverter<String, ValueType> {

    public static final String STRING_VALUE = "stringValue";
    public static final String FLOAT_VALUE = "floatValue";

    private final Map<String, ValueType> conversionMap = Map.of(
            STRING_VALUE, ValueType.STRING_VALUE,
            FLOAT_VALUE, ValueType.FLOAT_VALUE);

    @Override
    public ValueType convert(String value) {
        return conversionMap.get(value);
    }
}
