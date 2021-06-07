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
package org.openelisglobal.dataexchange.order.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.validator.GenericValidator;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.dataexchange.order.dao.ElectronicOrderDAO;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder.SortOrder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("externalid", id);

            List<ElectronicOrder> eOrders = query.list();
            // closeSession(); // CSL remove old
            return eOrders;
        } catch (HibernateException e) {
            handleException(e, "getElectronicOrderByExternalId");
        }
        return null;
    }

//
//	@Override
//	public List<ElectronicOrder> getElectronicOrdersByPatientId(String id) throws LIMSRuntimeException {
//		String sql = "from ElectronicOrder eo where eo.patient.id = :patientid";
//
//		try {
//			Query query = entityManager.unwrap(Session.class).createQuery(sql);
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
//			String id = (String) entityManager.unwrap(Session.class).save(eOrder);
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
//			entityManager.unwrap(Session.class).merge(eOrder);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(eOrder);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(eOrder);
//		} catch (HibernateException e) {
//			handleException(e, "updateData");
//		}
//	}

//	public ElectronicOrder readOrder(String idString) {
//		try {
//			ElectronicOrder eOrder = entityManager.unwrap(Session.class).get(ElectronicOrder.class, idString);
//			// closeSession(); // CSL remove old
//			return eOrder;
//		} catch (HibernateException e) {
//			handleException(e, "readOrder");
//		}
//
//		return null;
//	}

//
//	@Override
//	public List<ElectronicOrder> getAllElectronicOrders() {
//		List<ElectronicOrder> list = new Vector<>();
//		try {
//			String sql = "from ElectronicOrder";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			handleException(e, "getAllElectronicOrders");
//		}
//
//		return list;
//
//	}

    @Override
    @Transactional(readOnly = true)
    public List<ElectronicOrder> getAllElectronicOrdersOrderedBy(SortOrder order) {
        List<ElectronicOrder> list = new Vector<>();
        try {
            if (order.equals(ElectronicOrder.SortOrder.LAST_UPDATED_DESC)) {
                list = entityManager.unwrap(Session.class).createCriteria(ElectronicOrder.class)
                        .addOrder(Order.desc("lastupdated")).list();
            } else {

                list = entityManager.unwrap(Session.class).createCriteria(ElectronicOrder.class)
                        .addOrder(Order.asc(order.getValue())).addOrder(Order.desc("lastupdated")).list();
            }
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            handleException(e, "getAllElectronicOrdersOrderedBy");
        }

        return list;
    }

    @Override
    public List<ElectronicOrder> getAllElectronicOrdersContainingValueOrderedBy(String searchValue, SortOrder order) {

        String sql = "from ElectronicOrder eo " + "join eo.patient patient " + "join patient.person person  "
                + "where lower(eo.data) like concat('%', lower(:searchValue), '%') "
                + "or lower(person.firstName) like concat('%', lower(:searchValue), '%') "
                + "or lower(person.lastName) like concat('%', lower(:searchValue), '%') "
                + "or lower(concat(person.firstName, ' ', person.lastName)) like concat('%', lower(:searchValue), '%') order by ";

        switch (order.getValue()) {
        case "statusId":
            sql = sql + "eo.statusId asc";
            break;
        case "lastupdatedasc":
            sql = sql + "eo.statusId asc, eo.lastupdated asc";
            break;
        case "lastupdateddesc":
            sql = sql + "eo.statusId asc, eo.lastupdated desc";
            break;
        case "externalId":
            sql = sql + "eo.externalId asc";
            break;
        default:
            //
            break;
        }
        try {

            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("searchValue", searchValue);
            // query.setString("order", order.getValue());
            List<Object> records = query.list();
            List<ElectronicOrder> eOrders = new ArrayList<>();
            for (int i = 0; i < records.size(); i++) {
                Object[] oArray = (Object[]) records.get(i);
                ElectronicOrder eo = (ElectronicOrder) oArray[0];
                eOrders.add(eo);
            }
            return eOrders;
        } catch (HibernateException e) {
            handleException(e, "getAllElectronicOrdersContainingValue");
        }
        return null;
    }

    @Override
    public List<ElectronicOrder> getElectronicOrdersContainingValueExludedByOrderedBy(String searchValue,
            List<Integer> excludedStatuses, SortOrder sortOrder) {

        String sql = "from ElectronicOrder eo " + "join eo.patient patient " + "join patient.person person  "
                + "where lower(eo.data) like concat('%', lower(:searchValue), '%') "
                + "or lower(person.firstName) like concat('%', lower(:searchValue), '%') "
                + "or lower(person.lastName) like concat('%', lower(:searchValue), '%') "
                + "or lower(concat(person.firstName, ' ', person.lastName)) like concat('%', lower(:searchValue), '%')"
                + "and eo.statusId not in (:excludedStatuses) order by ";

        switch (sortOrder) {
        case STATUS_ID:
            sql = sql + "eo.statusId asc";
            break;
        case LAST_UPDATED_ASC:
            sql = sql + "eo.statusId asc, eo.lastupdated asc";
            break;
        case LAST_UPDATED_DESC:
            sql = sql + "eo.statusId asc, eo.lastupdated desc";
            break;
        case EXTERNAL_ID:
            sql = sql + "eo.externalId asc";
            break;
        default:
            //
            break;
        }
        try {

            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("searchValue", searchValue);
            query.setParameter("excludedStatuses", excludedStatuses);
            // query.setString("order", order.getValue());
            List<Object> records = query.list();
            List<ElectronicOrder> eOrders = new ArrayList<>();
            for (int i = 0; i < records.size(); i++) {
                Object[] oArray = (Object[]) records.get(i);
                ElectronicOrder eo = (ElectronicOrder) oArray[0];
                eOrders.add(eo);
            }
            return eOrders;
        } catch (HibernateException e) {
            handleException(e, "getAllElectronicOrdersContainingValue");
        }
        return null;
    }

    @Override
    public List<ElectronicOrder> getAllElectronicOrdersContainingValuesOrderedBy(String accessionNumber,
            String patientLastName, String patientFirstName, String gender, SortOrder order) {
        String sql = "from ElectronicOrder eo " + "join eo.patient patient " + "join patient.person person  ";
        boolean whereClauseStarted = false;
        if (!GenericValidator.isBlankOrNull(accessionNumber)) {
            sql += getWherePrefix(whereClauseStarted)
                    + " lower(eo.data) like concat('%', lower(:accessionNumber), '%') ";
            whereClauseStarted = true;
        }
//        if (!GenericValidator.isBlankOrNull(patientId)) {
//            sql += getWherePrefix(whereClauseStarted) + "and lower(eo.data) like concat('%', lower(:patientId), '%') ";
//     }
        if (!GenericValidator.isBlankOrNull(patientLastName)) {
            sql += getWherePrefix(whereClauseStarted)
                    + " lower(person.lastName) like concat('%', lower(:patientLastName), '%') ";
            whereClauseStarted = true;
        }
        if (!GenericValidator.isBlankOrNull(patientFirstName)) {
            sql += getWherePrefix(whereClauseStarted)
                    + " lower(person.firstName) like concat('%', lower(:patientFirstName), '%') ";
            whereClauseStarted = true;
        }
//        if (!GenericValidator.isBlankOrNull(dateOfBirth)) {
//            sql += getWherePrefix(whereClauseStarted) + "lower(patient.birthDate) like concat('%', lower(:dateOfBirth), '%') ";
//        }
        if (!GenericValidator.isBlankOrNull(gender)) {
            sql += getWherePrefix(whereClauseStarted) + " lower(patient.gender) = lower(:gender) ";
            whereClauseStarted = true;
        }
        sql += " order by ";

        switch (order.getValue()) {
        case "statusId":
            sql = sql + "eo.statusId asc";
            break;
        case "lastupdatedasc":
            sql = sql + "eo.statusId asc, eo.lastupdated asc";
            break;
        case "lastupdateddesc":
            sql = sql + "eo.statusId asc, eo.lastupdated desc";
            break;
        case "externalId":
            sql = sql + "eo.externalId asc";
            break;
        default:
            //
            break;
        }
        try {

            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            if (!GenericValidator.isBlankOrNull(accessionNumber)) {
                query.setString("accessionNumber", accessionNumber);
            }
            if (!GenericValidator.isBlankOrNull(patientLastName)) {
                query.setString("patientLastName", patientLastName);
            }
            if (!GenericValidator.isBlankOrNull(patientFirstName)) {
                query.setString("patientFirstName", patientFirstName);
            }
            if (!GenericValidator.isBlankOrNull(gender)) {
                query.setString("gender", gender);
            }
            // query.setString("order", order.getValue());
            List<Object> records = query.list();
            List<ElectronicOrder> eOrders = new ArrayList<>();
            for (int i = 0; i < records.size(); i++) {
                Object[] oArray = (Object[]) records.get(i);
                ElectronicOrder eo = (ElectronicOrder) oArray[0];
                eOrders.add(eo);
            }
            return eOrders;
        } catch (HibernateException e) {
            handleException(e, "getAllElectronicOrdersContainingValue");
        }
        return null;
    }

    private String getWherePrefix(boolean whereClauseStarted) {
        if (!whereClauseStarted) {
            return " where ";
        } else {
            return " and ";
        }
    }
}
