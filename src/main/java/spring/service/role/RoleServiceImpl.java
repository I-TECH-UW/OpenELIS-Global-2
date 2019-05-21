package spring.service.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.role.dao.RoleDAO;
import us.mn.state.health.lims.role.valueholder.Role;

@Service
public class RoleServiceImpl extends BaseObjectServiceImpl<Role> implements RoleService {
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
}
