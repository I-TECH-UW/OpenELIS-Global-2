package org.openelisglobal.reports.service;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.reports.valueholder.DocumentType;

public interface DocumentTypeService extends BaseObjectService<DocumentType, String> {
  DocumentType getDocumentTypeByName(String name);
}
