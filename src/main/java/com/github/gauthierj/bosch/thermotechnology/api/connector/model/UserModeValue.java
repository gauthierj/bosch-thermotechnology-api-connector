package com.github.gauthierj.bosch.thermotechnology.api.connector.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.gauthierj.bosch.thermotechnology.api.connector.util.json.StringToUserModeConverter;
import com.github.gauthierj.bosch.thermotechnology.api.connector.util.json.UserModeToStringConverter;

@org.immutables.value.Value.Immutable
@ImmutableStyle
@JsonDeserialize(as = UserModeValueImpl.class)
public interface UserModeValue extends Value<UserMode> {

    @org.immutables.value.Value.Parameter
    @JsonSerialize(converter = UserModeToStringConverter.class)
    @Override
    UserMode value();
}
