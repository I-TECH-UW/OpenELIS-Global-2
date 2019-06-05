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
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.address.dao.PersonAddressDAO;
import us.mn.state.health.lims.address.valueholder.AddressPK;
import us.mn.state.health.lims.address.valueholder.PersonAddress;
import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;

@Component
@Transactional
public class PersonAddressDAOImpl extends BaseDAOImpl<PersonAddress, AddressPK> implements PersonAddressDAO {

	public PersonAddressDAOImpl() {
		super(PersonAddress.class);
	}

	@Autowired
	private AuditTrailDAO auditDAO;

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonAddress> getAddressPartsByPersonId(String personId) throws LIMSRuntimeException {
		String sql = "from PersonAddress pa where pa.compoundId.targetId = :personId";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setInteger("personId", Integer.parseInt(personId));
			List<PersonAddress> addressPartList = query.list();
			return addressPartList;
		} catch (HibernateException e) {
			handleException(e, "getAddressPartsByPersonId");
		}

		return null;
	}

	@Override
	public AddressPK insert(PersonAddress personAddress) throws LIMSRuntimeException {
		try {
			AddressPK id = (AddressPK) sessionFactory.getCurrentSession().save(personAddress);
			auditDAO.saveNewHistory(personAddress, personAddress.getSysUserId(), "person_address");
			// closeSession(); // CSL remove old
			return id;
		} catch (HibernateException e) {
			handleException(e, "insert");
		}
		return null;
	}

	@Override
	public Optional<PersonAddress> update(PersonAddress personAddress) throws LIMSRuntimeException {

		PersonAddress oldData = readPersonAddress(personAddress);

		try {
			auditDAO.saveHistory(personAddress, oldData, personAddress.getSysUserId(),
					IActionConstants.AUDIT_TRAIL_UPDATE, "person_address");

			sessionFactory.getCurrentSession().merge(personAddress);
			// closeSession(); // CSL remove old
			// sessionFactory.getCurrentSession().evict // CSL remove old(personAddress);
			// sessionFactory.getCurrentSession().refresh // CSL remove old(personAddress);
		} catch (HibernateException e) {
			handleException(e, "update");
		}
		return Optional.ofNullable(personAddress);
	}

	public PersonAddress readPersonAddress(PersonAddress personAddress) {
		try {
			PersonAddress oldPersonAddress = sessionFactory.getCurrentSession().get(PersonAddress.class,
					personAddress.getCompoundId());
			// closeSession(); // CSL remove old

			return oldPersonAddress;
		} catch (HibernateException e) {
			handleException(e, "readPersonAddress");
		}

		return null;
	}

	@Override
	public PersonAddress getByPersonIdAndPartId(String personId, String addressPartId) throws LIMSRuntimeException {
		String sql = "from PersonAddress pa where pa.compoundId.targetId = :personId and pa.compoundId.addressPartId = :partId";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
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
