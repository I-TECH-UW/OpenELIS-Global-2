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
package org.openelisglobal.testanalyte.daoimpl;

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
import org.openelisglobal.test.valueholder.Test;
import org.openelisglobal.testanalyte.dao.TestAnalyteDAO;
import org.openelisglobal.testanalyte.valueholder.TestAnalyte;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diane benz
 */
@Component
@Transactional
public class TestAnalyteDAOImpl extends BaseDAOImpl<TestAnalyte, String> implements TestAnalyteDAO {

    public TestAnalyteDAOImpl() {
        super(TestAnalyte.class);
    }

//	@Override
//	public void deleteData(List testAnalytes) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < testAnalytes.size(); i++) {
//				TestAnalyte data = (TestAnalyte) testAnalytes.get(i);
//
//				TestAnalyte oldData = readTestAnalyte(data.getId());
//				TestAnalyte newData = new TestAnalyte();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "TEST_ANALYTE";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TestAnalyteDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestAnalyte AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < testAnalytes.size(); i++) {
//				TestAnalyte data = (TestAnalyte) testAnalytes.get(i);
//				// bugzilla 2206
//				data = readTestAnalyte(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TestAnalyteDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestAnalyte deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(TestAnalyte testAnalyte) throws LIMSRuntimeException {
//
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(testAnalyte);
//			testAnalyte.setId(id);
//
//			// bugzilla 1824 inserts will be logged in history table
//
//			String sysUserId = testAnalyte.getSysUserId();
//			String tableName = "TEST_ANALYTE";
//			auditDAO.saveNewHistory(testAnalyte, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TestAnalyteDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestAnalyte insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(TestAnalyte testAnalyte) throws LIMSRuntimeException {
//
//		TestAnalyte oldData = readTestAnalyte(testAnalyte.getId());
//		TestAnalyte newData = testAnalyte;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = testAnalyte.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "TEST_ANALYTE";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TestAnalyteDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestAnalyte AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(testAnalyte);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(testAnalyte);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(testAnalyte);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TestAnalyteDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestAnalyte updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public TestAnalyte getData(TestAnalyte testAnalyte) throws LIMSRuntimeException {
        try {
            TestAnalyte anal = entityManager.unwrap(Session.class).get(TestAnalyte.class, testAnalyte.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (anal != null) {
                PropertyUtils.copyProperties(testAnalyte, anal);
            } else {
                testAnalyte.setId(null);
            }

            return anal;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in TestAnalyte getData()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestAnalyte> getAllTestAnalytes() throws LIMSRuntimeException {
        List<TestAnalyte> list;
        try {
            String sql = "from TestAnalyte";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in TestAnalyte getAllTestAnalytes()", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestAnalyte> getPageOfTestAnalytes(int startingRecNo) throws LIMSRuntimeException {
        List<TestAnalyte> list;
        try {
            // calculate maxRow to be one more than the page size
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            String sql = "from TestAnalyte t order by t.id";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in TestAnalyte getPageOfTestAnalytes()", e);
        }

        return list;
    }

    public TestAnalyte readTestAnalyte(String idString) {
        TestAnalyte ta = null;
        try {
            ta = entityManager.unwrap(Session.class).get(TestAnalyte.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in TestAnalyte readTestAnalyte()", e);
        }

        return ta;

    }

    // this is for autocomplete
    // TODO: need to convert to hibernate ( not in use??? )
    @Override
    @Transactional(readOnly = true)
    public List<TestAnalyte> getTestAnalytes(String filter) throws LIMSRuntimeException {

        return null;
        /*
         * try { DatabaseSession aSession = getSession(); ReadAllQuery query = new
         * ReadAllQuery(TestAnalyte.class); ExpressionBuilder builder = new
         * ExpressionBuilder(); Expression exp = null; if (filter != null) { exp =
         * builder.get("testAnalyteName").toUpperCase().like( filter.toUpperCase() +
         * "%"); } else { exp = builder.get("testAnalyteName").like(filter + "%"); }
         *
         * // Expression exp1 = builder.get("isActive").equal(true); // exp =
         * exp.and(exp1);
         *
         * query.setSelectionCriteria(exp);
         * query.addAscendingOrdering("testAnalyteName");
         *
         * LogEvent.logInfo(this.getClass().getName(), "method unkown", "This is query "
         * + query.getSQLString()); List testAnalytes = (Vector)
         * aSession.executeQuery(query);
         *
         * LogEvent.logInfo(this.getClass().getName(), "method unkown",
         * "This is size of list retrieved " + testAnalytes.size() + " " +
         * testAnalytes.get(0)); return testAnalytes;
         *
         * } catch (RuntimeException e) { throw new LIMSRuntimeException(
         * "Error in TestAnalyte getTestAnalytes(String filter)", e); }
         */
    }

    @Override
    @Transactional(readOnly = true)
    public TestAnalyte getTestAnalyteById(TestAnalyte testAnalyte) throws LIMSRuntimeException {
        TestAnalyte newTestAnalyte;
        try {
            newTestAnalyte = entityManager.unwrap(Session.class).get(TestAnalyte.class, testAnalyte.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in TestAnalyte getTestAnalyteById()", e);
        }

        return newTestAnalyte;

    }

    @Override
    @Transactional(readOnly = true)
    public List<TestAnalyte> getAllTestAnalytesPerTest(Test test) throws LIMSRuntimeException {
        List<TestAnalyte> list;

        if (test == null || StringUtil.isNullorNill(test.getId())) {
            return new ArrayList();
        }

        try {
            String sql = "from TestAnalyte t where t.test = :testId order by t.sortOrder asc";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("testId", Integer.parseInt(test.getId()));

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in TestAnalyte getAllTestAnalytesPerTest()", e);
        }

        return list;

    }

}