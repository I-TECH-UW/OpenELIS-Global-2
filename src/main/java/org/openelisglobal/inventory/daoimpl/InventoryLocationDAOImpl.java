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

import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.inventory.dao.InventoryLocationDAO;
import org.openelisglobal.inventory.valueholder.InventoryLocation;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class InventoryLocationDAOImpl extends BaseDAOImpl<InventoryLocation, String> implements InventoryLocationDAO {

    public InventoryLocationDAOImpl() {
        super(InventoryLocation.class);
    }

//	@Override
//	
//	public List<InventoryLocation> getAllInventoryLocations() throws LIMSRuntimeException {
//		List<InventoryLocation> inventoryItems;
//		try {
//			String sql = "from InventoryLocation";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			inventoryItems = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			LogEvent.logError("InventoryLocationDAOImpl", "getAllInventoryLocations()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryLocation getAllInventoryLocations()", e);
//		}
//
//		return inventoryItems;
//	}

//	@Override
//	public void deleteData(List<InventoryLocation> inventoryItems) throws LIMSRuntimeException {
//		try {
//
//			for (InventoryLocation data : inventoryItems) {
//
//				InventoryLocation oldData = readInventoryLocation(data.getId());
//				InventoryLocation newData = new InventoryLocation();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "INVENTORY_ITEM";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//
//			for (InventoryLocation data : inventoryItems) {
//
//				data = readInventoryLocation(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("InventoryLocationDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryLocation deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(InventoryLocation inventoryItem) throws LIMSRuntimeException {
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
//			LogEvent.logError("InventoryLocationDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryLocation insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(InventoryLocation inventoryItem) throws LIMSRuntimeException {
//		InventoryLocation oldData = readInventoryLocation(inventoryItem.getId());
//		InventoryLocation newData = inventoryItem;
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
//			LogEvent.logError("InventoryLocationDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryLocation updateData()", e);
//		}
//	}

//	@Override
//	public void getData(InventoryLocation inventoryItem) throws LIMSRuntimeException {
//		try {
//			InventoryLocation tmpInventoryLocation = entityManager.unwrap(Session.class).get(InventoryLocation.class,
//					inventoryItem.getId());
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			if (tmpInventoryLocation != null) {
//				PropertyUtils.copyProperties(inventoryItem, tmpInventoryLocation);
//			} else {
//				inventoryItem.setId(null);
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("InventoryLocationDAOImpl", "getData()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryLocation getData()", e);
//		}
//	}

    public InventoryLocation readInventoryLocation(String idString) throws LIMSRuntimeException {
        InventoryLocation data = null;
        try {
            data = entityManager.unwrap(Session.class).get(InventoryLocation.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in InventoryLocation readInventoryLocation()", e);
        }

        return data;
    }

//	@Override
//	public InventoryLocation getInventoryLocationById(InventoryLocation inventoryItem) throws LIMSRuntimeException {
//		try {
//			InventoryLocation re = entityManager.unwrap(Session.class).get(InventoryLocation.class,
//					inventoryItem.getId());
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			return re;
//		} catch (RuntimeException e) {
//			LogEvent.logError("InventoryLocationDAOImpl", "getInventoryLocationById()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryLocation getInventoryLocationById()", e);
//		}
//	}

}