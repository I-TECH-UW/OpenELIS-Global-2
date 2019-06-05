package spring.service.systemusersection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.systemusersection.dao.SystemUserSectionDAO;
import us.mn.state.health.lims.systemusersection.valueholder.SystemUserSection;

@Service
public class SystemUserSectionServiceImpl extends BaseObjectServiceImpl<SystemUserSection, String> implements SystemUserSectionService {
	@Autowired
	protected SystemUserSectionDAO baseObjectDAO;

	SystemUserSectionServiceImpl() {
		super(SystemUserSection.class);
	}

	@Override
	protected SystemUserSectionDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void getData(SystemUserSection systemUserSection) {
        getBaseObjectDAO().getData(systemUserSection);

	}

	@Override
	public void deleteData(List systemUserSection) {
        getBaseObjectDAO().deleteData(systemUserSection);

	}

	@Override
	public void updateData(SystemUserSection systemUserSection) {
        getBaseObjectDAO().updateData(systemUserSection);

	}

	@Override
	public boolean insertData(SystemUserSection systemUserSection) {
        return getBaseObjectDAO().insertData(systemUserSection);
	}

	@Override
	public List getAllSystemUserSections() {
        return getBaseObjectDAO().getAllSystemUserSections();
	}

	@Override
	public List getPageOfSystemUserSections(int startingRecNo) {
        return getBaseObjectDAO().getPageOfSystemUserSections(startingRecNo);
	}

	@Override
	public List getNextSystemUserSectionRecord(String id) {
        return getBaseObjectDAO().getNextSystemUserSectionRecord(id);
	}

	@Override
	public Integer getTotalSystemUserSectionCount() {
        return getBaseObjectDAO().getTotalSystemUserSectionCount();
	}

	@Override
	public List getPreviousSystemUserSectionRecord(String id) {
        return getBaseObjectDAO().getPreviousSystemUserSectionRecord(id);
	}

	@Override
	public List getAllSystemUserSectionsBySystemUserId(int systemUserId) {
        return getBaseObjectDAO().getAllSystemUserSectionsBySystemUserId(systemUserId);
	}
}
