package com.practice.sber_practice.rest;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class RestUriProperties {

    private String scheme;
    private String host;
    private String path;
    private Integer port;
    private Integer timeout;
}
