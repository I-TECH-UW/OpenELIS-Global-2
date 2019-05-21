package spring.service.reports;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.reports.valueholder.DocumentTrack;

public interface DocumentTrackService extends BaseObjectService<DocumentTrack> {

	List<DocumentTrack> getByTypeRecordAndTable(String typeId, String tableId, String recordId);
}
