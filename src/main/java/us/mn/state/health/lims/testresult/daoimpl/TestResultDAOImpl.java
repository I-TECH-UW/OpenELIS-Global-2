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
package us.mn.state.health.lims.testresult.daoimpl;

import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;
import us.mn.state.health.lims.testresult.dao.TestResultDAO;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

/**
 * @author diane benz
 */
@Component
@Transactional
public class TestResultDAOImpl extends BaseDAOImpl<TestResult, String> implements TestResultDAO {

	public TestResultDAOImpl() {
		super(TestResult.class);
	}

	@Autowired
	private AuditTrailDAO auditDAO;

//	@Override
//	public void deleteData(List testResults) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (Object testResult : testResults) {
//				TestResult data = (TestResult) testResult;
//
//				TestResult oldData = readTestResult(data.getId());
//				TestResult newData = new TestResult();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "TEST_RESULT";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("TestResultDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestResult AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (Object testResult : testResults) {
//				TestResult data = (TestResult) testResult;
//				// bugzilla 2206
//				data = readTestResult(data.getId());
//				sessionFactory.getCurrentSession().delete(data);
//				// sessionFactory.getCurrentSession().flush(); // CSL remove old
//				// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			}
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("TestResultDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestResult deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(TestResult testResult) throws LIMSRuntimeException {
//
//		try {
//			String id = (String) sessionFactory.getCurrentSession().save(testResult);
//			testResult.setId(id);
//
//			// bugzilla 1824 inserts will be logged in history table
//
//			String sysUserId = testResult.getSysUserId();
//			String tableName = "TEST_RESULT";
//			auditDAO.saveNewHistory(testResult, sysUserId, tableName);
//
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("TestResultDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestResult insertData()", e);
//		}
//
//		return true;
//	}
//
//	@Override
//	public void updateData(TestResult testResult) throws LIMSRuntimeException {
//
//		TestResult oldData = readTestResult(testResult.getId());
//
//		try {
//
//			String sysUserId = testResult.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "TEST_RESULT";
//			auditDAO.saveHistory(testResult, oldData, sysUserId, event, tableName);
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("TestResultDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestResult AuditTrail updateData()", e);
//		}
//
//		try {
//			sessionFactory.getCurrentSession().merge(testResult);
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			// sessionFactory.getCurrentSession().evict // CSL remove old(testResult);
//			// sessionFactory.getCurrentSession().refresh // CSL remove old(testResult);
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("TestResultDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestResult updateData()", e);
//		}
//	}

	@Override
	public void getData(TestResult testResult) throws LIMSRuntimeException {
		try {
			TestResult tr = sessionFactory.getCurrentSession().get(TestResult.class, testResult.getId());
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
			if (tr != null) {
				PropertyUtils.copyProperties(testResult, tr);
			} else {
				testResult.setId(null);
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestResultDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in TestResult getData()", e);
		}
	}

	@Override
	public List getAllTestResults() throws LIMSRuntimeException {
		List list;
		try {
			String sql = "from TestResult";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestResultDAOImpl", "getAllTestResults()", e.toString());
			throw new LIMSRuntimeException("Error in TestResult getAllTestResults()", e);
		}

		return list;
	}

	@Override
	public List getPageOfTestResults(int startingRecNo) throws LIMSRuntimeException {
		List list;
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
			String sql = "from TestResult t order by t.test.description";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);

			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestResultDAOImpl", "getPageOfTestResults()", e.toString());
			throw new LIMSRuntimeException("Error in TestResult getPageOfTestResults()", e);
		}

		return list;
	}

	public TestResult readTestResult(String idString) {
		TestResult tr;
		try {
			tr = sessionFactory.getCurrentSession().get(TestResult.class, idString);
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestResultDAOImpl", "readTestResult()", e.toString());
			throw new LIMSRuntimeException("Error in TestResult readTestResult()", e);
		}

		return tr;

	}

	@Override
	public List getNextTestResultRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "TestResult", TestResult.class);

	}

	@Override
	public List getPreviousTestResultRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "TestResult", TestResult.class);
	}

	@Override
	public TestResult getTestResultById(TestResult testResult) throws LIMSRuntimeException {
		TestResult newTestResult;
		try {
			newTestResult = sessionFactory.getCurrentSession().get(TestResult.class, testResult.getId());
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestResultDAOImpl", "getTestResultById()", e.toString());
			throw new LIMSRuntimeException("Error in TestResult getTestResultById()", e);
		}

		return newTestResult;
	}

	@Override
	public List getTestResultsByTestAndResultGroup(TestAnalyte testAnalyte) throws LIMSRuntimeException {
		List list = new Vector();
		try {
			if (testAnalyte.getId() != null && testAnalyte.getResultGroup() != null) {
				// bugzilla 1845 added testResult sortOrder
				String sql = "from TestResult t where t.test = :testId and t.resultGroup = :resultGroup order by t.sortOrder";
				org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);

				query.setInteger("testId", Integer.parseInt(testAnalyte.getTest().getId()));
				query.setInteger("resultGroup", Integer.parseInt(testAnalyte.getResultGroup()));

				list = query.list();
				// sessionFactory.getCurrentSession().flush(); // CSL remove old
				// sessionFactory.getCurrentSession().clear(); // CSL remove old
			}

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestResultDAOImpl", "getTestResultByTestAndResultGroup()", e.toString());
			throw new LIMSRuntimeException("Error in TestResult getTestResultByTestAndResultGroup()", e);
		}

		return list;

	}

	@Override
	public List getAllActiveTestResultsPerTest(Test test) throws LIMSRuntimeException {
		if (test == null || (test.getId() == null) || (test.getId().length() == 0)) {
			return null;
		}

		List list;
		try {
			String sql = "from TestResult t where t.test = :testId and t.isActive = true order by t.resultGroup, t.id asc";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setInteger("testId", Integer.parseInt(test.getId()));

			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("TestResultDAOImpl", "getAllActiveTestResultsPerTest()", e.toString());
			throw new LIMSRuntimeException("Error in TestResult getAllActiveTestResultsPerTest()", e);
		}

		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public TestResult getTestResultsByTestAndDictonaryResult(String testId, String result) throws LIMSRuntimeException {
		if (StringUtil.isInteger(result)) {
			List<TestResult> list;
			try {
				String sql = "from TestResult t where  t.testResultType in ('D','M','Q') and t.test = :testId and t.value = :testValue";
				org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
				query.setInteger("testId", Integer.parseInt(testId));
				query.setString("testValue", result);

				list = query.list();

				if (list != null && !list.isEmpty()) {
					return list.get(0);
				}

			} catch (Exception e) {
				LogEvent.logError("TestResultDAOImpl", "getTestResultsByTestAndDictonaryResult", e.toString());
				throw new LIMSRuntimeException(
						"Error in TestResult getTestResultsByTestAndDictonaryResult(String testId, String result)", e);
			}
		}

		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TestResult> getActiveTestResultsByTest(String testId) throws LIMSRuntimeException {
		List<TestResult> list;
		try {
			String sql = "from TestResult t where  t.test = :testId and t.isActive = true";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setInteger("testId", Integer.parseInt(testId));

			list = query.list();

			// closeSession(); // CSL remove old
			return list;

		} catch (Exception e) {
			handleException(e, "getActiveTestResultsByTest");
		}

		return null;
	}

}