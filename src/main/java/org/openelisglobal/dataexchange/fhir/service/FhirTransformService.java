package org.openelisglobal.dataexchange.fhir.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Resource;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.order.valueholder.PortableOrder;
import org.openelisglobal.dataexchange.resultreporting.beans.TestResultsXmit;
import org.openelisglobal.patient.action.bean.PatientManagementInfo;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.sample.action.util.SamplePatientUpdateData;
import org.openelisglobal.sample.form.SamplePatientEntryForm;
import org.openelisglobal.sample.service.PatientManagementUpdate;

public interface FhirTransformService {
    
    public String CreateFhirFromOESample(PortableOrder porder);

    public String CreateFhirFromOESample(TestResultsXmit result, Patient patient);
    
    public String CreateFhirFromOESample(ElectronicOrder eOrder, TestResultsXmit result);

    public String CreateFhirFromOESample(SamplePatientUpdateData updateData, PatientManagementUpdate patientUpdate, PatientManagementInfo patientInfo, SamplePatientEntryForm form, HttpServletRequest request);
    
    public org.hl7.fhir.r4.model.Patient CreateFhirPatientFromOEPatient(Patient patient);
    
    public org.hl7.fhir.r4.model.Patient CreateFhirPatientFromOEPatient(PatientManagementInfo patientInfo);
    
    public Bundle CreateFhirResource(Resource resource);
    
    public Bundle UpdateFhirResource(Resource resource);

    public List<ElectronicOrder> getFhirOrdersById(String id);
    
}
