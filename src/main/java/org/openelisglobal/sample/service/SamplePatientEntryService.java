package org.openelisglobal.sample.service;

import javax.servlet.http.HttpServletRequest;

import org.openelisglobal.sample.form.SamplePatientEntryForm;
import org.openelisglobal.patient.action.bean.PatientManagementInfo;
import org.openelisglobal.sample.action.util.SamplePatientUpdateData;

public interface SamplePatientEntryService {

	void persistData(SamplePatientUpdateData updateData, PatientManagementUpdate patientUpdate,
			PatientManagementInfo patientInfo, SamplePatientEntryForm form, HttpServletRequest request);

}
