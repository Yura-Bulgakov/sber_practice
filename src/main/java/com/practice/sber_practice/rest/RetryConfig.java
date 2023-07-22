package com.practice.sber_practice.rest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@EnableRetry
public class RetryConfig {

    private static final int MAX_RETRY_ATTEMPTS = 3;
    private static final long BACKOFF_INTERVAL_MS = 1000; // 1 second

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        // Configure the retry policy
        RetryPolicy retryPolicy = new SimpleRetryPolicy(MAX_RETRY_ATTEMPTS);
        retryTemplate.setRetryPolicy(retryPolicy);

        // Configure the back-off policy
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(BACKOFF_INTERVAL_MS);
        retryTemplate.setBackOffPolicy(backOffPolicy);

        return retryTemplate;
    }
}