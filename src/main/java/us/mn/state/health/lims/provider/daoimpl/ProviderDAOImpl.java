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
package us.mn.state.health.lims.provider.daoimpl;

import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.provider.dao.ProviderDAO;
import us.mn.state.health.lims.provider.valueholder.Provider;

/**
 * @author diane benz
 */
@Component
@Transactional
public class ProviderDAOImpl extends BaseDAOImpl<Provider> implements ProviderDAO {

	@Autowired
	AuditTrailDAO auditDAO;

	public ProviderDAOImpl() {
		super(Provider.class);
	}

	@Override
	public void deleteData(List providers) throws LIMSRuntimeException {
		// add to audit trail
		try {

			for (int i = 0; i < providers.size(); i++) {
				Provider data = (Provider) providers.get(i);

				Provider oldData = readProvider(data.getId());
				Provider newData = new Provider();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "PROVIDER";
				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("ProviderDAOImpl", "AuditTrail deleteData()", e.toString());
			throw new LIMSRuntimeException("Error in Provider AuditTrail deleteData()", e);
		}

		try {
			for (int i = 0; i < providers.size(); i++) {
				Provider data = (Provider) providers.get(i);
				// bugzilla 2206
				data = readProvider(data.getId());
				sessionFactory.getCurrentSession().delete(data);
				// sessionFactory.getCurrentSession().flush(); // CSL remove old
				// sessionFactory.getCurrentSession().clear(); // CSL remove old
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("ProviderDAOImpl", "deleteData()", e.toString());
			throw new LIMSRuntimeException("Error in Provider deleteData()", e);
		}
	}

	@Override
	public boolean insertData(Provider provider) throws LIMSRuntimeException {

		try {
			String id = (String) sessionFactory.getCurrentSession().save(provider);
			provider.setId(id);

			String sysUserId = provider.getSysUserId();
			String tableName = "PROVIDER";
			auditDAO.saveNewHistory(provider, sysUserId, tableName);

			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old

		} catch (Exception e) {
			LogEvent.logError("ProviderDAOImpl", "insertData()", e.toString());
			throw new LIMSRuntimeException("Error in Provider insertData()", e);
		}

		return true;
	}

	@Override
	public void updateData(Provider provider) throws LIMSRuntimeException {

		Provider oldData = readProvider(provider.getId());
		Provider newData = provider;

		// add to audit trail
		try {

			String sysUserId = provider.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "PROVIDER";
			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("ProviderDAOImpl", "AuditTrail updateData()", e.toString());
			throw new LIMSRuntimeException("Error in Provider AuditTrail updateData()", e);
		}

		try {
			sessionFactory.getCurrentSession().merge(provider);
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
			// sessionFactory.getCurrentSession().evict // CSL remove old(provider);
			// sessionFactory.getCurrentSession().refresh // CSL remove old(provider);
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("ProviderDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in Provider updateData()", e);
		}
	}

	@Override
	public void getData(Provider provider) throws LIMSRuntimeException {
		try {
			Provider prov = sessionFactory.getCurrentSession().get(Provider.class, provider.getId());
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
			if (prov != null) {
				PropertyUtils.copyProperties(provider, prov);
			} else {
				provider.setId(null);
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("ProviderDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in Provider getData()", e);
		}
	}

	@Override
	public List getAllProviders() throws LIMSRuntimeException {
		List list = new Vector();
		try {
			String sql = "from Provider";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("ProviderDAOImpl", "getAllProviders()", e.toString());
			throw new LIMSRuntimeException("Error in Provider getAllProviders()", e);
		}

		return list;
	}

	@Override
	public List getPageOfProviders(int startingRecNo) throws LIMSRuntimeException {
		List list = new Vector();
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			String sql = "from Provider p order by p.id";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);

			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("ProviderDAOImpl", "getPageOfProviders()", e.toString());
			throw new LIMSRuntimeException("Error in Provider getPageOfProviders()", e);
		}

		return list;
	}

	public Provider readProvider(String idString) {
		Provider provider = null;
		try {
			provider = sessionFactory.getCurrentSession().get(Provider.class, idString);
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("ProviderDAOImpl", "readProvider()", e.toString());
			throw new LIMSRuntimeException("Error in Provider readProvider()", e);
		}

		return provider;

	}

	@Override
	public List getNextProviderRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "Provider", Provider.class);

	}

	@Override
	public List getPreviousProviderRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "Provider", Provider.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Provider getProviderByPerson(Person person) throws LIMSRuntimeException {
		List<Provider> list = null;
		try {
			String sql = "from Provider p where p.person.id = :personId";
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setInteger("personId", Integer.parseInt(person.getId()));

			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("ProviderDAOImpl", "getProviderByPerson()", e.toString());
			throw new LIMSRuntimeException("Error in Provider getProviderByPerson()", e);
		}

		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
}