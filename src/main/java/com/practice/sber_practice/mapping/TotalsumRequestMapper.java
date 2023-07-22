package com.practice.sber_practice.mapping;


import com.practice.sber_practice.pojo_scheme.request.ParticipantType;
import com.practice.sber_practice.pojo_totalsum.request.TotalSumRq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TotalsumRequestMapper {
    TotalsumRequestMapper INSTANCE = Mappers.getMapper(TotalsumRequestMapper.class);

    @Mapping(target = "personFirstName", source = "naturalPerson.currentPersonData.firstName")
    @Mapping(target = "personLastName", source = "naturalPerson.currentPersonData.lastName")
    @Mapping(target = "personMiddleName", source = "naturalPerson.currentPersonData.middleName")
    @Mapping(target = "personBirthDate", source = "naturalPerson.currentPersonData.birthDate")
    @Mapping(target = "primaryIdType", source = "naturalPerson.primaryID.idType")
    @Mapping(target = "primaryIdSerie", source = "naturalPerson.primaryID.serie")
    @Mapping(target = "primaryIdNumber", source = "naturalPerson.primaryID.number")
    TotalSumRq toTotalSumRq(ParticipantType participantType);
}
