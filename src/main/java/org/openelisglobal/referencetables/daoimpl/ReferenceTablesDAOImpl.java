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
package org.openelisglobal.referencetables.daoimpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.referencetables.dao.ReferenceTablesDAO;
import org.openelisglobal.referencetables.valueholder.ReferenceTables;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Yi Chen
 */
@Component
@Transactional
public class ReferenceTablesDAOImpl extends BaseDAOImpl<ReferenceTables, String> implements ReferenceTablesDAO {

    public ReferenceTablesDAOImpl() {
        super(ReferenceTables.class);
    }

//	@Override
//	public void deleteData(List referenceTableses) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < referenceTableses.size(); i++) {
//
//				ReferenceTables data = (ReferenceTables) referenceTableses.get(i);
//
//				ReferenceTables oldData = readReferenceTables(data.getId());
//				ReferenceTables newData = new ReferenceTables();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "REFERENCE_TABLES";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ReferenceTablesDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in ReferenceTables AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < referenceTableses.size(); i++) {
//
//				ReferenceTables data = (ReferenceTables) referenceTableses.get(i);
//				// bugzilla 2206
//				data = readReferenceTables(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ReferenceTablesDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in ReferenceTables deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(ReferenceTables referenceTables) throws LIMSRuntimeException {
//
//		// String isHl7Encoded;
//		// String keepHistory;
//		boolean isNew = true;
//
//		/*
//		 * isHl7Encoded = referencetables.getIsHl7Encoded(); if
//		 * (StringUtil.isNullorNill(isHl7Encoded) || "0".equals(isHl7Encoded)) {
//		 * referencetables.setIsHl7Encoded ("N"); }
//		 *
//		 * keepHistory = referencetables.getKeepHistory(); if
//		 * (StringUtil.isNullorNill(keepHistory) || "0".equals(keepHistory)) {
//		 * referencetables.setKeepHistory ("N"); }
//		 */
//
//		try {
//			// bugzilla 1482 throw Exception if record already exists
//			if (duplicateReferenceTablesExists(referenceTables, isNew)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for " + referenceTables.getTableName());
//			}
//
//			// LogEvent.logInfo(this.getClass().getName(), "method unkown", "This is ID from insert referencetables " +
//			// referencetables.getId());
//			String id = (String) entityManager.unwrap(Session.class).save(referenceTables);
//			referenceTables.setId(id);
//
//			// bugzilla 1824 inserts will be logged in history table
//
//			String sysUserId = referenceTables.getSysUserId();
//			String tableName = "REFERENCE_TABLES";
//			auditDAO.saveNewHistory(referenceTables, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ReferenceTablesDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in Referencetables insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(ReferenceTables referenceTables) throws LIMSRuntimeException {
//		// bugzilla 1482 throw Exception if record already exists
//
//		// String isHl7Encoded;
//		// String keepHistory;
//		boolean isNew = false;
//
//		/*
//		 * isHl7Encoded = referencetables.getIsHl7Encoded(); System.out.println
//		 * ("Yi isH17Encodded is " + isHl7Encoded); if
//		 * (StringUtil.isNullorNill(isHl7Encoded) || "0".equals(isHl7Encoded)) {
//		 * referencetables.setIsHl7Encoded ("N"); }
//		 *
//		 * keepHistory = referencetables.getKeepHistory(); System.out.println
//		 * ("Yi isH17Encodded is " + keepHistory); if
//		 * (StringUtil.isNullorNill(keepHistory) || "0".equals(keepHistory)) {
//		 * referencetables.setKeepHistory ("N"); }
//		 */
//
//		try {
//			if (duplicateReferenceTablesExists(referenceTables, isNew)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for " + referenceTables.getTableName());
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ReferenceTablesDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Referencetables updateData()", e);
//		}
//
//		// LogEvent.logInfo(this.getClass().getName(), "method unkown", "This is name from updateData " +
//		// referencetables.getTableName());
//		ReferenceTables oldData = readReferenceTables(referenceTables.getId());
//		ReferenceTables newData = referenceTables;
//
//		// LogEvent.logInfo(this.getClass().getName(), "method unkown", "updateDate " + newData.getTableName() + " " +
//		// oldData.getTableName());
//		// add to audit trail
//		try {
//
//			String sysUserId = referenceTables.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "REFERENCE_TABLES";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ReferenceTablesDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Referencetables AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(referenceTables);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(referenceTables);
//			// entityManager.unwrap(Session.class).refresh // CSL remove
//			// old(referenceTables);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ReferenceTablesDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Referencetables updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(ReferenceTables referenceTables) throws LIMSRuntimeException {
        try {
            ReferenceTables reftbl = entityManager.unwrap(Session.class).get(ReferenceTables.class,
                    referenceTables.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (reftbl != null) {
                PropertyUtils.copyProperties(referenceTables, reftbl);
            } else {
                referenceTables.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Referencetables getData()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReferenceTables> getAllReferenceTables() throws LIMSRuntimeException {
        List<ReferenceTables> list = new Vector<>();
        try {
            String sql = "from ReferenceTables";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            // query.setMaxResults(10);
            // query.setFirstResult(3);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Referencetables getAllReferenceTables()", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReferenceTables> getPageOfReferenceTables(int startingRecNo) throws LIMSRuntimeException {
        List<ReferenceTables> list = new Vector<>();
        try {
            // calculate maxRow to be one more than the page size
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            String sql = "from ReferenceTables r order by r.tableName";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);
            // query.setCacheMode(org.hibernate.CacheMode.REFRESH);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Referencetables getPageOfReferenceTables()", e);
        }

        return list;
    }

    public ReferenceTables readReferenceTables(String idString) {
        ReferenceTables referenceTables = null;
        try {
            referenceTables = entityManager.unwrap(Session.class).get(ReferenceTables.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Referencetables readReferenceTables(idString)", e);
        }

        return referenceTables;

    }

    // bugzilla 1411
    @Override
    @Transactional(readOnly = true)
    public Integer getTotalReferenceTablesCount() throws LIMSRuntimeException {
        return getCount();
    }

    // bugzilla 1482
    @Override
    public boolean duplicateReferenceTablesExists(ReferenceTables referenceTables, boolean isNew)
            throws LIMSRuntimeException {
        try {

            List<ReferenceTables> list = new ArrayList<>();
            String sql;

            // not case sensitive hemolysis and Hemolysis are considered
            // duplicates
            if (isNew) {
                sql = "from ReferenceTables t where trim(lower(t.tableName)) = :param";
            } else {
                sql = "from ReferenceTables t where trim(lower(t.tableName)) = :param and id != :param2";
            }

            // LogEvent.logInfo(this.getClass().getName(), "method unkown", "Yi in
            // duplicateReferencetables sql is " + sql);
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            // LogEvent.logInfo(this.getClass().getName(), "method unkown",
            // "duplicateReferencetables sql is " + sql);

            query.setParameter("param", referenceTables.getTableName().toLowerCase().trim());

            // initialize with 0 (for new records where no id has been generated
            // yet
            String referenceTablesId = "0";
            if (!StringUtil.isNullorNill(referenceTables.getId())) {
                referenceTablesId = referenceTables.getId();
            }

            if (!isNew) {
                query.setParameter("param2", referenceTablesId);
            }
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
            throw new LIMSRuntimeException("Error in duplicateReferenceTablesExists()", e);
        }
    }

    // bugzilla 2571 go through ReferenceTablesDAO to get reference tables info
    @Override
    @Transactional(readOnly = true)
    public List<ReferenceTables> getAllReferenceTablesForHl7Encoding() throws LIMSRuntimeException {
        List<ReferenceTables> list = new Vector<>();
        try {
            String sql = "from ReferenceTables rt where trim(upper(rt.isHl7Encoded)) = 'Y'";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            // query.setMaxResults(10);
            // query.setFirstResult(3);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // buzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in ReferenceTables getAllReferenceTablesForHl7Encoding()", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public ReferenceTables getReferenceTableByName(ReferenceTables referenceTables) throws LIMSRuntimeException {
        return getReferenceTableByName(referenceTables.getTableName());
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getTotalReferenceTableCount() throws LIMSRuntimeException {
        return getCount();
    }

    @Override
    @Transactional(readOnly = true)
    public ReferenceTables getReferenceTableByName(String tableName) {
        try {
            String sql = "from ReferenceTables rt where trim(lower(rt.tableName)) = :tableName";
            Query<ReferenceTables> query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("tableName", tableName.toLowerCase().trim());

            ReferenceTables table = query.setMaxResults(1).uniqueResult();

            return table;

        } catch (HibernateException e) {
            handleException(e, "getReferenceTableByName");
        }

        return null;
    }

}
