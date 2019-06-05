package spring.service.testtrailer;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.testtrailer.valueholder.TestTrailer;

public interface TestTrailerService extends BaseObjectService<TestTrailer, String> {
	void getData(TestTrailer testTrailer);

	void deleteData(List testTrailers);

	void updateData(TestTrailer testTrailer);

	boolean insertData(TestTrailer testTrailer);

	List getPageOfTestTrailers(int startingRecNo);

	List getNextTestTrailerRecord(String id);

	Integer getTotalTestTrailerCount();

	TestTrailer getTestTrailerByName(TestTrailer testTrailer);

	List getPreviousTestTrailerRecord(String id);

	List getAllTestTrailers();

	List getTestTrailers(String filter);
}
