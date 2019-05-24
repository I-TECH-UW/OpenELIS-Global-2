package spring.service.samplehuman;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.provider.valueholder.Provider;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.valueholder.SampleHuman;

public interface SampleHumanService extends BaseObjectService<SampleHuman> {
	void getData(SampleHuman sampleHuman);

	void deleteData(List sampleHumans);

	void updateData(SampleHuman sampleHuman);

	boolean insertData(SampleHuman sampleHuman);

	Provider getProviderForSample(Sample sample);

	Patient getPatientForSample(Sample sample);

	List<Sample> getSamplesForPatient(String patientID);

	SampleHuman getDataBySample(SampleHuman sampleHuman);
}
