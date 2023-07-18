package com.practice.sber_practice.utils;

import com.practice.sber_practice.pojo_scheme.response.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ErrorResponseGenerator {


    public static ServiceResponse generateErrorResponse(String errorCode, String errorMessage, String errorCause) {
        ServiceResponse response = new ServiceResponse();
        response.setHeader(generateHeader());
        response.setPayload(generatePayload(errorCode, errorMessage, errorCause));
        return response;
    }

    private static Header generateHeader() {
        Header header = new Header();
        header.setTimestampResponse(LocalDateTime.now());
        return header;
    }

    private static Payload generatePayload(String errorCode, String errorMessage, String errorCause) {
        Payload payload = new Payload();
        payload.setResponse(generateResponseObject(errorCode, errorMessage, errorCause));
        return payload;
    }

    private static Response generateResponseObject(String errorCode, String errorMessage, String errorCause) {
        Response responseObject = new Response();
        responseObject.setDealResult(generateDealResult(errorCode, errorMessage, errorCause));
        return responseObject;
    }

    private static DealResult generateDealResult(String errorCode, String errorMessage, String errorCause) {
        DealResult dealResult = new DealResult();
        dealResult.setSystemErrors(generateSystemErrors(errorCode, errorMessage, errorCause));
        dealResult.setStatus(DealResult.Status.ERROR);
        return dealResult;
    }

    private static SystemErrors generateSystemErrors(String errorCode, String errorMessage, String errorCause) {
        SystemErrors systemErrors = new SystemErrors();
        List<SystemErrorType> errorList = new ArrayList<>();
        errorList.add(generateRandomSystemError(errorCode, errorMessage, errorCause));
        systemErrors.setError(errorList);
        return systemErrors;
    }

    private static SystemErrorType generateRandomSystemError(String errorCode, String errorMessage, String errorCause) {
        SystemErrorType systemError = new SystemErrorType();
        systemError.setCode(errorCode);
        systemError.setMessage(errorMessage);
        systemError.setCause(errorCause);
        return systemError;
    }

}
