package spring.mine.patient.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.patient.form.PatientEntryByProjectForm;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.patient.saving.Accessioner;

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
		BaseErrors errors = new BaseErrors();
		if (form.getErrors() != null) {
			errors = (BaseErrors) form.getErrors();
		}
		ModelAndView mv = checkUserAndSetup(form, errors, request);

		if (errors.hasErrors()) {
			return mv;
		}

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

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("patientEntryByProjectDefinition", "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("homePageDefinition", "form", form);
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
