package spring.mine.patient.controller;

import javax.servlet.http.HttpServletRequest;

import spring.mine.common.controller.BaseController;
import spring.mine.sample.form.SamplePatientEntryForm;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;

public abstract class PatientManagementBaseController extends BaseController {

	public void cleanAndSetupRequestForm(SamplePatientEntryForm form, HttpServletRequest request) {
		request.getSession().setAttribute(SAVE_DISABLED, TRUE);
		form.setPatientProperties(new PatientManagementInfo());
		form.setPatientSearch(new PatientSearch());
		form.getPatientProperties().setPatientProcessingStatus("add");
	}

}
