package spring.service.referencetables;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.referencetables.valueholder.ReferenceTables;

public interface ReferenceTablesService extends BaseObjectService<ReferenceTables> {

	ReferenceTables getReferenceTableByName(String tableName);
}
