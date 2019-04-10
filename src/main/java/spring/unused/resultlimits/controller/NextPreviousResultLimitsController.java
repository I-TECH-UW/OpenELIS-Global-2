package spring.unused.resultlimits.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.validator.BaseErrors;
import spring.unused.resultlimits.form.ResultLimitsForm;

//seemingly unused controller
@Controller
public class NextPreviousResultLimitsController extends BaseController {
	@RequestMapping(value = "/NextPreviousResultLimits", method = RequestMethod.GET)
	public ModelAndView showNextPreviousResultLimits(HttpServletRequest request,
			@ModelAttribute("form") ResultLimitsForm form) {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new ResultLimitsForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();

		return findForward(forward, form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "/ResultLimits.do";
		} else if (FWD_FAIL.equals(forward)) {
			return "resultLimitsDefinition";
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
