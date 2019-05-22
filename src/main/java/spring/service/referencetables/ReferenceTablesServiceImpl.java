package spring.service.referencetables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.referencetables.dao.ReferenceTablesDAO;
import us.mn.state.health.lims.referencetables.valueholder.ReferenceTables;

@Service
public class ReferenceTablesServiceImpl extends BaseObjectServiceImpl<ReferenceTables>
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
}
