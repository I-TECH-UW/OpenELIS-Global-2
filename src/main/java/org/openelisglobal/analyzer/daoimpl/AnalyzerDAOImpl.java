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
package org.openelisglobal.analyzer.daoimpl;

import org.openelisglobal.analyzer.dao.AnalyzerDAO;
import org.openelisglobal.analyzer.valueholder.Analyzer;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("AnalyzerDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyzer deleteData()", e);
//		}
//	}

//	@Override
//	
//	public List<Analyzer> getAllAnalyzers() throws LIMSRuntimeException {
//		List<Analyzer> analyzer;
//		try {
//			String sql = "from Analyzer";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			analyzer = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			LogEvent.logError("AnalyzerDAOImpl", "getAllAnalyzerItems()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyzer getAllAnalyzer()", e);
//		}
//
//		return analyzer;
//	}

//	@Override
//	public Analyzer getAnalyzerById(Analyzer analyzer) throws LIMSRuntimeException {
//		try {
//			Analyzer re = entityManager.unwrap(Session.class).get(Analyzer.class, analyzer.getId());
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			return re;
//		} catch (RuntimeException e) {
//			LogEvent.logError("AnalyzerDAOImpl", "getAnalyzerById()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyzer getAnalyzerById()", e);
//		}
//	}

//	@Override
//	public Analyzer getAnalyzerByName(String name) throws LIMSRuntimeException {
//		String sql = "From Analyzer a where a.name = :name";
//		try {
//			Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setString("name", name);
//			Analyzer analyzer = (Analyzer) query.uniqueResult();
//			// closeSession(); // CSL remove old
//			return analyzer;
//		} catch (HibernateException e) {
//			handleException(e, "getAnalyzerrByName");
//		}
//		return null;
//	}

//	@Override
//	public void getData(Analyzer analyzer) throws LIMSRuntimeException {
//		try {
//			Analyzer tmpAnalyzer = entityManager.unwrap(Session.class).get(Analyzer.class, analyzer.getId());
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			if (tmpAnalyzer != null) {
//				PropertyUtils.copyProperties(analyzer, tmpAnalyzer);
//			} else {
//				analyzer.setId(null);
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("AnalyzerDAOImpl", "getData()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyzer getData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(Analyzer analyzer) throws LIMSRuntimeException {
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(analyzer);
//			analyzer.setId(id);
//
//			String sysUserId = analyzer.getSysUserId();
//			String tableName = "ANALYZER";
//			auditDAO.saveNewHistory(analyzer, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
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
//			entityManager.unwrap(Session.class).merge(analyzer);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(analyzer);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(analyzer);
//		} catch (RuntimeException e) {
//			LogEvent.logError("AnalyzerDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyzer updateData()", e);
//		}
//	}

//	@Override
//	public Analyzer readAnalyzer(String idString) throws LIMSRuntimeException {
//		Analyzer data = null;
//		try {
//			data = entityManager.unwrap(Session.class).get(Analyzer.class, idString);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			LogEvent.logError("AnalyzerDAOImpl", "readAnalyzer()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyzer readAnalyzer()", e);
//		}
//
//		return data;
//	}
}