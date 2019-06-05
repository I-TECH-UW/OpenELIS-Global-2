package spring.service.samplehuman;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.provider.valueholder.Provider;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.valueholder.SampleHuman;

@Service
public class SampleHumanServiceImpl extends BaseObjectServiceImpl<SampleHuman, String> implements SampleHumanService {
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
		return getMatch("sampleId", sampleHuman.getSampleId()).orElse(null);
	}

	@Override
	@Transactional
	public Patient getPatientForSample(Sample sample) {
		return baseObjectDAO.getPatientForSample(sample);
	}

	@Override
	public void getData(SampleHuman sampleHuman) {
		getBaseObjectDAO().getData(sampleHuman);

	}

	@Override
	public void deleteData(List sampleHumans) {
		getBaseObjectDAO().deleteData(sampleHumans);

	}

	@Override
	public void updateData(SampleHuman sampleHuman) {
		getBaseObjectDAO().updateData(sampleHuman);

	}

	@Override
	public boolean insertData(SampleHuman sampleHuman) {
		return getBaseObjectDAO().insertData(sampleHuman);
	}

	@Override
	public Provider getProviderForSample(Sample sample) {
		return getBaseObjectDAO().getProviderForSample(sample);
	}

	@Override
	public List<Sample> getSamplesForPatient(String patientID) {
		return getBaseObjectDAO().getSamplesForPatient(patientID);
	}

}
