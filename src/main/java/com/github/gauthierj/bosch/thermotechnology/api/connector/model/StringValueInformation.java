package com.github.gauthierj.bosch.thermotechnology.api.connector.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.gauthierj.bosch.thermotechnology.api.connector.util.immutable.ImmutableStyle;

@org.immutables.value.Value.Immutable
@ImmutableStyle
@JsonDeserialize(as = StringValueInformationImpl.class)
public interface StringValueInformation extends ValueInformation<String> {
    boolean used();
}
