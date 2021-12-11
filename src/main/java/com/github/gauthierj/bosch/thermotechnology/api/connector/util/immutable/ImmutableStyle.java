package com.github.gauthierj.bosch.thermotechnology.api.connector.util.immutable;

import org.immutables.value.Value;

@Value.Style(get = "*", privateNoargConstructor = true, typeImmutable = "*Impl")
public @interface ImmutableStyle {
}
