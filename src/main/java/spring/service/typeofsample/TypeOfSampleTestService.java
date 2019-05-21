package spring.service.typeofsample;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSampleTest;

public interface TypeOfSampleTestService extends BaseObjectService<TypeOfSampleTest> {

	TypeOfSampleTest getTypeOfSampleTestForTest(String testId);

	List<TypeOfSampleTest> getTypeOfSampleTestsForTest(String id);

	List<TypeOfSampleTest> getTypeOfSampleTestsForSampleType(String id);
}
