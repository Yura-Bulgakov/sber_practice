package com.practice.sber_practice.validation.rule;


import com.practice.sber_practice.pojo_scheme.request.Processing.SourceSystem;
import com.practice.sber_practice.pojo_scheme.request.ServiceRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;



@Component
@Slf4j
public class SourceSystemValidator extends AbstractJsonRequestValidator{

    private static final String REQUEST_SOURCE_ERROR_MESSAGE = "Unavailable source system %s";
    private static final String VALIDATED_FIELD = "processing.sourceSystem";

    @Override
    protected boolean needToApplyValidator(ServiceRequest serviceRequestScoringJson) {
        return true;
    }

    @Override
    protected void validateRequest(ServiceRequest serviceRequest, Errors errors) {

        SourceSystem sourceSystem = serviceRequest.getHeader().getProcessing().getSourceSystem();
        String value = sourceSystem.value();

        if (!isValidSourceSystem(sourceSystem)) {
            log.warn(String.format(REQUEST_SOURCE_ERROR_MESSAGE, value));
            errors.rejectValue(VALIDATED_FIELD,
                    "field.invalid",
                    String.format(REQUEST_SOURCE_ERROR_MESSAGE, value));
        }

    }

    private boolean isValidSourceSystem(SourceSystem sourceSystem) {
        return (sourceSystem == SourceSystem.CC ||
                sourceSystem == SourceSystem.CPTRIGGER ||
                sourceSystem == SourceSystem.AUTO);
    }
}
