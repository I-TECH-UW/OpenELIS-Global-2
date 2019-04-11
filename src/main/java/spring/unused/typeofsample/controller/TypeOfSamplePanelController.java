package spring.unused.typeofsample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.validator.BaseErrors;
import spring.unused.typeofsample.form.TypeOfSamplePanelForm;

//seemingly unused controller
@Controller
public class TypeOfSamplePanelController extends BaseController {
	@RequestMapping(value = "/TypeOfSamplePanel", method = RequestMethod.GET)
	public ModelAndView showTypeOfSamplePanel(HttpServletRequest request,
			@ModelAttribute("form") TypeOfSamplePanelForm form) {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new TypeOfSamplePanelForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();

		return findForward(forward, form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "typeOfSamplePanelDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "haitiMasterListsPageDefinition";
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
