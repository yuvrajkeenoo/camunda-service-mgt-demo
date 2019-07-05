package org.camunda.bpm.servicemgt.delegate;

import org.camunda.bpm.servicemgt.model.Requirement;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.servicemgt.constant.ApplicationConstants;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class EvaluateCustomerReqDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<Requirement> requirementList = new ArrayList<Requirement>();
        List<Map<String, Object>> serviceRequirements = (List<Map<String, Object>>) execution.getVariable(ApplicationConstants.VARIABLE_SERVICE_REQUIREMENTS);
        serviceRequirements.stream().filter(req -> req.get(ApplicationConstants.VARIABLE_REQUIREMENT_PROCESS) != null).forEach(result -> {
            Requirement requirement = new Requirement();
            requirement.setProcessKey((String) result.get(ApplicationConstants.VARIABLE_REQUIREMENT_PROCESS));
            requirement.setRequirementType((String) result.get(ApplicationConstants.VARIABLE_REQUIREMENT));
            requirementList.add(requirement);
        });
        execution.setVariable(ApplicationConstants.VARIABLE_SERVICE_PROCESS, serviceRequirements.get(0).get(ApplicationConstants.VARIABLE_SERVICE_PROCESS));
        execution.setVariable(ApplicationConstants.VARIABLE_REQUIREMENT_LIST, Variables.objectValue(requirementList)
                .serializationDataFormat(Variables.SerializationDataFormats.JSON).create());
        execution.setVariable(ApplicationConstants.VARIABLE_REQUIREMENTS_NUMBER, requirementList.size());
    }
}
