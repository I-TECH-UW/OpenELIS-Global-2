package spring.service.datasubmission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.datasubmission.dao.DataIndicatorDAO;
import us.mn.state.health.lims.datasubmission.valueholder.DataIndicator;
import us.mn.state.health.lims.datasubmission.valueholder.TypeOfDataIndicator;

@Service
public class DataIndicatorServiceImpl extends BaseObjectServiceImpl<DataIndicator> implements DataIndicatorService {
	@Autowired
	protected DataIndicatorDAO baseObjectDAO;

	DataIndicatorServiceImpl() {
		super(DataIndicator.class);
	}

	@Override
	protected DataIndicatorDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void getData(DataIndicator indicator) {
        getBaseObjectDAO().getData(indicator);

	}

	@Override
	public void updateData(DataIndicator dataIndicator) {
        getBaseObjectDAO().updateData(dataIndicator);

	}

	@Override
	public boolean insertData(DataIndicator dataIndicator) {
        return getBaseObjectDAO().insertData(dataIndicator);
	}

	@Override
	public DataIndicator getIndicator(String id) {
        return getBaseObjectDAO().getIndicator(id);
	}

	@Override
	public DataIndicator getIndicatorByTypeYearMonth(TypeOfDataIndicator type, int year, int month) {
        return getBaseObjectDAO().getIndicatorByTypeYearMonth(type,year,month);
	}

	@Override
	public List<DataIndicator> getIndicatorsByStatus(String status) {
        return getBaseObjectDAO().getIndicatorsByStatus(status);
	}
}
