package org.openelisglobal.dataexchange.fhir.service;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.dataexchange.fhir.exception.FhirLocalPersistingException;
import org.openelisglobal.dataexchange.fhir.exception.FhirPersistanceException;
import org.openelisglobal.dataexchange.fhir.exception.FhirTransformationException;
import org.openelisglobal.note.valueholder.Note;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.patient.action.bean.PatientManagementInfo;
import org.openelisglobal.result.action.util.ResultsUpdateDataSet;
import org.openelisglobal.result.valueholder.Result;
import org.openelisglobal.resultvalidation.bean.AnalysisItem;
import org.openelisglobal.sample.action.util.SamplePatientUpdateData;
import org.openelisglobal.sample.valueholder.Sample;

public interface FhirTransformService {

    void transformPersistPatient(PatientManagementInfo patientInfo)
            throws FhirTransformationException, FhirPersistanceException;

    void transformPersistOrderEntryFhirObjects(SamplePatientUpdateData updateData, PatientManagementInfo patientInfo)
            throws FhirTransformationException, FhirPersistanceException;

    void transformPersistResultsEntryFhirObjects(ResultsUpdateDataSet actionDataSet)
            throws FhirTransformationException, FhirPersistanceException;

    Organization transformToOrganization(org.hl7.fhir.r4.model.Organization fhirOrganization)
            throws FhirTransformationException;

    org.hl7.fhir.r4.model.Organization transformToFhirOrganization(Organization organization)
            throws FhirTransformationException;

    String getIdFromLocation(String location);

    Reference createReferenceFor(Resource resource);

    void transformPersistResultValidationFhirObjects(List<Result> deletableList, List<Analysis> analysisUpdateList,
            ArrayList<Result> resultUpdateList, List<AnalysisItem> resultItemList, ArrayList<Sample> sampleUpdateList,
            ArrayList<Note> noteUpdateList) throws FhirLocalPersistingException;

    org.hl7.fhir.r4.model.Patient transformToFhirPatient(String patientId) throws FhirTransformationException;

    Bundle transformPersistObjectsUnderSample(String sampleId) throws FhirLocalPersistingException;

}
