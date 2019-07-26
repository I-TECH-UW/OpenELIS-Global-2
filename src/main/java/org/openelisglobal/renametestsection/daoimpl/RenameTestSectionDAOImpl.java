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
package org.openelisglobal.renametestsection.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import  org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.renametestsection.dao.RenameTestSectionDAO;
import org.openelisglobal.renametestsection.valueholder.RenameTestSection;

@Component
@Transactional
public class RenameTestSectionDAOImpl extends BaseDAOImpl<RenameTestSection, String> implements RenameTestSectionDAO {

	public RenameTestSectionDAOImpl() {
		super(RenameTestSection.class);
	}

//	@Override
//	public void deleteData(List testSections) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < testSections.size(); i++) {
//				RenameTestSection data = (RenameTestSection) testSections.get(i);
//
//				RenameTestSection oldData = readTestSection(data.getId());
//				RenameTestSection newData = new RenameTestSection();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "TEST_SECTION";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("TestSectionDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestSection AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < testSections.size(); i++) {
//				RenameTestSection data = (RenameTestSection) testSections.get(i);
//				// bugzilla 2206
//				data = readTestSection(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("TestSectionDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestSection deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(RenameTestSection testSection) throws LIMSRuntimeException {
//		try {
//			// bugzilla 1482 throw Exception if record already exists
//			if (duplicateTestSectionExists(testSection)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + testSection.getTestSectionName());
//			}
//
//			String id = (String) entityManager.unwrap(Session.class).save(testSection);
//			testSection.setId(id);
//
//			// bugzilla 1824 inserts will be logged in history table
//
//			String sysUserId = testSection.getSysUserId();
//			String tableName = "TEST_SECTION";
//			auditDAO.saveNewHistory(testSection, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("TestSectionDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestSection insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(RenameTestSection testSection) throws LIMSRuntimeException {
//		// bugzilla 1482 throw Exception if record already exists
//		try {
//			if (duplicateTestSectionExists(testSection)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + testSection.getTestSectionName());
//			}
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("TestSectionDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestSection updateData()", e);
//		}
//
//		RenameTestSection oldData = readTestSection(testSection.getId());
//		RenameTestSection newData = testSection;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = testSection.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "TEST_SECTION";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("TestSectionDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestSection AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(testSection);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(testSection);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(testSection);
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("TestSectionDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in TestSection updateData()", e);
//		}
//	}

	@Override
	@Transactional(readOnly = true)
	public void getData(RenameTestSection testSection) throws LIMSRuntimeException {
		try {
			RenameTestSection uom = entityManager.unwrap(Session.class).get(RenameTestSection.class,
					testSection.getId());
			// entityManager.unwrap(Session.class).flush(); // CSL remove old
			// entityManager.unwrap(Session.class).clear(); // CSL remove old
			if (uom != null) {
				PropertyUtils.copyProperties(testSection, uom);
			} else {
				testSection.setId(null);
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestSectionDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in TestSection getData()", e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List getAllTestSections() throws LIMSRuntimeException {
		List list = new Vector();
		try {
			String sql = "from TestSection";
			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
			// query.setMaxResults(10);
			// query.setFirstResult(3);
			list = query.list();
			// entityManager.unwrap(Session.class).flush(); // CSL remove old
			// entityManager.unwrap(Session.class).clear(); // CSL remove old
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestSectionDAOImpl", "getAllTestSections()", e.toString());
			throw new LIMSRuntimeException("Error in TestSection getAllTestSections()", e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List getPageOfTestSections(int startingRecNo) throws LIMSRuntimeException {
		List list = new Vector();
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			// bugzilla 1399
			String sql = "from TestSection t order by t.testSectionName";
			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);

			list = query.list();
			// entityManager.unwrap(Session.class).flush(); // CSL remove old
			// entityManager.unwrap(Session.class).clear(); // CSL remove old
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestSectionDAOImpl", "getPageOfTestSections()", e.toString());
			throw new LIMSRuntimeException("Error in TestSection getPageOfTestSections()", e);
		}

		return list;
	}

	public RenameTestSection readTestSection(String idString) {
		RenameTestSection tr = null;
		try {
			tr = entityManager.unwrap(Session.class).get(RenameTestSection.class, idString);
			// entityManager.unwrap(Session.class).flush(); // CSL remove old
			// entityManager.unwrap(Session.class).clear(); // CSL remove old
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestSectionDAOImpl", "readTestSection()", e.toString());
			throw new LIMSRuntimeException("Error in TestSection readTestSection()", e);
		}

		return tr;
	}

	@Override
	@Transactional(readOnly = true)
	public List getNextTestSectionRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "TestSection", RenameTestSection.class);

	}

	@Override
	@Transactional(readOnly = true)
	public List getPreviousTestSectionRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "TestSection", RenameTestSection.class);
	}

	@Override
	@Transactional(readOnly = true)
	public RenameTestSection getTestSectionByName(RenameTestSection testSection) throws LIMSRuntimeException {
		try {
			String sql = "from TestSection t where t.testSectionName = :param";
			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
			query.setParameter("param", testSection.getTestSectionName());

			List list = query.list();
			// entityManager.unwrap(Session.class).flush(); // CSL remove old
			// entityManager.unwrap(Session.class).clear(); // CSL remove old
			RenameTestSection t = null;
			if (list.size() > 0) {
				t = (RenameTestSection) list.get(0);
			}

			return t;

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestSectionDAOImpl", "getTestSectionByName()", e.toString());
			throw new LIMSRuntimeException("Error in TestSection getTestSectionByName()", e);
		}
	}

	// this is for autocomplete
	@Override
	@Transactional(readOnly = true)
	public List getTestSections(String filter) throws LIMSRuntimeException {
		List list = new Vector();
		try {
			String sql = "from TestSection t where upper(t.testSectionName) like upper(:param) order by upper(t.testSectionName)";
			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
			query.setParameter("param", filter + "%");

			list = query.list();
			// entityManager.unwrap(Session.class).flush(); // CSL remove old
			// entityManager.unwrap(Session.class).clear(); // CSL remove old
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestSectionDAOImpl", "getTestSections()", e.toString());
			throw new LIMSRuntimeException("Error in TestSection getTestSections(String filter)", e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public RenameTestSection getTestSectionById(String testSectionId) throws LIMSRuntimeException {
		try {
			RenameTestSection ts = entityManager.unwrap(Session.class).get(RenameTestSection.class, testSectionId);
			// closeSession(); // CSL remove old
			return ts;
		} catch (Exception e) {
			handleException(e, "getTestSectionById");
		}

		return null;
	}

	// bugzilla 1411
	@Override
	@Transactional(readOnly = true)
	public Integer getTotalTestSectionCount() throws LIMSRuntimeException {
		return getTotalCount("TestSection", RenameTestSection.class);
	}

	// overriding BaseDAOImpl bugzilla 1427 pass in name not id
	@Override
	@Transactional(readOnly = true)
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {

		List list = new Vector();
		try {
			String sql = "from " + table + " t where name >= " + enquote(id) + " order by t.testSectionName";
			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
			query.setFirstResult(1);
			query.setMaxResults(2);

			list = query.list();

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestSectionDAOImpl", "getNextRecord()", e.toString());
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
			String sql = "from " + table + " t order by t.testSectionName desc where name <= " + enquote(id);
			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
			query.setFirstResult(1);
			query.setMaxResults(2);

			list = query.list();
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TestSectionDAOImpl", "getPreviousRecord()", e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		}

		return list;
	}

	// bugzilla 1482
	@Override
	public boolean duplicateTestSectionExists(RenameTestSection testSection) throws LIMSRuntimeException {
		try {

			List list = new ArrayList();

			// not case sensitive hemolysis and Hemolysis are considered
			// duplicates
			String sql = "from TestSection t where trim(lower(t.testSectionName)) = :param and t.id != :param2";
			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
			query.setParameter("param", testSection.getTestSectionName().toLowerCase().trim());

			// initialize with 0 (for new records where no id has been generated
			// yet
			String testSectionId = "0";
			if (!StringUtil.isNullorNill(testSection.getId())) {
				testSectionId = testSection.getId();
			}
			query.setParameter("param2", testSectionId);

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
			LogEvent.logError("TestSectionDAOImpl", "duplicateTestSectionExists()", e.toString());
			throw new LIMSRuntimeException("Error in duplicateTestSectionExists()", e);
		}
	}
}
