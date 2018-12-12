package spring.mine.sample.controller;

import java.lang.String;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.sample.form.SamplePatientEntryForm;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.services.SampleOrderService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;

@Controller
public class SamplePatientEntryController extends BaseSampleEntryController {
	@RequestMapping(value = "/SamplePatientEntry", method = RequestMethod.GET)
	public ModelAndView showSamplePatientEntry(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		SamplePatientEntryForm form = new SamplePatientEntryForm();
		form.setFormAction("");
		BaseErrors errors = new BaseErrors();
		ModelAndView mv = checkUserAndSetup(form, errors, request);

		if (errors.hasErrors()) {
			return mv;
		}

		request.getSession().setAttribute(IActionConstants.SAVE_DISABLED, IActionConstants.TRUE);
		SampleOrderService sampleOrderService = new SampleOrderService();
		PropertyUtils.setProperty(form, "sampleOrderItems", sampleOrderService.getSampleOrderItem());
		PropertyUtils.setProperty(form, "patientProperties", new PatientManagementInfo());
		PropertyUtils.setProperty(form, "patientSearch", new PatientSearch());
		PropertyUtils.setProperty(form, "sampleTypes", DisplayListService.getList(ListType.SAMPLE_TYPE_ACTIVE));
		PropertyUtils.setProperty(form, "testSectionList", DisplayListService.getList(ListType.TEST_SECTION));
		PropertyUtils.setProperty(form, "currentDate", DateUtil.getCurrentDateAsText());

		for (Object program : form.getSampleOrderItems().getProgramList()) {
			System.out.println(((IdValuePair) program).getValue());
		}

		addProjectList(form);

		if (FormFields.getInstance().useField(FormFields.Field.InitialSampleCondition)) {
			PropertyUtils.setProperty(form, "initialSampleConditionList",
					DisplayListService.getList(ListType.INITIAL_SAMPLE_CONDITION));
		}

		return findForward(forward, form);
	}

	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("samplePatientEntryDefinition", "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("homePageDefinition", "form", form);
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
