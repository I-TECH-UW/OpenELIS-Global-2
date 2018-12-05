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
package us.mn.state.health.lims.project.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.project.dao.ProjectDAO;
import us.mn.state.health.lims.project.valueholder.Project;

/**
 * @author diane benz
 */
public class ProjectDAOImpl extends BaseDAOImpl implements ProjectDAO {

	public void deleteData(List projects) throws LIMSRuntimeException {
		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < projects.size(); i++) {
				Project data = (Project)projects.get(i);
			
				Project oldData = (Project)readProject(data.getId());
				Project newData = new Project();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "PROJECT";
				auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
			}
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ProjectDAOImpl","AuditTrail deleteData()",e.toString()); 
			throw new LIMSRuntimeException("Error in Project AuditTrail deleteData()", e);
		}  
		
		try {
			for (int i = 0; i < projects.size(); i++) {
				Project data = (Project) projects.get(i);
				Project cloneData = (Project) readProject(data.getId());

				// Make the change to the object.
				cloneData.setIsActive(IActionConstants.NO);
				HibernateUtil.getSession().merge(cloneData);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
				HibernateUtil.getSession().evict(cloneData);
				HibernateUtil.getSession().refresh(cloneData);
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ProjectDAOImpl","deleteData()",e.toString()); 
			throw new LIMSRuntimeException("Error in Project deleteData()", e);
		}
	}

	public boolean insertData(Project project) throws LIMSRuntimeException {

		try {
			// bugzilla 1482 throw Exception if active record already exists
			if (duplicateProjectExists(project)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for " + project.getProjectName());
			}
			
			String id = (String) HibernateUtil.getSession().save(project);
			project.setId(id);
			
			//bugzilla 1824 inserts will be logged in history table
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = project.getSysUserId();
			String tableName = "PROJECT";
			auditDAO.saveNewHistory(project,sysUserId,tableName);
			
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ProjectDAOImpl","insertData()",e.toString()); 
			throw new LIMSRuntimeException("Error in Project insertData()", e);
		}

		return true;
	}

	public void updateData(Project project) throws LIMSRuntimeException {
		// bugzilla 1482 throw Exception if active record already exists
		try {
			if (duplicateProjectExists(project)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for " + project.getProjectName());
			}
		} catch (Exception e) {
    		//bugzilla 2154
			LogEvent.logError("ProjectDAOImpl","updateData()",e.toString()); 
			throw new LIMSRuntimeException("Error in Project updateData()", e);
		}
		
		Project oldData = (Project)readProject(project.getId());
		Project newData = project;

		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = project.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "PROJECT";
			auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ProjectDAOImpl","AuditTrail updateData()",e.toString()); 
			throw new LIMSRuntimeException("Error in Project AuditTrail updateData()", e);
		}  
			
		try {
			HibernateUtil.getSession().merge(project);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(project);
			HibernateUtil.getSession().refresh(project);
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ProjectDAOImpl","updateData()",e.toString()); 
			throw new LIMSRuntimeException("Error in Project updateData()", e);
		}
	}

	public void getData(Project project) throws LIMSRuntimeException {
		try {
			Project proj = (Project) HibernateUtil.getSession().get(
					Project.class, project.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			
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
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ProjectDAOImpl","getData()",e.toString()); 
			throw new LIMSRuntimeException("Error in Project getData()", e);
		}
	}

	public List getAllProjects() throws LIMSRuntimeException {
		List list = new Vector();
		try {
			String sql = "from Project";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ProjectDAOImpl","getAllProjects()",e.toString()); 
			throw new LIMSRuntimeException("Error in Project getAllProjects()",
					e);
		}

		return list;
	}

	public List getPageOfProjects(int startingRecNo)
			throws LIMSRuntimeException {
		List list = new Vector();
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo
					+ (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			//bugzilla 1399
			//bugzilla 2438 order by local abbreviation
			String sql = "from Project p order by p.localAbbreviation desc";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ProjectDAOImpl","getPageOfProjects()",e.toString()); 
			throw new LIMSRuntimeException(
					"Error in Project getPageOfProjects()", e);
		}

		return list;
	}

	public Project readProject(String idString) {
		Project pro = null;
		try {
			pro = (Project) HibernateUtil.getSession().get(Project.class,
					idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ProjectDAOImpl","readProject()",e.toString());
			throw new LIMSRuntimeException("Error in Project readProject()", e);
		}

		return pro;

	}

	public List getNextProjectRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "Project", Project.class);

	}

	public List getPreviousProjectRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "Project", Project.class);
	}

	

    //bugzilla 1978: added param activeOnly
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
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			if (ignoreCase) {
			    query.setParameter("param", project.getProjectName().toLowerCase().trim());
			} else {
				query.setParameter("param", project.getProjectName().trim());	
			}

			List list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			Project pro = null;
			if (list.size() > 0)
				pro = (Project) list.get(0);

			return pro;

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ProjectDAOImpl","getProjectByName()",e.toString());
			throw new LIMSRuntimeException(
					"Error in Project getProjectByName()", e);
		}
	}

	// this is for autocomplete
	//bugzilla 1978: added param activeOnly
	public List getProjects(String filter, boolean activeOnly) throws LIMSRuntimeException {
		try {
			String sql = "";
			if (activeOnly) {
			 sql = "from Project p where upper(p.projectName) like upper(:param) and p.isActive='Y' order by upper(p.projectName)";
			} else {
			 sql = "from Project p where upper(p.projectName) like upper(:param) order by upper(p.projectName)";
			}
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setParameter("param", filter + "%");

			List list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			return list;

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ProjectDAOImpl","getProjects()",e.toString());
			throw new LIMSRuntimeException(
					"Error in Project getProjects(String filter)", e);
		}
	}
	
	//bugzilla 1411
	public Integer getTotalProjectCount() throws LIMSRuntimeException {
		return getTotalCount("Project", Project.class);
	}
	
	//overriding BaseDAOImpl bugzilla 1427 pass in name not id
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {	
				
		List list = new Vector();
		try {			
			String sql = "from "+table+" t where name >= "+ enquote(id) + " order by t.projectName";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(1);
			query.setMaxResults(2); 	
			
			list = query.list();		
			
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ProjectDAOImpl","getNextRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
		}
		
		return list;		
	}

	//overriding BaseDAOImpl bugzilla 1427 pass in name not id
	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {		
		
		List list = new Vector();
		try {			
			String sql = "from "+table+" t order by t.projectName desc where name <= "+ enquote(id);
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(1);
			query.setMaxResults(2); 	
			
			list = query.list();					
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ProjectDAOImpl","getPreviousRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		} 

		return list;
	}	
	
	//bugzilla 1482
	private boolean duplicateProjectExists(Project project) throws LIMSRuntimeException {
		try {
			
			List list = new ArrayList();
			
			// not case sensitive hemolysis and Hemolysis are considered
			// duplicates

			//bugzilla 2438 adding local abbreviation to duplicate check
			String sql = "from Project t where ((trim(lower(t.projectName)) = :param and t.id != :param2) or (trim(lower(t.localAbbreviation)) = :param3 and t.id != :param2))";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setParameter("param", project.getProjectName().toLowerCase().trim());
			query.setParameter("param3", project.getLocalAbbreviation().toLowerCase().trim());

			//initialize with 0 (for new records where no id has been generated yet
			String projId = "0";
			if (!StringUtil.isNullorNill(project.getId())) {
				projId = project.getId();
			}
			query.setParameter("param2", projId);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		
						
			if (list.size() > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ProjectDAOImpl","duplicateProjectExists()",e.toString());
			throw new LIMSRuntimeException("Error in duplicateProjectExists()", e);
		}
	}
	
    //bugzilla 2438
	public Project getProjectByLocalAbbreviation(Project project, boolean activeOnly)
			throws LIMSRuntimeException {
		try {
			String sql = null;
			if (activeOnly) {
					sql = "from Project p where trim(lower(p.localAbbreviation)) = :param and p.isActive='Y'";
			} else {
					sql = "from Project p where trim(lower(p.localAbbreviation)) = :param";
			}
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setParameter("param", project.getLocalAbbreviation().toLowerCase().trim());
            List list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			Project pro = null;
			if (list.size() > 0)
				pro = (Project) list.get(0);

			return pro;

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ProjectDAOImpl","getProjectByLocalAbbreviation()",e.toString());
			throw new LIMSRuntimeException(
					"Error in Project getProjectByLocalAbbreviation()", e);
		}
	}
	
    public Project getProjectById(String id) throws LIMSRuntimeException {
        if( !GenericValidator.isBlankOrNull(id)){
            try{
                Query query = HibernateUtil.getSession().createQuery("from Project p where p.id = :id");
                query.setInteger("id", Integer.parseInt(id));
                Project project = (Project)query.uniqueResult();


                return project;
            }catch(HibernateException e){
                handleException(e, "getProjectById");
            } finally {
                closeSession();
            }
        }
        return null;
    }

}