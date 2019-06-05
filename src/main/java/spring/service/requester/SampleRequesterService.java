package spring.service.requester;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.requester.valueholder.SampleRequester;

public interface SampleRequesterService extends BaseObjectService<SampleRequester, String> {

	void delete(SampleRequester sampleRequester);

	void updateData(SampleRequester sampleRequester);

	boolean insertData(SampleRequester sampleRequester);

	void insertOrUpdateData(SampleRequester sampleRequester);

	List<SampleRequester> getRequestersForSampleId(String sampleId);
}
