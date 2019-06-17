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

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.systemusermodule.dao.RoleModuleDAO;
import us.mn.state.health.lims.systemusermodule.valueholder.RoleModule;

/**
 *
 */
@Component
@Transactional
@Qualifier("RoleModuleDAO")
public class RoleModuleDAOImpl extends BaseDAOImpl<RoleModule, String> implements RoleModuleDAO {

	public RoleModuleDAOImpl() {
		super(RoleModule.class);
	}

//	@Override
//	public void deleteData(List roleModules) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < roleModules.size(); i++) {
//				RoleModule data = (RoleModule) roleModules.get(i);
//
//				RoleModule oldData = readRoleModule(data.getId());
//				RoleModule newData = new RoleModule();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "SYSTEM_ROLE_MODULE";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (Exception e) {
//			LogEvent.logError("RoleModuleDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in RoleModule AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < roleModules.size(); i++) {
//				RoleModule data = (RoleModule) roleModules.get(i);
//				data = readRoleModule(data.getId());
//				sessionFactory.getCurrentSession().delete(data);
//				// sessionFactory.getCurrentSession().flush(); // CSL remove old
//				// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			}
//		} catch (Exception e) {
//			LogEvent.logError("RoleModuleDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in RoleModule deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(RoleModule permissionModule) throws LIMSRuntimeException {
//
//		try {
//			if (duplicateRoleModuleExists(permissionModule)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + permissionModule.getPermissionAgentId());
//			}
//
////			String id = (String) sessionFactory.getCurrentSession().save(permissionModule);
//			String id = (String) sessionFactory.getCurrentSession().save(permissionModule);
//			permissionModule.setId(id);
//
//			// add to audit trail
//
//			String sysUserId = permissionModule.getSysUserId();
//			String tableName = "SYSTEM_ROLE_MODULE";
//			auditDAO.saveNewHistory(permissionModule, sysUserId, tableName);
//
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//		} catch (Exception e) {
//			LogEvent.logError("RoleModuleDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in RoleModule insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(RoleModule roleModule) throws LIMSRuntimeException {
//
//		try {
//			if (duplicateRoleModuleExists(roleModule)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + roleModule.getPermissionAgentId());
//			}
//		} catch (Exception e) {
//			LogEvent.logError("RoleModuleDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in RoleModule updateData()", e);
//		}
//
//		RoleModule oldData = readRoleModule(roleModule.getId());
//		RoleModule newData = roleModule;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = roleModule.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "SYSTEM_ROLE_MODULE";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (Exception e) {
//			LogEvent.logError("RoleModuleDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in RoleModule AuditTrail updateData()", e);
//		}
//
//		try {
//			sessionFactory.getCurrentSession().merge(roleModule);
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			// sessionFactory.getCurrentSession().evict // CSL remove old(roleModule);
//			// sessionFactory.getCurrentSession().refresh // CSL remove old(roleModule);
//		} catch (Exception e) {
//			LogEvent.logError("RoleModuleDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in RoleModule updateData()", e);
//		}
//	}

	@Override
	@Transactional(readOnly = true)
	public void getData(RoleModule systemUserModule) throws LIMSRuntimeException {
		try {
			RoleModule sysUserModule = sessionFactory.getCurrentSession().get(RoleModule.class,
					systemUserModule.getId());
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
			if (sysUserModule != null) {
				PropertyUtils.copyProperties(systemUserModule, sysUserModule);
			} else {
				systemUserModule.setId(null);
			}
		} catch (Exception e) {
			LogEvent.logError("RoleModuleDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in RoleModule getData()", e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List getAllPermissionModules() throws LIMSRuntimeException {
		List list;
		try {
			String sql = "from RoleModule";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("RoleModuleDAOImpl", "getAllSystemModules()", e.toString());
			throw new LIMSRuntimeException("Error in RoleModule getAllSystemModules()", e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List getAllPermissionModulesByAgentId(int systemUserId) throws LIMSRuntimeException {
		List list;
		try {
			String sql = "from RoleModule s where s.role.id = :param";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter("param", systemUserId);
			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("RoleModuleDAOImpl", "getAllRoleModulesBySystemUserId()", e.toString());
			throw new LIMSRuntimeException("Error in RoleModule getAllRoleModulesBySystemUserId()", e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List getPageOfPermissionModules(int startingRecNo) throws LIMSRuntimeException {
		List list;
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			String sql = "from RoleModule s order by s.role.id";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);

			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("RoleModuleDAOImpl", "getPageOfRoleModules()", e.toString());
			throw new LIMSRuntimeException("Error in RoleModule getPageOfRoleModules()", e);
		}

		return list;
	}

	public RoleModule readRoleModule(String idString) {
		RoleModule sysUserModule;
		try {
			sysUserModule = sessionFactory.getCurrentSession().get(RoleModule.class, idString);
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("RoleModuleDAOImpl", "readRoleModule()", e.toString());
			throw new LIMSRuntimeException("Error in Gender readRoleModule(idString)", e);
		}

		return sysUserModule;
	}

	@Override
	@Transactional(readOnly = true)
	public RoleModule getRoleModuleByRoleAndModuleId(String roleId, String moduleId) {
		String sql = "From RoleModule rm where rm.systemModule.id = :moduleId and rm.role.id = :roleId";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setInteger("moduleId", Integer.parseInt(moduleId));
			query.setInteger("roleId", Integer.parseInt(roleId));
			List<RoleModule> modules = query.list();
			// closeSession(); // CSL remove old
			return modules.isEmpty() ? new RoleModule() : modules.get(0);
		} catch (HibernateException he) {
			handleException(he, "getRoleModuleByRoleAndModuleId");
		}

		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List getNextPermissionModuleRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "RoleModule", RoleModule.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List getPreviousPermissionModuleRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "RoleModule", RoleModule.class);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getTotalPermissionModuleCount() throws LIMSRuntimeException {
		return getTotalCount("RoleModule", RoleModule.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
		int currentId = Integer.valueOf(id);
		String tablePrefix = getTablePrefix(table);

		List list;
		int rrn;
		try {
			String sql = "select rm.id from RoleModule rm order by rm.role.id";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
			rrn = list.indexOf(String.valueOf(currentId));

			list = sessionFactory.getCurrentSession().getNamedQuery(tablePrefix + "getNext").setFirstResult(rrn + 1)
					.setMaxResults(2).list();

		} catch (Exception e) {
			LogEvent.logError("RoleModuleDAOImpl", "getNextRecord()", e.toString());
			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
		int currentId = Integer.valueOf(id);
		String tablePrefix = getTablePrefix(table);

		List list;
		int rrn;
		try {
			String sql = "select rm.id from RoleModule rm order by rm.role.id";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
			rrn = list.indexOf(String.valueOf(currentId));

			list = sessionFactory.getCurrentSession().getNamedQuery(tablePrefix + "getPrevious").setFirstResult(rrn + 1)
					.setMaxResults(2).list();

		} catch (Exception e) {
			LogEvent.logError("RoleModuleDAOImpl", "getPreviousRecord()", e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		}

		return list;
	}

	@Override
	public boolean duplicateRoleModuleExists(RoleModule roleModule) throws LIMSRuntimeException {

		try {

			List list;

			String sql = "from RoleModule s where s.role.id = :param and s.systemModule.id = :param2 and s.id != :param3";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setInteger("param", Integer.parseInt(roleModule.getRole().getId()));
			query.setInteger("param2", Integer.parseInt(roleModule.getSystemModule().getId()));

			String systemUserModuleId = "0";
			if (!StringUtil.isNullorNill(roleModule.getId())) {
				systemUserModuleId = roleModule.getId();
			}
			query.setInteger("param3", Integer.parseInt(systemUserModuleId));

			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old

			return list.size() > 0;

		} catch (Exception e) {
			LogEvent.logError("RoleModuleDAOImpl", "duplicateRoleModuleExists()", e.toString());
			throw new LIMSRuntimeException("Error in duplicateRoleModuleExists()", e);
		}
	}

//	@Override
//	public boolean isAgentAllowedAccordingToName(String id, String name) throws LIMSRuntimeException {
//		return userRoleDAO.userInRole(id, name);
//	}

	@Override
	public boolean doesUserHaveAnyModules(int userId) throws LIMSRuntimeException {
		try {
			String sql = "select count(*) from system_user_role where system_user_id = :userId";
			Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
			query.setInteger("userId", userId);
			int roleCount = ((BigInteger) query.uniqueResult()).intValue();
			return roleCount > 0;
		} catch (HibernateException he) {
			LogEvent.logError("RoleModuleDAOImpl", "doesUserHaveAnyModules(int)", he.toString());
			throw new LIMSRuntimeException("Error in doesUserHaveAnyModules(int)", he);
		}
	}
}