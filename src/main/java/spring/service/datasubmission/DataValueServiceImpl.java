package spring.service.datasubmission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.datasubmission.dao.DataValueDAO;
import us.mn.state.health.lims.datasubmission.valueholder.DataValue;

@Service
public class DataValueServiceImpl extends BaseObjectServiceImpl<DataValue, String> implements DataValueService {
	@Autowired
	protected DataValueDAO baseObjectDAO;

	DataValueServiceImpl() {
		super(DataValue.class);
	}

	@Override
	protected DataValueDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void getData(DataValue dataValue) {
        getBaseObjectDAO().getData(dataValue);

	}

	@Override
	public void updateData(DataValue dataValue) {
        getBaseObjectDAO().updateData(dataValue);

	}

	@Override
	public boolean insertData(DataValue dataValue) {
        return getBaseObjectDAO().insertData(dataValue);
	}

	@Override
	public DataValue getDataValue(String id) {
        return getBaseObjectDAO().getDataValue(id);
	}
}
