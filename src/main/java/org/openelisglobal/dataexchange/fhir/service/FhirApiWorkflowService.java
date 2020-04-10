package org.openelisglobal.dataexchange.fhir.service;

import org.hl7.fhir.r4.model.ResourceType;
import org.openelisglobal.dataexchange.order.action.MessagePatient;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;

public interface FhirApiWorkflowService {

    void processWorkflow(ResourceType resourceType);
    
}
