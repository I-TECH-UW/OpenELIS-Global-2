package spring.mine.common.management.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.management.form.PanelManagementForm;

@Controller
public class PanelManagementController extends BaseController {
	@RequestMapping(value = "/PanelManagement", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView showPanelManagement(HttpServletRequest request) {
		PanelManagementForm form = new PanelManagementForm();

		return findForward(FWD_SUCCESS, form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "panelManagementDefinition";
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
