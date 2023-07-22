package com.practice.sber_practice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.practice.sber_practice.pojo_scheme.response.ServiceResponse;
import com.practice.sber_practice.pojo_totalsum.request.TotalSumRq;
import com.practice.sber_practice.pojo_totalsum.response.TotalSumRs;
import com.practice.sber_practice.utils.TotalSumResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OuterServiceController {

//    @RequestBody TotalSumRq totalSumRq

    @GetMapping("/extract")
    public TotalSumRs handleServiceRequest() throws JsonProcessingException {
        TotalSumRs response = TotalSumResponseGenerator.generateTotalSumResponce();

        return response;
    }
}
