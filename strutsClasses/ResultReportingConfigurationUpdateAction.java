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
package us.mn.state.health.lims.dataexchange.resultreporting.action;

import java.util.ArrayList;
import java.util.List;

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
import us.mn.state.health.lims.dataexchange.resultreporting.beans.ReportingConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.scheduler.LateStartScheduler;
import us.mn.state.health.lims.scheduler.dao.CronSchedulerDAO;
import us.mn.state.health.lims.scheduler.daoimpl.CronSchedulerDAOImpl;
import us.mn.state.health.lims.scheduler.valueholder.CronScheduler;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

public class ResultReportingConfigurationUpdateAction extends BaseAction {

	private SiteInformationDAO siteInformationDAO = new SiteInformationDAOImpl();
	private CronSchedulerDAO schedulerDAO = new CronSchedulerDAOImpl();
	private static final String NEVER = "never";
	private static final String CRON_POSTFIX = "? * *";
	private static final String CRON_PREFIX = "0 ";
	
	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaActionForm dynaForm = (DynaActionForm) (form);

		List<SiteInformation> informationList = new ArrayList<SiteInformation>();
		List<CronScheduler> scheduleList = new ArrayList<CronScheduler>();
		@SuppressWarnings("unchecked")
		List<ReportingConfiguration> reports = (List<ReportingConfiguration>) dynaForm.get("reports");

		for (ReportingConfiguration config : reports) {
			informationList.add(setSiteInformationFor( config.getUrl(), config.getUrlId()));
			informationList.add(setSiteInformationFor( config.getEnabled(), config.getEnabledId()));
			
			if( config.getIsScheduled()){
				CronScheduler scheduler = setScheduleInformationFor( config );
				if( scheduler != null){
					scheduleList.add(scheduler);
				}
			}
		}

		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			for (SiteInformation info : informationList) {
				siteInformationDAO.updateData(info);
			}
			
			for( CronScheduler scheduler : scheduleList){
				schedulerDAO.update(scheduler);
			}

			tx.commit();
			ConfigurationProperties.forceReload();
		} catch (HibernateException e) {
			tx.rollback();
		}

		ConfigurationProperties.forceReload();
		new LateStartScheduler().restartSchedules();
		
		return mapping.findForward(FWD_SUCCESS);
	}

	private CronScheduler setScheduleInformationFor(ReportingConfiguration config) {
		CronScheduler scheduler = schedulerDAO.getCronScheduleById( config.getSchedulerId());

		if( scheduler != null){
			String cronStatement = createCronStatement(config.getScheduleHours(), config.getScheduleMin(), false);
			scheduler.setActive("enable".equals(config.getEnabled()));		
			scheduler.setCronStatement(cronStatement);
			scheduler.setSysUserId(currentUserId);
		}
		return scheduler;
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
	private SiteInformation setSiteInformationFor(String value, String id) {
		SiteInformation siteInformation = siteInformationDAO.getSiteInformationById(id);

		if (siteInformation.getId() != null) {

			if ("boolean".equals(siteInformation.getValueType())) {
				siteInformation.setValue("enable".equals(value) ? "true" : "false");
			} else {
				siteInformation.setValue(value);
			}

			siteInformation.setSysUserId(currentUserId);
		}
		return siteInformation;
	}

	@Override
	protected String getPageTitleKey() {
		return "resultreporting.browse.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "resultreporting.browse.title";
	}

}
