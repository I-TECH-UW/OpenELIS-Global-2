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
* Contributor(s): ITECH, University of Washington, Seattle WA.
*/
package us.mn.state.health.lims.sampleqaevent.daoimpl;

import java.sql.Date;
import java.util.ArrayList;
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
import us.mn.state.health.lims.sampleqaevent.dao.SampleQaEventDAO;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;

/**
 *  $Header$
 *
 *  @author         Diane Benz
 *  @date created   06/12/2008
 *  @version        $Revision$
 *  bugzilla 2510
 */
public class SampleQaEventDAOImpl extends BaseDAOImpl implements SampleQaEventDAO {

	public void deleteData(List sampleQaEvents) throws LIMSRuntimeException {
		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < sampleQaEvents.size(); i++) {
				SampleQaEvent data = (SampleQaEvent)sampleQaEvents.get(i);

				SampleQaEvent oldData = (SampleQaEvent)readSampleQaEvent(data.getId());
				SampleQaEvent newData = new SampleQaEvent();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "SAMPLE_QAEVENT";
				auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
			}
		}  catch (Exception e) {
			//buzilla 2154
			LogEvent.logError("SampleQaEventDAOImpl","AuditTrail deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleQaEvent AuditTrail deleteData()", e);
		}

		try {
			for (int i = 0; i < sampleQaEvents.size(); i++) {
				SampleQaEvent data = (SampleQaEvent) sampleQaEvents.get(i);
				//bugzilla 2206
				data = (SampleQaEvent)readSampleQaEvent(data.getId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}
		} catch (Exception e) {
			//buzilla 2154
			LogEvent.logError("SampleQaEventDAOImpl","deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleQaEvent deleteData()", e);
		}
	}

	public boolean insertData(SampleQaEvent sampleQaEvent) throws LIMSRuntimeException {

		try {
			String id = (String)HibernateUtil.getSession().save(sampleQaEvent);
			sampleQaEvent.setId(id);

			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = sampleQaEvent.getSysUserId();
			String tableName = "SAMPLE_QAEVENT";
			auditDAO.saveNewHistory(sampleQaEvent,sysUserId,tableName);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		} catch (Exception e) {
			//buzilla 2154
			LogEvent.logError("SampleQaEventDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleQaEvent insertData()", e);
		}

		return true;
	}

	public void updateData(SampleQaEvent sampleQaEvent) throws LIMSRuntimeException {

		SampleQaEvent oldData = (SampleQaEvent)readSampleQaEvent(sampleQaEvent.getId());
		SampleQaEvent newData = sampleQaEvent;

		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = sampleQaEvent.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "SAMPLE_QAEVENT";
			auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
		}  catch (Exception e) {
			//buzilla 2154
			LogEvent.logError("SampleQaEventDAOImpl","AuditTrail insertData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleQaEvent AuditTrail updateData()", e);
		}

		try {
			HibernateUtil.getSession().merge(sampleQaEvent);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(sampleQaEvent);
			HibernateUtil.getSession().refresh(sampleQaEvent);
		} catch (Exception e) {
			//buzilla 2154
			LogEvent.logError("SampleQaEventDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleQaEvent updateData()", e);
		}
	}

	@Override
	public SampleQaEvent getData(String sampleQaEventId) throws LIMSRuntimeException {
		try{
			SampleQaEvent data = (SampleQaEvent)HibernateUtil.getSession().get(SampleQaEvent.class, sampleQaEventId);
			closeSession();
			return data;
		} catch (Exception e) {
			handleException(e, "getData");
		}
			return null;
	}
	
	public void getData(SampleQaEvent sampleQaEvent) throws LIMSRuntimeException {
		try {
			SampleQaEvent data = (SampleQaEvent)HibernateUtil.getSession().get(SampleQaEvent.class, sampleQaEvent.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (data != null) {
				PropertyUtils.copyProperties(sampleQaEvent, data);
			} else {
				sampleQaEvent.setId(null);
			}
		} catch (Exception e) {
			//buzilla 2154
			LogEvent.logError("SampleQaEventDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleQaEvent getData()", e);
		}
	}

	public SampleQaEvent readSampleQaEvent(String idString) {
		SampleQaEvent sp = null;
		try {
			sp = (SampleQaEvent)HibernateUtil.getSession().get(SampleQaEvent.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//buzilla 2154
			LogEvent.logError("SampleQaEventDAOImpl","readSampleQaEvent()",e.toString());
			throw new LIMSRuntimeException("Error in SampleQaEvent readSampleQaEvent()", e);
		}

		return sp;
	}

	public List getSampleQaEventsBySample(SampleQaEvent sampleQaEvent) throws LIMSRuntimeException {
		List sampleQaEvents = new ArrayList();

		try {
			String sql = "from SampleQaEvent aqe where aqe.sample = :param";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", sampleQaEvent.getSample().getId());

			sampleQaEvents = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			return sampleQaEvents;


		} catch (Exception e) {
			//buzilla 2154
			LogEvent.logError("SampleQaEventDAOImpl","getSampleQaEventsBySample(SampleQaEvent)",e.toString());
			throw new LIMSRuntimeException(
					"Error in SampleQaEventDAO getSampleQaEventsBySample(SampleQaEvent)", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<SampleQaEvent> getSampleQaEventsBySample(Sample sample) throws LIMSRuntimeException {
		List<SampleQaEvent> sampleQaEvents;

		try {
			String sql = "from SampleQaEvent aqe where aqe.sample = :sampleId";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("sampleId", Integer.parseInt(sample.getId()));

			sampleQaEvents = query.list();
			closeSession();

			return sampleQaEvents;
		} catch (Exception e) {
			handleException(e, "getSampleQaEventsBySample(Sample sample)");
		}

		return new ArrayList<SampleQaEvent>();
	}

	public SampleQaEvent getSampleQaEventBySampleAndQaEvent(SampleQaEvent sampleQaEvent) throws LIMSRuntimeException {
		SampleQaEvent analQaEvent = null;
		try
		{
			// Use an expression to read in the SampleQaEvent whose
			// sample and qaevent is given
			String sql = "from SampleQaEvent aqe where aqe.sample = :param and aqe.qaEvent = :param2";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", sampleQaEvent.getSample().getId());
			query.setParameter("param2", sampleQaEvent.getQaEvent().getId());
			List list = query.list();
			if ((list != null) &&
				!list.isEmpty())
			{
				analQaEvent = (SampleQaEvent)list.get(0);
			}
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		}
		catch (Exception e)
		{
			//buzilla 2154
			LogEvent.logError("SampleQaEventDAOImpl","getSampleQaEventBySampleAndQaEvent()",e.toString());
			throw new LIMSRuntimeException("Exception occurred in getSampleQaEventBySampleAndQaEvent", e);
		}
		return analQaEvent;


	}

    /**
     * @see us.mn.state.health.lims.sampleqaevent.dao.SampleQaEventDAO#getSampleQaEventsByUpdatedDate(java.sql.Date, java.sql.Date)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<SampleQaEvent> getSampleQaEventsByUpdatedDate(Date lowDate, Date highDate) throws LIMSRuntimeException {
        List<SampleQaEvent> sampleQaEvents = null;

        try {
            String sql = "FROM SampleQaEvent sqe WHERE sqe.lastupdated >= :lowDate AND sqe.lastupdated <= :highDate";
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setDate("lowDate", lowDate);
            query.setDate("highDate", highDate);

            sampleQaEvents = query.list();
            closeSession();
        } catch (Exception e) {
            handleException(e, "getSampleQaEventsByDate");
        }

        return sampleQaEvents;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<SampleQaEvent> getAllUncompleatedEvents() throws LIMSRuntimeException {
		String sql = "From SampleQaEvent sqa where sqa.completedDate is null";
		
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			List<SampleQaEvent> events = query.list();
			closeSession();
			return events;
		}catch( HibernateException e){
			handleException(e, "getAllUncompleatedEvents");
		}
		
		return null;
	}

	
}