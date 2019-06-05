package spring.service.analyzerresults;

import java.util.List;

import spring.mine.result.controller.AnalyzerResultsController.SampleGrouping;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.analyzerresults.valueholder.AnalyzerResults;

public interface AnalyzerResultsService extends BaseObjectService<AnalyzerResults> {
	void getData(AnalyzerResults results);

	@Override
	void deleteAll(List<AnalyzerResults> deletableAnalyzerResults);

	void updateData(AnalyzerResults results);

	AnalyzerResults readAnalyzerResults(String idString);

	List<AnalyzerResults> getResultsbyAnalyzer(String analyzerId);

	void insertAnalyzerResults(List<AnalyzerResults> results, String sysUserId);

	void persistAnalyzerResults(List<AnalyzerResults> deletableAnalyzerResults, List<SampleGrouping> sampleGroupList,
			String sysUserId);
}
