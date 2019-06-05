package spring.service.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.reports.dao.DocumentTypeDAO;
import us.mn.state.health.lims.reports.valueholder.DocumentType;

@Service
public class DocumentTypeServiceImpl extends BaseObjectServiceImpl<DocumentType, String> implements DocumentTypeService {
	@Autowired
	protected DocumentTypeDAO baseObjectDAO;

	DocumentTypeServiceImpl() {
		super(DocumentType.class);
	}

	@Override
	protected DocumentTypeDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional
	public DocumentType getDocumentTypeByName(String name) {
		return getMatch("name", name).orElse(null);
	}
}
