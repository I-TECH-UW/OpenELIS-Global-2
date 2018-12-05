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
package us.mn.state.health.lims.scriptlet.daoimpl;

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
import us.mn.state.health.lims.scriptlet.dao.ScriptletDAO;
import us.mn.state.health.lims.scriptlet.valueholder.Scriptlet;

/**
 * @author diane benz
 */
public class ScriptletDAOImpl extends BaseDAOImpl implements ScriptletDAO {

	public void deleteData(List scriptlets) throws LIMSRuntimeException {
		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < scriptlets.size(); i++) {
				Scriptlet data = (Scriptlet)scriptlets.get(i);
			
				Scriptlet oldData = (Scriptlet)readScriptlet(data.getId());
				Scriptlet newData = new Scriptlet();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "SCRIPTLET";
				auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
			}
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ScriptletDAOImpl","AuditTrail deleteData()",e.toString());	
			throw new LIMSRuntimeException("Error in Scriptlet AuditTrail deleteData()", e);
		}  
		
		try {		
			for (int i = 0; i < scriptlets.size(); i++) {
				Scriptlet data = (Scriptlet) scriptlets.get(i);
				//bugzilla 2206
				data = (Scriptlet)readScriptlet(data.getId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();			
			}			
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ScriptletDAOImpl","deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in Scriptlet deleteData()", e);
		} 
	}

	public boolean insertData(Scriptlet scriptlet) throws LIMSRuntimeException {
		try {
			// bugzilla 1482 throw Exception if record already exists
			if (duplicateScriptletExists(scriptlet)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for "
								+ scriptlet.getScriptletName());
			}
			
			String id = (String)HibernateUtil.getSession().save(scriptlet);
			scriptlet.setId(id);
			
			//bugzilla 1824 inserts will be logged in history table
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = scriptlet.getSysUserId();
			String tableName = "SCRIPTLET";
			auditDAO.saveNewHistory(scriptlet,sysUserId,tableName);
			
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();							
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ScriptletDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in Scriptlet insertData()", e);
		}
		
		return true;
	}

	public void updateData(Scriptlet scriptlet) throws LIMSRuntimeException {
		// bugzilla 1482 throw Exception if record already exists
		try {
			if (duplicateScriptletExists(scriptlet)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for "
								+ scriptlet.getScriptletName());
			}
		} catch (Exception e) {
    		//bugzilla 2154
			LogEvent.logError("ScriptletDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in Scriptlet updateData()",
					e);
		}
		
		Scriptlet oldData = (Scriptlet)readScriptlet(scriptlet.getId());
		Scriptlet newData = scriptlet;

		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = scriptlet.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "SCRIPTLET";
			auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ScriptletDAOImpl","AuditTrail updateData()",e.toString());
			throw new LIMSRuntimeException("Error in Scriptlet AuditTrail updateData()", e);
		}  
				
		try {
			HibernateUtil.getSession().merge(scriptlet);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(scriptlet);
			HibernateUtil.getSession().refresh(scriptlet);			
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ScriptletDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in Scriptlet updateData()", e);
		}
	}

	public void getData(Scriptlet scriptlet) throws LIMSRuntimeException {
		try {
			Scriptlet sc = (Scriptlet)HibernateUtil.getSession().get(Scriptlet.class, scriptlet.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (sc != null) {
			  PropertyUtils.copyProperties(scriptlet, sc);
			} else {
				scriptlet.setId(null);
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ScriptletDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in Scriptlet getData()", e);
		}
	}

	public List getAllScriptlets() throws LIMSRuntimeException {
		List list = new Vector();
		try {
			String sql = "from Scriptlet";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			//query.setMaxResults(10);
			//query.setFirstResult(3);				
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ScriptletDAOImpl","getAllScriptlets()",e.toString());
			throw new LIMSRuntimeException("Error in Scriptlet getAllScriptlets()", e);
		}

		return list;
	}

	public List getPageOfScriptlets(int startingRecNo) throws LIMSRuntimeException {
		List list = new Vector();
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
			
			//bugzilla 1399
			String sql = "from Scriptlet s order by s.scriptletName";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(startingRecNo-1);
			query.setMaxResults(endingRecNo-1); 
					
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ScriptletDAOImpl","getPageOfScriptlets()",e.toString());
			throw new LIMSRuntimeException("Error in Scriptlet getPageOfScriptlets()", e);
		}

		return list;
	}

	public Scriptlet readScriptlet(String idString) {
		Scriptlet scriptlet = null;
		try {
			scriptlet = (Scriptlet)HibernateUtil.getSession().get(Scriptlet.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ScriptletDAOImpl","readScriptlet()",e.toString());
			throw new LIMSRuntimeException("Error in Scriptlet readScriptlet()", e);
		}			
		
		return scriptlet;
	}

	public List getNextScriptletRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "Scriptlet", Scriptlet.class);

	}

	public List getPreviousScriptletRecord(String id)
			throws LIMSRuntimeException {

		return getPreviousRecord(id, "Scriptlet", Scriptlet.class);
	}

	// this is for autocomplete
	public List getScriptlets(String filter) throws LIMSRuntimeException {
		List list = new Vector(); 
		try {
			String sql = "from Scriptlet s where upper(s.scriptletName) like upper(:param) order by upper(s.scriptletName)";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", filter+"%");	

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ScriptletDAOImpl","getScriptlets()",e.toString());
			throw new LIMSRuntimeException( "Error in Scriptlet getScriptlets(String filter)", e);
		}
		return list;	
	}

	public Scriptlet getScriptletByName(Scriptlet scriptlet) throws LIMSRuntimeException {
		try {
			String sql = "from Scriptlet s where s.scriptletName = :param";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", scriptlet.getScriptletName());

			List list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			Scriptlet s = null;
			if ( list.size() > 0 )
				s = (Scriptlet)list.get(0);
			
			return s;

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ScriptletDAOImpl","getScriptletByName()",e.toString());
			throw new LIMSRuntimeException("Error in Scriptlet getScriptletByName()", e);
		}
	}
	
	//bugzilla 1411
	public Integer getTotalScriptletCount() throws LIMSRuntimeException {
		return getTotalCount("Scriptlet", Scriptlet.class);
	}
	
	//overriding BaseDAOImpl bugzilla 1427 pass in name not id
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {	
				
		List list = new Vector();
		try {			
			String sql = "from "+table+" t where name >= "+ enquote(id) + " order by t.scriptletName";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(1);
			query.setMaxResults(2); 	
			
			list = query.list();		
			
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ScriptletDAOImpl","getNextRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
		}
		
		return list;		
	}

	//overriding BaseDAOImpl bugzilla 1427 pass in name not id
	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {		
		
		List list = new Vector();
		try {			
			String sql = "from "+table+" t order by t.scriptletName desc where name <= "+ enquote(id);
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(1);
			query.setMaxResults(2); 	
			
			list = query.list();					
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ScriptletDAOImpl","getPreviousRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		} 

		return list;
	}
	

	private boolean duplicateScriptletExists(Scriptlet scriptlet) throws LIMSRuntimeException {
		try {

			List list = new ArrayList();

			// not case sensitive hemolysis and Hemolysis are considered
			// duplicates
			String sql = "from Scriptlet t where trim(lower(t.scriptletName)) = :param and t.id != :param2";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setParameter("param", scriptlet.getScriptletName().toLowerCase().trim());
	
			// initialize with 0 (for new records where no id has been generated
			// yet
			String scriptletId = "0";
			if (!StringUtil.isNullorNill(scriptlet.getId())) {
				scriptletId = scriptlet.getId();
			}
			query.setParameter("param2", scriptletId);

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
			LogEvent.logError("ScriptletDAOImpl","duplicateScriptletExists()",e.toString());
			throw new LIMSRuntimeException(
					"Error in duplicateScriptletExists()", e);
		}
	}

	public Scriptlet getScriptletById(String scriptletId)  throws LIMSRuntimeException {
		try {
			Scriptlet scriptlet = (Scriptlet)HibernateUtil.getSession().get(Scriptlet.class, scriptletId);
			closeSession();
			return scriptlet;
		} catch (Exception e) {
			handleException(e, "getScriptletById");
		}
		
		return null;
	}
}