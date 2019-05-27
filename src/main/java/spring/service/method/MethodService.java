package spring.service.method;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.method.valueholder.Method;

public interface MethodService extends BaseObjectService<Method> {
	List getMethods(String filter);

	void getData(Method method);

	void deleteData(List methods);

	void updateData(Method method);

	boolean insertData(Method method);

	List getNextMethodRecord(String id);

	List getPreviousMethodRecord(String id);

	Integer getTotalMethodCount();

	List getAllMethods();

	Method getMethodByName(Method method);

	List getPageOfMethods(int startingRecNo);
}
