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
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 *
 */
package us.mn.state.health.lims.dataexchange.order.daoimpl;

import java.util.List;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.dataexchange.order.dao.ElectronicOrderDAO;
import us.mn.state.health.lims.dataexchange.order.valueholder.ElectronicOrder;

@Component
@Transactional
public class ElectronicOrderDAOImpl extends BaseDAOImpl<ElectronicOrder, String> implements ElectronicOrderDAO {

	public ElectronicOrderDAOImpl() {
		super(ElectronicOrder.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ElectronicOrder> getElectronicOrdersByExternalId(String id) throws LIMSRuntimeException {
		String sql = "from ElectronicOrder eo where eo.externalId = :externalid order by id";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setString("externalid", id);
			@SuppressWarnings("unchecked")
			List<ElectronicOrder> eOrders = query.list();
			// closeSession(); // CSL remove old
			return eOrders;
		} catch (HibernateException e) {
			handleException(e, "getElectronicOrderByExternalId");
		}
		return null;
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<ElectronicOrder> getElectronicOrdersByPatientId(String id) throws LIMSRuntimeException {
//		String sql = "from ElectronicOrder eo where eo.patient.id = :patientid";
//
//		try {
//			Query query = sessionFactory.getCurrentSession().createQuery(sql);
//
//			query.setString("patientid", id);
//			List<ElectronicOrder> eorders = query.list();
//			// closeSession(); // CSL remove old
//
//			return eorders;
//		} catch (HibernateException e) {
//			handleException(e, "getElectronicOrdersByPatientId");
//		}
//		return null;
//	}

//	@Override
//	public void insertData(ElectronicOrder eOrder) throws LIMSRuntimeException {
//		try {
//			String id = (String) sessionFactory.getCurrentSession().save(eOrder);
//			eOrder.setId(id);
//
//			auditDAO.saveNewHistory(eOrder, eOrder.getSysUserId(), "ELECTROINIC_ORDER");
//
//			// closeSession(); // CSL remove old
//		} catch (HibernateException e) {
//			handleException(e, "insertData");
//		}
//	}

//	@Override
//	public void updateData(ElectronicOrder eOrder) throws LIMSRuntimeException {
//
//		ElectronicOrder oldOrder = readOrder(eOrder.getId());
//
//		try {
//			auditDAO.saveHistory(eOrder, oldOrder, eOrder.getSysUserId(), IActionConstants.AUDIT_TRAIL_UPDATE,
//					"ELECTROINIC_ORDER");
//
//			sessionFactory.getCurrentSession().merge(eOrder);
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			// sessionFactory.getCurrentSession().evict // CSL remove old(eOrder);
//			// sessionFactory.getCurrentSession().refresh // CSL remove old(eOrder);
//		} catch (HibernateException e) {
//			handleException(e, "updateData");
//		}
//	}

//	public ElectronicOrder readOrder(String idString) {
//		try {
//			ElectronicOrder eOrder = sessionFactory.getCurrentSession().get(ElectronicOrder.class, idString);
//			// closeSession(); // CSL remove old
//			return eOrder;
//		} catch (HibernateException e) {
//			handleException(e, "readOrder");
//		}
//
//		return null;
//	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<ElectronicOrder> getAllElectronicOrders() {
//		List<ElectronicOrder> list = new Vector<>();
//		try {
//			String sql = "from ElectronicOrder";
//			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
//			list = query.list();
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//		} catch (Exception e) {
//			handleException(e, "getAllElectronicOrders");
//		}
//
//		return list;
//
//	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<ElectronicOrder> getAllElectronicOrdersOrderedBy(ElectronicOrder.SortOrder order) {
		List<ElectronicOrder> list = new Vector<>();
		try {
			if (order.equals(ElectronicOrder.SortOrder.LAST_UPDATED)) {
				list = sessionFactory.getCurrentSession().createCriteria(ElectronicOrder.class)
						.addOrder(Order.desc("lastupdated")).list();
			} else {

				list = sessionFactory.getCurrentSession().createCriteria(ElectronicOrder.class)
						.addOrder(Order.asc(order.getValue())).addOrder(Order.desc("lastupdated")).list();
			}
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			handleException(e, "getAllElectronicOrdersOrderedBy");
		}

		return list;
	}

}
