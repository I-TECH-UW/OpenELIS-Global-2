package us.mn.state.health.lims.testcodes.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.testcodes.valueholder.OrganizationHL7Schema;

@Component
public class OrganizationHL7SchemaDAOImpl extends BaseDAOImpl<OrganizationHL7Schema> implements OrganizationHL7SchemaDAO {
  OrganizationHL7SchemaDAOImpl() {
    super(OrganizationHL7Schema.class);
  }
}
