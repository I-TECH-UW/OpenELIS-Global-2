package spring.service.reports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.reports.dao.DocumentTrackDAO;
import us.mn.state.health.lims.reports.valueholder.DocumentTrack;

@Service
public class DocumentTrackServiceImpl extends BaseObjectServiceImpl<DocumentTrack> implements DocumentTrackService {
	@Autowired
	protected DocumentTrackDAO baseObjectDAO;

	DocumentTrackServiceImpl() {
		super(DocumentTrack.class);
	}

	@Override
	protected DocumentTrackDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional
	public List<DocumentTrack> getByTypeRecordAndTable(String typeId, String tableId, String recordId) {
		Map<String, Object> propertyValues = new HashMap<>();
		propertyValues.put("documentTypeId", typeId);
		propertyValues.put("tableId", tableId);
		propertyValues.put("recordId", recordId);
		return baseObjectDAO.getAllMatchingOrdered(propertyValues, "reportTime", false);
	}

	@Override
	public void insertData(DocumentTrack docTrack) {
        getBaseObjectDAO().insertData(docTrack);

	}

	@Override
	public List<DocumentTrack> getByTypeRecordAndTableAndName(String reportTypeId, String referenceTable, String id, String name) {
        return getBaseObjectDAO().getByTypeRecordAndTableAndName(reportTypeId,referenceTable,id,name);
	}

	@Override
	public DocumentTrack readEntity(String id) {
        return getBaseObjectDAO().readEntity(id);
	}
}
