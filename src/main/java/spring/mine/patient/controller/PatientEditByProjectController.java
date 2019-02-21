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
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
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

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("patientEditByProjectDefinition", "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("homePageDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
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
