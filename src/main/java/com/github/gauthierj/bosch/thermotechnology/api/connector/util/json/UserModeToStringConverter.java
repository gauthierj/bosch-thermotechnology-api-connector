package com.github.gauthierj.bosch.thermotechnology.api.connector.util.json;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.gauthierj.bosch.thermotechnology.api.connector.model.UserMode;

import java.util.Map;

public class UserModeToStringConverter extends StdConverter<UserMode, String> {

    private final Map<UserMode, String> conversionMap = Map.of(
            UserMode.CLOCK, StringToUserModeConverter.CLOCK,
            UserMode.MANUAL, StringToUserModeConverter.MANUAL);

    @Override
    public String convert(UserMode value) {
        return conversionMap.get(value);
    }
}
