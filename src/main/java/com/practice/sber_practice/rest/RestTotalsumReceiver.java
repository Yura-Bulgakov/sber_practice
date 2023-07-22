package com.practice.sber_practice.rest;

import com.practice.sber_practice.pojo_totalsum.request.TotalSumRq;
import com.practice.sber_practice.pojo_totalsum.response.TotalSumRs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class RestTotalsumReceiver extends CommonRestReceiver<TotalSumRs, TotalSumRq>{

    private static final String TOTALSUM_REST_MESSAGE = "Querying TOTALSUM data with: {}";
    private static final String TOTALSUM_REST_PATH = "/extract";

    @Autowired
    protected RestTotalsumReceiver(@Qualifier("TotalSumRestUriProperties") RestUriProperties restUriProperties, RestTemplate restTemplate) {
        super(restUriProperties, restTemplate, TOTALSUM_REST_PATH);
    }

    public TotalSumRs getTotalsumFromRest(TotalSumRq totalSumRq) throws Exception {
        UriComponentsBuilder uriQueryParamBuilder = UriComponentsBuilder.newInstance()
                .queryParam("requestID", totalSumRq.getRequestId())
                .queryParam("personFirstName", totalSumRq.getRequestId())
                .queryParam("personLastName", totalSumRq.getRequestId())
                .queryParam("personMiddleName", totalSumRq.getRequestId())
                .queryParam("personBirthDate", totalSumRq.getRequestId())
                .queryParam("primaryIdType", totalSumRq.getRequestId())
                .queryParam("primaryIdSerie", totalSumRq.getRequestId());
        return getForDataFromRest(TotalSumRs.class, TOTALSUM_REST_MESSAGE, uriQueryParamBuilder);
    }
}
