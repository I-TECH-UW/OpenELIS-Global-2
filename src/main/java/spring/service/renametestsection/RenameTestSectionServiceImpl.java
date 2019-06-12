package spring.service.renametestsection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.renametestsection.dao.RenameTestSectionDAO;
import us.mn.state.health.lims.renametestsection.valueholder.RenameTestSection;

@Service
public class RenameTestSectionServiceImpl extends BaseObjectServiceImpl<RenameTestSection, String>
		implements RenameTestSectionService {
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

	@Override
	public RenameTestSection getTestSectionById(String id) {
		return getBaseObjectDAO().getTestSectionById(id);
	}

	@Override
	public String insert(RenameTestSection renameTestSection) {
		if (duplicateTestSectionExists(renameTestSection)) {
			throw new LIMSDuplicateRecordException(
					"Duplicate record exists for " + renameTestSection.getTestSectionName());
		}
		return super.insert(renameTestSection);
	}

	@Override
	public RenameTestSection save(RenameTestSection renameTestSection) {
		if (duplicateTestSectionExists(renameTestSection)) {
			throw new LIMSDuplicateRecordException(
					"Duplicate record exists for " + renameTestSection.getTestSectionName());
		}
		return super.save(renameTestSection);
	}

	@Override
	public RenameTestSection update(RenameTestSection renameTestSection) {
		if (duplicateTestSectionExists(renameTestSection)) {
			throw new LIMSDuplicateRecordException(
					"Duplicate record exists for " + renameTestSection.getTestSectionName());
		}
		return super.update(renameTestSection);
	}

	private boolean duplicateTestSectionExists(RenameTestSection renameTestSection) {
		return baseObjectDAO.duplicateTestSectionExists(renameTestSection);
	}
}
