package spring.service.analyzerimport;

import java.util.ArrayList;

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
		defaultSortOrder = new ArrayList<>();
	}

	@Override
	protected AnalyzerTestMappingDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

}
