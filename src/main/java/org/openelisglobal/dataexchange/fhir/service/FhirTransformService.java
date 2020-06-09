package org.openelisglobal.dataexchange.fhir.service;

import javax.servlet.http.HttpServletRequest;

import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.order.valueholder.PortableOrder;
import org.openelisglobal.dataexchange.resultreporting.beans.TestResultsXmit;
import org.openelisglobal.patient.action.bean.PatientManagementInfo;
import org.openelisglobal.sample.action.util.SamplePatientUpdateData;
import org.openelisglobal.sample.form.SamplePatientEntryForm;
import org.openelisglobal.sample.service.PatientManagementUpdate;

public interface FhirTransformService {
    
    public String CreateFhirFromOESample(PortableOrder porder);

    public String CreateFhirFromOESample(TestResultsXmit result);
    
    public String CreateFhirFromOESample(ElectronicOrder eOrder, TestResultsXmit result);

    public String CreateFhirFromOESample(SamplePatientUpdateData updateData, PatientManagementUpdate patientUpdate, PatientManagementInfo patientInfo, SamplePatientEntryForm form, HttpServletRequest request);
}
