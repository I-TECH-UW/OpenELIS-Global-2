package spring.service.analyzerimport;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.analyzerimport.valueholder.AnalyzerTestMapping;

public interface AnalyzerTestMappingService extends BaseObjectService<AnalyzerTestMapping> {
	void updateMapping(AnalyzerTestMapping analyzerTestNameMapping, String currentUserId);

	void deleteData(List<AnalyzerTestMapping> testMappingList, String currentUserId);

	void insertData(AnalyzerTestMapping analyzerTestMapping, String currentUserId);

	List<AnalyzerTestMapping> getAllAnalyzerTestMappings();
}
