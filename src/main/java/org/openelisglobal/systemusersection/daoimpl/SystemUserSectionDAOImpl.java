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
package org.openelisglobal.systemusersection.daoimpl;

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
import org.openelisglobal.systemusersection.dao.SystemUserSectionDAO;
import org.openelisglobal.systemusersection.valueholder.SystemUserSection;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Hung Nguyen (Hung.Nguyen@health.state.mn.us)
 */
@Component
@Transactional
public class SystemUserSectionDAOImpl extends BaseDAOImpl<SystemUserSection, String> implements SystemUserSectionDAO {

    public SystemUserSectionDAOImpl() {
        super(SystemUserSection.class);
    }

//	@Override
//	public void deleteData(List systemUserSections) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < systemUserSections.size(); i++) {
//				SystemUserSection data = (SystemUserSection) systemUserSections.get(i);
//
//				SystemUserSection oldData = readSystemUserSection(data.getId());
//				SystemUserSection newData = new SystemUserSection();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "SYSTEM_USER_SECTION";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SystemUserSectionDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemUserSection AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < systemUserSections.size(); i++) {
//				SystemUserSection data = (SystemUserSection) systemUserSections.get(i);
//				// bugzilla 2206
//				data = readSystemUserSection(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SystemUserSectionDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemUserSection deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(SystemUserSection systemUserSection) throws LIMSRuntimeException {
//
//		try {
//			if (duplicateSystemUserSectionExists(systemUserSection)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + systemUserSection.getSysUserId());
//			}
//
//			String id = (String) entityManager.unwrap(Session.class).save(systemUserSection);
//			systemUserSection.setId(id);
//
//			// add to audit trail
//
//			String sysUserId = systemUserSection.getSysUserId();
//			String tableName = "SYSTEM_USER_SECTION";
//			auditDAO.saveNewHistory(systemUserSection, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SystemUserSectionDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemUserSection insertData()", e);
//		}
//
//		return true;
//	}
//
//	@Override
//	public void updateData(SystemUserSection systemUserSection) throws LIMSRuntimeException {
//
//		try {
//			if (duplicateSystemUserSectionExists(systemUserSection)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + systemUserSection.getSystemUserId());
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SystemUserSectionDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemUserSection updateData()", e);
//		}
//
//		SystemUserSection oldData = readSystemUserSection(systemUserSection.getId());
//		SystemUserSection newData = systemUserSection;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = systemUserSection.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "SYSTEM_USER_SECTION";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SystemUserSectionDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemUserSection AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(systemUserSection);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove
//			// old(systemUserSection);
//			// entityManager.unwrap(Session.class).refresh // CSL remove
//			// old(systemUserSection);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SystemUserSectionDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemUserSection updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(SystemUserSection systemUserSection) throws LIMSRuntimeException {
        try {
            SystemUserSection sysUserSection = entityManager.unwrap(Session.class).get(SystemUserSection.class,
                    systemUserSection.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (sysUserSection != null) {
                PropertyUtils.copyProperties(systemUserSection, sysUserSection);
            } else {
                systemUserSection.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SystemUserSection getData()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<SystemUserSection> getAllSystemUserSections() throws LIMSRuntimeException {
        List<SystemUserSection> list;
        try {
            String sql = "from SystemUserSection";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SystemUserSection getAllSystemModules()", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SystemUserSection> getAllSystemUserSectionsBySystemUserId(int systemUserId)
            throws LIMSRuntimeException {
        List<SystemUserSection> list;
        try {
            String sql = "from SystemUserSection s where s.systemUser.id = :param";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", systemUserId);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SystemUserSection getAllSystemUserSectionsBySystemUserId()", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SystemUserSection> getPageOfSystemUserSections(int startingRecNo) throws LIMSRuntimeException {
        List<SystemUserSection> list;
        try {
            // calculate maxRow to be one more than the page size
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            String sql = "from SystemUserSection s ";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SystemUserSection getPageOfSystemUserSections()", e);
        }

        return list;
    }

    public SystemUserSection readSystemUserSection(String idString) {
        SystemUserSection sysUserSection = null;
        try {
            sysUserSection = entityManager.unwrap(Session.class).get(SystemUserSection.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SystemUserSection readSystemUserSection(idString)", e);
        }

        return sysUserSection;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getTotalSystemUserSectionCount() throws LIMSRuntimeException {
        return getCount();
    }

    @Override
    public boolean duplicateSystemUserSectionExists(SystemUserSection systemUserSection) throws LIMSRuntimeException {
        try {

            List<SystemUserSection> list = new ArrayList();

            String sql = "from SystemUserSection s where s.systemUser.id = :param and s.testSection.id = :param2 and s.id != :param3";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", systemUserSection.getSystemUser().getId());
            query.setParameter("param2", systemUserSection.getTestSection().getId());

            String systemUserSectionId = "0";
            if (!StringUtil.isNullorNill(systemUserSection.getId())) {
                systemUserSectionId = systemUserSection.getId();
            }
            query.setParameter("param3", systemUserSectionId);

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
            throw new LIMSRuntimeException("Error in duplicateSystemUserSectionExists()", e);
        }
    }

}