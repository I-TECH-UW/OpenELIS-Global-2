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
package us.mn.state.health.lims.inventory.daoimpl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.inventory.dao.InventoryItemDAO;
import us.mn.state.health.lims.inventory.valueholder.InventoryItem;

@Component
@Transactional
public class InventoryItemDAOImpl extends BaseDAOImpl<InventoryItem, String> implements InventoryItemDAO {

	public InventoryItemDAOImpl() {
		super(InventoryItem.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<InventoryItem> getAllInventoryItems() throws LIMSRuntimeException {
		List<InventoryItem> inventoryItems;
		try {
			String sql = "from InventoryItem";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			inventoryItems = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("InventoryItemDAOImpl", "getAllInventoryItems()", e.toString());
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
//				sessionFactory.getCurrentSession().delete(data);
//				// sessionFactory.getCurrentSession().flush(); // CSL remove old
//				// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			}
//		} catch (Exception e) {
//			LogEvent.logError("InventoryItemDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryItem deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(InventoryItem inventoryItem) throws LIMSRuntimeException {
//		try {
//			String id = (String) sessionFactory.getCurrentSession().save(inventoryItem);
//			inventoryItem.setId(id);
//
//			String sysUserId = inventoryItem.getSysUserId();
//			String tableName = "INVENTORY_ITEM";
//			auditDAO.saveNewHistory(inventoryItem, sysUserId, tableName);
//
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//
//		} catch (Exception e) {
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
//			sessionFactory.getCurrentSession().merge(inventoryItem);
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			// sessionFactory.getCurrentSession().evict // CSL remove old(inventoryItem);
//			// sessionFactory.getCurrentSession().refresh // CSL remove old(inventoryItem);
//		} catch (Exception e) {
//			LogEvent.logError("InventoryItemDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryItem updateData()", e);
//		}
//	}

//	@Override
//	public void getData(InventoryItem inventoryItem) throws LIMSRuntimeException {
//		try {
//			InventoryItem tmpInventoryItem = sessionFactory.getCurrentSession().get(InventoryItem.class,
//					inventoryItem.getId());
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			if (tmpInventoryItem != null) {
//				PropertyUtils.copyProperties(inventoryItem, tmpInventoryItem);
//			} else {
//				inventoryItem.setId(null);
//			}
//		} catch (Exception e) {
//			LogEvent.logError("InventoryItemDAOImpl", "getData()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryItem getData()", e);
//		}
//	}

	@Override
	public InventoryItem readInventoryItem(String idString) throws LIMSRuntimeException {
		InventoryItem data = null;
		try {
			data = sessionFactory.getCurrentSession().get(InventoryItem.class, idString);
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("InventoryItemDAOImpl", "readInventoryItem()", e.toString());
			throw new LIMSRuntimeException("Error in InventoryItem readInventoryItem()", e);
		}

		return data;
	}

//	@Override
//	public InventoryItem getInventoryItemById(InventoryItem inventoryItem) throws LIMSRuntimeException {
//		try {
//			InventoryItem re = sessionFactory.getCurrentSession().get(InventoryItem.class, inventoryItem.getId());
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			return re;
//		} catch (Exception e) {
//			LogEvent.logError("InventoryItemDAOImpl", "getInventoryItemById()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryItem getInventoryItemById()", e);
//		}
//	}

}