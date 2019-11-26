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
import org.openelisglobal.address.dao.PersonAddressDAO;
import org.openelisglobal.address.valueholder.AddressPK;
import org.openelisglobal.address.valueholder.PersonAddress;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PersonAddressDAOImpl extends BaseDAOImpl<PersonAddress, AddressPK> implements PersonAddressDAO {

    public PersonAddressDAOImpl() {
        super(PersonAddress.class);
    }

    
    @Override
    public List<PersonAddress> getAddressPartsByPersonId(String personId) throws LIMSRuntimeException {
        String sql = "from PersonAddress pa where pa.compoundId.targetId = :personId";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("personId", Integer.parseInt(personId));
            List<PersonAddress> addressPartList = query.list();
            return addressPartList;
        } catch (HibernateException e) {
            handleException(e, "getAddressPartsByPersonId");
        }

        return null;
    }

//	@Override
//	public AddressPK insert(PersonAddress personAddress) throws LIMSRuntimeException {
//		try {
//			AddressPK id = (AddressPK) entityManager.unwrap(Session.class).save(personAddress);
//			auditDAO.saveNewHistory(personAddress, personAddress.getSysUserId(), "person_address");
//			// closeSession(); // CSL remove old
//			return id;
//		} catch (HibernateException e) {
//			handleException(e, "insert");
//		}
//		return null;
//	}

//	@Override
//	public Optional<PersonAddress> update(PersonAddress personAddress) throws LIMSRuntimeException {
//
//		PersonAddress oldData = readPersonAddress(personAddress);
//
//		try {
//			auditDAO.saveHistory(personAddress, oldData, personAddress.getSysUserId(),
//					IActionConstants.AUDIT_TRAIL_UPDATE, "person_address");
//
//			entityManager.unwrap(Session.class).merge(personAddress);
//			// closeSession(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(personAddress);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(personAddress);
//		} catch (HibernateException e) {
//			handleException(e, "update");
//		}
//		return Optional.ofNullable(personAddress);
//	}

//	public PersonAddress readPersonAddress(PersonAddress personAddress) {
//		try {
//			PersonAddress oldPersonAddress = entityManager.unwrap(Session.class).get(PersonAddress.class,
//					personAddress.getCompoundId());
//			// closeSession(); // CSL remove old
//
//			return oldPersonAddress;
//		} catch (HibernateException e) {
//			handleException(e, "readPersonAddress");
//		}
//
//		return null;
//	}

    @Override
    public PersonAddress getByPersonIdAndPartId(String personId, String addressPartId) throws LIMSRuntimeException {
        String sql = "from PersonAddress pa where pa.compoundId.targetId = :personId and pa.compoundId.addressPartId = :partId";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("personId", Integer.parseInt(personId));
            query.setInteger("partId", Integer.parseInt(addressPartId));
            PersonAddress addressPart = (PersonAddress) query.uniqueResult();
            // closeSession(); // CSL remove old
            return addressPart;
        } catch (HibernateException e) {
            handleException(e, "getByPersonIdAndPartId");
        }

        return null;
    }
}
