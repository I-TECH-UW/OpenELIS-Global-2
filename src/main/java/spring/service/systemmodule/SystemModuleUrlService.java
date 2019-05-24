package spring.service.systemmodule;

import java.lang.String;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModuleUrl;

public interface SystemModuleUrlService extends BaseObjectService<SystemModuleUrl> {
	boolean insertData(SystemModuleUrl systemModuleUrl);

	List<SystemModuleUrl> getByUrlPath(String urlPath);

	List<SystemModuleUrl> getByRequest(HttpServletRequest request);
}
