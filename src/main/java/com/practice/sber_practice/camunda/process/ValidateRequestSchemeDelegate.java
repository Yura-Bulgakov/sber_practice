package com.practice.sber_practice.camunda.process;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.practice.sber_practice.camunda.KeyStoreClass;
import com.practice.sber_practice.data.ProcessData;
import com.practice.sber_practice.data.storage.DataStore;
import com.practice.sber_practice.pojo_scheme.request.ServiceRequest;
import com.practice.sber_practice.validation.SchemeValidator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class ValidateRequestSchemeDelegate implements JavaDelegate {

    @NonNull
    private DataStore dataStore;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String parentId = (String) delegateExecution.getVariable(KeyStoreClass.PARENT_ID_KEY);
        log.info("Старт делегата {} для parentId={}", delegateExecution.getCurrentActivityName(), parentId);

        String requestString = dataStore.get(parentId).getServiceRequestString();

       if(!SchemeValidator.validateRequestScheme(requestString)){
           throw new BpmnError("callValidateSchemeError");
       } else {
          ObjectMapper objectMapper = new ObjectMapper();
          objectMapper.registerModule(new JavaTimeModule());
          ServiceRequest request = objectMapper.readValue(requestString, ServiceRequest.class);
          dataStore.get(parentId).setServiceRequest(request);
       }

    }
}
