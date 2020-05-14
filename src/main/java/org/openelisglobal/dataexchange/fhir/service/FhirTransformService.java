package org.openelisglobal.dataexchange.fhir.service;

import org.openelisglobal.dataexchange.order.valueholder.PortableOrder;

public interface FhirTransformService {
    
    public String CreateFhirFromOESample(PortableOrder eorder);
}
