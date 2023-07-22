package com.practice.sber_practice.mapping;

import com.practice.sber_practice.pojo_scheme.response.ParticipantListResultType;
import com.practice.sber_practice.pojo_scheme.response.ServiceResponse;
import com.practice.sber_practice.pojo_totalsum.response.TotalSumRs;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface ServiceResponceMapper {

    ServiceResponceMapper INSTANCE = Mappers.getMapper(ServiceResponceMapper.class);


    @Mapping(target = "creditPotentialData.availableTotalSum", source = "totalSum")
    ParticipantListResultType mapToServiceResponse(TotalSumRs totalSumRs);

    List<ParticipantListResultType> mapToListServiceResponse(List<TotalSumRs> totalSumRs);

}

