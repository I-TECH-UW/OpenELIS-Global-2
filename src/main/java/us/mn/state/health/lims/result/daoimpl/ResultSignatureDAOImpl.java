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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.result.dao.ResultSignatureDAO;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.result.valueholder.ResultSignature;


public class ResultSignatureDAOImpl extends BaseDAOImpl implements ResultSignatureDAO {

	public void deleteData(List<ResultSignature> resultSignatures) throws LIMSRuntimeException {
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (ResultSignature resultSig : resultSignatures) {
			
				ResultSignature oldData = (ResultSignature)readResultSignature(resultSig.getId());

				String sysUserId = resultSig.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "RESULT_SIGNATURE";
				auditDAO.saveHistory(resultSig,oldData,sysUserId,event,tableName);
			}
		}  catch (Exception e) {

			LogEvent.logError("ResultSignatureDAOImpl","AuditTrail deleteData()",e.toString());
		    throw new LIMSRuntimeException("Error in ResultSignature AuditTrail deleteData()", e);
		}  
		
		try {		
			for (int i = 0; i < resultSignatures.size(); i++) {
				ResultSignature data = (ResultSignature) resultSignatures.get(i);	

				data = (ResultSignature)readResultSignature(data.getId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}			
		} catch (Exception e) {
			LogEvent.logError("ResultSignatureDAOImpl","deleteData()",e.toString());
			   throw new LIMSRuntimeException("Error in ResultSignature deleteData()", e);
		}
	}

	public boolean insertData(ResultSignature resultSignature) throws LIMSRuntimeException {	
		try {
			String id = (String)HibernateUtil.getSession().save(resultSignature);
			resultSignature.setId(id);
			
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = resultSignature.getSysUserId();
			String tableName = "RESULT_SIGNATURE";
			auditDAO.saveNewHistory(resultSignature,sysUserId,tableName);
			
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
							
		} catch (Exception e) {
			LogEvent.logError("ResultSignatureDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in ResultSignature insertData()", e);
		}
		
		return true;
	}

	public void updateData(ResultSignature resultSignature) throws LIMSRuntimeException {		
		ResultSignature oldData = (ResultSignature)readResultSignature(resultSignature.getId());
		ResultSignature newData = resultSignature;

		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = resultSignature.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "RESULT_SIGNATURE";
			auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
		}  catch (Exception e) {
			LogEvent.logError("ResultSignatureDAOImpl","AuditTrail insertData()",e.toString());
			throw new LIMSRuntimeException("Error in ResultSignature AuditTrail updateData()", e);
		}  
							
		try {
			HibernateUtil.getSession().merge(resultSignature);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(resultSignature);
			HibernateUtil.getSession().refresh(resultSignature);
		} catch (Exception e) {
			LogEvent.logError("ResultSignatureDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in ResultSignature updateData()", e);
		}
	}

	public void getData(ResultSignature resultSignature) throws LIMSRuntimeException {
		try {
			ResultSignature tmpResultSignature = (ResultSignature)HibernateUtil.getSession().get(ResultSignature.class, resultSignature.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (tmpResultSignature != null) {
				PropertyUtils.copyProperties(resultSignature, tmpResultSignature);
			} else {
				resultSignature.setId(null);
			}
		} catch (Exception e) {
			LogEvent.logError("ResultSignatureDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in ResultSignature getData()", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ResultSignature> getResultSignaturesByResult(Result result) throws LIMSRuntimeException {
		List<ResultSignature> resultSignatures = null;
		try {
		
			String sql = "from ResultSignature r where r.resultId = :resultId";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("resultId", Integer.parseInt(result.getId()));

			resultSignatures = query.list();		
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			
            return resultSignatures;

		} catch (Exception e) {
			LogEvent.logError("ResultSignatureDAOImpl","getResultSignatureByResult()",e.toString());
			throw new LIMSRuntimeException("Error in ResultSignature getResultSignatureResult()", e);
		}						
	}

	public ResultSignature readResultSignature(String idString) {
		ResultSignature data = null;
		try {
			data = (ResultSignature)HibernateUtil.getSession().get(ResultSignature.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("ResultSignatureDAOImpl","readResultSignature()",e.toString());
			throw new LIMSRuntimeException("Error in ResultSignature readResultSignature()", e);
		}		
		
		return data;
	}

	public ResultSignature getResultSignatureById(ResultSignature resultSignature) throws LIMSRuntimeException {
		try {
			ResultSignature re = (ResultSignature)HibernateUtil.getSession().get(ResultSignature.class, resultSignature.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			return re;
		} catch (Exception e) {
			LogEvent.logError("ResultSignatureDAOImpl","getResultSignatureById()",e.toString());
			throw new LIMSRuntimeException("Error in ResultSignature getResultSignatureById()", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResultSignature> getResultSignaturesByResults(List<Result> resultList) throws LIMSRuntimeException {
		if( resultList.isEmpty()){
			return new ArrayList<ResultSignature>();
		}
		
		List<Integer> resultIds = new ArrayList<Integer>();
		for( Result result: resultList){
			resultIds.add(Integer.parseInt(result.getId()));
		}
		
		String sql = "From ResultSignature rs where rs.resultId in (:resultIdList)";
		
		try {
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameterList("resultIdList", resultIds );
			List<ResultSignature> sigs = query.list();
			closeSession();
			return sigs;
			
		} catch (HibernateException e) {
			handleException(e, "getResultSignaturesByResults");
		}
		return null;
	}
	
}