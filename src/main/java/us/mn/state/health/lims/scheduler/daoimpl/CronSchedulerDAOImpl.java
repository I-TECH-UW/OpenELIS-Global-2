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
* Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.scheduler.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.scheduler.dao.CronSchedulerDAO;
import us.mn.state.health.lims.scheduler.valueholder.CronScheduler;

public class CronSchedulerDAOImpl extends BaseDAOImpl implements CronSchedulerDAO {

	@Override
	public List<CronScheduler> getAllCronSchedules() throws LIMSRuntimeException {
		String sql = "from CronScheduler";
		
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			@SuppressWarnings("unchecked")
			List<CronScheduler> schedulers = query.list();
			closeSession();
			return schedulers;
 		}catch( HibernateException e){
 			handleException(e, "getAllCronSchedules");
 		}
		
		return null;
	}

	@Override
	public CronScheduler getCronScheduleByJobName(String jobName) throws LIMSRuntimeException {
		String sql = "from CronScheduler cs where cs.jobName = :jobName";
		
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("jobName", jobName);
			CronScheduler scheduler = (CronScheduler)query.uniqueResult();
			closeSession();
			return scheduler;
 		}catch( HibernateException e){
 			handleException(e, "getCronScheduleByJobName");
 		}
		
		return null;	
	}

	@Override
	public void insert(CronScheduler cronSchedule) throws LIMSRuntimeException {
		try {
			String id = (String) HibernateUtil.getSession().save(cronSchedule);
			cronSchedule.setId(id);
			new AuditTrailDAOImpl().saveNewHistory(cronSchedule, cronSchedule.getSysUserId(), "QUARTZ_CRON_SCHEDULER");
			closeSession();
		} catch (HibernateException e) {
			handleException(e, "insert");
		}
	}

	@Override
	public void update(CronScheduler cronSchedule) throws LIMSRuntimeException {
		CronScheduler oldData = readCronScheduler(cronSchedule.getId());

		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();

			auditDAO.saveHistory(cronSchedule, oldData, cronSchedule.getSysUserId(), IActionConstants.AUDIT_TRAIL_UPDATE, "QUARTZ_CRON_SCHEDULER");

			HibernateUtil.getSession().merge(cronSchedule);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(cronSchedule);
			HibernateUtil.getSession().refresh(cronSchedule);
		} catch (Exception e) {
			handleException(e, "update");
		}
	}

	public CronScheduler readCronScheduler(String idString) throws LIMSRuntimeException {

		try {
			CronScheduler data = (CronScheduler) HibernateUtil.getSession().get(CronScheduler.class, idString);
			closeSession();
			return data;
		} catch (HibernateException e) {
			handleException(e, "readCronScheduler");
		}
		return null;
	}

	@Override
	public CronScheduler getCronScheduleById(String schedulerId) throws LIMSRuntimeException {
		String sql = "from CronScheduler cs where cs.id = :id";
		
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("id", Integer.parseInt(schedulerId));
			CronScheduler scheduler = (CronScheduler)query.uniqueResult();
			closeSession();
			return scheduler;
 		}catch( HibernateException e){
 			handleException(e, "getCronScheduleById");
 		}
		
		return null;	
	}
}
