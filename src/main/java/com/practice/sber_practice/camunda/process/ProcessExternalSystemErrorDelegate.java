package com.practice.sber_practice.camunda.process;

import com.practice.sber_practice.camunda.KeyStoreClass;
import com.practice.sber_practice.data.storage.DataStore;
import com.practice.sber_practice.pojo_scheme.response.ServiceResponse;
import com.practice.sber_practice.utils.ErrorResponseGenerator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProcessExternalSystemErrorDelegate implements JavaDelegate {
    @NonNull
    private DataStore dataStore;
    private static final String EXTERNAL_SYSTEM_ERROR_MESSAGE = "Ошибка при получении ответа от внешнего сервиса";
    private static final String EXTERNAL_SYSTEM_ERROR_CAUSE = "Внешний сервис не отвечает";
    private static final String EXTERNAL_SYSTEM_ERROR_CODE = "ERR_CODE_003";

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String parentId = (String) delegateExecution.getVariable(KeyStoreClass.PARENT_ID_KEY);
        log.info("Старт делегата {} для parentId={}", delegateExecution.getCurrentActivityName(), parentId);

        ServiceResponse response = ErrorResponseGenerator.
                generateErrorResponse(EXTERNAL_SYSTEM_ERROR_CODE,
                        EXTERNAL_SYSTEM_ERROR_MESSAGE,
                        EXTERNAL_SYSTEM_ERROR_CAUSE);
        dataStore.get(parentId).setServiceResponse(response);
    }
}
