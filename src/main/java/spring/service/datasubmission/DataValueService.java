package spring.service.datasubmission;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.datasubmission.valueholder.DataValue;

public interface DataValueService extends BaseObjectService<DataValue, String> {
	void getData(DataValue dataValue);

	void updateData(DataValue dataValue);

	boolean insertData(DataValue dataValue);

	DataValue getDataValue(String id);
}
