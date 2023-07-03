package com.practice.sber_practice;

import com.practice.sber_practice.pojo_scheme.request.ServiceRequest;
import com.practice.sber_practice.pojo_scheme.response.ServiceResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProcessData {
    private ServiceRequest serviceRequest;
    private ServiceResponse serviceResponse;
}
