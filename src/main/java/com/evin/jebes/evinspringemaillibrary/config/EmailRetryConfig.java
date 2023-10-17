package com.evin.jebes.evinspringemaillibrary.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class EmailRetryConfig {

    @Value("${email.retry.maxAttempts}")
    private int maxAttempts;
}
