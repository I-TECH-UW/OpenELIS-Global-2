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
package org.openelisglobal.systemmodule.daoimpl;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.systemmodule.dao.SystemModuleDAO;
import org.openelisglobal.systemmodule.valueholder.SystemModule;

/**
 * @author Hung Nguyen (Hung.Nguyen@health.state.mn.us)
 */
@Component
@Transactional
public class SystemModuleDAOImpl extends BaseDAOImpl<SystemModule, String> implements SystemModuleDAO {

    public SystemModuleDAOImpl() {
        super(SystemModule.class);
    }

//	@Override
//	public void deleteData(List systemModules) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < systemModules.size(); i++) {
//				SystemModule data = (SystemModule) systemModules.get(i);
//
//				SystemModule oldData = readSystemModule(data.getId());
//				SystemModule newData = new SystemModule();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "SYSTEM_MODULE";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (Exception e) {
//			LogEvent.logError("SystemModuleDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemModule AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < systemModules.size(); i++) {
//				SystemModule data = (SystemModule) systemModules.get(i);
//				data = readSystemModule(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (Exception e) {
//			LogEvent.logError("SystemModuleDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemModule deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(SystemModule systemModule) throws LIMSRuntimeException {
//
//		try {
//			if (duplicateSystemModuleExists(systemModule)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + systemModule.getSystemModuleName());
//			}
//
//			String id = (String) entityManager.unwrap(Session.class).save(systemModule);
//			systemModule.setId(id);
//
////
////			String sysUserId = systemModule.getSysUserId();
////			String tableName = "SYSTEM_MODULE";
////			auditDAO.saveNewHistory(systemModule, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("SystemModuleDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemModule insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(SystemModule systemModule) throws LIMSRuntimeException {
//		try {
//			if (duplicateSystemModuleExists(systemModule)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + systemModule.getSystemModuleName());
//			}
//		} catch (Exception e) {
//			LogEvent.logError("SystemModuleDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemModule updateData()", e);
//		}
//
//		SystemModule oldData = readSystemModule(systemModule.getId());
//
//		// add to audit trail
//		try {
//
//			auditDAO.saveHistory(systemModule, oldData, systemModule.getSysUserId(),
//					IActionConstants.AUDIT_TRAIL_UPDATE, "SYSTEM_MODULE");
//		} catch (Exception e) {
//			LogEvent.logError("SystemModuleDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemModule AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(systemModule);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(systemModule);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(systemModule);
//		} catch (Exception e) {
//			LogEvent.logError("SystemModuleDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemModule updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(SystemModule systemModule) throws LIMSRuntimeException {
        try {
            SystemModule sysModule = entityManager.unwrap(Session.class).get(SystemModule.class, systemModule.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (sysModule != null) {
                PropertyUtils.copyProperties(systemModule, sysModule);
            } else {
                systemModule.setId(null);
            }
        } catch (Exception e) {
            // bugzilla 2154
            LogEvent.logError("SystemModuleDAOImpl", "getData()", e.toString());
            throw new LIMSRuntimeException("Error in SystemModule getData()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List getAllSystemModules() throws LIMSRuntimeException {
        List list;
        try {
            String sql = "from SystemModule";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (Exception e) {
            LogEvent.logError("SystemModuleDAOImpl", "getAllSystemModules()", e.toString());
            throw new LIMSRuntimeException("Error in SystemModule getAllSystemModules()", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List getPageOfSystemModules(int startingRecNo) throws LIMSRuntimeException {
        List list;
        try {
            // calculate maxRow to be one more than the page size
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            String sql = "from SystemModule s order by s.systemModuleName";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (Exception e) {
            LogEvent.logError("SystemModuleDAOImpl", "getPageOfSystemModules()", e.toString());
            throw new LIMSRuntimeException("Error in SystemModule getPageOfSystemModules()", e);
        }

        return list;
    }

    public SystemModule readSystemModule(String idString) {
        SystemModule sysModule;
        try {
            sysModule = entityManager.unwrap(Session.class).get(SystemModule.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (Exception e) {
            LogEvent.logError("SystemModuleDAOImpl", "readSystemModule()", e.toString());
            throw new LIMSRuntimeException("Error in SystemModule readSystemModule(idString)", e);
        }

        return sysModule;
    }

    @Override
    @Transactional(readOnly = true)
    public List getNextSystemModuleRecord(String id) throws LIMSRuntimeException {

        return getNextRecord(id, "SystemModule", SystemModule.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List getPreviousSystemModuleRecord(String id) throws LIMSRuntimeException {

        return getPreviousRecord(id, "SystemModule", SystemModule.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getTotalSystemModuleCount() throws LIMSRuntimeException {
        return getTotalCount("SystemModule", SystemModule.class);
    }

    @Override
    @Transactional(readOnly = true)
    public SystemModule getSystemModuleByName(String name) throws LIMSRuntimeException {
        String sql = "From SystemModule sm where sm.systemModuleName = :name";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("name", name);
            SystemModule module = (SystemModule) query.uniqueResult();
            // closeSession(); // CSL remove old
            return module;
        } catch (HibernateException he) {
            handleException(he, "getSystemModuleByName");
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
        int currentId = Integer.valueOf(id);
        String tablePrefix = getTablePrefix(table);

        List list;
        int rrn;
        try {
            String sql = "select sm.id from SystemModule sm order by sm.systemModuleName";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            rrn = list.indexOf(String.valueOf(currentId));

            list = entityManager.unwrap(Session.class).getNamedQuery(tablePrefix + "getNext").setFirstResult(rrn + 1)
                    .setMaxResults(2).list();

        } catch (Exception e) {
            LogEvent.logError("SystemModuleDAOImpl", "getNextRecord()", e.toString());
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
            String sql = "select sm.id from SystemModule sm order by sm.systemModuleName";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            rrn = list.indexOf(String.valueOf(currentId));

            list = entityManager.unwrap(Session.class).getNamedQuery(tablePrefix + "getPrevious")
                    .setFirstResult(rrn + 1).setMaxResults(2).list();

        } catch (Exception e) {
            LogEvent.logError("SystemModuleDAOImpl", "getPreviousRecord()", e.toString());
            throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
        }

        return list;
    }

    @Override
    public boolean duplicateSystemModuleExists(SystemModule systemModule) throws LIMSRuntimeException {
        try {

            List list;

            String sql = "from SystemModule s where trim(s.systemModuleName) = :moduleName and s.id != :moduleId";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("moduleName", systemModule.getSystemModuleName().trim());

            String systemModuleId = "0";
            if (!StringUtil.isNullorNill(systemModule.getId())) {
                systemModuleId = systemModule.getId();
            }
            query.setInteger("moduleId", Integer.parseInt(systemModuleId));

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            return list.size() > 0;
        } catch (Exception e) {
            LogEvent.logError("SystemModuleDAOImpl", "duplicateSystemModuleExists()", e.toString());
            throw new LIMSRuntimeException("Error in duplicateSystemModuleExists()", e);
        }
    }
}