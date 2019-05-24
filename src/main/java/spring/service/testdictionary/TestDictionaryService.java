package spring.service.testdictionary;

import java.lang.String;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.testdictionary.valueholder.TestDictionary;

public interface TestDictionaryService extends BaseObjectService<TestDictionary> {
	TestDictionary getTestDictionaryForTestId(String testId);
}
