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
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 *
 */
package us.mn.state.health.lims.dataexchange.aggregatereporting.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.scheduler.LateStartScheduler;
import us.mn.state.health.lims.scheduler.dao.CronSchedulerDAO;
import us.mn.state.health.lims.scheduler.daoimpl.CronSchedulerDAOImpl;
import us.mn.state.health.lims.scheduler.independentthreads.TestUsageBacklog;
import us.mn.state.health.lims.scheduler.valueholder.CronScheduler;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

public class TestUsageConfigurationUpdateAction extends BaseAction {
	private static final String SEND_SITE_INDICATORS = "sendSiteIndicators";
	private static final String NEVER = "never";
	private static final String CRON_POSTFIX = "? * *";
	private static final String CRON_PREFIX = "0 ";
	private static CronSchedulerDAO cronScheduleDAO = new CronSchedulerDAOImpl();
	private static SiteInformationDAO siteInformationDAO = new SiteInformationDAOImpl();

	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaActionForm dynaForm = (DynaActionForm) (form);

		boolean sendingEnabled = "enable".equals(dynaForm.getString("enableSending"));

		String serviceUrl = dynaForm.getString("url");
		String serviceName = dynaForm.getString("serviceUserName");
		String servicePassword = dynaForm.getString("servicePassword");

		CronScheduler sendScheduler = getSendScheduler();
		sendScheduler.setActive(sendingEnabled);
		sendScheduler.setCronStatement(createCronStatement(dynaForm.getString("sendHour"), dynaForm.getString("sendMin"), true));
		sendScheduler.setSysUserId(currentUserId);

		SiteInformation targetUrl = getSiteInformationFor("testUsageAggregationUrl", serviceUrl, false);
		SiteInformation name = getSiteInformationFor("testUsageAggregationUserName", serviceName, false);
		SiteInformation password = getSiteInformationFor("testUsageAggregationPassword", servicePassword, true);
		SiteInformation enableSending = getSiteInformationFor("testUsageReporting", sendingEnabled ? "true" : "false", false);
		boolean refreash = false;
		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			updateSchedule(sendScheduler);
			updateSite(targetUrl);
			updateSite(name);
			updateSite(password);
			updateSite(enableSending);

			tx.commit();

			refreash = true;
		} catch (HibernateException e) {
			tx.rollback();
		}

		if( refreash){
			ConfigurationProperties.forceReload();
			new LateStartScheduler().restartSchedules();
			new TestUsageBacklog().run();
		}
		
		return mapping.findForward(FWD_SUCCESS);
	}

	private void updateSite(SiteInformation siteInfo) {
		if (siteInfo.getId() == null) {
			siteInformationDAO.insertData(siteInfo);
		} else {
			siteInformationDAO.updateData(siteInfo);
		}
	}

	private void updateSchedule(CronScheduler scheduler) {
		if (scheduler.getId() == null) {
			cronScheduleDAO.insert(scheduler);
		} else {
			cronScheduleDAO.update(scheduler);
		}
	}

	private SiteInformation getSiteInformationFor(String siteName, String value, boolean encrypted) {
		SiteInformation siteInformation = siteInformationDAO.getSiteInformationByName(siteName);

		if (siteInformation.getId() == null) {
			siteInformation = new SiteInformation();
			siteInformation.setName(siteName);
			siteInformation.setEncrypted(encrypted);
		}

		siteInformation.setValue(value);
		siteInformation.setSysUserId(currentUserId);

		return siteInformation;
	}

	private String createCronStatement(String hour, String min, boolean tweak) {
		StringBuilder cronBuilder = new StringBuilder();

		if (GenericValidator.isBlankOrNull(hour) || GenericValidator.isBlankOrNull(min)) {
			cronBuilder.append(NEVER);
		} else {
			cronBuilder.append(CRON_PREFIX);
			if (tweak) {
				int minute = Math.min(Integer.parseInt(min) + (int) (Math.random() * 9.0), 59);
				cronBuilder.append(String.valueOf(minute));
			}else{
				cronBuilder.append(min);
			}
			cronBuilder.append(" ");
			cronBuilder.append(hour);
			cronBuilder.append(" ");
			cronBuilder.append(CRON_POSTFIX);
		}

		return cronBuilder.toString();
	}

	private CronScheduler getSendScheduler() {
		CronScheduler sendScheduler = cronScheduleDAO.getCronScheduleByJobName(SEND_SITE_INDICATORS);

		if (sendScheduler == null) {
			sendScheduler = new CronScheduler();
			sendScheduler.setJobName(SEND_SITE_INDICATORS);
			sendScheduler.setName("send site indicators");
		}
		return sendScheduler;
	}

	@Override
	protected String getPageTitleKey() {
		return "testusageconfiguration.browse.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "testusageconfiguration.browse.title";
	}

}
