package spring.mine.result.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.result.form.LogbookResultsForm;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.inventory.action.InventoryUtility;
import us.mn.state.health.lims.inventory.form.InventoryKitItem;
import us.mn.state.health.lims.result.action.util.ResultsLoadUtility;
import us.mn.state.health.lims.result.action.util.ResultsPaging;
import us.mn.state.health.lims.statusofsample.util.StatusRules;
import us.mn.state.health.lims.test.beanItems.TestResultItem;
import us.mn.state.health.lims.test.dao.TestSectionDAO;
import us.mn.state.health.lims.test.daoimpl.TestSectionDAOImpl;
import us.mn.state.health.lims.test.valueholder.TestSection;

@Controller
public class LogbookResultsController extends LogbookResultsBaseController {

	@RequestMapping(value = "/LogbookResults", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView showLogbookResults(HttpServletRequest request, @ModelAttribute("form") LogbookResultsForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new LogbookResultsForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();
		

		String requestedPage = request.getParameter("page");

		String testSectionId = request.getParameter("testSectionId");

		request.getSession().setAttribute(SAVE_DISABLED, TRUE);

		TestSection ts = null;

		String currentDate = getCurrentDate();
		PropertyUtils.setProperty(form, "currentDate", currentDate);
		PropertyUtils.setProperty(form, "logbookType", request.getParameter("type"));
		PropertyUtils.setProperty(form, "referralReasons",
				DisplayListService.getList(DisplayListService.ListType.REFERRAL_REASONS));
		PropertyUtils.setProperty(form, "rejectReasons",
				DisplayListService.getNumberedListWithLeadingBlank(DisplayListService.ListType.REJECTION_REASONS));

		// load testSections for drop down
		TestSectionDAO testSectionDAO = new TestSectionDAOImpl();
		List<IdValuePair> testSections = DisplayListService.getList(ListType.TEST_SECTION);
		PropertyUtils.setProperty(form, "testSections", testSections);
		PropertyUtils.setProperty(form, "testSectionsByName",
				DisplayListService.getList(ListType.TEST_SECTION_BY_NAME));

		if (!GenericValidator.isBlankOrNull(testSectionId)) {
			ts = testSectionDAO.getTestSectionById(testSectionId);
			PropertyUtils.setProperty(form, "testSectionId", "0");
		}

		setRequestType(ts == null ? StringUtil.getMessageForKey("workplan.unit.types") : ts.getLocalizedName());

		List<TestResultItem> tests;

		ResultsPaging paging = new ResultsPaging();
		List<InventoryKitItem> inventoryList = new ArrayList<>();
		ResultsLoadUtility resultsLoadUtility = new ResultsLoadUtility(getSysUserId(request));

		if (GenericValidator.isBlankOrNull(requestedPage)) {

			new StatusRules().setAllowableStatusForLoadingResults(resultsLoadUtility);

			if (!GenericValidator.isBlankOrNull(testSectionId)) {
				tests = resultsLoadUtility.getUnfinishedTestResultItemsInTestSection(testSectionId);
			} else {
				tests = new ArrayList<>();
			}

			if (ConfigurationProperties.getInstance().isPropertyValueEqual(Property.PATIENT_DATA_ON_RESULTS_BY_ROLE,
					"true") && !userHasPermissionForModule(request, "PatientResults")) {
				for (TestResultItem resultItem : tests) {
					resultItem.setPatientInfo("---");
				}

			}

			paging.setDatabaseResults(request, form, tests);

		} else {
			paging.page(request, form, requestedPage);
		}
		PropertyUtils.setProperty(form, "displayTestKit", false);
		if (ts != null) {
			// this does not look right what happens after a new page!!!
			boolean isHaitiClinical = ConfigurationProperties.getInstance()
					.isPropertyValueEqual(Property.configurationName, "Haiti Clinical");
			if (resultsLoadUtility.inventoryNeeded() || (isHaitiClinical && ("VCT").equals(ts.getTestSectionName()))) {
				InventoryUtility inventoryUtility = new InventoryUtility();
				inventoryList = inventoryUtility.getExistingActiveInventory();

				PropertyUtils.setProperty(form, "displayTestKit", true);
			}
		}
		List<String> hivKits = new ArrayList<>();
		List<String> syphilisKits = new ArrayList<>();

		for (InventoryKitItem item : inventoryList) {
			if (item.getType().equals("HIV")) {
				hivKits.add(item.getInventoryLocationId());
			} else {
				syphilisKits.add(item.getInventoryLocationId());
			}
		}
		PropertyUtils.setProperty(form, "hivKits", hivKits);
		PropertyUtils.setProperty(form, "syphilisKits", syphilisKits);
		PropertyUtils.setProperty(form, "inventoryItems", inventoryList);

		return findForward(forward, form);
	}

	private String getCurrentDate() {
		Date today = Calendar.getInstance().getTime();
		return DateUtil.formatDateAsText(today);

	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if (FWD_SUCCESS.equals(forward)) {
			return new ModelAndView("resultsLogbookDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}
}
