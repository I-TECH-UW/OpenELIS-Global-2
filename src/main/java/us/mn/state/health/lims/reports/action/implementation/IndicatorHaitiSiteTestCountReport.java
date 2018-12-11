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
package us.mn.state.health.lims.reports.action.implementation;

import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.dataexchange.aggregatereporting.dao.ReportExternalImportDAO;
import us.mn.state.health.lims.dataexchange.aggregatereporting.daoimpl.ReportExternalImportDAOImpl;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportExternalImport;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.TestSiteYearReport;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.TestSiteYearReport.Months;

public class IndicatorHaitiSiteTestCountReport extends CSVExportReport implements IReportCreator, IReportParameterSetter {
	private static ReportExternalImportDAO reportExternalImportDAO = new ReportExternalImportDAOImpl();
	private static String EOL = System.getProperty("line.separator");
	private static List<IdValuePair> MONTH_LIST;

	private static ContainerFactory containerFactory = new ContainerFactory() {
		public List creatArrayContainer() {
			return new ArrayList();
		}

		public Map<String, Integer> createObjectContainer() {
			return new HashMap<String, Integer>();
		}

	};

	private List<TestSiteYearReport> reportList = new ArrayList<TestSiteYearReport>();

	static {
		MONTH_LIST = new ArrayList<IdValuePair>();

		MONTH_LIST.add(new IdValuePair("0", StringUtil.getMessageForKey("month.january.abbrev")));
		MONTH_LIST.add(new IdValuePair("1", StringUtil.getMessageForKey("month.february.abbrev")));
		MONTH_LIST.add(new IdValuePair("2", StringUtil.getMessageForKey("month.march.abbrev")));
		MONTH_LIST.add(new IdValuePair("3", StringUtil.getMessageForKey("month.april.abbrev")));
		MONTH_LIST.add(new IdValuePair("4", StringUtil.getMessageForKey("month.may.abbrev")));
		MONTH_LIST.add(new IdValuePair("5", StringUtil.getMessageForKey("month.june.abbrev")));
		MONTH_LIST.add(new IdValuePair("6", StringUtil.getMessageForKey("month.july.abbrev")));
		MONTH_LIST.add(new IdValuePair("7", StringUtil.getMessageForKey("month.august.abbrev")));
		MONTH_LIST.add(new IdValuePair("8", StringUtil.getMessageForKey("month.september.abbrev")));
		MONTH_LIST.add(new IdValuePair("9", StringUtil.getMessageForKey("month.october.abbrev")));
		MONTH_LIST.add(new IdValuePair("10", StringUtil.getMessageForKey("month.november.abbrev")));
		MONTH_LIST.add(new IdValuePair("11", StringUtil.getMessageForKey("month.december.abbrev")));
	}
	
	protected String reportFileName(){
		return "SiteTestCount";
	}
	
	@Override
	public void setRequestParameters(BaseActionForm dynaForm) {
		try {
			PropertyUtils.setProperty(dynaForm, "usePredefinedDateRanges", Boolean.TRUE);
            new ReportSpecificationList( getSiteList(), StringUtil.getMessageForKey( "report.select.site" )).setRequestParameters( dynaForm );
			PropertyUtils.setProperty(dynaForm, "instructions", StringUtil.getMessageForKey("report.instruction.inventory.test.count"));
			PropertyUtils.setProperty(dynaForm, "monthList", MONTH_LIST);
			PropertyUtils.setProperty(dynaForm, "yearList", getYearList());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private List<IdValuePair> getYearList() {
		List<IdValuePair> list = new ArrayList<IdValuePair>();

		int currentYear = DateUtil.getCurrentYear();

		for (int i = 5; i >= 0; i--) {
			String year = String.valueOf(currentYear - i);
			list.add(new IdValuePair(year, year));
		}

		return list;
	}

	private List<IdValuePair> getSiteList() {
		List<IdValuePair> pairList = new ArrayList<IdValuePair>();

		List<String> sites = reportExternalImportDAO.getUniqueSites();
		for (String site : sites) {
			pairList.add(new IdValuePair(site, site));
		}

		return pairList;
	}

	@Override
	public void initializeReport(BaseActionForm dynaForm) {
		super.initializeReport();
		createReportParameters();

		String period = dynaForm.getString("datePeriod");
		ReportSpecificationList specificationList = (ReportSpecificationList)dynaForm.get("selectList");

		createResults(specificationList.getSelection(), period, dynaForm);
	}

	@SuppressWarnings("unchecked")
	private void createResults(String site, String period, BaseActionForm dynaForm) {

		Timestamp beginning = null;
		Timestamp end = DateUtil.getTimestampForBeginningOfMonthAgo( -1 );

		if ("year".equals(period)) {
			beginning = DateUtil.getTimestampForBeginingOfYear();
		} else if ("months3".equals(period)) {
			beginning = DateUtil.getTimestampForBeginningOfMonthAgo( 2 );
		} else if ("months6".equals(period)) {
			beginning = DateUtil.getTimestampForBeginningOfMonthAgo( 5 );
		} else if ("months12".equals(period)) {
			beginning = DateUtil.getTimestampForBeginningOfMonthAgo( 11 );
		} else if ("custom".equals(period)) {
			int lowYear = Integer.parseInt(dynaForm.getString("lowerYear"));
			int lowMonth = Integer.parseInt(dynaForm.getString("lowerMonth"));
			int highYear = Integer.parseInt(dynaForm.getString("upperYear"));
			int highMonth = Integer.parseInt(dynaForm.getString("upperMonth"));

			int currentYear = DateUtil.getCurrentYear();
			int currentMonth = DateUtil.getCurrentMonth();

			beginning = DateUtil.getTimestampForBeginningOfMonthAgo( currentMonth - lowMonth + ( 12 * ( currentYear - lowYear ) ) );
			end = DateUtil.getTimestampForBeginningOfMonthAgo( currentMonth - highMonth + ( 12 * ( currentYear - highYear ) ) - 1 );
		}

		List<ReportExternalImport> reportImportList;
		// get all rows for the date range sort by date and site
		if (GenericValidator.isBlankOrNull(site)) {
			reportImportList = reportExternalImportDAO.getReportsInDateRangeSorted(beginning, end);
		} else {
			reportImportList = reportExternalImportDAO.getReportsInDateRangeSortedForSite(beginning, end, site);
		}
		String currentSite = null;

		Map<String, Integer>[] monthlyTestCount = null;

		for (ReportExternalImport report : reportImportList) {
			if (!report.getSendingSite().equals(currentSite)) {
				createReportLinesForSite(currentSite, monthlyTestCount);

				monthlyTestCount = createNewMonthlyTestCountArray();
				currentSite = report.getSendingSite();
			}

			Map<String, Integer> targetMonthTestCount = monthlyTestCount[DateUtil.getMonthForTimestamp(report.getEventDate())];

			JSONParser parser = new JSONParser();

			try {
				Map<String, Integer> databaseTestCountList = (Map<String, Integer>) parser.parse(report.getData().replace("\n", ""), containerFactory);

				for (String test : databaseTestCountList.keySet()) {
					if (!targetMonthTestCount.containsKey(test)) {
						targetMonthTestCount.put(test, 0);
					}

					int current = targetMonthTestCount.get( test );
					int additional = Integer.parseInt(String.valueOf(databaseTestCountList.get(test)));
					targetMonthTestCount.put(test, current + additional );
				}

			} catch (ParseException pe) {
				System.out.println(pe);
			}

		}

		createReportLinesForSite(currentSite, monthlyTestCount);
	}

	@SuppressWarnings("unchecked")
	private Map<String, Integer>[] createNewMonthlyTestCountArray() {
		Map<String, Integer>[] newArray = (Map<String, Integer>[]) new HashMap[12];

		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = new HashMap<String, Integer>();
		}

		return newArray;
	}

	private void createReportLinesForSite(String currentSite, Map<String, Integer>[] monthlyTestCount) {
		if (monthlyTestCount == null) {
			return;
		}

		Map<String, TestSiteYearReport> testToLineMap = new HashMap<String, TestSiteYearReport>();
		for (Months month : Months.values()) {
			Map<String, Integer> testToCountMap = monthlyTestCount[month.getIndex()];

			for (String test : testToCountMap.keySet()) {
				if (!testToLineMap.containsKey(test)) {
					TestSiteYearReport newReportLine = new TestSiteYearReport();
					newReportLine.setSiteName(currentSite);
					newReportLine.setTestName(test);
					testToLineMap.put(test, newReportLine);
					reportList.add(newReportLine);
				}

				testToLineMap.get(test).addToMonth(month, testToCountMap.get(test));
			}
		}
	}
	
	public String getResponseHeaderName(){
		return "Content-Disposition";
	}
	public String getResponseHeaderContent(){
		return "attachment;filename=" + getReportFileName() + ".csv";
	}


	public byte[] runReport() throws Exception {
		if (errorFound) {
			return super.runReport();
		}


		ByteArrayOutputStream buffer = new ByteArrayOutputStream(100000);
		buffer.write(getColumnNamesLine().getBytes("windows-1252"));

		Collections.sort(reportList, new Comparator<TestSiteYearReport>() {
			@Override
			public int compare(TestSiteYearReport o1, TestSiteYearReport o2) {
				int compare = o1.getSiteName().compareTo(o2.getSiteName());

				if (compare == 0) {
					return o1.getTestName().compareTo(o2.getTestName());
				}

				return compare;
			}
		});

		for (TestSiteYearReport report : reportList) {
			buffer.write(getReportLine(report).getBytes("windows-1252"));
		}

		return buffer.toByteArray();
	}

	private String getReportLine(TestSiteYearReport report) {
		int total = 0;
		StringBuilder line = new StringBuilder();

		line.append(StringUtil.escapeCSVValue(report.getTestName()));
		line.append(",");
		line.append(StringUtil.escapeCSVValue(report.getSiteName()));
		line.append(",");

		for (Months month : Months.values()) {
			int value = report.getCountForMonth(month);
			total += value;
			if (value > 0) {
				line.append(value);
			}
			line.append(",");
		}

		line.append(String.valueOf(total));
		line.append(EOL);

		return line.toString();
	}

	private String getColumnNamesLine() {
		StringBuilder line = new StringBuilder();

		line.append(StringUtil.getMessageForKey("report.column.test"));
		line.append(",");
		line.append(StringUtil.getMessageForKey("report.column.site"));
		line.append(",");
		for (IdValuePair month : MONTH_LIST) {
			line.append(month.getValue());
			line.append(",");
		}
		line.append(StringUtil.getMessageForKey("report.column.total"));
		line.append(EOL);
		return line.toString();
	}

}
