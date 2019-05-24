package spring.service.reports;

import java.lang.String;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.reports.valueholder.DocumentType;

public interface DocumentTypeService extends BaseObjectService<DocumentType> {
	DocumentType getDocumentTypeByName(String name);
}
