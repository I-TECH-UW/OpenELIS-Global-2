package spring.service.requester;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.requester.dao.SampleRequesterDAO;
import us.mn.state.health.lims.requester.valueholder.SampleRequester;

@Service
public class SampleRequesterServiceImpl extends BaseObjectServiceImpl<SampleRequester>
		implements SampleRequesterService {
	@Autowired
	protected SampleRequesterDAO baseObjectDAO;

	SampleRequesterServiceImpl() {
		super(SampleRequester.class);
	}

	@Override
	protected SampleRequesterDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional
	public List<SampleRequester> getRequestersForSampleId(String id) {
		return baseObjectDAO.getAllMatching("sampleId", id);
	}
}
