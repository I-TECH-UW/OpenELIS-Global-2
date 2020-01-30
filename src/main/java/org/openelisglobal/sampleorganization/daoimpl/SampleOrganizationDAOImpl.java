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
package org.openelisglobal.sampleorganization.daoimpl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.sampleorganization.dao.SampleOrganizationDAO;
import org.openelisglobal.sampleorganization.valueholder.SampleOrganization;
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
public class SampleOrganizationDAOImpl extends BaseDAOImpl<SampleOrganization, String>
        implements SampleOrganizationDAO {

    public SampleOrganizationDAOImpl() {
        super(SampleOrganization.class);
    }

//	@Override
//	public void deleteData(List sampleOrgss) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < sampleOrgss.size(); i++) {
//				SampleOrganization data = (SampleOrganization) sampleOrgss.get(i);
//
//				SampleOrganization oldData = readSampleOrganization(data.getId());
//				SampleOrganization newData = new SampleOrganization();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "SAMPLE_ORGANIZATION";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SampleOrganizationDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in SampleOrganization AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < sampleOrgss.size(); i++) {
//				SampleOrganization data = (SampleOrganization) sampleOrgss.get(i);
//				// bugzilla 2206
//				data = readSampleOrganization(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SampleOrganizationDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in SampleHuman deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(SampleOrganization sampleOrg) throws LIMSRuntimeException {
//
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(sampleOrg);
//			sampleOrg.setId(id);
//
//			// bugzilla 1824 inserts will be logged in history table
//
//			String sysUserId = sampleOrg.getSysUserId();
//			String tableName = "SAMPLE_ORGANIZATION";
//			auditDAO.saveNewHistory(sampleOrg, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SampleOrganizationDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in SampleOrganization insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(SampleOrganization sampleOrg) throws LIMSRuntimeException {
//
//		SampleOrganization oldData = readSampleOrganization(sampleOrg.getId());
//		SampleOrganization newData = sampleOrg;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = sampleOrg.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "SAMPLE_ORGANIZATION";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SampleOrganizationDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SampleOrganization AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(sampleOrg);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(sampleOrg);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(sampleOrg);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SampleOrganizationDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SampleOrganization updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(SampleOrganization sampleOrg) throws LIMSRuntimeException {
        try {
            SampleOrganization data = entityManager.unwrap(Session.class).get(SampleOrganization.class,
                    sampleOrg.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (data != null) {
                PropertyUtils.copyProperties(sampleOrg, data);
            } else {
                sampleOrg.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SampleOrganization getData()", e);
        }
    }

    public SampleOrganization readSampleOrganization(String idString) {
        SampleOrganization so = null;
        try {
            so = entityManager.unwrap(Session.class).get(SampleOrganization.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SampleOrganization readSampleOrganization()", e);
        }

        return so;
    }

    @Override
    @Transactional(readOnly = true)
    public void getDataBySample(SampleOrganization sampleOrganization) throws LIMSRuntimeException {

        try {
            String sql = "from SampleOrganization so where samp_id = :param";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("param", Integer.valueOf(sampleOrganization.getSample().getId()));
            List<SampleOrganization> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            SampleOrganization so = null;
            if (list.size() > 0) {
                so = list.get(0);
                PropertyUtils.copyProperties(sampleOrganization, so);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SampleOrganization getData()", e);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public SampleOrganization getDataBySample(Sample sample) throws LIMSRuntimeException {
        String sql = "From SampleOrganization so where so.sample.id = :sampleId";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("sampleId", Integer.parseInt(sample.getId()));
            List<SampleOrganization> sampleOrg = query.list();
            // closeSession(); // CSL remove old
            // There was a bug that allowed the same sample id / organization id to be
            // entered twice
            return sampleOrg.isEmpty() ? null : sampleOrg.get(0);

        } catch (HibernateException e) {
            handleException(e, "getDataBySample");
        }
        return null;
    }
}