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
package us.mn.state.health.lims.userrole.daoimpl;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.exception.ConstraintViolationException;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.userrole.dao.UserRoleDAO;
import us.mn.state.health.lims.userrole.valueholder.UserRole;
import us.mn.state.health.lims.userrole.valueholder.UserRolePK;

public class UserRoleDAOImpl extends BaseDAOImpl implements UserRoleDAO {

	public void deleteData(List<UserRole> roles) throws LIMSRuntimeException {
		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			
			for( UserRole data: roles){	

				UserRole oldData = readUserRole(data.getCompoundId());
				UserRole newData = new UserRole();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "SYSTEM_USER_ROLE";
				auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
			}
		}  catch (Exception e) {
			LogEvent.logError("UserRolesDAOImpl","AuditTrail deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in UserRole AuditTrail deleteData()", e);
		}  
		
		try {		
			for (UserRole data: roles) {
				data = readUserRole(data.getCompoundId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}			
		} catch (Exception e) {
			LogEvent.logError("UserRolesDAOImpl","deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in UserRole deleteData()",e);
		}
	}

	public boolean insertData(UserRole role) throws LIMSRuntimeException{

		try {
			UserRolePK id = (UserRolePK)HibernateUtil.getSession().save(role);
			role.setCompoundId(id);
			
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = role.getSysUserId();
			String tableName = "SYSTEM_USER_ROLE";
			auditDAO.saveNewHistory(role,sysUserId,tableName);
			
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
						
		} catch(ConstraintViolationException cve){
			LogEvent.logError("UserRolesDAOImpl","insertData()-- duplicate record",cve.toString());
			throw new LIMSRuntimeException("Error in UserRole insertData()-- duplicate record");
		}catch (Exception e) {
			LogEvent.logError("UserRolesDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in UserRole insertData()",e);
		} 
		
		return true;
	}

	public void updateData(UserRole role) throws LIMSRuntimeException {
		
		UserRole oldData = readUserRole(role.getCompoundId());

		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = role.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "SYSTEM_USER_ROLE";
			auditDAO.saveHistory(role,oldData,sysUserId,event,tableName);
		}  catch (Exception e) {
			LogEvent.logError("UserRolesDAOImpl","AuditTrail updateData()",e.toString());
			throw new LIMSRuntimeException("Error in UserRole AuditTrail updateData()", e);
		}  
							
		try {
			HibernateUtil.getSession().merge(role);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(role);
			HibernateUtil.getSession().refresh(role);
		} catch (Exception e) {
			LogEvent.logError("UserRolesDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in UserRole updateData()",e);
		}
	}

	public void getData(UserRole role) throws LIMSRuntimeException {
		try {
			UserRole tmpUserRole = (UserRole)HibernateUtil.getSession().get(UserRole.class, role.getCompoundId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (tmpUserRole != null) {
				PropertyUtils.copyProperties(role, tmpUserRole);
			} else {
				role.setCompoundId(null);
			}
		} catch (Exception e) {
			LogEvent.logError("UserRolesDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in UserRole getData()", e);
		}
	}

	public List getAllUserRoles() throws LIMSRuntimeException {
		List list;
		try {
			String sql = "from UserRole";
			Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("UserRolesDAOImpl","getAllUserRoles()",e.toString());
			throw new LIMSRuntimeException("Error in UserRole getAllUserRoles()", e);
		}

		return list;
	}

	public List getPageOfUserRoles(int startingRecNo) throws LIMSRuntimeException {
		List list;
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
			
			String sql = "from UserRole r order by r.id.systemUserId";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(startingRecNo-1);
			query.setMaxResults(endingRecNo-1); 					

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("UserRolesDAOImpl","getPageOfUserRoles()",e.toString());
			throw new LIMSRuntimeException("Error in UserRole getPageOfUserRoles()", e);
		}

		return list;
	}

	public UserRole readUserRole(UserRolePK userRolePK) {
		UserRole recoveredUserRole;
		try {
			recoveredUserRole = (UserRole)HibernateUtil.getSession().get(UserRole.class, userRolePK);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("UserRoleDAOImpl","readUserRole()",e.toString());
			throw new LIMSRuntimeException("Error in UserRole readUserRole()", e);
		}			
		
		return recoveredUserRole;
	}
	
	public List getNextUserRoleRecord(String id) throws LIMSRuntimeException {
		return getNextRecord(id, "UserRole", UserRole.class);
	}
	
	public List getPreviousUserRoleRecord(String id) throws LIMSRuntimeException {
		return getPreviousRecord(id, "UserRole", UserRole.class);
	}

	@SuppressWarnings("unchecked")
	public List<String> getRoleIdsForUser(String userId) throws LIMSRuntimeException {
		List<String> userRoles;
		
		try{
			String sql = "select cast(role_id AS varchar) from system_user_role where system_user_id = :userId";
			Query query = HibernateUtil.getSession().createSQLQuery(sql);
			query.setInteger("userId", Integer.parseInt(userId));
			userRoles = query.list();
		} catch (Exception e) {
			LogEvent.logError("UserRoleDAOImpl","getUserRolesForUser()",e.toString());
			throw new LIMSRuntimeException("Error in UserRoleDAOImpl getUserRolesForUser()", e);
		}
		return userRoles;
	}

	public boolean userInRole(String userId, String roleName)	throws LIMSRuntimeException {
		boolean inRole;
		
		try{
			String sql = "select count(*) from system_user_role sur " + 
						  "join system_role as sr on sr.id = sur.role_id " + 
						  "where sur.system_user_id = :userId and sr.name = :roleName";
			Query query = HibernateUtil.getSession().createSQLQuery(sql);
			query.setInteger("userId", Integer.parseInt(userId));
			query.setString("roleName", roleName);
			int result = ((BigInteger)query.uniqueResult()).intValue();
			
			inRole = result != 0;
		}catch(HibernateException he){
			LogEvent.logError("UserRoleDAOImpl","userInRole()",he.toString());
			throw new LIMSRuntimeException("Error in UserRoleDAOImpl userInRole()", he);	
		}
		
		return inRole;
	}

    public boolean userInRole(String userId, Collection<String> roleNames)	throws LIMSRuntimeException {
        boolean inRole;

        try{
            String sql = "select count(*) from system_user_role sur " +
                    "join system_role as sr on sr.id = sur.role_id " +
                    "where sur.system_user_id = :userId and sr.name in (:roleNames)";
            Query query = HibernateUtil.getSession().createSQLQuery(sql);
            query.setInteger("userId", Integer.parseInt(userId));
            query.setParameterList("roleNames", roleNames);
            int result = ((BigInteger)query.uniqueResult()).intValue();

            inRole = result != 0;
        }catch(HibernateException he){
            LogEvent.logError("UserRoleDAOImpl","userInRole()",he.toString());
            throw new LIMSRuntimeException("Error in UserRoleDAOImpl userInRole()", he);
        }

        return inRole;
    }
}