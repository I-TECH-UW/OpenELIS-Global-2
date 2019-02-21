package spring.mine.patient.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.patient.form.PatientEntryByProjectForm;
import us.mn.state.health.lims.login.valueholder.UserSessionData;
import us.mn.state.health.lims.patient.saving.Accessioner;
import us.mn.state.health.lims.patient.saving.PatientEditUpdate;
import us.mn.state.health.lims.patient.saving.PatientEntry;
import us.mn.state.health.lims.patient.saving.PatientEntryAfterAnalyzer;
import us.mn.state.health.lims.patient.saving.PatientEntryAfterSampleEntry;
import us.mn.state.health.lims.patient.saving.PatientSecondEntry;

@Controller
public class PatientEntryByProjectUpdateController extends BasePatientEntryByProject {

	@RequestMapping(value = "/PatientEntryByProjectUpdate", method = RequestMethod.POST)
	public ModelAndView showPatientEntryByProjectUpdate(HttpServletRequest request,
			@ModelAttribute("form") PatientEntryByProjectForm form) throws Exception {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new PatientEntryByProjectForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();
		

		UserSessionData usd = (UserSessionData) request.getSession().getAttribute(USER_SESSION_DATA);
		String sysUserId = String.valueOf(usd.getSystemUserId());
		Accessioner accessioner;
		addAllPatientFormLists(form);

		accessioner = new PatientEditUpdate(form, sysUserId, request);
		if (accessioner.canAccession()) {
			forward = handleSave(request, accessioner);
			return findForward(forward, form);
		}

		accessioner = new PatientSecondEntry(form, sysUserId, request);
		if (accessioner.canAccession()) {
			forward = handleSave(request, accessioner);
			return findForward(forward, form);
		}

		accessioner = new PatientEntry(form, sysUserId, request);
		if (accessioner.canAccession()) {
			forward = handleSave(request, accessioner);
			if (forward != null) {
				return findForward(forward, form);
			}
		}
		accessioner = new PatientEntryAfterSampleEntry(form, sysUserId, request);
		if (accessioner.canAccession()) {
			forward = handleSave(request, accessioner);
			if (forward != null) {
				return findForward(forward, form);
			}
		}
		accessioner = new PatientEntryAfterAnalyzer(form, sysUserId, request);
		if (accessioner.canAccession()) {
			forward = handleSave(request, accessioner);
			if (forward != null) {
				return findForward(forward, form);
			}
		}
		logAndAddMessage(request, "performAction", "errors.UpdateException");

		return findForward(FWD_FAIL, form);
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			String redirectURL = "/PatientEntryByProject.do?forward=success&type=" + request.getParameter("type");
			return new ModelAndView("redirect:" + redirectURL, "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("patientEntryByProjectDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}

	@Override
	protected String getPageTitleKey() {
		return "patient.project.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "patient.project.title";
	}
}
