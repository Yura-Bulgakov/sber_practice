package com.practice.sber_practice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.practice.sber_practice.pojo_scheme.response.ServiceResponse;

import java.util.concurrent.CompletableFuture;

public interface AsyncRequestProcessService {

    public CompletableFuture<ServiceResponse> processRequest(String requestString) throws JsonProcessingException;
}
