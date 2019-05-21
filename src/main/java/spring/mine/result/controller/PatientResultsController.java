package spring.mine.result.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.internationalization.MessageUtil;
import spring.mine.result.form.PatientResultsForm;
import spring.service.patient.PatientService;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.inventory.action.InventoryUtility;
import us.mn.state.health.lims.inventory.form.InventoryKitItem;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.result.action.util.ResultsLoadUtility;
import us.mn.state.health.lims.result.action.util.ResultsPaging;
import us.mn.state.health.lims.test.beanItems.TestResultItem;

@Controller
public class PatientResultsController extends BaseController {

	@Autowired
	PatientService patientService;

	@RequestMapping(value = "/PatientResults", method = RequestMethod.GET)
	public ModelAndView showPatientResults(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		PatientResultsForm form = new PatientResultsForm();

		ResultsLoadUtility resultsUtility = new ResultsLoadUtility(getSysUserId(request));
		request.getSession().setAttribute(SAVE_DISABLED, TRUE);

		PropertyUtils.setProperty(form, "displayTestKit", Boolean.FALSE);
		PropertyUtils.setProperty(form, "referralReasons",
				DisplayListService.getList(DisplayListService.ListType.REFERRAL_REASONS));
		PropertyUtils.setProperty(form, "rejectReasons",
				DisplayListService.getNumberedListWithLeadingBlank(DisplayListService.ListType.REJECTION_REASONS));
		PatientSearch patientSearch = new PatientSearch();
		patientSearch.setLoadFromServerWithPatient(true);
		patientSearch.setSelectedPatientActionButtonText(MessageUtil.getMessage("resultsentry.patient.search"));
		PropertyUtils.setProperty(form, "patientSearch", patientSearch);

		ResultsPaging paging = new ResultsPaging();
		String newPage = request.getParameter("page");
		if (GenericValidator.isBlankOrNull(newPage)) {

			String patientID = request.getParameter("patientID");

			if (!GenericValidator.isBlankOrNull(patientID)) {

				PropertyUtils.setProperty(form, "searchFinished", Boolean.TRUE);
				Patient patient = getPatient(patientID);

				String statusRules = ConfigurationProperties.getInstance()
						.getPropertyValueUpperCase(Property.StatusRules);
				if (statusRules.equals(STATUS_RULES_RETROCI)) {
					resultsUtility.addExcludedAnalysisStatus(AnalysisStatus.TechnicalRejected);
					resultsUtility.addExcludedAnalysisStatus(AnalysisStatus.Canceled);
				} else if (statusRules.equals(STATUS_RULES_HAITI) || statusRules.equals(STATUS_RULES_HAITI_LNSP)) {
					resultsUtility.addExcludedAnalysisStatus(AnalysisStatus.Canceled);
				}

				List<TestResultItem> results = resultsUtility.getGroupedTestsForPatient(patient);

				PropertyUtils.setProperty(form, "testResult", results);

				// move this out of results utility
				resultsUtility.addIdentifingPatientInfo(patient, form);

				if (resultsUtility.inventoryNeeded()) {
					addInventory(form);
					PropertyUtils.setProperty(form, "displayTestKit", true);
				} else {
					addEmptyInventoryList(form);
				}

				paging.setDatabaseResults(request, form, results);

			} else {
				PropertyUtils.setProperty(form, "testResult", new ArrayList<TestResultItem>());
				PropertyUtils.setProperty(form, "searchFinished", Boolean.FALSE);
			}
		} else {
			paging.page(request, form, newPage);
		}

		addFlashMsgsToRequest(request);
		return findForward(FWD_SUCCESS, form);
	}

	private void addInventory(PatientResultsForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		InventoryUtility inventoryUtility = new InventoryUtility();
		List<InventoryKitItem> list = inventoryUtility.getExistingActiveInventory();
		PropertyUtils.setProperty(form, "inventoryItems", list);
	}

	private void addEmptyInventoryList(PatientResultsForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		PropertyUtils.setProperty(form, "inventoryItems", new ArrayList<InventoryKitItem>());
	}

	private Patient getPatient(String patientID) {
		return patientService.get(patientID);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "patientResultDefinition";
		} else {
			return "PageNotFound";
		}
	}

	@Override
	protected String getPageTitleKey() {
		return "banner.menu.results";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "banner.menu.results";
	}
}
