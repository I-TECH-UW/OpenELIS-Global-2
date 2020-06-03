package org.openelisglobal.dataexchange.fhir.service;

import org.openelisglobal.dataexchange.order.valueholder.PortableOrder;
import org.openelisglobal.dataexchange.resultreporting.beans.TestResultsXmit;

public interface FhirTransformService {
    
    public String CreateFhirFromOESample(PortableOrder porder);

    public String CreateFhirFromOESample(TestResultsXmit result);
}
