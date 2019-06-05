package spring.service.analysis;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

public interface AnalysisService extends BaseObjectService<Analysis, String> {
	void getData(Analysis analysis);

	Analysis getAnalysisById(String analysisId);

	void deleteData(List analysiss);

	List getAllAnalyses();

	void updateData(Analysis analysis);

	void updateData(Analysis analysis, boolean skipAuditTrail);

	List getAnalyses(String filter);

	boolean insertData(Analysis analysis, boolean duplicateCheck);

	List<Analysis> getAnalysisByTestDescriptionAndCompletedDateRange(List<String> descriptions, Date sqlDayOne,
			Date sqlDayTwo);

	List getMaxRevisionPendingAnalysesReadyForReportPreviewBySample(Sample sample);

	List getMaxRevisionAnalysesReadyForReportPreviewBySample(List accessionNumbers);

	List getMaxRevisionPendingAnalysesReadyToBeReportedBySample(Sample sample);

	List<Analysis> getAnalysesBySampleIdExcludedByStatusId(String id, Set<Integer> statusIds);

	List<Analysis> getAnalysisStartedOrCompletedInDateRange(Date lowDate, Date highDate);

	List<Analysis> getAllAnalysisByTestSectionAndStatus(String testSectionId, List<Integer> analysisStatusList,
			List<Integer> sampleStatusList);

	List getAllAnalysisByTestSectionAndStatus(String testSectionId, List<Integer> statusIdList,
			boolean sortedByDateAndAccession);

	List getMaxRevisionAnalysesBySampleIncludeCanceled(SampleItem sampleItem);

	Analysis getPatientPreviousAnalysisForTestName(Patient patient, Sample currentSample, String testName);

	List<Analysis> getAnalysisByTestNamesAndCompletedDateRange(List<String> testNames, Date lowDate, Date highDate);

	List<Analysis> getAnalysesBySampleIdTestIdAndStatusId(List<Integer> sampleIdList, List<Integer> testIdList,
			List<Integer> statusIdList);

	List getMaxRevisionParentTestAnalysesBySample(SampleItem sampleItem);

	List<Analysis> getAnalysesBySampleItemsExcludingByStatusIds(SampleItem sampleItem, Set<Integer> statusIds);

	List<Analysis> getAnalysisStartedOnRangeByStatusId(Date lowDate, Date highDate, String statusID);

	List getRevisionHistoryOfAnalysesBySample(SampleItem sampleItem);

	List<Analysis> getAnalysisCollectedOnExcludedByStatusId(Date collectionDate, Set<Integer> statusIds);

	Analysis getPreviousAnalysisForAmendedAnalysis(Analysis analysis);

	List getAllAnalysisByTestSectionAndExcludedStatus(String testSectionId, List<Integer> statusIdList);

	List<Analysis> getAnalysesBySampleStatusIdExcludingByStatusId(String statusId, Set<Integer> statusIds);

	List<Analysis> getAnalysesBySampleItemIdAndStatusId(String sampleItemId, String statusId);

	List<Analysis> getAnalysisStartedOnExcludedByStatusId(Date collectionDate, Set<Integer> statusIds);

	List<Analysis> getAnalysisByTestSectionAndCompletedDateRange(String sectionID, Date lowDate, Date highDate);

	List getMaxRevisionAnalysesReadyToBeReported();

	void getMaxRevisionAnalysisBySampleAndTest(Analysis analysis);

	List getAllAnalysisByTestAndExcludedStatus(String testId, List<Integer> statusIdList);

	List getAnalysesAlreadyReportedBySample(Sample sample);

	List getRevisionHistoryOfAnalysesBySampleAndTest(SampleItem sampleItem, Test test, boolean includeLatestRevision);

	List<Analysis> getAnalysesBySampleStatusId(String statusId);

	List<Analysis> getAnalysisEnteredAfterDate(Timestamp latestCollectionDate);

	List<Analysis> getAnalysesBySampleIdAndStatusId(String id, Set<Integer> analysisStatusIds);

	List<Analysis> getAnalysisStartedOn(Date collectionDate);

	List getMaxRevisionAnalysesBySample(SampleItem sampleItem);

	List getAllChildAnalysesByResult(Result result);

	List<Analysis> getAnalysesBySampleId(String id);

	List getAnalysesReadyToBeReported();

	List<Analysis> getAnalysisBySampleAndTestIds(String sampleKey, List<Integer> testIds);

	List<Analysis> getAnalysisCompleteInRange(Timestamp lowDate, Timestamp highDate);

	List<Analysis> getAnalysesForStatusId(String statusId);

	List getAllMaxRevisionAnalysesPerTest(Test test);

	List<Analysis> getAnalysisByAccessionAndTestId(String accessionNumber, String testId);

	List<Analysis> getAnalysisCollectedOn(Date collectionDate);

	List getAllAnalysesPerTest(Test test);

	List getAllAnalysisByTestAndStatus(String testId, List<Integer> statusIdList);

	List getPreviousAnalysisRecord(String id);

	List<Analysis> getAnalysesBySampleItem(SampleItem sampleItem);

	List getPageOfAnalyses(int startingRecNo);

	List getNextAnalysisRecord(String id);

	List getAllAnalysisByTestsAndStatus(List<String> testIdList, List<Integer> statusIdList);

	void insert(Analysis analysis, boolean duplicateCheck);

	String getCompletedDateForDisplay();

	Result getQuantifiedResult();

	String getJSONMultiSelectResults();

	String getCSVMultiselectResults();

	boolean isParentNonConforming();

	String getTestDisplayName();

	String getOrderAccessionNumber();

	boolean patientReportHasBeenDone();

	boolean resultIsConclusion(Result currentResult);

	Boolean getTriggeredReflex();

	boolean hasBeenCorrectedSinceLastPatientReport();

	Test getTest();

	Analysis buildAnalysis(Test test, SampleItem sampleItem);

	void updateAnalysises(List<Analysis> cancelAnalysis, List<Analysis> newAnalysis, String sysUserId);

	TestSection getTestSection();

	Panel getPanel();

	TypeOfSample getTypeOfSample();

	String getNotesAsString(boolean prefixType, boolean prefixTimestamp, String noteSeparator,
			boolean excludeExternPrefix);

	String getAnalysisType();

	String getStatusId();

	Analysis getAnalysis();

	List<Result> getResults();

}
