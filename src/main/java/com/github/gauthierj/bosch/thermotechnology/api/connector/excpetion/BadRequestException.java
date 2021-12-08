package com.github.gauthierj.bosch.thermotechnology.api.connector.excpetion;

import com.github.gauthierj.bosch.thermotechnology.api.connector.model.Error;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.ErrorImpl;

public class BadRequestException extends BoschThermotechnologyApiConnectorException {

    private final Error error;
    private final int statusCode;

    public BadRequestException(Error error, int statusCode) {
        super("Bad request");
        this.error = error;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Error getError() {
        return error;
    }
}
