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
package us.mn.state.health.lims.sampleproject.daoimpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Query;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.sampleproject.dao.SampleProjectDAO;
import us.mn.state.health.lims.sampleproject.valueholder.SampleProject;

/**
 *  $Header$
 *
 *  @author         Hung Nguyen
 *  @date created   08/04/2006
 *  @version        $Revision$
 */
public class SampleProjectDAOImpl extends BaseDAOImpl implements SampleProjectDAO {

	public void deleteData(List sampleProjs) throws LIMSRuntimeException {
		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < sampleProjs.size(); i++) {
				SampleProject data = (SampleProject)sampleProjs.get(i);

				SampleProject oldData = (SampleProject)readSampleProject(data.getId());
				SampleProject newData = new SampleProject();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				//bugzilla 2104 change to SAMPLE_PROJECTS instead of SAMPLE_PROJECT
				String tableName = "SAMPLE_PROJECTS";
				auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
			}
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SampleProjectDAOImpl","AuditTrail deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleProject AuditTrail deleteData()", e);
		}

		try {
			for (int i = 0; i < sampleProjs.size(); i++) {
				SampleProject data = (SampleProject) sampleProjs.get(i);
				//bugzilla 2206
				data = (SampleProject)readSampleProject(data.getId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SampleProjectDAOImpl","deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleProject deleteData()", e);
		}
	}

	public boolean insertData(SampleProject sampleProj) throws LIMSRuntimeException {

		try {
			String id = (String)HibernateUtil.getSession().save(sampleProj);
			sampleProj.setId(id);

			//bugzilla 1824 inserts will be logged in history table
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = sampleProj.getSysUserId();
			//bugzilla 2104 change to SAMPLE_PROJECTS instead of SAMPLE_PROJECT
			String tableName = "SAMPLE_PROJECTS";
			auditDAO.saveNewHistory(sampleProj,sysUserId,tableName);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SampleProjectDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleProject insertData()", e);
		}

		return true;
	}

	public void updateData(SampleProject sampleProj) throws LIMSRuntimeException {

		SampleProject oldData = (SampleProject)readSampleProject(sampleProj.getId());
		SampleProject newData = sampleProj;

		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = sampleProj.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			//bugzilla 2104 change to SAMPLE_PROJECTS instead of SAMPLE_PROJECT
			String tableName = "SAMPLE_PROJECTS";
			auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SampleProjectDAOImpl","AuditTrail updateData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleProject AuditTrail updateData()", e);
		}

		try {
			HibernateUtil.getSession().merge(sampleProj);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(sampleProj);
			HibernateUtil.getSession().refresh(sampleProj);
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SampleProjectDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleProject updateData()", e);
		}
	}

	public void getData(SampleProject sampleProj) throws LIMSRuntimeException {
		try {
			SampleProject data = (SampleProject)HibernateUtil.getSession().get(SampleProject.class, sampleProj.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (data != null) {
				PropertyUtils.copyProperties(sampleProj, data);
			} else {
				sampleProj.setId(null);
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SampleProjectDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleProject getData()", e);
		}
	}

	public SampleProject readSampleProject(String idString) {
		SampleProject sp = null;
		try {
			sp = (SampleProject)HibernateUtil.getSession().get(SampleProject.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SampleProjectDAOImpl","readSampleProject()",e.toString());
			throw new LIMSRuntimeException("Error in SampleProject readSampleProject()", e);
		}

		return sp;
	}

	// AIS - bugzilla 1851
	//Diane - bugzilla 1920
	public List getSampleProjectsByProjId(String projId) throws LIMSRuntimeException {
		List sampleProjects = new ArrayList();

		try {
			String sql = "from SampleProject sp where sp.project = :param";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", projId);

			sampleProjects = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			return sampleProjects;


		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SampleProjectDAOImpl","getSampleProjectsByProjId()",e.toString());
			throw new LIMSRuntimeException(
					"Error in SampleProjectDAO getSampleProjectsByProjId()", e);
		}
	}

	@SuppressWarnings("unchecked")
	public SampleProject getSampleProjectBySampleId(String id) throws LIMSRuntimeException {
		List<SampleProject> sampleProjects = null;

		try {
			String sql = "from SampleProject sp where sp.sample.id = :sampleId";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("sampleId", Integer.parseInt(id));

			sampleProjects = query.list();
			closeSession();

		} catch (Exception e) {
			handleException(e, "getSampleProjectBySampleId");
		}

		return sampleProjects.isEmpty() ? null : sampleProjects.get(0);
	}
	
    @SuppressWarnings("unchecked")
    @Override
    public List<SampleProject> getByOrganizationProjectAndReceivedOnRange(String organizationId, String projectName, Date lowReceivedDate, Date highReceivedDate) throws LIMSRuntimeException {
        List<SampleProject> list = null;
        try {          
            String sql = "FROM SampleProject as sp " + 
                " WHERE sp.project.projectName = :projectName AND sp.sample.id IN (SELECT so.sample.id FROM SampleOrganization as so WHERE so.sample.receivedTimestamp >= :dateLow AND so.sample.receivedTimestamp <= :dateHigh " +
                " AND   so.organization.id = :organizationId ) ";
            Query query = HibernateUtil.getSession().createQuery(sql);
            
            query.setString("projectName", projectName);
            query.setDate("dateLow", lowReceivedDate);
            query.setDate("dateHigh", highReceivedDate);
            query.setInteger("organizationId", Integer.valueOf(organizationId));
            list = query.list();
        } catch (Exception e) {
            LogEvent.logError("SampleDAOImpl", "getSamplesByOrganiztionAndReceivedOnRange()", e.toString());
            throw new LIMSRuntimeException("Exception occurred in SampleNumberDAOImpl.getByOrganizationProjectAndReceivedOnRange", e);
        }

        return list;
    }   
	
	
}