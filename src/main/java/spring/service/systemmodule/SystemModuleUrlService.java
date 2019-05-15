package spring.service.systemmodule;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModuleUrl;

public interface SystemModuleUrlService extends BaseObjectService<SystemModuleUrl> {

	List<SystemModuleUrl> getByRequest(HttpServletRequest request);
}
