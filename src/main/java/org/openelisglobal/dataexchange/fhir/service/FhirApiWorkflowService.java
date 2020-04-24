package org.openelisglobal.dataexchange.fhir.service;

import org.hl7.fhir.r4.model.ResourceType;

public interface FhirApiWorkflowService {
    
    public String getLocalFhirStorePath();

    void processWorkflow(ResourceType resourceType);

    void pollForRemoteTasks();
}
