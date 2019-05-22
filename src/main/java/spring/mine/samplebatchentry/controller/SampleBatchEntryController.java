package spring.mine.samplebatchentry.controller;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.samplebatchentry.form.SampleBatchEntryForm;
import spring.mine.samplebatchentry.validator.SampleBatchEntryFormValidator;
import spring.service.organization.OrganizationService;
import us.mn.state.health.lims.common.services.TestService;
import spring.service.typeofsample.TypeOfSampleService;
import us.mn.state.health.lims.common.services.SampleOrderService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.sample.bean.SampleOrderItem;

@Controller
public class SampleBatchEntryController extends BaseController {

	@Autowired
	SampleBatchEntryFormValidator formValidator;

	@Autowired
	spring.service.test.TestService testService;
	@Autowired
	TypeOfSampleService typeOfSampleService;
	@Autowired
	OrganizationService organizationService;

	@RequestMapping(value = { "/SampleBatchEntry" }, method = RequestMethod.POST)
	public ModelAndView showSampleBatchEntry(HttpServletRequest request,
			@ModelAttribute("form") @Valid SampleBatchEntryForm form, BindingResult result) throws DocumentException {
		formValidator.validate(form, result);
		if (result.hasErrors()) {
			saveErrors(result);
			return findForward(FWD_FAIL, form);
		}

		String sampleXML = form.getSampleXML();
		SampleOrderService sampleOrderService = new SampleOrderService();
		SampleOrderItem soi = sampleOrderService.getSampleOrderItem();
		// preserve fields that are already in form in refreshed object
		soi.setReceivedTime(form.getSampleOrderItems().getReceivedTime());
		soi.setReceivedDateForDisplay(form.getSampleOrderItems().getReceivedDateForDisplay());
		soi.setNewRequesterName(form.getSampleOrderItems().getNewRequesterName());
		soi.setReferringSiteId(form.getFacilityID());
		form.setSampleOrderItems(soi);

		form.setLocalDBOnly(ConfigurationProperties.getInstance()
				.getPropertyValueLowerCase(Property.UseExternalPatientInfo).equals("false"));
		/*
		 * errors = validate(request); if (errors.hasErrors()) { saveErrors(errors,
		 * form); request.setAttribute(IActionConstants.FWD_SUCCESS, false); forward =
		 * FWD_FAIL; return findForward(forward, form); }
		 */

		// get summary of tests selected to place in common fields section
		Document sampleDom = DocumentHelper.parseText(sampleXML);
		Element sampleItem = sampleDom.getRootElement().element("sample");
		String testIDs = sampleItem.attributeValue("tests");
		StringTokenizer tokenizer = new StringTokenizer(testIDs, ",");
		StringBuilder sBuilder = new StringBuilder();
		String seperator = "";
		while (tokenizer.hasMoreTokens()) {
			sBuilder.append(seperator);
			sBuilder.append(TestService.getUserLocalizedTestName(testService.get(tokenizer.nextToken().trim())));
			seperator = "<br>";
		}
		String sampleType = typeOfSampleService.get(sampleItem.attributeValue("sampleID")).getLocalAbbreviation();
		String testNames = sBuilder.toString();
		request.setAttribute("sampleType", sampleType);
		request.setAttribute("testNames", testNames);

		// get facility name from id
		String facilityName = "";
		if (!StringUtil.isNullorNill(form.getFacilityID())) {
			Organization organization = organizationService.get(form.getFacilityID());
			facilityName = organization.getOrganizationName();
		} else if (!StringUtil.isNullorNill(form.getSampleOrderItems().getNewRequesterName())) {
			facilityName = form.getSampleOrderItems().getNewRequesterName();
		}
		request.setAttribute("facilityName", facilityName);
		form.setPatientSearch(new PatientSearch());

		return findForward(form.getMethod(), form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if ("On Demand".equals(forward)) {
			return "sampleBatchEntryOnDemandDefinition";
		} else if ("Pre-Printed".equals(forward)) {
			return "sampleBatchEntryPrePrintedDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "sampleBatchEntrySetupDefinition";
		} else {
			return "redirect:/SampleBatchEntrySetup.do";
		}
	}

	@Override
	protected String getPageTitleKey() {
		return "sample.batchentry.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "sample.batchentry.title";
	}
}
