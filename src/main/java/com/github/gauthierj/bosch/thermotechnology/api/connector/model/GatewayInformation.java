package com.github.gauthierj.bosch.thermotechnology.api.connector.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.gauthierj.bosch.thermotechnology.api.connector.util.immutable.ImmutableStyle;
import com.github.gauthierj.bosch.thermotechnology.api.connector.util.immutable.Nullable;
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
