package com.github.gauthierj.bosch.thermotechnology.api.connector;

import com.github.gauthierj.bosch.thermotechnology.api.connector.authorization.CredentialsProviderImpl;
import com.github.gauthierj.bosch.thermotechnology.api.connector.authorization.RenewableAuthorizationProvider;
import com.github.gauthierj.bosch.thermotechnology.api.connector.authorization.WebBearerTokenProvider;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.ApplianceInformation;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.FloatValue;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.Gateway;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.GatewayInformation;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.StringValue;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GatewayConnectorImpl implements GatewayConnector {

    private final GatewayConnectorConfiguration configuration;
    private final AuthorizationProvider authorizationProvider;
    private final RestTemplate restTemplate;

    public GatewayConnectorImpl(GatewayConnectorConfiguration configuration, AuthorizationProvider authorizationProvider) {
        this.authorizationProvider = authorizationProvider;
        this.restTemplate = new RestTemplate();
        this.restTemplate.setErrorHandler(new GatewayConnectorErrorHandler());
        this.configuration = configuration;
    }

    public List<Gateway> getGateways() {
        ResponseEntity<List<Gateway>> exchange = restTemplate.exchange(configuration.getApiUrl() + "/gateways",
                HttpMethod.GET,
                getHttpEntity(),
                new ParameterizedTypeReference<List<Gateway>>() {
                });
        return exchange.getBody();
    }

    private HttpEntity<?> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authorizationProvider.getAuthorization());
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        return httpEntity;
    }

    public GatewayInformation getGatewayInformation(Gateway gateway) {
        return restTemplate.exchange(configuration.getApiUrl() + "/gateways/{deviceId}",
                HttpMethod.GET,
                getHttpEntity(),
                GatewayInformation.class,
                gateway.deviceId()).getBody();
    }

    public ApplianceInformation getApplianceInformation(Gateway gateway) {
        return restTemplate.exchange(configuration.getApiUrl() + "/gateways/{deviceId}/applianceInfo",
                HttpMethod.GET,
                getHttpEntity(),
                ApplianceInformation.class,
                gateway.deviceId()).getBody();
    }

    public StringValue getCurrentUserMode(Gateway gateway, String zoneId) {
        return restTemplate.exchange(configuration.getApiUrl() + "/gateways/{deviceId}/resource/zones/{zoneId}/userMode",
                HttpMethod.GET,
                getHttpEntity(),
                StringValue.class,
                gateway.deviceId(),
                zoneId).getBody();
    }

    public void setCurrentUserMode(Gateway gateway, String zoneId, String userMode) {

    }

    public FloatValue getClockOverrideTemperatureHeating(Gateway gateway, String zoneId) {
        return restTemplate.exchange(configuration.getApiUrl() + "/gateways/{deviceId}/resource/zones/{zoneId}/clockOverride/temperatureHeating",
                HttpMethod.GET,
                getHttpEntity(),
                FloatValue.class,
                gateway.deviceId(),
                zoneId).getBody();
    }

    public String setClockOverrideTemperatureHeating(Gateway gateway, String zoneId, String temperature) {
        return null;
    }

    public FloatValue getTemperatureHeatingSetpoint(Gateway gateway, String zoneId) {
        return restTemplate.exchange(configuration.getApiUrl() + "/gateways/{deviceId}/resource/zones/{zoneId}/temperatureHeatingSetpoint",
                HttpMethod.GET,
                getHttpEntity(),
                FloatValue.class,
                gateway.deviceId(),
                zoneId).getBody();
    }

    public FloatValue getManualTemperatureHeating(Gateway gateway, String zoneId) {
        return restTemplate.exchange(configuration.getApiUrl() + "/gateways/{deviceId}/resource/zones/{zoneId}/manualTemperatureHeating",
                HttpMethod.GET,
                getHttpEntity(),
                FloatValue.class,
                gateway.deviceId(),
                zoneId).getBody();
    }

    public String setManualTemperatureHeating(Gateway gateway, String zoneId) {
        return null;
    }

    public FloatValue getActualTemperature(Gateway gateway, String zoneId) {
        return restTemplate.exchange(configuration.getApiUrl() + "/gateways/{deviceId}/resource/zones/{zoneId}/temperatureActual",
                HttpMethod.GET,
                getHttpEntity(),
                FloatValue.class,
                gateway.deviceId(),
                zoneId).getBody();
    }
}
