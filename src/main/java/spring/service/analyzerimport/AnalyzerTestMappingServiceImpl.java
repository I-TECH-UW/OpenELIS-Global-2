package spring.service.analyzerimport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.analyzerimport.dao.AnalyzerTestMappingDAO;
import us.mn.state.health.lims.analyzerimport.valueholder.AnalyzerTestMapping;
import us.mn.state.health.lims.analyzerimport.valueholder.AnalyzerTestMappingPK;

@Service
public class AnalyzerTestMappingServiceImpl extends BaseObjectServiceImpl<AnalyzerTestMapping, AnalyzerTestMappingPK>
		implements AnalyzerTestMappingService {
	@Autowired
	protected AnalyzerTestMappingDAO baseObjectDAO;

	AnalyzerTestMappingServiceImpl() {
		super(AnalyzerTestMapping.class);
	}

	@Override
	protected AnalyzerTestMappingDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void updateMapping(AnalyzerTestMapping analyzerTestNameMapping, String currentUserId) {
		getBaseObjectDAO().updateMapping(analyzerTestNameMapping, currentUserId);

	}

	@Override
	public void deleteData(List<AnalyzerTestMapping> testMappingList, String currentUserId) {
		getBaseObjectDAO().deleteData(testMappingList, currentUserId);

	}

	@Override
	public void insertData(AnalyzerTestMapping analyzerTestMapping, String currentUserId) {
		analyzerTestMapping.setSysUserId(currentUserId);
		insert(analyzerTestMapping);
	}

	@Override
	public List<AnalyzerTestMapping> getAllAnalyzerTestMappings() {
		return getBaseObjectDAO().getAllAnalyzerTestMappings();
	}
}
