package com.github.gauthierj.bosch.thermotechnology.api.connector.authorization;

import java.util.Scanner;

public class CredentialsProviderImpl implements CredentialsProvider {

    @Override
    public String getBoschId() {
        if(System.console() != null) {
            return new String(System.console().readLine("boschId>"));
        }
        Scanner scanner = new Scanner(System.in);
        System.out.printf("boschId>");
        return scanner.nextLine();
    }

    @Override
    public String getPassword() {
        if(System.console() != null) {
            return new String(System.console().readPassword("password>"));
        }
        Scanner scanner = new Scanner(System.in);
        System.out.printf("password>");
        return scanner.nextLine();
    }
}
