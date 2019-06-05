package us.mn.state.health.lims.testcodes.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.testcodes.valueholder.OrganizationHL7Schema;
import us.mn.state.health.lims.testcodes.valueholder.OrganizationSchemaPK;

@Component
@Transactional
public class OrganizationHL7SchemaDAOImpl extends BaseDAOImpl<OrganizationHL7Schema, OrganizationSchemaPK>
		implements OrganizationHL7SchemaDAO {
	OrganizationHL7SchemaDAOImpl() {
		super(OrganizationHL7Schema.class);
	}
}
