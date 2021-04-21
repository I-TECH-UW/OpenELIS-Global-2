package org.openelisglobal.referral.fhir.service;

import java.util.List;

import org.hl7.fhir.r4.model.Bundle;
import org.openelisglobal.dataexchange.fhir.exception.FhirLocalPersistingException;
import org.openelisglobal.dataexchange.fhir.service.FhirApiWorkFlowServiceImpl.ReferralResultsImportObjects;

public interface FhirReferralService {

    Bundle referAnalysisesToOrganization(String organizationId, String sampleId, List<String> analysisIds)
            throws FhirLocalPersistingException;

    void setReferralResult(ReferralResultsImportObjects resultsImport);

//    Bundle cancelReferralToOrganization(String organizationId, String sampleId, List<String> analysisIds)
//            throws FhirLocalPersistingException;

}
