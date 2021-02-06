package org.openelisglobal.dataexchange.fhir.service;

import java.util.List;

import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.order.valueholder.PortableOrder;
import org.openelisglobal.dataexchange.resultreporting.beans.TestResultsXmit;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.patient.action.bean.PatientManagementInfo;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.sample.action.util.SamplePatientUpdateData;

import ca.uhn.fhir.rest.client.api.IGenericClient;

public interface FhirTransformService {

    public String CreateFhirFromOESample(PortableOrder porder);

    public String CreateFhirFromOESample(TestResultsXmit result, Patient patient);

    public void CreateFhirFromOESample(ElectronicOrder eOrder, TestResultsXmit result);

    String CreateFhirFromOESample(SamplePatientUpdateData updateData, PatientManagementInfo patientInfo);

    public org.hl7.fhir.r4.model.Patient CreateFhirPatientFromOEPatient(Patient patient);

    public org.hl7.fhir.r4.model.Patient CreateFhirPatientFromOEPatient(PatientManagementInfo patientInfo);

    public List<ElectronicOrder> getFhirOrdersById(String id);

    Organization fhirOrganizationToOrganization(org.hl7.fhir.r4.model.Organization fhirOrganization,
            IGenericClient client);

    Reference createReferenceFor(Resource resource);

    org.hl7.fhir.r4.model.Organization getFhirOrganization(Organization organization);

    public org.hl7.fhir.r4.model.Patient getFhirPatientOrCreate(PatientManagementInfo patientInfo);

    org.hl7.fhir.r4.model.Organization organizationToFhirOrganization(Organization organization);

    org.hl7.fhir.r4.model.Patient getFhirPatient(PatientManagementInfo patientInfo);

    String getIdFromLocation(String location);

}
