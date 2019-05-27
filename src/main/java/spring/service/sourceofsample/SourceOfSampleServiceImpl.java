package spring.service.sourceofsample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.sourceofsample.dao.SourceOfSampleDAO;
import us.mn.state.health.lims.sourceofsample.valueholder.SourceOfSample;

@Service
public class SourceOfSampleServiceImpl extends BaseObjectServiceImpl<SourceOfSample> implements SourceOfSampleService {
	@Autowired
	protected SourceOfSampleDAO baseObjectDAO;

	SourceOfSampleServiceImpl() {
		super(SourceOfSample.class);
	}

	@Override
	protected SourceOfSampleDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
