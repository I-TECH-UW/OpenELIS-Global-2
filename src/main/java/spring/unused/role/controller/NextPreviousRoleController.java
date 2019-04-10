package spring.unused.role.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.validator.BaseErrors;
import spring.unused.role.form.RoleForm;

//seemingly unused controller
@Controller
public class NextPreviousRoleController extends BaseController {
	@RequestMapping(value = "/NextPreviousRole", method = RequestMethod.GET)
	public ModelAndView showNextPreviousRole(HttpServletRequest request, @ModelAttribute("form") RoleForm form) {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new RoleForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();

		return findForward(forward, form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "/Role.do";
		} else if (FWD_FAIL.equals(forward)) {
			return "roleDefinition";
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
