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
package us.mn.state.health.lims.common.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.dataexchange.aggregatereporting.daoimpl.ReportExternalExportDAOImpl;
import us.mn.state.health.lims.dataexchange.aggregatereporting.daoimpl.ReportQueueTypeDAOImpl;
import us.mn.state.health.lims.dataexchange.connectionTest.ConnectionTest;
import us.mn.state.health.lims.dataexchange.resultreporting.beans.ReportingConfiguration;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

public class ExchangeConfigurationService {

	private static String RESULT_REPORT_TYPE_ID;
	private static String MALARIA_CASE_TYPE_ID;

	public enum ConfigurationDomain {
		REPORT("resultReporting");

		private String domainName;

		ConfigurationDomain(String siteDomain) {
			this.domainName = siteDomain;
		}

		public String getSiteDomain() {
			return domainName;
		}
	}

	public enum ExchangeType {
		RESULT_REPORT("Result Reporting", "resultReport", true, RESULT_REPORT_TYPE_ID), 
		MALARIA_SURVEILLANCE("Malaria Surveillance", "malariaSurvaeillance", false, null), 
		MALARIA_CASE("Malaria Case Report", "malariaCase", true,	MALARIA_CASE_TYPE_ID);

		private String title;
		private boolean showbacklog = false;
		private String backlogId = null;
		private String urlTestToken;

		private ExchangeType(String title, String urlTestToken, boolean showbacklog, String backlogId) {
			this.title = title;
			this.showbacklog = showbacklog;
			this.backlogId = backlogId;
			this.urlTestToken = urlTestToken;
		}

		public String getUrlTestToken() {
			return urlTestToken;
		}

		public boolean isShowbacklog() {
			return showbacklog;
		}

		public String getTitle() {
			return title;
		}

		public String getBacklogId() {
			return backlogId;
		}
	}

	private ConfigurationDomain domain;
	private ExchangeType exchangeType;

	private static Map<String, ExchangeType> dbNameToExchangeTypeMap = new HashMap<String, ExchangeType>();
	private static Map<String, ExchangeType> testTokenToExchangeTypeMap = new HashMap<String, ExchangeType>();
	private static Map<String, ConfigurationDomain> testTokenToDomainMap = new HashMap<String, ConfigurationDomain>();

	static {
		RESULT_REPORT_TYPE_ID = new ReportQueueTypeDAOImpl().getReportQueueTypeByName("Results").getId();
		MALARIA_CASE_TYPE_ID = new ReportQueueTypeDAOImpl().getReportQueueTypeByName("malariaCase").getId();

		dbNameToExchangeTypeMap.put("resultReporting", ExchangeType.RESULT_REPORT);
		dbNameToExchangeTypeMap.put("malariaSurReport", ExchangeType.MALARIA_SURVEILLANCE);
		dbNameToExchangeTypeMap.put("malariaCaseReport", ExchangeType.MALARIA_CASE);

		if (ExchangeType.RESULT_REPORT.getUrlTestToken() != null) {
			String token = ExchangeType.RESULT_REPORT.getUrlTestToken();
			testTokenToExchangeTypeMap.put(token, ExchangeType.RESULT_REPORT);
			testTokenToDomainMap.put(token, ConfigurationDomain.REPORT);
		}
		if (ExchangeType.MALARIA_CASE.getUrlTestToken() != null) {
			String token = ExchangeType.MALARIA_CASE.getUrlTestToken();
			testTokenToExchangeTypeMap.put(token, ExchangeType.MALARIA_CASE);
			testTokenToDomainMap.put(token, ConfigurationDomain.REPORT);
		}
		if (ExchangeType.MALARIA_SURVEILLANCE.getUrlTestToken() != null) {
			String token = ExchangeType.MALARIA_SURVEILLANCE.getUrlTestToken();
			testTokenToExchangeTypeMap.put(token,ExchangeType.MALARIA_SURVEILLANCE);
			testTokenToDomainMap.put(token, ConfigurationDomain.REPORT);
		}

	}

	public ExchangeConfigurationService(ConfigurationDomain domain) {
		this.domain = domain;
	}
	
	public ExchangeConfigurationService(String urlTestToken){
		domain = testTokenToDomainMap.get(urlTestToken);
		exchangeType = testTokenToExchangeTypeMap.get(urlTestToken);
	}

	public List<ReportingConfiguration> getConfigurations() {

		List<SiteInformation> informationList = new SiteInformationDAOImpl().getPageOfSiteInformationByDomainName(0,
				domain.getSiteDomain());

		Collections.sort(informationList, new Comparator<SiteInformation>() {
			@Override
			public int compare(SiteInformation o1, SiteInformation o2) {
				return o1.getGroup() - o2.getGroup();
			}
		});

		int group = informationList.get(0).getGroup();
		List<ReportingConfiguration> reports = new ArrayList<ReportingConfiguration>();
		ReportingConfiguration configuration = new ReportingConfiguration();
		reports.add(configuration);

		for (SiteInformation information : informationList) {
			if (group != information.getGroup()) {
				group = information.getGroup();
				configuration = new ReportingConfiguration();
				reports.add(configuration);
			}
			if ("url".equals(information.getTag())) {
				configuration.setUrl(information.getValue());
				configuration.setUrlId(information.getId());
			} else if ("enable".equals(information.getTag())) {
				configuration.setEnabled(getReportingEnabled(information.getValue()));
				configuration.setEnabledId(information.getId());

				ExchangeType exchangeType = dbNameToExchangeTypeMap.get(information.getName());
				if (exchangeType != null) {
					// System.out.println(information.getName());
					configuration.setTitle(exchangeType.getTitle());
					configuration.setConnectionTestIdentifier(exchangeType.getUrlTestToken());
					if (exchangeType.isShowbacklog()) {
						configuration.setShowBacklog(true);
						configuration.setBacklogSize(getReportingBacklogSize(exchangeType.getBacklogId()));
					}
				}

			}

			if (information.getSchedule() != null) {
				configuration.setIsScheduled(true);
				configuration.setSchedulerId(information.getSchedule().getId());

				String cronString = information.getSchedule().getCronStatement();

				if (!"never".equals(cronString)) {
					String[] cronParts = cronString.split(" ");
					int minutes = Integer.parseInt(cronParts[1]);
					configuration.setScheduleHours(cronParts[2]);
					configuration.setScheduleMin(String.valueOf((int) (minutes / 10) * 10));
				}
			}
		}

		return reports;
	}

	private String getReportingBacklogSize(String reportType) {
		int size = new ReportExternalExportDAOImpl().getUnsentReportExports(reportType).size();
		return String.valueOf(size);
	}

	private String getReportingEnabled(String value) {
		return ("true".equals(value) || "enable".equals(value)) ? "enable" : "disable";
	}

	public String testConnection(String url) {
		if( GenericValidator.isBlankOrNull(url)){
			return StringUtil.getMessageForKey("connection.test.error.missingURL");
		}
		
		ConnectionTest connectionTest = new ConnectionTest();
		return connectionTest.testURL(url);
	}
}
