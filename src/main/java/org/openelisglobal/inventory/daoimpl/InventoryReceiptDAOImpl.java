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
* Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
*
* Contributor(s): CIRG, University of Washington, Seattle WA.
*/
package org.openelisglobal.inventory.daoimpl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.inventory.dao.InventoryReceiptDAO;
import org.openelisglobal.inventory.valueholder.InventoryReceipt;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class InventoryReceiptDAOImpl extends BaseDAOImpl<InventoryReceipt, String> implements InventoryReceiptDAO {

    public InventoryReceiptDAOImpl() {
        super(InventoryReceipt.class);
    }

    @Override

    @Transactional(readOnly = true)
    public List<InventoryReceipt> getAllInventoryReceipts() throws LIMSRuntimeException {
        List<InventoryReceipt> inventoryReceipts;
        try {
            String sql = "from InventoryReceipt";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            inventoryReceipts = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in InventoryReceipt getAllInventoryReceipts()", e);
        }

        return inventoryReceipts;
    }
//
//	@Override
//	public void deleteData(List<InventoryReceipt> inventoryReceipts) throws LIMSRuntimeException {
//		try {
//
//			for (InventoryReceipt data : inventoryReceipts) {
//
//				InventoryReceipt oldData = getInventoryReceiptById(data.getId());
//				InventoryReceipt newData = new InventoryReceipt();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "INVENTORY_RECEIPT";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//
//			for (InventoryReceipt data : inventoryReceipts) {
//
//				data = getInventoryReceiptById(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("InventoryReceiptDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryReceipt deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(InventoryReceipt inventoryReceipt) throws LIMSRuntimeException {
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(inventoryReceipt);
//			inventoryReceipt.setId(id);
//
//			String sysUserId = inventoryReceipt.getSysUserId();
//			String tableName = "INVENTORY_RECEIPT";
//			auditDAO.saveNewHistory(inventoryReceipt, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			LogEvent.logError("InventoryReceiptDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryReceipt insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(InventoryReceipt inventoryReceipt) throws LIMSRuntimeException {
//		InventoryReceipt oldData = getInventoryReceiptById(inventoryReceipt.getId());
//		InventoryReceipt newData = inventoryReceipt;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = inventoryReceipt.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "INVENTORY_RECEIPT";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//
//			entityManager.unwrap(Session.class).merge(inventoryReceipt);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(inventoryReceipt);
//			// entityManager.unwrap(Session.class).refresh // CSL remove
//			// old(inventoryReceipt);
//		} catch (RuntimeException e) {
//			LogEvent.logError("InventoryReceiptDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryReceipt updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(InventoryReceipt inventoryReceipt) throws LIMSRuntimeException {
        try {
            InventoryReceipt tmpInventoryReceipt = entityManager.unwrap(Session.class).get(InventoryReceipt.class,
                    inventoryReceipt.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (tmpInventoryReceipt != null) {
                PropertyUtils.copyProperties(inventoryReceipt, tmpInventoryReceipt);
            } else {
                inventoryReceipt.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in InventoryReceipt getData()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public InventoryReceipt getInventoryReceiptById(String idString) throws LIMSRuntimeException {
        InventoryReceipt data = null;
        try {
            data = entityManager.unwrap(Session.class).get(InventoryReceipt.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in InventoryReceipt readInventoryReceipt()", e);
        }

        return data;
    }

    @Override
    @Transactional(readOnly = true)
    public InventoryReceipt getInventoryReceiptByInventoryItemId(String id) throws LIMSRuntimeException {
        InventoryReceipt inventory = null;

        try {
            List<InventoryReceipt> inventoryReceipts;

            String sql = "from InventoryReceipt where invitem_id = :id";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("id", Integer.parseInt(id));
            inventoryReceipts = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            if (inventoryReceipts != null && inventoryReceipts.size() > 0) {
                inventory = inventoryReceipts.get(0);
            }

        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in InventoryReceipt getInventoryReceiptByInventoryItemId()", e);
        }

        return inventory;
    }

}