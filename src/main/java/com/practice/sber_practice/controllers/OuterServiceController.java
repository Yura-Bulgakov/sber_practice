package com.practice.sber_practice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.practice.sber_practice.pojo_scheme.response.ServiceResponse;
import com.practice.sber_practice.pojo_totalsum.request.TotalSumRq;
import com.practice.sber_practice.pojo_totalsum.response.TotalSumRs;
import com.practice.sber_practice.services.RequestProcessService;
import com.practice.sber_practice.utils.TotalSumResponseGenerator;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@Slf4j
public class OuterServiceController {

//    @RequestBody TotalSumRq totalSumRq
    private final Bucket bucket;

    public OuterServiceController(){
        Bandwidth limit = Bandwidth.classic(3, Refill.greedy(3, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @GetMapping("/extract")
    public ResponseEntity<TotalSumRs> handleServiceRequest(@RequestParam("requestID") String requestID) throws JsonProcessingException {

        if (bucket.tryConsume(1)){
            log.info("Обработка запроса с id {} во внешней системе", requestID);
            return ResponseEntity.ok(TotalSumResponseGenerator.generateTotalSumResponce());
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();

    }
}
