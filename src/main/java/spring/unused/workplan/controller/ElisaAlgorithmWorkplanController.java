package spring.unused.workplan.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.validator.BaseErrors;
import spring.mine.workplan.form.WorkplanForm;

//seemingly unused controller
@Controller
public class ElisaAlgorithmWorkplanController extends BaseController {
	@RequestMapping(value = "/ElisaAlgorithmWorkplan", method = RequestMethod.GET)
	public ModelAndView showElisaAlgorithmWorkplan(HttpServletRequest request,
			@ModelAttribute("form") WorkplanForm form) {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new WorkplanForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();

		return findForward(forward, form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "elisaAlgorithmResultValidationDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "homePageDefinition";
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
