package com.github.gauthierj.bosch.thermotechnology.api.connector.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.gauthierj.bosch.thermotechnology.api.connector.util.json.StringToValueTypeConverter;

public interface ValueInformation<T> extends Value<T> {

    String id();

    @JsonDeserialize(converter = StringToValueTypeConverter.class)
    ValueType type();

    boolean writeable();

    boolean recordable();
}
