package org.openelisglobal.referencetables.service;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.referencetables.valueholder.ReferenceTables;

public interface ReferenceTablesService extends BaseObjectService<ReferenceTables, String> {
	void getData(ReferenceTables referenceTables);

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
