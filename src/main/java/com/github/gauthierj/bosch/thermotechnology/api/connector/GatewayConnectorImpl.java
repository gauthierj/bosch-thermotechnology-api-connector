package com.github.gauthierj.bosch.thermotechnology.api.connector;

import com.github.gauthierj.bosch.thermotechnology.api.connector.authorization.CredentialsProviderImpl;
import com.github.gauthierj.bosch.thermotechnology.api.connector.authorization.RenewableAuthorizationProvider;
import com.github.gauthierj.bosch.thermotechnology.api.connector.authorization.WebBearerTokenProvider;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.ApplianceInformation;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.FloatValueImpl;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.FloatValueInformation;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.Gateway;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.GatewayInformation;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.StringValueInformation;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.UserMode;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.UserModeValueImpl;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.UserModeValueInformation;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
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

    @Override
    public List<Gateway> getGateways() {
        ResponseEntity<List<Gateway>> exchange = restTemplate.exchange(configuration.getApiUrl() + "/gateways",
                HttpMethod.GET,
                getHttpEntity(),
                new ParameterizedTypeReference<List<Gateway>>() {
                });
        return exchange.getBody();
    }

    @Override
    public GatewayInformation getGatewayInformation(Gateway gateway) {
        return restTemplate.exchange(configuration.getApiUrl() + "/gateways/{deviceId}",
                HttpMethod.GET,
                getHttpEntity(),
                GatewayInformation.class,
                gateway.deviceId()).getBody();
    }

    @Override
    public ApplianceInformation getApplianceInformation(Gateway gateway) {
        return restTemplate.exchange(configuration.getApiUrl() + "/gateways/{deviceId}/applianceInfo",
                HttpMethod.GET,
                getHttpEntity(),
                ApplianceInformation.class,
                gateway.deviceId()).getBody();
    }

    @Override
    public UserModeValueInformation getCurrentUserMode(Gateway gateway, String zoneId) {
        return restTemplate.exchange(configuration.getApiUrl() + "/gateways/{deviceId}/resource/zones/{zoneId}/userMode",
                HttpMethod.GET,
                getHttpEntity(),
                UserModeValueInformation.class,
                gateway.deviceId(),
                zoneId).getBody();
    }

    @Override
    public void setCurrentUserMode(Gateway gateway, String zoneId, UserMode userMode) {
        restTemplate.exchange(configuration.getApiUrl() + "/gateways/{deviceId}/resource/zones/{zoneId}/userMode",
                HttpMethod.PUT,
                getHttpEntity(UserModeValueImpl.of(userMode)),
                Void.class,
                gateway.deviceId(),
                zoneId);
    }

    @Override
    public FloatValueInformation getClockOverrideTemperatureHeating(Gateway gateway, String zoneId) {
        return restTemplate.exchange(configuration.getApiUrl() + "/gateways/{deviceId}/resource/zones/{zoneId}/clockOverride/temperatureHeating",
                HttpMethod.GET,
                getHttpEntity(),
                FloatValueInformation.class,
                gateway.deviceId(),
                zoneId).getBody();
    }

    @Override
    public void setClockOverrideTemperatureHeating(Gateway gateway, String zoneId, float temperature) {
        ResponseEntity<ResponseEntity> exchange = restTemplate.exchange(configuration.getApiUrl() + "/gateways/{deviceId}/resource/zones/{zoneId}/clockOverride/temperatureHeating",
                HttpMethod.PUT,
                getHttpEntity(FloatValueImpl.of(temperature)),
                ResponseEntity.class,
                gateway.deviceId(),
                zoneId);

        System.out.println(exchange);
    }

    @Override
    public FloatValueInformation getTemperatureHeatingSetpoint(Gateway gateway, String zoneId) {
        return restTemplate.exchange(configuration.getApiUrl() + "/gateways/{deviceId}/resource/zones/{zoneId}/temperatureHeatingSetpoint",
                HttpMethod.GET,
                getHttpEntity(),
                FloatValueInformation.class,
                gateway.deviceId(),
                zoneId).getBody();
    }

    @Override
    public FloatValueInformation getManualTemperatureHeating(Gateway gateway, String zoneId) {
        return restTemplate.exchange(configuration.getApiUrl() + "/gateways/{deviceId}/resource/zones/{zoneId}/manualTemperatureHeating",
                HttpMethod.GET,
                getHttpEntity(),
                FloatValueInformation.class,
                gateway.deviceId(),
                zoneId).getBody();
    }

    @Override
    public void setManualTemperatureHeating(Gateway gateway, String zoneId, float temperature) {
        restTemplate.exchange(configuration.getApiUrl() + "/gateways/{deviceId}/resource/zones/{zoneId}/manualTemperatureHeating",
                HttpMethod.PUT,
                getHttpEntity(FloatValueImpl.of(temperature)),
                Void.class,
                gateway.deviceId(),
                zoneId);
    }

    @Override
    public FloatValueInformation getActualTemperature(Gateway gateway, String zoneId) {
        return restTemplate.exchange(configuration.getApiUrl() + "/gateways/{deviceId}/resource/zones/{zoneId}/temperatureActual",
                HttpMethod.GET,
                getHttpEntity(),
                FloatValueInformation.class,
                gateway.deviceId(),
                zoneId).getBody();
    }

    // --------------------------------------------------------------------------------------------------------
    // Private implementation

    private HttpEntity<?> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authorizationProvider.getAuthorization());
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        return httpEntity;
    }

    private <T> HttpEntity<T> getHttpEntity(T body) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authorizationProvider.getAuthorization());
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<T>(body, headers);
    }
}
