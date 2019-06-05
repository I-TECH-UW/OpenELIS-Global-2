package spring.service.testcodes;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.testcodes.valueholder.TestCodeType;

public interface TestCodeTypeService extends BaseObjectService<TestCodeType, String> {
	TestCodeType getTestCodeTypeById(String id);

	TestCodeType getTestCodeTypeByName(String name);
}
