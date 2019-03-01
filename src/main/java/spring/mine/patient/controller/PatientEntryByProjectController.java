package spring.mine.patient.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

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
import spring.mine.patient.form.PatientEntryByProjectForm;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.login.valueholder.UserSessionData;
import us.mn.state.health.lims.patient.saving.Accessioner;
import us.mn.state.health.lims.patient.saving.PatientEditUpdate;
import us.mn.state.health.lims.patient.saving.PatientEntry;
import us.mn.state.health.lims.patient.saving.PatientEntryAfterAnalyzer;
import us.mn.state.health.lims.patient.saving.PatientEntryAfterSampleEntry;
import us.mn.state.health.lims.patient.saving.PatientSecondEntry;
import us.mn.state.health.lims.patient.saving.RequestType;

@Controller
public class PatientEntryByProjectController extends BasePatientEntryByProject {

	@RequestMapping(value = "/PatientEntryByProject", method = RequestMethod.GET)
	public ModelAndView showPatientEntryByProject(HttpServletRequest request,
			@ModelAttribute("form") PatientEntryByProjectForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new PatientEntryByProjectForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();

		String todayAsText = DateUtil.formatDateAsText(new Date());

		request.getSession().setAttribute(IActionConstants.SAVE_DISABLED, IActionConstants.TRUE);

		// retrieve the current project, before clearing, so that we can set it on
		// later.
		String projectFormName;
		projectFormName = Accessioner.findProjectFormName(form);
		updateRequestType(request);

		addAllPatientFormLists(form);

		PropertyUtils.setProperty(form, "currentDate", todayAsText); // TODO Needed?
		PropertyUtils.setProperty(form, "receivedDateForDisplay", todayAsText);
		PropertyUtils.setProperty(form, "interviewDate", todayAsText);
		// put the projectFormName back in.
		setProjectFormName(form, projectFormName);

		return findForward(forward, form);
	}

	@RequestMapping(value = "/PatientEntryByProjectUpdate", method = RequestMethod.POST)
	public ModelAndView showPatientEntryByProjectUpdate(HttpServletRequest request,
			@ModelAttribute("form") PatientEntryByProjectForm form) throws Exception {
		String forward = FWD_SUCCESS_INSERT;
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

		return findForward(FWD_FAIL_INSERT, form);
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if (FWD_SUCCESS.equals(forward)) {
			return new ModelAndView("patientEntryByProjectDefinition", "form", form);
		} else if (FWD_FAIL.equals(forward)) {
			return new ModelAndView("homePageDefinition", "form", form);
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			String redirectURL = "/PatientEntryByProject.do?forward=success&type=" + request.getParameter("type");
			return new ModelAndView("redirect:" + redirectURL, "form", form);
		} else if (FWD_FAIL_INSERT.equals(forward)) {
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
