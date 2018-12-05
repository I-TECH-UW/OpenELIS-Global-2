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
package us.mn.state.health.lims.testanalyte.daoimpl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testanalyte.dao.TestAnalyteTestResultDAO;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

/**
 * @author diane benz
 */
/**
 * @author diane benz
 */
public class TestAnalyteTestResultDAOImpl extends BaseDAOImpl implements TestAnalyteTestResultDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see us.mn.state.health.lims.testanalyte.dao.TestAnalyteTestResultDAO#insertData(us.mn.state.health.lims.test.valueholder.Test,
	 *      java.util.List, java.util.List) TODO: test this
	 */
	public boolean insertData(Test test, List testAnalytes, List testResults) throws LIMSRuntimeException {
		Transaction tx = null;
		Session session = null;		
		
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			long maxNum = getMaximumResultGroupForTest(test);
			for (int i = 0; i < testAnalytes.size(); i++) {

				// Test Analyte
				// adjust resultGroup number depending on maximum existing rg
				// for test
				TestAnalyte ta = (TestAnalyte) testAnalytes.get(i);
				int rg = Integer.parseInt(ta.getResultGroup());
				ta.setResultGroup(String.valueOf(rg + maxNum));
				
				session.save(ta);
				session.flush();
				session.clear();
				
			}
			for (int j = 0; j < testResults.size(); j++) {
				// Test Result table
				// adjust resultGroup number depending on maximum existing rg
				// for test
				TestResult tr = (TestResult) testResults.get(j);
				int rg = Integer.parseInt(tr.getResultGroup());
				tr.setResultGroup(String.valueOf(rg + maxNum));

				session.save(tr);
				session.flush();
				session.clear();
			}

			tx.commit();

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TestAnalyteTestResultDAOImpl","insertData()",e.toString());
			if (tx!=null) tx.rollback();
			throw new LIMSRuntimeException("Error in TestAnalyteTestResult insertData()", e);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see us.mn.state.health.lims.testanalyte.dao.TestAnalyteTestResultDAO#updateData(us.mn.state.health.lims.test.valueholder.Test,
	 *      java.util.List, java.util.List, java.util.List, java.util.List)
	 */
	public void updateData(Test test, List allOldTestAnalytes,
			List allOldTestResults, List allNewTestAnalytes,
			List allNewTestResults) throws LIMSRuntimeException {

		Transaction tx = null;
		Session session = null;
		
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			// this stores all testResult Ids (test results can be shared
			// amongst components)
			Hashtable allOldTestResultsForTest = new Hashtable();
			Hashtable allNewTestResultsForTest = new Hashtable();

			// load hashtables
			if (allNewTestResults != null) {
				for (int j = 0; j < allNewTestResults.size(); j++) {
					TestResult tr = (TestResult) allNewTestResults.get(j);
					if (!StringUtil.isNullorNill(tr.getId())) {
						allNewTestResultsForTest.put(tr.getId(), tr);
					}
				}
			}

			if ( (allOldTestResults != null) && (allOldTestResults.size()>0) ) {

				for (int j = 0; j < allOldTestResults.size(); j++) {
					TestResult tr = (TestResult) allOldTestResults.get(j);
					allOldTestResultsForTest.put(tr.getId(), tr);
				}

			}

			// Get a List of allNewTrIds (all new test result Ids) from
			// Hashtable allNewTestResultsForTest
			// Note : this does not include new ones that don't have an id yet!!
			Set allNewTrIdsSet = (Set) allNewTestResultsForTest.keySet();
			Iterator allNewTrIdsIt = allNewTrIdsSet.iterator();
			List allNewTrIds = new ArrayList();

			while (allNewTrIdsIt.hasNext()) {
				allNewTrIds.add(allNewTrIdsIt.next());
			}

			// Get a List of allOldTrIds (all old test result Ids) from
			// Hashtable allOldTestResultsForTest
			Set allOldTrIdsSet = (Set) allOldTestResultsForTest.keySet();
			Iterator allOldTrIdsIt = allOldTrIdsSet.iterator();
			List allOldTrIds = new ArrayList();

			while (allOldTrIdsIt.hasNext()) {
				allOldTrIds.add(allOldTrIdsIt.next());
			}

			// Loop through all new test analytes
			for (int i = 0; i < allNewTestAnalytes.size(); i++) {
				TestAnalyte testAnalyte = (TestAnalyte) allNewTestAnalytes.get(i);
				List testResults = new ArrayList();
				List oldTestResults = new ArrayList();
				TestAnalyte testAnalyteClone;
				//bug#1342
				String analyteType;

				if (!StringUtil.isNullorNill(testAnalyte.getId())) {
					// UPDATE
					testAnalyteClone = readTestAnalyte(testAnalyte.getId());
					oldTestResults = testAnalyteClone.getTestResults();
					
					//bug#1342 recover old test analyte type (R/N) as this is not submitted
					//correctly when select is disabled and User re-sorts
					analyteType = testAnalyteClone.getTestAnalyteType();
					PropertyUtils.copyProperties(testAnalyteClone, testAnalyte);
					
					//bug#1342 recover old test analyte type (R/N) as this is not submitted
					//correctly when select is disabled and User resorts
					if (testAnalyte.getTestAnalyteType() == null) {
						testAnalyteClone.setTestAnalyteType(analyteType);
					}
					// list of testResults
					testResults = testAnalyteClone.getTestResults();
					
					session.merge(testAnalyteClone);
					session.flush();
					session.clear();

				} else {
					// INSERT
					testAnalyte.setId(null);

					// list of testResults
					testResults = testAnalyte.getTestResults();
					
					session.save(testAnalyte);
					session.flush();
					session.clear();
					
				}

				List newIds = new ArrayList();
				List oldIds = new ArrayList();

				if (testResults != null) {
					for (int j = 0; j < testResults.size(); j++) {
						TestResult tr = (TestResult) testResults.get(j);
						newIds.add(tr.getId());
					}
				}

				if ( (oldTestResults != null) && (oldTestResults.size() > 0) ) {
					List listOfOldOnesToRemove = new ArrayList();
					for (int j = 0; j < oldTestResults.size(); j++) {
						TestResult tr = (TestResult) oldTestResults.get(j);
						oldIds.add(tr.getId());
						if (!newIds.contains(tr.getId())) {
							// remove ones that are to be deleted
							listOfOldOnesToRemove.add(new Integer(j));
						}
					}

					int decreaseOTRIndexBy = 0;
					int decreaseOIIndexBy = 0;
					for (int j = 0; j < listOfOldOnesToRemove.size(); j++) {
						oldTestResults.remove(((Integer) listOfOldOnesToRemove
								.get(j)).intValue()
								- decreaseOTRIndexBy++);
						oldIds.remove(((Integer) listOfOldOnesToRemove.get(j))
								.intValue()
								- decreaseOIIndexBy++);

					}
				}

				//Loop through new testResults for this particular testAnalyte
				if (testResults != null) {
					for (int j = 0; j < testResults.size(); j++) {
						TestResult teRe = (TestResult) testResults.get(j);

						int index = oldIds.indexOf(teRe.getId());

						if (!StringUtil.isNullorNill(teRe.getId())
								&& allOldTestResultsForTest.containsKey(teRe
										.getId())) {
                            //UPDATE
							TestResult testResultClone = readTestResult(teRe.getId());
							PropertyUtils.copyProperties(testResultClone, teRe);
							if (index >= 0) {
								oldTestResults.set(index, testResultClone);
							} else {
								oldTestResults.add(testResultClone);
							}
							session.merge(teRe);
							session.flush();
							session.clear();
							
						} else {
                            //INSERT
							oldTestResults.add(teRe);
							session.save(teRe);
							session.flush();
							session.clear();
						}

					}

				}
				
				// remove test analytes from total list which holds all test analytes to be deleted (this is not a
				// candidate for delete because it is amongst the new test
				// analytes list!)
				if ( allOldTestAnalytes != null ) {
					for (int x = 0; x < allOldTestAnalytes.size(); x++) {
						TestAnalyte ta = (TestAnalyte) allOldTestAnalytes.get(x);
						if (ta.getId().equals(testAnalyte.getId())) {
							allOldTestAnalytes.remove(x);
							break;
						}
					}
				}
			}

			// BEGIN OF DELETE

			// Delete any left-over old test analytes
			if ( (allOldTestAnalytes != null) && (allOldTestAnalytes.size()>0) ) {
				for (int i = 0; i < allOldTestAnalytes.size(); i++) {
					TestAnalyte testAnalyte = (TestAnalyte) allOldTestAnalytes.get(i);
				
					session.delete(testAnalyte);
					session.flush();
					session.clear();	
				}
			}
		
			// Delete any left-over old test results
			if ( (allOldTrIds != null) && (allOldTrIds.size()>0) ) {
				for (int i = 0; i < allOldTrIds.size(); i++) {
					if (!allNewTrIds.contains(allOldTrIds.get(i))) {
						TestResult testResult = (TestResult) allOldTestResultsForTest.get(allOldTrIds.get(i));
					
						session.delete(testResult);
						session.flush();
						session.clear();	
					}
				}	
			}
			// END OF DELETE

			tx.commit();

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TestAnalyteTestResultDAOImpl","updateData()",e.toString());
			if (tx!=null) tx.rollback();
			throw new LIMSRuntimeException("Error in TestAnalyteTestResult updateData()", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see us.mn.state.health.lims.testanalyte.dao.TestAnalyteTestResultDAO#getAllTestAnalyteTestResultsPerTest(us.mn.state.health.lims.test.valueholder.Test)
	 */
	public List getAllTestAnalyteTestResultsPerTest(Test test) throws LIMSRuntimeException {
		Transaction tx = null;
		Session session = null;

		List list = new Vector(); 
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			String testId = test.getId();
			String sql = "from TestAnalyteTestResult t where t.testId = :param order by t.testAnalyteId, t.testResultId";
			org.hibernate.Query query = session.createQuery(sql);
			query.setParameter("param", testId);

			list = query.list();		
			session.flush();
			session.clear();				

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TestAnalyteTestResultDAOImpl","getAllTestAnalyteTestResultsPerTest()",e.toString());
			throw new LIMSRuntimeException("Error in TestAnalyteTestResult getAllTestAnalyteTestResultsPerTest()",e);
		}

		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see us.mn.state.health.lims.testanalyte.dao.TestAnalyteTestResultDAO#getPageOfTestAnalyteTestResults(int,
	 *      us.mn.state.health.lims.test.valueholder.Test)
	 */
	public List getPageOfTestAnalyteTestResults(int startingRecNo, Test test) throws LIMSRuntimeException {
		Transaction tx = null;
		Session session = null;
		
		List testAnalyteTestResults = new ArrayList();

		String testId = "0";

		if (test != null) {
			testId = test.getId();
		}

		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			//String query;
			if (!StringUtil.isNullorNill(testId)) {						
				String sql = "from TestAnalyteTestResult t where t.testId = :param";
				org.hibernate.Query query = session.createQuery(sql);
				query.setParameter("param", test.getId());
				
				query.setFirstResult(startingRecNo-1);
				query.setMaxResults(endingRecNo-1); 
						
				testAnalyteTestResults = query.list();
				session.flush();
				session.clear();		

				
			} else {
				String sql = "from TestAnalyteTestResult";
				org.hibernate.Query query = session.createQuery(sql);
				query.setFirstResult(startingRecNo-1);
				query.setMaxResults(endingRecNo-1); 
						
				testAnalyteTestResults = query.list();
				session.flush();				
			}

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TestAnalyteTestResultDAOImpl","getPageOfTestAnalyteTestResults()",e.toString());
			throw new LIMSRuntimeException("Error in TestAnalyteTestResult getPageOfTestAnalyteTestResults()",e);
		}

		return testAnalyteTestResults;
	}

	/**
	 * @param idString
	 * @return
	 */
	public TestAnalyte readTestAnalyte(String idString) {
		Transaction tx = null;
		Session session = null;
		
		TestAnalyte ta = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			ta = (TestAnalyte)session.get(TestAnalyte.class, idString);
			session.flush();
			session.clear();		
			
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TestAnalyteTestResultDAOImpl","readTestAnalyte()",e.toString());
			throw new LIMSRuntimeException("Error in TestAnalyte readTestAnalyte()", e);
		}			
		
		return ta;
	}

	/**
	 * @param idString
	 * @return
	 */
	public TestResult readTestResult(String idString) {
		Transaction tx = null;
		Session session = null;
		
		TestResult tr = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			tr = (TestResult)session.get(TestResult.class, idString);
			session.flush();
			session.clear();		
			
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TestAnalyteTestResultDAOImpl","readTestResult()",e.toString());
			throw new LIMSRuntimeException("Error in TestResult readTestResult()", e);
		}			
		
		return tr;		
	}

	// NOT USED CURRENTLY
	/*
	 * (non-Javadoc)
	 * 
	 * @see us.mn.state.health.lims.testanalyte.dao.TestAnalyteTestResultDAO#getNextTestAnalyteTestResultRecord(java.lang.String)
	 */
	public List getNextTestAnalyteTestResultRecord(String id)
			throws LIMSRuntimeException {

		return getNextRecord(id, "Analyte", TestAnalyte.class);

	}

	// NOT USED CURRENTLY
	/*
	 * (non-Javadoc)
	 * 
	 * @see us.mn.state.health.lims.testanalyte.dao.TestAnalyteTestResultDAO#getPreviousTestAnalyteTestResultRecord(java.lang.String)
	 */
	public List getPreviousTestAnalyteTestResultRecord(String id)
			throws LIMSRuntimeException {

		return getPreviousRecord(id, "Analyte", TestAnalyte.class);
	}

	/**
	 * @param uow
	 * @param test
	 * @return
	 */
	private long getMaximumResultGroupForTest(Test test) {
		Transaction tx = null;
		Session session = null;
		
		long maxNumber = 0;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			String sql = "from TestAnalyte t where t.test = :param order by t.resultGroup desc";
			org.hibernate.Query query = session.createQuery(sql);
			query.setParameter("param", test.getId());

			List list = query.list();
			session.flush();
			session.clear();		
			
			TestAnalyte ta = null;
			if ( list.size() > 0 ) {
				ta = (TestAnalyte)list.get(0);
				if ( (ta.getResultGroup() != null) && (ta.getResultGroup().length()>0) )
					maxNumber = Long.parseLong(ta.getResultGroup());
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TestAnalyteTestResultDAOImpl","getMaximumResultGroupForTest()",e.toString());
			throw new LIMSRuntimeException("Error in long getMaximumResultGroupForTest(test)", e);
		}

		return maxNumber;
	}
}