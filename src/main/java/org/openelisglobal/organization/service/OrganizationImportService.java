package org.openelisglobal.organization.service;

import org.openelisglobal.dataexchange.fhir.exception.FhirGeneralException;
import org.openelisglobal.dataexchange.fhir.exception.FhirLocalPersistingException;

public interface OrganizationImportService {

    void importOrganizationList() throws FhirLocalPersistingException, FhirGeneralException;

}
