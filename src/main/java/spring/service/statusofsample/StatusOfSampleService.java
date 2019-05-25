package spring.service.statusofsample;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.statusofsample.valueholder.StatusOfSample;

public interface StatusOfSampleService extends BaseObjectService<StatusOfSample> {
	void getData(StatusOfSample sourceOfSample);

	void updateData(StatusOfSample sourceOfSample);

	boolean insertData(StatusOfSample sourceOfSample);

	List getPreviousStatusOfSampleRecord(String id);

	List getPageOfStatusOfSamples(int startingRecNo);

	Integer getTotalStatusOfSampleCount();

	StatusOfSample getDataByStatusTypeAndStatusCode(StatusOfSample statusofsample);

	List getAllStatusOfSamples();

	List getNextStatusOfSampleRecord(String id);
}
