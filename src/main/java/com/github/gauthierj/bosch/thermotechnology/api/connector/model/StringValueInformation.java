package com.github.gauthierj.bosch.thermotechnology.api.connector.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@org.immutables.value.Value.Immutable
@ImmutableStyle
@JsonDeserialize(as = StringValueInformationImpl.class)
public interface StringValueInformation extends ValueInformation<String> {
    boolean used();
}
