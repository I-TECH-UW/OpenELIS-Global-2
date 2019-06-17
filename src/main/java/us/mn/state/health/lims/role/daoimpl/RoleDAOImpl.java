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
package us.mn.state.health.lims.role.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.role.dao.RoleDAO;
import us.mn.state.health.lims.role.valueholder.Role;

@Component
@Transactional
public class RoleDAOImpl extends BaseDAOImpl<Role, String> implements RoleDAO {

	public RoleDAOImpl() {
		super(Role.class);
	}

//	@Override
//	public void deleteData(List<Role> roles) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (Role data : roles) {
//
//				Role oldData = readRole(data.getId());
//				Role newData = new Role();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "SYSTEM_ROLE";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (Exception e) {
//			LogEvent.logError("RolesDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Role AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (Role data : roles) {
//				data = readRole(data.getId());
//				sessionFactory.getCurrentSession().delete(data);
//				// sessionFactory.getCurrentSession().flush(); // CSL remove old
//				// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			}
//		} catch (Exception e) {
//			LogEvent.logError("RolesDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Role deleteData()", e);
//		}
//	}
//
//	@Override
//	public boolean insertData(Role role) throws LIMSRuntimeException {
//
//		try {
//			String id = (String) sessionFactory.getCurrentSession().save(role);
//			role.setId(id);
//
//			String sysUserId = role.getSysUserId();
//			String tableName = "SYSTEM_ROLE";
//			auditDAO.saveNewHistory(role, sysUserId, tableName);
//
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//
//		} catch (Exception e) {
//			LogEvent.logError("RoleDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in Role insertData()", e);
//		}
//
//		return true;
//	}
//
//	@Override
//	public void updateData(Role role) throws LIMSRuntimeException {
//
//		Role oldData = readRole(role.getId());
//		Role newData = role;
//
//		try {
//
//			String sysUserId = role.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "SYSTEM_ROLE";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (Exception e) {
//			LogEvent.logError("RolesDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Role AuditTrail updateData()", e);
//		}
//
//		try {
//			sessionFactory.getCurrentSession().merge(role);
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			// sessionFactory.getCurrentSession().evict // CSL remove old(role);
//			// sessionFactory.getCurrentSession().refresh // CSL remove old(role);
//		} catch (Exception e) {
//			LogEvent.logError("RolesDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Role updateData()", e);
//		}
//	}

	@Override
	@Transactional(readOnly = true)
	public void getData(Role role) throws LIMSRuntimeException {
		try {
			Role tmpRole = sessionFactory.getCurrentSession().get(Role.class, role.getId());
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
			if (tmpRole != null) {
				PropertyUtils.copyProperties(role, tmpRole);
			} else {
				role.setId(null);
			}
		} catch (Exception e) {
			LogEvent.logError("RolesDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in Role getData()", e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Role> getAllRoles() throws LIMSRuntimeException {
		List<Role> list = null;
		try {
			String sql = "from Role";
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			list = query.list();
			// closeSession(); // CSL remove old
		} catch (HibernateException e) {
			handleException(e, "getAllRoles");
		}

		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Role> getAllActiveRoles() throws LIMSRuntimeException {
		List<Role> list = null;
		try {
			String sql = "from Role r where r.active = true";
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			list = query.list();
			// closeSession(); // CSL remove old
		} catch (HibernateException e) {
			handleException(e, "getAllActiveRoles");
		}

		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Role> getPageOfRoles(int startingRecNo) throws LIMSRuntimeException {
		List<Role> list = null;
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			String sql = "from Role r order by r.id";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);

			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("RolesDAOImpl", "getPageOfRoles()", e.toString());
			throw new LIMSRuntimeException("Error in Role getPageOfRoles()", e);
		}

		return list;
	}

	public Role readRole(String idString) {
		Role recoveredRole = null;
		try {
			recoveredRole = sessionFactory.getCurrentSession().get(Role.class, idString);
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("RoleDAOImpl", "readRole()", e.toString());
			throw new LIMSRuntimeException("Error in Role readRole()", e);
		}

		return recoveredRole;
	}

	@Override
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public List getNextRoleRecord(String id) throws LIMSRuntimeException {
		return getNextRecord(id, "Role", Role.class);
	}

	@Override
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public List getPreviousRoleRecord(String id) throws LIMSRuntimeException {
		return getPreviousRecord(id, "Role", Role.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Role> getReferencingRoles(Role role) throws LIMSRuntimeException {
		if (GenericValidator.isBlankOrNull(role.getId())) {
			return new ArrayList<>();
		}

		List<Role> list = null;
		try {
			String sql = "from Role where grouping_parent = :parent";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setInteger("parent", Integer.parseInt(role.getId()));

			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("RolesDAOImpl", "getReferencingRoles()", e.toString());
			throw new LIMSRuntimeException("Error in Role getReferencingRoles()", e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Role getRoleByName(String name) throws LIMSRuntimeException {
		String sql = "from Role r where trim(r.name) = :name";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setString("name", name);
			Role role = (Role) query.setMaxResults(1).uniqueResult();
			// closeSession(); // CSL remove old
			return role;
		} catch (HibernateException e) {
			handleException(e, "getRoleByName");
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Role getRoleById(String roleId) throws LIMSRuntimeException {
		String sql = "from Role r where r.id = :id";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setInteger("id", Integer.parseInt(roleId));
			Role role = (Role) query.uniqueResult();
			// closeSession(); // CSL remove old
			return role;
		} catch (HibernateException e) {
			handleException(e, "getRoleById");
		}
		return null;
	}

}