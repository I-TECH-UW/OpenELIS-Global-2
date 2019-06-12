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
package us.mn.state.health.lims.typeofsample.daoimpl;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleTestDAO;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSampleTest;

@Component
@Transactional
public class TypeOfSampleTestDAOImpl extends BaseDAOImpl<TypeOfSampleTest, String> implements TypeOfSampleTestDAO {

	public TypeOfSampleTestDAOImpl() {
		super(TypeOfSampleTest.class);
	}

//	@Override
//	public void deleteData(String[] typeOfSamplesTestIDs, String currentUserId) throws LIMSRuntimeException {
//
//		try {
//
//			for (String id : typeOfSamplesTestIDs) {
//				TypeOfSampleTest data = readTypeOfSample(id);
//
//				auditDAO.saveHistory(new TypeOfSampleTest(), data, currentUserId, IActionConstants.AUDIT_TRAIL_DELETE,
//						"SAMPLETYPE_TEST");
//				sessionFactory.getCurrentSession().delete(data);
//				// sessionFactory.getCurrentSession().flush(); // CSL remove old
//				// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			}
//
//		} catch (Exception e) {
//
//			LogEvent.logError("TypeOfSampleDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in TypeOfSampleTest deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(TypeOfSampleTest typeOfSampleTest) throws LIMSRuntimeException {
//
//		try {
//
//			String id = (String) sessionFactory.getCurrentSession().save(typeOfSampleTest);
//			typeOfSampleTest.setId(id);
//
//			auditDAO.saveNewHistory(typeOfSampleTest, typeOfSampleTest.getSysUserId(), "SAMPLETYPE_TEST");
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//		} catch (Exception e) {
//			LogEvent.logError("TypeOfSampleTestDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in TypeOfSampleTest insertData()", e);
//		}
//
//		return true;
//	}

	@Override
	public void getData(TypeOfSampleTest typeOfSample) throws LIMSRuntimeException {
		try {
			TypeOfSampleTest tos = sessionFactory.getCurrentSession().get(TypeOfSampleTest.class, typeOfSample.getId());
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
			if (tos != null) {
				PropertyUtils.copyProperties(typeOfSample, tos);
			} else {
				typeOfSample.setId(null);
			}
		} catch (Exception e) {

			LogEvent.logError("TypeOfSampleDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in TypeOfSampleTest getData()", e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TypeOfSampleTest> getAllTypeOfSampleTests() throws LIMSRuntimeException {

		List<TypeOfSampleTest> list;

		try {
			String sql = "from TypeOfSampleTest";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			// query.setMaxResults(10);
			// query.setFirstResult(3);
			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {

			LogEvent.logError("TypeOfSampleDAOImpl", "getAllTypeOfSamples()", e.toString());
			throw new LIMSRuntimeException("Error in TypeOfSampleTest getAllTypeOfSamples()", e);
		}

		return list;
	}

	@Override
	public List getPageOfTypeOfSampleTests(int startingRecNo) throws LIMSRuntimeException {
		List list;
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + DEFAULT_PAGE_SIZE + 1;

			String sql = "from TypeOfSampleTest t order by t.typeOfSampleId, t.testId";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);
			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("TypeOfSampleDAOImpl", "getPageOfTypeOfSamples()", e.toString());
			throw new LIMSRuntimeException("Error in TypeOfSampleTest getPageOfTypeOfSamples()", e);
		}

		return list;
	}

	public TypeOfSampleTest readTypeOfSample(String idString) {
		TypeOfSampleTest tos;
		try {
			tos = sessionFactory.getCurrentSession().get(TypeOfSampleTest.class, idString);
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {

			LogEvent.logError("TypeOfSampleDAOImpl", "readTypeOfSample()", e.toString());
			throw new LIMSRuntimeException("Error in TypeOfSampleTest readTypeOfSample()", e);
		}

		return tos;
	}

	@Override
	public List getNextTypeOfSampleTestRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "TypeOfSampleTest", TypeOfSampleTest.class);

	}

	@Override
	public List getPreviousTypeOfSampleRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "TypeOfSampleTest", TypeOfSampleTest.class);
	}

	@Override
	public Integer getTotalTypeOfSampleTestCount() throws LIMSRuntimeException {
		return getTotalCount("TypeOfSampleTest", TypeOfSampleTest.class);
	}

	@Override
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
		int currentId = Integer.valueOf(id);
		String tablePrefix = getTablePrefix(table);

		List list;

		int rrn;
		try {

			// oracle ROWNUM
			// instead get the list in this sortorder and determine the index of
			// record with id = currentId
			String sql = "select tos.id from TypeOfSampleTest tos " + " order by tos.domain, tos.description";

			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
			rrn = list.indexOf(String.valueOf(currentId));

			list = sessionFactory.getCurrentSession().getNamedQuery(tablePrefix + "getNext").setFirstResult(rrn + 1)
					.setMaxResults(2).list();

		} catch (Exception e) {

			LogEvent.logError("TypeOfSampleDAOImpl", "getNextRecord()", e.toString());
			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
		}

		return list;
	}

	@Override
	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
		int currentId = Integer.valueOf(id);
		String tablePrefix = getTablePrefix(table);

		List list;

		int rrn;
		try {
			String sql = "select tos.id from TypeOfSampleTest tos " + " order by tos.domain desc, tos.description desc";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
			rrn = list.indexOf(String.valueOf(currentId));

			list = sessionFactory.getCurrentSession().getNamedQuery(tablePrefix + "getPrevious").setFirstResult(rrn + 1)
					.setMaxResults(2).list();

		} catch (Exception e) {

			LogEvent.logError("TypeOfSampleDAOImpl", "getPreviousRecord()", e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		}

		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TypeOfSampleTest> getTypeOfSampleTestsForSampleType(String sampleTypeId) throws LIMSRuntimeException {
		String sql = "from TypeOfSampleTest tt where tt.typeOfSampleId = :sampleId";

		try {
			if (sampleTypeId.equals("null")) {
				// so parseInt doesn't throw
				sampleTypeId = "0";
			}
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setInteger("sampleId", Integer.parseInt(sampleTypeId));
			List<TypeOfSampleTest> list = query.list();
			// closeSession(); // CSL remove old
			return list;
		} catch (Exception e) {
			handleException(e, "getTypeOfSampleTestsForSampleType");
		}

		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public TypeOfSampleTest getTypeOfSampleTestForTest(String testId) throws LIMSRuntimeException {

		String sql = "from TypeOfSampleTest tt where tt.testId = :testId";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setInteger("testId", Integer.parseInt(testId));
			List<TypeOfSampleTest> list = query.list();
			// closeSession(); // CSL remove old
			return list.size() > 0 ? list.get(0) : null;
		} catch (Exception e) {
			handleException(e, "getTypeOfSampleTestForTest");
		}

		return null;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TypeOfSampleTest> getTypeOfSampleTestsForTest(String testId) throws LIMSRuntimeException {
		String sql = "from TypeOfSampleTest tt where tt.testId = :testId";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setInteger("testId", Integer.parseInt(testId));
			List<TypeOfSampleTest> list = query.list();
			// closeSession(); // CSL remove old
			return list;
		} catch (Exception e) {
			handleException(e, "getTypeOfSampleTestsForTest");
		}
		return null;
	}

}