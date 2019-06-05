package spring.service.analysis;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import spring.service.dictionary.DictionaryService;
import spring.service.note.NoteServiceImpl;
import spring.service.referencetables.ReferenceTablesService;
import spring.service.result.ResultService;
import spring.service.result.ResultServiceImpl;
import spring.service.test.TestServiceImpl;
import spring.service.typeofsample.TypeOfSampleService;
import spring.service.typeofsample.TypeOfSampleServiceImpl;
import spring.service.typeoftestresult.TypeOfTestResultServiceImpl;
import spring.util.SpringContext;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.QAService;
import us.mn.state.health.lims.common.services.ReportTrackingService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

@Service
@DependsOn({ "springContext" })
@Scope("prototype")
public class AnalysisServiceImpl extends BaseObjectServiceImpl<Analysis, String> implements AnalysisService {

	@Autowired
	protected AnalysisDAO baseObjectDAO = SpringContext.getBean(AnalysisDAO.class);

	@Autowired
	private DictionaryService dictionaryService = SpringContext.getBean(DictionaryService.class);
	@Autowired
	private ResultService resultService = SpringContext.getBean(ResultService.class);
	@Autowired
	private TypeOfSampleService typeOfSampleService = SpringContext.getBean(TypeOfSampleService.class);
	@Autowired
	private ReferenceTablesService referenceTablesService = SpringContext.getBean(ReferenceTablesService.class);

	private Analysis analysis;
	public static String TABLE_REFERENCE_ID;
	private final String DEFAULT_ANALYSIS_TYPE = "MANUAL";

	public synchronized void initializeGlobalVariables() {
		if (TABLE_REFERENCE_ID == null) {
			TABLE_REFERENCE_ID = referenceTablesService.getReferenceTableByName("ANALYSIS").getId();
		}
	}

	public AnalysisServiceImpl() {
		super(Analysis.class);
		initializeGlobalVariables();

	}

	public AnalysisServiceImpl(Analysis analysis) {
		this();
		this.analysis = analysis;
	}

	public AnalysisServiceImpl(String analysisId) {
		this();
		if (!GenericValidator.isBlankOrNull(analysisId)) {
			analysis = baseObjectDAO.getAnalysisById(analysisId);
		}
	}

	@Override
	public Analysis getAnalysis() {
		return analysis;
	}

	@Override
	public String getTestDisplayName() {
		if (analysis == null) {
			return "";
		}
		Test test = getTest();
		String name = TestServiceImpl.getLocalizedTestNameWithType(test);

		TypeOfSample typeOfSample = TypeOfSampleServiceImpl.getInstance().getTypeOfSampleForTest(test.getId());

		if (typeOfSample != null && typeOfSample.getId()
				.equals(TypeOfSampleServiceImpl.getInstance().getTypeOfSampleIdForLocalAbbreviation("Variable"))) {
			name += "(" + analysis.getSampleTypeName() + ")";
		}

		String parentResultType = analysis.getParentResult() != null ? analysis.getParentResult().getResultType() : "";
		if (TypeOfTestResultServiceImpl.ResultType.isMultiSelectVariant(parentResultType)) {
			Dictionary dictionary = dictionaryService.getDictionaryById(analysis.getParentResult().getValue());
			if (dictionary != null) {
				String parentResult = dictionary.getLocalAbbreviation();
				if (GenericValidator.isBlankOrNull(parentResult)) {
					parentResult = dictionary.getDictEntry();
				}
				name = parentResult + " &rarr; " + name;
			}
		}

		return name;
	}

	@Override
	public String getCSVMultiselectResults() {
		if (analysis == null) {
			return "";
		}
		List<Result> existingResults = resultService.getResultsByAnalysis(analysis);
		StringBuilder multiSelectBuffer = new StringBuilder();
		for (Result existingResult : existingResults) {
			if (TypeOfTestResultServiceImpl.ResultType.isMultiSelectVariant(existingResult.getResultType())) {
				multiSelectBuffer.append(existingResult.getValue());
				multiSelectBuffer.append(',');
			}
		}

		// remove last ','
		multiSelectBuffer.setLength(multiSelectBuffer.length() - 1);

		return multiSelectBuffer.toString();
	}

	@Override
	public String getJSONMultiSelectResults() {
		return analysis == null ? ""
				: ResultServiceImpl.getJSONStringForMultiSelect(resultService.getResultsByAnalysis(analysis));
	}

	@Override
	public Result getQuantifiedResult() {
		if (analysis == null) {
			return null;
		}
		List<Result> existingResults = resultService.getResultsByAnalysis(analysis);
		List<String> quantifiableResultsIds = new ArrayList<>();
		for (Result existingResult : existingResults) {
			if (TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(existingResult.getResultType())) {
				quantifiableResultsIds.add(existingResult.getId());
			}
		}

		for (Result existingResult : existingResults) {
			if (!TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(existingResult.getResultType())
					&& existingResult.getParentResult() != null
					&& quantifiableResultsIds.contains(existingResult.getParentResult().getId())
					&& !GenericValidator.isBlankOrNull(existingResult.getValue())) {
				return existingResult;
			}
		}

		return null;
	}

	@Override
	public String getCompletedDateForDisplay() {
		return analysis == null ? "" : analysis.getCompletedDateForDisplay();
	}

	@Override
	public String getAnalysisType() {
		return analysis == null ? "" : analysis.getAnalysisType();
	}

	@Override
	public String getStatusId() {
		return analysis == null ? "" : analysis.getStatusId();
	}

	@Override
	public Boolean getTriggeredReflex() {
		return analysis == null ? false : analysis.getTriggeredReflex();
	}

	@Override
	public boolean resultIsConclusion(Result currentResult) {
		if (analysis == null || currentResult == null) {
			return false;
		}
		List<Result> results = resultService.getResultsByAnalysis(analysis);
		if (results.size() == 1) {
			return false;
		}

		Long testResultId = Long.parseLong(currentResult.getId());
		// This based on the fact that the conclusion is always added
		// after the shared result so if there is a result with a larger id
		// then this is not a conclusion
		for (Result result : results) {
			if (Long.parseLong(result.getId()) > testResultId) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean isParentNonConforming() {
		return analysis == null ? false : QAService.isAnalysisParentNonConforming(analysis);
	}

	@Override
	public Test getTest() {
		return analysis == null ? null : analysis.getTest();
	}

	@Override
	public List<Analysis> getAnalysisStartedOrCompletedInDateRange(Date lowDate, Date highDate) {
		return baseObjectDAO.getAnalysisStartedOrCompletedInDateRange(lowDate, highDate);
	}

	@Override
	public List<Result> getResults() {
		return analysis == null ? new ArrayList<>() : resultService.getResultsByAnalysis(analysis);
	}

	@Override
	public boolean hasBeenCorrectedSinceLastPatientReport() {
		return analysis == null ? false : analysis.isCorrectedSincePatientReport();
	}

	@Override
	public boolean patientReportHasBeenDone() {
		return analysis == null ? false
				: ReportTrackingService.getInstance().getLastReportForSample(analysis.getSampleItem().getSample(),
						ReportTrackingService.ReportType.PATIENT) != null;
	}

	@Override
	public String getNotesAsString(boolean prefixType, boolean prefixTimestamp, String noteSeparator,
			boolean excludeExternPrefix) {
		return analysis == null ? ""
				: new NoteServiceImpl(analysis).getNotesAsString(prefixType, prefixTimestamp, noteSeparator,
						excludeExternPrefix);
	}

	@Override
	public String getOrderAccessionNumber() {
		return analysis == null ? "" : analysis.getSampleItem().getSample().getAccessionNumber();
	}

	@Override
	public TypeOfSample getTypeOfSample() {
		return analysis == null ? null
				: typeOfSampleService.getTypeOfSampleById(analysis.getSampleItem().getTypeOfSampleId());
	}

	@Override
	public Panel getPanel() {
		return analysis == null ? null : analysis.getPanel();
	}

	@Override
	public TestSection getTestSection() {
		return analysis == null ? null : analysis.getTestSection();
	}

	@Override
	public Analysis buildAnalysis(Test test, SampleItem sampleItem) {

		Analysis analysis = new Analysis();
		analysis.setTest(test);
		analysis.setIsReportable(test.getIsReportable());
		analysis.setAnalysisType(DEFAULT_ANALYSIS_TYPE);
		analysis.setRevision("0");
		analysis.setStartedDate(DateUtil.getNowAsSqlDate());
		analysis.setStatusId(StatusService.getInstance().getStatusID(StatusService.AnalysisStatus.NotStarted));
		analysis.setSampleItem(sampleItem);
		analysis.setTestSection(test.getTestSection());
		analysis.setSampleTypeName(sampleItem.getTypeOfSample().getLocalizedName());

		return analysis;
	}

	@Override
	protected AnalysisDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional
	public List<Analysis> getAnalysesBySampleId(String id) {
		return baseObjectDAO.getAnalysesBySampleId(id);
	}

	@Override
	@Transactional
	public void insert(Analysis analysis, boolean duplicateCheck) {
		baseObjectDAO.insertData(analysis, duplicateCheck);
	}

	@Override
	@Transactional
	public List<Analysis> getAnalysisByAccessionAndTestId(String accessionNumber, String testId) {
		return baseObjectDAO.getAnalysisByAccessionAndTestId(accessionNumber, testId);
	}

	@Override
	@Transactional
	public List<Analysis> getAnalysisCollectedOnExcludedByStatusId(Date date, Set<Integer> excludedStatusIds) {
		return baseObjectDAO.getAnalysisCollectedOnExcludedByStatusId(date, excludedStatusIds);
	}

	@Override
	@Transactional
	public List<Analysis> getAnalysesBySampleItemsExcludingByStatusIds(SampleItem sampleItem,
			Set<Integer> excludedStatusIds) {
		return baseObjectDAO.getAnalysesBySampleItemsExcludingByStatusIds(sampleItem, excludedStatusIds);
	}

	@Override
	@Transactional
	public List<Analysis> getAnalysesForStatusId(String status) {
		return baseObjectDAO.getAllMatching("statusId", status);
	}

	@Override
	@Transactional
	public List<Analysis> getAnalysesBySampleStatusIdExcludingByStatusId(String sampleStatus,
			Set<Integer> excludedStatusIds) {
		return baseObjectDAO.getAnalysesBySampleStatusIdExcludingByStatusId(sampleStatus, excludedStatusIds);
	}

	@Override
	@Transactional
	public List<Analysis> getAllAnalysisByTestAndExcludedStatus(String testId, List<Integer> excludedStatusIntList) {
		return baseObjectDAO.getAllAnalysisByTestAndExcludedStatus(testId, excludedStatusIntList);
	}

	@Override
	@Transactional
	public void updateAnalysises(List<Analysis> cancelAnalysis, List<Analysis> newAnalysis, String sysUserId) {
		String cancelStatus = StatusService.getInstance().getStatusID(StatusService.AnalysisStatus.Canceled);
		for (Analysis analysis : cancelAnalysis) {
			analysis.setStatusId(cancelStatus);
			analysis.setSysUserId(sysUserId);
			update(analysis);
		}

		for (Analysis analysis : newAnalysis) {
			analysis.setSysUserId(sysUserId);
			insert(analysis, false);
		}
	}

	@Override
	@Transactional
	public List<Analysis> getAllAnalysisByTestAndStatus(String id, List<Integer> statusList) {
		return baseObjectDAO.getAllAnalysisByTestAndStatus(id, statusList);
	}

	@Override
	@Transactional
	public List<Analysis> getAllAnalysisByTestsAndStatus(List<String> nfsTestIdList, List<Integer> statusList) {
		return baseObjectDAO.getAllAnalysisByTestsAndStatus(nfsTestIdList, statusList);
	}

	@Override
	@Transactional
	public List<Analysis> getAllAnalysisByTestSectionAndStatus(String sectionId, List<Integer> statusList,
			boolean sortedByDateAndAccession) {
		return baseObjectDAO.getAllAnalysisByTestSectionAndStatus(sectionId, statusList, sortedByDateAndAccession);
	}

	@Override
	@Transactional
	public List<Analysis> getAnalysesBySampleItemIdAndStatusId(String sampleItemId, String canceledTestStatusId) {
		return baseObjectDAO.getAnalysesBySampleItemIdAndStatusId(sampleItemId, canceledTestStatusId);
	}

	@Override
	public void getData(Analysis analysis) {
		getBaseObjectDAO().getData(analysis);

	}

	@Override
	public Analysis getAnalysisById(String analysisId) {
		return getBaseObjectDAO().getAnalysisById(analysisId);
	}

	@Override
	public void deleteData(List analysiss) {
		getBaseObjectDAO().deleteData(analysiss);

	}

	@Override
	public List getAllAnalyses() {
		return getBaseObjectDAO().getAllAnalyses();
	}

	@Override
	public void updateData(Analysis analysis) {
		getBaseObjectDAO().updateData(analysis);

	}

	@Override
	public void updateData(Analysis analysis, boolean skipAuditTrail) {
		getBaseObjectDAO().updateData(analysis, skipAuditTrail);

	}

	@Override
	public List getAnalyses(String filter) {
		return getBaseObjectDAO().getAnalyses(filter);
	}

	@Override
	public boolean insertData(Analysis analysis, boolean duplicateCheck) {
		return getBaseObjectDAO().insertData(analysis, duplicateCheck);
	}

	@Override
	public List<Analysis> getAnalysisByTestDescriptionAndCompletedDateRange(List<String> descriptions, Date sqlDayOne,
			Date sqlDayTwo) {
		return getBaseObjectDAO().getAnalysisByTestDescriptionAndCompletedDateRange(descriptions, sqlDayOne, sqlDayTwo);
	}

	@Override
	public List getMaxRevisionPendingAnalysesReadyForReportPreviewBySample(Sample sample) {
		return getBaseObjectDAO().getMaxRevisionPendingAnalysesReadyForReportPreviewBySample(sample);
	}

	@Override
	public List getMaxRevisionAnalysesReadyForReportPreviewBySample(List accessionNumbers) {
		return getBaseObjectDAO().getMaxRevisionAnalysesReadyForReportPreviewBySample(accessionNumbers);
	}

	@Override
	public List getMaxRevisionPendingAnalysesReadyToBeReportedBySample(Sample sample) {
		return getBaseObjectDAO().getMaxRevisionPendingAnalysesReadyToBeReportedBySample(sample);
	}

	@Override
	public List<Analysis> getAnalysesBySampleIdExcludedByStatusId(String id, Set<Integer> statusIds) {
		return getBaseObjectDAO().getAnalysesBySampleIdExcludedByStatusId(id, statusIds);
	}

	@Override
	public List<Analysis> getAllAnalysisByTestSectionAndStatus(String testSectionId, List<Integer> analysisStatusList,
			List<Integer> sampleStatusList) {
		return getBaseObjectDAO().getAllAnalysisByTestSectionAndStatus(testSectionId, analysisStatusList,
				sampleStatusList);
	}

	@Override
	public List getMaxRevisionAnalysesBySampleIncludeCanceled(SampleItem sampleItem) {
		return getBaseObjectDAO().getMaxRevisionAnalysesBySampleIncludeCanceled(sampleItem);
	}

	@Override
	public Analysis getPatientPreviousAnalysisForTestName(Patient patient, Sample currentSample, String testName) {
		return getBaseObjectDAO().getPatientPreviousAnalysisForTestName(patient, currentSample, testName);
	}

	@Override
	public List<Analysis> getAnalysisByTestNamesAndCompletedDateRange(List<String> testNames, Date lowDate,
			Date highDate) {
		return getBaseObjectDAO().getAnalysisByTestNamesAndCompletedDateRange(testNames, lowDate, highDate);
	}

	@Override
	public List<Analysis> getAnalysesBySampleIdTestIdAndStatusId(List<Integer> sampleIdList, List<Integer> testIdList,
			List<Integer> statusIdList) {
		return getBaseObjectDAO().getAnalysesBySampleIdTestIdAndStatusId(sampleIdList, testIdList, statusIdList);
	}

	@Override
	public List getMaxRevisionParentTestAnalysesBySample(SampleItem sampleItem) {
		return getBaseObjectDAO().getMaxRevisionParentTestAnalysesBySample(sampleItem);
	}

	@Override
	public List<Analysis> getAnalysisStartedOnRangeByStatusId(Date lowDate, Date highDate, String statusID) {
		return getBaseObjectDAO().getAnalysisStartedOnRangeByStatusId(lowDate, highDate, statusID);
	}

	@Override
	public List getRevisionHistoryOfAnalysesBySample(SampleItem sampleItem) {
		return getBaseObjectDAO().getRevisionHistoryOfAnalysesBySample(sampleItem);
	}

	@Override
	public Analysis getPreviousAnalysisForAmendedAnalysis(Analysis analysis) {
		return getBaseObjectDAO().getPreviousAnalysisForAmendedAnalysis(analysis);
	}

	@Override
	public List getAllAnalysisByTestSectionAndExcludedStatus(String testSectionId, List<Integer> statusIdList) {
		return getBaseObjectDAO().getAllAnalysisByTestSectionAndExcludedStatus(testSectionId, statusIdList);
	}

	@Override
	public List<Analysis> getAnalysisStartedOnExcludedByStatusId(Date collectionDate, Set<Integer> statusIds) {
		return getBaseObjectDAO().getAnalysisStartedOnExcludedByStatusId(collectionDate, statusIds);
	}

	@Override
	public List<Analysis> getAnalysisByTestSectionAndCompletedDateRange(String sectionID, Date lowDate, Date highDate) {
		return getBaseObjectDAO().getAnalysisByTestSectionAndCompletedDateRange(sectionID, lowDate, highDate);
	}

	@Override
	public List getMaxRevisionAnalysesReadyToBeReported() {
		return getBaseObjectDAO().getMaxRevisionAnalysesReadyToBeReported();
	}

	@Override
	public void getMaxRevisionAnalysisBySampleAndTest(Analysis analysis) {
		getBaseObjectDAO().getMaxRevisionAnalysisBySampleAndTest(analysis);

	}

	@Override
	public List getAnalysesAlreadyReportedBySample(Sample sample) {
		return getBaseObjectDAO().getAnalysesAlreadyReportedBySample(sample);
	}

	@Override
	public List getRevisionHistoryOfAnalysesBySampleAndTest(SampleItem sampleItem, Test test,
			boolean includeLatestRevision) {
		return getBaseObjectDAO().getRevisionHistoryOfAnalysesBySampleAndTest(sampleItem, test, includeLatestRevision);
	}

	@Override
	public List<Analysis> getAnalysesBySampleStatusId(String statusId) {
		return getBaseObjectDAO().getAnalysesBySampleStatusId(statusId);
	}

	@Override
	public List<Analysis> getAnalysisEnteredAfterDate(Timestamp latestCollectionDate) {
		return getBaseObjectDAO().getAnalysisEnteredAfterDate(latestCollectionDate);
	}

	@Override
	public List<Analysis> getAnalysesBySampleIdAndStatusId(String id, Set<Integer> analysisStatusIds) {
		return getBaseObjectDAO().getAnalysesBySampleIdAndStatusId(id, analysisStatusIds);
	}

	@Override
	public List<Analysis> getAnalysisStartedOn(Date collectionDate) {
		return getBaseObjectDAO().getAnalysisStartedOn(collectionDate);
	}

	@Override
	public List getMaxRevisionAnalysesBySample(SampleItem sampleItem) {
		return getBaseObjectDAO().getMaxRevisionAnalysesBySample(sampleItem);
	}

	@Override
	public List getAllChildAnalysesByResult(Result result) {
		return getBaseObjectDAO().getAllChildAnalysesByResult(result);
	}

	@Override
	public List getAnalysesReadyToBeReported() {
		return getBaseObjectDAO().getAnalysesReadyToBeReported();
	}

	@Override
	public List<Analysis> getAnalysisBySampleAndTestIds(String sampleKey, List<Integer> testIds) {
		return getBaseObjectDAO().getAnalysisBySampleAndTestIds(sampleKey, testIds);
	}

	@Override
	public List<Analysis> getAnalysisCompleteInRange(Timestamp lowDate, Timestamp highDate) {
		return getBaseObjectDAO().getAnalysisCompleteInRange(lowDate, highDate);
	}

	@Override
	public List getAllMaxRevisionAnalysesPerTest(Test test) {
		return getBaseObjectDAO().getAllMaxRevisionAnalysesPerTest(test);
	}

	@Override
	public List<Analysis> getAnalysisCollectedOn(Date collectionDate) {
		return getBaseObjectDAO().getAnalysisCollectedOn(collectionDate);
	}

	@Override
	public List getAllAnalysesPerTest(Test test) {
		return getBaseObjectDAO().getAllAnalysesPerTest(test);
	}

	@Override
	public List getPreviousAnalysisRecord(String id) {
		return getBaseObjectDAO().getPreviousAnalysisRecord(id);
	}

	@Override
	public List<Analysis> getAnalysesBySampleItem(SampleItem sampleItem) {
		return getBaseObjectDAO().getAnalysesBySampleItem(sampleItem);
	}

	@Override
	public List getPageOfAnalyses(int startingRecNo) {
		return getBaseObjectDAO().getPageOfAnalyses(startingRecNo);
	}

	@Override
	public List getNextAnalysisRecord(String id) {
		return getBaseObjectDAO().getNextAnalysisRecord(id);
	}
}
