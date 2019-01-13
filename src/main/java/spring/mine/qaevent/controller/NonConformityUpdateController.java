package spring.mine.qaevent.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.qaevent.form.NonConformityForm;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.qaevent.worker.NonConformityUpdateData;
import us.mn.state.health.lims.qaevent.worker.NonConformityUpdateWorker;

@Controller
public class NonConformityUpdateController extends BaseController {

	@RequestMapping(value = "/NonConformityUpdate", method = RequestMethod.POST)
	public ModelAndView showNonConformityUpdate(HttpServletRequest request,
			@ModelAttribute("form") NonConformityForm form) {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new NonConformityForm();
		}
		form.setFormAction("");
		BaseErrors errors = new BaseErrors();
		if (getErrors() != null) {
			errors = (BaseErrors) getErrors();
		}
		ModelAndView mv = checkUserAndSetup(form, errors, request);
		if (errors.hasErrors()) {
			return mv;
		}

		NonConformityUpdateData data = new NonConformityUpdateData(form, currentUserId);
		NonConformityUpdateWorker worker = new NonConformityUpdateWorker(data);
		String result = worker.update();

		if (IActionConstants.FWD_FAIL.equals(result)) {
			saveErrors(worker.getErrors());
			request.setAttribute(Globals.ERROR_KEY, errors);
		}

		return findForward(forward, form);
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("redirect:NonConformity.do?forward=success", "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("nonConformityDefiniton", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}

	@Override
	protected String getPageSubtitleKey() {
		return "qaevent.add.title";
	}

	@Override
	protected String getPageTitleKey() {
		return "qaevent.add.title";
	}
}
