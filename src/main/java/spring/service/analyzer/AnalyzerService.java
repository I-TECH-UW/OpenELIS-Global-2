package spring.service.analyzer;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.analyzer.valueholder.Analyzer;

public interface AnalyzerService extends BaseObjectService<Analyzer> {
	void getData(Analyzer analyzer);

	Analyzer getAnalyzerById(Analyzer analyzer);

	List<Analyzer> getAllAnalyzers();

	Analyzer readAnalyzer(String idString);

	void deleteData(List<Analyzer> results);

	void updateData(Analyzer analyzer);

	boolean insertData(Analyzer analyzer);

	Analyzer getAnalyzerByName(String name);
}
