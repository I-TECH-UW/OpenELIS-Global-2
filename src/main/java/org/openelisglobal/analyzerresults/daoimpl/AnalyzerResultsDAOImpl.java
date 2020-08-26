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
 * Contributor(s): CIRG, University of Washington, Seattle WA.
 */
package org.openelisglobal.analyzerresults.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.openelisglobal.analyzerresults.dao.AnalyzerResultsDAO;
import org.openelisglobal.analyzerresults.valueholder.AnalyzerResults;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AnalyzerResultsDAOImpl extends BaseDAOImpl<AnalyzerResults, String> implements AnalyzerResultsDAO {

    public AnalyzerResultsDAOImpl() {
        super(AnalyzerResults.class);
    }

//	@Override
//
//	public List<AnalyzerResults> getResultsbyAnalyzer(String analyzerId) throws LIMSRuntimeException {
//
//		List<AnalyzerResults> results = null;
//		try {
//			String sql = "from AnalyzerResults ar where ar.analyzerId = :analyzerId order by ar.id";
//
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setInteger("analyzerId", Integer.parseInt(analyzerId));
//
//			results = query.list();
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//			return results;
//
//		} catch (RuntimeException e) {
//			LogEvent.logError("AnalyzerResultsDAOImpl", "getResultsbyAnalyzer()", e.toString());
//			throw new LIMSRuntimeException("Error in AnalyzerResults getResultsbyAnalyzer()", e);
//		}
//
//	}

    @Override

    @Transactional(readOnly = true)
    public List<AnalyzerResults> getDuplicateResultByAccessionAndTest(AnalyzerResults result) {
        try {

            List<AnalyzerResults> list = new ArrayList<>();

            String sql = "from AnalyzerResults a where a.analyzerId = :analyzerId and "
                    + "a.accessionNumber = :assessionNumber and " + "a.testName = :testName";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("analyzerId", Integer.parseInt(result.getAnalyzerId()));
            query.setString("assessionNumber", result.getAccessionNumber());
            query.setString("testName", result.getTestName());

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            return list.size() > 0 ? list : null;

        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in duplicateAnalyzerResultsExists()", e);
        }
    }

//	@Override
//	public void updateData(AnalyzerResults results) throws LIMSRuntimeException {
//
//		AnalyzerResults oldData = readAnalyzerResults(results.getId());
//		AnalyzerResults newData = results;
//
//		try {
//
//			auditDAO.saveHistory(newData, oldData, results.getSysUserId(), IActionConstants.AUDIT_TRAIL_UPDATE,
//					"ANALYZER_RESULTS");
//
//			entityManager.unwrap(Session.class).merge(results);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(results);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(results);
//		} catch (RuntimeException e) {
//			LogEvent.logError("AnalyzerResultsImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in AnalyzerResults updateData()", e);
//		}
//	}

    @Override
    public AnalyzerResults readAnalyzerResults(String idString) throws LIMSRuntimeException {
        AnalyzerResults data = null;
        try {
            data = entityManager.unwrap(Session.class).get(AnalyzerResults.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in AnalyzerResults readAnalyzerResults()", e);
        }
        return data;
    }

//	@Override
//	public void getData(AnalyzerResults analyzerResults) throws LIMSRuntimeException {
//
//		try {
//			AnalyzerResults analyzerResultsClone = entityManager.unwrap(Session.class).get(AnalyzerResults.class,
//					analyzerResults.getId());
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			if (analyzerResultsClone != null) {
//				PropertyUtils.copyProperties(analyzerResults, analyzerResultsClone);
//			} else {
//				analyzerResults.setId(null);
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("AnalyzerResultsDAOImpl", "getData()", e.toString());
//			throw new LIMSRuntimeException("Error in AnalyzerResults getData()", e);
//		}
//	}

//	@Override
//	public void deleteAll(List<AnalyzerResults> analyzerResults) throws LIMSRuntimeException {
//		try {
//			for (AnalyzerResults result : analyzerResults) {
//				result = readAnalyzerResults(result.getId());
//
//				entityManager.unwrap(Session.class).delete(result);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//			}
//		} catch (HibernateException e) {
//			LogEvent.logError("AnalyzerResultsDAOImpl", "delete()", se.toString());
//			throw new LIMSRuntimeException("Error in AnalyzerResults delete()", se);
//		}
//
//	}
}
