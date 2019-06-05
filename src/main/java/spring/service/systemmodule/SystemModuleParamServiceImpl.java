package spring.service.systemmodule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.systemmodule.dao.SystemModuleParamDAO;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModuleParam;

@Service
public class SystemModuleParamServiceImpl extends BaseObjectServiceImpl<SystemModuleParam, String> implements SystemModuleParamService {
	@Autowired
	protected SystemModuleParamDAO baseObjectDAO;

	SystemModuleParamServiceImpl() {
		super(SystemModuleParam.class);
	}

	@Override
	protected SystemModuleParamDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
