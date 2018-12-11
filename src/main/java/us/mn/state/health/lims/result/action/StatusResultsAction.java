/**
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 *
 * The Original Code is OpenELIS code.
 *
 * Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
 *
 * Contributor(s): CIRG, University of Washington, Seattle WA.
 */
package us.mn.state.health.lims.result.action;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.action.BaseAction;
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

public class StatusResultsAction extends BaseAction implements Serializable {

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
		excludedStatusIds = new HashSet<Integer>();
		excludedStatusIds.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.Canceled)));
	}

	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		resultsUtility = new ResultsLoadUtility(currentUserId);
		String forward = FWD_SUCCESS;

		request.getSession().setAttribute(SAVE_DISABLED, TRUE);

		String newRequest = request.getParameter("blank");

		DynaActionForm dynaForm = (DynaActionForm) form;
		PropertyUtils.setProperty(dynaForm, "referralReasons", DisplayListService.getList( DisplayListService.ListType.REFERRAL_REASONS));
        PropertyUtils.setProperty( dynaForm, "rejectReasons", DisplayListService.getNumberedListWithLeadingBlank( DisplayListService.ListType.REJECTION_REASONS ) );

		ResultsPaging paging = new ResultsPaging();

		String newPage = request.getParameter("page");
		if (GenericValidator.isBlankOrNull(newPage)) {
			List<TestResultItem> tests;
			if (GenericValidator.isBlankOrNull(newRequest) || newRequest.equals("false")) {
				tests = setSearchResults(dynaForm);

				if (configProperties.isPropertyValueEqual(Property.PATIENT_DATA_ON_RESULTS_BY_ROLE, "true")
						&& !userHasPermissionForModule(request, "PatientResults")) {
					for (TestResultItem resultItem : tests) {
						resultItem.setPatientInfo("---");
					}
				}

				paging.setDatabaseResults(request, dynaForm, tests);
			} else {
				setEmptyResults(dynaForm);
			}

			setSelectionLists(dynaForm);
		} else {
			paging.page(request, dynaForm, newPage);
		}

		return mapping.findForward(forward);
	}

	private List<TestResultItem> setSearchResults(DynaActionForm dynaForm) throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		List<TestResultItem> tests = getSelectedTests(dynaForm);
		PropertyUtils.setProperty(dynaForm, "searchFinished", Boolean.TRUE);

		if (resultsUtility.inventoryNeeded()) {
			addInventory(dynaForm);
			PropertyUtils.setProperty(dynaForm, "displayTestKit", true);
		} else {
			addEmptyInventoryList(dynaForm);
			PropertyUtils.setProperty(dynaForm, "displayTestKit", false);
		}

		return tests;
	}

	private void setEmptyResults(DynaActionForm dynaForm) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		PropertyUtils.setProperty(dynaForm, "testResult", new ArrayList<TestResultItem>());
		PropertyUtils.setProperty(dynaForm, "displayTestKit", false);
		PropertyUtils.setProperty(dynaForm, "collectionDate", "");
		PropertyUtils.setProperty(dynaForm, "recievedDate", "");
		PropertyUtils.setProperty(dynaForm, "selectedAnalysisStatus", "");
		PropertyUtils.setProperty(dynaForm, "selectedTest", "");
		PropertyUtils.setProperty(dynaForm, "searchFinished", Boolean.FALSE);
	}

	private void addInventory(DynaActionForm dynaForm) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		List<InventoryKitItem> list = inventoryUtility.getExistingActiveInventory();
		PropertyUtils.setProperty(dynaForm, "inventoryItems", list);
	}

	private void addEmptyInventoryList(DynaActionForm dynaForm) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		PropertyUtils.setProperty(dynaForm, "inventoryItems", new ArrayList<InventoryKitItem>());
	}

	private void setSelectionLists(DynaActionForm dynaForm) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		List<DropPair> analysisStatusList = getAnalysisStatusTypes();

		PropertyUtils.setProperty(dynaForm, "analysisStatusSelections", analysisStatusList);
		PropertyUtils.setProperty(dynaForm, "testSelections", DisplayListService.getListWithLeadingBlank( DisplayListService.ListType.ALL_TESTS ));

		List<DropPair> sampleStatusList = getSampleStatusTypes();
		PropertyUtils.setProperty(dynaForm, "sampleStatusSelections", sampleStatusList);

	}

	private List<TestResultItem> getSelectedTests(DynaActionForm dynaForm) {
		String collectionDate = dynaForm.getString("collectionDate");
		String receivedDate = dynaForm.getString("recievedDate");
		String analysisStatus = dynaForm.getString("selectedAnalysisStatus");
		String sampleStatus = dynaForm.getString("selectedSampleStatus");
		String test = dynaForm.getString("selectedTest");

		List<Analysis> analysisList = new ArrayList<Analysis>();

		if (!GenericValidator.isBlankOrNull(collectionDate)) {
			analysisList = getAnalysisForCollectionDate(collectionDate);
			if( analysisList.isEmpty()){
				return new ArrayList<TestResultItem>();
			}
		}

		if (!GenericValidator.isBlankOrNull(receivedDate)) {
			analysisList = blendLists(analysisList, getAnalysisForRecievedDate(receivedDate));
			if (analysisList.isEmpty()) {
				return new ArrayList<TestResultItem>();
			}
		}

		if (!(GenericValidator.isBlankOrNull(analysisStatus) || analysisStatus.equals("0"))) {
			analysisList = blendLists(analysisList, getAnalysisForAnalysisStatus(analysisStatus));
			if( analysisList.isEmpty()){
				return new ArrayList<TestResultItem>();
			}
		}

		if (!(GenericValidator.isBlankOrNull(sampleStatus) || sampleStatus.equals("0"))) {
			analysisList = blendLists(analysisList, getAnalysisForSampleStatus(sampleStatus));
			if( analysisList.isEmpty()){
				return new ArrayList<TestResultItem>();
			}
		}

		if (!(GenericValidator.isBlankOrNull(test) || test.equals("0"))) {
			analysisList = blendLists(analysisList, getAnalysisForTest(test));
			if( analysisList.isEmpty()){
				return new ArrayList<TestResultItem>();
			}
		}

		return buildTestItems(analysisList);
	}

	private List<Analysis> blendLists(List<Analysis> masterList, List<Analysis> newList){
		if( masterList.isEmpty() ){
			return newList;
		}else{
			List<Analysis> blendedList = new ArrayList<Analysis>();
			for(Analysis master : masterList){
				for( Analysis newAnalysis : newList){
					if( master.getId().equals(newAnalysis.getId())){
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
		List<Analysis> analysisList = new ArrayList<Analysis>();
		SampleItemDAO sampleItemDAO = new SampleItemDAOImpl();

		for (Sample sample : sampleList) {
			List<SampleItem> sampleItemList = sampleItemDAO.getSampleItemsBySampleId(sample.getId());

			for (SampleItem sampleItem : sampleItemList) {
				List<Analysis> analysisListForItem = analysisDAO.getAnalysesBySampleItemsExcludingByStatusIds(sampleItem, excludedStatusIds);

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
		List<Integer> excludedStatusIntList = new ArrayList<Integer>();
		excludedStatusIntList.addAll(excludedStatusIds);
		return analysisDAO.getAllAnalysisByTestAndExcludedStatus(testId, excludedStatusIntList);
	}

	private List<TestResultItem> buildTestItems(List<Analysis> analysisList) {
		if( analysisList.isEmpty()){
			return new ArrayList<TestResultItem>();
		}

		return resultsUtility.getGroupedTestsForAnalysisList(analysisList, REVERSE_SORT_ORDER);
	}

	private List<DropPair> getAnalysisStatusTypes() {

		List<DropPair> list = new ArrayList<DropPair>();
		list.add(new DropPair("0", ""));

		list.add(new DropPair(StatusService.getInstance().getStatusID(AnalysisStatus.NotStarted), StatusService.getInstance().getStatusName(AnalysisStatus.NotStarted)));
		list.add(new DropPair(StatusService.getInstance().getStatusID(AnalysisStatus.Canceled), StatusService.getInstance().getStatusName(AnalysisStatus.Canceled)));
		list.add(new DropPair(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance), StatusService.getInstance()
				.getStatusName(AnalysisStatus.TechnicalAcceptance)));
		list.add(new DropPair(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalRejected), StatusService.getInstance()
				.getStatusName(AnalysisStatus.TechnicalRejected)));
		list.add(new DropPair(StatusService.getInstance().getStatusID(AnalysisStatus.BiologistRejected), StatusService.getInstance()
				.getStatusName(AnalysisStatus.BiologistRejected)));

		return list;
	}

	private List<DropPair> getSampleStatusTypes() {

		List<DropPair> list = new ArrayList<DropPair>();
		list.add(new DropPair("0", ""));

		list.add(new DropPair(StatusService.getInstance().getStatusID(OrderStatus.Entered), StatusService.getInstance().getStatusName(OrderStatus.Entered)));
		list.add(new DropPair(StatusService.getInstance().getStatusID(OrderStatus.Started), StatusService.getInstance().getStatusName(OrderStatus.Started)));

		return list;
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
