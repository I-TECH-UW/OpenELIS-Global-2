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
 * Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
 *
 */
package us.mn.state.health.lims.reports.action.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import spring.mine.common.form.BaseForm;
import spring.service.analysis.AnalysisService;
import spring.service.test.TestSectionService;
import spring.util.SpringContext;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.ValidationBacklogData;
import us.mn.state.health.lims.test.valueholder.TestSection;

/**
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 *
 * The Original Code is OpenELIS code.
 *
 * Copyright (C) CIRG, University of Washington, Seattle WA. All Rights
 * Reserved.
 *
 */
public class ValidationBacklogReport extends Report {

	private List<ValidationBacklogData> reportItems;
	private Map<String, TestBucket> sectionIdToBucketList;
	private List<TestBucket> sectionBucketList;
	private String TECH_ACCEPT_ID;
	private String USER_SELECT_SECTION_ID;

	private TestSectionService testSectionService = SpringContext.getBean(TestSectionService.class);
	private AnalysisService analysisService = SpringContext.getBean(AnalysisService.class);

	public ValidationBacklogReport() {
		TECH_ACCEPT_ID = StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance);
		TestSection testSection = testSectionService.getTestSectionByName("user");
		if (testSection != null) {
			USER_SELECT_SECTION_ID = testSection.getId();
		}
	}

	@Override
	protected String reportFileName() {
		return "ValidationBacklog";
	}

	@Override
	public JRDataSource getReportDataSource() throws IllegalStateException {
		return new JRBeanCollectionDataSource(reportItems);
	}

	@Override
	public void initializeReport(BaseForm form) {
		super.initializeReport();

		createReportParameters();
		setMapForAllSections();
		loadBuckets();
		bucketsToBeans();

	}

	private void setMapForAllSections() {
		sectionIdToBucketList = new HashMap<>();
		sectionBucketList = new ArrayList<>();

		List<TestSection> sectionList = testSectionService.getAllActiveTestSections();

		for (TestSection section : sectionList) {
			if (USER_SELECT_SECTION_ID == null || !USER_SELECT_SECTION_ID.equals(section.getId())) {
				TestBucket bucket = new TestBucket();
				bucket.testSection = section.getLocalizedName();
				sectionBucketList.add(bucket);
				sectionIdToBucketList.put(section.getId(), bucket);
			}
		}
	}

	private void loadBuckets() {
		List<Analysis> analysisList = analysisService.getAnalysesForStatusId(TECH_ACCEPT_ID);

		for (Analysis analysis : analysisList) {
			TestBucket bucket = sectionIdToBucketList.get(analysis.getTestSection().getId());
			bucket.count++;
		}
	}

	private void bucketsToBeans() {
		reportItems = new ArrayList<>();

		for (TestBucket bucket : sectionBucketList) {
			ValidationBacklogData data = new ValidationBacklogData();
			data.setTestSection(bucket.testSection);
			data.setCount(String.valueOf(bucket.count));
			reportItems.add(data);
		}

	}

	private class TestBucket {
		public String testSection;
		public int count = 0;
	}

}
