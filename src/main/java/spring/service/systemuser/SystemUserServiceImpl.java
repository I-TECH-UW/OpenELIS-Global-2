package spring.service.systemuser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.systemuser.dao.SystemUserDAO;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;

@Service
public class SystemUserServiceImpl extends BaseObjectServiceImpl<SystemUser, String> implements SystemUserService {
	@Autowired
	protected SystemUserDAO baseObjectDAO;

	SystemUserServiceImpl() {
		super(SystemUser.class);
	}

	@Override
	protected SystemUserDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional
	public void delete(SystemUser systemUser) {
		SystemUser oldData = get(systemUser.getId());
		oldData.setIsActive(IActionConstants.NO);
		updateDelete(oldData);
	}

	@Override
	public void getData(SystemUser systemUser) {
		getBaseObjectDAO().getData(systemUser);

	}

	@Override
	public List getPageOfSystemUsers(int startingRecNo) {
		return getBaseObjectDAO().getPageOfSystemUsers(startingRecNo);
	}

	@Override
	public List getAllSystemUsers() {
		return getBaseObjectDAO().getAllSystemUsers();
	}

	@Override
	public List getNextSystemUserRecord(String id) {
		return getBaseObjectDAO().getNextSystemUserRecord(id);
	}

	@Override
	public List getPreviousSystemUserRecord(String id) {
		return getBaseObjectDAO().getPreviousSystemUserRecord(id);
	}

	@Override
	public Integer getTotalSystemUserCount() {
		return getBaseObjectDAO().getTotalSystemUserCount();
	}

	@Override
	public SystemUser getDataForLoginUser(String name) {
		return getBaseObjectDAO().getDataForLoginUser(name);
	}

	@Override
	public SystemUser getUserById(String userId) {
		return getBaseObjectDAO().getUserById(userId);
	}

	@Override
	public String insert(SystemUser systemUser) {
		if (baseObjectDAO.duplicateSystemUserExists(systemUser)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + systemUser.getFirstName()
					+ IActionConstants.BLANK + systemUser.getFirstName());
		}
		return super.insert(systemUser);
	}

	@Override
	public SystemUser save(SystemUser systemUser) {
		if (baseObjectDAO.duplicateSystemUserExists(systemUser)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + systemUser.getFirstName()
					+ IActionConstants.BLANK + systemUser.getFirstName());
		}
		return super.save(systemUser);
	}

	@Override
	public SystemUser update(SystemUser systemUser) {
		if (baseObjectDAO.duplicateSystemUserExists(systemUser)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + systemUser.getFirstName()
					+ IActionConstants.BLANK + systemUser.getFirstName());
		}
		return super.update(systemUser);
	}

}
