package com.practice.sber_practice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.sber_practice.services.RequestProcessService;
import com.practice.sber_practice.pojo_scheme.request.ServiceRequest;
import com.practice.sber_practice.pojo_scheme.response.ServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final RequestProcessService requestProcessService;

    @PostMapping("/")
    public ServiceResponse handleServiceRequest(@RequestBody String requestString) throws JsonProcessingException {

        ServiceResponse response = requestProcessService.processRequest(requestString);

        return response;
    }
}
