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

import org.apache.commons.beanutils.PropertyUtils;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.inventory.dao.InventoryItemDAO;
import us.mn.state.health.lims.inventory.valueholder.InventoryItem;

public class InventoryItemDAOImpl extends BaseDAOImpl implements InventoryItemDAO {

	@SuppressWarnings("unchecked")
	public List<InventoryItem> getAllInventoryItems() throws LIMSRuntimeException {
		List<InventoryItem> inventoryItems;
		try {
			String sql = "from InventoryItem";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			inventoryItems = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("InventoryItemDAOImpl","getAllInventoryItems()",e.toString());
			throw new LIMSRuntimeException("Error in InventoryItem getAllInventoryItems()", e);
		}

		return inventoryItems;
	}

	public void deleteData(List<InventoryItem> inventoryItems) throws LIMSRuntimeException {
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (InventoryItem data: inventoryItems) {
			
				InventoryItem oldData = readInventoryItem(data.getId());
				InventoryItem newData = new InventoryItem();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "INVENTORY_ITEM";
				auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
			}
				
			for (InventoryItem data: inventoryItems) {

				data = readInventoryItem(data.getId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}			
		} catch (Exception e) {
			LogEvent.logError("InventoryItemDAOImpl","deleteData()",e.toString());
			   throw new LIMSRuntimeException("Error in InventoryItem deleteData()", e);
		}
	}

	public boolean insertData(InventoryItem inventoryItem) throws LIMSRuntimeException {	
		try {
			String id = (String)HibernateUtil.getSession().save(inventoryItem);
			inventoryItem.setId(id);
			
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = inventoryItem.getSysUserId();
			String tableName = "INVENTORY_ITEM";
			auditDAO.saveNewHistory(inventoryItem,sysUserId,tableName);
			
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
							
		} catch (Exception e) {
			LogEvent.logError("InventoryItemDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in InventoryItem insertData()", e);
		}
		
		return true;
	}

	public void updateData(InventoryItem inventoryItem) throws LIMSRuntimeException {		
		InventoryItem oldData = readInventoryItem(inventoryItem.getId());
		InventoryItem newData = inventoryItem;

		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = inventoryItem.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "INVENTORY_ITEM";
			auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
		
			HibernateUtil.getSession().merge(inventoryItem);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(inventoryItem);
			HibernateUtil.getSession().refresh(inventoryItem);
		} catch (Exception e) {
			LogEvent.logError("InventoryItemDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in InventoryItem updateData()", e);
		}
	}

	public void getData(InventoryItem inventoryItem) throws LIMSRuntimeException {
		try {
			InventoryItem tmpInventoryItem = (InventoryItem)HibernateUtil.getSession().get(InventoryItem.class, inventoryItem.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (tmpInventoryItem != null) {
				PropertyUtils.copyProperties(inventoryItem, tmpInventoryItem);
			} else {
				inventoryItem.setId(null);
			}
		} catch (Exception e) {
			LogEvent.logError("InventoryItemDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in InventoryItem getData()", e);
		}
	}

	public InventoryItem readInventoryItem(String idString) throws LIMSRuntimeException{
		InventoryItem data = null;
		try {
			data = (InventoryItem)HibernateUtil.getSession().get(InventoryItem.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("InventoryItemDAOImpl","readInventoryItem()",e.toString());
			throw new LIMSRuntimeException("Error in InventoryItem readInventoryItem()", e);
		}		
		
		return data;
	}

	public InventoryItem getInventoryItemById(InventoryItem inventoryItem) throws LIMSRuntimeException {
		try {
			InventoryItem re = (InventoryItem)HibernateUtil.getSession().get(InventoryItem.class, inventoryItem.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			return re;
		} catch (Exception e) {
			LogEvent.logError("InventoryItemDAOImpl","getInventoryItemById()",e.toString());
			throw new LIMSRuntimeException("Error in InventoryItem getInventoryItemById()", e);
		}
	}
	
}