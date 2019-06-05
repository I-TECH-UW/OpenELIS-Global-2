package spring.service.region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.region.dao.RegionDAO;
import us.mn.state.health.lims.region.valueholder.Region;

@Service
public class RegionServiceImpl extends BaseObjectServiceImpl<Region, String> implements RegionService {
	@Autowired
	protected RegionDAO baseObjectDAO;

	RegionServiceImpl() {
		super(Region.class);
	}

	@Override
	protected RegionDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
