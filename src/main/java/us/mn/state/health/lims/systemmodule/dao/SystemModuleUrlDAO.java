package us.mn.state.health.lims.systemmodule.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModuleUrl;

public interface SystemModuleUrlDAO extends BaseDAO<SystemModuleUrl, String> {

	public List<SystemModuleUrl> getByRequest(HttpServletRequest request);

	public List<SystemModuleUrl> getByUrlPath(String urlPath);

}
