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
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.result.dao.ResultInventoryDAO;
import org.openelisglobal.result.valueholder.Result;
import org.openelisglobal.result.valueholder.ResultInventory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ResultInventoryDAOImpl extends BaseDAOImpl<ResultInventory, String> implements ResultInventoryDAO {

    public ResultInventoryDAOImpl() {
        super(ResultInventory.class);
    }

    @Override

    @Transactional(readOnly = true)
    public List<ResultInventory> getAllResultInventorys() throws LIMSRuntimeException {
        List<ResultInventory> resultInventories;
        try {
            String sql = "from ResultInventory";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            resultInventories = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in ResultInventory getAllResultInventorys()", e);
        }

        return resultInventories;
    }

//	@Override
//	public void deleteData(List resultInventories) throws LIMSRuntimeException {
//		try {
//
//			for (int i = 0; i < resultInventories.size(); i++) {
//				ResultInventory data = (ResultInventory) resultInventories.get(i);
//
//				ResultInventory oldData = readResultInventory(data.getId());
//				ResultInventory newData = new ResultInventory();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "RESULT_INVENTORY";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("ResultInventoryDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in ResultInventory AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < resultInventories.size(); i++) {
//				ResultInventory data = (ResultInventory) resultInventories.get(i);
//
//				data = readResultInventory(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("ResultInventoryDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in ResultInventory deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(ResultInventory resultInventory) throws LIMSRuntimeException {
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(resultInventory);
//			resultInventory.setId(id);
//
//			String sysUserId = resultInventory.getSysUserId();
//			String tableName = "RESULT_INVENTORY";
//			auditDAO.saveNewHistory(resultInventory, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			LogEvent.logError("ResultInventoryDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in ResultInventory insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(ResultInventory resultInventory) throws LIMSRuntimeException {
//		ResultInventory oldData = readResultInventory(resultInventory.getId());
//		ResultInventory newData = resultInventory;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = resultInventory.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "RESULT_INVENTORY";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			LogEvent.logError("ResultInventoryDAOImpl", "AuditTrail insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in ResultInventory AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(resultInventory);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(resultInventory);
//			// entityManager.unwrap(Session.class).refresh // CSL remove
//			// old(resultInventory);
//		} catch (RuntimeException e) {
//			LogEvent.logError("ResultInventoryDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in ResultInventory updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(ResultInventory resultInventory) throws LIMSRuntimeException {
        try {
            ResultInventory tmpResultInventory = entityManager.unwrap(Session.class).get(ResultInventory.class,
                    resultInventory.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (tmpResultInventory != null) {
                PropertyUtils.copyProperties(resultInventory, tmpResultInventory);
            } else {
                resultInventory.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in ResultInventory getData()", e);
        }
    }

    @Override

    @Transactional(readOnly = true)
    public List<ResultInventory> getResultInventorysByResult(Result result) throws LIMSRuntimeException {
        List<ResultInventory> resultInventories = null;
        try {

            String sql = "from ResultInventory r where r.resultId = :resultId";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("resultId", Integer.parseInt(result.getId()));

            resultInventories = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            return resultInventories;

        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in ResultInventory getResultInventoryByResult()", e);
        }
    }

    public ResultInventory readResultInventory(String idString) {
        ResultInventory data = null;
        try {
            data = entityManager.unwrap(Session.class).get(ResultInventory.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in ResultInventory readResultInventory()", e);
        }

        return data;
    }

    @Override
    @Transactional(readOnly = true)
    public ResultInventory getResultInventoryById(ResultInventory resultInventory) throws LIMSRuntimeException {
        try {
            ResultInventory re = entityManager.unwrap(Session.class).get(ResultInventory.class,
                    resultInventory.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            return re;
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in ResultInventory getResultInventoryById()", e);
        }
    }

}