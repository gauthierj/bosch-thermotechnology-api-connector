package com.github.gauthierj.bosch.thermotechnology.api.connector.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@org.immutables.value.Value.Immutable
@ImmutableStyle
@JsonDeserialize(as = FloatValueInformationImpl.class)
public interface FloatValueInformation extends ValueInformation<Float> {

    enum TEMPERATURE_UNIT{
        C,F;
    }

    TEMPERATURE_UNIT unitOfMeasure();

    Float minValue();
    Float maxValue();
    Float stepSize();
}
