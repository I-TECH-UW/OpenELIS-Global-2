package spring.service.samplehuman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.valueholder.SampleHuman;

@Service
public class SampleHumanServiceImpl extends BaseObjectServiceImpl<SampleHuman> implements SampleHumanService {
	@Autowired
	protected SampleHumanDAO baseObjectDAO;

	SampleHumanServiceImpl() {
		super(SampleHuman.class);
	}

	@Override
	protected SampleHumanDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional
	public SampleHuman getDataBySample(SampleHuman sampleHuman) {
		return getMatch("sampleId", sampleHuman.getSampleId()).get();
	}

	@Override
	@Transactional
	public Patient getPatientForSample(Sample sample) {
		return baseObjectDAO.getPatientForSample(sample);
	}
}
