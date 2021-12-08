package com.github.gauthierj.bosch.thermotechnology.api.connector.authorization;

import com.github.gauthierj.bosch.thermotechnology.api.connector.AuthorizationProvider;

import java.io.Closeable;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class RenewableAuthorizationProvider implements AuthorizationProvider, Closeable {

    private final AuthorizationProvider delegate;
    private final ScheduledExecutorService executorService;

    private final AtomicReference<String> authorization = new AtomicReference<>("Not authorized");
    private final AtomicReference<LocalDateTime> expirationDateTime = new AtomicReference<>(LocalDateTime.now());

    public RenewableAuthorizationProvider(AuthorizationProvider delegate) {
        this.delegate = delegate;
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        renew();
        executorService.scheduleAtFixedRate(() -> renewIfNeeded(), 10, 1, TimeUnit.MINUTES);
    }

    @Override
    public String getAuthorization() {
        return authorization.get();
    }

    private synchronized void renewIfNeeded() {
        if(isAboutToExpire()) {
            renew();
        }
    }

    private void renew() {
        System.out.println("Renewing token at: " + LocalDateTime.now());
        authorization.set(delegate.getAuthorization());
        expirationDateTime.set(LocalDateTime.now().plus(45, ChronoUnit.MINUTES));
        System.out.println("Current token: " + authorization.get());
    }

    private boolean isAboutToExpire() {
        return expirationDateTime.get().minus(10, ChronoUnit.MINUTES).isBefore(LocalDateTime.now());
    }

    @Override
    public void close() throws IOException {
        this.executorService.shutdownNow();
    }
}
