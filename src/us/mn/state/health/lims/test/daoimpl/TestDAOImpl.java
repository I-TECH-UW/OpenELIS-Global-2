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
package us.mn.state.health.lims.test.daoimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;

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
import us.mn.state.health.lims.login.dao.UserModuleDAO;
import us.mn.state.health.lims.login.daoimpl.UserModuleDAOImpl;
import us.mn.state.health.lims.login.valueholder.UserSessionData;
import us.mn.state.health.lims.method.valueholder.Method;
import us.mn.state.health.lims.systemusersection.dao.SystemUserSectionDAO;
import us.mn.state.health.lims.systemusersection.daoimpl.SystemUserSectionDAOImpl;
import us.mn.state.health.lims.systemusersection.valueholder.SystemUserSection;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testanalyte.dao.TestAnalyteDAO;
import us.mn.state.health.lims.testanalyte.daoimpl.TestAnalyteDAOImpl;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;

/**
 * @author diane benz
 */
public class TestDAOImpl extends BaseDAOImpl implements TestDAO{

	@Override
	public void deleteData(List tests) throws LIMSRuntimeException{
		// add to audit trail
		try{
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for(int i = 0; i < tests.size(); i++){
				Test data = (Test)tests.get(i);

				Test oldData = readTest(data.getId());
				Test newData = new Test();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "TEST";
				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
			}
		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("TestDAOImpl", "AuditTrail deleteData()", e.toString());
			throw new LIMSRuntimeException("Error in Test AuditTrail deleteData()", e);
		}

		try{
			for(int i = 0; i < tests.size(); i++){
				Test data = (Test)tests.get(i);
				Test cloneData = readTest(data.getId());

				cloneData.setIsActive(IActionConstants.NO);
				HibernateUtil.getSession().merge(cloneData);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
				HibernateUtil.getSession().evict(cloneData);
				HibernateUtil.getSession().refresh(cloneData);
			}
		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("TestDAOImpl", "deleteData()", e.toString());
			throw new LIMSRuntimeException("Error in Test deleteData()", e);
		}
	}

	@Override
	public boolean insertData(Test test) throws LIMSRuntimeException{

		try{
			if(test.getIsActive().equals(IActionConstants.YES) && duplicateTestExists(test)){
				throw new LIMSDuplicateRecordException("Duplicate record exists for " + test.getDescription());
			}
			String id = (String)HibernateUtil.getSession().save(test);
			test.setId(id);

			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = test.getSysUserId();
			String tableName = "TEST";
			auditDAO.saveNewHistory(test, sysUserId, tableName);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("TestDAOImpl", "insertData()", e.toString());
			throw new LIMSRuntimeException("Error in Test insertData()", e);
		}

		return true;
	}

	@Override
	public void updateData(Test test) throws LIMSRuntimeException{

		try{
			if(test.getIsActive().equals(IActionConstants.YES) && duplicateTestExists(test)){
				throw new LIMSDuplicateRecordException("Duplicate record exists for " + TestService.getUserLocalizedTestName( test ));
			}
		}catch(Exception e){
			LogEvent.logError("TestDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in Test updateData()", e);
		}
		Test oldData = readTest(test.getId());

		try{
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = test.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "TEST";
			auditDAO.saveHistory(test, oldData, sysUserId, event, tableName);
		}catch(Exception e){
			LogEvent.logError("TestDAOImpl", "AuditTrail updateData()", e.toString());
			throw new LIMSRuntimeException("Error in Test AuditTrail updateData()", e);
		}

		try{
			HibernateUtil.getSession().merge(test);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(test);
			HibernateUtil.getSession().refresh(test);
		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("TestDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in Test updateData()", e);
		}
	}

	@Override
	public void getData(Test test) throws LIMSRuntimeException{
		try{
			Test testClone = (Test)HibernateUtil.getSession().get(Test.class, test.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if(testClone != null){
				PropertyUtils.copyProperties(test, testClone);
			}else{
				test.setId(null);
			}
		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("TestDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in Test getData()", e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Test> getAllTests(boolean onlyTestsFullySetup) throws LIMSRuntimeException{
		List<Test> list = new Vector<Test>();
		try{
			String sql = "from Test Order by description";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			list = filterOnlyFullSetup(onlyTestsFullySetup, list);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		}catch(Exception e){
			handleException(e, "getAllTests()");
		}

		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Test> getAllActiveTests(boolean onlyTestsFullySetup) throws LIMSRuntimeException{
		List<Test> list = new Vector<Test>();
		try{
			String sql = "from Test WHERE is_Active = 'Y' Order by description";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			list = filterOnlyFullSetup(onlyTestsFullySetup, list);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		}catch(Exception e){
			handleException(e, "getAllActiveTests()");
		}

		return list;
	}

	private List<Test> filterOnlyFullSetup(boolean onlyTestsFullySetup, List<Test> list){
		if(onlyTestsFullySetup && list != null && list.size() > 0){
			Iterator<Test> testIterator = list.iterator();
			list = new Vector<Test>();
			while(testIterator.hasNext()){
				Test test = testIterator.next();
				if(isTestFullySetup(test)){
					list.add(test);
				}
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> getAllActiveOrderableTests() throws LIMSRuntimeException{
		try{
			String sql = "from Test t WHERE t.isActive = 'Y'  and t.orderable = true Order by t.description";
			Query query = HibernateUtil.getSession().createQuery(sql);
			List<Test> list = query.list();

			closeSession();
			return list;
		}catch(Exception e){
			handleException(e, "getAllActiveOrderableTests()");
		}

		return null;
	}
	
	/**
	 * Get all the tests assigned to this user
	 * 
	 * @param sysUserId
	 *            the user system id
	 * @return list of test section bugzilla 2291 added onlyTestsFullySetup
	 */
	@Override
	public List getAllTestsBySysUserId(int sysUserId, boolean onlyTestsFullySetup) throws LIMSRuntimeException{
		List list = new Vector();
		String sectionIdList = "";
		String sql;

		try{
			SystemUserSectionDAO systemUserSectionDao = new SystemUserSectionDAOImpl();
			List userTestSectionList = systemUserSectionDao.getAllSystemUserSectionsBySystemUserId(sysUserId);
			for(int i = 0; i < userTestSectionList.size(); i++){
				SystemUserSection sus = (SystemUserSection)userTestSectionList.get(i);
				sectionIdList += sus.getTestSection().getId() + ",";
			}

			if(!(sectionIdList.equals("")) && (sectionIdList.length() > 0)){
				sectionIdList = sectionIdList.substring(0, sectionIdList.length() - 1);
				sql = "from Test t where t.testSection.id  in (" + sectionIdList + ") Order by description";
			}else{
				return list;
			}

			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();

			list = filterOnlyFullSetup(onlyTestsFullySetup, list);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("TestDAOImpl", "getAllTestsBySysUserId()", e.toString());
			throw new LIMSRuntimeException("Error in Test getAllTestsBySysUserId()", e);
		}
		return list;
	}

	@Override
	public List getPageOfTests(int startingRecNo) throws LIMSRuntimeException{
		List list;
		try{
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			// bugzilla 1399
			String sql = "from Test t order by t.testSection.testSectionName, t.testName";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("TestDAOImpl", "getPageOfTests()", e.toString());
			throw new LIMSRuntimeException("Error in Test getPageOfTests()", e);
		}

		return list;
	}

	// bugzilla 2371
	@Override
	public List getPageOfSearchedTests(int startingRecNo, String searchString) throws LIMSRuntimeException{
		List list;
		String wildCard = "*";
		String newSearchStr;
		String sql;

		try{
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
			int wCdPosition = searchString.indexOf(wildCard);

			if(wCdPosition == -1) // no wild card looking for exact match
			{
				newSearchStr = searchString.toLowerCase().trim();
				sql = "from Test t  where trim(lower (t.description)) = :param  order by t.testSection.testSectionName, t.testName";
			}else{
				newSearchStr = searchString.replace(wildCard, "%").toLowerCase().trim();
				sql = "from Test t where trim(lower (t.description)) like :param  order by t.testSection.testSectionName, t.testName";
			}
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", newSearchStr);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		}catch(Exception e){
			e.printStackTrace();
			throw new LIMSRuntimeException("Error in Test getPageOfSearchedTests()", e);
		}

		return list;
	}

	// end bugzilla 2371

	/**
	 * Get all the tests assigned to this user
	 * 
	 * @param startingRecNo
	 *            the start record
	 * @param sysUserId
	 *            is the user system id
	 * @return list of test section
	 */
	@Override
	public List getPageOfTestsBySysUserId(int startingRecNo, int sysUserId) throws LIMSRuntimeException{
		List list = new Vector();
		try{
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			String sectionIdList = "";
			String sql;

			SystemUserSectionDAO systemUserSectionDao = new SystemUserSectionDAOImpl();
			List userTestSectionList = systemUserSectionDao.getAllSystemUserSectionsBySystemUserId(sysUserId);
			for(int i = 0; i < userTestSectionList.size(); i++){
				SystemUserSection sus = (SystemUserSection)userTestSectionList.get(i);
				sectionIdList += sus.getTestSection().getId() + ",";
			}

			if(!(sectionIdList.equals("")) && (sectionIdList.length() > 0)){
				sectionIdList = sectionIdList.substring(0, sectionIdList.length() - 1);
				sql = "from Test t where t.testSection.id in (" + sectionIdList + ") order by t.testSection.testSectionName, t.testName";
			}else{
				return list;
			}

			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("TestDAOImpl", "getPageOfTestsBySysUserId()", e.toString());
			throw new LIMSRuntimeException("Error in Test getPageOfTestsBySysUserId()", e);
		}

		return list;
	}

	/**
	 * Get all the tests assigned to this user
	 * 
	 * @param startingRecNo
	 *            the start record
	 * @param sysUserId
	 *            is the user system id
	 * @return list of test section
	 */
	@Override
	public List<Test> getPageOfSearchedTestsBySysUserId(int startingRecNo, int sysUserId, String searchString) throws LIMSRuntimeException{
		String wildCard = "*";
		String newSearchStr;
		String sql;

		try{
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			String sectionIdList = "";
			SystemUserSectionDAO systemUserSectionDao = new SystemUserSectionDAOImpl();

			@SuppressWarnings("unchecked")
			List<SystemUserSection> userTestSectionList = systemUserSectionDao.getAllSystemUserSectionsBySystemUserId(sysUserId);

			for(int i = 0; i < userTestSectionList.size(); i++){
				sectionIdList += userTestSectionList.get(i).getTestSection().getId() + ",";
			}

			if(!(sectionIdList.equals("")) && (sectionIdList.length() > 0)){
				sectionIdList = sectionIdList.substring(0, sectionIdList.length() - 1);
				int wCdPosition = searchString.indexOf(wildCard);

				if(wCdPosition == -1) // no wild card looking for exact match
				{
					newSearchStr = searchString.toLowerCase().trim();
					sql = "from Test t  where t.testSection.id in (" + sectionIdList
							+ " ) and  trim(lower (t.description)) = :param  order by t.testSection.testSectionName, t.testName";
				}else{
					newSearchStr = searchString.replace(wildCard, "%").toLowerCase().trim();
					sql = "from Test t where t.testSection.id in (" + sectionIdList
							+ ") and trim(lower (t.description)) like :param  order by t.testSection.testSectionName, t.testName";
				}
			}else
				return new ArrayList<Test>();

			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", newSearchStr);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);

			@SuppressWarnings("unchecked")
			List<Test> list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			throw new LIMSRuntimeException("Error in Test getPageOfTestsBySysUserId()", e);
		}

	}

	public Test readTest(String idString){
		Test test;
		try{
			test = (Test)HibernateUtil.getSession().get(Test.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("TestDAOImpl", "readTest()", e.toString());
			throw new LIMSRuntimeException("Error in Test readTest()", e);
		}

		return test;
	}

	@Override
	public List getNextTestRecord(String id) throws LIMSRuntimeException{

		return getNextRecord(id, "Test", Test.class);

	}

	@Override
	public List getPreviousTestRecord(String id) throws LIMSRuntimeException{

		return getPreviousRecord(id, "Test", Test.class);
	}

	// this is for autocomplete
	// bugzilla 2291 added onlyTestsFullySetup
	@Override
	public List getTests(String filter, boolean onlyTestsFullySetup) throws LIMSRuntimeException{
		List list;
		try{
			String sql = "from Test t where upper(t.testName) like upper(:param) and t.isActive='Y' order by upper(t.testName)";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", filter + "%");
			list = query.list();

			list = filterOnlyFullSetup(onlyTestsFullySetup, list);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		}catch(Exception e){
			LogEvent.logError("TestDAOImpl", "getTests()", e.toString());
			throw new LIMSRuntimeException("Error in Test getTests(String filter)", e);
		}

		return list;
	}

	@Override
	public Test getTestByName(Test test) throws LIMSRuntimeException{
       return getTestByName( test.getTestName() );
	}

    @Override
	public Test getTestByName(String testName) throws LIMSRuntimeException{
        try{
            String sql = "from Test t where t.testName = :testName";
            org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
            query.setParameter("testName", testName);

            @SuppressWarnings("unchecked")
            List<Test> list = query.list();
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();
            Test t = null;

            if(!list.isEmpty()){
                t = list.get(0);
            }

            return t;

        }catch(Exception e){
            LogEvent.logError("TestDAOImpl", "getTestByName()", e.toString());
            throw new LIMSRuntimeException("Error in Test getTestByName()", e);
        }
    }
	@Override
	public Test getTestByUserLocalizedName(String testName) throws LIMSRuntimeException{
		try{
			String sql = "from Test t where (t.localizedTestName.english = :testName or t.localizedTestName.french = :testName) and t.isActive='Y'";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("testName", testName);

			@SuppressWarnings("unchecked")
			List<Test> list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			Test t = null;

			if(!list.isEmpty()){
				t = list.get(0);
			}

			return t;

		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("TestDAOImpl", "getTestByName()", e.toString());
			throw new LIMSRuntimeException("Error in Test getTestByName()", e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Test> getActiveTestByName(String testName) throws LIMSRuntimeException{
		try{
			String sql = "from Test t where t.testName = :testName and t.isActive='Y'";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("testName", testName);

			List<Test> list = query.list();
			closeSession();
			return list;
		}catch(HibernateException e){
			handleException(e, "getActiveTestByName");
		}

		return new ArrayList<Test>();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Test getActiveTestById(Integer testId) throws LIMSRuntimeException{
		List<Test> list = null;

		try{
			String sql = "from Test t where t.id = :testId and t.isActive='Y'";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("testId", testId);

			list = query.list();

			closeSession();
		}catch(HibernateException e){
			handleException(e, "getActiveTestById");
		}

		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public Test getTestById(Test test) throws LIMSRuntimeException{
		Test returnTest;
		try{
			returnTest = (Test)HibernateUtil.getSession().get(Test.class, test.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("TestDAOImpl", "getTestById()", e.toString());
			throw new LIMSRuntimeException("Error in Test getTestById()", e);
		}

		return returnTest;
	}

	// this is for selectdropdown
	@Override
	public List getMethodsByTestSection(String filter) throws LIMSRuntimeException{
		try{
			String sql = "from Test t where t.testSection = :param";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", filter);

			List list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			List methods = new ArrayList();

			for(int i = 0; i < list.size(); i++){
				Test t = (Test)list.get(i);
				/*
				 * System.out.println("This is test " + t.getId() + " " +
				 * t.getTestName());
				 */
				Method method = t.getMethod();
				if(!methods.contains(method)){
					methods.add(method);
				}
				/*
				 * System.out.println("Adding this method to list " +
				 * method.getId() + " " + method.getMethodName());
				 */
			}

			return methods;

		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("TestDAOImpl", "getMethodsByTestSection()", e.toString());
			throw new LIMSRuntimeException("Error in Method getMethodsByTestSection(String filter)", e);
		}
	}

	// this is for selectdropdown
	@Override
	public List getTestsByTestSection(String filter) throws LIMSRuntimeException{
		try{
			String sql = "from Test t where t.testSection = :param";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("param", Integer.parseInt(filter));

			List list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			return list;

		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("TestDAOImpl", "getTestsByTestSection()", e.toString());
			throw new LIMSRuntimeException("Error in Method getTestsByTestSection(String filter)", e);
		}
	}

    @Override
	public List<Test> getTestsByTestSectionId(String id) throws LIMSRuntimeException{
        try{
            String sql = "from Test t where t.testSection.id = :id";
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setInteger("id", Integer.parseInt(id));

            List list = query.list();
            closeSession();
            return list;

        }catch(Exception e){
            handleException(e,"getTestsByTestSectionId");
        }

        return null;
    }

	// this is for selectdropdown
	@Override
	public List getTestsByMethod(String filter) throws LIMSRuntimeException{
		try{
			String sql = "from Test t where t.method = :param";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", filter);

			List list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			return list;

		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("TestDAOImpl", "getTestsByMethod()", e.toString());
			throw new LIMSRuntimeException("Error in Method getTestsByMethod(String filter)", e);
		}
	}

	// this is for selectdropdown
	@Override
	public List getTestsByTestSectionAndMethod(String filter, String filter2) throws LIMSRuntimeException{
		try{
			String sql = "from Test t where t.testSection = :param1 and t.method = :param2";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param1", filter);
			query.setParameter("param2", filter2);

			List list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			return list;

		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("TestDAOImpl", "getTestsByMethod()", e.toString());
			throw new LIMSRuntimeException("Error in Method getTestsByMethod(String filter)", e);
		}
	}

	// bugzilla 1411
	@Override
	public Integer getTotalTestCount() throws LIMSRuntimeException{
		return getTotalCount("Test", Test.class);
	}

	// bugzilla 2371
	@Override
	public Integer getTotalSearchedTestCount(String searchString) throws LIMSRuntimeException{
		String wildCard = "*";
		String newSearchStr;
		String sql;
		Integer count = null;

		try{
			int wCdPosition = searchString.indexOf(wildCard);
			if(wCdPosition == -1) // no wild card looking for exact match
			{
				newSearchStr = searchString.toLowerCase().trim();
				sql = "select count (*) from Test t  where trim(lower (t.description)) = :param  ";
			}else{
				newSearchStr = searchString.replace(wildCard, "%").toLowerCase().trim();
				sql = "select count (*) from Test t where trim(lower (t.description)) like :param ";
			}
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", newSearchStr);

			List results = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			if(results != null && results.get(0) != null){
				if(results.get(0) != null){
					count = (Integer)results.get(0);
				}
			}

		}catch(Exception e){
			e.printStackTrace();
			throw new LIMSRuntimeException("Error in TestDaoImpl getTotalSearchedTestCount()", e);
		}

		return count;

	}

	// end bugzilla 2371

	// bugzilla 2371
	@Override
	public Integer getTotalSearchedTestCountBySysUserId(int sysUserId, String searchString) throws LIMSRuntimeException{
		String wildCard = "*";
		String newSearchStr;
		String sql;
		Integer count = null;

		try{
			String sectionIdList = "";
			SystemUserSectionDAO systemUserSectionDao = new SystemUserSectionDAOImpl();

			List userTestSectionList = systemUserSectionDao.getAllSystemUserSectionsBySystemUserId(sysUserId);

			for(int i = 0; i < userTestSectionList.size(); i++){
				SystemUserSection sus = (SystemUserSection)userTestSectionList.get(i);
				sectionIdList += sus.getTestSection().getId() + ",";
			}

			if(!(sectionIdList.equals("")) && (sectionIdList.length() > 0)){
				sectionIdList = sectionIdList.substring(0, sectionIdList.length() - 1);
				int wCdPosition = searchString.indexOf(wildCard);

				if(wCdPosition == -1) // no wild card looking for exact match
				{
					newSearchStr = searchString.toLowerCase().trim();
					sql = "select count (*) from Test t  where t.testSection.id in (" + sectionIdList + ") and trim(lower (t.description)) = :param ";
				}else{
					newSearchStr = searchString.replace(wildCard, "%").toLowerCase().trim();
					sql = "select count (*) from Test t where t.testSection.id in (" + sectionIdList
							+ ") and trim(lower (t.description)) like :param ";
				}
			}else
				return count;

			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", newSearchStr);

			List results = query.list();

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			if(results != null && results.get(0) != null){
				if(results.get(0) != null){
					count = (Integer)results.get(0);
				}
			}

		}catch(Exception e){
			e.printStackTrace();
			throw new LIMSRuntimeException("Error in TestDaoImpl getTotalSearchedTestCountBySysUserId()", e);
		}

		return count;
	}

	// end bugzilla 2371

	// bugzilla 2371
	@Override
	public Integer getAllSearchedTotalTestCount(HttpServletRequest request, String searchString) throws LIMSRuntimeException{
		Integer count;
		TestDAO testDAO = new TestDAOImpl();

		try{
			if(SystemConfiguration.getInstance().getEnableUserTestSection().equals(NO)){
				count = testDAO.getTotalSearchedTestCount(searchString);
			}else{
				UserSessionData usd = (UserSessionData)request.getSession().getAttribute(USER_SESSION_DATA);

				UserModuleDAO userModuleDAO = new UserModuleDAOImpl();
				if(!userModuleDAO.isUserAdmin(request)){
					count = testDAO.getTotalSearchedTestCountBySysUserId(usd.getSystemUserId(), searchString);
				}else{
					count = testDAO.getTotalSearchedTestCount(searchString);

				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new LIMSRuntimeException("Error in testDAOImpl getAllSearchedTotalTestCount()", e);
		}
		return count;
	}

	// end if bugzilla 2371

	// bugzilla 1427
	@Override
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException{
		int currentId = Integer.valueOf( id );
		String tablePrefix = getTablePrefix(table);

		List list;
		// bugzilla 1908
		int rrn;
		try{
			// bugzilla 1908 cannot use named query for postgres because of
			// oracle ROWNUM
			// instead get the list in this sortorder and determine the index of
			// record with id = currentId
			String sql = "select t.id from Test t " + " order by t.testSection.testSectionName, t.testName";

			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			rrn = list.indexOf(String.valueOf(currentId));

			list = HibernateUtil.getSession().getNamedQuery(tablePrefix + "getNext").setFirstResult(rrn + 1).setMaxResults(2).list();

		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("TestDAOImpl", "getNextRecord()", e.toString());
			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
		}

		return list;
	}

	// bugzilla 1427
	@Override
	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException{
		int currentId = Integer.valueOf( id );
		String tablePrefix = getTablePrefix(table);

		List list;
		// bugzilla 1908
		int rrn;
		try{
			// bugzilla 1908 cannot use named query for postgres because of
			// oracle ROWNUM
			// instead get the list in this sortorder and determine the index of
			// record with id = currentId
			String sql = "select t.id from Test t " + " order by t.testSection.testSectionName desc, t.testName desc";

			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			rrn = list.indexOf(String.valueOf(currentId));

			list = HibernateUtil.getSession().getNamedQuery(tablePrefix + "getPrevious").setFirstResult(rrn + 1).setMaxResults(2).list();

		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("TestDAOImpl", "getPreviousRecord()", e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		}

		return list;
	}

	private boolean duplicateTestExists(Test test) throws LIMSRuntimeException{
		try{

			List list = new ArrayList();

			if(test.getIsActive().equalsIgnoreCase("Y")){
				// not case sensitive hemolysis and Hemolysis are considered
				// duplicates
				String sql = "from Test t where (trim(lower(t.description)) = :description and t.isActive='Y' and t.id != :testId)";
				org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);

				// initialize with 0 (for new records where no id has been
				// generated yet
				String testId = "0";
				if(!StringUtil.isNullorNill(test.getId())){
					testId = test.getId();
				}
				query.setInteger("testId", Integer.parseInt(testId));
				query.setParameter("description", test.getDescription().toLowerCase().trim());

				list = query.list();
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();

			}

            return !list.isEmpty();

		}catch(Exception e){
			LogEvent.logError("TestDAOImpl", "duplicateTestExists()", e.toString());
			throw new LIMSRuntimeException("Error in duplicateTestExists()", e);
		}
	}

	// bugzilla 2236
	@Override
	public boolean isTestFullySetup(Test test) throws LIMSRuntimeException{
		try{
			TestAnalyteDAO testAnalyteDAO = new TestAnalyteDAOImpl();
			List testAnalytesByTest = testAnalyteDAO.getAllTestAnalytesPerTest(test);
			boolean result = true;
			if(testAnalytesByTest == null || testAnalytesByTest.size() == 0){
				result = false;
			}else{
				// bugzilla 2291 make sure none of the components has a null
				// result group
				boolean atLeastOneResultGroupFound = false;
				for(int j = 0; j < testAnalytesByTest.size(); j++){
					TestAnalyte testAnalyte = (TestAnalyte)testAnalytesByTest.get(j);
					if(testAnalyte.getResultGroup() == null){
						atLeastOneResultGroupFound = true;
						break;
					}
				}
				if(atLeastOneResultGroupFound){
					result = false;
				}
			}
			return result;
		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("TestDAOImpl", "isTestFullySetup()", e.toString());
			throw new LIMSRuntimeException("Error in isTestFullySetup()", e);
		}
	}

	// bugzilla 2443
	@Override
	public Integer getNextAvailableSortOrderByTestSection(Test test) throws LIMSRuntimeException{
		Integer result = null;

		try{

			List list;
			Test testWithHighestSortOrder;

			String sql = "from Test t where t.testSection = :param and t.sortOrder is not null order by t.sortOrder desc";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", test.getTestSection());

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			if(list.size() > 0){
				testWithHighestSortOrder = (Test)list.get(0);
				if(testWithHighestSortOrder != null && !StringUtil.isNullorNill(testWithHighestSortOrder.getSortOrder())){
					result = ( Integer.parseInt( testWithHighestSortOrder.getSortOrder() ) + 1 );
				}
			}

		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("TestDAOImpl", "getNextAvailableSortOrderByTestSection()", e.toString());
			throw new LIMSRuntimeException("Error in getNextAvailableSortOrderByTestSection()", e);
		}
		return result;
	}

	/**
	 * @see us.mn.state.health.lims.test.dao.TestDAO#getAllOrderBy(java.lang.String)
	 *      Read all entities from the database sorted by an appropriate
	 *      property
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Test> getAllOrderBy(String columnName) throws LIMSRuntimeException{
		List<Test> entities;
		try{
			if(!StringUtil.isJavaIdentifier(columnName)){
				throw new IllegalArgumentException("\"" + columnName + "\" is not valid syntax for a column name");
			}
			// I didn't manage to get a query parameter to be used as a column
			// name to sort by (because ORDER BY "my_column" is not valid SQL).
			// so I had to generate the HQL manually, but only after the above
			// check.
			String hql = "from Test t where t.isActive='Y' ORDER BY " + columnName;
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(hql);
			entities = query.list();
			closeSession();
		}catch(Exception e){
			LogEvent.logError("TestDAOImpl", "getAllOrderBy()", e.toString());
			throw new LIMSRuntimeException("Error in getAllOrderBy()", e);
		}

		return entities;
	}

	@Override
	public Test getTestById(String testId) throws LIMSRuntimeException{
		String sql = "From Test t where t.id = :id";
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("id", Integer.parseInt(testId));

			Test test = (Test)query.uniqueResult();
			closeSession();
			return test;
		}catch(HibernateException e){
			handleException(e, "getTestById");
		}

		return null;
	}

	@Override
	public Test getTestByDescription(String description){
		String sql = "From Test t where t.description = :description";
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("description", description);

			Test test = (Test)query.uniqueResult();
			closeSession();
			return test;
		}catch(HibernateException e){
			handleException(e, "getTestByDescription");
		}

		return null;
	}

    @Override
    public Test getTestByGUID( String guid){
        String sql = "From Test t where t.guid = :guid";
        try{
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setString("guid", guid);

            Test test = (Test)query.uniqueResult();
            closeSession();
            return test;
        }catch(HibernateException e){
            handleException(e, "getTestByGUID");
        }

        return null;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<Test> getTestsByLoincCode(String loincCode) {
    	String sql = "From Test t where t.loinc = :loinc";
    	try {
    		Query query = HibernateUtil.getSession().createQuery(sql);
    		query.setString("loinc", loincCode);
    		List<Test> tests = query.list();
    		closeSession();
    		return tests;
    	} catch (HibernateException e) {
    		handleException(e, "getTestByLoincCode");
    	}
    	
    	return null;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> getActiveTestsByLoinc(String loincCode) {
    	String sql = "From Test t where t.loinc = :loinc and t.isActive='Y'";
    	try {
    		Query query = HibernateUtil.getSession().createQuery(sql);
    		query.setString("loinc", loincCode);
    		List<Test> tests = query.list();
    		closeSession();
    		return tests;
    	} catch (HibernateException e) {
    		handleException(e, "getActiveTestByLoinc");
    	}
    	
    	return null;
	}
}