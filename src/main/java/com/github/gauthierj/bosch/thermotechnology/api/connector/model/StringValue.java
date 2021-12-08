package com.github.gauthierj.bosch.thermotechnology.api.connector.model;

@org.immutables.value.Value.Immutable
@ImmutableStyle
public interface StringValue extends Value<String> {

    @org.immutables.value.Value.Parameter
    @Override
    String value();
}
