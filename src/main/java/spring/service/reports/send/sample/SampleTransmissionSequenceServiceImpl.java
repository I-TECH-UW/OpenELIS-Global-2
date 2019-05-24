package spring.service.reports.send.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.reports.send.sample.dao.SampleTransmissionSequenceDAO;
import us.mn.state.health.lims.reports.send.sample.valueholder.SampleTransmissionSequence;

@Service
public class SampleTransmissionSequenceServiceImpl extends BaseObjectServiceImpl<SampleTransmissionSequence> implements SampleTransmissionSequenceService {
	@Autowired
	protected SampleTransmissionSequenceDAO baseObjectDAO;

	SampleTransmissionSequenceServiceImpl() {
		super(SampleTransmissionSequence.class);
	}

	@Override
	protected SampleTransmissionSequenceDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
