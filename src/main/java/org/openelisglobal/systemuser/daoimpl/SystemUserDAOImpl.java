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
package org.openelisglobal.systemuser.daoimpl;

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
import org.openelisglobal.systemuser.dao.SystemUserDAO;
import org.openelisglobal.systemuser.valueholder.SystemUser;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diane benz
 */
@Component
@Transactional
public class SystemUserDAOImpl extends BaseDAOImpl<SystemUser, String> implements SystemUserDAO {

    public SystemUserDAOImpl() {
        super(SystemUser.class);
    }

//	@Override
//	public void deleteData(List systemUsers) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < systemUsers.size(); i++) {
//				SystemUser data = (SystemUser) systemUsers.get(i);
//
//				SystemUser oldData = readSystemUser(data.getId());
//				SystemUser newData = new SystemUser();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "SYSTEM_USER";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SystemUserDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemUser AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < systemUsers.size(); i++) {
//				SystemUser data = (SystemUser) systemUsers.get(i);
//				SystemUser cloneData = readSystemUser(data.getId());
//
//				// Make the change to the object.
//				cloneData.setIsActive(IActionConstants.NO);
//				entityManager.unwrap(Session.class).merge(cloneData);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//				// entityManager.unwrap(Session.class).evict // CSL remove old(cloneData);
//				// entityManager.unwrap(Session.class).refresh // CSL remove old(cloneData);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SystemUserDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemUser deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(SystemUser systemUser) throws LIMSRuntimeException {
//
//		try {
//			// bugzilla 1482 throw Exception if record already exists
//			if (duplicateSystemUserExists(systemUser)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + systemUser.getFirstName() + BLANK + systemUser.getFirstName());
//			}
//			String id = (String) entityManager.unwrap(Session.class).save(systemUser);
//			systemUser.setId(id);
//
//			// bugzilla 1824 inserts will be logged in history table
//
//			String sysUserId = systemUser.getSysUserId();
//			String tableName = "SYSTEM_USER";
//			auditDAO.saveNewHistory(systemUser, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SystemUserDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemUser insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(SystemUser systemUser) throws LIMSRuntimeException {
//		// bugzilla 1482 throw Exception if active record already exists
//		try {
//			if (duplicateSystemUserExists(systemUser)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + systemUser.getLastName() + BLANK + systemUser.getFirstName());
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SystemUserDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemUser updateData()", e);
//		}
//
//		SystemUser oldData = readSystemUser(systemUser.getId());
//		SystemUser newData = systemUser;
//		// some bug is occurring where a new entry is entered when lastupdated is null
//		// so we are passing in a value which will be corrected on update
//		// TODO find reason for the bug and fix
//		systemUser.setLastupdated(oldData.getLastupdated());
//
//		// add to audit trail
//		try {
//
//			String sysUserId = systemUser.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "SYSTEM_USER";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SystemUserDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemUser AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(systemUser);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(systemUser);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(systemUser);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SystemUserDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemUser updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(SystemUser systemUser) throws LIMSRuntimeException {
        try {
            SystemUser sysUser = entityManager.unwrap(Session.class).get(SystemUser.class, systemUser.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            if (sysUser != null) {
                // LogEvent.logInfo(this.getClass().getName(), "method unkown", "Just read
                // sysUser " + sysUser.getId() + " " +
                // sysUser.getLastName());
                PropertyUtils.copyProperties(systemUser, sysUser);
            } else {
                systemUser.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SystemUser getData()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<SystemUser> getAllSystemUsers() throws LIMSRuntimeException {
        List<SystemUser> list;
        try {
            String sql = "from SystemUser";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SystemUser getAllSystemUsers()", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SystemUser> getPageOfSystemUsers(int startingRecNo) throws LIMSRuntimeException {
        List<SystemUser> list;
        try {
            // calculate maxRow to be one more than the page size
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            // bugzilla 1399
            String sql = "from SystemUser s order by s.lastName, s.firstName";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SystemUser getPageOfSystemUsers()", e);
        }

        return list;
    }

    public SystemUser readSystemUser(String idString) {
        SystemUser su = null;
        try {
            su = entityManager.unwrap(Session.class).get(SystemUser.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SystemUser readSystemUser()", e);
        }

        return su;
    }

    // bugzilla 1411
    @Override
    @Transactional(readOnly = true)
    public Integer getTotalSystemUserCount() throws LIMSRuntimeException {
        return getCount();
    }

    // bugzilla 1482
    @Override
    public boolean duplicateSystemUserExists(SystemUser systemUser) throws LIMSRuntimeException {
        try {

            List<SystemUser> list = new ArrayList();

            // not case sensitive hemolysis and Hemolysis are considered
            // duplicates
            String sql = "from SystemUser t where trim(lower(t.lastName)) = :param and trim(lower(t.firstName)) = :param2 and t.id != :id";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", systemUser.getLastName().toLowerCase().trim());
            query.setParameter("param2", systemUser.getFirstName().toLowerCase().trim());

            // initialize with 0 (for new records where no id has been generated yet
            String sysUserId = "0";
            if (!StringUtil.isNullorNill(systemUser.getId())) {
                sysUserId = systemUser.getId();
            }
            query.setInteger("id", Integer.parseInt(sysUserId));

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
            throw new LIMSRuntimeException("Error in duplicateSystemUserExists()", e);
        }
    }

    @Override

    @Transactional(readOnly = true)
    public SystemUser getDataForLoginUser(String userName) throws LIMSRuntimeException {
        List<SystemUser> list;
        try {
            String sql = "from SystemUser where login_name = :name";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("name", userName);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SystemUser getDataForUser()", e);
        }

        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public SystemUser getUserById(String userId) throws LIMSRuntimeException {
        try {
            SystemUser sysUser = entityManager.unwrap(Session.class).get(SystemUser.class, userId);
            // closeSession(); // CSL remove old
            return sysUser;
        } catch (RuntimeException e) {
            handleException(e, "getUserById");
        }

        return null;
    }

}