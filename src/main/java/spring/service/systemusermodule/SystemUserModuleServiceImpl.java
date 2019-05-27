package spring.service.systemusermodule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.systemusermodule.dao.SystemUserModuleDAO;
import us.mn.state.health.lims.systemusermodule.valueholder.SystemUserModule;

@Service
public class SystemUserModuleServiceImpl extends BaseObjectServiceImpl<SystemUserModule>
		implements SystemUserModuleService {

	@Autowired
	SystemUserModuleDAO baseObjectDAO;

	public SystemUserModuleServiceImpl() {
		super(SystemUserModule.class);
	}

	@Override
	protected BaseDAO<SystemUserModule> getBaseObjectDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getData(SystemUserModule systemUserModule) {
		baseObjectDAO.getData(systemUserModule);
	}

	@Override
	public void deleteData(List systemUserModules) {
		baseObjectDAO.deleteData(systemUserModules);
	}

	@Override
	public void updateData(SystemUserModule systemUserModule) {
		baseObjectDAO.updateData(systemUserModule);
	}

	@Override
	public boolean insertData(SystemUserModule systemUserModule) {
		return baseObjectDAO.insertData(systemUserModule);
	}

	@Override
	public List getAllPermissionModules() {
		return baseObjectDAO.getAllPermissionModules();
	}

	@Override
	public Integer getTotalPermissionModuleCount() {
		return baseObjectDAO.getTotalPermissionModuleCount();
	}

	@Override
	public List getPageOfPermissionModules(int startingRecNo) {
		return baseObjectDAO.getPageOfPermissionModules(startingRecNo);
	}

	@Override
	public boolean isAgentAllowedAccordingToName(String id, String string) {
		return baseObjectDAO.isAgentAllowedAccordingToName(id, string);
	}

	@Override
	public List getNextPermissionModuleRecord(String id) {
		return baseObjectDAO.getNextPermissionModuleRecord(id);
	}

	@Override
	public List getAllPermissionModulesByAgentId(int systemUserId) {
		return baseObjectDAO.getAllPermissionModulesByAgentId(systemUserId);
	}

	@Override
	public boolean doesUserHaveAnyModules(int userId) {
		return baseObjectDAO.doesUserHaveAnyModules(userId);
	}

	@Override
	public List getPreviousPermissionModuleRecord(String id) {
		return baseObjectDAO.getPreviousPermissionModuleRecord(id);
	}

}
