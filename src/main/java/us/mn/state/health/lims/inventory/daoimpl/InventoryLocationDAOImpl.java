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

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.inventory.dao.InventoryLocationDAO;
import us.mn.state.health.lims.inventory.valueholder.InventoryLocation;

@Component
@Transactional
public class InventoryLocationDAOImpl extends BaseDAOImpl<InventoryLocation, String> implements InventoryLocationDAO {

	public InventoryLocationDAOImpl() {
		super(InventoryLocation.class);
	}

//	@Override
//	@SuppressWarnings("unchecked")
//	public List<InventoryLocation> getAllInventoryLocations() throws LIMSRuntimeException {
//		List<InventoryLocation> inventoryItems;
//		try {
//			String sql = "from InventoryLocation";
//			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
//			inventoryItems = query.list();
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//		} catch (Exception e) {
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
//				sessionFactory.getCurrentSession().delete(data);
//				// sessionFactory.getCurrentSession().flush(); // CSL remove old
//				// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			}
//		} catch (Exception e) {
//			LogEvent.logError("InventoryLocationDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryLocation deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(InventoryLocation inventoryItem) throws LIMSRuntimeException {
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
//			sessionFactory.getCurrentSession().merge(inventoryItem);
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			// sessionFactory.getCurrentSession().evict // CSL remove old(inventoryItem);
//			// sessionFactory.getCurrentSession().refresh // CSL remove old(inventoryItem);
//		} catch (Exception e) {
//			LogEvent.logError("InventoryLocationDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryLocation updateData()", e);
//		}
//	}

//	@Override
//	public void getData(InventoryLocation inventoryItem) throws LIMSRuntimeException {
//		try {
//			InventoryLocation tmpInventoryLocation = sessionFactory.getCurrentSession().get(InventoryLocation.class,
//					inventoryItem.getId());
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			if (tmpInventoryLocation != null) {
//				PropertyUtils.copyProperties(inventoryItem, tmpInventoryLocation);
//			} else {
//				inventoryItem.setId(null);
//			}
//		} catch (Exception e) {
//			LogEvent.logError("InventoryLocationDAOImpl", "getData()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryLocation getData()", e);
//		}
//	}

	public InventoryLocation readInventoryLocation(String idString) throws LIMSRuntimeException {
		InventoryLocation data = null;
		try {
			data = sessionFactory.getCurrentSession().get(InventoryLocation.class, idString);
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("InventoryLocationDAOImpl", "readInventoryLocation()", e.toString());
			throw new LIMSRuntimeException("Error in InventoryLocation readInventoryLocation()", e);
		}

		return data;
	}

//	@Override
//	public InventoryLocation getInventoryLocationById(InventoryLocation inventoryItem) throws LIMSRuntimeException {
//		try {
//			InventoryLocation re = sessionFactory.getCurrentSession().get(InventoryLocation.class,
//					inventoryItem.getId());
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			return re;
//		} catch (Exception e) {
//			LogEvent.logError("InventoryLocationDAOImpl", "getInventoryLocationById()", e.toString());
//			throw new LIMSRuntimeException("Error in InventoryLocation getInventoryLocationById()", e);
//		}
//	}

}