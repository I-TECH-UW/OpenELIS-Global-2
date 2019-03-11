package spring.mine.patient.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.patient.form.PatientEditByProjectForm;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.login.valueholder.UserSessionData;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.patient.saving.Accessioner;
import us.mn.state.health.lims.patient.saving.PatientEditUpdate;
import us.mn.state.health.lims.patient.saving.PatientEntry;
import us.mn.state.health.lims.patient.saving.PatientEntryAfterAnalyzer;
import us.mn.state.health.lims.patient.saving.PatientEntryAfterSampleEntry;
import us.mn.state.health.lims.patient.saving.PatientSecondEntry;
import us.mn.state.health.lims.patient.saving.RequestType;

@Controller
public class PatientEditByProjectController extends BasePatientEntryByProject {
	@RequestMapping(value = "/PatientEditByProject", method = RequestMethod.GET)
	public ModelAndView showPatientEditByProject(HttpServletRequest request,
			@ModelAttribute("form") PatientEditByProjectForm form) throws Exception {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new PatientEditByProjectForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();

		request.getSession().setAttribute(IActionConstants.SAVE_DISABLED, IActionConstants.TRUE);
		updateRequestType(request);

		// Set current date and entered date to today's date
		PropertyUtils.setProperty(form, "currentDate", DateUtil.getCurrentDateAsText()); // TODO Needed?
		PatientSearch patientSearch = new PatientSearch();
		patientSearch.setLoadFromServerWithPatient(false);
		PropertyUtils.setProperty(form, "patientSearch", patientSearch);

		addAllPatientFormLists(form);

		return findForward(forward, form);
	}

	@RequestMapping(value = "/PatientEditByProjectSave", method = RequestMethod.POST)
	public ModelAndView showPatientEditByProjectSave(HttpServletRequest request,
			@ModelAttribute("form") PatientEditByProjectForm form) throws LIMSRuntimeException, Exception {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new PatientEditByProjectForm();
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

		return findForward(FWD_FAIL_INSERT, form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "patientEditByProjectDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "homePageDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/PatientEditByProject.do?forward=success";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "patientEditByProjectDefinition";
		} else {
			return "PageNotFound";
		}
	}

	public String getProject() {
		return null;
	}

	@Override
	protected String getPageTitleKey() {
		return "patient.project.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		RequestType requestType = getRequestType(request);
		String key = null;
		switch (requestType) {
		case READWRITE: {
			key = "banner.menu.editPatient.ReadWrite";
			break;
		}
		case READONLY: {
			key = "banner.menu.editPatient.ReadOnly";
			break;
		}

		default: {
			key = "banner.menu.editPatient.ReadOnly";
		}
		}

		return key;
	}
}
