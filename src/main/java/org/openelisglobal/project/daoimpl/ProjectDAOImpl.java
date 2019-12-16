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
package org.openelisglobal.project.daoimpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.project.dao.ProjectDAO;
import org.openelisglobal.project.valueholder.Project;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diane benz
 */
@Component
@Transactional
public class ProjectDAOImpl extends BaseDAOImpl<Project, String> implements ProjectDAO {

    public ProjectDAOImpl() {
        super(Project.class);
    }

//	@Override
//	public void deleteData(List projects) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < projects.size(); i++) {
//				Project data = (Project) projects.get(i);
//
//				Project oldData = readProject(data.getId());
//				Project newData = new Project();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "PROJECT";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ProjectDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Project AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < projects.size(); i++) {
//				Project data = (Project) projects.get(i);
//				Project cloneData = readProject(data.getId());
//
//				// Make the change to the object.
//				cloneData.setIsActive(IActionConstants.NO);
//				entityManager.unwrap(Session.class).merge(cloneData);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//				// entityManager.unwrap(Session.class).evict // CSL remove old(cloneData);
//				// entityManager.unwrap(Session.class).refresh // CSL remove old(cloneData);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ProjectDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Project deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(Project project) throws LIMSRuntimeException {
//
//		try {
//			// bugzilla 1482 throw Exception if active record already exists
//			if (duplicateProjectExists(project)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for " + project.getProjectName());
//			}
//
//			String id = (String) entityManager.unwrap(Session.class).save(project);
//			project.setId(id);
//
//			// bugzilla 1824 inserts will be logged in history table
//
//			String sysUserId = project.getSysUserId();
//			String tableName = "PROJECT";
//			auditDAO.saveNewHistory(project, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ProjectDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in Project insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(Project project) throws LIMSRuntimeException {
//		// bugzilla 1482 throw Exception if active record already exists
//		try {
//			if (duplicateProjectExists(project)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for " + project.getProjectName());
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ProjectDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Project updateData()", e);
//		}
//
//		Project oldData = readProject(project.getId());
//		Project newData = project;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = project.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "PROJECT";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ProjectDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Project AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(project);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(project);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(project);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ProjectDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Project updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(Project project) throws LIMSRuntimeException {
        try {
            Project proj = entityManager.unwrap(Session.class).get(Project.class, project.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            if (proj != null) {
                if (proj.getStartedDate() != null) {
                    proj.setStartedDateForDisplay(DateUtil.convertSqlDateToStringDate(proj.getStartedDate()));
                }
                if (proj.getCompletedDate() != null) {
                    proj.setCompletedDateForDisplay(DateUtil.convertSqlDateToStringDate(proj.getCompletedDate()));
                }
                PropertyUtils.copyProperties(project, proj);
            } else {
                project.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Project getData()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Project> getAllProjects() throws LIMSRuntimeException {
        List<Project> list;
        try {
            String sql = "from Project";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Project getAllProjects()", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Project> getPageOfProjects(int startingRecNo) throws LIMSRuntimeException {
        List<Project> list;
        try {
            // calculate maxRow to be one more than the page size
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            // bugzilla 1399
            // bugzilla 2438 order by local abbreviation
            String sql = "from Project p order by p.localAbbreviation desc";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Project getPageOfProjects()", e);
        }

        return list;
    }

    public Project readProject(String idString) {
        Project pro = null;
        try {
            pro = entityManager.unwrap(Session.class).get(Project.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Project readProject()", e);
        }

        return pro;

    }

    // bugzilla 1978: added param activeOnly
    @Override
    @Transactional(readOnly = true)
    public Project getProjectByName(Project project, boolean ignoreCase, boolean activeOnly)
            throws LIMSRuntimeException {
        try {
            String sql = null;
            if (activeOnly) {
                if (ignoreCase) {
                    sql = "from Project p where trim(lower(p.projectName)) = :param and p.isActive='Y'";
                } else {
                    sql = "from Project p where p.projectName = :param and p.isActive='Y'";
                }
            } else {
                if (ignoreCase) {
                    sql = "from Project p where trim(lower(p.projectName)) = :param";
                } else {
                    sql = "from Project p where p.projectName = :param";
                }
            }
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            if (ignoreCase) {
                query.setParameter("param", project.getProjectName().toLowerCase().trim());
            } else {
                query.setParameter("param", project.getProjectName().trim());
            }

            List<Project> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            Project pro = null;
            if (list.size() > 0) {
                pro = list.get(0);
            }

            return pro;

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Project getProjectByName()", e);
        }
    }

    // this is for autocomplete
    // bugzilla 1978: added param activeOnly
    @Override
    @Transactional(readOnly = true)
    public List<Project> getProjects(String filter, boolean activeOnly) throws LIMSRuntimeException {
        try {
            String sql = "";
            if (activeOnly) {
                sql = "from Project p where upper(p.projectName) like upper(:param) and p.isActive='Y' order by upper(p.projectName)";
            } else {
                sql = "from Project p where upper(p.projectName) like upper(:param) order by upper(p.projectName)";
            }
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", filter + "%");

            List<Project> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            return list;

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Project getProjects(String filter)", e);
        }
    }

    // bugzilla 1411
    @Override
    @Transactional(readOnly = true)
    public Integer getTotalProjectCount() throws LIMSRuntimeException {
        return getCount();
    }

    // overriding BaseDAOImpl bugzilla 1427 pass in name not id
    // bugzilla 1482
    @Override
    public boolean duplicateProjectExists(Project project) throws LIMSRuntimeException {
        try {

            List<Project> list = new ArrayList();

            // not case sensitive hemolysis and Hemolysis are considered
            // duplicates

            // bugzilla 2438 adding local abbreviation to duplicate check
            String sql = "from Project t where ((trim(lower(t.projectName)) = :param and t.id != :param2) or (trim(lower(t.localAbbreviation)) = :param3 and t.id != :param2))";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", project.getProjectName().toLowerCase().trim());
            query.setParameter("param3", project.getLocalAbbreviation().toLowerCase().trim());

            // initialize with 0 (for new records where no id has been generated yet
            String projId = "0";
            if (!StringUtil.isNullorNill(project.getId())) {
                projId = project.getId();
            }
            query.setParameter("param2", projId);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            if (list.size() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in duplicateProjectExists()", e);
        }
    }

    // bugzilla 2438
    @Override
    @Transactional(readOnly = true)
    public Project getProjectByLocalAbbreviation(Project project, boolean activeOnly) throws LIMSRuntimeException {
        try {
            String sql = null;
            if (activeOnly) {
                sql = "from Project p where trim(lower(p.localAbbreviation)) = :param and p.isActive='Y'";
            } else {
                sql = "from Project p where trim(lower(p.localAbbreviation)) = :param";
            }
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", project.getLocalAbbreviation().toLowerCase().trim());
            List<Project> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            Project pro = null;
            if (list.size() > 0) {
                pro = list.get(0);
            }

            return pro;

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Project getProjectByLocalAbbreviation()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Project getProjectById(String id) throws LIMSRuntimeException {
        if (!GenericValidator.isBlankOrNull(id)) {
            try {
                Query query = entityManager.unwrap(Session.class).createQuery("from Project p where p.id = :id");
                query.setInteger("id", Integer.parseInt(id));
                Project project = (Project) query.uniqueResult();

                return project;
            } catch (HibernateException e) {
                handleException(e, "getProjectById");
            } finally {
                // closeSession(); // CSL remove old
            }
        }
        return null;
    }

}