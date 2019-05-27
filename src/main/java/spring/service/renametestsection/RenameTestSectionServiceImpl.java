package spring.service.renametestsection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.renametestsection.dao.RenameTestSectionDAO;
import us.mn.state.health.lims.renametestsection.valueholder.RenameTestSection;

@Service
public class RenameTestSectionServiceImpl extends BaseObjectServiceImpl<RenameTestSection> implements RenameTestSectionService {
	@Autowired
	protected RenameTestSectionDAO baseObjectDAO;

	RenameTestSectionServiceImpl() {
		super(RenameTestSection.class);
	}

	@Override
	protected RenameTestSectionDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void getData(RenameTestSection testSection) {
        getBaseObjectDAO().getData(testSection);

	}

	@Override
	public void deleteData(List testSections) {
        getBaseObjectDAO().deleteData(testSections);

	}

	@Override
	public void updateData(RenameTestSection testSection) {
        getBaseObjectDAO().updateData(testSection);

	}

	@Override
	public boolean insertData(RenameTestSection testSection) {
        return getBaseObjectDAO().insertData(testSection);
	}

	@Override
	public List getTestSections(String filter) {
        return getBaseObjectDAO().getTestSections(filter);
	}

	@Override
	public RenameTestSection getTestSectionByName(RenameTestSection testSection) {
        return getBaseObjectDAO().getTestSectionByName(testSection);
	}

	@Override
	public List getNextTestSectionRecord(String id) {
        return getBaseObjectDAO().getNextTestSectionRecord(id);
	}

	@Override
	public List getPageOfTestSections(int startingRecNo) {
        return getBaseObjectDAO().getPageOfTestSections(startingRecNo);
	}

	@Override
	public Integer getTotalTestSectionCount() {
        return getBaseObjectDAO().getTotalTestSectionCount();
	}

	@Override
	public List getPreviousTestSectionRecord(String id) {
        return getBaseObjectDAO().getPreviousTestSectionRecord(id);
	}

	@Override
	public List getAllTestSections() {
        return getBaseObjectDAO().getAllTestSections();
	}
}
