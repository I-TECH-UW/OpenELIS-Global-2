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
package org.openelisglobal.test.daoimpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.method.valueholder.Method;
import org.openelisglobal.test.dao.TestDAO;
import org.openelisglobal.test.valueholder.Test;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diane benz
 */
@Component
@Transactional
public class TestDAOImpl extends BaseDAOImpl<Test, String> implements TestDAO {

    public TestDAOImpl() {
        super(Test.class);
    }

//	@Override
//	public void deleteData(List tests) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < tests.size(); i++) {
//				Test data = (Test) tests.get(i);
//
//				Test oldData = readTest(data.getId());
//				Test newData = new Test();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "TEST";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TestDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Test AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < tests.size(); i++) {
//				Test data = (Test) tests.get(i);
//				Test cloneData = readTest(data.getId());
//
//				cloneData.setIsActive(IActionConstants.NO);
//				entityManager.unwrap(Session.class).merge(cloneData);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//				// entityManager.unwrap(Session.class).evict // CSL remove old(cloneData);
//				// entityManager.unwrap(Session.class).refresh // CSL remove old(cloneData);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TestDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Test deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(Test test) throws LIMSRuntimeException {
//
//		try {
//			if (test.getIsActive().equals(IActionConstants.YES) && duplicateTestExists(test)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for " + test.getDescription());
//			}
//			String id = (String) entityManager.unwrap(Session.class).save(test);
//			test.setId(id);
//
//			String sysUserId = test.getSysUserId();
//			String tableName = "TEST";
//			auditDAO.saveNewHistory(test, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TestDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in Test insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(Test test) throws LIMSRuntimeException {
//
//		try {
//			if (test.getIsActive().equals(IActionConstants.YES) && duplicateTestExists(test)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + TestServiceImpl.getUserLocalizedTestName(test));
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("TestDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Test updateData()", e);
//		}
//		Test oldData = readTest(test.getId());
//
//		try {
//
//			String sysUserId = test.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "TEST";
//			auditDAO.saveHistory(test, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			LogEvent.logError("TestDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Test AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(test);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(test);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(test);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TestDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Test updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(Test test) throws LIMSRuntimeException {
        try {
            Test testClone = entityManager.unwrap(Session.class).get(Test.class, test.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (testClone != null) {
                PropertyUtils.copyProperties(test, testClone);
            } else {
                test.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Test getData()", e);
        }
    }

    @Override

    @Transactional(readOnly = true)
    public List<Test> getAllTests(boolean onlyTestsFullySetup) throws LIMSRuntimeException {
        List<Test> list = new Vector<>();
        try {
            String sql = "from Test Order by description";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
//			list = filterOnlyFullSetup(onlyTestsFullySetup, list);

        } catch (RuntimeException e) {
            handleException(e, "getAllTests()");
        }

        return list;
    }

    @Override

    @Transactional(readOnly = true)
    public List<Test> getAllActiveTests(boolean onlyTestsFullySetup) throws LIMSRuntimeException {
        List<Test> list = new Vector<>();
        try {
            String sql = "from Test WHERE is_Active = 'Y' Order by description";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
//			list = filterOnlyFullSetup(onlyTestsFullySetup, list);

            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            handleException(e, "getAllActiveTests()");
        }

        return list;
    }

//	private List<Test> filterOnlyFullSetup(boolean onlyTestsFullySetup, List<Test> list) {
//		if (onlyTestsFullySetup && list != null && list.size() > 0) {
//			Iterator<Test> testIterator = list.iterator();
//			list = new Vector<>();
//			while (testIterator.hasNext()) {
//				Test test = testIterator.next();
//				if (isTestFullySetup(test)) {
//					list.add(test);
//				}
//			}
//		}
//		return list;
//	}

    @Override
    @Transactional(readOnly = true)
    public List<Test> getAllActiveOrderableTests() throws LIMSRuntimeException {
        try {
            String sql = "from Test t WHERE t.isActive = 'Y'  and t.orderable = true Order by t.description";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            List<Test> list = query.list();

            // closeSession(); // CSL remove old
            return list;
        } catch (RuntimeException e) {
            handleException(e, "getAllActiveOrderableTests()");
        }

        return null;
    }

    /**
     * Get all the tests assigned to this user
     *
     * @param sysUserId the user system id
     * @return list of test section bugzilla 2291 added onlyTestsFullySetup
     */
//	@Override
//	public List getAllTestsBySysUserId(int sysUserId, boolean onlyTestsFullySetup) throws LIMSRuntimeException {
//		List list ;
//		String sectionIdList = "";
//		String sql;
//
//		try {
//			List userTestSectionList = systemUserSectionDAO.getAllSystemUserSectionsBySystemUserId(sysUserId);
//			for (int i = 0; i < userTestSectionList.size(); i++) {
//				SystemUserSection sus = (SystemUserSection) userTestSectionList.get(i);
//				sectionIdList += sus.getTestSection().getId() + ",";
//			}
//
//			if (!(sectionIdList.equals("")) && (sectionIdList.length() > 0)) {
//				sectionIdList = sectionIdList.substring(0, sectionIdList.length() - 1);
//				sql = "from Test t where t.testSection.id  in (" + sectionIdList + ") Order by description";
//			} else {
//				return list;
//			}
//
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			list = query.list();
//
//			list = filterOnlyFullSetup(onlyTestsFullySetup, list);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TestDAOImpl", "getAllTestsBySysUserId()", e.toString());
//			throw new LIMSRuntimeException("Error in Test getAllTestsBySysUserId()", e);
//		}
//		return list;
//	}

    @Override
    @Transactional(readOnly = true)
    public List<Test> getPageOfTests(int startingRecNo) throws LIMSRuntimeException {
        List<Test> list;
        try {
            // calculate maxRow to be one more than the page size
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            // bugzilla 1399
            String sql = "from Test t order by t.testSection.testSectionName";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Test getPageOfTests()", e);
        }

        return list;
    }

    // bugzilla 2371
    @Override
    @Transactional(readOnly = true)
    public List<Test> getPageOfSearchedTests(int startingRecNo, String searchString) throws LIMSRuntimeException {
        List<Test> list;
        String wildCard = "*";
        String newSearchStr;
        String sql;

        try {
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
            int wCdPosition = searchString.indexOf(wildCard);

            if (wCdPosition == -1) // no wild card looking for exact match
            {
                newSearchStr = searchString.toLowerCase().trim();
                sql = "from Test t  where trim(lower (t.description)) = :param  order by t.testSection.testSectionName";
            } else {
                newSearchStr = searchString.replace(wildCard, "%").toLowerCase().trim();
                sql = "from Test t where trim(lower (t.description)) like :param  order by t.testSection.testSectionName";
            }
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", newSearchStr);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logDebug(e);
            throw new LIMSRuntimeException("Error in Test getPageOfSearchedTests()", e);
        }

        return list;
    }

    // end bugzilla 2371

    /**
     * Get all the tests assigned to this user
     *
     * @param startingRecNo the start record
     * @param sysUserId     is the user system id
     * @return list of test section
     */
//	@Override
//	public List getPageOfTestsBySysUserId(int startingRecNo, int sysUserId) throws LIMSRuntimeException {
//		List list ;
//		try {
//			// calculate maxRow to be one more than the page size
//			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
//
//			String sectionIdList = "";
//			String sql;
//
//			List userTestSectionList = systemUserSectionDAO.getAllSystemUserSectionsBySystemUserId(sysUserId);
//			for (int i = 0; i < userTestSectionList.size(); i++) {
//				SystemUserSection sus = (SystemUserSection) userTestSectionList.get(i);
//				sectionIdList += sus.getTestSection().getId() + ",";
//			}
//
//			if (!(sectionIdList.equals("")) && (sectionIdList.length() > 0)) {
//				sectionIdList = sectionIdList.substring(0, sectionIdList.length() - 1);
//				sql = "from Test t where t.testSection.id in (" + sectionIdList
//						+ ") order by t.testSection.testSectionName, t.testName";
//			} else {
//				return list;
//			}
//
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setFirstResult(startingRecNo - 1);
//			query.setMaxResults(endingRecNo - 1);
//
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TestDAOImpl", "getPageOfTestsBySysUserId()", e.toString());
//			throw new LIMSRuntimeException("Error in Test getPageOfTestsBySysUserId()", e);
//		}
//
//		return list;
//	}

    /**
     * Get all the tests assigned to this user
     *
     * @param startingRecNo the start record
     * @param sysUserId     is the user system id
     * @return list of test section
     */
//	@Override
//	public List<Test> getPageOfSearchedTestsBySysUserId(int startingRecNo, int sysUserId, String searchString)
//			throws LIMSRuntimeException {
//		String wildCard = "*";
//		String newSearchStr;
//		String sql;
//
//		try {
//			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
//
//			String sectionIdList = "";
//
//
//			List<SystemUserSection> userTestSectionList = systemUserSectionDAO
//					.getAllSystemUserSectionsBySystemUserId(sysUserId);
//
//			for (int i = 0; i < userTestSectionList.size(); i++) {
//				sectionIdList += userTestSectionList.get(i).getTestSection().getId() + ",";
//			}
//
//			if (!(sectionIdList.equals("")) && (sectionIdList.length() > 0)) {
//				sectionIdList = sectionIdList.substring(0, sectionIdList.length() - 1);
//				int wCdPosition = searchString.indexOf(wildCard);
//
//				if (wCdPosition == -1) // no wild card looking for exact match
//				{
//					newSearchStr = searchString.toLowerCase().trim();
//					sql = "from Test t  where t.testSection.id in (" + sectionIdList
//							+ " ) and  trim(lower (t.description)) = :param  order by t.testSection.testSectionName, t.testName";
//				} else {
//					newSearchStr = searchString.replace(wildCard, "%").toLowerCase().trim();
//					sql = "from Test t where t.testSection.id in (" + sectionIdList
//							+ ") and trim(lower (t.description)) like :param  order by t.testSection.testSectionName, t.testName";
//				}
//			} else {
//				return new ArrayList<>();
//			}
//
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setParameter("param", newSearchStr);
//			query.setFirstResult(startingRecNo - 1);
//			query.setMaxResults(endingRecNo - 1);
//
//
//			List<Test> list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			return list;
//		} catch (RuntimeException e) {
//			LogEvent.logDebug(e);
//			throw new LIMSRuntimeException("Error in Test getPageOfTestsBySysUserId()", e);
//		}
//
//	}

    public Test readTest(String idString) {
        Test test;
        try {
            test = entityManager.unwrap(Session.class).get(Test.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Test readTest()", e);
        }

        return test;
    }

    // this is for autocomplete
    // bugzilla 2291 added onlyTestsFullySetup
    @Override
    @Transactional(readOnly = true)
    public List<Test> getTests(String filter, boolean onlyTestsFullySetup) throws LIMSRuntimeException {
        List<Test> list;
        try {
            String sql = "from Test t where (upper(t.localizedTestName.english) like upper(:param) or upper(t.localizedTestName.french) like upper(:param)) and t.isActive='Y'";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", filter + "%");
            list = query.list();

//			list = filterOnlyFullSetup(onlyTestsFullySetup, list);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Test getTests(String filter)", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Test> getTestsByName(String testName) throws LIMSRuntimeException {
        String sql = "from Test t where (t.localizedTestName.english = :testName or t.localizedTestName.french = :testName)";
        try {
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("testName", testName);

            return query.list();
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Test getTestByName()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Test> getActiveTestsByName(String testName) throws LIMSRuntimeException {
        String sql = "from Test t where (t.localizedTestName.english = :testName or t.localizedTestName.french = :testName) and t.isActive='Y'";
        try {
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("testName", testName);

            return query.list();
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Test getTestByName()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Test getActiveTestByLocalizedName(String testName, Locale locale) throws LIMSRuntimeException {
        String sql;
        if (Locale.ENGLISH.equals(locale)) {
            sql = "from Test t where t.localizedTestName.english = :testName and t.isActive='Y'";
        } else {
            sql = "from Test t where t.localizedTestName.french = :testName and t.isActive='Y'";
        }
        try {
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("testName", testName);

            List<Test> list = query.list();
            Test t = null;

            if (!list.isEmpty()) {
                t = list.get(0);
            }

            return t;

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Test getTestByName()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Test getTestByLocalizedName(String testName, Locale locale) throws LIMSRuntimeException {
        String sql;
        if (Locale.ENGLISH.equals(locale)) {
            sql = "from Test t where t.localizedTestName.english = :testName";
        } else {
            sql = "from Test t where t.localizedTestName.french = :testName";
        }
        try {
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("testName", testName);

            List<Test> list = query.list();
            Test t = null;

            if (!list.isEmpty()) {
                t = list.get(0);
            }

            return t;

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Test getTestByName()", e);
        }
    }

    @Override

    @Transactional(readOnly = true)
    public Test getActiveTestById(Integer testId) throws LIMSRuntimeException {
        List<Test> list = null;

        try {
            String sql = "from Test t where t.id = :testId and t.isActive='Y'";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("testId", testId);

            list = query.list();

            // closeSession(); // CSL remove old
        } catch (HibernateException e) {
            handleException(e, "getActiveTestById");
        }

        return (list != null && list.size() > 0) ? list.get(0) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public Test getTestById(Test test) throws LIMSRuntimeException {
        Test returnTest;
        try {
            returnTest = entityManager.unwrap(Session.class).get(Test.class, test.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Test getTestById()", e);
        }

        return returnTest;
    }

    // this is for selectdropdown
    @Override
    @Transactional(readOnly = true)
    public List<Method> getMethodsByTestSection(String filter) throws LIMSRuntimeException {
        try {
            String sql = "from Test t where t.testSection = :param";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", filter);

            List<Test> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            List<Method> methods = new ArrayList();

            for (int i = 0; i < list.size(); i++) {
                Test t = list.get(i);
                /*
                 * LogEvent.logInfo(this.getClass().getName(), "method unkown", "This is test "
                 * + t.getId() + " " + t.getTestName());
                 */
                Method method = t.getMethod();
                if (!methods.contains(method)) {
                    methods.add(method);
                }
                /*
                 * LogEvent.logInfo(this.getClass().getName(), "method unkown",
                 * "Adding this method to list " + method.getId() + " " +
                 * method.getMethodName());
                 */
            }

            return methods;

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Method getMethodsByTestSection(String filter)", e);
        }
    }

    // this is for selectdropdown
    @Override
    @Transactional(readOnly = true)
    public List<Test> getTestsByTestSection(String filter) throws LIMSRuntimeException {
        try {
            String sql = "from Test t where t.testSection = :param";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("param", Integer.parseInt(filter));

            List<Test> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            return list;

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Method getTestsByTestSection(String filter)", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Test> getTestsByTestSectionId(String id) throws LIMSRuntimeException {
        try {
            String sql = "from Test t where t.testSection.id = :id";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("id", Integer.parseInt(id));

            List<Test> list = query.list();
            // closeSession(); // CSL remove old
            return list;

        } catch (RuntimeException e) {
            handleException(e, "getTestsByTestSectionId");
        }

        return null;
    }

    // this is for selectdropdown
    @Override
    @Transactional(readOnly = true)
    public List<Test> getTestsByMethod(String filter) throws LIMSRuntimeException {
        try {
            String sql = "from Test t where t.method = :param";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", filter);

            List<Test> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            return list;

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Method getTestsByMethod(String filter)", e);
        }
    }

    // this is for selectdropdown
    @Override
    @Transactional(readOnly = true)
    public List<Test> getTestsByTestSectionAndMethod(String filter, String filter2) throws LIMSRuntimeException {
        try {
            String sql = "from Test t where t.testSection = :param1 and t.method = :param2";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param1", filter);
            query.setParameter("param2", filter2);

            List<Test> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            return list;

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Method getTestsByMethod(String filter)", e);
        }
    }

    // bugzilla 1411
    @Override
    @Transactional(readOnly = true)
    public Integer getTotalTestCount() throws LIMSRuntimeException {
        return getCount();
    }

    // bugzilla 2371
    @Override
    @Transactional(readOnly = true)
    public Integer getTotalSearchedTestCount(String searchString) throws LIMSRuntimeException {
        String wildCard = "*";
        String newSearchStr;
        String sql;
        Integer count = null;

        try {
            int wCdPosition = searchString.indexOf(wildCard);
            if (wCdPosition == -1) // no wild card looking for exact match
            {
                newSearchStr = searchString.toLowerCase().trim();
                sql = "select count (*) from Test t  where trim(lower (t.description)) = :param  ";
            } else {
                newSearchStr = searchString.replace(wildCard, "%").toLowerCase().trim();
                sql = "select count (*) from Test t where trim(lower (t.description)) like :param ";
            }
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", newSearchStr);

            List<Long> results = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            if (results != null && results.get(0) != null) {
                if (results.get(0) != null) {
                    count = results.get(0).intValue();
                }
            }

        } catch (RuntimeException e) {
            LogEvent.logDebug(e);
            throw new LIMSRuntimeException("Error in TestDaoImpl getTotalSearchedTestCount()", e);
        }

        return count;

    }

    // end bugzilla 2371

    // bugzilla 2371
//	@Override
//	public Integer getTotalSearchedTestCountBySysUserId(int sysUserId, String searchString)
//			throws LIMSRuntimeException {
//		String wildCard = "*";
//		String newSearchStr;
//		String sql;
//		Integer count = null;
//
//		try {
//			String sectionIdList = "";
//
//			List userTestSectionList = systemUserSectionDAO.getAllSystemUserSectionsBySystemUserId(sysUserId);
//
//			for (int i = 0; i < userTestSectionList.size(); i++) {
//				SystemUserSection sus = (SystemUserSection) userTestSectionList.get(i);
//				sectionIdList += sus.getTestSection().getId() + ",";
//			}
//
//			if (!(sectionIdList.equals("")) && (sectionIdList.length() > 0)) {
//				sectionIdList = sectionIdList.substring(0, sectionIdList.length() - 1);
//				int wCdPosition = searchString.indexOf(wildCard);
//
//				if (wCdPosition == -1) // no wild card looking for exact match
//				{
//					newSearchStr = searchString.toLowerCase().trim();
//					sql = "select count (*) from Test t  where t.testSection.id in (" + sectionIdList
//							+ ") and trim(lower (t.description)) = :param ";
//				} else {
//					newSearchStr = searchString.replace(wildCard, "%").toLowerCase().trim();
//					sql = "select count (*) from Test t where t.testSection.id in (" + sectionIdList
//							+ ") and trim(lower (t.description)) like :param ";
//				}
//			} else {
//				return count;
//			}
//
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setParameter("param", newSearchStr);
//
//			List results = query.list();
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//			if (results != null && results.get(0) != null) {
//				if (results.get(0) != null) {
//					count = ((Long) results.get(0)).intValue();
//				}
//			}
//
//		} catch (RuntimeException e) {
//			LogEvent.logDebug(e);
//			throw new LIMSRuntimeException("Error in TestDaoImpl getTotalSearchedTestCountBySysUserId()", e);
//		}
//
//		return count;
//	}

    // end bugzilla 2371

    // bugzilla 2371
//	@Override
//	public Integer getAllSearchedTotalTestCount(HttpServletRequest request, String searchString)
//			throws LIMSRuntimeException {
//		Integer count;
//
//		try {
//			if (SystemConfiguration.getInstance().getEnableUserTestSection().equals(NO)) {
//				count = getTotalSearchedTestCount(searchString);
//			} else {
//				UserSessionData usd = (UserSessionData) request.getSession().getAttribute(USER_SESSION_DATA);
//
//				UserModuleService userModuleService = SpringContext.getBean(UserModuleService.class);
//				if (!userModuleService.isUserAdmin(request)) {
//					count = getTotalSearchedTestCountBySysUserId(usd.getSystemUserId(), searchString);
//				} else {
//					count = getTotalSearchedTestCount(searchString);
//
//				}
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logDebug(e);
//			throw new LIMSRuntimeException("Error in testDAOImpl getAllSearchedTotalTestCount()", e);
//		}
//		return count;
//	}

    // end if bugzilla 2371

    @Override
    public boolean duplicateTestExists(Test test) throws LIMSRuntimeException {
        try {

            List<Test> list = new ArrayList();

            if (test.getIsActive().equalsIgnoreCase("Y")) {
                // not case sensitive hemolysis and Hemolysis are considered
                // duplicates
                String sql = "from Test t where (trim(lower(t.description)) = :description and t.isActive='Y' and t.id != :testId)";
                org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);

                // initialize with 0 (for new records where no id has been
                // generated yet
                String testId = "0";
                if (!StringUtil.isNullorNill(test.getId())) {
                    testId = test.getId();
                }
                query.setInteger("testId", Integer.parseInt(testId));
                query.setParameter("description", test.getDescription().toLowerCase().trim());

                list = query.list();
                // entityManager.unwrap(Session.class).flush(); // CSL remove old
                // entityManager.unwrap(Session.class).clear(); // CSL remove old

            }

            return !list.isEmpty();

        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in duplicateTestExists()", e);
        }
    }

    // bugzilla 2236
//	@Override
//	public boolean isTestFullySetup(Test test) throws LIMSRuntimeException {
//		try {
//			List testAnalytesByTest = testAnalyteDAO.getAllTestAnalytesPerTest(test);
//			boolean result = true;
//			if (testAnalytesByTest == null || testAnalytesByTest.size() == 0) {
//				result = false;
//			} else {
//				// bugzilla 2291 make sure none of the components has a null
//				// result group
//				boolean atLeastOneResultGroupFound = false;
//				for (int j = 0; j < testAnalytesByTest.size(); j++) {
//					TestAnalyte testAnalyte = (TestAnalyte) testAnalytesByTest.get(j);
//					if (testAnalyte.getResultGroup() == null) {
//						atLeastOneResultGroupFound = true;
//						break;
//					}
//				}
//				if (atLeastOneResultGroupFound) {
//					result = false;
//				}
//			}
//			return result;
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TestDAOImpl", "isTestFullySetup()", e.toString());
//			throw new LIMSRuntimeException("Error in isTestFullySetup()", e);
//		}
//	}

    // bugzilla 2443
    @Override
    @Transactional(readOnly = true)
    public Integer getNextAvailableSortOrderByTestSection(Test test) throws LIMSRuntimeException {
        Integer result = null;

        try {

            List<Test> list;
            Test testWithHighestSortOrder;

            String sql = "from Test t where t.testSection = :param and t.sortOrder is not null order by t.sortOrder desc";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", test.getTestSection());

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            if (list.size() > 0) {
                testWithHighestSortOrder = list.get(0);
                if (testWithHighestSortOrder != null
                        && !StringUtil.isNullorNill(testWithHighestSortOrder.getSortOrder())) {
                    result = (Integer.parseInt(testWithHighestSortOrder.getSortOrder()) + 1);
                }
            }

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in getNextAvailableSortOrderByTestSection()", e);
        }
        return result;
    }

    /**
     * @see org.openelisglobal.test.dao.TestDAO#getAllOrderBy(java.lang.String) Read
     *      all entities from the database sorted by an appropriate property
     */
    @Override

    @Transactional(readOnly = true)
    public List<Test> getAllOrderBy(String columnName) throws LIMSRuntimeException {
        List<Test> entities;
        try {
            if (!StringUtil.isJavaIdentifier(columnName)) {
                throw new IllegalArgumentException("\"" + columnName + "\" is not valid syntax for a column name");
            }
            // I didn't manage to get a query parameter to be used as a column
            // name to sort by (because ORDER BY "my_column" is not valid SQL).
            // so I had to generate the HQL manually, but only after the above
            // check.
            String hql = "from Test t where t.isActive='Y' ORDER BY " + columnName;
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(hql);
            entities = query.list();
            // closeSession(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in getAllOrderBy()", e);
        }

        return entities;
    }

    @Override
    @Transactional(readOnly = true)
    public Test getTestById(String testId) throws LIMSRuntimeException {
        String sql = "From Test t where t.id = :id";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("id", Integer.parseInt(testId));

            Test test = (Test) query.uniqueResult();
            // closeSession(); // CSL remove old
            return test;
        } catch (HibernateException e) {
            handleException(e, "getTestById");
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Test getTestByDescription(String description) {
        String sql = "From Test t where t.description = :description";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("description", description);

            Test test = (Test) query.uniqueResult();
            // closeSession(); // CSL remove old
            return test;
        } catch (HibernateException e) {
            handleException(e, "getTestByDescription");
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Test getTestByGUID(String guid) {
        String sql = "From Test t where t.guid = :guid";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("guid", guid);

            Test test = (Test) query.uniqueResult();
            // closeSession(); // CSL remove old
            return test;
        } catch (HibernateException e) {
            handleException(e, "getTestByGUID");
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Test> getTestsByLoincCode(String loincCode) {
        String sql = "From Test t where t.loinc = :loinc";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("loinc", loincCode);
            List<Test> tests = query.list();
            // closeSession(); // CSL remove old
            return tests;
        } catch (HibernateException e) {
            handleException(e, "getTestsByLoincCode");
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Test> getActiveTestsByLoinc(String loincCode) {
        String sql = "From Test t where t.loinc = :loinc and t.isActive='Y'";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("loinc", loincCode);
            List<Test> tests = query.list();
            // closeSession(); // CSL remove old
            return tests;
        } catch (HibernateException e) {
            handleException(e, "getActiveTestsByLoinc");
        }

        return null;
    }

    @Override
    public List<Test> getActiveTestsByLoinc(String[] loincCodes) {
        String sql = "From Test t where t.loinc IN (:loinc) and t.isActive='Y'";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameterList("loinc", loincCodes);
            List<Test> tests = query.list();
            // closeSession(); // CSL remove old
            return tests;
        } catch (HibernateException e) {
            handleException(e, "getActiveTestsByLoinc");
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Test> getAllTestsByDictionaryResult() {
        String sql = "From Test t where t.id in (select tr.test from TestResult tr where tr.testResultType in ('D','M','C')) ORDER BY t.description asc";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            List<Test> tests = query.list();
            // closeSession(); // CSL remove old
            return tests;
        } catch (HibernateException e) {
            handleException(e, "getAllTestsByDictionaryResult");
        }
        return null;
    }
}