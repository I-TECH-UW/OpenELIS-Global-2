package spring.generated.typeofsample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.forms.TypeOfSampleTestMenuForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.validator.BaseErrors;

//seemingly unused controller
@Controller
public class TypeOfSampleTestMenuController extends BaseController {
	@RequestMapping(value = "/TypeOfSampleTestMenu", method = RequestMethod.GET)
	public ModelAndView showTypeOfSampleTestMenu(HttpServletRequest request,
			@ModelAttribute("form") TypeOfSampleTestMenuForm form) {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new TypeOfSampleTestMenuForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();

		return findForward(forward, form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "haitiMasterListsPageDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "/MasterListsPage.do";
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
