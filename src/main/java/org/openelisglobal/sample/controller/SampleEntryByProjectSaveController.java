package org.openelisglobal.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.openelisglobal.sample.form.SampleEntryByProjectForm;
import org.openelisglobal.common.validator.BaseErrors;
import org.openelisglobal.sample.controller.BaseSampleEntryController;

@Controller
public class SampleEntryByProjectSaveController extends BaseSampleEntryController {
	@RequestMapping(value = "/SampleEntryByProjectSave", method = RequestMethod.POST)
	public ModelAndView showSampleEntryByProjectSave(HttpServletRequest request,
			@ModelAttribute("form") SampleEntryByProjectForm form) throws Exception {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new SampleEntryByProjectForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();

		return findForward(forward, form);
	}

	@Override
	protected String findLocalForward(String forward) {

		if (FWD_SUCCESS.equals(forward)) {
			return "forward:/SampleEntryByProject.do?forward=success";
		} else if (FWD_FAIL.equals(forward)) {
			return "sampleEntryByProjectDefinition";
		} else {
			return "PageNotFound";
		}
	}

	@Override
	protected String getPageTitleKey() {
		return null;
	}

	@Override
	protected String getPageSubtitleKey() {
		return null;
	}
}
