package com.github.gauthierj.bosch.thermotechnology.api.connector.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.gauthierj.bosch.thermotechnology.api.connector.util.immutable.ImmutableStyle;
import com.github.gauthierj.bosch.thermotechnology.api.connector.util.json.StringToUserModeConverter;

@org.immutables.value.Value.Immutable
@ImmutableStyle
@JsonDeserialize(as = UserModeValueInformationImpl.class)
public interface UserModeValueInformation extends ValueInformation<UserMode> {
    boolean used();

    @JsonDeserialize(converter = StringToUserModeConverter.class)
    @Override
    UserMode value();
}
