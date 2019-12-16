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
package org.openelisglobal.sampleproject.daoimpl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.sampleproject.dao.SampleProjectDAO;
import org.openelisglobal.sampleproject.valueholder.SampleProject;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * $Header$
 *
 * @author Hung Nguyen
 * @date created 08/04/2006
 * @version $Revision$
 */
@Component
@Transactional
public class SampleProjectDAOImpl extends BaseDAOImpl<SampleProject, String> implements SampleProjectDAO {

    public SampleProjectDAOImpl() {
        super(SampleProject.class);
    }

//	@Override
//	public void deleteData(List sampleProjs) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < sampleProjs.size(); i++) {
//				SampleProject data = (SampleProject) sampleProjs.get(i);
//
//				SampleProject oldData = readSampleProject(data.getId());
//				SampleProject newData = new SampleProject();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				// bugzilla 2104 change to SAMPLE_PROJECTS instead of SAMPLE_PROJECT
//				String tableName = "SAMPLE_PROJECTS";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SampleProjectDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in SampleProject AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < sampleProjs.size(); i++) {
//				SampleProject data = (SampleProject) sampleProjs.get(i);
//				// bugzilla 2206
//				data = readSampleProject(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SampleProjectDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in SampleProject deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(SampleProject sampleProj) throws LIMSRuntimeException {
//
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(sampleProj);
//			sampleProj.setId(id);
//
//			// bugzilla 1824 inserts will be logged in history table
//
//			String sysUserId = sampleProj.getSysUserId();
//			// bugzilla 2104 change to SAMPLE_PROJECTS instead of SAMPLE_PROJECT
//			String tableName = "SAMPLE_PROJECTS";
//			auditDAO.saveNewHistory(sampleProj, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SampleProjectDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in SampleProject insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(SampleProject sampleProj) throws LIMSRuntimeException {
//
//		SampleProject oldData = readSampleProject(sampleProj.getId());
//		SampleProject newData = sampleProj;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = sampleProj.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			// bugzilla 2104 change to SAMPLE_PROJECTS instead of SAMPLE_PROJECT
//			String tableName = "SAMPLE_PROJECTS";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SampleProjectDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SampleProject AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(sampleProj);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(sampleProj);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(sampleProj);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SampleProjectDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SampleProject updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(SampleProject sampleProj) throws LIMSRuntimeException {
        try {
            SampleProject data = entityManager.unwrap(Session.class).get(SampleProject.class, sampleProj.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (data != null) {
                PropertyUtils.copyProperties(sampleProj, data);
            } else {
                sampleProj.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SampleProject getData()", e);
        }
    }

    public SampleProject readSampleProject(String idString) {
        SampleProject sp = null;
        try {
            sp = entityManager.unwrap(Session.class).get(SampleProject.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SampleProject readSampleProject()", e);
        }

        return sp;
    }

    // AIS - bugzilla 1851
    // Diane - bugzilla 1920
    @Override
    @Transactional(readOnly = true)
    public List<SampleProject> getSampleProjectsByProjId(String projId) throws LIMSRuntimeException {
        List<SampleProject> sampleProjects = new ArrayList<>();

        try {
            String sql = "from SampleProject sp where sp.project = :param";
            Query<SampleProject> query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", projId);

            sampleProjects = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            return sampleProjects;

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SampleProjectDAO getSampleProjectsByProjId()", e);
        }
    }

    @Override

    @Transactional(readOnly = true)
    public SampleProject getSampleProjectBySampleId(String id) throws LIMSRuntimeException {
        List<SampleProject> sampleProjects = null;

        try {
            String sql = "from SampleProject sp where sp.sample.id = :sampleId";
            Query<SampleProject> query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("sampleId", Integer.parseInt(id));

            sampleProjects = query.list();
            // closeSession(); // CSL remove old

        } catch (RuntimeException e) {
            handleException(e, "getSampleProjectBySampleId");
        }

        return (sampleProjects == null || sampleProjects.isEmpty()) ? null : sampleProjects.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SampleProject> getByOrganizationProjectAndReceivedOnRange(String organizationId, String projectName,
            Date lowReceivedDate, Date highReceivedDate) throws LIMSRuntimeException {
        List<SampleProject> list = null;
        try {
            String sql = "FROM SampleProject as sp "
                    + " WHERE sp.project.projectName = :projectName AND sp.sample.id IN (SELECT so.sample.id FROM SampleOrganization as so WHERE so.sample.receivedTimestamp >= :dateLow AND so.sample.receivedTimestamp <= :dateHigh "
                    + " AND   so.organization.id = :organizationId ) ";
            Query<SampleProject> query = entityManager.unwrap(Session.class).createQuery(sql);

            query.setString("projectName", projectName);
            query.setDate("dateLow", lowReceivedDate);
            query.setDate("dateHigh", highReceivedDate);
            query.setInteger("organizationId", Integer.valueOf(organizationId));
            list = query.list();
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException(
                    "Exception occurred in SampleNumberDAOImpl.getByOrganizationProjectAndReceivedOnRange", e);
        }

        return list;
    }

}