package com.practice.sber_practice.services;


import com.practice.sber_practice.mapping.TotalsumRequestMapper;
import com.practice.sber_practice.pojo_scheme.request.ParticipantType;
import com.practice.sber_practice.pojo_scheme.request.ServiceRequest;
import com.practice.sber_practice.pojo_totalsum.request.TotalSumRq;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TotalsumRequestGenService {


    public TotalSumRq generateTotalSumRq(ParticipantType participantType){
        TotalsumRequestMapper totalSumRequestMapper = TotalsumRequestMapper.INSTANCE;
        TotalSumRq totalSumRq = totalSumRequestMapper.toTotalSumRq(participantType);
        totalSumRq.setRequestId(UUID.randomUUID().toString());
        return totalSumRq;
    }

}
