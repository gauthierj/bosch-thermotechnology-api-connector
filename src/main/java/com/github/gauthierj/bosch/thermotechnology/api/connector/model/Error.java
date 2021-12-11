package com.github.gauthierj.bosch.thermotechnology.api.connector.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.gauthierj.bosch.thermotechnology.api.connector.util.immutable.ImmutableStyle;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@ImmutableStyle
@JsonDeserialize(as = ErrorImpl.class)
public interface Error {

    int code();
    String description();
    List<String> details();
    String errorId();
    String message();

}
