package com.github.gauthierj.bosch.thermotechnology.api.connector.excpetion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NotFoundException extends BoschThermotechnologyApiConnectorException {

    private final Class<?> objectClass;
    private final List<Object> idComponents;

    public NotFoundException(String message, Class<?> objectClass, Object ... idComponents) {
        super("Object Not found");
        this.objectClass = objectClass;
        this.idComponents = Optional.ofNullable(idComponents)
                .map(List::of)
                .orElse(List.of());
    }

    public Class<?> getObjectClass() {
        return objectClass;
    }

    public List<Object> getIdComponents() {
        return idComponents;
    }
}
