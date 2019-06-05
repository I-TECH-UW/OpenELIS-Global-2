package spring.service.result;

import java.sql.Date;
import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

public interface ResultService extends BaseObjectService<Result, String> {
	void getData(Result result);

	void deleteData(Result result);

	void deleteData(List results);

	void updateData(Result result);

	boolean insertData(Result result);

	List<Result> getResultsForTestSectionInDateRange(String testSectionId, Date lowDate, Date highDate);

	List getNextResultRecord(String id);

	List getPreviousResultRecord(String id);

	void getResultByAnalysisAndAnalyte(Result result, Analysis analysis, TestAnalyte ta);

	List<Result> getResultsByAnalysis(Analysis analysis);

	List<Result> getResultsForAnalysisIdList(List<Integer> analysisIdList);

	List<Result> getResultsForPanelInDateRange(String panelId, Date lowDate, Date highDate);

	List<Result> getResultsForSample(Sample sample);

	Result getResultForAnalyteInAnalysisSet(String analyteId, List<Integer> analysisIDList);

	List<Result> getResultsForTestInDateRange(String testId, Date startDate, Date endDate);

	void getResultByTestResult(Result result, TestResult testResult);

	Result getResultForAnalyteAndSampleItem(String analyteId, String sampleItemId);

	List<Result> getResultsForTestAndSample(String sampleId, String testId);

	List<Result> getReportableResultsByAnalysis(Analysis analysis);

	List<Result> getChildResults(String resultId);

	List getAllResults();

	Result getResultById(Result result);

	Result getResultById(String resultId);

	List getPageOfResults(int startingRecNo);
}
