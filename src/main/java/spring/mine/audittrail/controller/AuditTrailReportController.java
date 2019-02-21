package spring.mine.audittrail.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.audittrail.form.AuditTrailViewForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import us.mn.state.health.lims.audittrail.action.workers.AuditTrailItem;
import us.mn.state.health.lims.audittrail.action.workers.AuditTrailViewWorker;

@Controller
public class AuditTrailReportController extends BaseController {
	@RequestMapping(value = "/AuditTrailReport", method = RequestMethod.GET)
	public ModelAndView showAuditTrailReport(HttpServletRequest request,
			@ModelAttribute("form") AuditTrailViewForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new AuditTrailViewForm();
		}
		form.setFormAction("");
		form.setFormMethod(RequestMethod.GET);
		Errors errors = new BaseErrors();
		

		String accessionNumber = form.getString("accessionNumberSearch");
		if (!GenericValidator.isBlankOrNull(accessionNumber)) {
			AuditTrailViewWorker worker = new AuditTrailViewWorker(accessionNumber);
			List<AuditTrailItem> items = worker.getAuditTrail();
			PropertyUtils.setProperty(form, "log", items);
			PropertyUtils.setProperty(form, "accessionNumber", accessionNumber);
			PropertyUtils.setProperty(form, "sampleOrderItems", worker.getSampleOrderSnapshot());
			PropertyUtils.setProperty(form, "patientProperties", worker.getPatientSnapshot());
		}

		return findForward(forward, form);
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("auditTrailViewDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}

	@Override
	protected String getPageTitleKey() {
		return "reports.auditTrail";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "reports.auditTrail";
	}
}
