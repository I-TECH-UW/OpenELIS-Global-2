package spring.service.systemmodule;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.systemmodule.dao.SystemModuleUrlDAO;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModuleUrl;

@Service
public class SystemModuleUrlServiceImpl extends BaseObjectServiceImpl<SystemModuleUrl> implements SystemModuleUrlService {
	@Autowired
	protected SystemModuleUrlDAO baseObjectDAO;

	SystemModuleUrlServiceImpl() {
		super(SystemModuleUrl.class);
	}

	@Override
	protected SystemModuleUrlDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public List<SystemModuleUrl> getByRequest(HttpServletRequest request) {
		return baseObjectDAO.getByRequest(request);
	}

	@Override
	public boolean insertData(SystemModuleUrl systemModuleUrl) {
        return getBaseObjectDAO().insertData(systemModuleUrl);
	}

	@Override
	public List<SystemModuleUrl> getByUrlPath(String urlPath) {
        return getBaseObjectDAO().getByUrlPath(urlPath);
	}
}
