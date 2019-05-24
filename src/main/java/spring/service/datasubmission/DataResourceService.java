package spring.service.datasubmission;

import java.lang.String;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.datasubmission.valueholder.DataResource;

public interface DataResourceService extends BaseObjectService<DataResource> {
	void getData(DataResource resource);

	void updateData(DataResource resource);

	boolean insertData(DataResource resource);

	DataResource getDataResource(String id);
}
