package spring.service.sample;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.sample.valueholder.Sample;

public interface SampleService extends BaseObjectService<Sample> {

	Sample getSampleByAccessionNumber(String labNumber);

	void insertDataWithAccessionNumber(Sample sample);

	List<Sample> getSamplesReceivedOn(String recievedDate);

	List<Sample> getSamplesForPatient(String patientID);
}
