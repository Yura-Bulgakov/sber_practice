package com.practice.sber_practice.rest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Slf4j
public abstract class CommonRestReceiver<T, U> {

    private static final String ERROR_GETTING_DATA = "Error while getting data from ";

    protected final RestUriProperties restUriProperties;
    private final RestTemplate restTemplate;
    private final String restPath;

    protected CommonRestReceiver(RestUriProperties restUriProperties,
                                 RestTemplate restTemplate,
                                 String restPath) {
        this.restUriProperties = restUriProperties;
        this.restTemplate = restTemplate;
        this.restPath = StringUtils.isNotEmpty(restUriProperties.getPath()) ? restUriProperties.getPath() : restPath;
    }

    private UriComponentsBuilder getUriComponentsBuilder() {
        UriComponentsBuilder uriComponentsBuilder;
        uriComponentsBuilder = UriComponentsBuilder.newInstance()
                .scheme(restUriProperties.getScheme())
                .host(restUriProperties.getHost())
                .path(restPath);

        if (Objects.nonNull(restUriProperties.getPort())) {
            uriComponentsBuilder.port(restUriProperties.getPort());
        }
        return uriComponentsBuilder;
    }

    protected T getForDataFromRest(Class<T> responseClass, String message, UriComponentsBuilder uriQueryParamBuilder) throws Exception {
        UriComponentsBuilder uriComponentsBuilder = getUriComponentsBuilder();

        //uriBuilder.setQueryParams(uriComponentsBuilder);
        //uriComponentsBuilder.queryParam(uriBuilder.build().getQuery());
        uriComponentsBuilder.queryParams(uriQueryParamBuilder.build().getQueryParams());

        UriComponents uriComponents = uriComponentsBuilder.build();
        log.debug(message, uriComponents.toUriString());
        try {
            return restTemplate.getForObject(uriComponents.toUriString(), responseClass);
        } catch (RestClientException e) {
            throw new Exception(ERROR_GETTING_DATA + uriComponents.toUriString() + " Get Rest:\n" +
                    ExceptionUtils.getStackTrace(e));
        }
    }
}
