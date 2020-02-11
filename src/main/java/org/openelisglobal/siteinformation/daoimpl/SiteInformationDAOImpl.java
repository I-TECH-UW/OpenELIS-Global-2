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
package org.openelisglobal.siteinformation.daoimpl;

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
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.siteinformation.dao.SiteInformationDAO;
import org.openelisglobal.siteinformation.valueholder.SiteInformation;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SiteInformationDAOImpl extends BaseDAOImpl<SiteInformation, String> implements SiteInformationDAO {

    public SiteInformationDAOImpl() {
        super(SiteInformation.class);
    }

//	@Override
//	public void deleteData(String siteInformationId, String currentUserId) throws LIMSRuntimeException {
//
//		try {
//
//			SiteInformation oldData = readSiteInformation(siteInformationId);
//			SiteInformation newData = new SiteInformation();
//
//			auditDAO.saveHistory(newData, oldData, currentUserId, IActionConstants.AUDIT_TRAIL_DELETE,
//					"SITE_INFORMATION");
//
//		} catch (RuntimeException e) {
//			LogEvent.logError("SiteInformationDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in SiteInformation AuditTrail deleteData()", e);
//		}
//
//		try {
//			SiteInformation siteInformation = readSiteInformation(siteInformationId);
//			entityManager.unwrap(Session.class).delete(siteInformation);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			LogEvent.logError("SiteInformationsDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in SiteInformation deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(SiteInformation siteInformation) throws LIMSRuntimeException {
//
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(siteInformation);
//			siteInformation.setId(id);
//
//			auditDAO.saveNewHistory(siteInformation, siteInformation.getSysUserId(), "SITE_INFORMATION");
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			LogEvent.logError("SiteInformationDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in SiteInformation insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(SiteInformation siteInformation) throws LIMSRuntimeException {
//
//		SiteInformation oldData = readSiteInformation(siteInformation.getId());
//
//		try {
//
//			auditDAO.saveHistory(siteInformation, oldData, siteInformation.getSysUserId(),
//					IActionConstants.AUDIT_TRAIL_UPDATE, "SITE_INFORMATION");
//		} catch (RuntimeException e) {
//			LogEvent.logError("SiteInformationDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SiteInformation AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(siteInformation);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(siteInformation);
//			// entityManager.unwrap(Session.class).refresh // CSL remove
//			// old(siteInformation);
//		} catch (RuntimeException e) {
//			LogEvent.logError("SiteInformationsDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SiteInformation updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(SiteInformation siteInformation) throws LIMSRuntimeException {
        try {
            SiteInformation tmpSiteInformation = entityManager.unwrap(Session.class).get(SiteInformation.class,
                    siteInformation.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (tmpSiteInformation != null) {
                PropertyUtils.copyProperties(siteInformation, tmpSiteInformation);
            } else {
                siteInformation.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SiteInformation getData()", e);
        }
    }

    @Override

    @Transactional(readOnly = true)
    public List<SiteInformation> getAllSiteInformation() throws LIMSRuntimeException {
        List<SiteInformation> list;
        try {
            String sql = "from SiteInformation";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SiteInformation getAllSiteInformation()", e);
        }

        return list;
    }

    @Override

    @Transactional(readOnly = true)
    public List<SiteInformation> getPageOfSiteInformationByDomainName(int startingRecNo, String domainName)
            throws LIMSRuntimeException {
        List<SiteInformation> list = new ArrayList<>();
        try {

            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            String sql = "from SiteInformation si where si.domain.name = :domainName order by si.name";
//			Query query = entityManager.unwrap(Session.class).createQuery(sql);
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("domainName", domainName);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            handleException(e, "getPageOfSiteInformationByDomainName");
        }

        return list;
    }

    public SiteInformation readSiteInformation(String idString) {
        SiteInformation recoveredSiteInformation;
        try {
            recoveredSiteInformation = entityManager.unwrap(Session.class).get(SiteInformation.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SiteInformation readSiteInformation()", e);
        }

        return recoveredSiteInformation;
    }

    @Override
    @Transactional(readOnly = true)
    public SiteInformation getSiteInformationByName(String siteName) throws LIMSRuntimeException {
        String sql = "From SiteInformation si where name = :name";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("name", siteName);
            SiteInformation information = (SiteInformation) query.uniqueResult();
            // closeSession(); // CSL remove old
            return information;
        } catch (HibernateException e) {
            handleException(e, "getSiteInformationByName");
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public int getCountForDomainName(String domainName) throws LIMSRuntimeException {
        String sql = "select count(*) from SiteInformation si where si.domain.name = :domainName";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("domainName", domainName);
            Integer count = ((Long) query.uniqueResult()).intValue();
            // closeSession(); // CSL remove old
            return count;
        } catch (HibernateException e) {
            handleException(e, "getSiteInformationForDomainName");
        }
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public SiteInformation getSiteInformationById(String id) throws LIMSRuntimeException {

        try {
            SiteInformation info = entityManager.unwrap(Session.class).get(SiteInformation.class, id);
            // closeSession(); // CSL remove old
            return info;
        } catch (HibernateException e) {
            handleException(e, "getSiteInformationById");
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SiteInformation> getSiteInformationByDomainName(String domainName) {
        String sql = "From SiteInformation si where si.domain.name = :domainName";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("domainName", domainName);
            List<SiteInformation> list = query.list();
            // closeSession(); // CSL remove old
            return list;
        } catch (HibernateException e) {
            handleException(e, "getSiteInformationByDomainName");
        }

        return null;
    }
}