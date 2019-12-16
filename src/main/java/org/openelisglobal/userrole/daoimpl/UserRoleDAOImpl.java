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
package org.openelisglobal.userrole.daoimpl;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.userrole.dao.UserRoleDAO;
import org.openelisglobal.userrole.valueholder.UserRole;
import org.openelisglobal.userrole.valueholder.UserRolePK;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserRoleDAOImpl extends BaseDAOImpl<UserRole, UserRolePK> implements UserRoleDAO {

    public UserRoleDAOImpl() {
        super(UserRole.class);
    }

    // @Override
//	public void deleteData(List<UserRole> roles) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (UserRole data : roles) {
//
//				UserRole oldData = readUserRole(data.getCompoundId());
//				UserRole newData = new UserRole();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "SYSTEM_USER_ROLE";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("UserRolesDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in UserRole AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (UserRole data : roles) {
//				data = readUserRole(data.getCompoundId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("UserRolesDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in UserRole deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(UserRole role) throws LIMSRuntimeException {
//
//		try {
//			UserRolePK id = (UserRolePK) entityManager.unwrap(Session.class).save(role);
//			role.setCompoundId(id);
//
//			String sysUserId = role.getSysUserId();
//			String tableName = "SYSTEM_USER_ROLE";
//			auditDAO.saveNewHistory(role, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (ConstraintViolationException e) {
//			LogEvent.logError("UserRolesDAOImpl", "insertData()-- duplicate record", cve.toString());
//			throw new LIMSRuntimeException("Error in UserRole insertData()-- duplicate record");
//		} catch (RuntimeException e) {
//			LogEvent.logError("UserRolesDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in UserRole insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(UserRole role) throws LIMSRuntimeException {
//
//		UserRole oldData = readUserRole(role.getCompoundId());
//
//		try {
//
//			String sysUserId = role.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "SYSTEM_USER_ROLE";
//			auditDAO.saveHistory(role, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			LogEvent.logError("UserRolesDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in UserRole AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(role);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(role);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(role);
//		} catch (RuntimeException e) {
//			LogEvent.logError("UserRolesDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in UserRole updateData()", e);
//		}
//	}

//	@Override
//	public void getData(UserRole role) throws LIMSRuntimeException {
//		try {
//			UserRole tmpUserRole = entityManager.unwrap(Session.class).get(UserRole.class, role.getCompoundId());
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			if (tmpUserRole != null) {
//				PropertyUtils.copyProperties(role, tmpUserRole);
//			} else {
//				role.setCompoundId(null);
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("UserRolesDAOImpl", "getData()", e.toString());
//			throw new LIMSRuntimeException("Error in UserRole getData()", e);
//		}
//	}

//	@Override
//	public List getAllUserRoles() throws LIMSRuntimeException {
//		List list;
//		try {
//			String sql = "from UserRole";
//			Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			LogEvent.logError("UserRolesDAOImpl", "getAllUserRoles()", e.toString());
//			throw new LIMSRuntimeException("Error in UserRole getAllUserRoles()", e);
//		}
//
//		return list;
//	}

//	@Override
//	public List getPageOfUserRoles(int startingRecNo) throws LIMSRuntimeException {
//		List list;
//		try {
//			// calculate maxRow to be one more than the page size
//			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
//
//			String sql = "from UserRole r order by r.id.systemUserId";
//			Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setFirstResult(startingRecNo - 1);
//			query.setMaxResults(endingRecNo - 1);
//
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			LogEvent.logError("UserRolesDAOImpl", "getPageOfUserRoles()", e.toString());
//			throw new LIMSRuntimeException("Error in UserRole getPageOfUserRoles()", e);
//		}
//
//		return list;
//	}

//	public UserRole readUserRole(UserRolePK userRolePK) {
//		UserRole recoveredUserRole;
//		try {
//			recoveredUserRole = entityManager.unwrap(Session.class).get(UserRole.class, userRolePK);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			LogEvent.logError("UserRoleDAOImpl", "readUserRole()", e.toString());
//			throw new LIMSRuntimeException("Error in UserRole readUserRole()", e);
//		}
//
//		return recoveredUserRole;
//	}

//	@Override
//	public List getNextUserRoleRecord(String id) throws LIMSRuntimeException {
//		return getNextRecord(id, "UserRole", UserRole.class);
//	}

//	@Override
//	public List getPreviousUserRoleRecord(String id) throws LIMSRuntimeException {
//		return getPreviousRecord(id, "UserRole", UserRole.class);
//	}

    @Override
    @Transactional
    public List<String> getRoleIdsForUser(String userId) throws LIMSRuntimeException {
        List<String> userRoles;

        try {
            String sql = "select cast(role_id AS varchar) from system_user_role where system_user_id = :userId";
            Query query = entityManager.unwrap(Session.class).createSQLQuery(sql);
            query.setInteger("userId", Integer.parseInt(userId));
            userRoles = query.list();
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in UserRoleDAOImpl getUserRolesForUser()", e);
        }
        return userRoles;
    }

    @Override
    @Transactional
    public boolean userInRole(String userId, String roleName) throws LIMSRuntimeException {
        boolean inRole;
        try {
            String sql = "select count(*) from system_user_role sur " + "join system_role as sr on sr.id = sur.role_id "
                    + "where sur.system_user_id = :userId and sr.name = :roleName";
            Query query = entityManager.unwrap(Session.class).createSQLQuery(sql);
            query.setInteger("userId", Integer.parseInt(userId));
            query.setString("roleName", roleName);
            int result = ((BigInteger) query.uniqueResult()).intValue();

            inRole = result != 0;
        } catch (HibernateException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in UserRoleDAOImpl userInRole()", e);
        }

        return inRole;
    }

    @Override
    @Transactional
    public boolean userInRole(String userId, Collection<String> roleNames) throws LIMSRuntimeException {
        boolean inRole;

        try {
            String sql = "select count(*) from system_user_role sur " + "join system_role as sr on sr.id = sur.role_id "
                    + "where sur.system_user_id = :userId and sr.name in (:roleNames)";
            Query query = entityManager.unwrap(Session.class).createSQLQuery(sql);
            query.setInteger("userId", Integer.parseInt(userId));
            query.setParameterList("roleNames", roleNames);
            int result = ((BigInteger) query.uniqueResult()).intValue();

            inRole = result != 0;
        } catch (HibernateException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in UserRoleDAOImpl userInRole()", e);
        }

        return inRole;
    }
}