package com.github.gauthierj.bosch.thermotechnology.api.connector.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@org.immutables.value.Value.Immutable
@ImmutableStyle
@JsonDeserialize(as = StringValueImpl.class)
public interface StringValue extends Value<String> {
    boolean used();
}
