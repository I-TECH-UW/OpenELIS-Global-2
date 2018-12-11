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
package us.mn.state.health.lims.inventory.daoimpl;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Query;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.inventory.dao.InventoryReceiptDAO;
import us.mn.state.health.lims.inventory.valueholder.InventoryReceipt;

public class InventoryReceiptDAOImpl extends BaseDAOImpl implements InventoryReceiptDAO {

	@SuppressWarnings("unchecked")
	public List<InventoryReceipt> getAllInventoryReceipts() throws LIMSRuntimeException {
		List<InventoryReceipt> inventoryReceipts;
		try {
			String sql = "from InventoryReceipt";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			inventoryReceipts = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("InventoryReceiptDAOImpl","getAllInventoryReceipts()",e.toString());
			throw new LIMSRuntimeException("Error in InventoryReceipt getAllInventoryReceipts()", e);
		}

		return inventoryReceipts;
	}

	public void deleteData(List<InventoryReceipt> inventoryReceipts) throws LIMSRuntimeException {
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (InventoryReceipt data: inventoryReceipts) {
			
				InventoryReceipt oldData = getInventoryReceiptById(data.getId());
				InventoryReceipt newData = new InventoryReceipt();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "INVENTORY_RECEIPT";
				auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
			}
				
			for (InventoryReceipt data: inventoryReceipts) {

				data = getInventoryReceiptById(data.getId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}			
		} catch (Exception e) {
			LogEvent.logError("InventoryReceiptDAOImpl","deleteData()",e.toString());
			   throw new LIMSRuntimeException("Error in InventoryReceipt deleteData()", e);
		}
	}

	public boolean insertData(InventoryReceipt inventoryReceipt) throws LIMSRuntimeException {	
		try {
			String id = (String)HibernateUtil.getSession().save(inventoryReceipt);
			inventoryReceipt.setId(id);
			
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = inventoryReceipt.getSysUserId();
			String tableName = "INVENTORY_RECEIPT";
			auditDAO.saveNewHistory(inventoryReceipt,sysUserId,tableName);
			
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
							
		} catch (Exception e) {
			LogEvent.logError("InventoryReceiptDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in InventoryReceipt insertData()", e);
		}
		
		return true;
	}

	public void updateData(InventoryReceipt inventoryReceipt) throws LIMSRuntimeException {		
		InventoryReceipt oldData = getInventoryReceiptById(inventoryReceipt.getId());
		InventoryReceipt newData = inventoryReceipt;

		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = inventoryReceipt.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "INVENTORY_RECEIPT";
			auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
		
			HibernateUtil.getSession().merge(inventoryReceipt);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(inventoryReceipt);
			HibernateUtil.getSession().refresh(inventoryReceipt);
		} catch (Exception e) {
			LogEvent.logError("InventoryReceiptDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in InventoryReceipt updateData()", e);
		}
	}

	public void getData(InventoryReceipt inventoryReceipt) throws LIMSRuntimeException {
		try {
			InventoryReceipt tmpInventoryReceipt = (InventoryReceipt)HibernateUtil.getSession().get(InventoryReceipt.class, inventoryReceipt.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (tmpInventoryReceipt != null) {
				PropertyUtils.copyProperties(inventoryReceipt, tmpInventoryReceipt);
			} else {
				inventoryReceipt.setId(null);
			}
		} catch (Exception e) {
			LogEvent.logError("InventoryReceiptDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in InventoryReceipt getData()", e);
		}
	}

	public InventoryReceipt getInventoryReceiptById(String idString) throws LIMSRuntimeException{
		InventoryReceipt data = null;
		try {
			data = (InventoryReceipt)HibernateUtil.getSession().get(InventoryReceipt.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("InventoryReceiptDAOImpl","readInventoryReceipt()",e.toString());
			throw new LIMSRuntimeException("Error in InventoryReceipt readInventoryReceipt()", e);
		}		
		
		return data;
	}


	public InventoryReceipt getInventoryReceiptByInventoryItemId(String id) throws LIMSRuntimeException {
		InventoryReceipt inventory = null;
		

		try {
			List inventoryReceipts;
			
			String sql = "from InventoryReceipt where invitem_id = :id";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("id", Integer.parseInt(id));
			inventoryReceipts = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			
			if( inventoryReceipts != null && inventoryReceipts.size() > 0){
				inventory = (InventoryReceipt)inventoryReceipts.get(0);
			}
			
		} catch (Exception e) {
			LogEvent.logError("InventoryReceiptDAOImpl","getInventoryReceiptByInventoryItemId()",e.toString());
			throw new LIMSRuntimeException("Error in InventoryReceipt getInventoryReceiptByInventoryItemId()", e);
		}

		return inventory;
	}
	
}