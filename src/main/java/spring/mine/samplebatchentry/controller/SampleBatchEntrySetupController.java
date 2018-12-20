package spring.mine.samplebatchentry.controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.sample.controller.BaseSampleEntryController;
import spring.mine.samplebatchentry.form.SampleBatchEntryForm;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.SampleOrderService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.sample.form.ProjectData;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;

@Controller
public class SampleBatchEntrySetupController extends BaseSampleEntryController {

	@RequestMapping(value = "/SampleBatchEntrySetup", method = RequestMethod.GET)
	public ModelAndView showSampleBatchEntrySetup(HttpServletRequest request,
			@ModelAttribute("form") SampleBatchEntryForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new SampleBatchEntryForm();
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

		request.getSession().setAttribute(IActionConstants.NEXT_DISABLED, IActionConstants.TRUE);

		SampleOrderService sampleOrderService = new SampleOrderService();
		form.setSampleOrderItem(sampleOrderService.getSampleOrderItem());
		form.setSampleTypes(DisplayListService.getList(ListType.SAMPLE_TYPE_ACTIVE));
		form.setTestSectionList(DisplayListService.getList(ListType.TEST_SECTION));
		form.setCurrentDate(DateUtil.getCurrentDateAsText());
		form.setCurrentTime(DateUtil.getCurrentTimeAsText());
		form.getSampleOrderItem().setReceivedTime(DateUtil.getCurrentTimeAsText());
		form.getSampleOrderItem().setReceivedDateForDisplay(DateUtil.getCurrentDateAsText());
		form.setProjectDataVL(new ProjectData());
		form.setProjectDataEID(new ProjectData());

		SiteInformationDAO siteInfoDAO = new SiteInformationDAOImpl();
		String siteInfo = siteInfoDAO.getSiteInformationByName("Study Management tab").getValue();
		request.getSession().setAttribute("siteInfo", siteInfo);

		addProjectList(form);

		if (FormFields.getInstance().useField(FormFields.Field.InitialSampleCondition)) {
			form.setInitialSampleConditionList(DisplayListService.getList(ListType.INITIAL_SAMPLE_CONDITION));
		}

		return findForward(forward, form);
	}

	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("sampleBatchEntrySetupDefinition", "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("homePageDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}

	@Override
	protected String getPageTitleKey() {
		return "sample.batchentry.setup.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "sample.batchentry.setup.title";
	}
}
