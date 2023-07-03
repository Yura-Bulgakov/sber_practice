package com.practice.sber_practice;

import com.practice.sber_practice.pojo_scheme.request.ServiceRequest;
import com.practice.sber_practice.pojo_scheme.response.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    private final CacheDataStore cacheDataStore;

    @Autowired
    public ApplicationController(CacheDataStore cacheDataStore){
        this.cacheDataStore = cacheDataStore;
    }
    @PostMapping("/")
    public ServiceResponse handleServiceRequest(@RequestBody ServiceRequest request){
        ProcessData processData = ProcessData.builder().build();
        processData.setServiceRequest(request);
        String parentId = ParentIdGenerator.generateRandomString(10);
        cacheDataStore.getProcessDataConcurrentHashMap().put(parentId,processData);
        ServiceResponse serviceResponse = ServiceResponseGenerator.generateRandomResponse();
        processData.setServiceResponse(serviceResponse);
        String responseMessage = cacheDataStore.getProcessDataConcurrentHashMap().get(parentId).getServiceResponse().toString();
        cacheDataStore.getProcessDataConcurrentHashMap().remove(parentId);

        return serviceResponse;
    }
}
