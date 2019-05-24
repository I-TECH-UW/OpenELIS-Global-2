package spring.service.datasubmission;

import java.lang.String;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.datasubmission.valueholder.DataValue;

public interface DataValueService extends BaseObjectService<DataValue> {
	void getData(DataValue dataValue);

	void updateData(DataValue dataValue);

	boolean insertData(DataValue dataValue);

	DataValue getDataValue(String id);
}
