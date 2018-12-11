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
package us.mn.state.health.lims.referencetables.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
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
import us.mn.state.health.lims.referencetables.dao.ReferenceTablesDAO;
import us.mn.state.health.lims.referencetables.valueholder.ReferenceTables;


/**
 * @author Yi Chen
 */
public class ReferenceTablesDAOImpl extends BaseDAOImpl implements ReferenceTablesDAO {

	public void deleteData(List referenceTableses) throws LIMSRuntimeException {
		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < referenceTableses.size(); i++) {
			
			    ReferenceTables data = (ReferenceTables)referenceTableses.get(i);
			
				ReferenceTables oldData = (ReferenceTables)readReferenceTables(data.getId());
				ReferenceTables newData = new ReferenceTables();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "REFERENCE_TABLES";
				auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
			}
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ReferenceTablesDAOImpl","AuditTrail deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in ReferenceTables AuditTrail deleteData()", e);
		}  
		
		try {					
			for (int i = 0; i < referenceTableses.size(); i++) {
				
				ReferenceTables data = (ReferenceTables) referenceTableses.get(i);
				//bugzilla 2206
				data = (ReferenceTables)readReferenceTables(data.getId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}						
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ReferenceTablesDAOImpl","deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in ReferenceTables deleteData()", e);
		}
	}

	public boolean insertData(ReferenceTables referenceTables) throws LIMSRuntimeException {	
		
		//String isHl7Encoded;
		//String keepHistory;
		boolean isNew = true;
		
		/*isHl7Encoded = referencetables.getIsHl7Encoded();
		if (StringUtil.isNullorNill(isHl7Encoded) || "0".equals(isHl7Encoded)) {
			referencetables.setIsHl7Encoded ("N");
		}
		
		keepHistory = referencetables.getKeepHistory();
		if (StringUtil.isNullorNill(keepHistory) || "0".equals(keepHistory)) {
			referencetables.setKeepHistory ("N");
		}*/
			
		try {
			// bugzilla 1482 throw Exception if record already exists
			if (duplicateReferenceTablesExists(referenceTables, isNew)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for "
								+ referenceTables.getTableName());
			}
			
			
			//System.out.println("This is ID from insert referencetables " + referencetables.getId());
			String id = (String)HibernateUtil.getSession().save(referenceTables);
			referenceTables.setId(id);
			
			//bugzilla 1824 inserts will be logged in history table
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = referenceTables.getSysUserId();
			String tableName = "REFERENCE_TABLES";
			auditDAO.saveNewHistory(referenceTables,sysUserId,tableName);
			
			HibernateUtil.getSession().flush();	
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ReferenceTablesDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in Referencetables insertData()", e);
		} 
		
		return true;
	}

	public void updateData(ReferenceTables referenceTables) throws LIMSRuntimeException {
		// bugzilla 1482 throw Exception if record already exists
		
		//String isHl7Encoded;
		//String keepHistory;
		boolean isNew = false;
		
		
		
		/*isHl7Encoded = referencetables.getIsHl7Encoded();
		System.out.println ("Yi isH17Encodded is " + isHl7Encoded);
		if (StringUtil.isNullorNill(isHl7Encoded) || "0".equals(isHl7Encoded)) {
			referencetables.setIsHl7Encoded ("N");
		}
		
		keepHistory = referencetables.getKeepHistory();
		System.out.println ("Yi isH17Encodded is " + keepHistory);
		if (StringUtil.isNullorNill(keepHistory) || "0".equals(keepHistory)) {
			referencetables.setKeepHistory ("N");
		}*/
				
		
		try {
			if (duplicateReferenceTablesExists(referenceTables, isNew)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for "
								+ referenceTables.getTableName());
			}
		} catch (Exception e) {
    		//bugzilla 2154
			LogEvent.logError("ReferenceTablesDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in Referencetables updateData()",
					e);
		}
     
		//System.out.println("This is name from updateData " + referencetables.getTableName());
		ReferenceTables oldData = (ReferenceTables)readReferenceTables(referenceTables.getId());
		ReferenceTables newData = referenceTables;
		
		

		//System.out.println("updateDate " + newData.getTableName() + " " + oldData.getTableName());
		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = referenceTables.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "REFERENCE_TABLES";
			auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ReferenceTablesDAOImpl","AuditTrail updateData()",e.toString());
			throw new LIMSRuntimeException("Error in Referencetables AuditTrail updateData()", e);
		}  
		
		try {
			HibernateUtil.getSession().merge(referenceTables);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(referenceTables);
			HibernateUtil.getSession().refresh(referenceTables);
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ReferenceTablesDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in Referencetables updateData()", e);
		} 
	}

	public void getData(ReferenceTables referenceTables) throws LIMSRuntimeException {	
		try {					
			ReferenceTables reftbl = (ReferenceTables)HibernateUtil.getSession().get(ReferenceTables.class, referenceTables.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (reftbl != null) {
			  PropertyUtils.copyProperties(referenceTables, reftbl);
			} else {
				referenceTables.setId(null);
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ReferenceTablesDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in Referencetables getData()", e);
		}
	}

	public List getAllReferenceTables() throws LIMSRuntimeException {		
		List list = new Vector();
		try {
			String sql = "from ReferenceTables";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			//query.setMaxResults(10);
			//query.setFirstResult(3);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch(Exception e) {
			//bugzilla 2154
			LogEvent.logError("ReferenceTablesDAOImpl","getAllReferenceTables()",e.toString());
			throw new LIMSRuntimeException("Error in Referencetables getAllReferenceTables()", e);
		} 
		
		return list;
	}

	public List getPageOfReferenceTables(int startingRecNo) throws LIMSRuntimeException {		
		List list = new Vector();
		try {			
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
			
			
			String sql = "from ReferenceTables r order by r.tableName";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(startingRecNo-1);
			query.setMaxResults(endingRecNo-1); 
			//query.setCacheMode(org.hibernate.CacheMode.REFRESH);
			
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ReferenceTablesDAOImpl","getPageOfReferenceTables()",e.toString());
			throw new LIMSRuntimeException("Error in Referencetables getPageOfReferenceTables()", e);
		} 
		
		return list;
	}

	public ReferenceTables readReferenceTables(String idString) {		
		ReferenceTables referenceTables = null;
		try {			
			referenceTables = (ReferenceTables)HibernateUtil.getSession().get(ReferenceTables.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ReferenceTablesDAOImpl","readReferenceTables()",e.toString());
			throw new LIMSRuntimeException("Error in Referencetables readReferenceTables(idString)", e);
		}			
	
		return referenceTables;
		
	}
	
	public List getNextReferenceTablesRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "ReferenceTables", ReferenceTables.class);
	}

	public List getPreviousReferenceTablesRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "ReferenceTables", ReferenceTables.class);
	}
	
	//bugzilla 1411
	public Integer getTotalReferenceTablesCount() throws LIMSRuntimeException {
		return getTotalCount("ReferenceTables", ReferenceTables.class);
	}
	
//	bugzilla 1427
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {	
		int currentId= (Integer.valueOf(id)).intValue();
		String tablePrefix = getTablePrefix(table);
		
		List list = new Vector();
		//bugzilla 1908
		int rrn = 0;
		try {
			//bugzilla 1908 cannot use named query for postgres because of oracle ROWNUM
			//instead get the list in this sortorder and determine the index of record with id = currentId
			String sql = "select r.id from ReferenceTables r order by r.tableName";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			rrn = list.indexOf(String.valueOf(currentId));

			list = HibernateUtil.getSession().getNamedQuery(tablePrefix + "getNext")
			.setFirstResult(rrn + 1)
			.setMaxResults(2)
			.list(); 		
			
							
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ReferenceTablesDAOImpl","getPreviousRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		} 

		return list;		
	}

	//bugzilla 1427
	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {		
		int currentId= (Integer.valueOf(id)).intValue();
		String tablePrefix = getTablePrefix(table);
		
		List list = new Vector();
		//bugzilla 1908
		int rrn = 0;
		try {	
			//bugzilla 1908 cannot use named query for postgres because of oracle ROWNUM
			//instead get the list in this sortorder and determine the index of record with id = currentId
			String sql = "select r.id from ReferenceTables r order by r.tableName";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			rrn = list.indexOf(String.valueOf(currentId));
			
			list = HibernateUtil.getSession().getNamedQuery(tablePrefix + "getPrevious")
			.setFirstResult(rrn - 1)
			.setMaxResults(2)
			.list(); 		
			
							
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ReferenceTablesDAOImpl","getPreviousRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		} 

		return list;
	}
	
	// bugzilla 1482
	private boolean duplicateReferenceTablesExists(ReferenceTables referenceTables, boolean isNew) throws LIMSRuntimeException {
		try {

			List list = new ArrayList();
			String sql;

			// not case sensitive hemolysis and Hemolysis are considered
			// duplicates
			if (isNew) 
			   { 
			      sql = "from ReferenceTables t where trim(lower(t.tableName)) = :param";
			   }
			else
			   {  
				  sql = "from ReferenceTables t where trim(lower(t.tableName)) = :param and id != :param2";
			   }
			
			
			//System.out.println("Yi in duplicateReferencetables sql is " + sql); 
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			//System.out.println("duplicateReferencetables sql is " + sql); 
			
			query.setParameter("param", referenceTables.getTableName().toLowerCase().trim());
	
			// initialize with 0 (for new records where no id has been generated
			// yet
			String referenceTablesId = "0";
			if (!StringUtil.isNullorNill(referenceTables.getId())) {
				referenceTablesId = referenceTables.getId();
			}
			
			if (!isNew)
			{
			   query.setParameter("param2", referenceTablesId);
			}
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
			LogEvent.logError("ReferenceTablesDAOImpl","duplicateReferenceTablesExists()",e.toString());
			throw new LIMSRuntimeException(
					"Error in duplicateReferenceTablesExists()", e);
		}
	}
	
	//bugzilla 2571 go through ReferenceTablesDAO to get reference tables info
	public List getAllReferenceTablesForHl7Encoding() throws LIMSRuntimeException {
		List list = new Vector();
		try {
			String sql = "from ReferenceTables rt where trim(upper(rt.isHl7Encoded)) = 'Y'";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			// query.setMaxResults(10);
			// query.setFirstResult(3);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//buzilla 2154
			LogEvent.logError("ReferenceTableDAOImpl","getAllReferenceTablesForHl7Encoding()",e.toString());
			throw new LIMSRuntimeException(
					"Error in ReferenceTables getAllReferenceTablesForHl7Encoding()",
					e);
		}

		return list;
	}

	public ReferenceTables getReferenceTableByName(	ReferenceTables referenceTables) throws LIMSRuntimeException {
		return getReferenceTableByName(referenceTables.getTableName());
	}

	public Integer getTotalReferenceTableCount()
			throws LIMSRuntimeException {
		return getTotalCount("ReferenceTables", ReferenceTables.class);
	}

	public ReferenceTables getReferenceTableByName(String tableName) {
		try {
			String sql = "from ReferenceTables rt where trim(lower(rt.tableName)) = :tableName";
			Query query = HibernateUtil.getSession().createQuery(sql);
		    query.setParameter("tableName", tableName.toLowerCase().trim());

			ReferenceTables table = (ReferenceTables)query.setMaxResults(1).uniqueResult();
			
			closeSession();

			return table;

		} catch (HibernateException e) {
			handleException(e, "getReferenceTableByName");
		}

		return null;
	}

}

