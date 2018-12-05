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
package us.mn.state.health.lims.systemusermodule.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.systemusermodule.dao.PermissionAgentModuleDAO;
import us.mn.state.health.lims.systemusermodule.valueholder.PermissionModule;
import us.mn.state.health.lims.systemusermodule.valueholder.SystemUserModule;

/**
 *  @author     Hung Nguyen (Hung.Nguyen@health.state.mn.us)
 */
public class SystemUserModuleDAOImpl extends BaseDAOImpl implements PermissionAgentModuleDAO {

	public void deleteData(List systemUserModules) throws LIMSRuntimeException {
		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < systemUserModules.size(); i++) {
				SystemUserModule data = (SystemUserModule)systemUserModules.get(i);
			
				SystemUserModule oldData = (SystemUserModule)readSystemUserModule(data.getId());
				SystemUserModule newData = new SystemUserModule();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "SYSTEM_USER_MODULE";
				auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
			}
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SystemUserModuleDAOImpl","AuditTrail deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in SystemUserModule AuditTrail deleteData()", e);
		}  
		
		try {					
			for (int i = 0; i < systemUserModules.size(); i++) {
				SystemUserModule data = (SystemUserModule) systemUserModules.get(i);
				//bugzilla 2206
				data = (SystemUserModule)readSystemUserModule(data.getId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}						
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SystemUserModuleDAOImpl","deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in SystemUserModule deleteData()", e);
		}
	}

	public boolean insertData(PermissionModule systemUserModule) throws LIMSRuntimeException {	
		
		try {
			if (duplicateSystemUserModuleExists((SystemUserModule)systemUserModule)) {
				throw new LIMSDuplicateRecordException("Duplicate record exists for " + systemUserModule.getPermissionAgentId());
			}
			
			String id = (String)HibernateUtil.getSession().save(systemUserModule);
			systemUserModule.setId(id);
			
			//add to audit trail
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = systemUserModule.getSysUserId();
			String tableName = "SYSTEM_USER_MODULE";
			auditDAO.saveNewHistory(systemUserModule,sysUserId,tableName);			
			
			HibernateUtil.getSession().flush();	
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SystemUserModuleDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in SystemUserModule insertData()", e);
		} 
		
		return true;
	}

	public void updateData(PermissionModule systemUserModule) throws LIMSRuntimeException {
	
		try {
			if (duplicateSystemUserModuleExists((SystemUserModule)systemUserModule)) {
				throw new LIMSDuplicateRecordException("Duplicate record exists for " + systemUserModule.getPermissionAgentId());
			}
		} catch (Exception e) {
    		//bugzilla 2154
			LogEvent.logError("SystemUserModuleDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in SystemUserModule updateData()", e);
		}			
			
		SystemUserModule oldData = (SystemUserModule)readSystemUserModule(systemUserModule.getId());
		SystemUserModule newData = (SystemUserModule)systemUserModule;

		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = systemUserModule.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "SYSTEM_USER_MODULE";
			auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SystemUserModuleDAOImpl","AuditTrail updateData()",e.toString());
			throw new LIMSRuntimeException("Error in SystemUserModule AuditTrail updateData()", e);
		}  
		
		try {			
			HibernateUtil.getSession().merge(systemUserModule);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(systemUserModule);
			HibernateUtil.getSession().refresh(systemUserModule);
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SystemUserModuleDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in SystemUserModule updateData()", e);
		} 
	}

	public void getData(PermissionModule systemUserModule) throws LIMSRuntimeException {	
		try {					
			SystemUserModule sysUserModule = (SystemUserModule)HibernateUtil.getSession().get(SystemUserModule.class, systemUserModule.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (sysUserModule != null) {
			  PropertyUtils.copyProperties(systemUserModule, sysUserModule);
			} else {
				systemUserModule.setId(null);
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SystemUserModuleDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in SystemUserModule getData()", e);
		}
	}

	public List getAllPermissionModules() throws LIMSRuntimeException {		
		List list = new Vector();
		try {
			String sql = "from SystemUserModule";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch(Exception e) {
			//bugzilla 2154
			LogEvent.logError("SystemUserModuleDAOImpl","getAllSystemModules()",e.toString());
			throw new LIMSRuntimeException("Error in SystemUserModule getAllSystemModules()", e);
		} 

		return list;
	}

	public List getAllPermissionModulesByAgentId(int systemUserId) throws LIMSRuntimeException {		
		List list = new Vector();
		try {
			String sql = "from SystemUserModule s where s.systemUser.id = :param";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", systemUserId);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch(Exception e) {
			//bugzilla 2154
			LogEvent.logError("SystemUserModuleDAOImpl","getAllSystemUserModulesBySystemUserId()",e.toString());
			throw new LIMSRuntimeException("Error in SystemUserModule getAllSystemUserModulesBySystemUserId()", e);
		} 	
	
		return list;
		
	}
	
	public List getPageOfPermissionModules(int startingRecNo) throws LIMSRuntimeException {		
		List list = new Vector();
		try {			
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
			
			String sql = "from SystemUserModule s order by s.systemUser.id";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(startingRecNo-1);
			query.setMaxResults(endingRecNo-1); 
			
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SystemUserModuleDAOImpl","getPageOfSystemUserModules()",e.toString());
			throw new LIMSRuntimeException("Error in SystemUserModule getPageOfSystemUserModules()", e);
		} 
		
		return list;
	}

	public SystemUserModule readSystemUserModule(String idString) {		
		SystemUserModule sysUserModule = null;
		try {			
			sysUserModule = (SystemUserModule)HibernateUtil.getSession().get(SystemUserModule.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SystemUserModuleDAOImpl","readSystemUserModule()",e.toString());
			throw new LIMSRuntimeException("Error in Gender readSystemUserModule(idString)", e);
		}			
		
		return sysUserModule;
	}
	
	public List getNextPermissionModuleRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "SystemUserModule", SystemUserModule.class);
	}

	public List getPreviousPermissionModuleRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "SystemUserModule", SystemUserModule.class);
	}
	
	public Integer getTotalPermissionModuleCount() throws LIMSRuntimeException {
		return getTotalCount("SystemUserModule", SystemUserModule.class);
	}
	
	public List getNextRecord(String id, String table, Class clazz)
			throws LIMSRuntimeException {
		int currentId = (Integer.valueOf(id)).intValue();
		String tablePrefix = getTablePrefix(table);

		List list = new Vector();
		int rrn = 0;
		try {
    		String sql = "select sum.id from SystemUserModule sum order by sum.systemUser.id";
 			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			rrn = list.indexOf(String.valueOf(currentId));

			list = HibernateUtil.getSession().getNamedQuery(
					tablePrefix + "getNext").setFirstResult(
					rrn + 1).setMaxResults(2).list();

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SystemUserModuleDAOImpl","getNextRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getNextRecord() for "
					+ table, e);
		}
		
		return list;
	}

	public List getPreviousRecord(String id, String table, Class clazz)
			throws LIMSRuntimeException {
		int currentId = (Integer.valueOf(id)).intValue();
		String tablePrefix = getTablePrefix(table);

		List list = new Vector();
		int rrn = 0;
		try {
			String sql = "select sum.id from SystemUserModule sum order by sum.systemUser.id";
 			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			rrn = list.indexOf(String.valueOf(currentId));

			list = HibernateUtil.getSession().getNamedQuery(
					tablePrefix + "getPrevious").setFirstResult(
					rrn + 1).setMaxResults(2).list();

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SystemUserModuleDAOImpl","getPreviousRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for "
					+ table, e);
		}

		return list;
	}
	
	private boolean duplicateSystemUserModuleExists(SystemUserModule systemUserModule) throws LIMSRuntimeException {
		try {

			List list = new ArrayList();

			String sql = "from SystemUserModule s where s.systemUser.id = :param and s.systemModule.id = :param2 and s.id != :param3";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", systemUserModule.getSystemUser().getId());
			query.setParameter("param2", systemUserModule.getSystemModule().getId());
	
			String systemUserModuleId = "0";
			if (!StringUtil.isNullorNill(systemUserModule.getId())) {
				systemUserModuleId = systemUserModule.getId();
			}
			query.setParameter("param3", systemUserModuleId);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			if (list.size() > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SystemUserModuleDAOImpl","duplicateSystemUserModuleExists()",e.toString());
			throw new LIMSRuntimeException("Error in duplicateSystemUserModuleExists()", e);
		}
	}

	public boolean isAgentAllowedAccordingToName(String id, String name) throws LIMSRuntimeException {
		try {
			List list = new ArrayList();

			String sql = "from SystemUserModule as sum join fetch sum.systemModule as sm where sum.systemUser.id = :id and sm.systemModuleName = :name";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("id", Integer.parseInt(id));
			query.setParameter("name", name);
	
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			return list.size() > 0;
		} catch (Exception e) {
			LogEvent.logError("SystemUserModuleDAOImpl","isUserAllowedAccordingToName()",e.toString());
			throw new LIMSRuntimeException("Error in isUserAllowedAccordingToName()", e);
		}
	}

	public boolean doesUserHaveAnyModules(int userId) throws LIMSRuntimeException {
		List userModuleList = getAllPermissionModulesByAgentId(userId);
		return userModuleList.size() > 0;
	}
}