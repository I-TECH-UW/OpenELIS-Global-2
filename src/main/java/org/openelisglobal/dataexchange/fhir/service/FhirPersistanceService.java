package org.openelisglobal.dataexchange.fhir.service;

import java.util.List;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Resource;

public interface FhirPersistanceService {

    Bundle createFhirResourcesInFhirStore(List<Resource> resources);

    public Bundle createFhirResourceInFhirStore(Resource resource);

    public Bundle updateFhirResourceInFhirStore(Resource resource);

    Bundle updateFhirResourcesInFhirStore(List<Resource> resources);

    Bundle makeTransactionBundleForCreate(List<Resource> resources);

    Bundle makeTransactionBundleForUpdate(List<Resource> resources);

}
