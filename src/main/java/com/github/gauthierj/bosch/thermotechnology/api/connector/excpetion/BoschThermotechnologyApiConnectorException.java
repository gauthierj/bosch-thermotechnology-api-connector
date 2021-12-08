package com.github.gauthierj.bosch.thermotechnology.api.connector.excpetion;

public class BoschThermotechnologyApiConnectorException extends RuntimeException {

    public BoschThermotechnologyApiConnectorException(String message) {
        super(message);
    }

    public BoschThermotechnologyApiConnectorException(String message, Throwable cause) {
        super(message, cause);
    }
}
