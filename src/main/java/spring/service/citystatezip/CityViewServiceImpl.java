package spring.service.citystatezip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.citystatezip.dao.CityViewDAO;
import us.mn.state.health.lims.citystatezip.valueholder.CityView;

@Service
public class CityViewServiceImpl extends BaseObjectServiceImpl<CityView> implements CityViewService {
	@Autowired
	protected CityViewDAO baseObjectDAO;

	CityViewServiceImpl() {
		super(CityView.class);
	}

	@Override
	protected CityViewDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
