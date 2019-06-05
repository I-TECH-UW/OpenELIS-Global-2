package spring.service.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.role.dao.RoleDAO;
import us.mn.state.health.lims.role.valueholder.Role;

@Service
public class RoleServiceImpl extends BaseObjectServiceImpl<Role, String> implements RoleService {
	@Autowired
	protected RoleDAO baseObjectDAO;

	RoleServiceImpl() {
		super(Role.class);
	}

	@Override
	protected RoleDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional
	public List<Role> getAllActiveRoles() {
		return baseObjectDAO.getAllMatching("active", true);
	}

	@Override
	public void getData(Role role) {
		getBaseObjectDAO().getData(role);

	}

	@Override
	public void deleteData(List<Role> roles) {
		getBaseObjectDAO().deleteData(roles);

	}

	@Override
	public void updateData(Role role) {
		getBaseObjectDAO().updateData(role);

	}

	@Override
	public boolean insertData(Role role) {
		return getBaseObjectDAO().insertData(role);
	}

	@Override
	public List getNextRoleRecord(String id) {
		return getBaseObjectDAO().getNextRoleRecord(id);
	}

	@Override
	public List getPreviousRoleRecord(String id) {
		return getBaseObjectDAO().getPreviousRoleRecord(id);
	}

	@Override
	public List<Role> getReferencingRoles(Role role) {
		return getBaseObjectDAO().getReferencingRoles(role);
	}

	@Override
	public List<Role> getPageOfRoles(int startingRecNo) {
		return getBaseObjectDAO().getPageOfRoles(startingRecNo);
	}

	@Override
	public Role getRoleByName(String name) {
		return getBaseObjectDAO().getRoleByName(name);
	}

	@Override
	public List<Role> getAllRoles() {
		return getBaseObjectDAO().getAllRoles();
	}

	@Override
	public Role getRoleById(String roleId) {
		return getBaseObjectDAO().getRoleById(roleId);

	}
}
