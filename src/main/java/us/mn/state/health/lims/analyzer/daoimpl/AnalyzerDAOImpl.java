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

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.analyzer.dao.AnalyzerDAO;
import us.mn.state.health.lims.analyzer.valueholder.Analyzer;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;

@Component
@Transactional
public class AnalyzerDAOImpl extends BaseDAOImpl<Analyzer, String> implements AnalyzerDAO {

	public AnalyzerDAOImpl() {
		super(Analyzer.class);
	}

//	@Override
//	public void deleteData(List<Analyzer> analyzers) throws LIMSRuntimeException {
//		try {
//
//			for (Analyzer data : analyzers) {
//
//				Analyzer oldData = readAnalyzer(data.getId());
//				Analyzer newData = new Analyzer();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "ANALYZER";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//
//			for (Analyzer data : analyzers) {
//
//				data = readAnalyzer(data.getId());
//				sessionFactory.getCurrentSession().delete(data);
//				// sessionFactory.getCurrentSession().flush(); // CSL remove old
//				// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			}
//		} catch (Exception e) {
//			LogEvent.logError("AnalyzerDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyzer deleteData()", e);
//		}
//	}

//	@Override
//	@SuppressWarnings("unchecked")
//	public List<Analyzer> getAllAnalyzers() throws LIMSRuntimeException {
//		List<Analyzer> analyzer;
//		try {
//			String sql = "from Analyzer";
//			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
//			analyzer = query.list();
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//		} catch (Exception e) {
//			LogEvent.logError("AnalyzerDAOImpl", "getAllAnalyzerItems()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyzer getAllAnalyzer()", e);
//		}
//
//		return analyzer;
//	}

	@Override
	public Analyzer getAnalyzerById(Analyzer analyzer) throws LIMSRuntimeException {
		try {
			Analyzer re = sessionFactory.getCurrentSession().get(Analyzer.class, analyzer.getId());
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
			return re;
		} catch (Exception e) {
			LogEvent.logError("AnalyzerDAOImpl", "getAnalyzerById()", e.toString());
			throw new LIMSRuntimeException("Error in Analyzer getAnalyzerById()", e);
		}
	}

	@Override
	public Analyzer getAnalyzerByName(String name) throws LIMSRuntimeException {
		String sql = "From Analyzer a where a.name = :name";
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setString("name", name);
			Analyzer analyzer = (Analyzer) query.uniqueResult();
			// closeSession(); // CSL remove old
			return analyzer;
		} catch (HibernateException e) {
			handleException(e, "getAnalyzerrByName");
		}
		return null;
	}

//	@Override
//	public void getData(Analyzer analyzer) throws LIMSRuntimeException {
//		try {
//			Analyzer tmpAnalyzer = sessionFactory.getCurrentSession().get(Analyzer.class, analyzer.getId());
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			if (tmpAnalyzer != null) {
//				PropertyUtils.copyProperties(analyzer, tmpAnalyzer);
//			} else {
//				analyzer.setId(null);
//			}
//		} catch (Exception e) {
//			LogEvent.logError("AnalyzerDAOImpl", "getData()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyzer getData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(Analyzer analyzer) throws LIMSRuntimeException {
//		try {
//			String id = (String) sessionFactory.getCurrentSession().save(analyzer);
//			analyzer.setId(id);
//
//			String sysUserId = analyzer.getSysUserId();
//			String tableName = "ANALYZER";
//			auditDAO.saveNewHistory(analyzer, sysUserId, tableName);
//
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//
//		} catch (Exception e) {
//			LogEvent.logError("analyzerDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in analyzer insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(Analyzer analyzer) throws LIMSRuntimeException {
//		Analyzer oldData = readAnalyzer(analyzer.getId());
//		Analyzer newData = analyzer;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = analyzer.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "ANALYZER";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//
//			sessionFactory.getCurrentSession().merge(analyzer);
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			// sessionFactory.getCurrentSession().evict // CSL remove old(analyzer);
//			// sessionFactory.getCurrentSession().refresh // CSL remove old(analyzer);
//		} catch (Exception e) {
//			LogEvent.logError("AnalyzerDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyzer updateData()", e);
//		}
//	}

//	@Override
//	public Analyzer readAnalyzer(String idString) throws LIMSRuntimeException {
//		Analyzer data = null;
//		try {
//			data = sessionFactory.getCurrentSession().get(Analyzer.class, idString);
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//		} catch (Exception e) {
//			LogEvent.logError("AnalyzerDAOImpl", "readAnalyzer()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyzer readAnalyzer()", e);
//		}
//
//		return data;
//	}
}