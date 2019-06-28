package spring.service.analysis;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

public interface AnalysisService extends BaseObjectService<Analysis, String> {
	void getData(Analysis analysis);

	Analysis getAnalysisById(String analysisId);

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

	List getAllAnalysisByTestAndStatus(String testId, List<Integer> statusIdList);

	List<Analysis> getAnalysesBySampleItem(SampleItem sampleItem);

	List getAllAnalysisByTestsAndStatus(List<String> testIdList, List<Integer> statusIdList);

	Analysis buildAnalysis(Test test, SampleItem sampleItem);

	void updateAnalysises(List<Analysis> cancelAnalysis, List<Analysis> newAnalysis, String sysUserId);

	void updateAll(List<Analysis> updatedAnalysis, boolean skipAuditTrail);

	void update(Analysis analysis, boolean skipAuditTrail);

	String getTestDisplayName(Analysis analysis);

	String getCompletedDateForDisplay(Analysis analysis);

	String getAnalysisType(Analysis analysis);

	String getJSONMultiSelectResults(Analysis analysis);

	String getCSVMultiselectResults(Analysis analysis);

	Result getQuantifiedResult(Analysis analysis);

	String getStatusId(Analysis analysis);

	Boolean getTriggeredReflex(Analysis analysis);

	boolean resultIsConclusion(Result currentResult, Analysis analysis);

	boolean isParentNonConforming(Analysis analysis);

	Test getTest(Analysis analysis);

	List<Result> getResults(Analysis analysis);

	boolean hasBeenCorrectedSinceLastPatientReport(Analysis analysis);

	boolean patientReportHasBeenDone(Analysis analysis);

	String getNotesAsString(Analysis analysis, boolean prefixType, boolean prefixTimestamp, String noteSeparator,
			boolean excludeExternPrefix);

	String getOrderAccessionNumber(Analysis analysis);

	TypeOfSample getTypeOfSample(Analysis analysis);

	Panel getPanel(Analysis analysis);

	TestSection getTestSection(Analysis analysis);

}
