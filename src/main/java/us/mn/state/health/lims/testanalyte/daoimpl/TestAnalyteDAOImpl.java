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
package us.mn.state.health.lims.testanalyte.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testanalyte.dao.TestAnalyteDAO;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;

/**
 * @author diane benz
 */
@Component
@Transactional 
public class TestAnalyteDAOImpl extends BaseDAOImpl<TestAnalyte, String> implements TestAnalyteDAO {

	public TestAnalyteDAOImpl() {
		super(TestAnalyte.class);
	}

	@Override
	public void deleteData(List testAnalytes) throws LIMSRuntimeException {
		// add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < testAnalytes.size(); i++) {
				TestAnalyte data = (TestAnalyte) testAnalytes.get(i);

				TestAnalyte oldData = readTestAnalyte(data.getId());
				TestAnalyte newData = new TestAnalyte();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "TEST_ANALYTE";
				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestAnalyteDAOImpl", "AuditTrail deleteData()", e.toString());
			throw new LIMSRuntimeException("Error in TestAnalyte AuditTrail deleteData()", e);
		}

		try {
			for (int i = 0; i < testAnalytes.size(); i++) {
				TestAnalyte data = (TestAnalyte) testAnalytes.get(i);
				// bugzilla 2206
				data = readTestAnalyte(data.getId());
				sessionFactory.getCurrentSession().delete(data);
				// sessionFactory.getCurrentSession().flush(); // CSL remove old
				// sessionFactory.getCurrentSession().clear(); // CSL remove old
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestAnalyteDAOImpl", "deleteData()", e.toString());
			throw new LIMSRuntimeException("Error in TestAnalyte deleteData()", e);
		}
	}

	@Override
	public boolean insertData(TestAnalyte testAnalyte) throws LIMSRuntimeException {

		try {
			String id = (String) sessionFactory.getCurrentSession().save(testAnalyte);
			testAnalyte.setId(id);

			// bugzilla 1824 inserts will be logged in history table
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = testAnalyte.getSysUserId();
			String tableName = "TEST_ANALYTE";
			auditDAO.saveNewHistory(testAnalyte, sysUserId, tableName);

			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestAnalyteDAOImpl", "insertData()", e.toString());
			throw new LIMSRuntimeException("Error in TestAnalyte insertData()", e);
		}

		return true;
	}

	@Override
	public void updateData(TestAnalyte testAnalyte) throws LIMSRuntimeException {

		TestAnalyte oldData = readTestAnalyte(testAnalyte.getId());
		TestAnalyte newData = testAnalyte;

		// add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = testAnalyte.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "TEST_ANALYTE";
			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestAnalyteDAOImpl", "AuditTrail updateData()", e.toString());
			throw new LIMSRuntimeException("Error in TestAnalyte AuditTrail updateData()", e);
		}

		try {
			sessionFactory.getCurrentSession().merge(testAnalyte);
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
			// sessionFactory.getCurrentSession().evict // CSL remove old(testAnalyte);
			// sessionFactory.getCurrentSession().refresh // CSL remove old(testAnalyte);
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestAnalyteDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in TestAnalyte updateData()", e);
		}
	}

	@Override
	public TestAnalyte getData(TestAnalyte testAnalyte) throws LIMSRuntimeException {
		try {
			TestAnalyte anal = (TestAnalyte) sessionFactory.getCurrentSession().get(TestAnalyte.class, testAnalyte.getId());
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
			if (anal != null) {
				PropertyUtils.copyProperties(testAnalyte, anal);
			} else {
				testAnalyte.setId(null);
			}

			return anal;
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestAnalyteDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in TestAnalyte getData()", e);
		}
	}

	@Override
	public List getAllTestAnalytes() throws LIMSRuntimeException {
		List list = new Vector();
		try {
			String sql = "from TestAnalyte";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestAnalyteDAOImpl", "getAllTestAnalytes()", e.toString());
			throw new LIMSRuntimeException("Error in TestAnalyte getAllTestAnalytes()", e);
		}

		return list;
	}

	@Override
	public List getPageOfTestAnalytes(int startingRecNo) throws LIMSRuntimeException {
		List list = new Vector();
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			String sql = "from TestAnalyte t order by t.id";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);

			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestAnalyteDAOImpl", "getPageOfTestAnalytes()", e.toString());
			throw new LIMSRuntimeException("Error in TestAnalyte getPageOfTestAnalytes()", e);
		}

		return list;
	}

	public TestAnalyte readTestAnalyte(String idString) {
		TestAnalyte ta = null;
		try {
			ta = (TestAnalyte) sessionFactory.getCurrentSession().get(TestAnalyte.class, idString);
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestAnalyteDAOImpl", "readTestAnalyte()", e.toString());
			throw new LIMSRuntimeException("Error in TestAnalyte readTestAnalyte()", e);
		}

		return ta;

	}

	// this is for autocomplete
	// TODO: need to convert to hibernate ( not in use??? )
	@Override
	public List getTestAnalytes(String filter) throws LIMSRuntimeException {

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
		 * System.out.println("This is query " + query.getSQLString()); List
		 * testAnalytes = (Vector) aSession.executeQuery(query);
		 * 
		 * System.out.println("This is size of list retrieved " + testAnalytes.size() +
		 * " " + testAnalytes.get(0)); return testAnalytes;
		 * 
		 * } catch (Exception e) { throw new LIMSRuntimeException(
		 * "Error in TestAnalyte getTestAnalytes(String filter)", e); }
		 */
	}

	@Override
	public List getNextTestAnalyteRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "TestAnalyte", TestAnalyte.class);

	}

	@Override
	public List getPreviousTestAnalyteRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "TestAnalyte", TestAnalyte.class);
	}

	@Override
	public TestAnalyte getTestAnalyteById(TestAnalyte testAnalyte) throws LIMSRuntimeException {
		TestAnalyte newTestAnalyte;
		try {
			newTestAnalyte = (TestAnalyte) sessionFactory.getCurrentSession().get(TestAnalyte.class, testAnalyte.getId());
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestAnalyteDAOImpl", "getTestAnalyteById()", e.toString());
			throw new LIMSRuntimeException("Error in TestAnalyte getTestAnalyteById()", e);
		}

		return newTestAnalyte;

	}

	@Override
	public List getAllTestAnalytesPerTest(Test test) throws LIMSRuntimeException {
		List list;

		if (test == null || StringUtil.isNullorNill(test.getId())) {
			return new ArrayList();
		}

		try {
			String sql = "from TestAnalyte t where t.test = :testId order by t.sortOrder asc";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setInteger("testId", Integer.parseInt(test.getId()));

			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("TestAnalyteDAOImpl", "getAllTestAnalytesPerTest()", e.toString());
			throw new LIMSRuntimeException("Error in TestAnalyte getAllTestAnalytesPerTest()", e);
		}

		return list;

	}

}