package spring.mine.patient.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.mine.patient.form.PatientEntryByProjectForm;
import spring.mine.patient.validator.PatientEntryByProjectFormValidator;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.patient.saving.Accessioner;
import us.mn.state.health.lims.patient.saving.PatientEditUpdate;
import us.mn.state.health.lims.patient.saving.PatientEntry;
import us.mn.state.health.lims.patient.saving.PatientEntryAfterAnalyzer;
import us.mn.state.health.lims.patient.saving.PatientEntryAfterSampleEntry;
import us.mn.state.health.lims.patient.saving.PatientSecondEntry;
import us.mn.state.health.lims.patient.saving.RequestType;

@Controller
public class PatientEntryByProjectController extends BasePatientEntryByProject {

	@Autowired
	PatientEntryByProjectFormValidator formValidator;

	@RequestMapping(value = "/PatientEntryByProject", method = RequestMethod.GET)
	public ModelAndView showPatientEntryByProject(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		PatientEntryByProjectForm form = new PatientEntryByProjectForm();

		String todayAsText = DateUtil.formatDateAsText(new Date());

		request.getSession().setAttribute(SAVE_DISABLED, TRUE);

		// retrieve the current project, before clearing, so that we can set it on
		// later.
		String projectFormName = Accessioner.findProjectFormName(form);
		updateRequestType(request);

		addAllPatientFormLists(form);

		PropertyUtils.setProperty(form, "currentDate", todayAsText); // TODO Needed?
		PropertyUtils.setProperty(form, "receivedDateForDisplay", todayAsText);
		PropertyUtils.setProperty(form, "interviewDate", todayAsText);
		// put the projectFormName back in.
		setProjectFormName(form, projectFormName);

		addFlashMsgsToRequest(request);

		return findForward(FWD_SUCCESS, form);
	}

	@RequestMapping(value = "/PatientEntryByProject", method = RequestMethod.POST)
	public ModelAndView showPatientEntryByProjectUpdate(HttpServletRequest request,
			@ModelAttribute("form") PatientEntryByProjectForm form, BindingResult result,
			RedirectAttributes redirectAttributes) throws Exception {

		formValidator.validate(form, result);
		if (result.hasErrors()) {
			saveErrors(result);
			return findForward(FWD_FAIL_INSERT, form);
		}

		String sysUserId = getSysUserId(request);
		Accessioner accessioner;
		addAllPatientFormLists(form);
		accessioner = new PatientEditUpdate(form, sysUserId, request);
		String forward = FWD_FAIL_INSERT;
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
		if (FWD_FAIL_INSERT.equals(forward) || forward == null) {
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
			return "patientEntryByProjectDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "homePageDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			String redirectURL = "/PatientEntryByProject.do?type="
					+ Encode.forUriComponent(request.getParameter("type"));
			return "redirect:" + redirectURL;
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "patientEntryByProjectDefinition";
		} else {
			return "PageNotFound";
		}
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
		case INITIAL: {
			key = "banner.menu.createPatient.Initial";
			break;
		}
		case VERIFY: {
			key = "banner.menu.createPatient.Verify";
			break;
		}

		default: {
			key = "banner.menu.createPatient.Initial";
		}
		}

		return key;
	}
}
