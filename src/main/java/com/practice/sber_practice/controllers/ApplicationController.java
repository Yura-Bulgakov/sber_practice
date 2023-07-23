package com.practice.sber_practice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.practice.sber_practice.services.AsyncRequestProcessService;
import com.practice.sber_practice.services.RequestProcessService;
import com.practice.sber_practice.pojo_scheme.response.ServiceResponse;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

@RestController
public class ApplicationController {

    @Autowired
    private final AsyncRequestProcessService requestProcessService;

    private final Bucket bucket;


    public ApplicationController(AsyncRequestProcessService requestProcessService){
        this.requestProcessService =  requestProcessService;
        Bandwidth limit = Bandwidth.classic(3, Refill.greedy(3, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @PostMapping("/")
    public ResponseEntity<ServiceResponse> handleServiceRequest(@RequestBody String requestString) throws JsonProcessingException {
        if (bucket.tryConsume(1)){
            CompletableFuture<ServiceResponse> futureResult = requestProcessService.processRequest(requestString);
            ServiceResponse result = futureResult.join();
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}
