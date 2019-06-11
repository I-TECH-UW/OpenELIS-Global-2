package spring.service.analyzer;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.analyzer.valueholder.Analyzer;
import us.mn.state.health.lims.analyzerimport.valueholder.AnalyzerTestMapping;

public interface AnalyzerService extends BaseObjectService<Analyzer, String> {
	Analyzer getAnalyzerByName(String name);

	void persistData(Analyzer analyzer, List<AnalyzerTestMapping> testMappings,
			List<AnalyzerTestMapping> existingMappings);
}
