package com.practice.sber_practice;
import com.practice.sber_practice.pojo_scheme.response.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.math.BigDecimal;

public class ServiceResponseGenerator {
    private static final Random RANDOM = new Random();


    public static ServiceResponse generateRandomResponse() {
        ServiceResponse response = new ServiceResponse();
        response.setHeader(generateRandomHeader());
        response.setPayload(generateRandomPayload());
        return response;
    }

    private static Header generateRandomHeader() {
        Header header = new Header();
        header.setTimestampResponse(LocalDateTime.now());
        return header;
    }

    private static Payload generateRandomPayload() {
        Payload payload = new Payload();
        payload.setResponse(generateRandomResponseObject());
        return payload;
    }

    private static Response generateRandomResponseObject() {
        Response responseObject = new Response();
        responseObject.setDealResult(generateRandomDealResult());
        responseObject.setParticipantResults(generateRandomParticipantResults());
        return responseObject;
    }

    private static DealResult generateRandomDealResult() {
        DealResult dealResult = new DealResult();
        dealResult.setSystemErrors(generateRandomSystemErrors());
        dealResult.setStatus(generateRandomDealResultStatus());
        return dealResult;
    }

    private static SystemErrors generateRandomSystemErrors() {
        SystemErrors systemErrors = new SystemErrors();
        List<SystemErrorType> errorList = new ArrayList<>();
        errorList.add(generateRandomSystemError());
        systemErrors.setError(errorList);
        return systemErrors;
    }

    private static SystemErrorType generateRandomSystemError() {
        SystemErrorType systemError = new SystemErrorType();
        systemError.setCode(generateRandomString(10));
        systemError.setMessage(generateRandomString(100));
        systemError.setCause(generateRandomString(100));
        return systemError;
    }

    private static DealResult.Status generateRandomDealResultStatus() {
        return RANDOM.nextBoolean() ? DealResult.Status.OK : DealResult.Status.ERROR;
    }
    private static ParticipantListResultType.Status generateRandomParticipantListResultTypeStatus() {
        return RANDOM.nextBoolean() ? ParticipantListResultType.Status.OK : ParticipantListResultType.Status.ERROR;
    }
    private static ParticipantResults generateRandomParticipantResults() {
        ParticipantResults participantResults = new ParticipantResults();
        List<ParticipantListResultType> participantList = new ArrayList<>();
        participantList.add(generateRandomParticipantResult());
        participantResults.setParticipantResult(participantList);
        return participantResults;
    }

    private static ParticipantListResultType generateRandomParticipantResult() {
        ParticipantListResultType participantResult = new ParticipantListResultType();
        participantResult.setParticipantIndex(RANDOM.nextInt(100));
        participantResult.setStatus(generateRandomParticipantListResultTypeStatus());
        participantResult.setRefinPermission(RANDOM.nextBoolean());
        participantResult.setCreditPotentialData(generateRandomCreditPotentialData());
        return participantResult;
    }

    private static CreditPotentialData generateRandomCreditPotentialData() {
        CreditPotentialData creditPotentialData = new CreditPotentialData();
        creditPotentialData.setAvailableTotalSum(generateRandomBigDecimal(10000));
        return creditPotentialData;
    }

    private static String generateRandomString(int maxLength) {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        StringBuilder sb = new StringBuilder();
        int length = RANDOM.nextInt(maxLength) + 1;
        for (int i = 0; i < length; i++) {
            char c = chars[RANDOM.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    private static BigDecimal generateRandomBigDecimal(int max) {
        return BigDecimal.valueOf(RANDOM.nextDouble() * max);
    }
}