package spring.service.samplehuman;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.valueholder.SampleHuman;

public interface SampleHumanService extends BaseObjectService<SampleHuman> {

	SampleHuman getDataBySample(SampleHuman sampleHuman);

	Patient getPatientForSample(Sample sample);
}
