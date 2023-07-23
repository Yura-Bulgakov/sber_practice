package com.practice.sber_practice.camunda.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.sber_practice.camunda.KeyStoreClass;
import com.practice.sber_practice.data.ProcessData;
import com.practice.sber_practice.data.storage.DataStore;
import com.practice.sber_practice.pojo_scheme.request.ParticipantType;
import com.practice.sber_practice.pojo_scheme.request.ServiceRequest;
import com.practice.sber_practice.pojo_scheme.response.ServiceResponse;
import com.practice.sber_practice.pojo_totalsum.request.TotalSumRq;
import com.practice.sber_practice.pojo_totalsum.response.TotalSumRs;
import com.practice.sber_practice.rest.CommonRestReceiver;
import com.practice.sber_practice.rest.RestTotalsumReceiver;
import com.practice.sber_practice.services.ResponceGenService;
import com.practice.sber_practice.services.TotalsumRequestGenService;
import com.practice.sber_practice.utils.RandomResponseGenerator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProcessRequestDelegate implements JavaDelegate {


    @NonNull
    private DataStore dataStore;
    @NonNull
    private TotalsumRequestGenService totalsumRequestGenService;
    @NonNull
    private RestTotalsumReceiver restReceiver;
    @NonNull
    private ResponceGenService responceGenService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String parentId = (String) delegateExecution.getVariable(KeyStoreClass.PARENT_ID_KEY);
        log.info("Старт делегата {} для parentId={}", delegateExecution.getCurrentActivityName(), parentId);
        List<ParticipantType> dealParticipants = dataStore
                .get(parentId)
                .getServiceRequest()
                .getPayload()
                .getRequest()
                .getParticipants()
                .getParticipant();
        List<TotalSumRs> totalSumRs = new ArrayList<>();

        try {
            for (ParticipantType dealParticipant : dealParticipants) {
                totalSumRs.add(
                        restReceiver
                                .getTotalsumFromRest(totalsumRequestGenService.generateTotalSumRq(dealParticipant)));
            }
        }catch (Exception e){
            throw new BpmnError("callExternalSystemError");
        }

        ServiceResponse response = responceGenService.generateServiceResponse(totalSumRs);
        dataStore.get(parentId).setServiceResponse(response);

    }
}
