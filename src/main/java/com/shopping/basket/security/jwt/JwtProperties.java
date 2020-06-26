package com.shopping.basket.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtProperties {
    private String	secretKey		= "hNIpwmDFLGrpaHS1uUgjnBjv4WJ480O7";

    //24 hours validity
    private long	validityInMs	= 3600000 * 24;

    public String getSecretKey() {
        return this.secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public long getValidityInMs() {
        return this.validityInMs;
    }

    public void setValidityInMs(long validityInMs) {
        this.validityInMs = validityInMs;
    }
}
