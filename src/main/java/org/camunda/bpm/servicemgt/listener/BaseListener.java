package org.camunda.bpm.servicemgt.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.servicemgt.constant.ApplicationConstants;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class BaseListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        execution.setVariable(ApplicationConstants.VARIABLE_REQUEST_DATE, new Date());
        execution.setVariable(ApplicationConstants.VARIABLE_ORDER_ID, UUID.randomUUID().toString());
    }
}
