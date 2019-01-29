package spring.mine.testconfiguration.controller;

import java.lang.String;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.forms.SampleEntryByProjectForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.testconfiguration.form.TestRenameEntryForm;
import us.mn.state.health.lims.common.services.DisplayListService;

@Controller
public class TestRenameEntryController extends BaseController {
	@RequestMapping(
			value = "/TestRenameEntry", 
			method = RequestMethod.GET
	)
	public ModelAndView showTestRenameEntry(HttpServletRequest request,
			@ModelAttribute("form") TestRenameEntryForm form) {	
		String forward = FWD_SUCCESS;
		if (form == null ) {
			 form = new TestRenameEntryForm();
		}
		form.setFormAction("");
		BaseErrors errors = new BaseErrors();
		ModelAndView mv = checkUserAndSetup(form, errors, request);

		if (errors.hasErrors()) {
			return mv;
		}

		form.setTestList(DisplayListService.getList(DisplayListService.ListType.ALL_TESTS));

		return findForward(forward, form);
	}

	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("testRenameDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}

	protected String getPageTitleKey() {
		return null;
	}

	protected String getPageSubtitleKey() {
		return null;
	}
}
