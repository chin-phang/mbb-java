package com.mbb.assessment.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final ExternalApi externalApi = new ExternalApi();

    @Data
    public static class ExternalApi {
        private String auth;
    }
}
