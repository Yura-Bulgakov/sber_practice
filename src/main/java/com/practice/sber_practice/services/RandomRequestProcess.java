package com.practice.sber_practice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.sber_practice.data.storage.DataStore;
import com.practice.sber_practice.data.ProcessData;
import com.practice.sber_practice.pojo_scheme.request.ServiceRequest;
import com.practice.sber_practice.pojo_scheme.response.*;
import com.practice.sber_practice.utils.RandomParentIdGenerator;
import com.practice.sber_practice.utils.RandomResponseGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class RandomRequestProcess implements RequestProcessService {

    private final DataStore dataStore;

    @Autowired
    public RandomRequestProcess(DataStore dataStore){this.dataStore = dataStore;}

    public ServiceResponse processRequest (String requestString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        ServiceRequest request = objectMapper.readValue(requestString, ServiceRequest.class);

        ProcessData processData = ProcessData.builder().build();
        processData.setServiceRequest(request);
        String parentId = RandomParentIdGenerator.getParentId(5);
        dataStore.put(parentId, processData);
        ServiceResponse serviceResponse = RandomResponseGenerator.generateRandomResponse();
        processData.setServiceResponse(serviceResponse);
        String responseMessage = dataStore.get(parentId).getServiceResponse().toString();
        dataStore.remove(parentId);
        return serviceResponse;


    }

}

