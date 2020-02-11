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
package org.openelisglobal.result.daoimpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.result.dao.ResultSignatureDAO;
import org.openelisglobal.result.valueholder.Result;
import org.openelisglobal.result.valueholder.ResultSignature;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ResultSignatureDAOImpl extends BaseDAOImpl<ResultSignature, String> implements ResultSignatureDAO {

    public ResultSignatureDAOImpl() {
        super(ResultSignature.class);
    }

//	@Override
//	public void deleteData(List<ResultSignature> resultSignatures) throws LIMSRuntimeException {
//		try {
//
//			for (ResultSignature resultSig : resultSignatures) {
//
//				ResultSignature oldData = readResultSignature(resultSig.getId());
//
//				String sysUserId = resultSig.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "RESULT_SIGNATURE";
//				auditDAO.saveHistory(resultSig, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("ResultSignatureDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in ResultSignature AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < resultSignatures.size(); i++) {
//				ResultSignature data = resultSignatures.get(i);
//
//				data = readResultSignature(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("ResultSignatureDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in ResultSignature deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(ResultSignature resultSignature) throws LIMSRuntimeException {
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(resultSignature);
//			resultSignature.setId(id);
//
//			String sysUserId = resultSignature.getSysUserId();
//			String tableName = "RESULT_SIGNATURE";
//			auditDAO.saveNewHistory(resultSignature, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			LogEvent.logError("ResultSignatureDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in ResultSignature insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(ResultSignature resultSignature) throws LIMSRuntimeException {
//		ResultSignature oldData = readResultSignature(resultSignature.getId());
//		ResultSignature newData = resultSignature;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = resultSignature.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "RESULT_SIGNATURE";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			LogEvent.logError("ResultSignatureDAOImpl", "AuditTrail insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in ResultSignature AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(resultSignature);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(resultSignature);
//			// entityManager.unwrap(Session.class).refresh // CSL remove
//			// old(resultSignature);
//		} catch (RuntimeException e) {
//			LogEvent.logError("ResultSignatureDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in ResultSignature updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(ResultSignature resultSignature) throws LIMSRuntimeException {
        try {
            ResultSignature tmpResultSignature = entityManager.unwrap(Session.class).get(ResultSignature.class,
                    resultSignature.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (tmpResultSignature != null) {
                PropertyUtils.copyProperties(resultSignature, tmpResultSignature);
            } else {
                resultSignature.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in ResultSignature getData()", e);
        }
    }

    @Override

    @Transactional(readOnly = true)
    public List<ResultSignature> getResultSignaturesByResult(Result result) throws LIMSRuntimeException {
        List<ResultSignature> resultSignatures = null;
        try {

            String sql = "from ResultSignature r where r.resultId = :resultId";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("resultId", Integer.parseInt(result.getId()));

            resultSignatures = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            return resultSignatures;

        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in ResultSignature getResultSignatureResult()", e);
        }
    }

    public ResultSignature readResultSignature(String idString) {
        ResultSignature data = null;
        try {
            data = entityManager.unwrap(Session.class).get(ResultSignature.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in ResultSignature readResultSignature()", e);
        }

        return data;
    }

    @Override
    @Transactional(readOnly = true)
    public ResultSignature getResultSignatureById(ResultSignature resultSignature) throws LIMSRuntimeException {
        try {
            ResultSignature re = entityManager.unwrap(Session.class).get(ResultSignature.class,
                    resultSignature.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            return re;
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in ResultSignature getResultSignatureById()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResultSignature> getResultSignaturesByResults(List<Result> resultList) throws LIMSRuntimeException {
        if (resultList.isEmpty()) {
            return new ArrayList<>();
        }

        List<Integer> resultIds = new ArrayList<>();
        for (Result result : resultList) {
            resultIds.add(Integer.parseInt(result.getId()));
        }

        String sql = "From ResultSignature rs where rs.resultId in (:resultIdList)";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameterList("resultIdList", resultIds);
            List<ResultSignature> sigs = query.list();
            // closeSession(); // CSL remove old
            return sigs;

        } catch (HibernateException e) {
            handleException(e, "getResultSignaturesByResults");
        }
        return null;
    }

}