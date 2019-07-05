package org.camunda.bpm.servicemgt.controller;

import org.camunda.bpm.servicemgt.constant.ApplicationConstants;
import org.camunda.bpm.servicemgt.model.ServiceOrder;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceManagementController {

    private ProcessEngine processEngine;

    @Autowired
    public ServiceManagementController(ProcessEngine processEngine){
        this.processEngine = processEngine;
    }

    @PostMapping(ApplicationConstants.CONTROLLER_PATH_SERVICE_MANAGEMENT)
    public void newServiceOrder(@RequestBody ServiceOrder serviceOrder){
        VariableMap variableMap = Variables.createVariables()
                .putValue(ApplicationConstants.VARIABLE_CUSTOMER_ID, serviceOrder.getCustomerId())
                .putValue(ApplicationConstants.VARIABLE_SERVICE_TYPE, serviceOrder.getServiceType())
                .putValue(ApplicationConstants.VARIABLE_REQUEST_DATE, serviceOrder.getRequestDate())
                .putValue(ApplicationConstants.VARIABLE_REQUEST_TYPE, serviceOrder.getRequestType());

        processEngine.getRuntimeService().startProcessInstanceByKey(ApplicationConstants.PROCESSKEY_SERVICE_MANAGEMENT, variableMap);
    }
}
