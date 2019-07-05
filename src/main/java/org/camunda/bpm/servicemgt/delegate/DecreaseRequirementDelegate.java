package org.camunda.bpm.servicemgt.delegate;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.Execution;
import org.springframework.stereotype.Component;

import static org.camunda.bpm.servicemgt.constant.ApplicationConstants.VARIABLE_ORDER_ID;
import static org.camunda.bpm.servicemgt.constant.ApplicationConstants.VARIABLE_REQUIREMENTS_LEFT;

@Component
public class DecreaseRequirementDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        RuntimeService runtimeService = execution.getProcessEngine().getRuntimeService();
        String orderId = (String) execution.getVariable(VARIABLE_ORDER_ID);
        Execution mainServiceExecution = runtimeService.createExecutionQuery().processInstanceBusinessKey(orderId)
                .list().get(0);
        Integer requirementsLeft = ((Integer) runtimeService.getVariable(mainServiceExecution.getId(), VARIABLE_REQUIREMENTS_LEFT)) - 1;
        execution.getProcessEngine().getRuntimeService().setVariable(mainServiceExecution.getId(), VARIABLE_REQUIREMENTS_LEFT, requirementsLeft);
    }
}
