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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.systemusermodule.dao.SystemUserModuleDAO;
import org.openelisglobal.systemusermodule.valueholder.SystemUserModule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Hung Nguyen (Hung.Nguyen@health.state.mn.us)
 */
@Component
@Transactional
@Qualifier(value = "SystemUserModuleDAO")
public class SystemUserModuleDAOImpl extends BaseDAOImpl<SystemUserModule, String> implements SystemUserModuleDAO {

    public SystemUserModuleDAOImpl() {
        super(SystemUserModule.class);
    }

//	@Override
//	public void deleteData(List systemUserModules) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < systemUserModules.size(); i++) {
//				SystemUserModule data = (SystemUserModule) systemUserModules.get(i);
//
//				SystemUserModule oldData = readSystemUserModule(data.getId());
//				SystemUserModule newData = new SystemUserModule();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "SYSTEM_USER_MODULE";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("systemUserModuleDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemUserModule AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < systemUserModules.size(); i++) {
//				SystemUserModule data = (SystemUserModule) systemUserModules.get(i);
//				// bugzilla 2206
//				data = readSystemUserModule(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("systemUserModuleDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemUserModule deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(SystemUserModule systemUserModule) throws LIMSRuntimeException {
//
//		try {
//			if (duplicateSystemUserModuleExists(systemUserModule)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + systemUserModule.getPermissionAgentId());
//			}
//
//			String id = (String) entityManager.unwrap(Session.class).save(systemUserModule);
//			systemUserModule.setId(id);
//
//			// add to audit trail
//
//			String sysUserId = systemUserModule.getSysUserId();
//			String tableName = "SYSTEM_USER_MODULE";
//			auditDAO.saveNewHistory(systemUserModule, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("systemUserModuleDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemUserModule insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(SystemUserModule systemUserModule) throws LIMSRuntimeException {
//
//		try {
//			if (duplicateSystemUserModuleExists(systemUserModule)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + systemUserModule.getPermissionAgentId());
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("systemUserModuleDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemUserModule updateData()", e);
//		}
//
//		SystemUserModule oldData = readSystemUserModule(systemUserModule.getId());
//		SystemUserModule newData = systemUserModule;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = systemUserModule.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "SYSTEM_USER_MODULE";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("systemUserModuleDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemUserModule AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(systemUserModule);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(systemUserModule);
//			// entityManager.unwrap(Session.class).refresh // CSL remove
//			// old(systemUserModule);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("systemUserModuleDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemUserModule updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(SystemUserModule systemUserModule) throws LIMSRuntimeException {
        try {
            SystemUserModule sysUserModule = entityManager.unwrap(Session.class).get(SystemUserModule.class,
                    systemUserModule.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (sysUserModule != null) {
                PropertyUtils.copyProperties(systemUserModule, sysUserModule);
            } else {
                systemUserModule.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SystemUserModule getData()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<SystemUserModule> getAllPermissionModules() throws LIMSRuntimeException {
        List<SystemUserModule> list;
        try {
            String sql = "from SystemUserModule";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SystemUserModule getAllSystemModules()", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SystemUserModule> getAllPermissionModulesByAgentId(int systemUserId) throws LIMSRuntimeException {
        List<SystemUserModule> list;
        try {
            String sql = "from SystemUserModule s where s.systemUser.id = :param";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", systemUserId);
            list = query.list();
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SystemUserModule getAllSystemUserModulesBySystemUserId()", e);
        }

        return list;

    }

    @Override
    @Transactional(readOnly = true)
    public List<SystemUserModule> getPageOfPermissionModules(int startingRecNo) throws LIMSRuntimeException {
        List<SystemUserModule> list;
        try {
            // calculate maxRow to be one more than the page size
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            String sql = "from SystemUserModule s order by s.systemUser.id";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SystemUserModule getPageOfSystemUserModules()", e);
        }

        return list;
    }

    public SystemUserModule readSystemUserModule(String idString) {
        SystemUserModule sysUserModule = null;
        try {
            sysUserModule = entityManager.unwrap(Session.class).get(SystemUserModule.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Gender readSystemUserModule(idString)", e);
        }

        return sysUserModule;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getTotalPermissionModuleCount() throws LIMSRuntimeException {
        return getCount();
    }

    @Override
    public boolean duplicateSystemUserModuleExists(SystemUserModule systemUserModule) throws LIMSRuntimeException {
        try {

            List<SystemUserModule> list = new ArrayList();

            String sql = "from SystemUserModule s where s.systemUser.id = :param and s.systemModule.id = :param2 and s.id != :param3";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", systemUserModule.getSystemUser().getId());
            query.setParameter("param2", systemUserModule.getSystemModule().getId());

            String systemUserModuleId = "0";
            if (!StringUtil.isNullorNill(systemUserModule.getId())) {
                systemUserModuleId = systemUserModule.getId();
            }
            query.setParameter("param3", systemUserModuleId);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            if (list.size() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in duplicateSystemUserModuleExists()", e);
        }
    }

//	@Override
//	public boolean isAgentAllowedAccordingToName(String id, String name) throws LIMSRuntimeException {
//		try {
//			List list = new ArrayList();
//
//			String sql = "from SystemUserModule as sum join fetch sum.systemModule as sm where sum.systemUser.id = :id and sm.systemModuleName = :name";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setInteger("id", Integer.parseInt(id));
//			query.setParameter("name", name);
//
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//			return list.size() > 0;
//		} catch (RuntimeException e) {
//			LogEvent.logError("systemUserModuleDAOImpl", "isUserAllowedAccordingToName()", e.toString());
//			throw new LIMSRuntimeException("Error in isUserAllowedAccordingToName()", e);
//		}
//	}

    @Override
    public boolean doesUserHaveAnyModules(int userId) throws LIMSRuntimeException {
        List<SystemUserModule> userModuleList = getAllPermissionModulesByAgentId(userId);
        return userModuleList.size() > 0;
    }

}