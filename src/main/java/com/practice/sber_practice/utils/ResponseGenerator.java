package com.practice.sber_practice.utils;

import com.practice.sber_practice.pojo_scheme.response.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ResponseGenerator {

    public static ServiceResponse generateResponse(ParticipantListResultType dealParticipant) {
        ServiceResponse response = new ServiceResponse();
        response.setHeader(generateHeader());
        response.setPayload(generatePayload(dealParticipant));
        return response;
    }

    public static ServiceResponse generateResponse(List<ParticipantListResultType> dealParticipants) {
        ServiceResponse response = new ServiceResponse();
        response.setHeader(generateHeader());
        response.setPayload(generatePayload(dealParticipants));
        return response;
    }

    private static Header generateHeader() {
        Header header = new Header();
        header.setTimestampResponse(LocalDateTime.now());
        return header;
    }

    private static Payload generatePayload(ParticipantListResultType dealParticipant) {
        Payload payload = new Payload();
        payload.setResponse(generateResponseObject(dealParticipant));
        return payload;
    }

    private static Payload generatePayload(List<ParticipantListResultType> dealParticipants) {
        Payload payload = new Payload();
        payload.setResponse(generateResponseObject(dealParticipants));
        return payload;
    }

    private static Response generateResponseObject(ParticipantListResultType dealParticipant) {
        Response responseObject = new Response();
        responseObject.setDealResult(generateDealResult(dealParticipant));
        responseObject.setParticipantResults(generateParticipantResults(dealParticipant));
        return responseObject;
    }

    private static Response generateResponseObject(List<ParticipantListResultType> dealParticipants) {
        Response responseObject = new Response();
        responseObject.setDealResult(generateDealResult(dealParticipants));
        responseObject.setParticipantResults(generateParticipantResults(dealParticipants));
        return responseObject;
    }

    private static DealResult generateDealResult(ParticipantListResultType dealParticipant) {
        DealResult dealResult = new DealResult();
        dealResult.setStatus(generateDealResultStatus(dealParticipant));
        return dealResult;
    }

    private static DealResult generateDealResult(List<ParticipantListResultType> dealParticipants) {
        DealResult dealResult = new DealResult();
        dealResult.setStatus(generateDealResultStatus(dealParticipants));
        return dealResult;
    }


    private static DealResult.Status generateDealResultStatus(ParticipantListResultType dealParticipant) {
        if(dealParticipant.getStatus().equals(ParticipantListResultType.Status.OK)){
            return DealResult.Status.OK;
        } else{
            return DealResult.Status.ERROR;
        }
    }

    private static DealResult.Status generateDealResultStatus(List<ParticipantListResultType> dealParticipants) {
        ParticipantListResultType.Status refStatus = ParticipantListResultType.Status.OK;
        DealResult.Status dealStatus = DealResult.Status.OK;
        for(ParticipantListResultType dealParticipant : dealParticipants){
            if(!dealParticipant.getStatus().equals(refStatus)){
                dealStatus = DealResult.Status.ERROR;
            }
        }
        return dealStatus;
    }


    private static ParticipantResults generateParticipantResults(ParticipantListResultType dealParticipant) {
        ParticipantResults participantResults = new ParticipantResults();
        List<ParticipantListResultType> participantList = new ArrayList<>();
        participantList.add(dealParticipant);
        participantResults.setParticipantResult(participantList);
        return participantResults;
    }

    private static ParticipantResults generateParticipantResults(List<ParticipantListResultType> dealParticipants) {
        ParticipantResults participantResults = new ParticipantResults();
        participantResults.setParticipantResult(dealParticipants);
        return participantResults;
    }
}
