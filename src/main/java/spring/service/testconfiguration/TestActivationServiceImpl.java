package spring.service.testconfiguration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.test.TestService;
import spring.service.typeofsample.TypeOfSampleService;
import spring.util.SpringContext;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

@Service
public class TestActivationServiceImpl implements TestActivationService {

	@Autowired
	private TypeOfSampleService typeOfSampleService;
	@Autowired
	private TestService testService;

	@Override
	@Transactional
	public void updateAll(List<Test> deactivateTests, List<Test> activateTests,
			List<TypeOfSample> deactivateSampleTypes, List<TypeOfSample> activateSampleTypes) {

		for (Test test : deactivateTests) {
			testService.update(test);
		}

		for (Test test : activateTests) {
			testService.update(test);
		}

		for (TypeOfSample typeOfSample : deactivateSampleTypes) {
			typeOfSampleService.update(typeOfSample);
		}

		for (TypeOfSample typeOfSample : activateSampleTypes) {
			typeOfSampleService.update(typeOfSample);
		}

		if (!deactivateSampleTypes.isEmpty() || !activateSampleTypes.isEmpty()) {
			SpringContext.getBean(TypeOfSampleService.class).clearCache();
		}
	}

}
