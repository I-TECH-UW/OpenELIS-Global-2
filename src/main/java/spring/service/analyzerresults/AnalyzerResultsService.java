package spring.service.analyzerresults;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.analyzerresults.valueholder.AnalyzerResults;

public interface AnalyzerResultsService extends BaseObjectService<AnalyzerResults> {

	List<AnalyzerResults> getResultsbyAnalyzer(String analyzerIdForName);
}
