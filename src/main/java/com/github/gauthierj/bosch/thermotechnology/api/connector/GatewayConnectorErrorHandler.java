package com.github.gauthierj.bosch.thermotechnology.api.connector;

import com.github.gauthierj.bosch.thermotechnology.api.connector.excpetion.BadRequestException;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.Error;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.ErrorImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.util.List;

public class GatewayConnectorErrorHandler implements ResponseErrorHandler {

    private final DefaultResponseErrorHandler defaultResponseErrorHandler;
    private final HttpMessageConverter messageConverter;

    public GatewayConnectorErrorHandler() {
        this.messageConverter = new MappingJackson2HttpMessageConverter();
        this.defaultResponseErrorHandler = new DefaultResponseErrorHandler();
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return defaultResponseErrorHandler.hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if(List.of(HttpStatus.NOT_FOUND, HttpStatus.BAD_REQUEST).contains(response.getStatusCode())) {
            throw new BadRequestException((Error) messageConverter.read(Error.class, response), response.getStatusCode().value());
        }
        defaultResponseErrorHandler.handleError(response);
    }

    protected byte[] getResponseBody(ClientHttpResponse response) {
        try {
            return FileCopyUtils.copyToByteArray(response.getBody());
        }
        catch (IOException ex) {
            // ignore
        }
        return new byte[0];
    }
}
