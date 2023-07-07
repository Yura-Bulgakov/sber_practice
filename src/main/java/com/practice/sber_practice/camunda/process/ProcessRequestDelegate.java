package com.practice.sber_practice.camunda.process;

import com.practice.sber_practice.camunda.KeyStoreClass;
import com.practice.sber_practice.data.ProcessData;
import com.practice.sber_practice.data.storage.DataStore;
import com.practice.sber_practice.pojo_scheme.response.ServiceResponse;
import com.practice.sber_practice.utils.RandomResponseGenerator;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Slf4j
public class ProcessRequestDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        ServiceResponse response = RandomResponseGenerator.generateRandomResponse();

        String parentId = (String) delegateExecution.getVariable(KeyStoreClass.PARENT_ID_KEY);
        DataStore dataStore = (DataStore) delegateExecution.getVariable(KeyStoreClass.VAR_MAP_KEY);

        ProcessData data = dataStore.get(parentId);
        data.setServiceResponse(response);
        dataStore.put(parentId,data);

        log.info("Старт делегата processRequestDelegate");
    }
}
