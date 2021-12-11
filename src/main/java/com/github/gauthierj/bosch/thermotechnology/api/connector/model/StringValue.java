package com.github.gauthierj.bosch.thermotechnology.api.connector.model;

import com.github.gauthierj.bosch.thermotechnology.api.connector.util.immutable.ImmutableStyle;

@org.immutables.value.Value.Immutable
@ImmutableStyle
public interface StringValue extends Value<String> {

    @org.immutables.value.Value.Parameter
    @Override
    String value();
}
