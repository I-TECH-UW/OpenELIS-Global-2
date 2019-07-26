package org.openelisglobal.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.validator.BaseErrors;
import org.openelisglobal.sample.form.SampleConfirmationEntryForm;

@Controller
public class SampleConfirmationUpdateController extends BaseController {

	@RequestMapping(value = "/SampleConfirmation", method = RequestMethod.POST)
	public ModelAndView showSampleConfirmationUpdate(HttpServletRequest request,
			@ModelAttribute("form") SampleConfirmationEntryForm form) {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new SampleConfirmationEntryForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();

		return findForward(forward, form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "/SampleConfirmationEntry.do?forward=success";
		} else if (FWD_FAIL.equals(forward)) {
			return "/SampleConfirmationEntry.do?forward=fail";
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
