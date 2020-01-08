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
package org.openelisglobal.address.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.address.dao.OrganizationAddressDAO;
import org.openelisglobal.address.valueholder.AddressPK;
import org.openelisglobal.address.valueholder.OrganizationAddress;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OrganizationAddressDAOImpl extends BaseDAOImpl<OrganizationAddress, AddressPK>
        implements OrganizationAddressDAO {

    public OrganizationAddressDAOImpl() {
        super(OrganizationAddress.class);
    }

    
    @Override
    public List<OrganizationAddress> getAddressPartsByOrganizationId(String organizationId)
            throws LIMSRuntimeException {
        String sql = "from OrganizationAddress pa where pa.compoundId.targetId = :organizationId";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("organizationId", Integer.parseInt(organizationId));
            List<OrganizationAddress> addressPartList = query.list();
            return addressPartList;
        } catch (HibernateException e) {
            handleException(e, "getAddressPartsByOrganizationId");
        }

        return null;
    }

//	@Override
//	public Serializable insert(OrganizationAddress organizationAddress) throws LIMSRuntimeException {
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(organizationAddress);
//			auditDAO.saveNewHistory(organizationAddress, organizationAddress.getSysUserId(), "organization_address");
//			return id;
//		} catch (HibernateException e) {
//			handleException(e, "insert");
//		}
//		return null;
//	}

//	@Override
//	public Optional<OrganizationAddress> update(OrganizationAddress organizationAddress) throws LIMSRuntimeException {
//
//		OrganizationAddress oldData = readOrganizationAddress(organizationAddress);
//
//		try {
//			auditDAO.saveHistory(organizationAddress, oldData, organizationAddress.getSysUserId(),
//					IActionConstants.AUDIT_TRAIL_UPDATE, "organization_address");
//
//			entityManager.unwrap(Session.class).merge(organizationAddress);
//			// closeSession(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove
//			// old(organizationAddress);
//			// entityManager.unwrap(Session.class).refresh // CSL remove
//			// old(organizationAddress);
//		} catch (HibernateException e) {
//			handleException(e, "update");
//		}
//		return Optional.ofNullable(organizationAddress);
//	}

//	public OrganizationAddress readOrganizationAddress(OrganizationAddress organizationAddress) {
//		try {
//			OrganizationAddress oldOrganizationAddress = entityManager.unwrap(Session.class)
//					.get(OrganizationAddress.class, organizationAddress.getCompoundId());
//			// closeSession(); // CSL remove old
//
//			return oldOrganizationAddress;
//		} catch (HibernateException e) {
//			handleException(e, "readOrganizationAddress");
//		}
//
//		return null;
//	}
}
