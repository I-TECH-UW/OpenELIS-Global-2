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
package us.mn.state.health.lims.result.daoimpl;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.result.dao.ResultInventoryDAO;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.result.valueholder.ResultInventory;


public class ResultInventoryDAOImpl extends BaseDAOImpl implements ResultInventoryDAO {

	@SuppressWarnings("unchecked")
	public List<ResultInventory> getAllResultInventoryss() throws LIMSRuntimeException {
		List<ResultInventory> resultInventories;
		try {
			String sql = "from ResultInventory";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			resultInventories = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("ResultInventoryDAOImpl","getAllResultInventorys()",e.toString());
			throw new LIMSRuntimeException("Error in ResultInventory getAllResultInventorys()", e);
		}

		return resultInventories;
	}

	public void deleteData(List resultInventories) throws LIMSRuntimeException {
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < resultInventories.size(); i++) {
				ResultInventory data = (ResultInventory)resultInventories.get(i);
			
				ResultInventory oldData = (ResultInventory)readResultInventory(data.getId());
				ResultInventory newData = new ResultInventory();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "RESULT_INVENTORY";
				auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
			}
		}  catch (Exception e) {

			LogEvent.logError("ResultInventoryDAOImpl","AuditTrail deleteData()",e.toString());
		    throw new LIMSRuntimeException("Error in ResultInventory AuditTrail deleteData()", e);
		}  
		
		try {		
			for (int i = 0; i < resultInventories.size(); i++) {
				ResultInventory data = (ResultInventory) resultInventories.get(i);	

				data = (ResultInventory)readResultInventory(data.getId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}			
		} catch (Exception e) {
			LogEvent.logError("ResultInventoryDAOImpl","deleteData()",e.toString());
			   throw new LIMSRuntimeException("Error in ResultInventory deleteData()", e);
		}
	}

	public boolean insertData(ResultInventory resultInventory) throws LIMSRuntimeException {	
		try {
			String id = (String)HibernateUtil.getSession().save(resultInventory);
			resultInventory.setId(id);
			
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = resultInventory.getSysUserId();
			String tableName = "RESULT_INVENTORY";
			auditDAO.saveNewHistory(resultInventory,sysUserId,tableName);
			
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
							
		} catch (Exception e) {
			LogEvent.logError("ResultInventoryDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in ResultInventory insertData()", e);
		}
		
		return true;
	}

	public void updateData(ResultInventory resultInventory) throws LIMSRuntimeException {		
		ResultInventory oldData = (ResultInventory)readResultInventory(resultInventory.getId());
		ResultInventory newData = resultInventory;

		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = resultInventory.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "RESULT_INVENTORY";
			auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
		}  catch (Exception e) {
			LogEvent.logError("ResultInventoryDAOImpl","AuditTrail insertData()",e.toString());
			throw new LIMSRuntimeException("Error in ResultInventory AuditTrail updateData()", e);
		}  
							
		try {
			HibernateUtil.getSession().merge(resultInventory);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(resultInventory);
			HibernateUtil.getSession().refresh(resultInventory);
		} catch (Exception e) {
			LogEvent.logError("ResultInventoryDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in ResultInventory updateData()", e);
		}
	}

	public void getData(ResultInventory resultInventory) throws LIMSRuntimeException {
		try {
			ResultInventory tmpResultInventory = (ResultInventory)HibernateUtil.getSession().get(ResultInventory.class, resultInventory.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (tmpResultInventory != null) {
				PropertyUtils.copyProperties(resultInventory, tmpResultInventory);
			} else {
				resultInventory.setId(null);
			}
		} catch (Exception e) {
			LogEvent.logError("ResultInventoryDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in ResultInventory getData()", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ResultInventory> getResultInventorysByResult(Result result) throws LIMSRuntimeException {
		List<ResultInventory> resultInventories = null;
		try {
		
			String sql = "from ResultInventory r where r.resultId = :resultId";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("resultId", Integer.parseInt(result.getId()));

			resultInventories = query.list();		
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			
            return resultInventories;

		} catch (Exception e) {
			LogEvent.logError("ResultInventoryDAOImpl","getResultInventoryByResult()",e.toString());
			throw new LIMSRuntimeException("Error in ResultInventory getResultInventoryByResult()", e);
		}						
	}

	public ResultInventory readResultInventory(String idString) {
		ResultInventory data = null;
		try {
			data = (ResultInventory)HibernateUtil.getSession().get(ResultInventory.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("ResultInventoryDAOImpl","readResultInventory()",e.toString());
			throw new LIMSRuntimeException("Error in ResultInventory readResultInventory()", e);
		}		
		
		return data;
	}

	public ResultInventory getResultInventoryById(ResultInventory resultInventory) throws LIMSRuntimeException {
		try {
			ResultInventory re = (ResultInventory)HibernateUtil.getSession().get(ResultInventory.class, resultInventory.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			return re;
		} catch (Exception e) {
			LogEvent.logError("ResultInventoryDAOImpl","getResultInventoryById()",e.toString());
			throw new LIMSRuntimeException("Error in ResultInventory getResultInventoryById()", e);
		}
	}
	
}