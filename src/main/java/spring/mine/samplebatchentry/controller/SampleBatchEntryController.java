package spring.mine.samplebatchentry.controller;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.samplebatchentry.form.SampleBatchEntryForm;
import us.mn.state.health.lims.common.services.SampleOrderService;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.sample.bean.SampleOrderItem;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;

@Controller
public class SampleBatchEntryController extends BaseController {

	@RequestMapping(value = { "/SampleBatchEntry" }, method = RequestMethod.POST)
	public ModelAndView showSampleBatchEntry(HttpServletRequest request,
			@ModelAttribute("form") SampleBatchEntryForm form) throws DocumentException {
		String forward = FWD_SUCCESS;
		form.setFormAction("");
		BaseErrors errors = new BaseErrors();
		if (form.getErrors() != null) {
			errors = (BaseErrors) form.getErrors();
		}
		ModelAndView mv = checkUserAndSetup(form, errors, request);

		String sampleXML = form.getSampleXML();
		SampleOrderService sampleOrderService = new SampleOrderService();
		SampleOrderItem soi = sampleOrderService.getSampleOrderItem();
		// preserve fields that are already in form in refreshed object
		// (SPRING doesn't preserve objects between controllers)
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
		TestDAO testDAO = new TestDAOImpl();
		StringTokenizer tokenizer = new StringTokenizer(testIDs, ",");
		StringBuilder sBuilder = new StringBuilder();
		String seperator = "";
		while (tokenizer.hasMoreTokens()) {
			sBuilder.append(seperator);
			sBuilder.append(TestService.getUserLocalizedTestName(testDAO.getTestById(tokenizer.nextToken().trim())));
			seperator = "<br>";
		}
		TypeOfSampleDAO typeOfSampleDAO = new TypeOfSampleDAOImpl();
		String sampleType = typeOfSampleDAO.getTypeOfSampleById(sampleItem.attributeValue("sampleID"))
				.getLocalAbbreviation();
		String testNames = sBuilder.toString();
		request.setAttribute("sampleType", sampleType);
		request.setAttribute("testNames", testNames);

		// get facility name from id
		String facilityName = "";
		if (!StringUtil.isNullorNill(form.getFacilityID())) {
			OrganizationDAO organizationDAO = new OrganizationDAOImpl();
			Organization organization = new Organization();
			organization.setId(form.getFacilityID());
			organizationDAO.getData(organization);
			facilityName = organization.getOrganizationName();
		} else if (!StringUtil.isNullorNill(form.getSampleOrderItems().getNewRequesterName())) {
			facilityName = form.getSampleOrderItems().getNewRequesterName();
		}
		request.setAttribute("facilityName", facilityName);
		form.setPatientSearch(new PatientSearch());
		if (errors.hasErrors()) {
			return mv;
		}
		forward = form.getMethod();
		return findForward(forward, form);
	}

	/*
	 * private BaseErrors validate(HttpServletRequest request) { ActionMessages
	 * errors = new ActionMessages(); DateValidationProvider dateValidationProvider
	 * = new DateValidationProvider(); String curDateValid = dateValidationProvider
	 * .validateDate(dateValidationProvider.getDate(request.getParameter(
	 * "currentDate")), "past"); String recDateValid =
	 * dateValidationProvider.validateDate(
	 * dateValidationProvider.getDate(request.getParameter(
	 * "sampleOrderItems.receivedDateForDisplay")), "past");
	 *
	 * // validate date and times if
	 * (!(curDateValid.equals(IActionConstants.VALID))) { ActionError error = new
	 * ActionError("batchentry.error.curdate.invalid");
	 * errors.add(ActionMessages.GLOBAL_MESSAGE, error); } if
	 * (!GenericValidator.is24HourTime(request.getParameter("currentTime"))) {
	 * ActionError error = new ActionError("batchentry.error.curtime.invalid");
	 * errors.add(ActionMessages.GLOBAL_MESSAGE, error); } if
	 * (!(recDateValid.equals(IActionConstants.VALID))) { ActionError error = new
	 * ActionError("batchentry.error.recdate.invalid");
	 * errors.add(ActionMessages.GLOBAL_MESSAGE, error); } if
	 * (!GenericValidator.is24HourTime(request.getParameter(
	 * "sampleOrderItems.receivedTime"))) { ActionError error = new
	 * ActionError("batchentry.error.rectime.invalid");
	 * errors.add(ActionMessages.GLOBAL_MESSAGE, error); }
	 *
	 * // validate check boxes if
	 * (!GenericValidator.isBool(request.getParameter("facilityIDCheck")) &&
	 * !StringUtil.isNullorNill(request.getParameter("facilityIDCheck"))) {
	 * ActionError error = new
	 * ActionError("batchentry.error.facilitycheck.invalid");
	 * errors.add(ActionMessages.GLOBAL_MESSAGE, error); } if
	 * (!GenericValidator.isBool(request.getParameter("patientInfoCheck")) &&
	 * !StringUtil.isNullorNill(request.getParameter("patientInfoCheck"))) {
	 * ActionError error = new ActionError("batchentry.error.patientcheck.invalid");
	 * errors.add(ActionMessages.GLOBAL_MESSAGE, error); }
	 *
	 * // validate ID if
	 * (!GenericValidator.isInt(request.getParameter("facilityID")) &&
	 * !StringUtil.isNullorNill(request.getParameter("facilityID"))) { ActionError
	 * error = new ActionError("batchentry.error.facilityid.invalid");
	 * errors.add(ActionMessages.GLOBAL_MESSAGE, error); }
	 *
	 * // TO DO: (Caleb) validate sampleOrderItems.newRequesterName
	 *
	 * // validate sampleXML validateSampleXML(errors,
	 * request.getParameter("sampleXML"));
	 *
	 * return errors; }
	 */

	/*
	 * @SuppressWarnings("rawtypes") private void validateSampleXML(ActionMessages
	 * errors, String sampleXML) { DateValidationProvider dateValidationProvider =
	 * new DateValidationProvider(); try { Document sampleDom =
	 * DocumentHelper.parseText(sampleXML); for (Iterator i =
	 * sampleDom.getRootElement().elementIterator("sample"); i.hasNext();) { Element
	 * sampleItem = (Element) i.next();
	 *
	 * // validate test ids String[] testIDs =
	 * sampleItem.attributeValue("tests").split(","); for (int j = 0; j <
	 * testIDs.length; ++j) { if (!GenericValidator.isInt(testIDs[j]) &&
	 * !GenericValidator.isBlankOrNull(testIDs[j])) { ActionError error = new
	 * ActionError("batchentry.error.sampleXML.invalid");
	 * errors.add(ActionMessages.GLOBAL_MESSAGE, error); return; } } // validate
	 * panel ids String[] panelIDs = sampleItem.attributeValue("panels").split(",");
	 * for (int j = 0; j < panelIDs.length; ++j) { if
	 * (!GenericValidator.isInt(panelIDs[j]) &&
	 * !GenericValidator.isBlankOrNull(panelIDs[j])) { ActionError error = new
	 * ActionError("batchentry.error.sampleXML.invalid");
	 * errors.add(ActionMessages.GLOBAL_MESSAGE, error); return; } } // validate
	 * date String collectionDate = sampleItem.attributeValue("date").trim(); if
	 * (!GenericValidator.isBlankOrNull(collectionDate)) { String colDateValid =
	 * dateValidationProvider
	 * .validateDate(dateValidationProvider.getDate(collectionDate), "past"); if
	 * (!(colDateValid.equals(IActionConstants.VALID))) { ActionError error = new
	 * ActionError("batchentry.error.sampleXML.invalid");
	 * errors.add(ActionMessages.GLOBAL_MESSAGE, error); return; } } // validate
	 * time String collectionTime = sampleItem.attributeValue("time").trim(); if
	 * (!GenericValidator.isBlankOrNull(collectionTime) &&
	 * !GenericValidator.is24HourTime(collectionTime)) { ActionError error = new
	 * ActionError("batchentry.error.sampleXML.invalid");
	 * errors.add(ActionMessages.GLOBAL_MESSAGE, error); return; } // validate
	 * sample id String sampleId = sampleItem.attributeValue("sampleID"); if
	 * (!GenericValidator.isInt(sampleId)) { ActionError error = new
	 * ActionError("batchentry.error.sampleXML.invalid");
	 * errors.add(ActionMessages.GLOBAL_MESSAGE, error); return; } } } catch
	 * (DocumentException e) { ActionError error = new
	 * ActionError("batchentry.error.sampleXML.invalid");
	 * errors.add(ActionMessages.GLOBAL_MESSAGE, error); } }
	 */

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("On Demand".equals(forward)) {
			return new ModelAndView("sampleBatchEntryOnDemandDefinition", "form", form);
		} else if ("Pre-Printed".equals(forward)) {
			return new ModelAndView("sampleBatchEntryPrePrintedDefinition", "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("/SampleBatchEntrySetup.do", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
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
