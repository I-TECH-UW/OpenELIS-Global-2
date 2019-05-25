package spring.service.referencetables;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.referencetables.valueholder.ReferenceTables;

public interface ReferenceTablesService extends BaseObjectService<ReferenceTables> {
	void getData(ReferenceTables referenceTables);

	void deleteData(List referenceTableses);

	void updateData(ReferenceTables referenceTables);

	boolean insertData(ReferenceTables referenceTables);

	List getAllReferenceTablesForHl7Encoding();

	List getAllReferenceTables();

	ReferenceTables getReferenceTableByName(String tableName);

	ReferenceTables getReferenceTableByName(ReferenceTables referenceTables);

	Integer getTotalReferenceTableCount();

	List getPreviousReferenceTablesRecord(String id);

	List getPageOfReferenceTables(int startingRecNo);

	List getNextReferenceTablesRecord(String id);

	Integer getTotalReferenceTablesCount();
}
