package us.mn.state.health.lims.samplebatchentry.action;

import java.util.Iterator;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.provider.validation.DateValidationProvider;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.services.SampleOrderService;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.sample.action.BaseSampleEntryAction;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;

public class SampleBatchEntryAction extends BaseSampleEntryAction {

  @Override
  protected ActionForward performAction(ActionMapping mapping, ActionForm form,
          HttpServletRequest request, HttpServletResponse response) throws Exception {
    String forward = "success";
    BaseActionForm dynaForm = (BaseActionForm) form;
    dynaForm.initialize(mapping);

    String sampleXML = request.getParameter("sampleXML");
    SampleOrderService sampleOrderService = new SampleOrderService();

    ActionMessages errors = validate(request);
    if (!errors.isEmpty()) {
      saveErrors(request, errors);
      request.setAttribute(IActionConstants.FWD_SUCCESS, false);
      forward = FWD_FAIL;
      return mapping.findForward(forward);
    }

    // set properties given by previous (setup) page
    PropertyUtils.setProperty(dynaForm, "sampleOrderItems",
            sampleOrderService.getSampleOrderItem());
    PropertyUtils.setProperty(dynaForm, "sampleTypes",
            DisplayListService.getList(ListType.SAMPLE_TYPE_ACTIVE));
    PropertyUtils.setProperty(dynaForm, "testSectionList",
            DisplayListService.getList(ListType.TEST_SECTION));
    PropertyUtils.setProperty(dynaForm, "currentDate", request.getParameter("currentDate"));
    PropertyUtils.setProperty(dynaForm, "currentTime", request.getParameter("currentTime"));
    PropertyUtils.setProperty(dynaForm, "sampleOrderItems.receivedTime",
            request.getParameter("sampleOrderItems.receivedTime"));
    PropertyUtils.setProperty(dynaForm, "sampleOrderItems.receivedDateForDisplay",
            request.getParameter("sampleOrderItems.receivedDateForDisplay"));
    PropertyUtils.setProperty(dynaForm, "sampleOrderItems.referringSiteId",
            request.getParameter("facilityID"));
    PropertyUtils.setProperty(dynaForm, "sampleOrderItems.newRequesterName",
            request.getParameter("sampleOrderItems.newRequesterName"));
    PropertyUtils.setProperty(dynaForm, "sampleXML", sampleXML);

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
      sBuilder.append(TestService
              .getUserLocalizedTestName(testDAO.getTestById(tokenizer.nextToken().trim())));
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
    if (!StringUtil.isNullorNill(request.getParameter("facilityID"))) {
      OrganizationDAO organizationDAO = new OrganizationDAOImpl();
      Organization organization = new Organization();
      organization.setId(request.getParameter("facilityID"));
      organizationDAO.getData(organization);
      facilityName = organization.getOrganizationName();
    } else if (!StringUtil
            .isNullorNill(request.getParameter("sampleOrderItems.newRequesterName"))) {
      facilityName = request.getParameter("sampleOrderItems.newRequesterName");
    }
    request.setAttribute("facilityName", facilityName);

    return mapping.findForward(findForward(request));
  }

	private String findForward(HttpServletRequest request) {
		String method = request.getParameter("method");
		if ("On Demand".equals(method)) {
			return "ondemand";
		} else if ("Pre-Printed".equals(method)) {
			return "preprinted";
		}
		return "fail";
	}

  private ActionMessages validate(HttpServletRequest request) {
    ActionMessages errors = new ActionMessages();
    DateValidationProvider dateValidationProvider = new DateValidationProvider();
    String curDateValid = dateValidationProvider.validateDate(
            dateValidationProvider.getDate(request.getParameter("currentDate")), "past");
    String recDateValid = dateValidationProvider.validateDate(dateValidationProvider
            .getDate(request.getParameter("sampleOrderItems.receivedDateForDisplay")), "past");

    // validate date and times
    if (!(curDateValid.equals(IActionConstants.VALID))) {
      ActionError error = new ActionError("batchentry.error.curdate.invalid");
      errors.add(ActionMessages.GLOBAL_MESSAGE, error);
    }
    if (!GenericValidator.is24HourTime(request.getParameter("currentTime"))) {
      ActionError error = new ActionError("batchentry.error.curtime.invalid");
      errors.add(ActionMessages.GLOBAL_MESSAGE, error);
    }
    if (!(recDateValid.equals(IActionConstants.VALID))) {
      ActionError error = new ActionError("batchentry.error.recdate.invalid");
      errors.add(ActionMessages.GLOBAL_MESSAGE, error);
    }
    if (!GenericValidator.is24HourTime(request.getParameter("sampleOrderItems.receivedTime"))) {
      ActionError error = new ActionError("batchentry.error.rectime.invalid");
      errors.add(ActionMessages.GLOBAL_MESSAGE, error);
    }

    // validate check boxes
    if (!GenericValidator.isBool(request.getParameter("facilityIDCheck"))
            && !StringUtil.isNullorNill(request.getParameter("facilityIDCheck"))) {
      ActionError error = new ActionError("batchentry.error.facilitycheck.invalid");
      errors.add(ActionMessages.GLOBAL_MESSAGE, error);
    }
    if (!GenericValidator.isBool(request.getParameter("patientInfoCheck"))
            && !StringUtil.isNullorNill(request.getParameter("patientInfoCheck"))) {
      ActionError error = new ActionError("batchentry.error.patientcheck.invalid");
      errors.add(ActionMessages.GLOBAL_MESSAGE, error);
    }

    // validate ID
    if (!GenericValidator.isInt(request.getParameter("facilityID"))
            && !StringUtil.isNullorNill(request.getParameter("facilityID"))) {
      ActionError error = new ActionError("batchentry.error.facilityid.invalid");
      errors.add(ActionMessages.GLOBAL_MESSAGE, error);
    }

    // TO DO: (Caleb) validate sampleOrderItems.newRequesterName

    // validate sampleXML
    validateSampleXML(errors, request.getParameter("sampleXML"));

    return errors;
  }

  @SuppressWarnings("rawtypes")
  private void validateSampleXML(ActionMessages errors, String sampleXML) {
    DateValidationProvider dateValidationProvider = new DateValidationProvider();
    try {
      Document sampleDom = DocumentHelper.parseText(sampleXML);
      for (Iterator i = sampleDom.getRootElement().elementIterator("sample"); i.hasNext();) {
        Element sampleItem = (Element) i.next();

        // validate test ids
        String[] testIDs = sampleItem.attributeValue("tests").split(",");
        for (int j = 0; j < testIDs.length; ++j) {
          if (!GenericValidator.isInt(testIDs[j]) && !GenericValidator.isBlankOrNull(testIDs[j])) {
            ActionError error = new ActionError("batchentry.error.sampleXML.invalid");
            errors.add(ActionMessages.GLOBAL_MESSAGE, error);
            return;
          }
        }
        // validate panel ids
        String[] panelIDs = sampleItem.attributeValue("panels").split(",");
        for (int j = 0; j < panelIDs.length; ++j) {
          if (!GenericValidator.isInt(panelIDs[j])
                  && !GenericValidator.isBlankOrNull(panelIDs[j])) {
            ActionError error = new ActionError("batchentry.error.sampleXML.invalid");
            errors.add(ActionMessages.GLOBAL_MESSAGE, error);
            return;
          }
        }
        // validate date
        String collectionDate = sampleItem.attributeValue("date").trim();
        if (!GenericValidator.isBlankOrNull(collectionDate)) {
          String colDateValid = dateValidationProvider
                  .validateDate(dateValidationProvider.getDate(collectionDate), "past");
          if (!(colDateValid.equals(IActionConstants.VALID))) {
            ActionError error = new ActionError("batchentry.error.sampleXML.invalid");
            errors.add(ActionMessages.GLOBAL_MESSAGE, error);
            return;
          }
        }
        // validate time
        String collectionTime = sampleItem.attributeValue("time").trim();
        if (!GenericValidator.isBlankOrNull(collectionTime)
                && !GenericValidator.is24HourTime(collectionTime)) {
          ActionError error = new ActionError("batchentry.error.sampleXML.invalid");
          errors.add(ActionMessages.GLOBAL_MESSAGE, error);
          return;
        }
        // validate sample id
        String sampleId = sampleItem.attributeValue("sampleID");
        if (!GenericValidator.isInt(sampleId)) {
          ActionError error = new ActionError("batchentry.error.sampleXML.invalid");
          errors.add(ActionMessages.GLOBAL_MESSAGE, error);
          return;
        }
      }
    } catch (DocumentException e) {
      ActionError error = new ActionError("batchentry.error.sampleXML.invalid");
      errors.add(ActionMessages.GLOBAL_MESSAGE, error);
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
