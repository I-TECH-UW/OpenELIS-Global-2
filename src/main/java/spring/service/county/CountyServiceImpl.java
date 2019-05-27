package spring.service.county;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.county.valueholder.County;

@Service
public class CountyServiceImpl extends BaseObjectServiceImpl<County> implements CountyService {
	@Autowired
	protected BaseDAO<County> baseObjectDAO;

	CountyServiceImpl() {
		super(County.class);
	}

	@Override
	protected BaseDAO<County> getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
