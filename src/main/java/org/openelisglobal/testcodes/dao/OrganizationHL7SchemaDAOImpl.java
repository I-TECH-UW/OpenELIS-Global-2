package org.openelisglobal.testcodes.dao;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.testcodes.valueholder.OrganizationHL7Schema;
import org.openelisglobal.testcodes.valueholder.OrganizationSchemaPK;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OrganizationHL7SchemaDAOImpl extends BaseDAOImpl<OrganizationHL7Schema, OrganizationSchemaPK>
        implements OrganizationHL7SchemaDAO {
    OrganizationHL7SchemaDAOImpl() {
        super(OrganizationHL7Schema.class);
    }
}
