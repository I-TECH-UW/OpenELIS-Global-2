package org.openelisglobal.dataexchange.fhir.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.order.valueholder.PortableOrder;
import org.openelisglobal.dataexchange.resultreporting.beans.TestResultsXmit;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.patient.action.bean.PatientManagementInfo;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.sample.action.util.SamplePatientUpdateData;
import org.openelisglobal.sample.form.SamplePatientEntryForm;
import org.openelisglobal.sample.service.PatientManagementUpdate;

import ca.uhn.fhir.rest.client.api.IGenericClient;

public interface FhirTransformService {

    public String CreateFhirFromOESample(PortableOrder porder);

    public String CreateFhirFromOESample(TestResultsXmit result, Patient patient);

    public void CreateFhirFromOESample(ElectronicOrder eOrder, TestResultsXmit result);

    public String CreateFhirFromOESample(SamplePatientUpdateData updateData, PatientManagementUpdate patientUpdate, PatientManagementInfo patientInfo, SamplePatientEntryForm form, HttpServletRequest request);

    public org.hl7.fhir.r4.model.Patient CreateFhirPatientFromOEPatient(Patient patient);

    public org.hl7.fhir.r4.model.Patient CreateFhirPatientFromOEPatient(PatientManagementInfo patientInfo);

    public List<ElectronicOrder> getFhirOrdersById(String id);

    Organization fhirOrganizationToOrganization(org.hl7.fhir.r4.model.Organization fhirOrganization,
            IGenericClient client);

    Reference createReferenceFor(Resource resource);

    org.hl7.fhir.r4.model.Organization getFhirOrganization(Organization organization);

    public org.hl7.fhir.r4.model.Patient getFhirPatient(PatientManagementInfo patientInfo);

}
