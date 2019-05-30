package spring.service.analyzer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.analyzerimport.AnalyzerTestMappingService;
import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.analyzer.dao.AnalyzerDAO;
import us.mn.state.health.lims.analyzer.valueholder.Analyzer;
import us.mn.state.health.lims.analyzerimport.valueholder.AnalyzerTestMapping;

@Service
public class AnalyzerServiceImpl extends BaseObjectServiceImpl<Analyzer> implements AnalyzerService {
	@Autowired
	protected AnalyzerDAO baseObjectDAO;
	@Autowired
	private AnalyzerTestMappingService analyzerMappingService;

	AnalyzerServiceImpl() {
		super(Analyzer.class);
	}

	@Override
	protected AnalyzerDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void getData(Analyzer analyzer) {
		getBaseObjectDAO().getData(analyzer);

	}

	@Override
	public Analyzer getAnalyzerById(Analyzer analyzer) {
		return getBaseObjectDAO().getAnalyzerById(analyzer);
	}

	@Override
	public List<Analyzer> getAllAnalyzers() {
		return getBaseObjectDAO().getAllAnalyzers();
	}

	@Override
	public Analyzer readAnalyzer(String idString) {
		return getBaseObjectDAO().readAnalyzer(idString);
	}

	@Override
	public void deleteData(List<Analyzer> results) {
		getBaseObjectDAO().deleteData(results);

	}

	@Override
	public void updateData(Analyzer analyzer) {
		update(analyzer);

	}

	@Override
	public boolean insertData(Analyzer analyzer) {
		return insert(analyzer) != null;
	}

	@Override
	public Analyzer getAnalyzerByName(String name) {
		return getMatch("name", name).orElse(null);
	}

	@Override
	@Transactional
	public void persistData(Analyzer analyzer, List<AnalyzerTestMapping> testMappings,
			List<AnalyzerTestMapping> existingMappings) {
		if (analyzer.getId() == null) {
			insertData(analyzer);
		} else {
			updateData(analyzer);
		}

		for (AnalyzerTestMapping mapping : testMappings) {
			mapping.setAnalyzerId(analyzer.getId());
			if (newMapping(mapping, existingMappings)) {
				analyzerMappingService.insertData(mapping, "1");
				existingMappings.add(mapping);
			}
		}

	}

	private boolean newMapping(AnalyzerTestMapping mapping, List<AnalyzerTestMapping> existingMappings) {
		for (AnalyzerTestMapping existingMap : existingMappings) {
			if (existingMap.getAnalyzerId().equals(mapping.getAnalyzerId())
					&& existingMap.getAnalyzerTestName().equals(mapping.getAnalyzerTestName())) {
				return false;
			}
		}
		return true;
	}
}
