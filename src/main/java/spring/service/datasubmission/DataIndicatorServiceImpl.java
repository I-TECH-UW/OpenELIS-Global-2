package spring.service.datasubmission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.datasubmission.dao.DataIndicatorDAO;
import us.mn.state.health.lims.datasubmission.valueholder.DataIndicator;
import us.mn.state.health.lims.datasubmission.valueholder.TypeOfDataIndicator;

@Service
public class DataIndicatorServiceImpl extends BaseObjectServiceImpl<DataIndicator, String>
		implements DataIndicatorService {
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
	public DataIndicator getIndicatorByTypeYearMonth(TypeOfDataIndicator type, int year, int month) {
		Map<String, Object> properties = new HashMap<>();
		properties.put("typeOfDataIndicator.id", type.getId());
		properties.put("year", year);
		properties.put("month", month);
		return getMatch(properties).orElse(null);
	}

	@Override
	public List<DataIndicator> getIndicatorsByStatus(String status) {
		return getBaseObjectDAO().getAllMatching("status", status);
	}
}
