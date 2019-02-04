package spring.generated.dictionary.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.dictionary.form.DictionaryForm;

@Controller
public class NextPreviousDictionaryController extends BaseController {
	public ModelAndView showNextPreviousDictionary(HttpServletRequest request,
			@ModelAttribute("form") DictionaryForm form) {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new DictionaryForm();
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

		return findForward(forward, form);
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("/Dictionary.do", "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("dictionaryDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
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
