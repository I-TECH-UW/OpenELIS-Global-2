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

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.result.dao.ResultInventoryDAO;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.result.valueholder.ResultInventory;

@Component
@Transactional 
public class ResultInventoryDAOImpl extends BaseDAOImpl<ResultInventory> implements ResultInventoryDAO {

	public ResultInventoryDAOImpl() {
		super(ResultInventory.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ResultInventory> getAllResultInventoryss() throws LIMSRuntimeException {
		List<ResultInventory> resultInventories;
		try {
			String sql = "from ResultInventory";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			resultInventories = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("ResultInventoryDAOImpl", "getAllResultInventorys()", e.toString());
			throw new LIMSRuntimeException("Error in ResultInventory getAllResultInventorys()", e);
		}

		return resultInventories;
	}

	@Override
	public void deleteData(List resultInventories) throws LIMSRuntimeException {
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < resultInventories.size(); i++) {
				ResultInventory data = (ResultInventory) resultInventories.get(i);

				ResultInventory oldData = readResultInventory(data.getId());
				ResultInventory newData = new ResultInventory();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "RESULT_INVENTORY";
				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
			}
		} catch (Exception e) {

			LogEvent.logError("ResultInventoryDAOImpl", "AuditTrail deleteData()", e.toString());
			throw new LIMSRuntimeException("Error in ResultInventory AuditTrail deleteData()", e);
		}

		try {
			for (int i = 0; i < resultInventories.size(); i++) {
				ResultInventory data = (ResultInventory) resultInventories.get(i);

				data = readResultInventory(data.getId());
				sessionFactory.getCurrentSession().delete(data);
				// sessionFactory.getCurrentSession().flush(); // CSL remove old
				// sessionFactory.getCurrentSession().clear(); // CSL remove old
			}
		} catch (Exception e) {
			LogEvent.logError("ResultInventoryDAOImpl", "deleteData()", e.toString());
			throw new LIMSRuntimeException("Error in ResultInventory deleteData()", e);
		}
	}

	@Override
	public boolean insertData(ResultInventory resultInventory) throws LIMSRuntimeException {
		try {
			String id = (String) sessionFactory.getCurrentSession().save(resultInventory);
			resultInventory.setId(id);

			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = resultInventory.getSysUserId();
			String tableName = "RESULT_INVENTORY";
			auditDAO.saveNewHistory(resultInventory, sysUserId, tableName);

			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old

		} catch (Exception e) {
			LogEvent.logError("ResultInventoryDAOImpl", "insertData()", e.toString());
			throw new LIMSRuntimeException("Error in ResultInventory insertData()", e);
		}

		return true;
	}

	@Override
	public void updateData(ResultInventory resultInventory) throws LIMSRuntimeException {
		ResultInventory oldData = readResultInventory(resultInventory.getId());
		ResultInventory newData = resultInventory;

		// add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = resultInventory.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "RESULT_INVENTORY";
			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
		} catch (Exception e) {
			LogEvent.logError("ResultInventoryDAOImpl", "AuditTrail insertData()", e.toString());
			throw new LIMSRuntimeException("Error in ResultInventory AuditTrail updateData()", e);
		}

		try {
			sessionFactory.getCurrentSession().merge(resultInventory);
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
			// sessionFactory.getCurrentSession().evict // CSL remove old(resultInventory);
			// sessionFactory.getCurrentSession().refresh // CSL remove old(resultInventory);
		} catch (Exception e) {
			LogEvent.logError("ResultInventoryDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in ResultInventory updateData()", e);
		}
	}

	@Override
	public void getData(ResultInventory resultInventory) throws LIMSRuntimeException {
		try {
			ResultInventory tmpResultInventory = (ResultInventory) sessionFactory.getCurrentSession().get(ResultInventory.class,
					resultInventory.getId());
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
			if (tmpResultInventory != null) {
				PropertyUtils.copyProperties(resultInventory, tmpResultInventory);
			} else {
				resultInventory.setId(null);
			}
		} catch (Exception e) {
			LogEvent.logError("ResultInventoryDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in ResultInventory getData()", e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ResultInventory> getResultInventorysByResult(Result result) throws LIMSRuntimeException {
		List<ResultInventory> resultInventories = null;
		try {

			String sql = "from ResultInventory r where r.resultId = :resultId";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setInteger("resultId", Integer.parseInt(result.getId()));

			resultInventories = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old

			return resultInventories;

		} catch (Exception e) {
			LogEvent.logError("ResultInventoryDAOImpl", "getResultInventoryByResult()", e.toString());
			throw new LIMSRuntimeException("Error in ResultInventory getResultInventoryByResult()", e);
		}
	}

	public ResultInventory readResultInventory(String idString) {
		ResultInventory data = null;
		try {
			data = (ResultInventory) sessionFactory.getCurrentSession().get(ResultInventory.class, idString);
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("ResultInventoryDAOImpl", "readResultInventory()", e.toString());
			throw new LIMSRuntimeException("Error in ResultInventory readResultInventory()", e);
		}

		return data;
	}

	@Override
	public ResultInventory getResultInventoryById(ResultInventory resultInventory) throws LIMSRuntimeException {
		try {
			ResultInventory re = (ResultInventory) sessionFactory.getCurrentSession().get(ResultInventory.class,
					resultInventory.getId());
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
			return re;
		} catch (Exception e) {
			LogEvent.logError("ResultInventoryDAOImpl", "getResultInventoryById()", e.toString());
			throw new LIMSRuntimeException("Error in ResultInventory getResultInventoryById()", e);
		}
	}

}