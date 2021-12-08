package com.github.gauthierj.bosch.thermotechnology.api.connector.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@org.immutables.value.Value.Immutable
@ImmutableStyle
@JsonDeserialize(as = FloatValueImpl.class)
public interface FloatValue extends Value<Float> {

    enum TEMPERATURE_UNIT{
        C,F;
    }

    TEMPERATURE_UNIT unitOfMeasure();

    Float minValue();
    Float maxValue();
    Float stepSize();
}
