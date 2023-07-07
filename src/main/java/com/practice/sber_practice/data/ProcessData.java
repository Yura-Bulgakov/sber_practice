package com.practice.sber_practice.data;

import com.practice.sber_practice.pojo_scheme.request.ServiceRequest;
import com.practice.sber_practice.pojo_scheme.response.ServiceResponse;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class ProcessData implements Serializable {
    private ServiceRequest serviceRequest;
    private ServiceResponse serviceResponse;
}
