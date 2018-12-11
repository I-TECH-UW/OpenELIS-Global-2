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
package us.mn.state.health.lims.samplehuman.daoimpl;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.provider.valueholder.Provider;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.valueholder.SampleHuman;

/**
 * $Header$
 *
 * @author Hung Nguyen
 * @date created 08/04/2006
 * @version $Revision$
 */
public class SampleHumanDAOImpl extends BaseDAOImpl implements SampleHumanDAO {

	public void deleteData(List sampleHumans) throws LIMSRuntimeException {
		// add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < sampleHumans.size(); i++) {
				SampleHuman data = (SampleHuman) sampleHumans.get(i);

				SampleHuman oldData = (SampleHuman) readSampleHuman(data.getId());
				SampleHuman newData = new SampleHuman();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "SAMPLE_HUMAN";
				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("SampleHumanDAOImpl", "AuditTrail deleteData()", e.toString());
			throw new LIMSRuntimeException("Error in SampleHuman AuditTrail deleteData()", e);
		}

		try {
			for (int i = 0; i < sampleHumans.size(); i++) {
				SampleHuman data = (SampleHuman) sampleHumans.get(i);
				// bugzilla 2206
				data = (SampleHuman) readSampleHuman(data.getId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("SampleHumanDAOImpl", "deleteData()", e.toString());
			throw new LIMSRuntimeException("Error in SampleHuman deleteData()", e);
		}
	}

	public boolean insertData(SampleHuman sampleHuman) throws LIMSRuntimeException {

		try {
			String id = (String) HibernateUtil.getSession().save(sampleHuman);
			sampleHuman.setId(id);

			// bugzilla 1824 inserts will be logged in history table
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = sampleHuman.getSysUserId();
			String tableName = "SAMPLE_HUMAN";
			auditDAO.saveNewHistory(sampleHuman, sysUserId, tableName);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("SampleHumanDAOImpl", "insertData()", e.toString());
			throw new LIMSRuntimeException("Error in SampleHuman insertData()", e);
		}

		return true;
	}

	public void updateData(SampleHuman sampleHuman) throws LIMSRuntimeException {

		SampleHuman oldData = readSampleHuman(sampleHuman.getId());
		SampleHuman newData = sampleHuman;

		// add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = sampleHuman.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "SAMPLE_HUMAN";
			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("SampleHumanDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in SampleHuman AuditTrail updateData()", e);
		}

		try {
			HibernateUtil.getSession().merge(sampleHuman);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(sampleHuman);
			HibernateUtil.getSession().refresh(sampleHuman);
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("SampleHumanDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in SampleHuman updateData()", e);
		}
	}

	public void getData(SampleHuman sampleHuman) throws LIMSRuntimeException {
		try {
			SampleHuman sampHuman = (SampleHuman)HibernateUtil.getSession().get(SampleHuman.class, sampleHuman.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (sampHuman != null) {
				PropertyUtils.copyProperties(sampleHuman, sampHuman);
			} else {
				sampleHuman.setId(null);
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("SampleHumanDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in SampleHuman getData()", e);
		}
	}

	public SampleHuman readSampleHuman(String idString) {
		SampleHuman sh = null;
		try {
			sh = (SampleHuman) HibernateUtil.getSession().get(SampleHuman.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("SampleHumanDAOImpl", "readSampleHuman()", e.toString());
			throw new LIMSRuntimeException("Error in SampleHuman readSampleHuman()", e);
		}

		return sh;
	}

	public void getDataBySample(SampleHuman sampleHuman) throws LIMSRuntimeException {

		try {
			String sql = "from SampleHuman sh where samp_id = :param";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("param", Integer.parseInt(sampleHuman.getSampleId()));
			List list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			SampleHuman sh = null;
			if (list.size() > 0) {
				sh = (SampleHuman) list.get(0);
				PropertyUtils.copyProperties(sampleHuman, sh);
			}
		} catch (Exception e) {
			LogEvent.logError("SampleHumanDAOImpl", "getDataBySample()", e.toString());
			throw new LIMSRuntimeException("Error in SampleHuman getDataBySample()", e);
		}

	}

	public Patient getPatientForSample(Sample sample) throws LIMSRuntimeException{
		Patient patient = null;
		try {
			String sql = "select patient from Patient as patient, SampleHuman as sampleHuman where sampleHuman.patientId = patient.id and sampleHuman.sampleId = :sId";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("sId", Integer.parseInt(sample.getId()));
			patient = (Patient)query.uniqueResult();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			
		} catch (HibernateException he) {
			LogEvent.logError("SampleHumanDAOImpl", "getPatientForSample()", he.toString());
			throw new LIMSRuntimeException("Error in SampleHuman getPatientForSample()", he);
		}

		return patient;
	}


	public Provider getProviderForSample(Sample sample) throws LIMSRuntimeException{
		try {
			String sql = "select provider from Provider as provider, SampleHuman as sampleHuman where sampleHuman.providerId = provider.id and sampleHuman.sampleId = :sId";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("sId", Integer.parseInt(sample.getId()));
			Provider provider = (Provider)query.uniqueResult();
			closeSession();

			return provider;
		} catch (HibernateException he) {
			handleException(he, "getProviderForSample");
		}

		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Sample> getSamplesForPatient(String patientID) throws LIMSRuntimeException {

		List<Sample> samples;

		try {
			String sql = "select sample from Sample as sample, SampleHuman as sampleHuman where sampleHuman.sampleId = sample.id and sampleHuman.patientId = :patientId order by sample.id";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("patientId", Integer.parseInt(patientID));
			samples = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (HibernateException he) {
			LogEvent.logError("SampleHumanDAOImpl", "getSamplesForPatient()", he.toString());
			throw new LIMSRuntimeException("Error in SampleHuman getSamplesForPatient()", he);
		}

		return samples;
	}
}