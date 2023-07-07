package com.practice.sber_practice.camunda.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.sber_practice.camunda.KeyStoreClass;
import com.practice.sber_practice.data.ProcessData;
import com.practice.sber_practice.data.storage.DataStore;
import com.practice.sber_practice.pojo_scheme.request.ServiceRequest;
import com.practice.sber_practice.pojo_scheme.response.ServiceResponse;
import com.practice.sber_practice.utils.RandomResponseGenerator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProcessRequestDelegate implements JavaDelegate {


    @NonNull
    private DataStore dataStore;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String parentId = (String) delegateExecution.getVariable(KeyStoreClass.PARENT_ID_KEY);
        log.info("Старт делегата {} для parentId={}", delegateExecution.getCurrentActivityName(), parentId);
        String requestString = (String) delegateExecution.getVariable(KeyStoreClass.REQUEST_STRING_KEY);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        ServiceRequest request = objectMapper.readValue(requestString, ServiceRequest.class);

        ProcessData processData = dataStore.get(parentId);
        processData.setServiceRequest(request);
        ServiceResponse response = RandomResponseGenerator.generateRandomResponse();
        processData.setServiceResponse(response);

    }
}
