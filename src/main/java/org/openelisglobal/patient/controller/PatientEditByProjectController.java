package org.openelisglobal.patient.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.openelisglobal.patient.form.PatientEditByProjectForm;
import org.openelisglobal.patient.validator.PatientEditByProjectFormValidator;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.patient.action.bean.PatientSearch;
import org.openelisglobal.patient.saving.Accessioner;
import org.openelisglobal.patient.saving.PatientEditUpdate;
import org.openelisglobal.patient.saving.PatientEntry;
import org.openelisglobal.patient.saving.PatientEntryAfterAnalyzer;
import org.openelisglobal.patient.saving.PatientEntryAfterSampleEntry;
import org.openelisglobal.patient.saving.PatientSecondEntry;
import org.openelisglobal.patient.saving.RequestType;

@Controller
public class PatientEditByProjectController extends BasePatientEntryByProject {

	@Autowired
	PatientEditByProjectFormValidator formValidator;

	@RequestMapping(value = "/PatientEditByProject", method = RequestMethod.GET)
	public ModelAndView showPatientEditByProject(HttpServletRequest request) throws Exception {
		PatientEditByProjectForm form = new PatientEditByProjectForm();

		request.getSession().setAttribute(SAVE_DISABLED, TRUE);
		updateRequestType(request);

		// Set current date and entered date to today's date
		PropertyUtils.setProperty(form, "currentDate", DateUtil.getCurrentDateAsText()); // TODO Needed?
		PatientSearch patientSearch = new PatientSearch();
		patientSearch.setLoadFromServerWithPatient(false);
		PropertyUtils.setProperty(form, "patientSearch", patientSearch);

		addAllPatientFormLists(form);

		addFlashMsgsToRequest(request);
		return findForward(FWD_SUCCESS, form);
	}

	@RequestMapping(value = "/PatientEditByProject", method = RequestMethod.POST)
	public ModelAndView showPatientEditByProjectSave(HttpServletRequest request,
			@ModelAttribute("form") @Valid PatientEditByProjectForm form, BindingResult result,
			RedirectAttributes redirectAttributes) throws LIMSRuntimeException, Exception {
		formValidator.validate(form, result);
		if (result.hasErrors()) {
			saveErrors(result);
			return findForward(FWD_FAIL_INSERT, form);
		}

		String forward = FWD_FAIL_INSERT;

		String sysUserId = getSysUserId(request);
		Accessioner accessioner;
		addAllPatientFormLists(form);
		accessioner = new PatientEditUpdate(form, sysUserId, request);
		if (accessioner.canAccession()) {
			forward = handleSave(request, accessioner);
		}

		accessioner = new PatientSecondEntry(form, sysUserId, request);
		if (accessioner.canAccession()) {
			forward = handleSave(request, accessioner);
		}

		accessioner = new PatientEntry(form, sysUserId, request);
		if (accessioner.canAccession()) {
			forward = handleSave(request, accessioner);
		}
		accessioner = new PatientEntryAfterSampleEntry(form, sysUserId, request);
		if (accessioner.canAccession()) {
			forward = handleSave(request, accessioner);
		}
		accessioner = new PatientEntryAfterAnalyzer(form, sysUserId, request);
		if (accessioner.canAccession()) {
			forward = handleSave(request, accessioner);
		}
		if (forward == null || FWD_FAIL_INSERT.equals(forward)) {
			logAndAddMessage(request, "performAction", "errors.UpdateException");
			forward = FWD_FAIL_INSERT;
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
		}

		return findForward(forward, form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "patientEditByProjectDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "redirect:/Dashboard.do";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/PatientEditByProject.do";
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
