package spring.service.testanalyte;

import java.lang.String;
import java.util.List;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;

public interface TestAnalyteService extends BaseObjectService<TestAnalyte> {
	TestAnalyte getData(TestAnalyte testAnalyte);

	void deleteData(List testAnalytes);

	void updateData(TestAnalyte testAnalyte);

	boolean insertData(TestAnalyte testAnalyte);

	List getAllTestAnalytes();

	List getPageOfTestAnalytes(int startingRecNo);

	List getNextTestAnalyteRecord(String id);

	List getPreviousTestAnalyteRecord(String id);

	TestAnalyte getTestAnalyteById(TestAnalyte testAnalyte);

	List getAllTestAnalytesPerTest(Test test);

	List getTestAnalytes(String filter);
}
