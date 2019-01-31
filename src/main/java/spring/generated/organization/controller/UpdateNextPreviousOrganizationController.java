package spring.generated.organization.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.organization.form.OrganizationForm;

@Controller
public class UpdateNextPreviousOrganizationController extends BaseController {
	@RequestMapping(value = "/UpdateNextPreviousOrganization", method = RequestMethod.GET)
	public ModelAndView showUpdateNextPreviousOrganization(HttpServletRequest request,
			@ModelAttribute("form") OrganizationForm form) {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new OrganizationForm();
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
			return new ModelAndView("/NextPreviousOrganization.do", "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("organizationDefinition", "form", form);
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
