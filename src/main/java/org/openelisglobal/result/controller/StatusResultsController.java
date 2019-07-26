package org.openelisglobal.result.controller;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.result.form.StatusResultsForm;
import org.openelisglobal.analysis.service.AnalysisService;
import org.openelisglobal.sample.service.SampleService;
import org.openelisglobal.sampleitem.service.SampleItemService;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.StatusService;
import org.openelisglobal.common.services.StatusService.AnalysisStatus;
import org.openelisglobal.common.services.StatusService.OrderStatus;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.inventory.action.InventoryUtility;
import org.openelisglobal.inventory.form.InventoryKitItem;
import org.openelisglobal.result.action.util.ResultsLoadUtility;
import org.openelisglobal.result.action.util.ResultsPaging;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.sampleitem.valueholder.SampleItem;
import org.openelisglobal.test.beanItems.TestResultItem;

@Controller
public class StatusResultsController extends BaseController {

	private static final boolean REVERSE_SORT_ORDER = false;
	@Autowired
	private AnalysisService analysisService;
	@Autowired
	private SampleService sampleService;
	@Autowired
	SampleItemService sampleItemService;
	private ResultsLoadUtility resultsUtility;
	private final InventoryUtility inventoryUtility = SpringContext.getBean(InventoryUtility.class);
	private static final ConfigurationProperties configProperties = ConfigurationProperties.getInstance();

	private static Set<Integer> excludedStatusIds;

	static {
		// currently this is the only one being excluded for Haiti_LNSP. If it
		// gets more complicate use the status sets
		excludedStatusIds = new HashSet<>();
		excludedStatusIds.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.Canceled)));
	}

	@RequestMapping(value = "/StatusResults", method = RequestMethod.GET)
	public ModelAndView showStatusResults(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		StatusResultsForm form = new StatusResultsForm();

		resultsUtility = SpringContext.getBean(ResultsLoadUtility.class);
		resultsUtility.setSysUser(getSysUserId(request));

		request.getSession().setAttribute(SAVE_DISABLED, TRUE);

		String newRequest = request.getParameter("blank");

		PropertyUtils.setProperty(form, "referralReasons",
				DisplayListService.getInstance().getList(DisplayListService.ListType.REFERRAL_REASONS));
		PropertyUtils.setProperty(form, "rejectReasons", DisplayListService.getInstance()
				.getNumberedListWithLeadingBlank(DisplayListService.ListType.REJECTION_REASONS));

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
		addFlashMsgsToRequest(request);
		return findForward(FWD_SUCCESS, form);
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
				DisplayListService.getInstance().getListWithLeadingBlank(DisplayListService.ListType.ALL_TESTS));

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
		return analysisService.getAnalysisCollectedOnExcludedByStatusId(date, excludedStatusIds);
	}

	private List<Analysis> getAnalysisForRecievedDate(String recievedDate) {
		List<Sample> sampleList = sampleService.getSamplesReceivedOn(recievedDate);

		return getAnalysisListForSampleItems(sampleList);
	}

	private List<Analysis> getAnalysisListForSampleItems(List<Sample> sampleList) {
		List<Analysis> analysisList = new ArrayList<>();

		for (Sample sample : sampleList) {
			List<SampleItem> sampleItemList = sampleItemService.getSampleItemsBySampleId(sample.getId());

			for (SampleItem sampleItem : sampleItemList) {
				List<Analysis> analysisListForItem = analysisService
						.getAnalysesBySampleItemsExcludingByStatusIds(sampleItem, excludedStatusIds);

				analysisList.addAll(analysisListForItem);
			}
		}

		return analysisList;
	}

	private List<Analysis> getAnalysisForAnalysisStatus(String status) {
		return analysisService.getAnalysesForStatusId(status);
	}

	private List<Analysis> getAnalysisForSampleStatus(String sampleStatus) {
		return analysisService.getAnalysesBySampleStatusIdExcludingByStatusId(sampleStatus, excludedStatusIds);
	}

	@SuppressWarnings("unchecked")
	private List<Analysis> getAnalysisForTest(String testId) {
		List<Integer> excludedStatusIntList = new ArrayList<>();
		excludedStatusIntList.addAll(excludedStatusIds);
		return analysisService.getAllAnalysisByTestAndExcludedStatus(testId, excludedStatusIntList);
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
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "statusResultDefinition";
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
