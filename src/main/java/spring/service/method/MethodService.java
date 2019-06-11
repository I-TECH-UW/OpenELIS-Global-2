package spring.service.method;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.method.valueholder.Method;

public interface MethodService extends BaseObjectService<Method, String> {
	List getMethods(String filter);

}
