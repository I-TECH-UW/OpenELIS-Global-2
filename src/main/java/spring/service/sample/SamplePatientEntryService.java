package spring.service.sample;

import javax.servlet.http.HttpServletRequest;

import spring.mine.sample.form.SamplePatientEntryForm;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.sample.action.util.SamplePatientUpdateData;

public interface SamplePatientEntryService {

	void persistData(SamplePatientUpdateData updateData, PatientManagementUpdate patientUpdate,
			PatientManagementInfo patientInfo, SamplePatientEntryForm form, HttpServletRequest request);

}
