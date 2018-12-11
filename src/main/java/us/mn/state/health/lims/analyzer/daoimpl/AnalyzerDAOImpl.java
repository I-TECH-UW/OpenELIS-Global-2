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
package us.mn.state.health.lims.analyzer.daoimpl;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.analyzer.dao.AnalyzerDAO;
import us.mn.state.health.lims.analyzer.valueholder.Analyzer;
import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;

public class AnalyzerDAOImpl extends BaseDAOImpl implements AnalyzerDAO {


	public void deleteData(List<Analyzer> analyzers) throws LIMSRuntimeException {
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (Analyzer data: analyzers) {
			
				Analyzer oldData = readAnalyzer(data.getId());
				Analyzer newData = new Analyzer();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "ANALYZER";
				auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
			}
				
			for (Analyzer data: analyzers) {

				data = readAnalyzer(data.getId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}			
		} catch (Exception e) {
			LogEvent.logError("AnalyzerDAOImpl","deleteData()",e.toString());
			   throw new LIMSRuntimeException("Error in Analyzer deleteData()", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Analyzer> getAllAnalyzers() throws LIMSRuntimeException {
		List<Analyzer> analyzer;
		try {
			String sql = "from Analyzer";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			analyzer = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("AnalyzerDAOImpl","getAllAnalyzerItems()",e.toString());
			throw new LIMSRuntimeException("Error in Analyzer getAllAnalyzer()", e);
		}

		return analyzer;
	}

	public Analyzer getAnalyzerById(Analyzer analyzer) throws LIMSRuntimeException {
		try {
			Analyzer re = (Analyzer)HibernateUtil.getSession().get(Analyzer.class, analyzer.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			return re;
		} catch (Exception e) {
			LogEvent.logError("AnalyzerDAOImpl","getAnalyzerById()",e.toString());
			throw new LIMSRuntimeException("Error in Analyzer getAnalyzerById()", e);
		}
	}

    @Override
    public Analyzer getAnalyzerByName(String name) throws LIMSRuntimeException {
        String sql = "From Analyzer a where a.name = :name";
        try {
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setString("name", name);
            Analyzer analyzer = (Analyzer)query.uniqueResult();
            closeSession();
            return analyzer;
        }catch ( HibernateException e){
            handleException(e, "getAnalyzerrByName");
        }
        return null;
    }

    public void getData(Analyzer analyzer) throws LIMSRuntimeException {
		try {
			Analyzer tmpAnalyzer = (Analyzer)HibernateUtil.getSession().get(Analyzer.class, analyzer.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (tmpAnalyzer != null) {
				PropertyUtils.copyProperties(analyzer, tmpAnalyzer);
			} else {
				analyzer.setId(null);
			}
		} catch (Exception e) {
			LogEvent.logError("AnalyzerDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in Analyzer getData()", e);
		}
	}

	public boolean insertData(Analyzer analyzer) throws LIMSRuntimeException {
		try {
			String id = (String)HibernateUtil.getSession().save(analyzer);
			analyzer.setId(id);
			
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = analyzer.getSysUserId();
			String tableName = "ANALYZER";
			auditDAO.saveNewHistory(analyzer,sysUserId,tableName);
			
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
							
		} catch (Exception e) {
			LogEvent.logError("analyzerDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in analyzer insertData()", e);
		}
		
		return true;
	}

	public void updateData(Analyzer analyzer) throws LIMSRuntimeException {
		Analyzer oldData = readAnalyzer(analyzer.getId());
		Analyzer newData = analyzer;

		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = analyzer.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "ANALYZER";
			auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
		
			HibernateUtil.getSession().merge(analyzer);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(analyzer);
			HibernateUtil.getSession().refresh(analyzer);
		} catch (Exception e) {
			LogEvent.logError("AnalyzerDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in Analyzer updateData()", e);
		}
	}
	
	public Analyzer readAnalyzer(String idString) throws LIMSRuntimeException{
		Analyzer data = null;
		try {
			data = (Analyzer)HibernateUtil.getSession().get(Analyzer.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("AnalyzerDAOImpl","readAnalyzer()",e.toString());
			throw new LIMSRuntimeException("Error in Analyzer readAnalyzer()", e);
		}		
		
		return data;
	}
}