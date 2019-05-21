package spring.service.analyzerresults;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.analyzerresults.dao.AnalyzerResultsDAO;
import us.mn.state.health.lims.analyzerresults.valueholder.AnalyzerResults;

@Service
public class AnalyzerResultsServiceImpl extends BaseObjectServiceImpl<AnalyzerResults>
		implements AnalyzerResultsService {
	@Autowired
	protected AnalyzerResultsDAO baseObjectDAO;

	AnalyzerResultsServiceImpl() {
		super(AnalyzerResults.class);
	}

	@Override
	protected AnalyzerResultsDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional
	public List<AnalyzerResults> getResultsbyAnalyzer(String analyzerId) {
		return baseObjectDAO.getAllMatchingOrdered("analyzerId", analyzerId, "id", false);
	}
}
