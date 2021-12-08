package com.github.gauthierj.bosch.thermotechnology.api.connector.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@ImmutableStyle
@JsonDeserialize(as = GatewayImpl.class)
public interface Gateway {

    String deviceId();
    String deviceType();

}
