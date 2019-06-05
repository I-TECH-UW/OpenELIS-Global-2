package spring.service.county;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.county.dao.CountyDAO;
import us.mn.state.health.lims.county.valueholder.County;

@Service
public class CountyServiceImpl extends BaseObjectServiceImpl<County, String> implements CountyService {
	@Autowired
	protected CountyDAO baseObjectDAO;

	CountyServiceImpl() {
		super(County.class);
	}

	@Override
	protected CountyDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
