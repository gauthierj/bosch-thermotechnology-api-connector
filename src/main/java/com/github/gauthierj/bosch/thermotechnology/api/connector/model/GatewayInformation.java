package com.github.gauthierj.bosch.thermotechnology.api.connector.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@ImmutableStyle
@JsonDeserialize(as = GatewayInformationImpl.class)
public interface GatewayInformation {

    @Nullable
    String brandId();
    String deviceId();
    String deviceType();
    String firmwareVersion();
    String hardwareVersion();
    @Nullable
    String productId();
    @Nullable
    String serialNumber();

}
