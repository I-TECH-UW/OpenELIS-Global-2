package spring.service.analysis;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

public interface AnalysisService extends BaseObjectService<Analysis> {

	List<Analysis> getAnalysesBySampleId(String id);

	void insert(Analysis analysis, boolean duplicateCheck);

	List<Analysis> getAnalysisByAccessionAndTestId(String accessionNumber, String testId);

	List<Analysis> getAnalysisCollectedOnExcludedByStatusId(Date date, Set<Integer> excludedStatusIds);

	List<Analysis> getAnalysesBySampleItemsExcludingByStatusIds(SampleItem sampleItem, Set<Integer> excludedStatusIds);

	List<Analysis> getAnalysesForStatusId(String status);

	List<Analysis> getAnalysesBySampleStatusIdExcludingByStatusId(String sampleStatus, Set<Integer> excludedStatusIds);

	List<Analysis> getAllAnalysisByTestAndExcludedStatus(String testId, List<Integer> excludedStatusIntList);

	void updateAnalysises(List<Analysis> cancelAnalysis, List<Analysis> newAnalysis, String sysUserId);

	List<Analysis> getAllAnalysisByTestAndStatus(String id, List<Integer> statusList);

	List<Analysis> getAllAnalysisByTestsAndStatus(List<String> nfsTestIdList, List<Integer> statusList);

	List<Analysis> getAllAnalysisByTestSectionAndStatus(String sectionId, List<Integer> statusList,
			boolean sortedByDateAndAccession);

	List<Analysis> getAnalysesBySampleItemIdAndStatusId(String sampleItemId, String canceledTestStatusId);

	Test getTest();

	Analysis buildAnalysis(Test test, SampleItem sampleItem);

	TestSection getTestSection();

	Panel getPanel();

	TypeOfSample getTypeOfSample();

	String getOrderAccessionNumber();

	String getNotesAsString(boolean prefixType, boolean prefixTimestamp, String noteSeparator,
			boolean excludeExternPrefix);

	boolean patientReportHasBeenDone();

	boolean hasBeenCorrectedSinceLastPatientReport();

	List<Result> getResults();

	List<Analysis> getAnalysisStartedOrCompletedInDateRange(Date lowDate, Date highDate);

	boolean isParentNonConforming();

	boolean resultIsConclusion(Result currentResult);

	Boolean getTriggeredReflex();

	String getStatusId();

	String getAnalysisType();

	String getCompletedDateForDisplay();

	Result getQuantifiedResult();

	String getJSONMultiSelectResults();

	String getCSVMultiselectResults();

	String getTestDisplayName();

	Analysis getAnalysis();
}
