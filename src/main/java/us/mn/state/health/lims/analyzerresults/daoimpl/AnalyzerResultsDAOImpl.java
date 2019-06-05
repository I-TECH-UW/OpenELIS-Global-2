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
package us.mn.state.health.lims.analyzerresults.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.analyzerresults.dao.AnalyzerResultsDAO;
import us.mn.state.health.lims.analyzerresults.valueholder.AnalyzerResults;
import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;

@Component
@Transactional 
public class AnalyzerResultsDAOImpl extends BaseDAOImpl<AnalyzerResults, String> implements AnalyzerResultsDAO {

	public AnalyzerResultsDAOImpl() {
		super(AnalyzerResults.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AnalyzerResults> getResultsbyAnalyzer(String analyzerId) throws LIMSRuntimeException {

		List<AnalyzerResults> results = null;
		try {
			String sql = "from AnalyzerResults ar where ar.analyzerId = :analyzerId order by ar.id";

			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setInteger("analyzerId", Integer.parseInt(analyzerId));

			results = query.list();

			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old

			return results;

		} catch (Exception e) {
			LogEvent.logError("AnalyzerResultsDAOImpl", "getResultsbyAnalyzer()", e.toString());
			throw new LIMSRuntimeException("Error in AnalyzerResults getResultsbyAnalyzer()", e);
		}

	}

	@Override
	public void insertAnalyzerResults(List<AnalyzerResults> results, String sysUserId) throws LIMSRuntimeException {
		// most of this should be moved out of this method, these are business rules,
		// not save ops
		try {
			for (AnalyzerResults result : results) {
				boolean duplicateByAccessionAndTestOnly = false;
				List<AnalyzerResults> previousResults = getDuplicateResultByAccessionAndTest(result);
				AnalyzerResults previousResult = null;

				// This next block may seem more complicated then it need be but it covers the
				// case where there may be a third duplicate
				// and it covers rereading the same file
				if (previousResults != null) {
					duplicateByAccessionAndTestOnly = true;
					for (AnalyzerResults foundResult : previousResults) {
						previousResult = foundResult;
						if (foundResult.getCompleteDate().equals(result.getCompleteDate())) {
							duplicateByAccessionAndTestOnly = false;
							break;
						}
					}
				}

				if (duplicateByAccessionAndTestOnly) {
					result.setDuplicateAnalyzerResultId(previousResult.getId());
					result.setReadOnly(true);
				}

				if (previousResults == null || duplicateByAccessionAndTestOnly) {

					String id = (String) sessionFactory.getCurrentSession().save(result);
					result.setId(id);

					if (duplicateByAccessionAndTestOnly) {
						previousResult.setDuplicateAnalyzerResultId(id);
						previousResult.setSysUserId(sysUserId);
					}

					AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
					auditDAO.saveNewHistory(result, sysUserId, "analyzer_results");

					if (duplicateByAccessionAndTestOnly) {
						updateData(previousResult);
					}
				}
			}

			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("AnalyzerResultDAOImpl", "insertAnalyzerResult()", e.toString());
			throw new LIMSRuntimeException("Error in AnalyzerResult insertAnalyzerResult()", e);
		}

	}

	@SuppressWarnings("unchecked")
	private List<AnalyzerResults> getDuplicateResultByAccessionAndTest(AnalyzerResults result) {
		try {

			List<AnalyzerResults> list = new ArrayList<>();

			String sql = "from AnalyzerResults a where a.analyzerId = :analyzerId and "
					+ "a.accessionNumber = :assessionNumber and " + "a.testName = :testName";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setInteger("analyzerId", Integer.parseInt(result.getAnalyzerId()));
			query.setString("assessionNumber", result.getAccessionNumber());
			query.setString("testName", result.getTestName());

			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old

			return list.size() > 0 ? list : null;

		} catch (Exception e) {
			LogEvent.logError("AnalyzerResultsDAOImpl", "duplicateAnalyzerResultsExists()", e.toString());
			throw new LIMSRuntimeException("Error in duplicateAnalyzerResultsExists()", e);
		}
	}

	@Override
	public void updateData(AnalyzerResults results) throws LIMSRuntimeException {

		AnalyzerResults oldData = readAnalyzerResults(results.getId());
		AnalyzerResults newData = results;

		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			auditDAO.saveHistory(newData, oldData, results.getSysUserId(), IActionConstants.AUDIT_TRAIL_UPDATE,
					"ANALYZER_RESULTS");

			sessionFactory.getCurrentSession().merge(results);
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
			// sessionFactory.getCurrentSession().evict // CSL remove old(results);
			// sessionFactory.getCurrentSession().refresh // CSL remove old(results);
		} catch (Exception e) {
			LogEvent.logError("AnalyzerResultsImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in AnalyzerResults updateData()", e);
		}
	}

	@Override
	public AnalyzerResults readAnalyzerResults(String idString) throws LIMSRuntimeException {
		AnalyzerResults data = null;
		try {
			data = (AnalyzerResults) sessionFactory.getCurrentSession().get(AnalyzerResults.class, idString);
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("AnalyzerResultsDAOImpl", "readAnalyzerResults()", e.toString());
			throw new LIMSRuntimeException("Error in AnalyzerResults readAnalyzerResults()", e);
		}
		return data;
	}

	@Override
	public void getData(AnalyzerResults analyzerResults) throws LIMSRuntimeException {

		try {
			AnalyzerResults analyzerResultsClone = (AnalyzerResults) sessionFactory.getCurrentSession()
					.get(AnalyzerResults.class, analyzerResults.getId());
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
			if (analyzerResultsClone != null) {
				PropertyUtils.copyProperties(analyzerResults, analyzerResultsClone);
			} else {
				analyzerResults.setId(null);
			}
		} catch (Exception e) {
			LogEvent.logError("AnalyzerResultsDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in AnalyzerResults getData()", e);
		}
	}

	@Override
	public void deleteAll(List<AnalyzerResults> analyzerResults) throws LIMSRuntimeException {
		try {
			for (AnalyzerResults result : analyzerResults) {
				result = readAnalyzerResults(result.getId());

				sessionFactory.getCurrentSession().delete(result);
				// sessionFactory.getCurrentSession().flush(); // CSL remove old
				// sessionFactory.getCurrentSession().clear(); // CSL remove old

			}
		} catch (HibernateException se) {
			LogEvent.logError("AnalyzerResultsDAOImpl", "delete()", se.toString());
			throw new LIMSRuntimeException("Error in AnalyzerResults delete()", se);
		}

	}
}
