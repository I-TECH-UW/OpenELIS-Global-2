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
package us.mn.state.health.lims.testtrailer.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import  us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.testtrailer.dao.TestTrailerDAO;
import us.mn.state.health.lims.testtrailer.valueholder.TestTrailer;

/**
 * @author diane benz
 */
@Component
@Transactional
public class TestTrailerDAOImpl extends BaseDAOImpl<TestTrailer, String> implements TestTrailerDAO {

	public TestTrailerDAOImpl() {
		super(TestTrailer.class);
	}

//	@Override
//	public void deleteData(List testTrailers) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < testTrailers.size(); i++) {
//				TestTrailer data = (TestTrailer) testTrailers.get(i);
//
//				TestTrailer oldData = readTestTrailer(data.getId());
//				TestTrailer newData = new TestTrailer();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "TEST_TRAILER";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("TestTrailerDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestTrailer AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < testTrailers.size(); i++) {
//				TestTrailer data = (TestTrailer) testTrailers.get(i);
//				// bugzilla 2206
//				data = readTestTrailer(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("TestTrailerDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestTrailer deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(TestTrailer testTrailer) throws LIMSRuntimeException {
//		try {
//			// bugzilla 1482 throw Exception if record already exists
//			if (duplicateTestTrailerExists(testTrailer)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + testTrailer.getTestTrailerName());
//			}
//
//			String id = (String) entityManager.unwrap(Session.class).save(testTrailer);
//			testTrailer.setId(id);
//
//			// bugzilla 1824 inserts will be logged in history table
//
//			String sysUserId = testTrailer.getSysUserId();
//			String tableName = "TEST_TRAILER";
//			auditDAO.saveNewHistory(testTrailer, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("TestTrailerDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestTrailer insertData()", e);
//		}
//
//		return true;
//	}
//
//	@Override
//	public void updateData(TestTrailer testTrailer) throws LIMSRuntimeException {
//		// bugzilla 1482 throw Exception if record already exists
//		try {
//			if (duplicateTestTrailerExists(testTrailer)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + testTrailer.getTestTrailerName());
//			}
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("TestTrailerDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestTrailer updateData()", e);
//		}
//
//		TestTrailer oldData = readTestTrailer(testTrailer.getId());
//		TestTrailer newData = testTrailer;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = testTrailer.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "TEST_TRAILER";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("TestTrailerDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestTrailer AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(testTrailer);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(testTrailer);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(testTrailer);
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("TestTrailerDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestTrailer updateData()", e);
//		}
//	}

	@Override
	@Transactional(readOnly = true)
	public void getData(TestTrailer testTrailer) throws LIMSRuntimeException {
		try {
			TestTrailer uom = entityManager.unwrap(Session.class).get(TestTrailer.class, testTrailer.getId());
			// entityManager.unwrap(Session.class).flush(); // CSL remove old
			// entityManager.unwrap(Session.class).clear(); // CSL remove old
			if (uom != null) {
				PropertyUtils.copyProperties(testTrailer, uom);
			} else {
				testTrailer.setId(null);
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestTrailerDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in TestTrailer getData()", e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List getAllTestTrailers() throws LIMSRuntimeException {
		List list = new Vector();
		try {
			String sql = "from TestTrailer";
			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
			// query.setMaxResults(10);
			// query.setFirstResult(3);
			list = query.list();
			// entityManager.unwrap(Session.class).flush(); // CSL remove old
			// entityManager.unwrap(Session.class).clear(); // CSL remove old
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestTrailerDAOImpl", "getAllTestTrailers()", e.toString());
			throw new LIMSRuntimeException("Error in TestTrailer getAllTestTrailers()", e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List getPageOfTestTrailers(int startingRecNo) throws LIMSRuntimeException {
		List list = new Vector();
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			// bugzilla 1399
			String sql = "from TestTrailer t order by t.testTrailerName";
			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);

			list = query.list();
			// entityManager.unwrap(Session.class).flush(); // CSL remove old
			// entityManager.unwrap(Session.class).clear(); // CSL remove old
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestTrailerDAOImpl", "getPageOfTestTrailers()", e.toString());
			throw new LIMSRuntimeException("Error in TestTrailer getPageOfTestTrailers()", e);
		}

		return list;
	}

	public TestTrailer readTestTrailer(String idString) {
		TestTrailer tr = null;
		try {
			tr = entityManager.unwrap(Session.class).get(TestTrailer.class, idString);
			// entityManager.unwrap(Session.class).flush(); // CSL remove old
			// entityManager.unwrap(Session.class).clear(); // CSL remove old
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestTrailerDAOImpl", "readTestTrailer()", e.toString());
			throw new LIMSRuntimeException("Error in TestTrailer readTestTrailer()", e);
		}

		return tr;
	}

	@Override
	@Transactional(readOnly = true)
	public List getNextTestTrailerRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "TestTrailer", TestTrailer.class);

	}

	@Override
	@Transactional(readOnly = true)
	public List getPreviousTestTrailerRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "TestTrailer", TestTrailer.class);
	}

	@Override
	@Transactional(readOnly = true)
	public TestTrailer getTestTrailerByName(TestTrailer testTrailer) throws LIMSRuntimeException {
		try {
			String sql = "from TestTrailer t where t.testTrailerName = :param";
			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
			query.setParameter("param", testTrailer.getTestTrailerName());

			List list = query.list();
			// entityManager.unwrap(Session.class).flush(); // CSL remove old
			// entityManager.unwrap(Session.class).clear(); // CSL remove old
			TestTrailer t = null;
			if (list.size() > 0) {
				t = (TestTrailer) list.get(0);
			}

			return t;

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestTrailerDAOImpl", "getTestTrailerByName()", e.toString());
			throw new LIMSRuntimeException("Error in TestTrailer getTestTrailerByName()", e);
		}
	}

	// this is for autocomplete
	@Override
	@Transactional(readOnly = true)
	public List getTestTrailers(String filter) throws LIMSRuntimeException {
		List list = new Vector();
		try {
			String sql = "from TestTrailer t where upper(t.testTrailerName) like upper(:param) order by upper(t.testTrailerName)";
			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
			query.setParameter("param", filter + "%");

			list = query.list();
			// entityManager.unwrap(Session.class).flush(); // CSL remove old
			// entityManager.unwrap(Session.class).clear(); // CSL remove old
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestTrailerDAOImpl", "getTestTrailers()", e.toString());
			throw new LIMSRuntimeException("Error in TestTrailer getTestTrailers(String filter)", e);
		}
		return list;
	}

	// bugzilla 1411
	@Override
	@Transactional(readOnly = true)
	public Integer getTotalTestTrailerCount() throws LIMSRuntimeException {
		return getTotalCount("TestTrailer", TestTrailer.class);
	}

	// overriding BaseDAOImpl bugzilla 1427 pass in name not id
	@Override
	@Transactional(readOnly = true)
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {

		List list = new Vector();
		try {
			String sql = "from " + table + " t where name >= " + enquote(id) + " order by t.testTrailerName";
			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
			query.setFirstResult(1);
			query.setMaxResults(2);

			list = query.list();

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestTrailerDAOImpl", "getNextRecord()", e.toString());
			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
		}

		return list;
	}

	// overriding BaseDAOImpl bugzilla 1427 pass in name not id
	@Override
	@Transactional(readOnly = true)
	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {

		List list = new Vector();
		try {
			String sql = "from " + table + " t order by t.testTrailerName desc where name <= " + enquote(id);
			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
			query.setFirstResult(1);
			query.setMaxResults(2);

			list = query.list();
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestTrailerDAOImpl", "getPreviousRecord()", e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		}

		return list;
	}

	// bugzilla 1482
	@Override
	public boolean duplicateTestTrailerExists(TestTrailer testTrailer) throws LIMSRuntimeException {
		try {

			List list = new ArrayList();

			// not case sensitive hemolysis and Hemolysis are considered
			// duplicates
			String sql = "from TestTrailer t where trim(lower(t.testTrailerName)) = :param and t.id != :param2";
			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
			query.setParameter("param", testTrailer.getTestTrailerName().toLowerCase().trim());

			// initialize with 0 (for new records where no id has been generated
			// yet
			String testTrailerId = "0";
			if (!StringUtil.isNullorNill(testTrailer.getId())) {
				testTrailerId = testTrailer.getId();
			}
			query.setParameter("param2", testTrailerId);

			list = query.list();
			// entityManager.unwrap(Session.class).flush(); // CSL remove old
			// entityManager.unwrap(Session.class).clear(); // CSL remove old

			if (list.size() > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestTrailerDAOImpl", "duplicateTestTrailerExists()", e.toString());
			throw new LIMSRuntimeException("Error in duplicateTestTrailerExists()", e);
		}
	}
}