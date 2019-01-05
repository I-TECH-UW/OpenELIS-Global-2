package spring.mine.patient.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.sample.form.SamplePatientEntryForm;

@Controller
public class PatientManagementController extends PatientManagementBaseController {

	@RequestMapping(value = "/PatientManagement", method = RequestMethod.GET)
	public ModelAndView showPatientManagement(HttpServletRequest request) {
		String forward = FWD_SUCCESS;
		SamplePatientEntryForm form = new SamplePatientEntryForm();
		form.setFormAction("");
		BaseErrors errors = new BaseErrors();
		ModelAndView mv = checkUserAndSetup(form, errors, request);

		if (errors.hasErrors()) {
			return mv;
		}

		cleanAndSetupRequestForm(form, request);

		return findForward(forward, form);
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("patientManagementDefinition", "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("homePageDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}

	@Override
	protected String getPageTitleKey() {
		return "patient.management.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "patient.management.title";
	}
}
