package com.practice.sber_practice.services;

import com.practice.sber_practice.mapping.ServiceResponceMapper;
import com.practice.sber_practice.pojo_scheme.response.ParticipantListResultType;
import com.practice.sber_practice.pojo_scheme.response.ServiceResponse;
import com.practice.sber_practice.pojo_totalsum.response.TotalSumRs;
import com.practice.sber_practice.utils.ResponseGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponceGenService {

    public ServiceResponse generateServiceResponse(TotalSumRs totalSumRs){
        ParticipantListResultType participant = ServiceResponceMapper.INSTANCE.mapToServiceResponse(totalSumRs);
        ServiceResponse response = ResponseGenerator.generateResponse(participant);
        return response;
    }

    public ServiceResponse generateServiceResponse(List<TotalSumRs> totalSumRs){
        List<ParticipantListResultType> participant = ServiceResponceMapper.INSTANCE.mapToListServiceResponse(totalSumRs);
        ServiceResponse response = ResponseGenerator.generateResponse(participant);
        return response;
    }
}
