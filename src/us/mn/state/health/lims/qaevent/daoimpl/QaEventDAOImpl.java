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
package us.mn.state.health.lims.qaevent.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.qaevent.dao.QaEventDAO;
import us.mn.state.health.lims.qaevent.valueholder.QaEvent;

/**
 * @author diane benz
 */
public class QaEventDAOImpl extends BaseDAOImpl implements QaEventDAO {

	public void deleteData(List qaEvents) throws LIMSRuntimeException {
		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < qaEvents.size(); i++) {
				QaEvent data = (QaEvent)qaEvents.get(i);
			
				QaEvent oldData = (QaEvent)readQaEvent(data.getId());
				QaEvent newData = new QaEvent();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "QA_EVENT";
				auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
			}
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("QaEventDAOImpl","AuditTrail deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in QaEvent AuditTrail deleteData()", e);
		}  
		
		try {
			for (int i = 0; i < qaEvents.size(); i++) {
				QaEvent data = (QaEvent) qaEvents.get(i);
				QaEvent cloneData = (QaEvent) readQaEvent(data.getId());

				HibernateUtil.getSession().merge(cloneData);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
				HibernateUtil.getSession().evict(cloneData);
				HibernateUtil.getSession().refresh(cloneData);
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("QaEventDAOImpl","deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in QaEvent deleteData()", e);
		}
	}

	public boolean insertData(QaEvent qaEvent) throws LIMSRuntimeException {

		try {
			if (duplicateQaEventExists(qaEvent)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for " + qaEvent.getQaEventName());
			}
			
			String id = (String) HibernateUtil.getSession().save(qaEvent);
			qaEvent.setId(id);
			
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = qaEvent.getSysUserId();
			String tableName = "QA_EVENT";
			auditDAO.saveNewHistory(qaEvent,sysUserId,tableName);
			
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("QaEventDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in QaEvent insertData()", e);
		}

		return true;
	}

	public void updateData(QaEvent qaEvent) throws LIMSRuntimeException {
		try {
			if (duplicateQaEventExists(qaEvent)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for " + qaEvent.getQaEventName());
			}
		} catch (Exception e) {
    		//bugzilla 2154
			LogEvent.logError("QaEventDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in QaEvent updateData()", e);
		}
		
		QaEvent oldData = (QaEvent)readQaEvent(qaEvent.getId());
		QaEvent newData = qaEvent;

		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = qaEvent.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "QA_EVENT";
			auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("QaEventDAOImpl","AuditTrail updateData()",e.toString());
			throw new LIMSRuntimeException("Error in QaEvent AuditTrail updateData()", e);
		}  
					
		try {
			HibernateUtil.getSession().merge(qaEvent);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(qaEvent);
			HibernateUtil.getSession().refresh(qaEvent);
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("QaEventDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in QaEvent updateData()", e);
		}
	}

	public void getData(QaEvent qaEvent) throws LIMSRuntimeException {
		try {
			QaEvent qaEv = (QaEvent) HibernateUtil.getSession().get(QaEvent.class,
					qaEvent.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (qaEv != null) {
				PropertyUtils.copyProperties(qaEvent, qaEv);

			} else {
				qaEvent.setId(null);
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("QaEventDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in QaEvent getData()", e);
		}
	}

	public List getAllQaEvents() throws LIMSRuntimeException {
		List list;
		try {
			String sql = "from QaEvent qe order by qe.id";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		} catch (Exception e) {
			LogEvent.logError("QaEventDAOImpl","getAllQaEvents()",e.toString());
			throw new LIMSRuntimeException("Error in QaEvent getAllQaEvents()", e);
		}

		return list;
	}

	public List getPageOfQaEvents(int startingRecNo) throws LIMSRuntimeException {
		List list = new Vector();
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo
					+ (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			String sql = "from QaEvent qe order by qe.qaEventName";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("QaEventDAOImpl","getPageOfQaEvents()",e.toString());
			throw new LIMSRuntimeException(
					"Error in QaEvent getPageOfQaEvents()", e);
		}

		return list;
	}

	public QaEvent readQaEvent(String idString) {
		QaEvent qaEvent = null;
		try {
			qaEvent = (QaEvent) HibernateUtil.getSession().get(QaEvent.class,
					idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("QaEventDAOImpl","readQaEvent()",e.toString());
			throw new LIMSRuntimeException("Error in QaEvent readQaEvent()", e);
		}

		return qaEvent;
	}

	public List getNextQaEventRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "QaEvent", QaEvent.class);

	}

	public List getPreviousQaEventRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "QaEvent", QaEvent.class);
	}

	// this is for autocomplete
	public List getQaEvents(String filter) throws LIMSRuntimeException {
		List list = new Vector();
		try {
			String sql = "from QaEvent qe where upper(qe.qaEventName) like upper(:param) order by upper(qe.qaEventName)";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setParameter("param", filter + "%");

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("QaEventDAOImpl","getQaEvents()",e.toString());
			throw new LIMSRuntimeException(
					"Error in QaEvent getQaEvents(String filter)", e);
		}
		return list;

	}

	public QaEvent getQaEventByName(QaEvent qaEvent) throws LIMSRuntimeException {
		try {
			String sql = "from QaEvent qe where qe.qaEventName = :param";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setParameter("param", qaEvent.getQaEventName());

			List list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			QaEvent qe = null;
			if (list.size() > 0)
				qe = (QaEvent) list.get(0);

			return qe;

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("QaEventDAOImpl","getQaEventByName()",e.toString());
			throw new LIMSRuntimeException("Error in QaEvent getQaEventByName()",
					e);
		}
	}
	
	public Integer getTotalQaEventCount() throws LIMSRuntimeException {
		return getTotalCount("QaEvent", QaEvent.class);
	}
	
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {	
				
		List list = new Vector();
		try {			
			String sql = "from "+table+" t where name >= "+ enquote(id) + " order by t.qaEventName";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(1);
			query.setMaxResults(2); 	
			
			list = query.list();		
			
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("QaEventDAOImpl","getNextRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
		}
		
		return list;		
	}

	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {		
		
		List list = new Vector();
		try {			
			String sql = "from "+table+" t order by t.qaEventName desc where name <= "+ enquote(id);
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(1);
			query.setMaxResults(2); 	
			
			list = query.list();					
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("QaEventDAOImpl","getPreviousRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		} 

		return list;
	}
	
	private boolean duplicateQaEventExists(QaEvent qaEvent) throws LIMSRuntimeException {
		try {
			
			List list = new ArrayList();
			
			// not case sensitive hemolysis and Hemolysis are considered
			// duplicates
			String sql = "from QaEvent t where " +
					"((trim(lower(t.qaEventName)) = :param and trim(lower(t.type)) = :param3 and t.id != :param2) " +
					"or " +
                    "(trim(lower(t.description)) = :param4 and trim(lower(t.type)) = :param3 and t.id != :param2)) ";
			
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setParameter("param", qaEvent.getQaEventName().toLowerCase().trim());
			query.setParameter("param3", qaEvent.getType());
			query.setParameter("param4", qaEvent.getDescription().toLowerCase().trim());

			//initialize with 0 (for new records where no id has been generated yet
			String qaEventId = "0";
			if (!StringUtil.isNullorNill(qaEvent.getId())) {
				qaEventId = qaEvent.getId();
			}
			query.setParameter("param2", qaEventId);

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
			LogEvent.logError("QaEventDAOImpl","duplicateQaEventExists()",e.toString());
			throw new LIMSRuntimeException("Error in duplicateQaEventExists()", e);
		}
	}
	
}