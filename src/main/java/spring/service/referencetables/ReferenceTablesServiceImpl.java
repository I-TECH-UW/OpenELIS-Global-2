package spring.service.referencetables;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.referencetables.dao.ReferenceTablesDAO;
import us.mn.state.health.lims.referencetables.valueholder.ReferenceTables;

@Service
public class ReferenceTablesServiceImpl extends BaseObjectServiceImpl<ReferenceTables, String>
		implements ReferenceTablesService {
	@Autowired
	protected ReferenceTablesDAO baseObjectDAO;

	ReferenceTablesServiceImpl() {
		super(ReferenceTables.class);
	}

	@Override
	protected ReferenceTablesDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional
	public ReferenceTables getReferenceTableByName(String tableName) {
		return baseObjectDAO.getReferenceTableByName(tableName);
	}

	@Override
	public void getData(ReferenceTables referenceTables) {
		getBaseObjectDAO().getData(referenceTables);

	}

	@Override
	public List getAllReferenceTablesForHl7Encoding() {
		return getBaseObjectDAO().getAllReferenceTablesForHl7Encoding();
	}

	@Override
	public List getAllReferenceTables() {
		return getBaseObjectDAO().getAllReferenceTables();
	}

	@Override
	public ReferenceTables getReferenceTableByName(ReferenceTables referenceTables) {
		return getBaseObjectDAO().getReferenceTableByName(referenceTables);
	}

	@Override
	public Integer getTotalReferenceTableCount() {
		return getBaseObjectDAO().getTotalReferenceTableCount();
	}

	@Override
	public List getPreviousReferenceTablesRecord(String id) {
		return getBaseObjectDAO().getPreviousReferenceTablesRecord(id);
	}

	@Override
	public List getPageOfReferenceTables(int startingRecNo) {
		return getBaseObjectDAO().getPageOfReferenceTables(startingRecNo);
	}

	@Override
	public List getNextReferenceTablesRecord(String id) {
		return getBaseObjectDAO().getNextReferenceTablesRecord(id);
	}

	@Override
	public Integer getTotalReferenceTablesCount() {
		return getBaseObjectDAO().getTotalReferenceTablesCount();
	}

	@Override
	public String insert(ReferenceTables referenceTables) {
		if (duplicateReferenceTablesExists(referenceTables, true)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + referenceTables.getTableName());
		}
		return super.insert(referenceTables);
	}

	@Override
	public ReferenceTables save(ReferenceTables referenceTables) {
		if (duplicateReferenceTablesExists(referenceTables, false)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + referenceTables.getTableName());
		}
		return super.save(referenceTables);
	}

	@Override
	public ReferenceTables update(ReferenceTables referenceTables) {
		if (duplicateReferenceTablesExists(referenceTables, false)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + referenceTables.getTableName());
		}
		return super.update(referenceTables);
	}

	private boolean duplicateReferenceTablesExists(ReferenceTables referenceTables, boolean isNew) {
		return baseObjectDAO.duplicateReferenceTablesExists(referenceTables, isNew);
	}
}
