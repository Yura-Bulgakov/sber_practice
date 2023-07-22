package com.practice.sber_practice.rest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TotalSumRestConfig {

    private static final String TOTALSUM_REST_SCHEME = "http";
    private static final String TOTALSUM_REST_HOST = "localhost";
    private static final String TOTALSUM_REST_PATH = "/extract";
    private static final Integer TOTALSUM_REST_PORT = 8080;
    private static final Integer TOTALSUM_REST_TIMEOUT = 1000;

    @Bean("TotalSumRestUriProperties")
    public RestUriProperties restUriProperties(){
        RestUriProperties restUriProperties = new RestUriProperties();
        restUriProperties.setScheme(TOTALSUM_REST_SCHEME);
        restUriProperties.setHost(TOTALSUM_REST_HOST);
        restUriProperties.setPath(TOTALSUM_REST_PATH);
        restUriProperties.setPort(TOTALSUM_REST_PORT);
        restUriProperties.setTimeout(TOTALSUM_REST_TIMEOUT);
        return restUriProperties;
    }

    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000); // тайм-аут подключения (в миллисекундах)
        factory.setReadTimeout(5000); // тайм-аут чтения (в миллисекундах)

        RestTemplate restTemplate = new RestTemplate(factory);

        return restTemplate;
    }

}
