package spring.service.method;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.method.dao.MethodDAO;
import us.mn.state.health.lims.method.valueholder.Method;

@Service
public class MethodServiceImpl extends BaseObjectServiceImpl<Method> implements MethodService {
	@Autowired
	protected MethodDAO baseObjectDAO;

	MethodServiceImpl() {
		super(Method.class);
	}

	@Override
	protected MethodDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public List getMethods(String filter) {
        return getBaseObjectDAO().getMethods(filter);
	}

	@Override
	public void getData(Method method) {
        getBaseObjectDAO().getData(method);

	}

	@Override
	public void deleteData(List methods) {
        getBaseObjectDAO().deleteData(methods);

	}

	@Override
	public void updateData(Method method) {
        getBaseObjectDAO().updateData(method);

	}

	@Override
	public boolean insertData(Method method) {
        return getBaseObjectDAO().insertData(method);
	}

	@Override
	public List getNextMethodRecord(String id) {
        return getBaseObjectDAO().getNextMethodRecord(id);
	}

	@Override
	public List getPreviousMethodRecord(String id) {
        return getBaseObjectDAO().getPreviousMethodRecord(id);
	}

	@Override
	public Integer getTotalMethodCount() {
        return getBaseObjectDAO().getTotalMethodCount();
	}

	@Override
	public List getAllMethods() {
        return getBaseObjectDAO().getAllMethods();
	}

	@Override
	public Method getMethodByName(Method method) {
        return getBaseObjectDAO().getMethodByName(method);
	}

	@Override
	public List getPageOfMethods(int startingRecNo) {
        return getBaseObjectDAO().getPageOfMethods(startingRecNo);
	}
}
