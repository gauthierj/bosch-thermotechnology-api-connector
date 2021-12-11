package com.github.gauthierj.bosch.thermotechnology.api.connector.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.gauthierj.bosch.thermotechnology.api.connector.util.immutable.ImmutableStyle;

@org.immutables.value.Value.Immutable
@ImmutableStyle
public interface FloatValue extends Value<Float> {

    @org.immutables.value.Value.Parameter
    @Override
    @JsonSerialize
    Float value();
}
