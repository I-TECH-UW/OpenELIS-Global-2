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
package org.openelisglobal.typeoftestresult.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.typeoftestresult.dao.TypeOfTestResultDAO;
import org.openelisglobal.typeoftestresult.valueholder.TypeOfTestResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diane benz
 */
@Component
@Transactional
public class TypeOfTestResultDAOImpl extends BaseDAOImpl<TypeOfTestResult, String> implements TypeOfTestResultDAO {

    public TypeOfTestResultDAOImpl() {
        super(TypeOfTestResult.class);
    }

//	@Override
//	public void deleteData(List typeOfTestResults) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < typeOfTestResults.size(); i++) {
//				TypeOfTestResult data = (TypeOfTestResult) typeOfTestResults.get(i);
//
//				TypeOfTestResult oldData = readTypeOfTestResult(data.getId());
//				TypeOfTestResult newData = new TypeOfTestResult();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "TYPE_OF_TEST_RESULT";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("TypeOfTestResultDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in TypeOfTestResult AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < typeOfTestResults.size(); i++) {
//				TypeOfTestResult data = (TypeOfTestResult) typeOfTestResults.get(i);
//
//				data = readTypeOfTestResult(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("TypeOfTestResultDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in TypeOfTestResult deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(TypeOfTestResult typeOfTestResult) throws LIMSRuntimeException {
//		try {
//			// bugzilla 1482 throw Exception if record already exists
//			if (duplicateTypeOfTestResultExists(typeOfTestResult)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + typeOfTestResult.getDescription());
//			}
//
//			String id = (String) entityManager.unwrap(Session.class).save(typeOfTestResult);
//			typeOfTestResult.setId(id);
//
//			// bugzilla 1824 inserts will be logged in history table
//
//			String sysUserId = typeOfTestResult.getSysUserId();
//			String tableName = "TYPE_OF_TEST_RESULT";
//			auditDAO.saveNewHistory(typeOfTestResult, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TypeOfTestResultDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in TypeOfTestResult insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(TypeOfTestResult typeOfTestResult) throws LIMSRuntimeException {
//		// bugzilla 1482 throw Exception if record already exists
//		try {
//			if (duplicateTypeOfTestResultExists(typeOfTestResult)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + typeOfTestResult.getDescription());
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TypeOfTestResultDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in TypeOfTestResult updateData()", e);
//		}
//
//		TypeOfTestResult oldData = readTypeOfTestResult(typeOfTestResult.getId());
//
//		try {
//
//			String sysUserId = typeOfTestResult.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "TYPE_OF_TEST_RESULT";
//			auditDAO.saveHistory(typeOfTestResult, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			LogEvent.logError("TypeOfTestResultDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in TypeOfTestResult AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(typeOfTestResult);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(typeOfTestResult);
//			// entityManager.unwrap(Session.class).refresh // CSL remove
//			// old(typeOfTestResult);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TypeOfTestResultDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in TypeOfTestResult updateData()", e);
//		}
//	}

//	@Override
//	public void getData(TypeOfTestResult typeOfTestResult) throws LIMSRuntimeException {
//		try {
//			TypeOfTestResult sc = entityManager.unwrap(Session.class).get(TypeOfTestResult.class,
//					typeOfTestResult.getId());
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			if (sc != null) {
//				PropertyUtils.copyProperties(typeOfTestResult, sc);
//			} else {
//				typeOfTestResult.setId(null);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TypeOfTestResultDAOImpl", "getData()", e.toString());
//			throw new LIMSRuntimeException("Error in TypeOfTestResult getData()", e);
//		}
//	}

//	@Override
//	public List getAllTypeOfTestResults() throws LIMSRuntimeException {
//		List list;
//		try {
//			String sql = "from TypeOfTestResult";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			// query.setMaxResults(10);
//			// query.setFirstResult(3);
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TypeOfTestResultDAOImpl", "getAllTypeOfTestResults()", e.toString());
//			throw new LIMSRuntimeException("Error in TypeOfTestResult getAllTypeOfTestResults()", e);
//		}
//
//		return list;
//	}

//	@Override
//	public List getPageOfTypeOfTestResults(int startingRecNo) throws LIMSRuntimeException {
//		List list;
//		try {
//			// calculate maxRow to be one more than the page size
//			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
//
//			String sql = "from TypeOfTestResult t order by t.description";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setFirstResult(startingRecNo - 1);
//			query.setMaxResults(endingRecNo - 1);
//
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TypeOfTestResultDAOImpl", "getPageOfTypeOfTestResults()", e.toString());
//			throw new LIMSRuntimeException("Error in TypeOfTestResult getPageOfTypeOfTestResults()", e);
//		}
//
//		return list;
//	}

//	public TypeOfTestResult readTypeOfTestResult(String idString) {
//		TypeOfTestResult data;
//		try {
//			data = entityManager.unwrap(Session.class).get(TypeOfTestResult.class, idString);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TypeOfTestResultDAOImpl", "readTypeOfTestResult()", e.toString());
//			throw new LIMSRuntimeException("Error in TypeOfTestResult readTypeOfTestResult()", e);
//		}
//
//		return data;
//	}

//	@Override
//	public List getNextTypeOfTestResultRecord(String id) throws LIMSRuntimeException {
//
//		return getNextRecord(id, "TypeOfTestResult", TypeOfTestResult.class);
//
//	}

//	@Override
//	public List getPreviousTypeOfTestResultRecord(String id) throws LIMSRuntimeException {
//
//		return getPreviousRecord(id, "TypeOfTestResult", TypeOfTestResult.class);
//	}

    // bugzilla 1411
//	@Override
//	public Integer getTotalTypeOfTestResultCount() throws LIMSRuntimeException {
//		return getCount();
//	}

    // overriding BaseDAOImpl bugzilla 1427 pass in name not id
//	@Override
//	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
//
//		List list;
//		try {
//			String sql = "from " + table + " t where description >= " + enquote(id) + " order by t.description";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setFirstResult(1);
//			query.setMaxResults(2);
//
//			list = query.list();
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TypeOfTestResultDAOImpl", "getNextRecord()", e.toString());
//			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
//		}
//
//		return list;
//	}
//
//	// overriding BaseDAOImpl bugzilla 1427 pass in name not id
//	@Override
//	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
//
//		List list;
//		try {
//			String sql = "from " + table + " t order by t.description desc where description <= " + enquote(id);
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setFirstResult(1);
//			query.setMaxResults(2);
//
//			list = query.list();
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TypeOfTestResultDAOImpl", "getPreviousRecord()", e.toString());
//			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
//		}
//
//		return list;
//	}

    // bugzilla 1482
    @Override
    public boolean duplicateTypeOfTestResultExists(TypeOfTestResult typeOfTestResult) throws LIMSRuntimeException {
        try {

            List<TypeOfTestResult> list;

            // not case sensitive hemolysis and Hemolysis are considered
            // duplicates
            String sql = "from TypeOfTestResult t where (trim(lower(t.description)) = :param and t.id != :param2) or (trim(lower(t.testResultType)) = :param3 and t.id != :param2)";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", typeOfTestResult.getDescription().toLowerCase().trim());
            query.setParameter("param3", typeOfTestResult.getTestResultType().toLowerCase().trim());

            // initialize with 0 (for new records where no id has been generated
            // yet
            String typeOfTestResultId = "0";
            if (!StringUtil.isNullorNill(typeOfTestResult.getId())) {
                typeOfTestResultId = typeOfTestResult.getId();
            }
            query.setParameter("param2", typeOfTestResultId);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            return list.size() > 0;

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in duplicateTypeOfTestResultExists()", e);
        }
    }

    // bugzilla 1866 to get HL7 value
    @Override
    @Transactional(readOnly = true)
    public TypeOfTestResult getTypeOfTestResultByType(TypeOfTestResult typeOfTestResult) throws LIMSRuntimeException {
        TypeOfTestResult totr = null;
        try {
            String sql = "from TypeOfTestResult totr where upper(totr.testResultType) = :param";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", typeOfTestResult.getTestResultType().trim().toUpperCase());

            List<TypeOfTestResult> list = query.list();

            if (list != null && list.size() > 0) {
                totr = list.get(0);
            }

            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in getTypeOfTestResultByType()", e);
        }

        return totr;
    }

//	@Override
//	public TypeOfTestResult getTypeOfTestResultByType(String type) throws LIMSRuntimeException {
//		String sql = "from TypeOfTestResult ttr where ttr.testResultType = :type";
//		try {
//			Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setString("type", type);
//			TypeOfTestResult typeOfTestResult = (TypeOfTestResult) query.uniqueResult();
//			// closeSession(); // CSL remove old
//			return typeOfTestResult;
//		} catch (HibernateException e) {
//			handleException(e, "getTypeOfTestResultByType");
//		}
//
//		return null;
//	}
}