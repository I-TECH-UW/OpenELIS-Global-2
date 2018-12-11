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
package us.mn.state.health.lims.result.action.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.analyte.dao.AnalyteDAO;
import us.mn.state.health.lims.analyte.daoimpl.AnalyteDAOImpl;
import us.mn.state.health.lims.analyte.valueholder.Analyte;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import us.mn.state.health.lims.common.services.AnalysisService;
import us.mn.state.health.lims.common.services.NoteService;
import us.mn.state.health.lims.common.services.NoteService.NoteType;
import us.mn.state.health.lims.common.services.PatientService;
import us.mn.state.health.lims.common.services.QAService;
import us.mn.state.health.lims.common.services.QAService.QAObservationType;
import us.mn.state.health.lims.common.services.ResultLimitService;
import us.mn.state.health.lims.common.services.ResultService;
import us.mn.state.health.lims.common.services.SampleService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.StatusService.OrderStatus;
import us.mn.state.health.lims.common.services.TestIdentityService;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.services.TypeOfSampleService;
import us.mn.state.health.lims.common.services.TypeOfTestResultService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DAOImplFactory;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.inventory.action.InventoryUtility;
import us.mn.state.health.lims.inventory.form.InventoryKitItem;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.daoimpl.ObservationHistoryDAOImpl;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.patient.util.PatientUtil;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;
import us.mn.state.health.lims.patientidentitytype.util.PatientIdentityTypeMap;
import us.mn.state.health.lims.referral.dao.ReferralDAO;
import us.mn.state.health.lims.referral.daoimpl.ReferralDAOImpl;
import us.mn.state.health.lims.referral.valueholder.Referral;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.dao.ResultInventoryDAO;
import us.mn.state.health.lims.result.dao.ResultSignatureDAO;
import us.mn.state.health.lims.result.daoimpl.ResultInventoryDAOImpl;
import us.mn.state.health.lims.result.daoimpl.ResultSignatureDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.result.valueholder.ResultInventory;
import us.mn.state.health.lims.result.valueholder.ResultSignature;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;
import us.mn.state.health.lims.sampleitem.dao.SampleItemDAO;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.sampleqaevent.dao.SampleQaEventDAO;
import us.mn.state.health.lims.sampleqaevent.daoimpl.SampleQaEventDAOImpl;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;
import us.mn.state.health.lims.statusofsample.util.StatusRules;
import us.mn.state.health.lims.systemuser.dao.SystemUserDAO;
import us.mn.state.health.lims.systemuser.daoimpl.SystemUserDAOImpl;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;
import us.mn.state.health.lims.test.beanItems.TestResultItem;
import us.mn.state.health.lims.test.beanItems.TestResultItem.ResultDisplayType;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
//import us.mn.state.health.lims.test.valueholder.TestSection;
import us.mn.state.health.lims.testreflex.action.util.TestReflexUtil;
import us.mn.state.health.lims.testreflex.valueholder.TestReflex;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

public class ResultsLoadUtility {

	private static final boolean SORT_FORWARD = true;

	public static final String TESTKIT = "TestKit";


	private static final String NO_PATIENT_NAME = " ";
	private static final String NO_PATIENT_INFO = " ";

	private List<Sample> samples;
	private String currentDate = "";
    private PatientService patientService;
	private Sample currSample;

	private Set<Integer> excludedAnalysisStatus = new HashSet<Integer>();
	private List<Integer> analysisStatusList = new ArrayList<Integer>();
	private List<Integer> sampleStatusList = new ArrayList<Integer>();

	private List<InventoryKitItem> activeKits;

	private final ResultDAO resultDAO = DAOImplFactory.getInstance().getResultDAOImpl();
	private final DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
	private final ResultSignatureDAO resultSignatureDAO = new ResultSignatureDAOImpl();
	private final ResultInventoryDAO resultInventoryDAO = new ResultInventoryDAOImpl();
	private final ObservationHistoryDAO observationHistoryDAO = new ObservationHistoryDAOImpl();
	private AnalysisDAO analysisDAO = new AnalysisDAOImpl();

	private final StatusRules statusRules = new StatusRules();

	private boolean inventoryNeeded = false;

	private static String ANALYTE_CONCLUSION_ID;
	private static String ANALYTE_CD4_CNT_CONCLUSION_ID;
    private static final String NUMERIC_RESULT_TYPE = "N";
	private static boolean depersonalize = FormFields.getInstance().useField(Field.DepersonalizedResults);
	private boolean useTechSignature =  ConfigurationProperties.getInstance().isPropertyValueEqual(Property.resultTechnicianName, "true");
	private static boolean supportReferrals = FormFields.getInstance().useField(Field.ResultsReferral);
	private static boolean useInitialSampleCondition = FormFields.getInstance().useField(Field.InitialSampleCondition);
	private boolean useCurrentUserAsTechDefault = ConfigurationProperties.getInstance().isPropertyValueEqual(Property.autoFillTechNameUser, "true"); 
	private String currentUserName = "";
	private ReferralDAO referralDAO = new ReferralDAOImpl();
	private int reflexGroup = 1;
	private boolean lockCurrentResults = false;

	static {

		AnalyteDAO analyteDAO = new AnalyteDAOImpl();
		Analyte analyte = new Analyte();
		analyte.setAnalyteName("Conclusion");
		analyte = analyteDAO.getAnalyteByName(analyte, false);
		ANALYTE_CONCLUSION_ID = analyte == null ? "" : analyte.getId();
        analyte = new Analyte();
		analyte.setAnalyteName("generated CD4 Count");
		analyte = analyteDAO.getAnalyteByName(analyte, false);
		ANALYTE_CD4_CNT_CONCLUSION_ID = analyte == null ? "" : analyte.getId();
	}

	public ResultsLoadUtility(String currentUserId) {
		if (useCurrentUserAsTechDefault) {
			SystemUserDAO systemUserDAO = new SystemUserDAOImpl();
			SystemUser systemUser = new SystemUser();
			systemUser.setId(currentUserId);
			systemUserDAO.getData(systemUser);

			if (systemUser.getId() != null) {
				currentUserName = systemUser.getFirstName() + " " + systemUser.getLastName();
			}
		}
	}

	public ResultsLoadUtility( ){}
	/*
	 * N.B. The patient info is used to determine the limits for the results,
	 * not for including patient information
	 */
	public List<TestResultItem> getGroupedTestsForSample(Sample sample, Patient patient) {

		reflexGroup = 1;
		activeKits = null;
		samples = new ArrayList<Sample>();

		if (sample != null) {
			samples.add(sample);
		}

        patientService = new PatientService( patient );

		return getGroupedTestsForSamples();
	}

	public List<TestResultItem> getGroupedTestsForPatient(Patient patient) {
		reflexGroup = 1;
		activeKits = null;
		inventoryNeeded = false;

        patientService = new PatientService( patient );

		SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
		samples = sampleHumanDAO.getSamplesForPatient(patient.getId());

		return getGroupedTestsForSamples( );
	}

	/*
	 * @deprecated -- unsafe to use outside of beans with firstName, lastName,
	 * dob, gender, st, nationalId
	 */
	@Deprecated
	public void addIdentifingPatientInfo(Patient patient, DynaActionForm dynaForm) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		if( patient == null){
			return;
		}

		PatientIdentityTypeMap identityMap = PatientIdentityTypeMap.getInstance();
		List<PatientIdentity> identityList = PatientUtil.getIdentityListForPatient(patient);

		if (!depersonalize) {
			PropertyUtils.setProperty(dynaForm, "firstName", patient.getPerson().getFirstName());
			PropertyUtils.setProperty(dynaForm, "lastName", patient.getPerson().getLastName());
			PropertyUtils.setProperty(dynaForm, "dob", patient.getBirthDateForDisplay());
			PropertyUtils.setProperty(dynaForm, "gender", patient.getGender());
		}

		PropertyUtils.setProperty(dynaForm, "st", identityMap.getIdentityValue(identityList, "ST"));
		PropertyUtils.setProperty(dynaForm, "nationalId", GenericValidator.isBlankOrNull(patient.getNationalId()) ? patient.getExternalId()
				: patient.getNationalId());
	}

	public List<TestResultItem> getUnfinishedTestResultItemsInTestSection(String testSectionId) {

		List<Analysis> analysisList = analysisDAO.getAllAnalysisByTestSectionAndStatus(testSectionId, analysisStatusList, sampleStatusList);

		return getGroupedTestsForAnalysisList(analysisList, SORT_FORWARD);
	}

	public List<TestResultItem> getGroupedTestsForAnalysisList(List<Analysis> filteredAnalysisList, boolean forwardSort)
			throws LIMSRuntimeException {

		activeKits = null;
		inventoryNeeded = false;
		reflexGroup = 1;

		List<TestResultItem> selectedTestList = new ArrayList<TestResultItem>();

		for (Analysis analysis : filteredAnalysisList) {
            patientService = new PatientService( new SampleService( analysis.getSampleItem().getSample() ).getPatient() );

			String patientName = "";
			String patientInfo;
			String nationalId = patientService.getNationalId();
			if (depersonalize) {
				patientInfo = GenericValidator.isBlankOrNull(nationalId) ? patientService.getExternalId() : nationalId;
			} else {
				patientName = patientService.getLastFirstName();
				patientInfo = nationalId + ", " + patientService.getGender() + ", " + patientService.getBirthdayForDisplay();
			}

			currSample = analysis.getSampleItem().getSample();
			List<TestResultItem> testResultItemList = getTestResultItemFromAnalysis(analysis, patientName, patientInfo, nationalId);

			for (TestResultItem selectionItem : testResultItemList) {
				selectedTestList.add(selectionItem);
			}
		}

		if (forwardSort) {
			sortByAccessionAndSequence(selectedTestList);
		} else {
			reverseSortByAccessionAndSequence(selectedTestList);
		}

		setSampleGroupingNumbers(selectedTestList);
		addUserSelectionReflexes(selectedTestList);

		return selectedTestList;
	}

	private void reverseSortByAccessionAndSequence(List<? extends ResultItem> selectedTest) {
		Collections.sort(selectedTest, new Comparator<ResultItem>() {
			public int compare(ResultItem a, ResultItem b) {
				int accessionSort = b.getSequenceAccessionNumber().compareTo(a.getSequenceAccessionNumber());

				if (accessionSort == 0) { //only the accession number sorting is reversed
					if (!GenericValidator.isBlankOrNull(a.getTestSortOrder()) && !GenericValidator.isBlankOrNull(b.getTestSortOrder())) {
						try {
							return Integer.parseInt(a.getTestSortOrder()) - Integer.parseInt(b.getTestSortOrder());
						} catch (NumberFormatException e) {
							return a.getTestName().compareTo(b.getTestName());
						}

					} else {
						return a.getTestName().compareTo(b.getTestName());
					}
				}

				return accessionSort;
			}
		});
	}

	public void sortByAccessionAndSequence(List<? extends ResultItem> selectedTest) {
		Collections.sort(selectedTest, new Comparator<ResultItem>() {
			public int compare(ResultItem a, ResultItem b) {
				int accessionSort = a.getSequenceAccessionNumber().compareTo(b.getSequenceAccessionNumber());

				if (accessionSort == 0) {
					if( !GenericValidator.isBlankOrNull(a.getTestSortOrder()) && !GenericValidator.isBlankOrNull(b.getTestSortOrder())){
						try {
							return Integer.parseInt(a.getTestSortOrder()) - Integer.parseInt(b.getTestSortOrder());
						} catch (NumberFormatException e) {
							return a.getTestName().compareTo(b.getTestName());
						}

					}else if( !GenericValidator.isBlankOrNull(a.getTestName()) && !GenericValidator.isBlankOrNull(b.getTestName())){
						return a.getTestName().compareTo(b.getTestName());
					}
				}

				return accessionSort;
			}
		});
	}

	public void setSampleGroupingNumbers(List<? extends ResultItem> selectedTests) {
		int groupingNumber = 1; // the header is always going to be 0

		String currentSequenceAccession = "";

		for (ResultItem item : selectedTests) {
			if (!currentSequenceAccession.equals(item.getSequenceAccessionNumber()) || item.getIsGroupSeparator()) {
				groupingNumber++;
				currentSequenceAccession = item.getSequenceAccessionNumber();
				item.setShowSampleDetails(true);
			} else {
				item.setShowSampleDetails(false);
			}

			item.setSampleGroupingNumber(groupingNumber);

		}
	}

	@SuppressWarnings("unchecked")
	public List<Test> getTestsInSection(String id) {

		TestDAO testDAO = new TestDAOImpl();
		return testDAO.getTestsByTestSection(id);
	}


	private List<TestResultItem> getTestResultItemFromAnalysis(Analysis analysis, String patientName, String patientInfo, String nationalId)
			throws LIMSRuntimeException {
		List<TestResultItem> testResultList = new ArrayList<TestResultItem>();

		SampleItem sampleItem = analysis.getSampleItem();
		List<Result> resultList = resultDAO.getResultsByAnalysis(analysis);

		ResultInventory testKit = null;

		String techSignature = "";
		String techSignatureId = "";

		if (resultList == null) {
			return testResultList;
		}

		// For historical reasons we add a null member to the collection if it
		// is empty
		// this should be refactored.
		// The result list are results associated with the analysis, if there is
		// none we want
		// to present the user with a blank one
		if (resultList.isEmpty()) {
			resultList.add(null);
		}

        boolean multiSelectionResult = false;
		for (Result result : resultList) {
			// If the parentResult has a value then this result was handled with
			// the parent
			if (result != null && result.getParentResult() != null) {
				continue;
			}

			if (result != null) {
				if (useTechSignature) {
					List<ResultSignature> signatures = resultSignatureDAO.getResultSignaturesByResults(resultList);

					for (ResultSignature signature : signatures) {
						//we no longer use supervisor signature but there may be some in db
						if (!signature.getIsSupervisor()) {
							techSignature = signature.getNonUserName();
							techSignatureId = signature.getId();
						}
					}
				}

				testKit = getInventoryForResult(result);

				multiSelectionResult = TypeOfTestResultService.ResultType.isMultiSelectVariant( result.getResultType());
			}

			String initialConditions = getInitialSampleConditionString(sampleItem);
            NoteType[] noteTypes = { NoteType.EXTERNAL, NoteType.INTERNAL, NoteType.REJECTION_REASON, NoteType.NON_CONFORMITY};
            String notes = new NoteService( analysis ).getNotesAsString( true, true, "<br/>", noteTypes, false );

			TestResultItem resultItem = createTestResultItem(new AnalysisService( analysis ), testKit, notes, sampleItem.getSortOrder(), result,
					sampleItem.getSample().getAccessionNumber(), patientName, patientInfo, techSignature, techSignatureId,
					initialConditions,  TypeOfSampleService.getTypeOfSampleNameForId(sampleItem.getTypeOfSampleId()));
			resultItem.setNationalId(nationalId);
			testResultList.add(resultItem);

			if (multiSelectionResult) {
				break;
			}
		}

		return testResultList;
	}

	private String getInitialSampleConditionString(SampleItem sampleItem) {
		if (useInitialSampleCondition) {
			List<ObservationHistory> observationList = observationHistoryDAO.getObservationHistoriesBySampleItemId(sampleItem.getId());
			StringBuilder conditions = new StringBuilder();

			for (ObservationHistory observation : observationList) {
				Dictionary dictionary = dictionaryDAO.getDictionaryById(observation.getValue());
				if (dictionary != null) {
					conditions.append(dictionary.getLocalizedName());
					conditions.append(", ");
				}
			}

			if (conditions.length() > 2) {
				return conditions.substring(0, conditions.length() - 2);
			}
		}

		return null;
	}

	private ResultInventory getInventoryForResult(Result result) throws LIMSRuntimeException {
		List<ResultInventory> inventoryList = resultInventoryDAO.getResultInventorysByResult(result);

		return inventoryList.size() > 0 ? inventoryList.get(0) : null;
	}

	private List<TestResultItem> getGroupedTestsForSamples() {

		List<TestResultItem> testList = new ArrayList<TestResultItem>();

		TestResultItem[] tests = getSortedTestsFromSamples();

		String currentAccessionNumber = "";

		for ( TestResultItem testItem : tests) {
			if (!currentAccessionNumber.equals(testItem.getAccessionNumber())) {

				TestResultItem separatorItem = new TestResultItem();
				separatorItem.setIsGroupSeparator( true );
				separatorItem.setAccessionNumber( testItem.getAccessionNumber() );
				separatorItem.setReceivedDate( testItem.getReceivedDate() );
				testList.add(separatorItem);

				currentAccessionNumber = testItem.getAccessionNumber();
				reflexGroup++;

			}

			testList.add(testItem);
		}

		return testList;
	}

	private TestResultItem[] getSortedTestsFromSamples() {

		List<TestResultItem> testList = new ArrayList<TestResultItem>();

		for (Sample sample : samples) {
			currSample = sample;
			List<SampleItem> sampleItems = getSampleItemsForSample(sample);

			for (SampleItem item : sampleItems) {
				List<Analysis> analysisList = getAnalysisForSampleItem(item);

				for (Analysis analysis : analysisList) {

					List<TestResultItem> selectedItemList = getTestResultItemFromAnalysis(analysis, NO_PATIENT_NAME, NO_PATIENT_INFO, "");

					for (TestResultItem selectedItem : selectedItemList) {
						testList.add(selectedItem);
					}
				}
			}
		}

		reverseSortByAccessionAndSequence(testList);
		setSampleGroupingNumbers(testList);
		addUserSelectionReflexes(testList);

		TestResultItem[] testArray = new TestResultItem[testList.size()];
		testList.toArray(testArray);

		return testArray;
	}

	private void addUserSelectionReflexes(List<TestResultItem> testList) {
		TestReflexUtil reflexUtil = new TestReflexUtil();

		Map<String, TestResultItem> groupedSibReflexMapping = new HashMap<String, TestResultItem>();

		for (TestResultItem resultItem : testList) {
			//N.B. showSampleDetails should be renamed.  It means that it is the first result for that group of accession numbers
			if (resultItem.isShowSampleDetails()) {
				groupedSibReflexMapping = new HashMap<String, TestResultItem>();
				reflexGroup++;
			}

			if (resultItem.isReflexGroup()) {
				resultItem.setReflexParentGroup(reflexGroup);
			}

			List<TestReflex> reflexList = reflexUtil.getPossibleUserChoiceTestReflexsForTest(resultItem.getTestId());
			resultItem.setUserChoiceReflex(reflexList.size() > 0);

			boolean possibleSibs = !groupedSibReflexMapping.isEmpty();

			for (TestReflex testReflex : reflexList) {
				if (!GenericValidator.isBlankOrNull(testReflex.getSiblingReflexId())) {
					if (possibleSibs) {
						TestResultItem sibTestResultItem = groupedSibReflexMapping.get(testReflex.getSiblingReflexId());
						if (sibTestResultItem != null) {
							Random r = new Random();
							String key1 = Long.toString(Math.abs(r.nextLong()), 36);
							String key2 = Long.toString(Math.abs(r.nextLong()), 36);

							sibTestResultItem.setThisReflexKey(key1);
							sibTestResultItem.setSiblingReflexKey(key2);

							resultItem.setThisReflexKey(key2);
							resultItem.setSiblingReflexKey(key1);

							break;
						}
					}
					groupedSibReflexMapping.put(testReflex.getId(), resultItem);
				}

			}

		}

	}

	private List<SampleItem> getSampleItemsForSample(Sample sample) {
		SampleItemDAO sampleItemDAO = DAOImplFactory.getInstance().getSampleItemDAOImpl();
		return sampleItemDAO.getSampleItemsBySampleId(sample.getId());
	}

	private List<Analysis> getAnalysisForSampleItem(SampleItem item) {
		return analysisDAO.getAnalysesBySampleItemsExcludingByStatusIds(item, excludedAnalysisStatus);
	}

	private TestResultItem createTestResultItem(AnalysisService analysisService, ResultInventory testKit, String notes,
			String sequenceNumber, Result result, String accessionNumber, String patientName, String patientInfo, String techSignature,
			String techSignatureId,  String initialSampleConditions, String sampleType) {

        TestService testService = new TestService( analysisService.getTest() );
        ResultLimit resultLimit = new ResultLimitService().getResultLimitForTestAndPatient(testService.getTest(), patientService.getPatient());

        String receivedDate = currSample == null ? getCurrentDate() : currSample.getReceivedDateForDisplay();
		String testMethodName = testService.getTestMethodName();
		List<TestResult> testResults = testService.getPossibleTestResults();

		String testKitId = null;
		String testKitInventoryId = null;
		Result testKitResult = new Result();
		boolean testKitInactive = false;

		if (testKit != null) {
			testKitId = testKit.getId();
			testKitInventoryId = testKit.getInventoryLocationId();
			testKitResult.setId(testKit.getResultId());
			resultDAO.getData(testKitResult);
			testKitInactive = kitNotInActiveKitList(testKitInventoryId);
		}

		String displayTestName = analysisService.getTestDisplayName( );

		boolean isConclusion = false;
		boolean isCD4Conclusion = false;

		if (result != null && result.getAnalyte() != null) {
			isConclusion = result.getAnalyte().getId().equals(ANALYTE_CONCLUSION_ID);
			isCD4Conclusion = result.getAnalyte().getId().equals(ANALYTE_CD4_CNT_CONCLUSION_ID);

			if (isConclusion) {
				displayTestName = StringUtil.getMessageForKey("result.conclusion");
			} else if (isCD4Conclusion) {
				displayTestName = StringUtil.getMessageForKey("result.conclusion.cd4");
			}
		}

		String referralId = null;
		String referralReasonId = null;
		boolean referralCanceled = false;
		if (supportReferrals) {
			if (analysisService.getAnalysis() != null) {
				Referral referral = referralDAO.getReferralByAnalysisId(analysisService.getAnalysis().getId());
				if (referral != null) {
					referralCanceled = referral.isCanceled();
					referralId = referral.getId();
					if (!referral.isCanceled()) {
						referralReasonId = referral.getReferralReasonId();
					}
				}
			}
		}

		String uom = testService.getUOM( isCD4Conclusion );

		String testDate = GenericValidator.isBlankOrNull(analysisService.getCompletedDateForDisplay()) ? getCurrentDate()
				: analysisService.getCompletedDateForDisplay();
        ResultDisplayType resultDisplayType = testService.getDisplayTypeForTestMethod(  );
        if(resultDisplayType != ResultDisplayType.TEXT){
            inventoryNeeded = true;
        }
		TestResultItem testItem = new TestResultItem();

		testItem.setAccessionNumber(accessionNumber);
		testItem.setAnalysisId( analysisService.getAnalysis().getId() );
		testItem.setSequenceNumber(sequenceNumber);
		testItem.setReceivedDate(receivedDate);
		testItem.setTestName(displayTestName);
		testItem.setTestId( testService.getTest().getId() );
		setResultLimitDependencies(resultLimit, testItem, testResults);
		testItem.setPatientName(patientName);
		testItem.setPatientInfo(patientInfo );
		testItem.setReportable(testService.isReportable());
		testItem.setUnitsOfMeasure(uom);
		testItem.setTestDate(testDate);
		testItem.setResultDisplayType(resultDisplayType);
		testItem.setTestMethod(testMethodName);
		testItem.setAnalysisMethod(analysisService.getAnalysisType());
		testItem.setResult(result);
		testItem.setResultValue( getFormattedResultValue( result ));
		testItem.setMultiSelectResultValues(analysisService.getJSONMultiSelectResults());
		testItem.setAnalysisStatusId(analysisService.getStatusId());
		//setDictionaryResults must come after setResultType, it may override it
		testItem.setResultType(testService.getResultType());
		setDictionaryResults( testItem, isConclusion, result, testResults );

		testItem.setTechnician(techSignature);
		testItem.setTechnicianSignatureId(techSignatureId);
		testItem.setTestKitId(testKitId);
		testItem.setTestKitInventoryId(testKitInventoryId);
		testItem.setTestKitInactive(testKitInactive);
		testItem.setReadOnly( isReadOnly(isConclusion, isCD4Conclusion) && result != null && result.getId() != null);
		testItem.setReferralId(referralId);
		testItem.setReferredOut(!GenericValidator.isBlankOrNull(referralId) && !referralCanceled);
        testItem.setShadowReferredOut( testItem.isReferredOut() );
		testItem.setReferralReasonId(referralReasonId);
		testItem.setReferralCanceled(referralCanceled);
		testItem.setInitialSampleCondition(initialSampleConditions);
		testItem.setSampleType(sampleType);
		testItem.setTestSortOrder(testService.getSortOrder());
		testItem.setFailedValidation(statusRules.hasFailedValidation(analysisService.getStatusId()));
		if (useCurrentUserAsTechDefault && GenericValidator.isBlankOrNull(testItem.getTechnician())) {
			testItem.setTechnician(currentUserName);
		}
		testItem.setReflexGroup(analysisService.getTriggeredReflex());
		testItem.setChildReflex(analysisService.getTriggeredReflex() && analysisService.resultIsConclusion( result ));
        testItem.setPastNotes( notes );
        testItem.setDisplayResultAsLog(hasLogValue(testService));
        testItem.setNonconforming( analysisService.isParentNonConforming() || 
                StatusService.getInstance().matches(analysisService.getStatusId(), AnalysisStatus.TechnicalRejected ));
        if(FormFields.getInstance().useField(Field.QaEventsBySection) )
        	testItem.setNonconforming(getQaEventByTestSection(analysisService.getAnalysis()));

        Result quantifiedResult = analysisService.getQuantifiedResult();
        if( quantifiedResult != null){
            testItem.setQualifiedResultId( quantifiedResult.getId() );
            testItem.setQualifiedResultValue( quantifiedResult.getValue() );
            testItem.setHasQualifiedResult( true );
        }

        if( NUMERIC_RESULT_TYPE.equals( testResults.get( 0 ).getTestResultType()  )){
            testItem.setSignificantDigits( Integer.parseInt( testResults.get( 0 ).getSignificantDigits() ));
        }
		return testItem;
	}

	private boolean isReadOnly(boolean isConclusion, boolean isCD4Conclusion) {
		return isConclusion || isCD4Conclusion || isLockCurrentResults();
	}

	private void setResultLimitDependencies(ResultLimit resultLimit, TestResultItem testItem, List<TestResult> testResults){
		if( resultLimit != null){
			testItem.setResultLimitId(resultLimit.getId());
			testItem.setLowerNormalRange(resultLimit.getLowNormal() == Double.NEGATIVE_INFINITY ? 0 : resultLimit.getLowNormal());
			testItem.setUpperNormalRange(resultLimit.getHighNormal() == Double.POSITIVE_INFINITY ? 0 : resultLimit.getHighNormal());
			testItem.setLowerAbnormalRange(resultLimit.getLowValid() == Double.NEGATIVE_INFINITY ? 0 : resultLimit.getLowValid());
			testItem.setUpperAbnormalRange(resultLimit.getHighValid() == Double.POSITIVE_INFINITY ? 0 : resultLimit.getHighValid());
			testItem.setValid(getIsValid(testItem.getResultValue(), resultLimit));
			testItem.setNormal(getIsNormal(testItem.getResultValue(), resultLimit));
            testItem.setNormalRange( ResultLimitService.getDisplayReferenceRange( resultLimit, testResults.get( 0 ).getSignificantDigits(), " - " ));
		}
	}

	private void setDictionaryResults(TestResultItem testItem, boolean isConclusion, Result result,	List<TestResult> testResults) {
		if (isConclusion) {
			testItem.setDictionaryResults(getAnyDictionaryValues( result ));
		} else {
			setDictionaryResults(testItem, testResults, result);
		}
	}

	private void setDictionaryResults(TestResultItem testItem, List<TestResult> testResults, Result result) {

		List<IdValuePair> values = null;
		Dictionary dictionary;

		if (testResults != null && !testResults.isEmpty() && TypeOfTestResultService.ResultType.isDictionaryVariant( testResults.get( 0 ).getTestResultType() )) {
			values = new ArrayList<IdValuePair>();

			Collections.sort(testResults, new Comparator<TestResult>() {
				@Override
				public int compare(TestResult o1, TestResult o2) {
					if (GenericValidator.isBlankOrNull(o1.getSortOrder())
							|| GenericValidator.isBlankOrNull(o2.getSortOrder())) {
						return 1;
					}

					return Integer.parseInt(o1.getSortOrder()) - Integer.parseInt(o2.getSortOrder());
				}
			});
			
			String qualifiedDictionaryIds = "";
			for (TestResult testResult : testResults) {
				if ( TypeOfTestResultService.ResultType.isDictionaryVariant( testResult.getTestResultType() )) {
					dictionary = new Dictionary();
					dictionary.setId(testResult.getValue());
					dictionaryDAO.getData(dictionary);
					String displayValue = dictionary.getLocalizedName();

					if ("unknown".equals(displayValue)) {
						displayValue = GenericValidator.isBlankOrNull(dictionary.getLocalAbbreviation()) ? dictionary
								.getDictEntry() : dictionary.getLocalAbbreviation();
					}
					values.add(new IdValuePair(testResult.getValue(), displayValue));
					if (testResult.getIsQuantifiable()) {
						if( !GenericValidator.isBlankOrNull( qualifiedDictionaryIds )){
							qualifiedDictionaryIds += ",";
						}
						qualifiedDictionaryIds += testResult.getValue();
						setQualifiedValues( testItem, result );
					}
				}
			}
			
			if( !GenericValidator.isBlankOrNull( qualifiedDictionaryIds )){
				testItem.setQualifiedDictionaryId( "[" + qualifiedDictionaryIds + "]" );
			}
		}
		if( !GenericValidator.isBlankOrNull(testItem.getQualifiedResultValue())){
			testItem.setHasQualifiedResult( true );
		}

		testItem.setDictionaryResults(values);
	}

	private void setQualifiedValues( TestResultItem testItem, Result result ) {
		if (result != null) {
			List<Result> results = resultDAO.getChildResults(result.getId());
			if (!results.isEmpty()) {
				Result childResult = results.get(0);
				testItem.setQualifiedResultId(childResult.getId());
				testItem.setQualifiedResultValue(childResult.getValue());
			}
		}
	}

	private String getFormattedResultValue( Result result ) {
		return result != null ? new ResultService(result).getResultValue( false ) : "";
	}

	private boolean hasLogValue(TestService testService){//Analysis analysis, String resultValue) {
		//TO-DO refactor
		//if ( ){
//			if (GenericValidator.isBlankOrNull(resultValue)) {
//				return true;
//			}
//			try {
//				Double.parseDouble(resultValue);
//				return true;
//			} catch (NumberFormatException e) {
//				return false;
//			}
			
		//	return true;
		//}

		//return false;
        return TestIdentityService.isTestNumericViralLoad( testService.getTest() );
	}

	private List<IdValuePair> getAnyDictionaryValues(Result result) {
		List<IdValuePair> values = null;

		if (result != null && TypeOfTestResultService.ResultType.isDictionaryVariant( result.getResultType() )) {
			values = new ArrayList<IdValuePair>();

			Dictionary dictionaryValue = new Dictionary();
			dictionaryValue.setId(result.getValue());
			dictionaryDAO.getData(dictionaryValue);

			List<Dictionary> dictionaryList = dictionaryDAO.getDictionaryEntriesByCategoryId(dictionaryValue.getDictionaryCategory()
					.getId());

			for (Dictionary dictionary : dictionaryList) {
				String displayValue = dictionary.getLocalizedName();

				if ("unknown".equals(displayValue)) {
					displayValue = GenericValidator.isBlankOrNull(dictionary.getLocalAbbreviation()) ? dictionary.getDictEntry()
							: dictionary.getLocalAbbreviation();
				}
				values.add(new IdValuePair(dictionary.getId(), displayValue));
			}
		}

		return values;

	}

	private boolean getIsValid(String resultValue, ResultLimit resultLimit) {
		boolean valid = true;

		if (!GenericValidator.isBlankOrNull(resultValue) && resultLimit != null) {
			try {
				double value = Double.valueOf(resultValue);

				valid = value >= resultLimit.getLowValid() && value <= resultLimit.getHighValid();

			} catch (NumberFormatException nfe) {
				// no-op
			}
		}

		return valid;
	}

	private boolean getIsNormal(String resultValue, ResultLimit resultLimit) {
		boolean normal = true;

		if (!GenericValidator.isBlankOrNull(resultValue) && resultLimit != null) {
			try {
				double value = Double.valueOf(resultValue);

				normal = value >= resultLimit.getLowNormal() && value <= resultLimit.getHighNormal();

			} catch (NumberFormatException nfe) {
				// no-op
			}
		}

		return normal;
	}

	private boolean kitNotInActiveKitList(String testKitId) {
		List<InventoryKitItem> activeKits = getActiveKits();

		for (InventoryKitItem kit : activeKits) {
			// The locationID is the reference held in the DB
			if (testKitId.equals(kit.getInventoryLocationId())) {
				return false;
			}
		}

		return true;
	}



	private String getCurrentDate() {
		if (GenericValidator.isBlankOrNull(currentDate)) {
			currentDate = DateUtil.getCurrentDateAsText();
		}

		return currentDate;
	}


	public boolean inventoryNeeded() {
		return inventoryNeeded;
	}

	public void addExcludedAnalysisStatus(AnalysisStatus status) {
		excludedAnalysisStatus.add(Integer.parseInt(StatusService.getInstance().getStatusID(status)));
	}

	public void addIncludedSampleStatus(OrderStatus status) {
		sampleStatusList.add(Integer.parseInt(StatusService.getInstance().getStatusID(status)));
	}

	public void addIncludedAnalysisStatus(AnalysisStatus status) {
		analysisStatusList.add(Integer.parseInt(StatusService.getInstance().getStatusID(status)));
	}

	private List<InventoryKitItem> getActiveKits() {
		if (activeKits == null) {
			InventoryUtility inventoryUtil = new InventoryUtility();
			activeKits = inventoryUtil.getExistingActiveInventory();
		}

		return activeKits;
	}

	public void setLockCurrentResults(boolean lockCurrentResults) {
		this.lockCurrentResults = lockCurrentResults;
	}

	public boolean isLockCurrentResults() {
		return lockCurrentResults;
	}

	private boolean getQaEventByTestSection(Analysis analysis){
		
		if (analysis.getTestSection()!=null && analysis.getSampleItem().getSample()!=null) {
			Sample sample=analysis.getSampleItem().getSample();
			List<SampleQaEvent> sampleQaEventsList=getSampleQaEvents(sample);
			for(SampleQaEvent event : sampleQaEventsList){
				QAService qa = new QAService(event);
				if(!GenericValidator.isBlankOrNull(qa.getObservationValue( QAObservationType.SECTION )) && qa.getObservationValue( QAObservationType.SECTION ).equals(analysis.getTestSection().getNameKey()))
					 return true;				
			}
		}
		return false;
	}

	public List<SampleQaEvent> getSampleQaEvents(Sample sample){
		SampleQaEventDAO sampleQaEventDAO = new SampleQaEventDAOImpl();
		return sampleQaEventDAO.getSampleQaEventsBySample(sample);
	}

}
