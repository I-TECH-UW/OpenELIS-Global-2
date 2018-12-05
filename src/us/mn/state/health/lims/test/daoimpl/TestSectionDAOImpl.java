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
package us.mn.state.health.lims.test.daoimpl;

import java.util.List;
import java.util.Vector;

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
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.systemusersection.dao.SystemUserSectionDAO;
import us.mn.state.health.lims.systemusersection.daoimpl.SystemUserSectionDAOImpl;
import us.mn.state.health.lims.systemusersection.valueholder.SystemUserSection;
import us.mn.state.health.lims.test.dao.TestSectionDAO;
import us.mn.state.health.lims.test.valueholder.TestSection;

/**
 * @author diane benz
 */
public class TestSectionDAOImpl extends BaseDAOImpl implements TestSectionDAO {

	public void deleteData(List testSections) throws LIMSRuntimeException {
		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < testSections.size(); i++) {
				TestSection data = (TestSection)testSections.get(i);
			
				TestSection oldData = (TestSection)readTestSection(data.getId());
				TestSection newData = new TestSection();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "TEST_SECTION";
				auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
			}
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TestSectionDAOImpl","AuditTrail deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in TestSection AuditTrail deleteData()", e);
		}  
		
		try {		
			for (int i = 0; i < testSections.size(); i++) {
				TestSection data = (TestSection) testSections.get(i);
				//bugzilla 2206
				data = (TestSection)readTestSection(data.getId());
    			HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}			
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TestSectionDAOImpl","deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in TestSection deleteData()",	e);
		} 
	}

	public boolean insertData(TestSection testSection) throws LIMSRuntimeException {
		
		try {
			if (duplicateTestSectionExists(testSection)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for "
								+ testSection.getTestSectionName());
			}
			
			String id = (String)HibernateUtil.getSession().save(testSection);
			testSection.setId(id);

            new AuditTrailDAOImpl().saveNewHistory(testSection,testSection.getSysUserId(),"TEST_SECTION");
			
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
								
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TestSectionDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in TestSection insertData()", e);
		}
		
		return true;
	}

	public void updateData(TestSection testSection) throws LIMSRuntimeException {
		// bugzilla 1482 throw Exception if record already exists
		try {
			if (duplicateTestSectionExists(testSection)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for "
								+ testSection.getTestSectionName());
			}
		} catch (Exception e) {
    		//bugzilla 2154
			LogEvent.logError("TestSectionDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in TestSection updateData()",
					e);
		}
		
		TestSection oldData = (TestSection)readTestSection(testSection.getId());
		TestSection newData = testSection;

		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = testSection.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "TEST_SECTION";
			auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TestSectionDAOImpl","AuditTrail updateData()",e.toString());
			throw new LIMSRuntimeException("Error in TestSection AuditTrail updateData()", e);
		}  
							
		try {
			HibernateUtil.getSession().merge(testSection);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(testSection);
			HibernateUtil.getSession().refresh(testSection);
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TestSectionDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in TestSection updateData()",	e);
		}
	}

	public void getData(TestSection testSection) throws LIMSRuntimeException {
		try {
			TestSection testSec = (TestSection)HibernateUtil.getSession().get(TestSection.class, testSection.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (testSec != null) {
				PropertyUtils.copyProperties(testSection, testSec);
			} else {
				testSection.setId(null);
			}
		} catch (Exception e) {
			LogEvent.logError("TestSectionDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in TestSection getData()", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<TestSection> getAllTestSections() throws LIMSRuntimeException {
		List<TestSection> list = null;
		try {
			String sql = "from TestSection";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("TestSectionDAOImpl","getAllTestSections()",e.toString());
			throw new LIMSRuntimeException("Error in TestSection getAllTestSections()", e);
		}
		return list;
	}

	/**
	 * Get all the test sections assigned to this specific user
	 * @param sysUserId the user system id
	 * @return list of tests
	 */
	public List getAllTestSectionsBySysUserId(int sysUserId) throws LIMSRuntimeException {
		List list = new Vector();
		
		String sectionIdList = "";
		String sql = "";
		
		try {
			SystemUserSectionDAO systemUserSectionDao = new SystemUserSectionDAOImpl();
			List userTestSectionList= systemUserSectionDao.getAllSystemUserSectionsBySystemUserId(sysUserId);
			for ( int i=0; i<userTestSectionList.size(); i++ ) {
				SystemUserSection sus = (SystemUserSection)userTestSectionList.get(i);	
				sectionIdList += sus.getTestSection().getId() + ",";			
			}				
			if ( !(sectionIdList.equals("")) && (sectionIdList.length() > 0) ) {
				sectionIdList = sectionIdList.substring(0,sectionIdList.length()-1);
				sql = "from TestSection where id in ("+sectionIdList+")";
			} else {
				return list;
			}				
		
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TestSectionDAOImpl","getAllTestSectionsBySysUserId()",e.toString());
			throw new LIMSRuntimeException("Error in TestSection getAllTestSectionsBySysUserId()", e);
		}
		return list;
	}
	
	public List getPageOfTestSections(int startingRecNo) throws LIMSRuntimeException {
		List list = new Vector();
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
			
			//bugzilla 1399
			String sql = "from TestSection t order by t.organization.organizationName, t.testSectionName";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(startingRecNo-1);
			query.setMaxResults(endingRecNo-1); 					
			
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TestSectionDAOImpl","getPageOfTestSections()",e.toString());
			throw new LIMSRuntimeException("Error in TestSection getPageOfTestSections()", e);
		}

		return list;
	}

	public TestSection readTestSection(String idString) {
		TestSection ts = null;
		try {
			ts = (TestSection)HibernateUtil.getSession().get(TestSection.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TestSectionDAOImpl","readCity()",e.toString());
			throw new LIMSRuntimeException("Error in TestSection readCity()", e);
		}			
		
		return ts;
	}

	// this is for autocomplete
	public List getTestSections(String filter) throws LIMSRuntimeException {
		List list = new Vector(); 	
		try {
			String sql = "from TestSection t where upper(t.testSectionName) like upper(:param) order by upper(t.testSectionName)";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", filter+"%");		
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TestSectionDAOImpl","getTestSections()",e.toString());
			throw new LIMSRuntimeException("Error in TestSection getTestSections(String filter)", e);
		}
		
		return list;
	}

	// this is for autocomplete
	public List getTestSectionsBySysUserId(String filter, int sysUserId) throws LIMSRuntimeException {
		List list = new Vector(); 	
		String sectionIdList = "";
		String sql = "";
		
		try {
			SystemUserSectionDAO systemUserSectionDao = new SystemUserSectionDAOImpl();
			List userTestSectionList= systemUserSectionDao.getAllSystemUserSectionsBySystemUserId(sysUserId);
			for ( int i=0; i<userTestSectionList.size(); i++ ) {
				SystemUserSection sus = (SystemUserSection)userTestSectionList.get(i);	
				sectionIdList += sus.getTestSection().getId() + ",";			
			}	
			if ( !(sectionIdList.equals("")) && (sectionIdList.length() > 0) ) {
				sectionIdList = sectionIdList.substring(0,sectionIdList.length()-1);
				sql = "from TestSection t where upper(t.testSectionName) like upper(:param) and t.id in ("+sectionIdList+") order by upper(t.testSectionName)";
			} else {
				return list;
			}						
		
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", filter+"%");		
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TestSectionDAOImpl","getTestSectionsBySysUserId()",e.toString());
			throw new LIMSRuntimeException("Error in TestSection getTestSectionsBySysUserId(String filter)", e);
		}
		
		return list;
	}
	
	public List getNextTestSectionRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "TestSection", TestSection.class);

	}

	public List getPreviousTestSectionRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "TestSection", TestSection.class);
	}

	@SuppressWarnings("unchecked")
	public TestSection getTestSectionByName(TestSection testSection) throws LIMSRuntimeException {
		try {
			String sql = "from TestSection t where t.testSectionName = :param";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", testSection.getTestSectionName());

			List<TestSection> list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			
			if ( !list.isEmpty() ){
				return list.get(0);
			}
			
			return null;

		} catch (Exception e) {
			LogEvent.logError("TestSectionDAOImpl","getTestSectionByName()",e.toString());
			throw new LIMSRuntimeException("Error in TestSection getTestSectionByName()", e);
		}
	}
	
	//bugzilla 1411
	public Integer getTotalTestSectionCount() throws LIMSRuntimeException {
		return getTotalCount("TestSection", TestSection.class);
	}
	
//	bugzilla 1427
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {	
		int currentId= (Integer.valueOf(id)).intValue();
		String tablePrefix = getTablePrefix(table);
		
		List list = new Vector();
		//bugzilla 1908
		int rrn = 0;
		try {
			//bugzilla 1908 cannot use named query for postgres because of oracle ROWNUM
			//instead get the list in this sortorder and determine the index of record with id = currentId
    		String sql = "select ts.id from TestSection ts " +
					" order by ts.organization.organizationName, ts.testSectionName";

  			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			rrn = list.indexOf(String.valueOf(currentId));

			list = HibernateUtil.getSession().getNamedQuery(
					tablePrefix + "getNext").setFirstResult(
					rrn + 1).setMaxResults(2).list();

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TestSectionDAOImpl","getNextRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getNextRecord() for "
					+ table, e);
		}

		return list;		
	}

	//bugzilla 1427
	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {		
		int currentId= (Integer.valueOf(id)).intValue();
		String tablePrefix = getTablePrefix(table);
		
		List list = new Vector();
		//bugzilla 1908
		int rrn = 0;
		try {
			//bugzilla 1908 cannot use named query for postgres because of oracle ROWNUM
			//instead get the list in this sortorder and determine the index of record with id = currentId
    		String sql = "select ts.id from TestSection ts " +
					" order by ts.organization.organizationName desc, ts.testSectionName desc";

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
			//bugzilla 2154
			LogEvent.logError("TestSectionDAOImpl","getPreviousRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		} 

		return list;
	}
	
	private boolean duplicateTestSectionExists(TestSection testSection) throws LIMSRuntimeException {
		try {


			String sql = "from TestSection t where trim(lower(t.testSectionName)) = :name and t.id != :id";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);

			query.setParameter("name", testSection.getTestSectionName().toLowerCase().trim());

			String testSectionId = "0";
			if (!StringUtil.isNullorNill(testSection.getId())) {
				testSectionId = testSection.getId();
			}
			query.setInteger("id", Integer.parseInt(testSectionId));

			List list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

            return !list.isEmpty();

		} catch (Exception e) {
			LogEvent.logError("TestSectionDAOImpl","duplicateTestSectionExists()",e.toString());
			throw new LIMSRuntimeException(
					"Error in duplicateTestSectionExists()", e);
		}
	}

	public List<TestSection> getAllActiveTestSections() {
		String sql = "from TestSection t where t.isActive = 'Y' order by t.sortOrderInt";
		
		try {
			Query query = HibernateUtil.getSession().createQuery(sql);
			@SuppressWarnings("unchecked")
			List<TestSection> sections = query.list();
			closeSession();
			return sections;
		} catch (HibernateException e) {
			handleException(e, "getAllActiveTestSections");
		}
		return null;
	}

    public List<TestSection> getAllInActiveTestSections() {
        String sql = "from TestSection t where t.isActive = 'N' order by t.sortOrderInt";

        try {
            Query query = HibernateUtil.getSession().createQuery(sql);
            List<TestSection> sections = query.list();
            closeSession();
            return sections;
        } catch (HibernateException e) {
            handleException(e, "getAllInActiveTestSections");
        }
        return null;
    }

    @SuppressWarnings("unchecked")
	@Override
	public TestSection getTestSectionByName(String testSection) throws LIMSRuntimeException {
		try {
			String sql = "from TestSection t where t.testSectionName = :name order by t.sortOrderInt";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("name", testSection);

			List<TestSection> list = query.list();
			
			closeSession();
			
			if ( !list.isEmpty() ){
				return list.get(0);
			}

		} catch (Exception e) {
			handleException(e, "getTestSectionByName");
		}
		
		return null;
	}

	@Override
	public TestSection getTestSectionById(String testSectionId) {
		try {
			TestSection testSection = (TestSection)HibernateUtil.getSession().get(TestSection.class, testSectionId);
			closeSession();
			return testSection;
		} catch (HibernateException e) {
			handleException(e, "getTestSectionById");
		}
		return null;
	}
	
}