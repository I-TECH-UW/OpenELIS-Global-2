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
package org.openelisglobal.provider.daoimpl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.person.valueholder.Person;
import org.openelisglobal.provider.dao.ProviderDAO;
import org.openelisglobal.provider.valueholder.Provider;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diane benz
 */
@Component
@Transactional
public class ProviderDAOImpl extends BaseDAOImpl<Provider, String> implements ProviderDAO {

    public ProviderDAOImpl() {
        super(Provider.class);
    }

//	@Override
//	public void deleteData(List providers) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < providers.size(); i++) {
//				Provider data = (Provider) providers.get(i);
//
//				Provider oldData = readProvider(data.getId());
//				Provider newData = new Provider();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "PROVIDER";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ProviderDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Provider AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < providers.size(); i++) {
//				Provider data = (Provider) providers.get(i);
//				// bugzilla 2206
//				data = readProvider(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ProviderDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Provider deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(Provider provider) throws LIMSRuntimeException {
//
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(provider);
//			provider.setId(id);
//
//			String sysUserId = provider.getSysUserId();
//			String tableName = "PROVIDER";
//			auditDAO.saveNewHistory(provider, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			LogEvent.logError("ProviderDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in Provider insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(Provider provider) throws LIMSRuntimeException {
//
//		Provider oldData = readProvider(provider.getId());
//		Provider newData = provider;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = provider.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "PROVIDER";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ProviderDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Provider AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(provider);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(provider);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(provider);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ProviderDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Provider updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(Provider provider) throws LIMSRuntimeException {
        try {
            Provider prov = entityManager.unwrap(Session.class).get(Provider.class, provider.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (prov != null) {
                PropertyUtils.copyProperties(provider, prov);
            } else {
                provider.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Provider getData()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Provider> getAllProviders() throws LIMSRuntimeException {
        List<Provider> list = new Vector<>();
        try {
            String sql = "from Provider";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Provider getAllProviders()", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Provider> getPageOfProviders(int startingRecNo) throws LIMSRuntimeException {
        List<Provider> list = new Vector<>();
        try {
            // calculate maxRow to be one more than the page size
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            String sql = "from Provider p order by p.id";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Provider getPageOfProviders()", e);
        }

        return list;
    }

    public Provider readProvider(String idString) {
        Provider provider = null;
        try {
            provider = entityManager.unwrap(Session.class).get(Provider.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Provider readProvider()", e);
        }

        return provider;

    }

    @Override

    @Transactional(readOnly = true)
    public Provider getProviderByPerson(Person person) throws LIMSRuntimeException {
        List<Provider> list = null;
        try {
            String sql = "from Provider p where p.person.id = :personId";
            Query<Provider> query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("personId", Integer.parseInt(person.getId()));

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Provider getProviderByPerson()", e);
        }

        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}