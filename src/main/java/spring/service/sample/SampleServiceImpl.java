package spring.service.sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;

@Service
public class SampleServiceImpl extends BaseObjectServiceImpl<Sample> implements SampleService {
	@Autowired
	protected SampleDAO baseObjectDAO;
	@Autowired
	protected SampleHumanDAO sampleHumanDAO;

	SampleServiceImpl() {
		super(Sample.class);
	}

	@Override
	protected SampleDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional
	public Sample getSampleByAccessionNumber(String labNumber) {
		return getMatch("accessionNumber", labNumber).get();
	}

	@Override
	@Transactional
	public void insertDataWithAccessionNumber(Sample sample) {
		insert(sample);
	}

	@Override
	@Transactional
	public List<Sample> getSamplesReceivedOn(String recievedDate) {
		return baseObjectDAO.getSamplesReceivedOn(recievedDate);
	}

	@Override
	@Transactional
	public List<Sample> getSamplesForPatient(String patientID) {
		return sampleHumanDAO.getSamplesForPatient(patientID);
	}
}
