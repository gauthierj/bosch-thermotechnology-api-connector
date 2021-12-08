package com.github.gauthierj.bosch.thermotechnology.api.connector;

import com.github.gauthierj.bosch.thermotechnology.api.connector.model.ApplianceInformation;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.FloatValue;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.Gateway;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.GatewayInformation;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.StringValue;

import java.util.List;

public interface GatewayConnector {

    List<Gateway> getGateways();
    GatewayInformation getGatewayInformation(Gateway gateway);
    ApplianceInformation getApplianceInformation(Gateway gateway);

    StringValue getCurrentUserMode(Gateway gateway, String zoneId);
    void setCurrentUserMode(Gateway gateway, String zoneId, String userMode);

    FloatValue getClockOverrideTemperatureHeating(Gateway gateway, String zoneId);
    String setClockOverrideTemperatureHeating(Gateway gateway, String zoneId, String temperature);

    FloatValue getTemperatureHeatingSetpoint(Gateway gateway, String zoneId);

    FloatValue getManualTemperatureHeating(Gateway gateway, String zoneId);
    String setManualTemperatureHeating(Gateway gateway, String zoneId);

    FloatValue getActualTemperature(Gateway gateway, String zoneId);
}
