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

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

public class SiteInformationDAOImpl extends BaseDAOImpl implements SiteInformationDAO {

	public void deleteData(String siteInformationId, String currentUserId) throws LIMSRuntimeException {

		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
						
				SiteInformation oldData = readSiteInformation(siteInformationId);
				SiteInformation newData = new SiteInformation();

				auditDAO.saveHistory(newData,oldData, currentUserId ,IActionConstants.AUDIT_TRAIL_DELETE, "SITE_INFORMATION");

		}  catch (Exception e) {
			LogEvent.logError("SiteInformationDAOImpl","AuditTrail deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in SiteInformation AuditTrail deleteData()", e);
		}  
		
		try {		
				SiteInformation siteInformation = readSiteInformation(siteInformationId);
				HibernateUtil.getSession().delete(siteInformation);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
				
		} catch (Exception e) {
			LogEvent.logError("SiteInformationsDAOImpl","deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in SiteInformation deleteData()",e);
		}
	}

	public boolean insertData(SiteInformation siteInformation) throws LIMSRuntimeException {

		try {
			String id = (String)HibernateUtil.getSession().save(siteInformation);
			siteInformation.setId(id);
			
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			auditDAO.saveNewHistory(siteInformation, siteInformation.getSysUserId(), "SITE_INFORMATION");
			
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
						
		} catch (Exception e) {
			LogEvent.logError("SiteInformationDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in SiteInformation insertData()",e);
		} 
		
		return true;
	}

	public void updateData(SiteInformation siteInformation) throws LIMSRuntimeException {
		
		SiteInformation oldData = readSiteInformation(siteInformation.getId());

		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();

			auditDAO.saveHistory(siteInformation,oldData, siteInformation.getSysUserId(), IActionConstants.AUDIT_TRAIL_UPDATE, "SITE_INFORMATION");
		}  catch (Exception e) {
			LogEvent.logError("SiteInformationDAOImpl","AuditTrail updateData()",e.toString());
			throw new LIMSRuntimeException("Error in SiteInformation AuditTrail updateData()", e);
		}  
							
		try {
			HibernateUtil.getSession().merge(siteInformation);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(siteInformation);
			HibernateUtil.getSession().refresh(siteInformation);
		} catch (Exception e) {
			LogEvent.logError("SiteInformationsDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in SiteInformation updateData()",e);
		}
	}

	public void getData(SiteInformation siteInformation) throws LIMSRuntimeException {
		try {
			SiteInformation tmpSiteInformation = (SiteInformation)HibernateUtil.getSession().get(SiteInformation.class, siteInformation.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (tmpSiteInformation != null) {
				PropertyUtils.copyProperties(siteInformation, tmpSiteInformation);
			} else {
				siteInformation.setId(null);
			}
		} catch (Exception e) {
			LogEvent.logError("SiteInformationsDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in SiteInformation getData()", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<SiteInformation> getAllSiteInformation() throws LIMSRuntimeException {
		List<SiteInformation> list;
		try {
			String sql = "from SiteInformation";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("SiteInformationsDAOImpl","getAllSiteInformation()",e.toString());
			throw new LIMSRuntimeException("Error in SiteInformation getAllSiteInformation()", e);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SiteInformation> getPageOfSiteInformationByDomainName(int startingRecNo,String domainName) throws LIMSRuntimeException {
		List<SiteInformation> list = null;
		try {

			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
			
			String sql = "from SiteInformation si where si.domain.name = :domainName order by si.name";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("domainName", domainName);
			query.setFirstResult(startingRecNo-1);
			query.setMaxResults(endingRecNo-1); 					

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			handleException(e, "getPageOfSiteInformationByDomainName");
		}

		return list;
	}

	public SiteInformation readSiteInformation(String idString) {
		SiteInformation recoveredSiteInformation;
		try {
			recoveredSiteInformation = (SiteInformation)HibernateUtil.getSession().get(SiteInformation.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("SiteInformationDAOImpl", "readSiteInformation()", e.toString());
			throw new LIMSRuntimeException("Error in SiteInformation readSiteInformation()", e);
		}			
		
		return recoveredSiteInformation;
	}	
	
	@SuppressWarnings("unchecked")
	public List<SiteInformation> getNextSiteInformationRecord(String id) throws LIMSRuntimeException {
		return getNextRecord(id, "SiteInformation", SiteInformation.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<SiteInformation> getPreviousSiteInformationRecord(String id) throws LIMSRuntimeException {
		return getPreviousRecord(id, "SiteInformation", SiteInformation.class);
	}

	@Override
	public SiteInformation getSiteInformationByName(String siteName) throws LIMSRuntimeException {
		String sql = "From SiteInformation si where name = :name";
		
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("name", siteName);
			SiteInformation information = (SiteInformation)query.uniqueResult();
			closeSession();
			return information;
		}catch(HibernateException e){
			handleException(e, "getSiteInformationByName");
		}
		
		
		return null;
	}

	@Override
	public int getCountForDomainName(String domainName) throws LIMSRuntimeException {
		String sql = "select count(*) from SiteInformation si where si.domain.name = :domainName";
		
		try {
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("domainName", domainName);
			Integer count = (Integer)query.uniqueResult();
			closeSession();
			return count;
		} catch (HibernateException e) {
			handleException(e, "getSiteInformationForDomainName");
		}
		return 0;
	}

	@Override
	public SiteInformation getSiteInformationById(String id) throws LIMSRuntimeException {
		
		try {
			SiteInformation info = (SiteInformation)HibernateUtil.getSession().get(SiteInformation.class, id);
			closeSession();
			return info;
		} catch (HibernateException e) {
			handleException(e, "getSiteInformationById");
		}
		
		return null;
	}

	public List<SiteInformation> getSiteInformationByDomainName(String domainName) {
		String sql = "From SiteInformation si where si.domain.name = :domainName";
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("domainName", domainName);
			List<SiteInformation> list = query.list();
			closeSession();
			return list;
		}catch (HibernateException e){
			handleException(e, "getSiteInformationByDomainName");
		}

		return null;
	}
}