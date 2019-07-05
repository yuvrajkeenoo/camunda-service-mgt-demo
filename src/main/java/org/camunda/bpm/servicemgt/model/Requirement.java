package org.camunda.bpm.servicemgt.model;

import lombok.Getter;
import lombok.Setter;

public class Requirement {

    @Getter @Setter
    private String requirementType;
    @Getter @Setter
    private String processKey;
}
