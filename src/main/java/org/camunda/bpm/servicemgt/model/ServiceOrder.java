package org.camunda.bpm.servicemgt.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class ServiceOrder {

    @Getter @Setter
    private String serviceType;
    @Getter @Setter
    private String customerId;
    @Getter @Setter
    private Date requestDate;
    @Getter @Setter
    private String requestType;
}
