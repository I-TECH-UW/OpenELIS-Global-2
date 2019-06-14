package spring.service.method;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.method.dao.MethodDAO;
import us.mn.state.health.lims.method.valueholder.Method;

@Service
public class MethodServiceImpl extends BaseObjectServiceImpl<Method, String> implements MethodService {
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
	@Transactional(readOnly = true)
	public List getMethods(String filter) {
		return getBaseObjectDAO().getMethods(filter);
	}

	@Override
	public void delete(Method method) {
		Method oldMethod = get(method.getId());
		oldMethod.setIsActive(IActionConstants.NO);
		oldMethod.setSysUserId(method.getSysUserId());
		updateDelete(oldMethod);
	}

	@Override
	public String insert(Method method) {
		if (getBaseObjectDAO().duplicateMethodExists(method)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + method.getMethodName());
		}
		return super.insert(method);
	}

	@Override
	public Method save(Method method) {
		if (getBaseObjectDAO().duplicateMethodExists(method)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + method.getMethodName());
		}
		return super.save(method);
	}

	@Override
	public Method update(Method method) {
		if (getBaseObjectDAO().duplicateMethodExists(method)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + method.getMethodName());
		}
		return super.update(method);
	}

}
