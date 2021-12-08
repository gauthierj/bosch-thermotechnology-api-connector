package com.github.gauthierj.bosch.thermotechnology.api.connector;

import com.github.gauthierj.bosch.thermotechnology.api.connector.model.ApplianceInformation;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.FloatValueInformation;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.Gateway;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.GatewayInformation;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.StringValueInformation;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.UserMode;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.UserModeValue;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.UserModeValueInformation;

import java.util.List;

public interface GatewayConnector {

    List<Gateway> getGateways();
    GatewayInformation getGatewayInformation(Gateway gateway);
    ApplianceInformation getApplianceInformation(Gateway gateway);

    UserModeValueInformation getCurrentUserMode(Gateway gateway, String zoneId);
    void setCurrentUserMode(Gateway gateway, String zoneId, UserMode userMode);

    FloatValueInformation getClockOverrideTemperatureHeating(Gateway gateway, String zoneId);
    void setClockOverrideTemperatureHeating(Gateway gateway, String zoneId, float temperature);

    FloatValueInformation getTemperatureHeatingSetpoint(Gateway gateway, String zoneId);

    FloatValueInformation getManualTemperatureHeating(Gateway gateway, String zoneId);
    void setManualTemperatureHeating(Gateway gateway, String zoneId, float temperature);

    FloatValueInformation getActualTemperature(Gateway gateway, String zoneId);
}
