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
package us.mn.state.health.lims.address.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.address.dao.AddressPartDAO;
import us.mn.state.health.lims.address.valueholder.AddressPart;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.hibernate.HibernateUtil;

public class AddressPartDAOImpl extends BaseDAOImpl implements AddressPartDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<AddressPart> getAll() throws LIMSRuntimeException {
		String sql = "from AddressPart";
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);

			List<AddressPart> addressPartList = query.list();

			closeSession();

			return addressPartList;

		}catch(HibernateException e){
			handleException(e, "getAll");
		}
		return null;
	}

	@Override
	public AddressPart getAddresPartByName(String name) throws LIMSRuntimeException{
		String sql = "from AddressPart ap where ap.partName = :name";
		
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("name", name);
			AddressPart part = (AddressPart)query.uniqueResult();
			closeSession();
			return part;
		}catch(HibernateException he){
			handleException(he, "getAddressPartByName");
		}
		return null;
	}
}
