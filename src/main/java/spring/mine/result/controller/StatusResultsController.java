package spring.mine.result.controller;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.result.form.StatusResultsForm;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.StatusService.OrderStatus;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.inventory.action.InventoryUtility;
import us.mn.state.health.lims.inventory.form.InventoryKitItem;
import us.mn.state.health.lims.result.action.util.ResultsLoadUtility;
import us.mn.state.health.lims.result.action.util.ResultsPaging;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleitem.dao.SampleItemDAO;
import us.mn.state.health.lims.sampleitem.daoimpl.SampleItemDAOImpl;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.test.beanItems.TestResultItem;

@Controller
public class StatusResultsController extends BaseController {

	private static final long serialVersionUID = 1L;
	private static final boolean REVERSE_SORT_ORDER = false;
	private final AnalysisDAO analysisDAO = new AnalysisDAOImpl();
	private final SampleDAO sampleDAO = new SampleDAOImpl();
	private ResultsLoadUtility resultsUtility;
	private final InventoryUtility inventoryUtility = new InventoryUtility();
	private static final ConfigurationProperties configProperties = ConfigurationProperties.getInstance();

	private static Set<Integer> excludedStatusIds;

	static {
		// currently this is the only one being excluded for Haiti_LNSP. If it
		// gets more complicate use the status sets
		excludedStatusIds = new HashSet<>();
		excludedStatusIds.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.Canceled)));
	}

	@RequestMapping(value = "/StatusResults", method = RequestMethod.GET)
	public ModelAndView showStatusResults(HttpServletRequest request, @ModelAttribute("form") StatusResultsForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new StatusResultsForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();
		

		resultsUtility = new ResultsLoadUtility(getSysUserId(request));

		request.getSession().setAttribute(SAVE_DISABLED, TRUE);

		String newRequest = request.getParameter("blank");

		PropertyUtils.setProperty(form, "referralReasons",
				DisplayListService.getList(DisplayListService.ListType.REFERRAL_REASONS));
		PropertyUtils.setProperty(form, "rejectReasons",
				DisplayListService.getNumberedListWithLeadingBlank(DisplayListService.ListType.REJECTION_REASONS));

		ResultsPaging paging = new ResultsPaging();

		String newPage = request.getParameter("page");
		if (GenericValidator.isBlankOrNull(newPage)) {
			List<TestResultItem> tests;
			if (GenericValidator.isBlankOrNull(newRequest) || newRequest.equals("false")) {
				tests = setSearchResults(form);

				if (configProperties.isPropertyValueEqual(Property.PATIENT_DATA_ON_RESULTS_BY_ROLE, "true")
						&& !userHasPermissionForModule(request, "PatientResults")) {
					for (TestResultItem resultItem : tests) {
						resultItem.setPatientInfo("---");
					}
				}

				paging.setDatabaseResults(request, form, tests);
			} else {
				setEmptyResults(form);
			}

			setSelectionLists(form);
		} else {
			paging.page(request, form, newPage);
		}
		return findForward(forward, form);
	}

	private List<TestResultItem> setSearchResults(StatusResultsForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<TestResultItem> tests = getSelectedTests(form);
		PropertyUtils.setProperty(form, "searchFinished", Boolean.TRUE);

		if (resultsUtility.inventoryNeeded()) {
			addInventory(form);
			PropertyUtils.setProperty(form, "displayTestKit", true);
		} else {
			addEmptyInventoryList(form);
			PropertyUtils.setProperty(form, "displayTestKit", false);
		}

		return tests;
	}

	private void setEmptyResults(StatusResultsForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		PropertyUtils.setProperty(form, "testResult", new ArrayList<TestResultItem>());
		PropertyUtils.setProperty(form, "displayTestKit", false);
		PropertyUtils.setProperty(form, "collectionDate", "");
		PropertyUtils.setProperty(form, "recievedDate", "");
		PropertyUtils.setProperty(form, "selectedAnalysisStatus", "");
		PropertyUtils.setProperty(form, "selectedTest", "");
		PropertyUtils.setProperty(form, "searchFinished", Boolean.FALSE);
	}

	private void addInventory(StatusResultsForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		List<InventoryKitItem> list = inventoryUtility.getExistingActiveInventory();
		PropertyUtils.setProperty(form, "inventoryItems", list);
	}

	private void addEmptyInventoryList(StatusResultsForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		PropertyUtils.setProperty(form, "inventoryItems", new ArrayList<InventoryKitItem>());
	}

	private void setSelectionLists(StatusResultsForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		List<DropPair> analysisStatusList = getAnalysisStatusTypes();

		PropertyUtils.setProperty(form, "analysisStatusSelections", analysisStatusList);
		PropertyUtils.setProperty(form, "testSelections",
				DisplayListService.getListWithLeadingBlank(DisplayListService.ListType.ALL_TESTS));

		List<DropPair> sampleStatusList = getSampleStatusTypes();
		PropertyUtils.setProperty(form, "sampleStatusSelections", sampleStatusList);

	}

	private List<TestResultItem> getSelectedTests(StatusResultsForm form) {
		String collectionDate = form.getString("collectionDate");
		String receivedDate = form.getString("recievedDate");
		String analysisStatus = form.getString("selectedAnalysisStatus");
		String sampleStatus = form.getString("selectedSampleStatus");
		String test = form.getString("selectedTest");

		List<Analysis> analysisList = new ArrayList<>();

		if (!GenericValidator.isBlankOrNull(collectionDate)) {
			analysisList = getAnalysisForCollectionDate(collectionDate);
			if (analysisList.isEmpty()) {
				return new ArrayList<>();
			}
		}

		if (!GenericValidator.isBlankOrNull(receivedDate)) {
			analysisList = blendLists(analysisList, getAnalysisForRecievedDate(receivedDate));
			if (analysisList.isEmpty()) {
				return new ArrayList<>();
			}
		}

		if (!(GenericValidator.isBlankOrNull(analysisStatus) || analysisStatus.equals("0"))) {
			analysisList = blendLists(analysisList, getAnalysisForAnalysisStatus(analysisStatus));
			if (analysisList.isEmpty()) {
				return new ArrayList<>();
			}
		}

		if (!(GenericValidator.isBlankOrNull(sampleStatus) || sampleStatus.equals("0"))) {
			analysisList = blendLists(analysisList, getAnalysisForSampleStatus(sampleStatus));
			if (analysisList.isEmpty()) {
				return new ArrayList<>();
			}
		}

		if (!(GenericValidator.isBlankOrNull(test) || test.equals("0"))) {
			analysisList = blendLists(analysisList, getAnalysisForTest(test));
			if (analysisList.isEmpty()) {
				return new ArrayList<>();
			}
		}

		return buildTestItems(analysisList);
	}

	private List<Analysis> blendLists(List<Analysis> masterList, List<Analysis> newList) {
		if (masterList.isEmpty()) {
			return newList;
		} else {
			List<Analysis> blendedList = new ArrayList<>();
			for (Analysis master : masterList) {
				for (Analysis newAnalysis : newList) {
					if (master.getId().equals(newAnalysis.getId())) {
						blendedList.add(master);
					}
				}
			}
			return blendedList;

		}
	}

	private List<Analysis> getAnalysisForCollectionDate(String collectionDate) {
		Date date = DateUtil.convertStringDateToSqlDate(collectionDate);
		return analysisDAO.getAnalysisCollectedOnExcludedByStatusId(date, excludedStatusIds);
	}

	private List<Analysis> getAnalysisForRecievedDate(String recievedDate) {
		List<Sample> sampleList = sampleDAO.getSamplesReceivedOn(recievedDate);

		return getAnalysisListForSampleItems(sampleList);
	}

	private List<Analysis> getAnalysisListForSampleItems(List<Sample> sampleList) {
		List<Analysis> analysisList = new ArrayList<>();
		SampleItemDAO sampleItemDAO = new SampleItemDAOImpl();

		for (Sample sample : sampleList) {
			List<SampleItem> sampleItemList = sampleItemDAO.getSampleItemsBySampleId(sample.getId());

			for (SampleItem sampleItem : sampleItemList) {
				List<Analysis> analysisListForItem = analysisDAO
						.getAnalysesBySampleItemsExcludingByStatusIds(sampleItem, excludedStatusIds);

				analysisList.addAll(analysisListForItem);
			}
		}

		return analysisList;
	}

	private List<Analysis> getAnalysisForAnalysisStatus(String status) {
		return analysisDAO.getAnalysesForStatusId(status);
	}

	private List<Analysis> getAnalysisForSampleStatus(String sampleStatus) {
		return analysisDAO.getAnalysesBySampleStatusIdExcludingByStatusId(sampleStatus, excludedStatusIds);
	}

	@SuppressWarnings("unchecked")
	private List<Analysis> getAnalysisForTest(String testId) {
		List<Integer> excludedStatusIntList = new ArrayList<>();
		excludedStatusIntList.addAll(excludedStatusIds);
		return analysisDAO.getAllAnalysisByTestAndExcludedStatus(testId, excludedStatusIntList);
	}

	private List<TestResultItem> buildTestItems(List<Analysis> analysisList) {
		if (analysisList.isEmpty()) {
			return new ArrayList<>();
		}

		return resultsUtility.getGroupedTestsForAnalysisList(analysisList, REVERSE_SORT_ORDER);
	}

	private List<DropPair> getAnalysisStatusTypes() {

		List<DropPair> list = new ArrayList<>();
		list.add(new DropPair("0", ""));

		list.add(new DropPair(StatusService.getInstance().getStatusID(AnalysisStatus.NotStarted),
				StatusService.getInstance().getStatusName(AnalysisStatus.NotStarted)));
		list.add(new DropPair(StatusService.getInstance().getStatusID(AnalysisStatus.Canceled),
				StatusService.getInstance().getStatusName(AnalysisStatus.Canceled)));
		list.add(new DropPair(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance),
				StatusService.getInstance().getStatusName(AnalysisStatus.TechnicalAcceptance)));
		list.add(new DropPair(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalRejected),
				StatusService.getInstance().getStatusName(AnalysisStatus.TechnicalRejected)));
		list.add(new DropPair(StatusService.getInstance().getStatusID(AnalysisStatus.BiologistRejected),
				StatusService.getInstance().getStatusName(AnalysisStatus.BiologistRejected)));

		return list;
	}

	private List<DropPair> getSampleStatusTypes() {

		List<DropPair> list = new ArrayList<>();
		list.add(new DropPair("0", ""));

		list.add(new DropPair(StatusService.getInstance().getStatusID(OrderStatus.Entered),
				StatusService.getInstance().getStatusName(OrderStatus.Entered)));
		list.add(new DropPair(StatusService.getInstance().getStatusID(OrderStatus.Started),
				StatusService.getInstance().getStatusName(OrderStatus.Started)));

		return list;
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if (FWD_SUCCESS.equals(forward)) {
			return new ModelAndView("statusResultDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
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

	public class DropPair implements Serializable {

		private static final long serialVersionUID = 1L;

		public String getId() {
			return id;
		}

		public String getDescription() {
			return description;
		}

		private final String id;
		private final String description;

		public DropPair(String id, String description) {
			this.id = id;
			this.description = description;
		}
	}
}
