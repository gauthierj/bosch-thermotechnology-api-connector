package com.github.gauthierj.bosch.thermotechnology.api.connector.authorization;

public interface CredentialsProvider {
    String getBoschId();

    String getPassword();
}
