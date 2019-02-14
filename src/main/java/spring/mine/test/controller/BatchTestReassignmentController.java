package spring.mine.test.controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.test.form.BatchTestReassignment;
import us.mn.state.health.lims.common.services.DisplayListService;

@Controller
public class BatchTestReassignmentController extends BaseController {
	@RequestMapping(value = "/BatchTestReassignment", method = RequestMethod.GET)
	public ModelAndView showBatchTestReassignment(HttpServletRequest request,
			@ModelAttribute("form") BatchTestReassignment form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new BatchTestReassignment();
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

		PropertyUtils.setProperty(form, "sampleList",
				DisplayListService.getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE));
		return findForward(forward, form);
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("BatchTestReassignmentDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}

	@Override
	protected String getPageTitleKey() {
		return "configuration.batch.test.reassignment";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "configuration.batch.test.reassignment";
	}
}
