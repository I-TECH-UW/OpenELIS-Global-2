package spring.service.testconfiguration;

import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

public interface SampleTypeTestAssignService {

	void update(TypeOfSample typeOfSample, String testId, String typeOfSamplesTestID, String sampleTypeId,
			boolean deleteExistingTypeOfSampleTest, boolean updateTypeOfSample, TypeOfSample deActivateTypeOfSample,
			String systemUserId);

}
