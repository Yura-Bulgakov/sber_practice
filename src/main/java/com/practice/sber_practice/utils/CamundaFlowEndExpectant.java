package com.practice.sber_practice.utils;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;

@Slf4j
public class CamundaFlowEndExpectant {
    private static final long TIMEOUT_MILLIS = 30000;
    private static final String INSTANCE_DELETE_MESSAGE =
            "Процесс camunda с id {} был прерван из-за превышения времени обработки!";

    @Autowired
    @NotNull
    @Setter
    private static RuntimeService runtimeService;

    public static boolean isFlowEnded(ProcessInstance processInstance){
        boolean result = true;
        long startTime = System.currentTimeMillis();

        while (!processInstance.isEnded() && (System.currentTimeMillis() - startTime) < TIMEOUT_MILLIS){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        //Если время выполнения camunda flow превысило время ожидания
        if (!processInstance.isEnded()) {
            result = false;
            log.info(INSTANCE_DELETE_MESSAGE, processInstance.getId());
            runtimeService.deleteProcessInstance(processInstance.getId(), INSTANCE_DELETE_MESSAGE);
        }
        return result;
    }
}
