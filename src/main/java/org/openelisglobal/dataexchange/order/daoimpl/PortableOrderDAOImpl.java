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

import java.util.List;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.dataexchange.order.dao.PortableOrderDAO;
import org.openelisglobal.dataexchange.order.valueholder.PortableOrder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PortableOrderDAOImpl extends BaseDAOImpl<PortableOrder, String> implements PortableOrderDAO {

    public PortableOrderDAOImpl() {
        super(PortableOrder.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PortableOrder> getPortableOrdersByExternalId(String id) throws LIMSRuntimeException {
        String sql = "from PortableOrder po where po.externalId = :externalid order by id";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("externalid", id);
            
            List<PortableOrder> pOrders = query.list();
            // closeSession(); // CSL remove old
            return pOrders;
        } catch (HibernateException e) {
            handleException(e, "getPortableOrderByExternalId");
        }
        return null;
    }

//  
//  @Override
//  public List<PortableOrder> getPortableOrdersByPatientId(String id) throws LIMSRuntimeException {
//      String sql = "from PortableOrder eo where eo.patient.id = :patientid";
//
//      try {
//          Query query = entityManager.unwrap(Session.class).createQuery(sql);
//
//          query.setString("patientid", id);
//          List<PortableOrder> eorders = query.list();
//          // closeSession(); // CSL remove old
//
//          return eorders;
//      } catch (HibernateException e) {
//          handleException(e, "getPortableOrdersByPatientId");
//      }
//      return null;
//  }

//  @Override
//  public void insertData(PortableOrder eOrder) throws LIMSRuntimeException {
//      try {
//          String id = (String) entityManager.unwrap(Session.class).save(eOrder);
//          eOrder.setId(id);
//
//          auditDAO.saveNewHistory(eOrder, eOrder.getSysUserId(), "ELECTROINIC_ORDER");
//
//          // closeSession(); // CSL remove old
//      } catch (HibernateException e) {
//          handleException(e, "insertData");
//      }
//  }

//  @Override
//  public void updateData(PortableOrder eOrder) throws LIMSRuntimeException {
//
//      PortableOrder oldOrder = readOrder(eOrder.getId());
//
//      try {
//          auditDAO.saveHistory(eOrder, oldOrder, eOrder.getSysUserId(), IActionConstants.AUDIT_TRAIL_UPDATE,
//                  "ELECTROINIC_ORDER");
//
//          entityManager.unwrap(Session.class).merge(eOrder);
//          // entityManager.unwrap(Session.class).flush(); // CSL remove old
//          // entityManager.unwrap(Session.class).clear(); // CSL remove old
//          // entityManager.unwrap(Session.class).evict // CSL remove old(eOrder);
//          // entityManager.unwrap(Session.class).refresh // CSL remove old(eOrder);
//      } catch (HibernateException e) {
//          handleException(e, "updateData");
//      }
//  }

//  public PortableOrder readOrder(String idString) {
//      try {
//          PortableOrder eOrder = entityManager.unwrap(Session.class).get(PortableOrder.class, idString);
//          // closeSession(); // CSL remove old
//          return eOrder;
//      } catch (HibernateException e) {
//          handleException(e, "readOrder");
//      }
//
//      return null;
//  }

//  
//  @Override
//  public List<PortableOrder> getAllPortableOrders() {
//      List<PortableOrder> list = new Vector<>();
//      try {
//          String sql = "from PortableOrder";
//          org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//          list = query.list();
//          // entityManager.unwrap(Session.class).flush(); // CSL remove old
//          // entityManager.unwrap(Session.class).clear(); // CSL remove old
//      } catch (RuntimeException e) {
//          handleException(e, "getAllPortableOrders");
//      }
//
//      return list;
//
//  }

    
    @Override
    @Transactional(readOnly = true)
    public List<PortableOrder> getAllPortableOrdersOrderedBy(PortableOrder.SortOrder order) {
        List<PortableOrder> list = new Vector<>();
        try {
            if (order.equals(PortableOrder.SortOrder.LAST_UPDATED)) {
                list = entityManager.unwrap(Session.class).createCriteria(PortableOrder.class)
                        .addOrder(Order.desc("lastupdated")).list();
            } else {

                list = entityManager.unwrap(Session.class).createCriteria(PortableOrder.class)
                        .addOrder(Order.asc(order.getValue())).addOrder(Order.desc("lastupdated")).list();
            }
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            handleException(e, "getAllPortableOrdersOrderedBy");
        }

        return list;
    }

}
