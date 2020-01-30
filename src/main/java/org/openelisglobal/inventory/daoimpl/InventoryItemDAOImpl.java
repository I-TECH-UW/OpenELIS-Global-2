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
*/
package org.openelisglobal.inventory.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.inventory.dao.InventoryItemDAO;
import org.openelisglobal.inventory.valueholder.InventoryItem;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class InventoryItemDAOImpl extends BaseDAOImpl<InventoryItem, String> implements InventoryItemDAO {

    public InventoryItemDAOImpl() {
        super(InventoryItem.class);
    }

    @Override
    
    @Transactional(readOnly = true)
    public List<InventoryItem> getAllInventoryItems() throws LIMSRuntimeException {
        List<InventoryItem> inventoryItems;
        try {
            String sql = "from InventoryItem";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            inventoryItems = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in InventoryItem getAllInventoryItems()", e);
        }

        return inventoryItems;
    }

//	@Override
//	public void deleteData(List<InventoryItem> inventoryItems) throws LIMSRuntimeException {
//		try {
//
//			for (InventoryItem data : inventoryItems) {
//
//				InventoryItem oldData = readInventoryItem(data.getId());
//				InventoryItem newData = new InventoryItem();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "INVENTORY_ITEM";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//
//			for (InventoryItem data : inventoryItems) {
//
//				data = readInventoryItem(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("InventoryItemDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryItem deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(InventoryItem inventoryItem) throws LIMSRuntimeException {
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(inventoryItem);
//			inventoryItem.setId(id);
//
//			String sysUserId = inventoryItem.getSysUserId();
//			String tableName = "INVENTORY_ITEM";
//			auditDAO.saveNewHistory(inventoryItem, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			LogEvent.logError("InventoryItemDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryItem insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(InventoryItem inventoryItem) throws LIMSRuntimeException {
//		InventoryItem oldData = readInventoryItem(inventoryItem.getId());
//		InventoryItem newData = inventoryItem;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = inventoryItem.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "INVENTORY_ITEM";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//
//			entityManager.unwrap(Session.class).merge(inventoryItem);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(inventoryItem);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(inventoryItem);
//		} catch (RuntimeException e) {
//			LogEvent.logError("InventoryItemDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryItem updateData()", e);
//		}
//	}

//	@Override
//	public void getData(InventoryItem inventoryItem) throws LIMSRuntimeException {
//		try {
//			InventoryItem tmpInventoryItem = entityManager.unwrap(Session.class).get(InventoryItem.class,
//					inventoryItem.getId());
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			if (tmpInventoryItem != null) {
//				PropertyUtils.copyProperties(inventoryItem, tmpInventoryItem);
//			} else {
//				inventoryItem.setId(null);
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("InventoryItemDAOImpl", "getData()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryItem getData()", e);
//		}
//	}

    @Override
    public InventoryItem readInventoryItem(String idString) throws LIMSRuntimeException {
        InventoryItem data = null;
        try {
            data = entityManager.unwrap(Session.class).get(InventoryItem.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in InventoryItem readInventoryItem()", e);
        }

        return data;
    }

//	@Override
//	public InventoryItem getInventoryItemById(InventoryItem inventoryItem) throws LIMSRuntimeException {
//		try {
//			InventoryItem re = entityManager.unwrap(Session.class).get(InventoryItem.class, inventoryItem.getId());
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			return re;
//		} catch (RuntimeException e) {
//			LogEvent.logError("InventoryItemDAOImpl", "getInventoryItemById()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryItem getInventoryItemById()", e);
//		}
//	}

}