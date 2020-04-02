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
package org.openelisglobal.method.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.method.dao.MethodDAO;
import org.openelisglobal.method.valueholder.Method;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diane benz
 */
@Component
@Transactional
public class MethodDAOImpl extends BaseDAOImpl<Method, String> implements MethodDAO {

    public MethodDAOImpl() {
        super(Method.class);
    }

//	@Override
//	public void deleteData(List methods) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < methods.size(); i++) {
//				Method data = (Method) methods.get(i);
//
//				Method oldData = readMethod(data.getId());
//				Method newData = new Method();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "METHOD";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("MethodDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Method AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < methods.size(); i++) {
//				Method data = (Method) methods.get(i);
//				Method cloneData = readMethod(data.getId());
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
//			LogEvent.logError("MethodDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Method deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(Method method) throws LIMSRuntimeException {
//
//		try {
//			// bugzilla 1482 throw Exception if active record already exists
//			if (duplicateMethodExists(method)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for " + method.getMethodName());
//			}
//
//			String id = (String) entityManager.unwrap(Session.class).save(method);
//			method.setId(id);
//
//			// bugzilla 1824 inserts will be logged in history table
//
//			String sysUserId = method.getSysUserId();
//			String tableName = "METHOD";
//			auditDAO.saveNewHistory(method, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("MethodDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in Method insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(Method method) throws LIMSRuntimeException {
//		// bugzilla 1482 throw Exception if active record already exists
//		try {
//			if (duplicateMethodExists(method)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for " + method.getMethodName());
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("MethodDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Method updateData()", e);
//		}
//
//		Method oldData = readMethod(method.getId());
//		Method newData = method;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = method.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "METHOD";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("MethodDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Method AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(method);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(method);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(method);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("MethodDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Method updateData()", e);
//		}
//	}

//	@Override
//	public void getData(Method method) throws LIMSRuntimeException {
//		try {
//			Method meth = entityManager.unwrap(Session.class).get(Method.class, method.getId());
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			if (meth != null) {
//
//				if (meth.getActiveBeginDate() != null) {
//					meth.setActiveBeginDateForDisplay(DateUtil.convertSqlDateToStringDate(meth.getActiveBeginDate()));
//				}
//				if (meth.getActiveEndDate() != null) {
//					meth.setActiveEndDateForDisplay(DateUtil.convertSqlDateToStringDate(meth.getActiveEndDate()));
//				}
//				PropertyUtils.copyProperties(method, meth);
//
//			} else {
//				method.setId(null);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("MethodDAOImpl", "getData()", e.toString());
//			throw new LIMSRuntimeException("Error in Method getData()", e);
//		}
//	}

//	@Override
//	public List getAllMethods() throws LIMSRuntimeException {
//		List list ;
//		try {
//			String sql = "from Method";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("MethodDAOImpl", "getAllMethods()", e.toString());
//			throw new LIMSRuntimeException("Error in Method getAllMethods()", e);
//		}
//
//		return list;
//	}

//	@Override
//	public List getPageOfMethods(int startingRecNo) throws LIMSRuntimeException {
//		List list ;
//		try {
//			// calculate maxRow to be one more than the page size
//			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
//
//			// bugzilla 1399
//			String sql = "from Method m order by m.methodName";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setFirstResult(startingRecNo - 1);
//			query.setMaxResults(endingRecNo - 1);
//
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("MethodDAOImpl", "getPageOfMethods()", e.toString());
//			throw new LIMSRuntimeException("Error in Method getPageOfMethods()", e);
//		}
//
//		return list;
//	}

//	public Method readMethod(String idString) {
//		Method method = null;
//		try {
//			method = entityManager.unwrap(Session.class).get(Method.class, idString);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("MethodDAOImpl", "readMethod()", e.toString());
//			throw new LIMSRuntimeException("Error in Method readMethod()", e);
//		}
//
//		return method;
//	}

//	@Override
//	public List getNextMethodRecord(String id) throws LIMSRuntimeException {
//
//		return getNextRecord(id, "Method", Method.class);
//
//	}
//
//	@Override
//	public List getPreviousMethodRecord(String id) throws LIMSRuntimeException {
//
//		return getPreviousRecord(id, "Method", Method.class);
//	}

    // this is for autocomplete
    @Override
    @Transactional(readOnly = true)
    public List<Method> getMethods(String filter) throws LIMSRuntimeException {
        List<Method> list = new Vector<>();
        try {
            String sql = "from Method m where upper(m.methodName) like upper(:param) and m.isActive='Y' order by upper(m.methodName)";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", filter + "%");

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Method getMethods(String filter)", e);
        }
        return list;

    }

//	@Override
//	public Method getMethodByName(Method method) throws LIMSRuntimeException {
//		try {
//			String sql = "from Method m where m.methodName = :param and m.isActive='Y'";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setParameter("param", method.getMethodName());
//
//			List list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			Method m = null;
//			if (list.size() > 0) {
//				m = (Method) list.get(0);
//			}
//
//			return m;
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("MethodDAOImpl", "getMethodByName()", e.toString());
//			throw new LIMSRuntimeException("Error in Method getMethodByName()", e);
//		}
//	}

    // bugzilla 1411
//	@Override
//	public Integer getTotalMethodCount() throws LIMSRuntimeException {
//		return getCount();
//	}

    // overriding BaseDAOImpl bugzilla 1427 pass in name not id
//	@Override
//	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
//
//		List list ;
//		try {
//			String sql = "from " + table + " t where name >= " + enquote(id) + " order by t.methodName";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setFirstResult(1);
//			query.setMaxResults(2);
//
//			list = query.list();
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("MethodDAOImpl", "getNextRecord()", e.toString());
//			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
//		}
//
//		return list;
//	}

    // overriding BaseDAOImpl bugzilla 1427 pass in name not id
//	@Override
//	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
//
//		List list ;
//		try {
//			String sql = "from " + table + " t order by t.methodName desc where name <= " + enquote(id);
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setFirstResult(1);
//			query.setMaxResults(2);
//
//			list = query.list();
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("MethodDAOImpl", "getPreviousRecord()", e.toString());
//			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
//		}
//
//		return list;
//	}

    // bugzilla 1482
    @Override
    public boolean duplicateMethodExists(Method method) throws LIMSRuntimeException {
        try {

            List<Method> list = new ArrayList<>();

            // not case sensitive hemolysis and Hemolysis are considered
            // duplicates
            String sql = "from Method t where trim(lower(t.methodName)) = :param and t.id != :param2";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", method.getMethodName().toLowerCase().trim());

            // initialize with 0 (for new records where no id has been generated yet
            String methodId = "0";
            if (!StringUtil.isNullorNill(method.getId())) {
                methodId = method.getId();
            }
            query.setParameter("param2", methodId);

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
            throw new LIMSRuntimeException("Error in duplicateMethodExists()", e);
        }
    }

}