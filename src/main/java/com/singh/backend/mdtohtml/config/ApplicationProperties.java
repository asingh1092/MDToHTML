package com.singh.backend.mdtohtml.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = ApplicationProperties.CONFIGURATION_PROPERTY_PREFIX,
        ignoreUnknownFields = false)
public class ApplicationProperties {
    static final String CONFIGURATION_PROPERTY_PREFIX = "application";
    private final Async async = new Async();

    public Async getAsync() {
        return async;
    }
}
