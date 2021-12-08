package com.github.gauthierj.bosch.thermotechnology.api.connector.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.gauthierj.bosch.thermotechnology.api.connector.util.json.StringToValueTypeConverter;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        visible = true,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = StringValue.class, name = StringToValueTypeConverter.STRING_VALUE),
        @JsonSubTypes.Type(value = FloatValue.class, name = StringToValueTypeConverter.FLOAT_VALUE)
})
public interface Value<T> {

    enum ValueType {
        STRING_VALUE, FLOAT_VALUE
    }

    String id();

    @JsonDeserialize(converter = StringToValueTypeConverter.class)
    ValueType type();

    boolean writeable();

    boolean recordable();

    T value();
}
