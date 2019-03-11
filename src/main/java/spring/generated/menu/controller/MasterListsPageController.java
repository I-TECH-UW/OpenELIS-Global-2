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
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "redirect:/MasterListsPage.do";
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
