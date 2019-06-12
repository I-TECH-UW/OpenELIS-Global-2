package spring.service.testconfiguration;

import java.util.List;

import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

public interface TestActivationService {

	void updateAll(List<Test> deactivateTests, List<Test> activateTests, List<TypeOfSample> deactivateSampleTypes,
			List<TypeOfSample> activateSampleTypes);

}
