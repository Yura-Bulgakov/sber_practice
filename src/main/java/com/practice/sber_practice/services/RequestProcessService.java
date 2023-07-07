package com.practice.sber_practice.services;

import com.practice.sber_practice.pojo_scheme.request.ServiceRequest;
import com.practice.sber_practice.pojo_scheme.response.ServiceResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface RequestProcessService {
    public ServiceResponse processRequest(ServiceRequest request);

}
