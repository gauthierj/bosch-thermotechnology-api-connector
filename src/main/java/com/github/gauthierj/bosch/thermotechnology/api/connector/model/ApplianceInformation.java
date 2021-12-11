package com.github.gauthierj.bosch.thermotechnology.api.connector.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.gauthierj.bosch.thermotechnology.api.connector.util.immutable.ImmutableStyle;
import com.github.gauthierj.bosch.thermotechnology.api.connector.util.immutable.Nullable;
import org.immutables.value.Value;

@Value.Immutable
@ImmutableStyle
@JsonDeserialize(as = ApplianceInformationImpl.class)
public interface ApplianceInformation {

    @Nullable
    String brand();
    String causeCode();
    String displayCode();
    @Nullable
    String serialNumber();
    @Nullable
    String type();
    @Nullable
    String version();

}
