package spring.service.systemmodule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.systemmodule.dao.SystemModuleDAO;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModule;

@Service
public class SystemModuleServiceImpl extends BaseObjectServiceImpl<SystemModule, String>
		implements SystemModuleService {
	@Autowired
	protected SystemModuleDAO baseObjectDAO;

	SystemModuleServiceImpl() {
		super(SystemModule.class);
	}

	@Override
	protected SystemModuleDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void getData(SystemModule systemModule) {
		getBaseObjectDAO().getData(systemModule);

	}

	@Override
	public Integer getTotalSystemModuleCount() {
		return getBaseObjectDAO().getTotalSystemModuleCount();
	}

	@Override
	public List getPageOfSystemModules(int startingRecNo) {
		return getBaseObjectDAO().getPageOfSystemModules(startingRecNo);
	}

	@Override
	public List getNextSystemModuleRecord(String id) {
		return getBaseObjectDAO().getNextSystemModuleRecord(id);
	}

	@Override
	public List getAllSystemModules() {
		return getBaseObjectDAO().getAllSystemModules();
	}

	@Override
	public List getPreviousSystemModuleRecord(String id) {
		return getBaseObjectDAO().getPreviousSystemModuleRecord(id);
	}

	@Override
	public SystemModule getSystemModuleByName(String name) {
		return getBaseObjectDAO().getSystemModuleByName(name);
	}

	@Override
	public String insert(SystemModule systemModule) {
		if (getBaseObjectDAO().duplicateSystemModuleExists(systemModule)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + systemModule.getSystemModuleName());
		}
		return super.insert(systemModule);
	}

	@Override
	public SystemModule save(SystemModule systemModule) {
		if (getBaseObjectDAO().duplicateSystemModuleExists(systemModule)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + systemModule.getSystemModuleName());
		}
		return super.save(systemModule);
	}

	@Override
	public SystemModule update(SystemModule systemModule) {
		if (getBaseObjectDAO().duplicateSystemModuleExists(systemModule)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + systemModule.getSystemModuleName());
		}
		return super.update(systemModule);
	}
}
