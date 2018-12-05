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
package us.mn.state.health.lims.testreflex.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.hibernate.Query;

import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;
import us.mn.state.health.lims.testreflex.dao.TestReflexDAO;
import us.mn.state.health.lims.testreflex.valueholder.TestReflex;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

/**
 * @author diane benz 11/17/2007 instead of printing StackTrace log error
 */
public class TestReflexDAOImpl extends BaseDAOImpl implements TestReflexDAO {

	public void deleteData(List testReflexs) throws LIMSRuntimeException {
		// add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < testReflexs.size(); i++) {
				TestReflex data = (TestReflex) testReflexs.get(i);

				TestReflex oldData = (TestReflex) readTestReflex(data.getId());
				TestReflex newData = new TestReflex();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "TEST_REFLEX";
				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestReflexDAOImpl", "AuditTrail deleteData()", e.toString());
			throw new LIMSRuntimeException("Error in TestReflex AuditTrail deleteData()", e);
		}

		try {
			for (int i = 0; i < testReflexs.size(); i++) {
				TestReflex data = (TestReflex) testReflexs.get(i);
				// bugzilla 2206
				data = (TestReflex) readTestReflex(data.getId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestReflexDAOImpl", "deleteData()", e.toString());
			throw new LIMSRuntimeException("Error in TestReflex deleteData()", e);
		}
	}

	public boolean insertData(TestReflex testReflex) throws LIMSRuntimeException {
		try {
			// bugzilla 1482 throw Exception if record already exists
			if (duplicateTestReflexExists(testReflex)) {
				throw new LIMSDuplicateRecordException("Duplicate record exists for " + TestService.getUserLocalizedTestName( testReflex.getTest() ) + BLANK
						+ testReflex.getTestAnalyte().getAnalyte().getAnalyteName() + BLANK + testReflex.getTestResult().getValue() + BLANK
						+ TestService.getUserLocalizedTestName( testReflex.getAddedTest() ));
			}

			String id = (String) HibernateUtil.getSession().save(testReflex);
			testReflex.setId(id);

			// bugzilla 1824 inserts will be logged in history table
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = testReflex.getSysUserId();
			String tableName = "TEST_REFLEX";
			auditDAO.saveNewHistory(testReflex, sysUserId, tableName);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestReflexDAOImpl", "insertData()", e.toString());
			throw new LIMSRuntimeException("Error in TestReflex insertData()", e);
		}

		return true;
	}

	public void updateData(TestReflex testReflex) throws LIMSRuntimeException {
		// bugzilla 1482 throw Exception if record already exists
		try {
			if (duplicateTestReflexExists(testReflex)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for "
						+ TestService.getUserLocalizedTestName( testReflex.getTest() ) + BLANK
						+ testReflex.getTestAnalyte().getAnalyte().getAnalyteName() +BLANK
						+ testReflex.getTestResult().getValue() + BLANK
						+ TestService.getUserLocalizedTestName( testReflex.getAddedTest() ));
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestReflexDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in TestReflex updateData()", e);
		}

		TestReflex oldData = (TestReflex) readTestReflex(testReflex.getId());
		TestReflex newData = testReflex;

		// add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = testReflex.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "TEST_REFLEX";
			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestReflexDAOImpl", "AuditTrail updateData()", e.toString());
			throw new LIMSRuntimeException("Error in TestReflex AuditTrail updateData()", e);
		}

		try {
			HibernateUtil.getSession().merge(testReflex);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(testReflex);
			HibernateUtil.getSession().refresh(testReflex);
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestReflexDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in TestReflex updateData()", e);
		}
	}

	public void getData(TestReflex testReflex) throws LIMSRuntimeException {
		try {
			TestReflex tr = (TestReflex) HibernateUtil.getSession().get(TestReflex.class, testReflex.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (tr != null) {
				PropertyUtils.copyProperties(testReflex, tr);
			} else {
				testReflex.setId(null);
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestReflexDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in TestReflex getData()", e);
		}
	}

	public List getAllTestReflexs() throws LIMSRuntimeException {
		List list = null;
		try {
			String sql = "from TestReflex t order by t.test.testName, t.testAnalyte.analyte.analyteName";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			// query.setMaxResults(10);
			// query.setFirstResult(3);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestReflexDAOImpl", "getAllTestReflexs()", e.toString());
			throw new LIMSRuntimeException("Error in TestReflex getAllTestReflexs()", e);
		}

		return list;
	}

	public List getPageOfTestReflexs(int startingRecNo) throws LIMSRuntimeException {
		List list = new Vector();
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			// bugzilla 1399 - still need to figure out how to sort (3rd sort
			// column) for dictionary values - requires further step of getting
			// value from dictionary table before sorting
			String sql = "from TestReflex t order by t.test.testName, t.testAnalyte.analyte.analyteName";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestReflexDAOImpl", "getPageOfTestReflexs()", e.toString());
			throw new LIMSRuntimeException("Error in TestReflex getPageOfTestReflexs()", e);
		}

		return list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @seeus.mn.state.health.lims.testreflex.dao.TestReflexDAO#
	 * getTestReflexesByTestResult
	 * (us.mn.state.health.lims.testreflex.valueholder.TestReflex,
	 * us.mn.state.health.lims.testresult.valueholder.TestResult)
	 */
	public List getTestReflexesByTestResult(TestResult testResult) throws LIMSRuntimeException {
		try {
			String sql = "from TestReflex t where t.testResult.id = :testResultId";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("testResultId", Integer.parseInt(testResult.getId()));

			List list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			return list;

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestReflexDAOImpl", "getTestReflexesByTestResult()", e.toString());
			throw new LIMSRuntimeException("Error in TestReflex getTestReflexesByTestResult(TestResult testResult)", e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @seeus.mn.state.health.lims.testreflex.dao.TestReflexDAO#
	 * getTestReflexesByTestResult
	 * (us.mn.state.health.lims.testreflex.valueholder.TestReflex,
	 * us.mn.state.health.lims.testresult.valueholder.TestResult)
	 */
	public List getTestReflexesByTestResultAndTestAnalyte(TestResult testResult, TestAnalyte testAnalyte) throws LIMSRuntimeException {
		try {
			// bugzilla 1404 testResultId is mapped as testResult.id now
			String sql = "from TestReflex t where t.testResult.id = :param and t.testAnalyte.id = :param2";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", testResult.getId());
			query.setParameter("param2", testAnalyte.getId());

			List list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			return list;

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestReflexDAOImpl", "getTestReflexesByTestResult()", e.toString());
			throw new LIMSRuntimeException("Error in TestReflex getTestReflexesByTestResult(TestResult testResult)", e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @seeus.mn.state.health.lims.testreflex.dao.TestReflexDAO#
	 * getTestReflexesByTestResult
	 * (us.mn.state.health.lims.testreflex.valueholder.TestReflex,
	 * us.mn.state.health.lims.testresult.valueholder.TestResult) bugzilla 1798
	 * find out if a test with a parent was reflexed or linked
	 */
	public boolean isReflexedTest(Analysis analysis) throws LIMSRuntimeException {
		try {
			List list = null;

			if (analysis.getParentAnalysis() != null && analysis.getParentResult() != null) {
				String sql = "from TestReflex t where t.testResult.id = :param and t.testAnalyte.analyte.id = :param2 and t.test.id = :param3 and t.addedTest.id = :param4";
				org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
				query.setParameter("param", analysis.getParentResult().getTestResult().getId());
				query.setParameter("param2", analysis.getParentResult().getAnalyte().getId());
				query.setParameter("param3", analysis.getParentAnalysis().getTest().getId());
				query.setParameter("param4", analysis.getTest().getId());

				list = query.list();
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}

			return list != null && list.size() > 0;

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestReflexDAOImpl", "getTestReflexesByTestResult()", e.toString());
			throw new LIMSRuntimeException("Error in TestReflex getTestReflexesByTestResult(TestResult testResult)", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<TestReflex> getTestReflexsByTestResultAnalyteTest(String testResultId, String analyteId, String testId) throws LIMSRuntimeException {
		if (!GenericValidator.isBlankOrNull(testResultId) &&
			!GenericValidator.isBlankOrNull(analyteId) &&
			!GenericValidator.isBlankOrNull(testId)){
			try {
				List<TestReflex> list = null;

				String sql = "from TestReflex t where t.testResult.id = :testResultId and t.testAnalyte.analyte.id = :analyteId and t.test.id = :testId";
				org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
				query.setInteger("testResultId", Integer.parseInt(testResultId));
				query.setInteger("analyteId", Integer.parseInt(analyteId));
				query.setInteger("testId", Integer.parseInt(testId));

				list = query.list();
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();

				return list;
			} catch (Exception e) {
				LogEvent.logError("TestReflexDAOImpl", "getTestReflexByTestResultAnalyteTest", e.toString());
				throw new LIMSRuntimeException("Error in TestReflex getTestReflexByTestResultAnalyteTest(String testResultId, String analyteId, String testId)", e);
			}
		}
		return new ArrayList<TestReflex>();
	}

	public TestReflex readTestReflex(String idString) {
		TestReflex tr = null;
		try {
			tr = (TestReflex) HibernateUtil.getSession().get(TestReflex.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestReflexDAOImpl", "readTestReflex()", e.toString());
			throw new LIMSRuntimeException("Error in TestReflex readTestReflex()", e);
		}
		return tr;
	}

	public List getNextTestReflexRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "TestReflex", TestReflex.class);

	}

	public List getPreviousTestReflexRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "TestReflex", TestReflex.class);
	}

	// bugzilla 1411
	public Integer getTotalTestReflexCount() throws LIMSRuntimeException {
		return getTotalCount("TestReflex", TestReflex.class);
	}

	// bugzilla 1427
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
		int currentId = (Integer.valueOf(id)).intValue();
		String tablePrefix = getTablePrefix(table);

		List list = new Vector();
		// bugzilla 1908
		int rrn = 0;
		try {
			//bugzilla 1908 cannot use named query for postgres because of oracle ROWNUM
			//instead get the list in this sortorder and determine the index of record with id = currentId
    		String sql = "select tr.id from TestReflex tr " +
					" order by tr.test.testName, tr.testAnalyte.analyte.analyteName";

			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			rrn = list.indexOf(String.valueOf(currentId));

			list = HibernateUtil.getSession().getNamedQuery(tablePrefix + "getNext").setFirstResult(rrn + 1).setMaxResults(2).list();

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestReflexDAOImpl", "getNextRecord()", e.toString());
			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
		}

		return list;
	}

	// bugzilla 1427
	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
		int currentId = (Integer.valueOf(id)).intValue();
		String tablePrefix = getTablePrefix(table);

		List list = new Vector();
		// bugzilla 1908
		int rrn = 0;
		try {
			//bugzilla 1908 cannot use named query for postgres because of oracle ROWNUM
			//instead get the list in this sortorder and determine the index of record with id = currentId
    		String sql = "select tr.id from TestReflex tr " +
					" order by tr.test.testName desc, tr.testAnalyte.analyte.analyteName desc";

			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			rrn = list.indexOf(String.valueOf(currentId));

			list = HibernateUtil.getSession().getNamedQuery(tablePrefix + "getPrevious")
			.setFirstResult(rrn + 1)
			.setMaxResults(2)
			.list();


		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestReflexDAOImpl", "getPreviousRecord()", e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		}

		return list;
	}

	// bugzilla 1482
	private boolean duplicateTestReflexExists(TestReflex testReflex) throws LIMSRuntimeException {
		try {

			List list = new ArrayList();

			// not case sensitive hemolysis and Hemolysis are considered
			// duplicates
			String sql = "from TestReflex t where t.test.localizedTestName = :localizedTestNameId and " +
			             "trim(lower(t.testAnalyte.analyte.analyteName)) = :analyteName and " +
			             "t.testResult.id = :resultId and " +
			             "t.addedTest.localizedTestName = :addedTestNameId or " +
			             "trim(lower(t.actionScriptlet.scriptletName)) = :scriptletName  ) and " +
			             " t.id != :testId";
			Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setInteger( "localizedTestName", Integer.parseInt( testReflex.getTest().getLocalizedTestName().getId() ));
			query.setString("analyteName", testReflex.getTestAnalyte().getAnalyte().getAnalyteName().toLowerCase().trim());
			query.setInteger("resultId", Integer.parseInt(testReflex.getTestResult().getId()));
			query.setInteger( "addedTestNameId", testReflex.getAddedTest() == null ? -1 : Integer.parseInt( testReflex.getAddedTest().getLocalizedTestName().getId() ) );
			query.setString("scriptletName", testReflex.getActionScriptlet() == null ? null : testReflex.getActionScriptlet().getScriptletName().toLowerCase().trim());

			String testReflexId = StringUtil.isNullorNill(testReflex.getId()) ? "0" : testReflex.getId();

			query.setInteger("testId", Integer.parseInt(testReflexId));

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			if (list.size() > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			LogEvent.logError("TestReflexDAOImpl", "duplicateTestReflexExists()", e.toString());
			throw new LIMSRuntimeException("Error in duplicateTestReflexExists()", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<TestReflex> getTestReflexsByTestAndFlag(String testId, String flag) throws LIMSRuntimeException {
		if( GenericValidator.isBlankOrNull(testId)){
			return new ArrayList<TestReflex>();
		}

		List<TestReflex> reflexList = null;

		try{
		Query query = null;
		if( GenericValidator.isBlankOrNull(flag)){
			String sql = "from TestReflex tr where tr.testResult.test.id = :id";
			query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("id", Integer.parseInt(testId));
		}else{
			String sql = "from TestReflex tr where tr.testResult.test.id = :id and tr.flags = :flag";
			query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("id", Integer.parseInt(testId));
			query.setString("flag", flag);
		}
		reflexList = query.list();

		HibernateUtil.getSession().flush();
		HibernateUtil.getSession().clear();

		}catch( Exception e){
			handleException(e, "getTestReflexsByTestAndFlag()");
		}

		return reflexList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestReflex> getFlaggedTestReflexesByTestResult(TestResult testResult, String flag) throws LIMSRuntimeException {
		if( GenericValidator.isBlankOrNull(flag)){
			return new ArrayList<TestReflex>();
		}
		
		try {
			String sql = "from TestReflex t where t.testResult.id = :testResultId and t.flags = :flag";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("testResultId", Integer.parseInt(testResult.getId()));
			query.setString("flag", flag);
			List<TestReflex> list = query.list();
			closeSession();
			return list;
		} catch (Exception e) {
			handleException(e, "getFlaggedTestReflexesByTestResult");
		}

		return null;
	}

}