package spring.generated.typeofsample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.forms.TypeOfSamplePanelMenuForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.validator.BaseErrors;

//seemingly unused controller
@Controller
public class TypeOfSamplePanelMenuController extends BaseController {
	@RequestMapping(value = "/TypeOfSamplePanelMenu", method = RequestMethod.GET)
	public ModelAndView showTypeOfSamplePanelMenu(HttpServletRequest request,
			@ModelAttribute("form") TypeOfSamplePanelMenuForm form) {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new TypeOfSamplePanelMenuForm();
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
