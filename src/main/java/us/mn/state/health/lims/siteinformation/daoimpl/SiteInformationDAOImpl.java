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
package us.mn.state.health.lims.siteinformation.daoimpl;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

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
//		} catch (Exception e) {
//			LogEvent.logError("SiteInformationDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in SiteInformation AuditTrail deleteData()", e);
//		}
//
//		try {
//			SiteInformation siteInformation = readSiteInformation(siteInformationId);
//			sessionFactory.getCurrentSession().delete(siteInformation);
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//
//		} catch (Exception e) {
//			LogEvent.logError("SiteInformationsDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in SiteInformation deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(SiteInformation siteInformation) throws LIMSRuntimeException {
//
//		try {
//			String id = (String) sessionFactory.getCurrentSession().save(siteInformation);
//			siteInformation.setId(id);
//
//			auditDAO.saveNewHistory(siteInformation, siteInformation.getSysUserId(), "SITE_INFORMATION");
//
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//
//		} catch (Exception e) {
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
//		} catch (Exception e) {
//			LogEvent.logError("SiteInformationDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SiteInformation AuditTrail updateData()", e);
//		}
//
//		try {
//			sessionFactory.getCurrentSession().merge(siteInformation);
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			// sessionFactory.getCurrentSession().evict // CSL remove old(siteInformation);
//			// sessionFactory.getCurrentSession().refresh // CSL remove
//			// old(siteInformation);
//		} catch (Exception e) {
//			LogEvent.logError("SiteInformationsDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SiteInformation updateData()", e);
//		}
//	}

	@Override
	public void getData(SiteInformation siteInformation) throws LIMSRuntimeException {
		try {
			SiteInformation tmpSiteInformation = sessionFactory.getCurrentSession().get(SiteInformation.class,
					siteInformation.getId());
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
			if (tmpSiteInformation != null) {
				PropertyUtils.copyProperties(siteInformation, tmpSiteInformation);
			} else {
				siteInformation.setId(null);
			}
		} catch (Exception e) {
			LogEvent.logError("SiteInformationsDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in SiteInformation getData()", e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SiteInformation> getAllSiteInformation() throws LIMSRuntimeException {
		List<SiteInformation> list;
		try {
			String sql = "from SiteInformation";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("SiteInformationsDAOImpl", "getAllSiteInformation()", e.toString());
			throw new LIMSRuntimeException("Error in SiteInformation getAllSiteInformation()", e);
		}

		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SiteInformation> getPageOfSiteInformationByDomainName(int startingRecNo, String domainName)
			throws LIMSRuntimeException {
		List<SiteInformation> list = null;
		try {

			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			String sql = "from SiteInformation si where si.domain.name = :domainName order by si.name";
//			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setString("domainName", domainName);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);

			list = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			handleException(e, "getPageOfSiteInformationByDomainName");
		}

		return list;
	}

	public SiteInformation readSiteInformation(String idString) {
		SiteInformation recoveredSiteInformation;
		try {
			recoveredSiteInformation = sessionFactory.getCurrentSession().get(SiteInformation.class, idString);
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("SiteInformationDAOImpl", "readSiteInformation()", e.toString());
			throw new LIMSRuntimeException("Error in SiteInformation readSiteInformation()", e);
		}

		return recoveredSiteInformation;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SiteInformation> getNextSiteInformationRecord(String id) throws LIMSRuntimeException {
		return getNextRecord(id, "SiteInformation", SiteInformation.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SiteInformation> getPreviousSiteInformationRecord(String id) throws LIMSRuntimeException {
		return getPreviousRecord(id, "SiteInformation", SiteInformation.class);
	}

	@Override
	public SiteInformation getSiteInformationByName(String siteName) throws LIMSRuntimeException {
		String sql = "From SiteInformation si where name = :name";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
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
	public int getCountForDomainName(String domainName) throws LIMSRuntimeException {
		String sql = "select count(*) from SiteInformation si where si.domain.name = :domainName";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
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
	public SiteInformation getSiteInformationById(String id) throws LIMSRuntimeException {

		try {
			SiteInformation info = sessionFactory.getCurrentSession().get(SiteInformation.class, id);
			// closeSession(); // CSL remove old
			return info;
		} catch (HibernateException e) {
			handleException(e, "getSiteInformationById");
		}

		return null;
	}

	@Override
	public List<SiteInformation> getSiteInformationByDomainName(String domainName) {
		String sql = "From SiteInformation si where si.domain.name = :domainName";
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
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