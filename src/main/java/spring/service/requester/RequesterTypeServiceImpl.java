package spring.service.requester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.requester.dao.RequesterTypeDAO;
import us.mn.state.health.lims.requester.valueholder.RequesterType;

@Service
public class RequesterTypeServiceImpl extends BaseObjectServiceImpl<RequesterType, String> implements RequesterTypeService {
	@Autowired
	protected RequesterTypeDAO baseObjectDAO;

	public RequesterTypeServiceImpl() {
		super(RequesterType.class);
	}

	@Override
	protected RequesterTypeDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public RequesterType getRequesterTypeByName(String typeName) {
		return getBaseObjectDAO().getRequesterTypeByName(typeName);
	}
}
