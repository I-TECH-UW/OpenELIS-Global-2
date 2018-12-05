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
package us.mn.state.health.lims.analyzerresults.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Transaction;

import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.analyzerresults.action.beanitems.AnalyzerResultItem;
import us.mn.state.health.lims.analyzerresults.dao.AnalyzerResultsDAO;
import us.mn.state.health.lims.analyzerresults.daoimpl.AnalyzerResultsDAOImpl;
import us.mn.state.health.lims.analyzerresults.valueholder.AnalyzerResults;
import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.services.NoteService;
import us.mn.state.health.lims.common.services.ResultLimitService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.StatusService.OrderStatus;
import us.mn.state.health.lims.common.services.StatusService.RecordStatus;
import us.mn.state.health.lims.common.services.StatusService.SampleStatus;
import us.mn.state.health.lims.common.services.StatusSet;
import us.mn.state.health.lims.common.services.TypeOfSampleService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.note.dao.NoteDAO;
import us.mn.state.health.lims.note.daoimpl.NoteDAOImpl;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.patient.util.PatientUtil;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.result.action.util.ResultUtil;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;
import us.mn.state.health.lims.samplehuman.valueholder.SampleHuman;
import us.mn.state.health.lims.sampleitem.dao.SampleItemDAO;
import us.mn.state.health.lims.sampleitem.daoimpl.SampleItemDAOImpl;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;
import us.mn.state.health.lims.testreflex.action.util.TestReflexBean;
import us.mn.state.health.lims.testreflex.action.util.TestReflexUtil;
import us.mn.state.health.lims.testresult.dao.TestResultDAO;
import us.mn.state.health.lims.testresult.daoimpl.TestResultDAOImpl;
import us.mn.state.health.lims.testresult.valueholder.TestResult;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleTestDAO;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleTestDAOImpl;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSampleTest;

public class AnalyzerResultsSaveAction extends BaseAction {

	private static final boolean IS_RETROCI = ConfigurationProperties.getInstance().isPropertyValueEqual(ConfigurationProperties.Property.configurationName, "CI_GENERAL");
	private static final String REJECT_VALUE = "XXXX";
	private ResultDAO resultDAO = new ResultDAOImpl();
	private NoteDAO noteDAO = new NoteDAOImpl();
	private AnalysisDAO analysisDAO = new AnalysisDAOImpl();
	private SampleDAO sampleDAO = new SampleDAOImpl();
	private SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
	private SampleItemDAO sampleItemDAO = new SampleItemDAOImpl();
	private TestResultDAO testResultDAO = new TestResultDAOImpl();
	private TestDAO testDAO = new TestDAOImpl();
	private TypeOfSampleTestDAO typeOfSampleTestDAO = new TypeOfSampleTestDAOImpl();
	private TypeOfSampleDAO typeOfSampleDAO = new TypeOfSampleDAOImpl();

	private String sysUserId = null;
	private List<SampleGrouping> sampleGroupList;

	private static final String RESULT_SUBJECT = "Analyzer Result Note";
	private static final String DBS_SAMPLE_TYPE_ID;

	static {
		if(IS_RETROCI) {
			TypeOfSample typeOfSample = new TypeOfSample();
			typeOfSample.setDescription("DBS");
			typeOfSample.setDomain("H");
			typeOfSample = new TypeOfSampleDAOImpl().getTypeOfSampleByDescriptionAndDomain(typeOfSample, false);
			DBS_SAMPLE_TYPE_ID = typeOfSample.getId();
		}else{
			DBS_SAMPLE_TYPE_ID = null;
		}
	}

	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String forward = FWD_SUCCESS;

		BaseActionForm dynaForm = (BaseActionForm) form;

		AnalyzerResultsPaging paging = new AnalyzerResultsPaging();
		paging.updatePagedResults(request, dynaForm);
		List<AnalyzerResultItem> resultItemList = paging.getResults(request);

		List<AnalyzerResultItem> actionableResults = extractActionableResult(resultItemList);

		if (actionableResults.isEmpty()) {
			return mapping.findForward(forward);
		}

		ActionMessages errors = validateSavableItems(actionableResults);

		if (errors.size() > 0) {
			saveErrors(request, errors);
			request.setAttribute(Globals.ERROR_KEY, errors);

			return mapping.findForward(FWD_VALIDATION_ERROR);
		}

		sampleGroupList = new ArrayList<SampleGrouping>();
		sysUserId = getSysUserId(request);

		resultItemList.removeAll(actionableResults);
		List<AnalyzerResultItem> childlessControls = extractChildlessControls(resultItemList);
		List<AnalyzerResults> deletableAnalyzerResults = getRemovableAnalyzerResults(actionableResults, childlessControls);

		createResultsFromItems(actionableResults);

		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			removeHandledResultsFromAnalyzerResults(deletableAnalyzerResults);

			boolean saveSuccess = insertResults(request);

			if (saveSuccess) {
				tx.commit();
			} else {
				tx.rollback();
				return mapping.findForward(FWD_VALIDATION_ERROR);
			}
		} catch (LIMSRuntimeException lre) {
			tx.rollback();
			errors = new ActionErrors();
			ActionError accessionError = new ActionError("errors.UpdateException");
			errors.add(ActionErrors.GLOBAL_MESSAGE, accessionError);
			saveErrors(request, errors);
			request.setAttribute(Globals.ERROR_KEY, errors);

			return mapping.findForward(FWD_VALIDATION_ERROR);
		}

		setSuccessFlag(request, forward);

		if (GenericValidator.isBlankOrNull(dynaForm.getString("analyzerType"))) {
			return mapping.findForward(forward);
		} else {
			Map<String, String> params = new HashMap<String, String>();
			params.put("type", dynaForm.getString("analyzerType"));
			params.put("forward", forward);
			return getForwardWithParameters(mapping.findForward(forward), params);
		}

	}

	private ActionMessages validateSavableItems(List<AnalyzerResultItem> savableResults) {
		ActionErrors errors = new ActionErrors();

		for (AnalyzerResultItem item : savableResults) {
			if (item.getIsAccepted() && item.isUserChoicePending()) {
				StringBuilder augmentedAccession = new StringBuilder(item.getAccessionNumber());
				augmentedAccession.append(" : ");
				augmentedAccession.append(item.getTestName());
				augmentedAccession.append(" - ");
				augmentedAccession.append(StringUtil.getMessageForKey("error.reflexStep.notChosen"));
				ActionError accessionError = new ActionError("errors.followingAccession", augmentedAccession);
				errors.add(ActionErrors.GLOBAL_MESSAGE, accessionError);
			}
		}

		return errors;
	}

	private boolean insertResults(HttpServletRequest request) {
		for (SampleGrouping grouping : sampleGroupList) {
			if (grouping.addSample) {
				try {
					sampleDAO.insertDataWithAccessionNumber(grouping.sample);
				} catch (LIMSRuntimeException lre) {

					ActionMessages errors = new ActionMessages();
					ActionError error = new ActionError("warning.duplicate.accession", grouping.sample.getAccessionNumber(), null);

					errors.add(ActionMessages.GLOBAL_MESSAGE, error);
					saveErrors(request, errors);
					request.setAttribute(Globals.ERROR_KEY, errors);
					return false;
				}
			} else if (grouping.updateSample) {
				sampleDAO.updateData(grouping.sample);
			}

			String sampleId = grouping.sample.getId();

			if (grouping.addSample) {
				grouping.sampleHuman.setSampleId(sampleId);
				sampleHumanDAO.insertData(grouping.sampleHuman);

				RecordStatus patientStatus = grouping.statusSet.getPatientRecordStatus() == null ? RecordStatus.NotRegistered : null;
				RecordStatus sampleStatus = grouping.statusSet.getSampleRecordStatus() == null ? RecordStatus.NotRegistered : null;
				StatusService.getInstance().persistRecordStatusForSample(grouping.sample, sampleStatus, grouping.patient, patientStatus, sysUserId);
			}

			if (grouping.addSampleItem) {
				grouping.sampleItem.setSample(grouping.sample);
				sampleItemDAO.insertData(grouping.sampleItem);
			}

			for (int i = 0; i < grouping.analysisList.size(); i++) {

				Analysis analysis = grouping.analysisList.get(i);
				if (GenericValidator.isBlankOrNull(analysis.getId())) {
					analysis.setSampleItem(grouping.sampleItem);
					analysisDAO.insertData(analysis, false);
				} else {
					analysisDAO.updateData(analysis);
				}

				Result result = grouping.resultList.get(i);
				if (GenericValidator.isBlankOrNull(result.getId())) {
					result.setAnalysis(analysis);
					setAnalyte(result);
					resultDAO.insertData(result);
				} else {
					resultDAO.updateData(result);
				}

				Note note = grouping.noteList.get(i);

				if (note != null) {
					note.setReferenceId(result.getId());
					noteDAO.insertData(note);
				}
			}
		}

	
		TestReflexUtil testReflexUtil = new TestReflexUtil();
		testReflexUtil.setCurrentUserId(currentUserId);
		testReflexUtil.addNewTestsToDBForReflexTests(convertGroupListToTestReflexBeans(sampleGroupList));

		return true;
	}

	private List<TestReflexBean> convertGroupListToTestReflexBeans(List<SampleGrouping> sampleGroupList) {
		List<TestReflexBean> reflexBeanList = new ArrayList<TestReflexBean>();

		for (SampleGrouping sampleGroup : sampleGroupList) {
			if (sampleGroup.accepted) {
				for (Result result : sampleGroup.resultList) {
					TestReflexBean reflex = new TestReflexBean();
					reflex.setPatient(sampleGroup.patient);
                    reflex.setTriggersToSelectedReflexesMap( sampleGroup.triggersToSelectedReflexesMap );
					reflex.setResult(result);
					reflex.setSample(sampleGroup.sample);
					reflexBeanList.add(reflex);
				}
			}
		}

		return reflexBeanList;
	}

	private void createResultsFromItems(List<AnalyzerResultItem> actionableResults) {
		int groupingNumber = -1;
		List<AnalyzerResultItem> groupedResultList = null;

		/*
		 * Basic idea is that analyzerResultItems are put into a
		 * groupedResultList if they have the same grouping number. When the
		 * grouping number changes then the list is converted to a
		 * sampleGrouping. Note that the first time through the
		 * groupedResultList is empty so the sampleGrouping is null
		 */
		for (AnalyzerResultItem analyzerResultItem : actionableResults) {
			if (analyzerResultItem.getIsDeleted()) {
				continue;
			}

			if (analyzerResultItem.getSampleGroupingNumber() != groupingNumber) {
				groupingNumber = analyzerResultItem.getSampleGroupingNumber();

				SampleGrouping sampleGrouping = createRecordsForNewResult(groupedResultList);

				if (sampleGrouping != null) {
					sampleGrouping.triggersToSelectedReflexesMap = new HashMap<String, List<String>>( );
					sampleGroupList.add(sampleGrouping);
				}

				groupedResultList = new ArrayList<AnalyzerResultItem>();
			}

			if (!analyzerResultItem.isReadOnly()) {
				groupedResultList.add(analyzerResultItem);
			}

		}

		// for the last set of results the grouping number will not change
		SampleGrouping sampleGrouping = createRecordsForNewResult(groupedResultList);
        //TODO currently there are no user selections of reflexes on the analyzer result page so for now this is ok
		sampleGrouping.triggersToSelectedReflexesMap = new HashMap<String, List<String>>( );

		sampleGroupList.add(sampleGrouping);

	}

	private SampleGrouping createRecordsForNewResult(List<AnalyzerResultItem> groupedAnalyzerResultItems) {

		if (groupedAnalyzerResultItems != null && !groupedAnalyzerResultItems.isEmpty()) {
			String accessionNumber = groupedAnalyzerResultItems.get(0).getAccessionNumber();
			StatusSet statusSet = StatusService.getInstance().getStatusSetForAccessionNumber(accessionNumber);

			// If neither the test request or demographics has been entered then
			// both a skeleton set of entries should be made
			// If either one of them has been done the sketched entries have
			// been done and we only care if the
			// sample is a skeleton. Otherwise we just enter the results.
			// One corner cases includes the results from one analyzer have been
			// done and this is a different
			// analyzer, it may or may not be from the same sample
			if (noEntryDone(statusSet, accessionNumber)) {
				return createGroupForNoSampleEntryDone(groupedAnalyzerResultItems, statusSet);
			} else if (statusSet.getSampleRecordStatus() == RecordStatus.NotRegistered
					&& statusSet.getPatientRecordStatus() == RecordStatus.NotRegistered) {
				return createGroupForPreviousAnalyzerDone(groupedAnalyzerResultItems, statusSet);
			} else if (statusSet.getSampleRecordStatus() == RecordStatus.NotRegistered) {
				return createGroupForDemographicsEntered(groupedAnalyzerResultItems, statusSet);
			} else {
				return createGroupForSampleAndDemographicsEntered(groupedAnalyzerResultItems, statusSet); // this is called when just sample entry has been done/ fix
			}
		}

		return null;
	}

	private boolean noEntryDone(StatusSet statusSet, String accessionNumber) {
		boolean sampleOrPatientEntryDone = statusSet.getPatientRecordStatus() != null
				|| statusSet.getSampleRecordStatus() != null;

		if (sampleOrPatientEntryDone) {
			return false;
		}

		// This last case is that non-conformity may have been done
		return sampleDAO.getSampleByAccessionNumber(accessionNumber) == null;

	}

	/*
	 * Demographics and sample are stubbed out but we may need to add a new
	 * sample_item, if the sample type is different then the current one.
	 */
	private SampleGrouping createGroupForPreviousAnalyzerDone(List<AnalyzerResultItem> groupedAnalyzerResultItems, StatusSet statusSet) {
		SampleGrouping sampleGrouping = new SampleGrouping();
		Sample sample = sampleDAO.getSampleByAccessionNumber(groupedAnalyzerResultItems.get(0).getAccessionNumber());

		List<Analysis> analysisList = new ArrayList<Analysis>();
		List<Result> resultList = new ArrayList<Result>();
		Map<Result, String> resultToUserSelectionMap = new HashMap<Result, String>();
		List<Note> noteList = new ArrayList<Note>();

		// we're not setting the sample status because this doesn't change it.
		sample.setEnteredDate(new Date(new java.util.Date().getTime()));
		sample.setSysUserId(sysUserId);

		Patient patient = sampleHumanDAO.getPatientForSample(sample);
		createAndAddItems_Analysis_Results(groupedAnalyzerResultItems, analysisList, resultList, resultToUserSelectionMap, noteList, patient);

		// We either have to find an existing sample item or create a new one
		SampleItem sampleItem = getOrCreateSampleItem(groupedAnalyzerResultItems, sample);

		sampleGrouping.sample = sample;
		sampleGrouping.sampleItem = sampleItem;
		sampleGrouping.analysisList = analysisList;
		sampleGrouping.resultList = resultList;
		sampleGrouping.noteList = noteList;
		sampleGrouping.addSample = false;
		sampleGrouping.addSampleItem = sampleItem.getId() == null;
		sampleGrouping.statusSet = statusSet;
		sampleGrouping.accepted = groupedAnalyzerResultItems.get(0).getIsAccepted();
		sampleGrouping.patient = patient;
		sampleGrouping.resultToUserserSelectionMap = resultToUserSelectionMap;

		return sampleGrouping;
	}

	protected SampleItem getOrCreateSampleItem(List<AnalyzerResultItem> groupedAnalyzerResultItems, Sample sample) {
		List<Analysis> dBAnalysisList = analysisDAO.getAnalysesBySampleId(sample.getId());

		TypeOfSampleTest typeOfSampleForNewTest = typeOfSampleTestDAO
				.getTypeOfSampleTestForTest(groupedAnalyzerResultItems.get(0).getTestId());
		String typeOfSampleId = typeOfSampleForNewTest.getTypeOfSampleId();

		SampleItem sampleItem = null;
		int maxSampleItemSortOrder = 0;

		for (Analysis dbAnalysis : dBAnalysisList) {
			if (GenericValidator.isBlankOrNull(dbAnalysis.getSampleItem().getSortOrder())) {
				maxSampleItemSortOrder = Math.max(maxSampleItemSortOrder, Integer.parseInt(dbAnalysis.getSampleItem().getSortOrder()));
			}
			if (typeOfSampleId.equals(dbAnalysis.getSampleItem().getTypeOfSampleId())) {
				sampleItem = dbAnalysis.getSampleItem();
				break;
			}
		}

		boolean newSampleItem = sampleItem == null;

		if (newSampleItem) {
			sampleItem = new SampleItem();
			sampleItem.setSysUserId(sysUserId);
			sampleItem.setSortOrder(Integer.toString(maxSampleItemSortOrder + 1));
			sampleItem.setStatusId(StatusService.getInstance().getStatusID(SampleStatus.Entered));
			TypeOfSample typeOfSample = new TypeOfSample();
			typeOfSample.setId(typeOfSampleId);
			typeOfSampleDAO.getData(typeOfSample);
			sampleItem.setTypeOfSample(typeOfSample);
		}
		return sampleItem;
	}

	private SampleGrouping createGroupForDemographicsEntered(List<AnalyzerResultItem> groupedAnalyzerResultItems, StatusSet statusSet) {
		SampleGrouping sampleGrouping = new SampleGrouping();
		Sample sample = sampleDAO.getSampleByAccessionNumber(groupedAnalyzerResultItems.get(0).getAccessionNumber());

		// A previous sample item may exist if there was a previous import and
		// patient demographics was entered
		SampleItem sampleItem = getOrCreateSampleItem(groupedAnalyzerResultItems, sample);

		List<Analysis> analysisList = new ArrayList<Analysis>();
		List<Result> resultList = new ArrayList<Result>();
		Map<Result, String> resultToUserSelectionMap = new HashMap<Result, String>();
		List<Note> noteList = new ArrayList<Note>();

		if (StatusService.getInstance().getStatusID(OrderStatus.Entered).equals(sample.getStatusId())) {
			sample.setStatusId(StatusService.getInstance().getStatusID(OrderStatus.Started));
		}
		sample.setEnteredDate(new Date(new java.util.Date().getTime()));
		sample.setSysUserId(sysUserId);

		Patient patient = sampleHumanDAO.getPatientForSample(sample);
		createAndAddItems_Analysis_Results(groupedAnalyzerResultItems, analysisList, resultList, resultToUserSelectionMap, noteList, patient);

		sampleGrouping.sample = sample;
		sampleGrouping.sampleItem = sampleItem;
		sampleGrouping.analysisList = analysisList;
		sampleGrouping.resultList = resultList;
		sampleGrouping.noteList = noteList;
		sampleGrouping.addSample = false;
		sampleGrouping.updateSample = true;
		sampleGrouping.statusSet = statusSet;
		sampleGrouping.addSampleItem = sampleItem.getId() == null;
		sampleGrouping.accepted = groupedAnalyzerResultItems.get(0).getIsAccepted();
		sampleGrouping.patient = patient;
		sampleGrouping.resultToUserserSelectionMap = resultToUserSelectionMap;

		return sampleGrouping;
	}

	private SampleGrouping createGroupForSampleAndDemographicsEntered(
			List<AnalyzerResultItem> groupedAnalyzerResultItems, StatusSet statusSet) {
		SampleGrouping sampleGrouping = new SampleGrouping();
		Sample sample = sampleDAO.getSampleByAccessionNumber(groupedAnalyzerResultItems.get(0).getAccessionNumber());

		List<Analysis> analysisList = new ArrayList<Analysis>();
		List<Result> resultList = new ArrayList<Result>();
		Map<Result, String> resultToUserSelectionMap = new HashMap<Result, String>();
		List<Note> noteList = new ArrayList<Note>();

		if (StatusService.getInstance().getStatusID(OrderStatus.Entered).equals(sample.getStatusId())) {
			sample.setStatusId(StatusService.getInstance().getStatusID(OrderStatus.Started));
		}
		sample.setEnteredDate(new Date(new java.util.Date().getTime()));
		sample.setSysUserId(sysUserId);

		SampleItem sampleItem = null;
		/***** this is causing the status id for the sample in the DB to be updated *********/
		List<Analysis> dBAnalysisList = analysisDAO.getAnalysesBySampleId(sample.getId());
		Patient patient = sampleHumanDAO.getPatientForSample(sample);

		for (AnalyzerResultItem resultItem : groupedAnalyzerResultItems) {
			Analysis analysis = null;

			for (Analysis dbAnalysis : dBAnalysisList) {
				if (dbAnalysis.getTest().getId().equals(resultItem.getTestId())) {
					analysis = dbAnalysis;
					break;
				}
			}

			if (analysis == null) {
				// This is an analysis which is not in the ordered tests but
				// should be tracked anyway
				analysis = new Analysis();
				Test test = new Test();
				test.setId(resultItem.getTestId());
				test = testDAO.getTestById(test);
				analysis.setTest(test);
				// A new sampleItem may be needed
				TypeOfSample typeOfSample = TypeOfSampleService.getTypeOfSampleForTest(test.getId());
				List<SampleItem> sampleItemsForSample = sampleItemDAO.getSampleItemsBySampleId(sample.getId());

				// if the type of sample is found then assign to analysis
				// otherwise create it and assign
				for (SampleItem item : sampleItemsForSample) {
					if (item.getTypeOfSample().getId().equals(typeOfSample.getId())) {
						sampleItem = item;
						analysis.setSampleItem(sampleItem);
					}
				}
				if (sampleItem == null) {
					sampleItem = new SampleItem();
					sampleItem.setSysUserId(sysUserId);
					sampleItem.setSortOrder("1");
					sampleItem.setStatusId(StatusService.getInstance().getStatusID(SampleStatus.Entered));
					sampleItem.setCollectionDate(DateUtil.getNowAsTimestamp());
					sampleItem.setTypeOfSample(typeOfSample);
					analysis.setSampleItem(sampleItem);
				}
			} else {
				dBAnalysisList.remove(analysis);
			}
			// Since this is for a single analyzer we are assuming a single
			// sample and sample type so a single SampleItem
			if (sampleItem == null) {
				sampleItem = analysis.getSampleItem();
				sampleItem.setSysUserId(sysUserId);
			}

			populateAnalysis(resultItem, analysis, analysis.getTest());
			analysis.setSysUserId(sysUserId);
			analysisList.add(analysis);

			Result result = getResult(analysis, patient, resultItem);
			resultToUserSelectionMap.put(result, resultItem.getReflexSelectionId());

			resultList.add(result);

			if (GenericValidator.isBlankOrNull(resultItem.getNote())) {
				noteList.add(null);
			} else {
				Note note =  new NoteService( analysis ).createSavableNote( NoteService.NoteType.INTERNAL, resultItem.getNote(), RESULT_SUBJECT, currentUserId );
				noteList.add(note);
			}
		}

		sampleGrouping.sample = sample;
		sampleGrouping.sampleItem = sampleItem;
		sampleGrouping.analysisList = analysisList;
		sampleGrouping.resultList = resultList;
		sampleGrouping.noteList = noteList;
		sampleGrouping.addSample = false;
		sampleGrouping.updateSample = true;
		sampleGrouping.statusSet = statusSet;
		sampleGrouping.addSampleItem = sampleItem.getId() == null;
		sampleGrouping.accepted = groupedAnalyzerResultItems.get(0).getIsAccepted();
		sampleGrouping.patient = patient;
		sampleGrouping.resultToUserserSelectionMap = resultToUserSelectionMap;

		return sampleGrouping;
	}

	private SampleGrouping createGroupForNoSampleEntryDone(List<AnalyzerResultItem> groupedAnalyzerResultItems, StatusSet statusSet) {
		SampleGrouping sampleGrouping = new SampleGrouping();
		Sample sample = new Sample();
		SampleHuman sampleHuman = new SampleHuman();
		SampleItem sampleItem = new SampleItem();
		sampleItem.setSysUserId(sysUserId);
		sampleItem.setSortOrder("1");
		sampleItem.setStatusId(StatusService.getInstance().getStatusID(SampleStatus.Entered));

		List<Analysis> analysisList = new ArrayList<Analysis>();
		List<Result> resultList = new ArrayList<Result>();
		Map<Result, String> resultToUserSelectionMap = new HashMap<Result, String>();
		List<Note> noteList = new ArrayList<Note>();

		sample.setAccessionNumber(groupedAnalyzerResultItems.get(0).getAccessionNumber());
		sample.setDomain("H");
		sample.setStatusId(StatusService.getInstance().getStatusID(OrderStatus.Started));
		sample.setEnteredDate(new Date(new java.util.Date().getTime()));
		sample.setReceivedDate(new Date(new java.util.Date().getTime()));
		sample.setSysUserId(sysUserId);

		sampleHuman.setPatientId(PatientUtil.getUnknownPatient().getId());
		sampleHuman.setSysUserId(sysUserId);

		Patient patient = PatientUtil.getUnknownPatient();
		createAndAddItems_Analysis_Results(groupedAnalyzerResultItems, analysisList, resultList, resultToUserSelectionMap, noteList, patient);

		addSampleTypeToSampleItem(sampleItem, analysisList, sample.getAccessionNumber());

		sampleGrouping.sample = sample;
		sampleGrouping.sampleHuman = sampleHuman;
		sampleGrouping.sampleItem = sampleItem;
		sampleGrouping.patient = patient;
		sampleGrouping.analysisList = analysisList;
		sampleGrouping.resultList = resultList;
		sampleGrouping.noteList = noteList;
		sampleGrouping.addSample = true;
		sampleGrouping.addSampleItem = true;
		sampleGrouping.statusSet = statusSet;
		sampleGrouping.accepted = groupedAnalyzerResultItems.get(0).getIsAccepted();
		sampleGrouping.resultToUserserSelectionMap = resultToUserSelectionMap;

		return sampleGrouping;
	}

	private void addSampleTypeToSampleItem(SampleItem sampleItem, List<Analysis> analysisList, String accessionNumber) {
		if (analysisList.size() > 0) {
			String typeOfSampleId = getTypeOfSampleId(analysisList, accessionNumber);
			sampleItem.setTypeOfSample(typeOfSampleDAO.getTypeOfSampleById(typeOfSampleId));
		}
	}

	private String getTypeOfSampleId(List<Analysis> analysisList, String accessionNumber) {
		if (IS_RETROCI && accessionNumber.startsWith("LDBS")) {
			List<TypeOfSampleTest> typeOfSmapleTestList = typeOfSampleTestDAO.getTypeOfSampleTestsForTest(analysisList.get(0).getTest().getId());

			for (TypeOfSampleTest typeOfSampleTest : typeOfSmapleTestList) {
				if (DBS_SAMPLE_TYPE_ID.equals(typeOfSampleTest.getTypeOfSampleId())) {
					return DBS_SAMPLE_TYPE_ID;
				}
			}

		}
		
		return typeOfSampleTestDAO.getTypeOfSampleTestsForTest(analysisList.get(0).getTest().getId()).get(0).getTypeOfSampleId();

	}

	private void createAndAddItems_Analysis_Results(List<AnalyzerResultItem> groupedAnalyzerResultItems,
													List<Analysis> analysisList, List<Result> resultList, Map<Result, String> resultToUserSelectionMap,
													List<Note> noteList, Patient patient) {

		for (AnalyzerResultItem resultItem : groupedAnalyzerResultItems) {
			Analysis analysis = getExistingAnalysis(resultItem);

			if (analysis == null) {
				analysis = new Analysis();
				Test test = new Test();
				test.setId(resultItem.getTestId());
				test = testDAO.getTestById(test);
				populateAnalysis(resultItem, analysis, test);
			} else {
				String statusId = StatusService.getInstance().getStatusID(resultItem.getIsAccepted() ? AnalysisStatus.TechnicalAcceptance
								: AnalysisStatus.TechnicalRejected);
				analysis.setStatusId(statusId);
			}

			analysis.setSysUserId(sysUserId);
			analysisList.add(analysis);

			Result result = getResult(analysis, patient, resultItem);
			resultList.add(result);
			resultToUserSelectionMap.put(result, resultItem.getReflexSelectionId());
			if (GenericValidator.isBlankOrNull(resultItem.getNote())) {
				noteList.add(null);
			} else {
                Note note = new NoteService( analysis ).createSavableNote( NoteService.NoteType.INTERNAL, resultItem.getNote(), RESULT_SUBJECT, currentUserId );
				noteList.add(note);
			}
		}
	}

	private Analysis getExistingAnalysis(AnalyzerResultItem resultItem) {
		List<Analysis> analysisList = analysisDAO.getAnalysisByAccessionAndTestId(resultItem.getAccessionNumber(), resultItem.getTestId());

		return analysisList.isEmpty() ? null : analysisList.get(0);
	}

	private Result getResult(Analysis analysis, Patient patient, AnalyzerResultItem resultItem) {

		Result result = null;

		if (analysis.getId() != null) {
			List<Result> resultList = resultDAO.getResultsByAnalysis(analysis);

			if (!resultList.isEmpty()) {
				result = resultList.get(resultList.size() - 1);
				//this should be refactored -- it's very close to createNewResult
				String resultValue = resultItem.getIsRejected() ? REJECT_VALUE : resultItem.getResult();
				result.setValue(resultValue);
				result.setTestResult(getTestResultForResult(resultItem));
				result.setSysUserId(currentUserId);

				setAnalyte(result);
			}
		}

		if (result == null) {
			result = createNewResult(resultItem, patient);
		}

		return result;
	}

	private void setAnalyte(Result result) {
		TestAnalyte testAnalyte = ResultUtil.getTestAnalyteForResult(result);

		if (testAnalyte != null) {
			result.setAnalyte(testAnalyte.getAnalyte());
		}
	}

	private Result createNewResult(AnalyzerResultItem resultItem, Patient patient) {
		Result result = new Result();
		String resultValue = resultItem.getIsRejected() ? REJECT_VALUE : resultItem.getResult();
		result.setValue(resultValue);
		result.setTestResult(getTestResultForResult(resultItem));
		result.setResultType(resultItem.getTestResultType());				
		//the results table is not autmatically updated with the significant digits from TestResult so we must do this
		if (!GenericValidator.isBlankOrNull(resultItem.getSignificantDigits())) {
			result.setSignificantDigits(Integer.parseInt(resultItem.getSignificantDigits()));
		}
		
		addMinMaxNormal(result, resultItem, patient);
		result.setSysUserId(sysUserId);

		return result;
	}

	private void addMinMaxNormal(Result result, AnalyzerResultItem resultItem, Patient patient) {
		boolean limitsFound = false;

		if( resultItem != null){
			ResultLimit resultLimit = new ResultLimitService().getResultLimitForTestAndPatient(resultItem.getTestId(), patient);
			if(resultLimit != null) {
				result.setMinNormal(resultLimit.getLowNormal());
				result.setMaxNormal(resultLimit.getHighNormal());
				limitsFound = true;
			}
		}

		if( !limitsFound){
			result.setMinNormal( Double.NEGATIVE_INFINITY);
			result.setMaxNormal( Double.POSITIVE_INFINITY);
		}
	}

	private TestResult getTestResultForResult(AnalyzerResultItem resultItem) {
		if ("D".equals(resultItem.getTestResultType())) {
			TestResult testResult;
			testResult = testResultDAO.getTestResultsByTestAndDictonaryResult(resultItem.getTestId(), resultItem.getResult());
			return testResult;
		} else {
			List<TestResult> testResultList = testResultDAO.getActiveTestResultsByTest( resultItem.getTestId() );
			// we are assuming there is only one testResult for a numeric
			// type result
			if (!testResultList.isEmpty()) {
				return testResultList.get(0);
			}
		}

		return null;
	}

	private void populateAnalysis(AnalyzerResultItem resultItem, Analysis analysis, Test test) {
		if (!StatusService.getInstance().getStatusID(AnalysisStatus.Canceled).equals(analysis.getStatusId())) {
			String statusId = StatusService.getInstance().getStatusID(resultItem.getIsAccepted() ? AnalysisStatus.TechnicalAcceptance
							: AnalysisStatus.TechnicalRejected);
			analysis.setStatusId(statusId);
			analysis.setAnalysisType(resultItem.getManual() ? IActionConstants.ANALYSIS_TYPE_MANUAL : IActionConstants.ANALYSIS_TYPE_AUTO);
			analysis.setCompletedDateForDisplay(resultItem.getCompleteDate());
			analysis.setTest(test);
			analysis.setTestSection(test.getTestSection());
			analysis.setIsReportable(test.getIsReportable());
			analysis.setRevision("0");
		}

	}

	private void removeHandledResultsFromAnalyzerResults(List<AnalyzerResults> deletableAnalyzerResults) {

		AnalyzerResultsDAO analyzerResultsDAO = new AnalyzerResultsDAOImpl();
		analyzerResultsDAO.delete(deletableAnalyzerResults);

	}

	private List<AnalyzerResults> getRemovableAnalyzerResults(List<AnalyzerResultItem> actionableResults,
			List<AnalyzerResultItem> childlessControls) {

		Set<AnalyzerResults> deletableAnalyzerResults = new HashSet<AnalyzerResults>();

		for (AnalyzerResultItem resultItem : actionableResults) {
			AnalyzerResults result = new AnalyzerResults();
			result.setId(resultItem.getId());
			deletableAnalyzerResults.add(result);
		}

		for (AnalyzerResultItem resultItem : childlessControls) {
			AnalyzerResults result = new AnalyzerResults();
			result.setId(resultItem.getId());
			deletableAnalyzerResults.add(result);
		}

		List<AnalyzerResults> resultList = new ArrayList<AnalyzerResults>();
		resultList.addAll(deletableAnalyzerResults);
		return resultList;
	}

	private List<AnalyzerResultItem> extractActionableResult(List<AnalyzerResultItem> resultItemList) {
		List<AnalyzerResultItem> actionableResultList = new ArrayList<AnalyzerResultItem>();

		int currentSampleGrouping = 0;
		boolean acceptResult = false;
		boolean rejectResult = false;
		boolean deleteResult = false;
		String accessionNumber = null;

		for (AnalyzerResultItem resultItem : resultItemList) {

			if (currentSampleGrouping != resultItem.getSampleGroupingNumber()) {
				currentSampleGrouping = resultItem.getSampleGroupingNumber();
				acceptResult = resultItem.getIsAccepted();
				rejectResult = resultItem.getIsRejected();
				deleteResult = resultItem.getIsDeleted();
				// this clears the selection in case of failure
				// Note it also screwed up acception and rejection.  This is why we should follow the struts pattern
				// resultItem.setIsAccepted(false);
				// resultItem.setIsRejected(false);
				// resultItem.setIsDeleted(false);
				accessionNumber = resultItem.getAccessionNumber();
			} else {
				resultItem.setAccessionNumber(accessionNumber);
				resultItem.setIsAccepted(acceptResult);
				resultItem.setIsRejected(rejectResult);
				resultItem.setIsDeleted(deleteResult);
			}

			if (acceptResult || rejectResult || deleteResult) {
				actionableResultList.add(resultItem);
			}
		}

		return actionableResultList;
	}

	private List<AnalyzerResultItem> extractChildlessControls(List<AnalyzerResultItem> resultItemList) {
		/*
		 * A childless control is a control which is adjacent to another
		 * control. It is the first set of controls which will be removed. For
		 * that reason we're going through the list backwards.
		 */

		List<AnalyzerResultItem> childLessControlList = new ArrayList<AnalyzerResultItem>();
		int sampleGroupingNumber = 0;
		boolean lastGroupIsControl = false;
		boolean inControlGroup = true;// covers the bottom control has no
		// children

		for (int i = resultItemList.size() - 1; i >= 0; i--) {
			AnalyzerResultItem resultItem = resultItemList.get(i);

			if (sampleGroupingNumber != resultItem.getSampleGroupingNumber()) {
				lastGroupIsControl = inControlGroup;
				inControlGroup = resultItem.getIsControl();
				sampleGroupingNumber = resultItem.getSampleGroupingNumber();
			}

			if (lastGroupIsControl && resultItem.getIsControl()) {
				childLessControlList.add(resultItem);
			}
		}

		return childLessControlList;
	}

	protected String getPageTitleKey() {
		return "banner.menu.results.analyzer";
	}

	protected String getPageSubtitleKey() {
		return "banner.menu.results.analyzer";
	}

	public class SampleGrouping {
		public boolean accepted = true;
		public Sample sample;
		private SampleHuman sampleHuman;
		public Patient patient;
		private List<Note> noteList;
		private SampleItem sampleItem;
		private List<Analysis> analysisList;
		public List<Result> resultList;
        public Map<String,List<String>> triggersToSelectedReflexesMap;
		private StatusSet statusSet;
		private boolean addSample = false; // implies adding patient
		private boolean updateSample = false;
		private boolean addSampleItem = false;
		private Map<Result, String> resultToUserserSelectionMap;

	}

}
