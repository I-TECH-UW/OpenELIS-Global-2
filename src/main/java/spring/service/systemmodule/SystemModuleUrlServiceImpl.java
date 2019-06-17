package spring.service.systemmodule;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.systemmodule.dao.SystemModuleUrlDAO;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModuleUrl;

@Service
public class SystemModuleUrlServiceImpl extends BaseObjectServiceImpl<SystemModuleUrl, String>
		implements SystemModuleUrlService {
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
	@Transactional(readOnly = true)
	public List<SystemModuleUrl> getByRequest(HttpServletRequest request) {
		return baseObjectDAO.getByRequest(request);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SystemModuleUrl> getByUrlPath(String urlPath) {
		return getBaseObjectDAO().getByUrlPath(urlPath);
	}
}
