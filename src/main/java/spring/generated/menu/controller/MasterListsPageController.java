package spring.generated.menu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.form.MainForm;
import spring.mine.common.validator.BaseErrors;

public class MasterListsPageController extends BaseController {

	public ModelAndView showMasterListsPage(HttpServletRequest request, @ModelAttribute("form") MainForm form) {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new MainForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();
		

		return findForward(forward, form);
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("masterListsPageDefinition", "form", form);
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
