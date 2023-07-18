package com.practice.sber_practice.camunda.process;

import com.practice.sber_practice.camunda.KeyStoreClass;
import com.practice.sber_practice.data.storage.DataStore;
import com.practice.sber_practice.pojo_scheme.response.ServiceResponse;
import com.practice.sber_practice.utils.ErrorResponseGenerator;
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
public class ProcessValidationSchemeErrorDelegate implements JavaDelegate {


    @NonNull
    private DataStore dataStore;

    private static final String VALIDATION_SCHEME_ERROR_MESSAGE = "Ошибка при валидации запроса на соответствие схеме";
    private static final String VALIDATION_SCHEME_ERROR_CAUSE = "Полученный запрос не соответствует схеме";
    private static final String VALIDATION_SCHEME_ERROR_CODE = "ERR_CODE_001";


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String parentId = (String) delegateExecution.getVariable(KeyStoreClass.PARENT_ID_KEY);
        log.info("Старт делегата {} для parentId={}", delegateExecution.getCurrentActivityName(), parentId);

        ServiceResponse response = ErrorResponseGenerator.
                generateErrorResponse(VALIDATION_SCHEME_ERROR_CODE,
                VALIDATION_SCHEME_ERROR_MESSAGE,
                VALIDATION_SCHEME_ERROR_CAUSE);
        dataStore.get(parentId).setServiceResponse(response);

    }
}
