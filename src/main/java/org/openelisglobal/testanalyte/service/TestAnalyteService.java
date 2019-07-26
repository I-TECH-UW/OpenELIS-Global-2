package org.openelisglobal.testanalyte.service;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.test.valueholder.Test;
import org.openelisglobal.testanalyte.valueholder.TestAnalyte;

public interface TestAnalyteService extends BaseObjectService<TestAnalyte, String> {
	TestAnalyte getData(TestAnalyte testAnalyte);

	List getAllTestAnalytes();

	List getPageOfTestAnalytes(int startingRecNo);

	List getNextTestAnalyteRecord(String id);

	List getPreviousTestAnalyteRecord(String id);

	TestAnalyte getTestAnalyteById(TestAnalyte testAnalyte);

	List getAllTestAnalytesPerTest(Test test);

	List getTestAnalytes(String filter);
}
