package spring.service.typeofsample;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSampleTest;

public interface TypeOfSampleTestService extends BaseObjectService<TypeOfSampleTest, String> {
	void getData(TypeOfSampleTest typeOfSampleTest);

	List<TypeOfSampleTest> getTypeOfSampleTestsForTest(String testId);

	List getPageOfTypeOfSampleTests(int startingRecNo);

	List getNextTypeOfSampleTestRecord(String id);

	List getAllTypeOfSampleTests();

	Integer getTotalTypeOfSampleTestCount();

	TypeOfSampleTest getTypeOfSampleTestForTest(String testId);

	List getPreviousTypeOfSampleRecord(String id);

	List<TypeOfSampleTest> getTypeOfSampleTestsForSampleType(String sampleTypeId);
}
