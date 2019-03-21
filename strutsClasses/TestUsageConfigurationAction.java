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

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.dataexchange.aggregatereporting.dao.ReportExternalExportDAO;
import us.mn.state.health.lims.dataexchange.aggregatereporting.daoimpl.ReportExternalExportDAOImpl;
import us.mn.state.health.lims.scheduler.dao.CronSchedulerDAO;
import us.mn.state.health.lims.scheduler.daoimpl.CronSchedulerDAOImpl;
import us.mn.state.health.lims.scheduler.valueholder.CronScheduler;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

public class TestUsageConfigurationAction extends BaseAction {
	
	private static CronSchedulerDAO cronSchedulerDAO = new CronSchedulerDAOImpl();
	private static SiteInformationDAO siteInfoDAO = new SiteInformationDAOImpl();
	private static ReportExternalExportDAO reportDAO = new ReportExternalExportDAOImpl();
	
	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "true");
		request.setAttribute(NEXT_DISABLED, "true");
		request.getSession().setAttribute(SAVE_DISABLED, "false");
		
		DynaActionForm dynaForm = (DynaActionForm)(form);
		dynaForm.initialize(mapping);

		PropertyUtils.setProperty(dynaForm, "hourList", DisplayListService.getList(ListType.HOURS));
		PropertyUtils.setProperty(dynaForm, "minList", DisplayListService.getList(ListType.MINS));
			
		setSchedulerProperties(dynaForm, "sendSiteIndicators", "enableSending", "sendMin", "sendHour");
		setSiteProperties(dynaForm, "testUsageAggregationUrl", "url");
		setSiteProperties(dynaForm, "testUsageAggregationUserName", "serviceUserName");
		setSiteProperties(dynaForm, "testUsageAggregationPassword", "servicePassword");
		
		PropertyUtils.setProperty(dynaForm, "lastAttemptToSend", getLastEvent("sendSiteIndicators"));
		PropertyUtils.setProperty(dynaForm, "lastSent", getLastSent());
		PropertyUtils.setProperty(dynaForm, "sendStatus", getSendStatus());
		

		return mapping.findForward(FWD_SUCCESS);
	}

	private Object getLastEvent(String cronJobName) {
		CronScheduler scheduler = cronSchedulerDAO.getCronScheduleByJobName(cronJobName);
		
		if( scheduler.getLastRun() != null){
			return DateUtil.convertTimestampToStringDateAndTime(scheduler.getLastRun());
		}

		return StringUtil.getMessageForKey("schedule.never");
	}

	private String getLastSent() {
		Timestamp lastTimestamp = reportDAO.getLastSentTimestamp();
		
		if( lastTimestamp != null){
			return DateUtil.convertTimestampToStringDateAndTime(lastTimestamp);
		}
		
		return StringUtil.getMessageForKey("schedule.never");
	}
	private Object getSendStatus() {
		SiteInformation sendStatusInfo = siteInfoDAO.getSiteInformationByName("testUsageSendStatus");
		return sendStatusInfo == null ? "N/A" : sendStatusInfo.getValue();
	}

	private void setSiteProperties(DynaActionForm dynaForm, String siteName, String bean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		SiteInformation site = siteInfoDAO.getSiteInformationByName(siteName);
		
		if( site != null ){
			PropertyUtils.setProperty(dynaForm, bean, site.getValue());
		}
	}

	private void setSchedulerProperties(DynaActionForm dynaForm, String indicatorName, String enable, String min, String hour) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		CronScheduler sendScheduler = cronSchedulerDAO.getCronScheduleByJobName(indicatorName);
		
		if( sendScheduler == null ){
			PropertyUtils.setProperty(dynaForm, enable, "enable");	
		}else{
			PropertyUtils.setProperty(dynaForm, enable, sendScheduler.getActive() ? "enable" : "disable");
			
			String cronString = sendScheduler.getCronStatement();
			
			if(!"never".equals( cronString)){
				String[] cronParts = cronString.split(" ");
				int minutes = Integer.parseInt(cronParts[1]);
				
				PropertyUtils.setProperty(dynaForm, min, String.valueOf((int)(minutes/10) * 10));
				PropertyUtils.setProperty(dynaForm, hour, cronParts[2]);
			}
		}
		
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
