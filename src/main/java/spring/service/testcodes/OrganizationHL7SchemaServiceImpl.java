package spring.service.testcodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.testcodes.dao.OrganizationHL7SchemaDAO;
import us.mn.state.health.lims.testcodes.valueholder.OrganizationHL7Schema;

@Service
public class OrganizationHL7SchemaServiceImpl extends BaseObjectServiceImpl<OrganizationHL7Schema> implements OrganizationHL7SchemaService {
	@Autowired
	protected OrganizationHL7SchemaDAO baseObjectDAO;

	OrganizationHL7SchemaServiceImpl() {
		super(OrganizationHL7Schema.class);
	}

	@Override
	protected OrganizationHL7SchemaDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
