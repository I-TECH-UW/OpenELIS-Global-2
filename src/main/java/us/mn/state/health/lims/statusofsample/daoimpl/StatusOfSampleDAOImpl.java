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
package us.mn.state.health.lims.statusofsample.daoimpl;

import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Query;

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
import us.mn.state.health.lims.statusofsample.dao.StatusOfSampleDAO;
import us.mn.state.health.lims.statusofsample.valueholder.StatusOfSample;

/**
 * @author bill mcgough
 */
public class StatusOfSampleDAOImpl extends BaseDAOImpl implements StatusOfSampleDAO {	

	public StatusOfSampleDAOImpl() {}	
	
	//bugzilla 1942
	public StatusOfSample getDataByStatusTypeAndStatusCode(StatusOfSample statusofsample)
		throws LIMSRuntimeException{
		
		try {
			//AIS - bugzilla 1546 - Used Upper
			String sql = "from StatusOfSample ss where UPPER(ss.statusType) = UPPER(:param) and ss.code = :param2";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", statusofsample.getStatusType());
			query.setParameter("param2", statusofsample.getCode());			
			List list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();	
		
			StatusOfSample statusOfSamp = null;
		
			if ( list.size()> 0 )
				statusOfSamp = (StatusOfSample)list.get(0);									
						
			return statusOfSamp;  

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("StatusOfSampleDAOImpl","getDataByStatusTypeAndStatusCode()",e.toString());	
			throw new LIMSRuntimeException("Error in StatusOfSample getDataByStatusTypeAndStatusCode()", e);
		}
	}
	
	/**
	 * insertData()
	 * 
	 * @param statusOfSample
	 * @return boolean
	 * @throws LIMSRuntimeException
	 */
	public boolean insertData(StatusOfSample statusOfSample) throws LIMSRuntimeException {

		try {
			// bugzilla 1482 throw Exception if record already exists
			if (duplicateStatusOfSampleExists(statusOfSample)) {	
				StringBuffer sb = new StringBuffer();
				sb.append("Duplicate record exists for Description: ");
				sb.append(statusOfSample.getDescription());
				sb.append(" Status Type: ");
				sb.append(statusOfSample.getStatusType());
				//bugzilla 2154
			    LogEvent.logError("StatusOfSample","insertData()",sb.toString());
				throw new LIMSDuplicateRecordException(sb.toString());
			}
						
			String id = (String) HibernateUtil.getSession().save(statusOfSample);
			
			statusOfSample.setId(id);	
			
			//bugzilla 1824 inserts will be logged in history table
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = statusOfSample.getSysUserId();
			String tableName = "STATUS_OF_SAMPLE";
			auditDAO.saveNewHistory(statusOfSample,sysUserId,tableName);
			
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("StatusOfSampleDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException(
					"Error in StatusOfSample insertData()", e);
		}
		
		return true;
	}
	
	/**
	 * udpateData()
	 * 
	 * @param statusOfSample
	 * @throws LIMSRuntimeException
	 */
	public void updateData(StatusOfSample statusOfSample) throws LIMSRuntimeException {
	
		try {
			if (duplicateStatusOfSampleExists(statusOfSample)) {	
				StringBuffer sb = new StringBuffer();
				sb.append("Duplicate record exists for Description: ");
				sb.append(statusOfSample.getDescription());
				sb.append(" Status Type: ");
				sb.append(statusOfSample.getStatusType());
				//bugzilla 2154
			    LogEvent.logError("StatusOfSample","updateData()",sb.toString());
				throw new LIMSDuplicateRecordException(sb.toString());
			}
		} catch (Exception e) {	
			//bugzilla 2154
			LogEvent.logError("StatusOfSampleDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in StatusOfSample updateData()",
					e);
		}
		
		StatusOfSample oldData = (StatusOfSample) readStatusOfSample(statusOfSample.getId());
		StatusOfSample newData = statusOfSample;
	
		// add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = statusOfSample.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "STATUS_OF_SAMPLE";
			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("StatusOfSampleDAOImpl","AuditTrail updateData()",e.toString());
			throw new LIMSRuntimeException(
					"Error in StatusOfSample AuditTrail updateData()", e);
		}
		
		try {
			HibernateUtil.getSession().merge(statusOfSample);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(statusOfSample);
			HibernateUtil.getSession().refresh(statusOfSample);
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("StatusOfSampleDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException(
					"Error in StatusOfSample updateData()", e);
		}
	}
	
	/**
	 * getData()
	 * 
	 * @param statusOfSample
	 * @throws LIMSRuntimeException
	 */
	public void getData(StatusOfSample statusOfSample) throws LIMSRuntimeException {
	
		try {
			StatusOfSample sos = (StatusOfSample) HibernateUtil.getSession()
					.get(StatusOfSample.class, statusOfSample.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (sos != null) {
				PropertyUtils.copyProperties(statusOfSample, sos);
			} else {
				statusOfSample.setId(null);
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("StatusOfSampleDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in StatusOfSample getData()",
					e);
		}
	}
	
	
	/**
	 * getAllStatusOfSamples()
	 * 
	 * @return List
	 * @throws LIMSRuntimeException
	 */
	public List getAllStatusOfSamples() throws LIMSRuntimeException {
		
		List list = new Vector();
		try {
			String sql = "from StatusOfSample sos order by sos.statusOfSampleName ";
			Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("StatusOfSampleDAOImpl","getAllStatusOfSamples()",e.toString());
			throw new LIMSRuntimeException(
					"Error in StatusOfSample getAllStatusOfSamples()", e);
		}
		
		return list;
				
	}
	
	
	/**
	 * getPageOfStatusOfSamples()
	 * 
	 * @param startingRecNo
	 * @return List
	 * @throws LIMSRuntimeException
	 */
	public List getPageOfStatusOfSamples(int startingRecNo) throws LIMSRuntimeException {
		
		List list = new Vector();
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo
					+ (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
		
			// bugzilla 1399
			String sql = "from StatusOfSample s order by s.statusType, s.code";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);
		
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("StatusOfSampleDAOImpl","getPageOfStatusOfSamples()",e.toString());
			throw new LIMSRuntimeException(
					"Error in StatusOfSample getPageOfStatusOfSamples()", e);
		}
		
		
		return list;
	}
	
	
	/**
	 * readStatusOfSample()
	 * 
	 * @param idString
	 * @return StatusOfSample
	 */
	public StatusOfSample readStatusOfSample(String idString) {
		
		StatusOfSample sos = null;
		try {
			sos = (StatusOfSample) HibernateUtil.getSession().get(
					StatusOfSample.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("StatusOfSampleDAOImpl","readStatusOfSample()",e.toString());		
			throw new LIMSRuntimeException(
					"Error in StatusOfSample readStatusOfSample()", e);
		}
		
		return sos;
	}
	
	
    //bugzilla 1761 removed getStatus() - no longer needed
    
	/**
	 * getNextStatusOfSampleRecord()
	 * 
	 * @param id
	 * @return List
	 * @throws LIMSRuntimeException
	 */
	public List getNextStatusOfSampleRecord(String id) throws LIMSRuntimeException {
			
		return getNextRecord(id, "StatusOfSample", StatusOfSample.class);
	
	}
	
	/**
	 * getPreviousStatusOfSampleRecord()
	 * 
	 * @param id
	 * @return List
	 * @throws LIMSRuntimeException
	 */
	public List getPreviousStatusOfSampleRecord(String id) 	throws LIMSRuntimeException {
	
		return getPreviousRecord(id, "StatusOfSample", StatusOfSample.class);
	}
	
	
	/**
	 * getTotalStatusOfSampleCount()
	 * 
	 * @return Integer - total count
	 * 
	 */
	public Integer getTotalStatusOfSampleCount() throws LIMSRuntimeException {
		return getTotalCount("StatusOfSample", StatusOfSample.class);
	}
	
	
	/**
	 * getNextRecord()
	 * 
	 * @param id
	 * @param table
	 * @param clazz
	 * 
	 * @return List
	 */
	public List getNextRecord(String id, String table, Class clazz)
		throws LIMSRuntimeException {
				
		int currentId = (Integer.valueOf(id)).intValue();
		String tablePrefix = getTablePrefix(table);
		
		List list = new Vector();
		 		
		//bugzilla 1908
		int rrn = 0;
		try {
			//bugzilla 1908 cannot use named query for postgres because of oracle ROWNUM
			//instead get the list in this sortorder and determine the index of record with id = currentId
    		String sql = "select sos.id from StatusOfSample sos " +
					" order by sos.statusType, sos.code";
 	
 			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			rrn = list.indexOf(String.valueOf(currentId));

			list = HibernateUtil.getSession().getNamedQuery(
					tablePrefix + "getNext").setFirstResult(
					rrn + 1).setMaxResults(2).list();

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("StatusOfSampleDAOImpl","getNextRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getNextRecord() for "
					+ table, e);
		}
		
		return list;
	}
	
	
	/**
	 * getPreviousRecord()
	 * 
	 * @param id
	 * @param table
	 * @param clazz
	 * 
	 * @return List
	 * 
	 */
	public List getPreviousRecord(String id, String table, Class clazz)
		throws LIMSRuntimeException {
		
		int currentId = (Integer.valueOf(id)).intValue();
		String tablePrefix = getTablePrefix(table);
		
		List list = new Vector();
		int rrn = 0;
		try {
			//bugzilla 1908 cannot use named query for postgres because of oracle ROWNUM
			//instead get the list in this sortorder and determine the index of record with id = currentId
    		String sql = "select sos.id from StatusOfSample sos " +
					" order by sos.statusType desc, sos.code desc";
 	
 			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			rrn = list.indexOf(String.valueOf(currentId));
						
			
			list = HibernateUtil.getSession().getNamedQuery(
					tablePrefix + "getPrevious").setFirstResult(
					rrn + 1).setMaxResults(2).list();
		
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("StatusOfSampleDAOImpl","getPreviousRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for "
					+ table, e);
		}
		
		return list;
	}
	
	

	
//	 bugzilla 1482
	/**
	 * duplicateStatusOfSampleExists() - checks for duplicate description & status type
	 * 
	 * @param statusOfSample
	 * @return boolean
	 * 
	 */
	private boolean duplicateStatusOfSampleExists(StatusOfSample statusOfSample) throws LIMSRuntimeException {
		try {

			List list = new Vector();

			// not case sensitive hemolysis and Hemolysis are considered
			// duplicates
			String sql = "from StatusOfSample t where trim(lower(t.code)) = :param and trim(lower(t.statusType)) = :param2 and t.id != :param3";
			Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setParameter("param", statusOfSample.getCode().toLowerCase().trim());
			query.setParameter("param2", statusOfSample.getStatusType().toLowerCase().trim());

	
			// initialize with 0 (for new records where no id has been generated
			// yet
			String statusOfSampleId = "0";
			if (!StringUtil.isNullorNill(statusOfSample.getId())) {
				statusOfSampleId = statusOfSample.getId();
			}
			query.setParameter("param3", statusOfSampleId);

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
			LogEvent.logError("StatusOfSample","duplicateStatusOfSampleExists()",e.toString());
			throw new LIMSRuntimeException(
					"Error in duplicateStatusOfSampleExists()", e);
		}
	}
	
}//end of class