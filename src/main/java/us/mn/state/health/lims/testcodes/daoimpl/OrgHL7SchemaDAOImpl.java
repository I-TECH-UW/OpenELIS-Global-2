/**
* The contents of this file are subject to the Mozilla Public License
* Version 1.1 (the "License"); you may not use this file except in
* compliance with the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/ 
* 
* Software distributed under the License is distributed on an "AS IS"
* basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
* License for the specific language governing rights and limitations under
* the License.
* 
* The Original Code is OpenELIS code.
* 
* Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.testcodes.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.testcodes.dao.OrgHL7SchemaDAO;
import us.mn.state.health.lims.testcodes.valueholder.OrganizationHL7Schema;


public class OrgHL7SchemaDAOImpl extends BaseDAOImpl implements OrgHL7SchemaDAO {

	@Override
	public OrganizationHL7Schema getOrganizationHL7SchemaByOrgId(String orgId) throws LIMSRuntimeException {
		String sql = "from OrganizationHL7Schema hs where hs.compoundId.organizationId = :id";
		
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("id", orgId);
			OrganizationHL7Schema hs = (OrganizationHL7Schema)query.uniqueResult();
			closeSession();
			return hs;
		}catch(HibernateException e){
			handleException(e, "getOrganizationHL7SchemaByOrgId");
		}
		return null;
	}

	
}