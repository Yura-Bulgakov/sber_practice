package com.practice.sber_practice.services;


import com.practice.sber_practice.camunda.KeyStoreClass;
import com.practice.sber_practice.data.ProcessData;
import com.practice.sber_practice.data.storage.DataStore;
import com.practice.sber_practice.pojo_scheme.request.ServiceRequest;
import com.practice.sber_practice.pojo_scheme.response.ServiceResponse;
import com.practice.sber_practice.utils.RandomParentIdGenerator;
import lombok.Setter;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.HashMap;



public class CamundaRequestProcess implements RequestProcessService{

    private static final String BUSINESS_KEY = "sberPracticeBuisnessKey";

    @Autowired
    @NotNull
    @Setter
    private RuntimeService runtimeService;

    @Autowired
    private DataStore dataStore;


    @Override
    public ServiceResponse processRequest(String requestString) {

        ProcessData processData = ProcessData.builder().build();
        String parentId = RandomParentIdGenerator.getParentId(5);
        dataStore.put(parentId, processData);
        Map<String, Object> varMap = new HashMap<>();
        varMap.put(KeyStoreClass.REQUEST_STRING_KEY, requestString);
        varMap.put(KeyStoreClass.PARENT_ID_KEY, parentId);

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(BUSINESS_KEY, varMap);
        while (!processInstance.isEnded()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        ServiceResponse serviceResponse = dataStore.get(parentId).getServiceResponse();
        dataStore.remove(parentId);

        return serviceResponse;
    }


}
