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
package org.openelisglobal.unitofmeasure.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.unitofmeasure.dao.UnitOfMeasureDAO;
import org.openelisglobal.unitofmeasure.valueholder.UnitOfMeasure;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diane benz
 */
@Component
@Transactional
public class UnitOfMeasureDAOImpl extends BaseDAOImpl<UnitOfMeasure, String> implements UnitOfMeasureDAO {

    public UnitOfMeasureDAOImpl() {
        super(UnitOfMeasure.class);
    }

//	@Override
//	public void deleteData(List unitOfMeasures) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (Object unitOfMeasure : unitOfMeasures) {
//				UnitOfMeasure data = (UnitOfMeasure) unitOfMeasure;
//
//				UnitOfMeasure oldData = readUnitOfMeasure(data.getId());
//				UnitOfMeasure newData = new UnitOfMeasure();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "UNIT_OF_MEASURE";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("UnitOfMeasureDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in UnitOfMeasure AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (Object unitOfMeasure : unitOfMeasures) {
//				UnitOfMeasure data = (UnitOfMeasure) unitOfMeasure;
//
//				data = readUnitOfMeasure(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("UnitOfMeasureDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in UnitOfMeasure deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(UnitOfMeasure unitOfMeasure) throws LIMSRuntimeException {
//		// LogEvent.logInfo(this.getClass().getName(), "method unkown", "insertData = " + unitOfMeasure.getUnitOfMeasureName());
//		try {
//			// bugzilla 1482 throw Exception if record already exists
//			if (duplicateUnitOfMeasureExists(unitOfMeasure)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + unitOfMeasure.getUnitOfMeasureName());
//			}
//
//			String id = (String) entityManager.unwrap(Session.class).save(unitOfMeasure);
//			unitOfMeasure.setId(id);
//
//			// bugzilla 1824 inserts will be logged in history table
//
//			String sysUserId = unitOfMeasure.getSysUserId();
//			String tableName = "UNIT_OF_MEASURE";
//			auditDAO.saveNewHistory(unitOfMeasure, "1", tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("UnitOfMeasureDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in UnitOfMeasure insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(UnitOfMeasure unitOfMeasure) throws LIMSRuntimeException {
//		// bugzilla 1482 throw Exception if record already exists
//		try {
//			if (duplicateUnitOfMeasureExists(unitOfMeasure)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + unitOfMeasure.getUnitOfMeasureName());
//			}
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("UnitOfMeasureDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in UnitOfMeasure updateData()", e);
//		}
//
//		UnitOfMeasure oldData = readUnitOfMeasure(unitOfMeasure.getId());
//
//		// add to audit trail
//		try {
//
//			auditDAO.saveHistory(unitOfMeasure, oldData, unitOfMeasure.getSysUserId(),
//					IActionConstants.AUDIT_TRAIL_UPDATE, "UNIT_OF_MEASURE");
//		} catch (RuntimeException e) {
//			LogEvent.logError("UnitOfMeasureDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in UnitOfMeasure AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(unitOfMeasure);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(unitOfMeasure);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(unitOfMeasure);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("UnitOfMeasureDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in UnitOfMeasure updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public UnitOfMeasure getUnitOfMeasureById(String uomId) throws LIMSRuntimeException {
        String sql = "from UnitOfMeasure uom where id = :id";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("id", Integer.parseInt(uomId));
            UnitOfMeasure uom = (UnitOfMeasure) query.uniqueResult();
            // closeSession(); // CSL remove old
            return uom;
        } catch (HibernateException e) {
            handleException(e, "getUnitOfMeeasureById");
        }

        return null;
    }

//	@Override
//	public void getData(UnitOfMeasure unitOfMeasure) throws LIMSRuntimeException {
//		try {
//			UnitOfMeasure uom = entityManager.unwrap(Session.class).get(UnitOfMeasure.class, unitOfMeasure.getId());
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			if (uom != null) {
//				PropertyUtils.copyProperties(unitOfMeasure, uom);
//			} else {
//				unitOfMeasure.setId(null);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("UnitOfMeasureDAOImpl", "getData()", e.toString());
//			throw new LIMSRuntimeException("Error in UnitOfMeasure getData()", e);
//		}
//	}

//	@Override
//	public List getAllUnitOfMeasures() throws LIMSRuntimeException {
//		List list;
//		try {
//			String sql = "from UnitOfMeasure";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			// query.setMaxResults(10);
//			// query.setFirstResult(3);
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("UnitOfMeasureDAOImpl", "getAllUnitOfMeasures()", e.toString());
//			throw new LIMSRuntimeException("Error in UnitOfMeasure getAllUnitOfMeasures()", e);
//		}
//
//		return list;
//	}

//	@Override
//	public List<UnitOfMeasure> getAllActiveUnitOfMeasures() {
//		List list;
//		try {
//			String sql = "from UnitOfMeasure";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			// query.setMaxResults(10);
//			// query.setFirstResult(3);
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("UnitOfMeasureDAOImpl", "getAllUnitOfMeasures()", e.toString());
//			throw new LIMSRuntimeException("Error in UnitOfMeasure getAllUnitOfMeasures()", e);
//		}
//
//		return list;
//	}
//		String sql = "from TestSection t where t.isActive = 'Y' order by t.sortOrderInt";
//
//		try {
//			Query query = entityManager.unwrap(Session.class).createQuery(sql);
//
//			List<TestSection> sections = query.list();
//			// closeSession(); // CSL remove old
//			return sections;
//		} catch (HibernateException e) {
//			handleException(e, "getAllActiveTestSections");
//		}
//		return null;
//	}

//	@Override
//	public List getPageOfUnitOfMeasures(int startingRecNo) throws LIMSRuntimeException {
//		List list;
//		try {
//			// calculate maxRow to be one more than the page size
//			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
//
//			// bugzilla 1399
//			String sql = "from UnitOfMeasure u order by u.unitOfMeasureName";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setFirstResult(startingRecNo - 1);
//			query.setMaxResults(endingRecNo - 1);
//
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("UnitOfMeasureDAOImpl", "getPageOfUnitOfMeasures()", e.toString());
//			throw new LIMSRuntimeException("Error in UnitOfMeasure getPageOfUnitOfMeasures()", e);
//		}
//
//		return list;
//	}

//	public UnitOfMeasure readUnitOfMeasure(String idString) {
//		UnitOfMeasure data;
//		try {
//			data = entityManager.unwrap(Session.class).get(UnitOfMeasure.class, idString);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("UnitOfMeasureDAOImpl", "readUnitOfMeasure()", e.toString());
//			throw new LIMSRuntimeException("Error in UnitOfMeasure readUnitOfMeasure()", e);
//		}
//
//		return data;
//	}

//	@Override
//	public List getNextUnitOfMeasureRecord(String id) throws LIMSRuntimeException {
//
//		return getNextRecord(id, "UnitOfMeasure", UnitOfMeasure.class);
//
//	}
//
//	@Override
//	public List getPreviousUnitOfMeasureRecord(String id) throws LIMSRuntimeException {
//
//		return getPreviousRecord(id, "UnitOfMeasure", UnitOfMeasure.class);
//	}

//	@Override
//	public UnitOfMeasure getUnitOfMeasureByName(UnitOfMeasure unitOfMeasure) throws LIMSRuntimeException {
//		try {
//			String sql = "from UnitOfMeasure u where u.unitOfMeasureName = :param";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setParameter("param", unitOfMeasure.getUnitOfMeasureName());
//
//			List list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			UnitOfMeasure data = null;
//			if (list.size() > 0) {
//				data = (UnitOfMeasure) list.get(0);
//			}
//
//			return data;
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("UnitOfMeasureDAOImpl", "getUnitOfMeasureByName()", e.toString());
//			throw new LIMSRuntimeException("Error in UnitOfMeasure getUnitOfMeasureByName()", e);
//		}
//
//	}

//	@Override
//	public Integer getTotalUnitOfMeasureCount() throws LIMSRuntimeException {
//		return getCount();
//	}

    // overriding BaseDAOImpl bugzilla 1427 pass in name not id
//	@Override
//	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
//
//		List list;
//		try {
//			String sql = "from " + table + " t where name >= " + enquote(id) + " order by t.unitOfMeasureName";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setFirstResult(1);
//			query.setMaxResults(2);
//
//			list = query.list();
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("UnitOfMeasureDAOImpl", "getNextRecord()", e.toString());
//			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
//		}
//
//		return list;
//	}

    // overriding BaseDAOImpl bugzilla 1427 pass in name not id
//	@Override
//	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
//
//		List list;
//		try {
//			String sql = "from " + table + " t order by t.unitOfMeasureName desc where name <= " + enquote(id);
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setFirstResult(1);
//			query.setMaxResults(2);
//
//			list = query.list();
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("UnitOfMeasureDAOImpl", "getPreviousRecord()", e.toString());
//			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
//		}
//
//		return list;
//	}

    @Override
    public boolean duplicateUnitOfMeasureExists(UnitOfMeasure unitOfMeasure) throws LIMSRuntimeException {
        try {
            List<UnitOfMeasure> list;

            // not case sensitive hemolysis and Hemolysis are considered
            // duplicates
            String sql = "from UnitOfMeasure t where trim(lower(t.unitOfMeasureName)) = :param and t.id != :param2";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", unitOfMeasure.getUnitOfMeasureName().toLowerCase().trim());

            // initialize with 0 (for new records where no id has been generated
            // yet
            String unitOfMeasureId = "0";
            if (!StringUtil.isNullorNill(unitOfMeasure.getId())) {
                unitOfMeasureId = unitOfMeasure.getId();
            }
            query.setInteger("param2", Integer.parseInt(unitOfMeasureId));

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            return !list.isEmpty();

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in duplicateUnitOfMeasureExists()", e);
        }
    }
}