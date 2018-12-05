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
package us.mn.state.health.lims.sampleorganization.daoimpl;

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
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleorganization.dao.SampleOrganizationDAO;
import us.mn.state.health.lims.sampleorganization.valueholder.SampleOrganization;

/**
 *  $Header$
 *
 *  @author         Hung Nguyen
 *  @date created   08/04/2006
 *  @version        $Revision$
 */
public class SampleOrganizationDAOImpl extends BaseDAOImpl implements SampleOrganizationDAO {

	public void deleteData(List sampleOrgss) throws LIMSRuntimeException {
		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < sampleOrgss.size(); i++) {
				SampleOrganization data = (SampleOrganization)sampleOrgss.get(i);

				SampleOrganization oldData = (SampleOrganization)readSampleOrganization(data.getId());
				SampleOrganization newData = new SampleOrganization();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "SAMPLE_ORGANIZATION";
				auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
			}
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SampleOrganizationDAOImpl","AuditTrail deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleOrganization AuditTrail deleteData()", e);
		}

		try {
			for (int i = 0; i < sampleOrgss.size(); i++) {
				SampleOrganization data = (SampleOrganization) sampleOrgss.get(i);
				//bugzilla 2206
				data = (SampleOrganization)readSampleOrganization(data.getId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SampleOrganizationDAOImpl","deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleHuman deleteData()", e);
		}
	}

	public boolean insertData(SampleOrganization sampleOrg) throws LIMSRuntimeException {

		try {
			String id = (String)HibernateUtil.getSession().save(sampleOrg);
			sampleOrg.setId(id);

			//bugzilla 1824 inserts will be logged in history table
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = sampleOrg.getSysUserId();
			String tableName = "SAMPLE_ORGANIZATION";
			auditDAO.saveNewHistory(sampleOrg,sysUserId,tableName);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SampleOrganizationDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleOrganization insertData()", e);
		}

		return true;
	}

	public void updateData(SampleOrganization sampleOrg) throws LIMSRuntimeException {

		SampleOrganization oldData = (SampleOrganization)readSampleOrganization(sampleOrg.getId());
		SampleOrganization newData = sampleOrg;

		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = sampleOrg.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "SAMPLE_ORGANIZATION";
			auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SampleOrganizationDAOImpl","AuditTrail updateData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleOrganization AuditTrail updateData()", e);
		}

		try {
			HibernateUtil.getSession().merge(sampleOrg);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(sampleOrg);
			HibernateUtil.getSession().refresh(sampleOrg);
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SampleOrganizationDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleOrganization updateData()", e);
		}
	}

	public void getData(SampleOrganization sampleOrg) throws LIMSRuntimeException {
		try {
			SampleOrganization data = (SampleOrganization)HibernateUtil.getSession().get(SampleOrganization.class, sampleOrg.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (data != null) {
				PropertyUtils.copyProperties(sampleOrg, data);
			} else {
				sampleOrg.setId(null);
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SampleOrganizationDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleOrganization getData()", e);
		}
	}

	public SampleOrganization readSampleOrganization(String idString) {
		SampleOrganization so = null;
		try {
			so = (SampleOrganization)HibernateUtil.getSession().get(SampleOrganization.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SampleOrganizationDAOImpl","readSampleOrganization()",e.toString());
			throw new LIMSRuntimeException("Error in SampleOrganization readSampleOrganization()", e);
		}

		return so;
	}

	public void getDataBySample(SampleOrganization sampleOrganization) throws LIMSRuntimeException {

		try {
		String sql = "from SampleOrganization so where samp_id = :param";
		Query query = HibernateUtil.getSession().createQuery(sql);
		query.setInteger("param", Integer.valueOf(sampleOrganization.getSample().getId()));
		List list = query.list();
		HibernateUtil.getSession().flush();
		HibernateUtil.getSession().clear();
		SampleOrganization so = null;
		if ( list.size()> 0 ) {
			so = (SampleOrganization)list.get(0);
			PropertyUtils.copyProperties(sampleOrganization, so);
		}
		}catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SampleOrganizationDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleOrganization getData()", e);
		}

	}

	@Override
	public SampleOrganization getDataBySample(Sample sample) throws LIMSRuntimeException {
		String sql = "From SampleOrganization so where so.sample.id = :sampleId";
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("sampleId", Integer.parseInt(sample.getId()));
			List<SampleOrganization> sampleOrg = query.list();
			closeSession();
			//There was a bug that allowed the same sample id / organization id to be entered twice
			return sampleOrg.isEmpty() ? null : sampleOrg.get(0);

		}catch(HibernateException e){
			handleException(e, "getDataBySample");
		}
		return null;
	}
}