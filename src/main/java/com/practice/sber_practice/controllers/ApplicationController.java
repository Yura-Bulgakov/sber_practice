package com.practice.sber_practice.controllers;

import com.practice.sber_practice.services.RequestProcessService;
import com.practice.sber_practice.pojo_scheme.request.ServiceRequest;
import com.practice.sber_practice.pojo_scheme.response.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    private final RequestProcessService requestProcessService;

    @Autowired
    public ApplicationController(RequestProcessService requestProcessService){
        this.requestProcessService = requestProcessService;
    }
    @PostMapping("/")
    public ServiceResponse handleServiceRequest(@RequestBody ServiceRequest request){
       ServiceResponse response = requestProcessService.processRequest(request);

        return response;
    }
}
