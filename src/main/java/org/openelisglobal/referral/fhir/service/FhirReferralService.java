package org.openelisglobal.referral.fhir.service;

import java.util.List;

import org.hl7.fhir.r4.model.Bundle;

public interface FhirReferralService {

    Bundle referAnalysisesToOrganization(String organizationId, String sampleId, List<String> analysisIds);

    Bundle referAnalysisesToOrganization(String organizationId, String analysisId);

    Bundle cancelReferralToOrganization(String organizationId, String sampleId, List<String> analysisIds);

}
