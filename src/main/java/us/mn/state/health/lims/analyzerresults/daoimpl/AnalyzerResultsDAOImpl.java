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

import us.mn.state.health.lims.analyzerresults.dao.AnalyzerResultsDAO;
import us.mn.state.health.lims.analyzerresults.valueholder.AnalyzerResults;
import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;

public class AnalyzerResultsDAOImpl extends BaseDAOImpl implements AnalyzerResultsDAO {


	@SuppressWarnings("unchecked")
	public List<AnalyzerResults> getResultsbyAnalyzer(String analyzerId) throws LIMSRuntimeException {

		List<AnalyzerResults> results = null;
		try {
			String sql = "from AnalyzerResults ar where ar.analyzerId = :analyzerId order by ar.id";

			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("analyzerId", Integer.parseInt(analyzerId));

			results = query.list();

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			return results;

		} catch (Exception e) {
			LogEvent.logError("AnalyzerResultsDAOImpl", "getResultsbyAnalyzer()", e.toString());
			throw new LIMSRuntimeException("Error in AnalyzerResults getResultsbyAnalyzer()", e);
		}

	}

	public void insertAnalyzerResults(List<AnalyzerResults> results, String sysUserId) throws LIMSRuntimeException {
		//most of this should be moved out of this method, these are business rules, not save ops
		try {
			for (AnalyzerResults result : results) {
				boolean duplicateByAccessionAndTestOnly = false;
				List<AnalyzerResults> previousResults = getDuplicateResultByAccessionAndTest(result);
				AnalyzerResults previousResult = null;

				//This next block may seem more complicated then it need be but it covers the case where there may be a third duplicate
				// and it covers rereading the same file
				if( previousResults != null){
					duplicateByAccessionAndTestOnly = true;
					for( AnalyzerResults foundResult : previousResults ){
						previousResult = foundResult;
						if( foundResult.getCompleteDate().equals(result.getCompleteDate())){
							duplicateByAccessionAndTestOnly = false;
							break;
						}
					}
				}


				if( duplicateByAccessionAndTestOnly){
					result.setDuplicateAnalyzerResultId(previousResult.getId());
					result.setReadOnly(true);
				}

				if ( previousResults == null || duplicateByAccessionAndTestOnly) {

					String id = (String) HibernateUtil.getSession().save(result);
					result.setId(id);

					if(duplicateByAccessionAndTestOnly){
						previousResult.setDuplicateAnalyzerResultId(id);
						previousResult.setSysUserId(sysUserId);
					}

					AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
					auditDAO.saveNewHistory(result, sysUserId, "analyzer_results");

					if( duplicateByAccessionAndTestOnly){
						updateData(previousResult);
					}
				}
			}

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("AnalyzerResultDAOImpl", "insertAnalyzerResult()", e.toString());
			throw new LIMSRuntimeException("Error in AnalyzerResult insertAnalyzerResult()", e);
		}

	}

	@SuppressWarnings("unchecked")
	private List<AnalyzerResults> getDuplicateResultByAccessionAndTest(AnalyzerResults result) {
		try {

			List<AnalyzerResults> list = new ArrayList<AnalyzerResults>();

			String sql = "from AnalyzerResults a where a.analyzerId = :analyzerId and " +
					                                  "a.accessionNumber = :assessionNumber and " +
					                                  "a.testName = :testName";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("analyzerId", Integer.parseInt(result.getAnalyzerId()));
			query.setString("assessionNumber", result.getAccessionNumber());
			query.setString("testName", result.getTestName());

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			return list.size() > 0 ?  list : null;

		} catch (Exception e) {
			LogEvent.logError("AnalyzerResultsDAOImpl","duplicateAnalyzerResultsExists()",e.toString());
			throw new LIMSRuntimeException(
					"Error in duplicateAnalyzerResultsExists()", e);
		}
	}

	public void updateData(AnalyzerResults results) throws LIMSRuntimeException {

		AnalyzerResults oldData = (AnalyzerResults) readAnalyzerResults(results.getId());
		AnalyzerResults newData = results;

		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			auditDAO.saveHistory(newData, oldData, results.getSysUserId(), IActionConstants.AUDIT_TRAIL_UPDATE, "ANALYZER_RESULTS");

			HibernateUtil.getSession().merge(results);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(results);
			HibernateUtil.getSession().refresh(results);
		} catch (Exception e) {
			LogEvent.logError("AnalyzerResultsImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in AnalyzerResults updateData()", e);
		}
	}

	public AnalyzerResults readAnalyzerResults(String idString)throws LIMSRuntimeException {
		AnalyzerResults data = null;
		try {
			data = (AnalyzerResults) HibernateUtil.getSession().get(AnalyzerResults.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("AnalyzerResultsDAOImpl","readAnalyzerResults()",e.toString());
			throw new LIMSRuntimeException("Error in AnalyzerResults readAnalyzerResults()", e);
		}
		return data;
	}

	public void getData(AnalyzerResults analyzerResults) throws LIMSRuntimeException {

		try {
			AnalyzerResults analyzerResultsClone = (AnalyzerResults)HibernateUtil.getSession().get(AnalyzerResults.class, analyzerResults.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (analyzerResultsClone != null) {
				PropertyUtils.copyProperties(analyzerResults, analyzerResultsClone);
			} else {
				analyzerResults.setId(null);
			}
		} catch (Exception e) {
			LogEvent.logError("AnalyzerResultsDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in AnalyzerResults getData()", e);
		}
	}

	public void delete(List<AnalyzerResults> analyzerResults ) throws LIMSRuntimeException {
		try{
			for( AnalyzerResults result : analyzerResults ){
				result = readAnalyzerResults(result.getId());

				HibernateUtil.getSession().delete(result);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();

			}
		}catch( HibernateException se){
			LogEvent.logError("AnalyzerResultsDAOImpl","delete()",se.toString());
			throw new LIMSRuntimeException("Error in AnalyzerResults delete()", se);
		}

	}
}
