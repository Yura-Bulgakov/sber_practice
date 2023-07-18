package com.practice.sber_practice.camunda.process;

import com.practice.sber_practice.camunda.KeyStoreClass;
import com.practice.sber_practice.data.storage.DataStore;
import com.practice.sber_practice.pojo_scheme.request.ServiceRequest;
import com.practice.sber_practice.validation.rule.AbstractJsonRequestValidator;
import com.practice.sber_practice.validation.rule.ParticipantsValidator;
import com.practice.sber_practice.validation.rule.SourceSystemValidator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ValidateRequestRuleDelegate implements JavaDelegate {

    @NonNull
    private DataStore dataStore;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String parentId = (String) delegateExecution.getVariable(KeyStoreClass.PARENT_ID_KEY);
        log.info("Старт делегата {} для parentId={}", delegateExecution.getCurrentActivityName(), parentId);
        Validator serviceRequestRuleValidator = getJsonRequestRuleValidator();


        final DataBinder dataBinder;
        dataBinder = new DataBinder(dataStore.get(parentId).getServiceRequest());
        dataBinder.addValidators(serviceRequestRuleValidator);
        dataBinder.validate();

        if (dataBinder.getBindingResult().hasErrors()) {
            log.error("Service request is not valid for parentId={}", parentId);

            List<ObjectError> errors = dataBinder.getBindingResult().getAllErrors();
            for (ObjectError error : errors) {
                String field = error.getObjectName();
                String message = error.getDefaultMessage();
                String code = error.getCode();

                System.out.println("Field: " + field);
                System.out.println("Message: " + message);
                System.out.println("Code: " + code);
            }
            throw new BpmnError("callValidateRuleError");
        }
    }

    private static Validator getJsonRequestRuleValidator(){
        AbstractJsonRequestValidator rootValidator = new SourceSystemValidator();
        AbstractJsonRequestValidator participantValidator = new ParticipantsValidator();

        rootValidator.setNext(participantValidator);

        return rootValidator;
    }

}
