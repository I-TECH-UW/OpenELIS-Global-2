package spring.mine.home.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.home.form.HomeForm;

@Controller
public class HomeController extends BaseController {
	@RequestMapping(value = { "/Dashboard", "/Home" }, method = RequestMethod.GET)
	public ModelAndView showPanelManagement(HttpServletRequest request) {
		HomeForm form = new HomeForm();
		form.setFormName("mainForm");

		return findForward(FWD_SUCCESS, form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
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