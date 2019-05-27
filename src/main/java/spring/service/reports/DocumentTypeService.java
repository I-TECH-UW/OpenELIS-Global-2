package spring.service.reports;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.reports.valueholder.DocumentType;

public interface DocumentTypeService extends BaseObjectService<DocumentType> {
	DocumentType getDocumentTypeByName(String name);
}
