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
package us.mn.state.health.lims.result.daoimpl;

import java.sql.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.analyte.valueholder.Analyte;
import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

/**
 * @author diane benz
 */
public class ResultDAOImpl extends BaseDAOImpl implements ResultDAO {

	public void deleteData(List results) throws LIMSRuntimeException {
		
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
            for( Object result : results ){
                Result data = ( Result ) result;
                Result oldData = readResult( data.getId() );

                String sysUserId = data.getSysUserId();
                String event = IActionConstants.AUDIT_TRAIL_DELETE;
                String tableName = "RESULT";
                auditDAO.saveHistory( new Result(), oldData, sysUserId, event, tableName );
            }
		} catch (Exception e) {
			
			LogEvent.logError("ResultDAOImpl", "AuditTrail deleteData()", e.toString());
			throw new LIMSRuntimeException("Error in Result AuditTrail deleteData()", e);
		}

		try {
            for( Object result : results ){
                Result data = ( Result ) result;
                data = readResult( data.getId() );
                HibernateUtil.getSession().delete( data );
                HibernateUtil.getSession().flush();
                HibernateUtil.getSession().clear();
            }
		} catch (Exception e) {
			
			LogEvent.logError("ResultDAOImpl", "deleteData()", e.toString());
			throw new LIMSRuntimeException("Error in Result deleteData()", e);
		}
	}

	public void deleteData(Result result) throws LIMSRuntimeException {
		Result oldData = readResult(result.getId());

		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			auditDAO.saveHistory(new Result(), oldData, result.getSysUserId(), IActionConstants.AUDIT_TRAIL_DELETE, "RESULT");
		} catch (HibernateException e) {
			handleException(e, "AuditTrail deleteData");
		}

		try {
			HibernateUtil.getSession().delete(oldData);
			closeSession();
		} catch (HibernateException e) {
			handleException(e, "deleteData");
		}
	}

	public boolean insertData(Result result) throws LIMSRuntimeException {

		try {
			String id = (String) HibernateUtil.getSession().save(result);
			result.setId(id);

			// bugzilla 1824 inserts will be logged in history table
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = result.getSysUserId();
			String tableName = "RESULT";
			auditDAO.saveNewHistory(result, sysUserId, tableName);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		} catch (Exception e) {
			
			LogEvent.logError("ResultDAOImpl", "insertData()", e.toString());
			throw new LIMSRuntimeException("Error in Result insertData()", e);
		}

		return true;
	}

	public void updateData(Result result) throws LIMSRuntimeException {

		Result oldData = readResult(result.getId());

		
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = result.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "RESULT";
			auditDAO.saveHistory(result, oldData, sysUserId, event, tableName);
		} catch (Exception e) {
			
			LogEvent.logError("ResultDAOImpl", "AuditTrail insertData()", e.toString());
			throw new LIMSRuntimeException("Error in Result AuditTrail updateData()", e);
		}

		try {
			HibernateUtil.getSession().merge(result);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(result);
			HibernateUtil.getSession().refresh(result);
		} catch (Exception e) {
			
			LogEvent.logError("ResultDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in Result updateData()", e);
		}
	}

	public void getData(Result result) throws LIMSRuntimeException {
		try {
			Result re = (Result) HibernateUtil.getSession().get(Result.class, result.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (re != null) {
				PropertyUtils.copyProperties(result, re);
			} else {
				result.setId(null);
			}
		} catch (Exception e) {
			
			LogEvent.logError("ResultDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in Result getData()", e);
		}
	}

	public void getResultByAnalysisAndAnalyte(Result result, Analysis analysis, TestAnalyte ta) throws LIMSRuntimeException {
		List results;
		try {
			Analyte analyte = ta.getAnalyte();

			String sql = "from Result r where r.analysis = :analysisId and r.analyte = :analyteId";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("analysisId", Integer.parseInt(analysis.getId()));
			query.setInteger("analyteId", Integer.parseInt(analyte.getId()));

			results = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			Result thisResult;
			if (results != null && results.size() > 0) {
				thisResult = (Result) results.get(0);
			} else {
				thisResult = null;
			}
			if (thisResult != null) {
				PropertyUtils.copyProperties(result, thisResult);
			} else {
				result.setId(null);
			}

		} catch (Exception e) {
			
			LogEvent.logError("ResultDAOImpl", "getResultByAnalysisAndAnalyte()", e.toString());
			throw new LIMSRuntimeException("Error in Result getResultByAnalysisAndAnalyte()", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Result> getResultsByAnalysis(Analysis analysis) throws LIMSRuntimeException {
		try {

			String sql = "from Result r where r.analysis = :analysisId order by r.id";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("analysisId", Integer.parseInt(analysis.getId()));

			List<Result> results = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			return results;

		} catch (Exception e) {
			LogEvent.logError("ResultDAOImpl", "getResultByAnalysis()", e.toString());
			throw new LIMSRuntimeException("Error in Result getResultByAnalysis()", e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * us.mn.state.health.lims.result.dao.ResultDAO#getResultByTestResult(us
	 * .mn.state.health.lims.result.valueholder.Result,
	 * us.mn.state.health.lims.testresult.valueholder.TestResult)
	 */
	public void getResultByTestResult(Result result, TestResult testResult) throws LIMSRuntimeException {
		List results;
		try {
			String sql = "from Result r where r.testResult = :testResultId";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("testResultId", Integer.parseInt(testResult.getId()));

			results = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			Result thisResult;
			if (results != null && results.size() > 0) {
				thisResult = (Result) results.get(0);
			} else {
				thisResult = null;
			}
			if (thisResult != null) {
				PropertyUtils.copyProperties(result, thisResult);
			} else {
				result.setId(null);
			}

		} catch (Exception e) {
			
			LogEvent.logError("ResultDAOImpl", "getResultByTestResult()", e.toString());
			throw new LIMSRuntimeException("Error in Result getResultByTestResult()", e);
		}
	}

	public List getAllResults() throws LIMSRuntimeException {
		List results;
		try {
			String sql = "from Result";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			results = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			
			LogEvent.logError("ResultDAOImpl", "getAllResults()", e.toString());
			throw new LIMSRuntimeException("Error in Result getAllResults()", e);
		}

		return results;
	}

	public List getPageOfResults(int startingRecNo) throws LIMSRuntimeException {
		List results;
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			String sql = "from Result r order by r.id";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);

			results = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			
			LogEvent.logError("ResultDAOImpl", "getPageOfResults()", e.toString());
			throw new LIMSRuntimeException("Error in Result getPageOfResults()", e);
		}

		return results;
	}

	public Result readResult(String idString) {
		Result data;
		try {
			data = (Result) HibernateUtil.getSession().get(Result.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			
			LogEvent.logError("ResultDAOImpl", "readResult()", e.toString());
			throw new LIMSRuntimeException("Error in Result readResult()", e);
		}

		return data;
	}

	public List getNextResultRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "Result", Result.class);

	}

	public List getPreviousResultRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "Result", Result.class);
	}

	public Result getResultById(Result result) throws LIMSRuntimeException {
		return getResultById(result.getId());
	}

	public Result getResultById(String resultId) throws LIMSRuntimeException {
		try {
			Result result = (Result) HibernateUtil.getSession().get(Result.class, resultId);

			closeSession();

			return result;
		} catch (Exception e) {
			handleException(e, "getResultById");
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Result> getReportableResultsByAnalysis(Analysis analysis) throws LIMSRuntimeException {
		try {

			String sql = "from Result r where r.analysis = :param1 and r.isReportable = " + enquote(YES);
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param1", analysis);

			List<Result> results = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			return results;

		} catch (Exception e) {
			LogEvent.logError("ResultDAOImpl", "getReportableResultsByAnalysis()", e.toString());
			throw new LIMSRuntimeException("Error in Result getReportableResultsByAnalysis()", e);
		}
	}

	@SuppressWarnings("unchecked")
	public Result getResultForAnalyteInAnalysisSet(String analyteId, List<Integer> analysisIDList) throws LIMSRuntimeException {

		try {

			String sql = "from Result r where r.analyte = :analyteId and r.analysis in (:analysisIdList)";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("analyteId", Integer.parseInt(analyteId));
			query.setParameterList("analysisIdList", analysisIDList);

			List<Result> results = query.list();

			closeSession();

			if (results.size() > 0) {
				return results.get(0);
			}

		} catch (Exception e) {
			handleException(e, "getResultForAnalyteInAnalysisSet");
		}

		return null;
	}
	
    @SuppressWarnings("unchecked")
    public Result getResultForAnalyteAndSampleItem(String analyteId, String sampleItemId) throws LIMSRuntimeException {

        try {
            // "from Result r where r.analyte.id = :analyteId and r.analysis.sampleItem.id = :sampleItemId)";
            String sql = "from Result r where r.analyte.id = :analyteId and r.analysis.sampleItem.id = :sampleItemId";
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setInteger("analyteId", Integer.parseInt(analyteId));
            query.setInteger("sampleItemId", Integer.parseInt(sampleItemId));

            List<Result> results = query.list();

            closeSession();

            if (results.size() > 0) {
                return results.get(0);
            }

        } catch (Exception e) {
            handleException(e, "getResultForAnalyteAndSampleItem");
        }

        return null;
    }
	
	@SuppressWarnings("unchecked")
	public List<Result> getResultsForAnalysisIdList(List<Integer> analysisIdList) throws LIMSRuntimeException {
		if( analysisIdList.isEmpty()){
			return null;
		}
		String sql = "from Result r where r.analysis IN (:analysisList)";

		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameterList("analysisList", analysisIdList);

			List<Result> resultList = query.list();

			closeSession();

			return resultList;

		}catch(HibernateException e){
			handleException(e, "getResultsForAnalysisIdList");
		}

		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Result> getResultsForTestAndSample(String sampleId, String testId) {
        String sql = "FROM Result r WHERE r.analysis.sampleItem.sample.id = :sampleId AND r.testResult.test.id = :testId";

        try{
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setParameter("testId", Integer.valueOf(testId));
            query.setParameter("sampleId", Integer.valueOf(sampleId));

            List<Result> resultList = query.list();

            closeSession();
            return resultList;

        } catch(HibernateException e){
            handleException(e, "getResultsForTestAndSample");
        }
        return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Result> getResultsForSample(Sample sample) throws LIMSRuntimeException {
		String sql = "From Result r where r.analysis.sampleItem.sample.id = :sampleId";
		
		try {
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("sampleId", Integer.parseInt(sample.getId()));
			List<Result> results = query.list();
			closeSession();
			return results;
			
		} catch (HibernateException e) {
			handleException(e, "getResultsForSample");
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Result> getChildResults(String resultId) throws LIMSRuntimeException {
		String sql = "From Result r where r.parentResult.id = :parentId";
		
		try {
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("parentId", Integer.parseInt(resultId));
			List<Result> results = query.list();
			closeSession();
			return results;
		} catch (HibernateException e) {
			handleException(e, "getChildResults");
		}
		
		return null;
	}

    @Override
    @SuppressWarnings( "unchecked" )
    public List<Result> getResultsForTestInDateRange( String testId, Date startDate, Date endDate ) throws LIMSRuntimeException{
        String sql = "FROM Result r WHERE r.analysis.test.id = :testId AND r.lastupdated BETWEEN :lowDate AND :highDate";

        try{
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setInteger("testId", Integer.valueOf(testId));
            query.setDate("lowDate", startDate);
            query.setDate("highDate", endDate);

            List<Result> resultList = query.list();

            closeSession();
            return resultList;

        } catch(HibernateException e){
            handleException(e, "getResultsForTestInDateRange");
        }
        return null;
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public List<Result> getResultsForPanelInDateRange( String panelId, Date lowDate, Date highDate ) throws LIMSRuntimeException{
        String sql = "FROM Result r WHERE r.analysis.panel.id = :panelId AND r.lastupdated BETWEEN :lowDate AND :highDate order by r.id";

        try{
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setInteger("panelId", Integer.valueOf(panelId));
            query.setDate("lowDate", lowDate);
            query.setDate("highDate", highDate);

            List<Result> resultList = query.list();

            closeSession();
            return resultList;

        } catch(HibernateException e){
            handleException(e, "getResultsForPanelInDateRange");
        }
        return null;
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public List<Result> getResultsForTestSectionInDateRange( String testSectionId, Date lowDate, Date highDate ) throws LIMSRuntimeException{
        String sql = "FROM Result r WHERE r.analysis.testSection.id = :testSectionId AND r.lastupdated BETWEEN :lowDate AND :highDate";

        try{
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setInteger("testSectionId", Integer.valueOf(testSectionId));
            query.setDate("lowDate", lowDate);
            query.setDate("highDate", highDate);

            List<Result> resultList = query.list();

            closeSession();
            return resultList;

        } catch(HibernateException e){
            handleException(e, "getResultsForTestSectionInDateRange");
        }
        return null;
    }
}