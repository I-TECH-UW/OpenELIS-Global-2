package spring.service.analyzer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.analyzer.dao.AnalyzerDAO;
import us.mn.state.health.lims.analyzer.valueholder.Analyzer;

@Service
public class AnalyzerServiceImpl extends BaseObjectServiceImpl<Analyzer> implements AnalyzerService {
	@Autowired
	protected AnalyzerDAO baseObjectDAO;

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
        getBaseObjectDAO().updateData(analyzer);

	}

	@Override
	public boolean insertData(Analyzer analyzer) {
        return getBaseObjectDAO().insertData(analyzer);
	}

	@Override
	public Analyzer getAnalyzerByName(String name) {
        return getBaseObjectDAO().getAnalyzerByName(name);
	}
}
