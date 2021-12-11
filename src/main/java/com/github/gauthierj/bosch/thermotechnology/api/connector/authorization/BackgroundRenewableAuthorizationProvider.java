package com.github.gauthierj.bosch.thermotechnology.api.connector.authorization;

import com.github.gauthierj.bosch.thermotechnology.api.connector.AuthorizationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class BackgroundRenewableAuthorizationProvider implements AuthorizationProvider, Closeable {

    private static final Logger LOGGER = LoggerFactory.getLogger(BackgroundRenewableAuthorizationProvider.class);

    private final AuthorizationProvider delegate;
    private final ScheduledExecutorService executorService;

    private final long renewInitialDelayMillis;
    private final long renewCheckPeriodMillis;
    private final long authorizationExpirationDelayMillis;
    private final long renewDelayBeforeExpirationMillis;

    private final AtomicReference<String> authorization = new AtomicReference<>("Not authorized");
    private final AtomicReference<LocalDateTime> expirationDateTime = new AtomicReference<>(LocalDateTime.now());

    public BackgroundRenewableAuthorizationProvider(AuthorizationProvider delegate) {
        this(delegate,
                10 * 60 * 1000L, // 10 minutes
                1 * 60 * 1000L, // 1 minutes
                45 * 60 * 1000L // 45 minutes
        );
    }

    public BackgroundRenewableAuthorizationProvider(AuthorizationProvider delegate,
                                                    long renewInitialDelayMillis,
                                                    long renewCheckPeriodMillis,
                                                    long authorizationExpirationDelayMillis) {
        this.delegate = delegate;
        this.renewInitialDelayMillis = renewInitialDelayMillis;
        this.renewCheckPeriodMillis = renewCheckPeriodMillis;
        this.authorizationExpirationDelayMillis = authorizationExpirationDelayMillis;
        this.renewDelayBeforeExpirationMillis = (long) Math.floor(0.1d * authorizationExpirationDelayMillis);

        this.executorService = Executors.newSingleThreadScheduledExecutor();
        renew();
        executorService.scheduleAtFixedRate(() -> renewIfNeeded(), renewInitialDelayMillis, renewCheckPeriodMillis, TimeUnit.MILLISECONDS);
    }

    @Override
    public String getAuthorization() {
        return authorization.get();
    }

    @Override
    public void close() throws IOException {
        this.executorService.shutdownNow();
    }

    private synchronized void renewIfNeeded() {
        if(isAboutToExpire()) {
            renew();
        }
    }

    private void renew() {
        LOGGER.trace("Renewing token");
        authorization.set(delegate.getAuthorization());
        expirationDateTime.set(LocalDateTime.now().plus(authorizationExpirationDelayMillis, ChronoUnit.MILLIS));
        LOGGER.trace("Token renewed");
    }

    private boolean isAboutToExpire() {
        return expirationDateTime.get().minus(renewDelayBeforeExpirationMillis, ChronoUnit.MILLIS).isBefore(LocalDateTime.now());
    }
}
