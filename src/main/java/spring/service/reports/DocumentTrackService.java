package spring.service.reports;

import java.lang.String;
import java.util.List;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.reports.valueholder.DocumentTrack;

public interface DocumentTrackService extends BaseObjectService<DocumentTrack> {
	void insertData(DocumentTrack docTrack);

	List<DocumentTrack> getByTypeRecordAndTableAndName(String reportTypeId, String referenceTable, String id,
			String name);

	List<DocumentTrack> getByTypeRecordAndTable(String typeId, String tableId, String recordId);

	DocumentTrack readEntity(String id);
}
