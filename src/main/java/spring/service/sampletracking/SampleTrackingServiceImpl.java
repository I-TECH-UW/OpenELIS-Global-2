package spring.service.sampletracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.sampletracking.dao.SampleTrackingDAO;
import us.mn.state.health.lims.sampletracking.valueholder.SampleTracking;

@Service
public class SampleTrackingServiceImpl extends BaseObjectServiceImpl<SampleTracking, String> implements SampleTrackingService {
	@Autowired
	protected SampleTrackingDAO baseObjectDAO;

	SampleTrackingServiceImpl() {
		super(SampleTracking.class);
	}

	@Override
	protected SampleTrackingDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
