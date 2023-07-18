package com.practice.sber_practice.validation.rule;

import com.practice.sber_practice.pojo_scheme.request.Participants;
import com.practice.sber_practice.pojo_scheme.request.Processing;
import com.practice.sber_practice.pojo_scheme.request.Processing.SourceSystem;
import com.practice.sber_practice.pojo_scheme.request.ServiceRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@Slf4j
public class ParticipantsValidator extends AbstractJsonRequestValidator{
    private static final String REQUEST_SOURCE_ERROR_MESSAGE = "Incorrect number of participants %s";
    private static final String VALIDATED_FIELD = "payload.request.participants";
    @Override
    protected boolean needToApplyValidator(ServiceRequest serviceRequestScoringJson) {
        return true;
    }

    @Override
    protected void validateRequest(ServiceRequest serviceRequest, Errors errors) {
        Participants participants = serviceRequest.getPayload().getRequest().getParticipants();
        SourceSystem sourceSystem = serviceRequest.getHeader().getProcessing().getSourceSystem();
        int value = participants.getParticipant().size();

        if (!isValidParticipants(sourceSystem, participants)) {
            log.warn(String.format(REQUEST_SOURCE_ERROR_MESSAGE, value));
            errors.rejectValue(VALIDATED_FIELD,
                    "field.invalid",
                    String.format(REQUEST_SOURCE_ERROR_MESSAGE, value));
        }
    }

    private boolean isValidParticipants(SourceSystem sourceSystem, Participants participants) {
        boolean result = false;

        if (sourceSystem == SourceSystem.CC) {
            result = participants != null && participants.getParticipant().size() == 2;
        } else {
            result = participants != null && participants.getParticipant().size() == 1;
        }
        return result;
    }
}
