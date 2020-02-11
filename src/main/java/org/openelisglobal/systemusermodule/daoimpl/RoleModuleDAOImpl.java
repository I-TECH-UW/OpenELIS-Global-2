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
package org.openelisglobal.systemusermodule.daoimpl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.systemusermodule.dao.RoleModuleDAO;
import org.openelisglobal.systemusermodule.valueholder.RoleModule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
//		} catch (RuntimeException e) {
//			LogEvent.logError("RoleModuleDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in RoleModule AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < roleModules.size(); i++) {
//				RoleModule data = (RoleModule) roleModules.get(i);
//				data = readRoleModule(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
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
////			String id = (String) entityManager.unwrap(Session.class).save(permissionModule);
//			String id = (String) entityManager.unwrap(Session.class).save(permissionModule);
//			permissionModule.setId(id);
//
//			// add to audit trail
//
//			String sysUserId = permissionModule.getSysUserId();
//			String tableName = "SYSTEM_ROLE_MODULE";
//			auditDAO.saveNewHistory(permissionModule, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
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
//		} catch (RuntimeException e) {
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
//		} catch (RuntimeException e) {
//			LogEvent.logError("RoleModuleDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in RoleModule AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(roleModule);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(roleModule);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(roleModule);
//		} catch (RuntimeException e) {
//			LogEvent.logError("RoleModuleDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in RoleModule updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(RoleModule systemUserModule) throws LIMSRuntimeException {
        try {
            RoleModule sysUserModule = entityManager.unwrap(Session.class).get(RoleModule.class,
                    systemUserModule.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (sysUserModule != null) {
                PropertyUtils.copyProperties(systemUserModule, sysUserModule);
            } else {
                systemUserModule.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in RoleModule getData()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleModule> getAllPermissionModules() throws LIMSRuntimeException {
        List<RoleModule> list;
        try {
            String sql = "from RoleModule";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in RoleModule getAllSystemModules()", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleModule> getAllPermissionModulesByAgentId(int systemUserId) throws LIMSRuntimeException {
        List<RoleModule> list;
        try {
            String sql = "from RoleModule s where s.role.id = :param";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", systemUserId);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in RoleModule getAllRoleModulesBySystemUserId()", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleModule> getPageOfPermissionModules(int startingRecNo) throws LIMSRuntimeException {
        List<RoleModule> list;
        try {
            // calculate maxRow to be one more than the page size
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            String sql = "from RoleModule s order by s.role.id";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in RoleModule getPageOfRoleModules()", e);
        }

        return list;
    }

    public RoleModule readRoleModule(String idString) {
        RoleModule sysUserModule;
        try {
            sysUserModule = entityManager.unwrap(Session.class).get(RoleModule.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Gender readRoleModule(idString)", e);
        }

        return sysUserModule;
    }

    @Override
    @Transactional(readOnly = true)
    public RoleModule getRoleModuleByRoleAndModuleId(String roleId, String moduleId) {
        String sql = "From RoleModule rm where rm.systemModule.id = :moduleId and rm.role.id = :roleId";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("moduleId", Integer.parseInt(moduleId));
            query.setInteger("roleId", Integer.parseInt(roleId));
            List<RoleModule> modules = query.list();
            // closeSession(); // CSL remove old
            return modules.isEmpty() ? new RoleModule() : modules.get(0);
        } catch (HibernateException e) {
            handleException(e, "getRoleModuleByRoleAndModuleId");
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getTotalPermissionModuleCount() throws LIMSRuntimeException {
        return getCount();
    }

    @Override
    public boolean duplicateRoleModuleExists(RoleModule roleModule) throws LIMSRuntimeException {

        try {

            List<RoleModule> list;

            String sql = "from RoleModule s where s.role.id = :param and s.systemModule.id = :param2 and s.id != :param3";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("param", Integer.parseInt(roleModule.getRole().getId()));
            query.setInteger("param2", Integer.parseInt(roleModule.getSystemModule().getId()));

            String systemUserModuleId = "0";
            if (!StringUtil.isNullorNill(roleModule.getId())) {
                systemUserModuleId = roleModule.getId();
            }
            query.setInteger("param3", Integer.parseInt(systemUserModuleId));

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            return list.size() > 0;

        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in duplicateRoleModuleExists()", e);
        }
    }

//	@Override
//	public boolean isAgentAllowedAccordingToName(String id, String name) throws LIMSRuntimeException {
//		return userRoleDAO.userInRole(id, name);
//	}

    @Override
    @Transactional
    public boolean doesUserHaveAnyModules(int userId) throws LIMSRuntimeException {
        try {
            String sql = "select count(*) from system_user_role where system_user_id = :userId";
            Query query = entityManager.unwrap(Session.class).createSQLQuery(sql);
            query.setInteger("userId", userId);
            int roleCount = ((BigInteger) query.uniqueResult()).intValue();
            return roleCount > 0;
        } catch (HibernateException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in doesUserHaveAnyModules(int)", e);
        }
    }
}