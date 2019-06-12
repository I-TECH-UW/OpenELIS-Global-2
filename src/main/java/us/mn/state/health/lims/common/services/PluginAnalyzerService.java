/*
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
 */

package us.mn.state.health.lims.common.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.analyzer.AnalyzerService;
import spring.service.analyzerimport.AnalyzerTestMappingService;
import spring.service.test.TestService;
import us.mn.state.health.lims.analyzer.valueholder.Analyzer;
import us.mn.state.health.lims.analyzerimport.analyzerreaders.AnalyzerLineReader;
import us.mn.state.health.lims.analyzerimport.util.AnalyzerTestNameCache;
import us.mn.state.health.lims.analyzerimport.valueholder.AnalyzerTestMapping;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.plugin.AnalyzerImporterPlugin;
import us.mn.state.health.lims.test.valueholder.Test;

@Service
public class PluginAnalyzerService {

	private static PluginAnalyzerService INSTANCE;

	@Autowired
	private AnalyzerTestMappingService analyzerMappingService;
	@Autowired
	private AnalyzerService analyzerService;
	@Autowired
	private TestService testService;

	private List<AnalyzerTestMapping> existingMappings;

	@PostConstruct
	private void registerInstance() {
		INSTANCE = this;
	}

	public static PluginAnalyzerService getInstance() {
		return INSTANCE;
	}

	public void registerAnalyzer(AnalyzerImporterPlugin analyzer) {
		AnalyzerLineReader.registerAnalyzerPlugin(analyzer);
	}

	@Transactional
	public String addAnalyzerDatabaseParts(String name, String description, List<TestMapping> nameMappings) {
		Analyzer analyzer = analyzerService.getAnalyzerByName(name);
		if (analyzer != null && analyzer.getId() != null) {
			analyzer.setActive(true);
			registerAanlyzerInCache(name, analyzer.getId());
		} else {
			if (analyzer == null) {
				analyzer = new Analyzer();
				analyzer.setActive(true);
				analyzer.setName(name);
			}
			analyzer.setDescription(description);
		}

		List<AnalyzerTestMapping> testMappings = createTestMappings(nameMappings);
		if (!testMappings.isEmpty() && existingMappings == null) {
			existingMappings = analyzerMappingService.getAll();
		}

		analyzer.setSysUserId("1");

		try {
			analyzerService.persistData(analyzer, testMappings, existingMappings);
			registerAanlyzerInCache(name, analyzer.getId());
		} catch (Exception lre) {
			LogEvent.logErrorStack(this.getClass().getSimpleName(), "addAnalyzerDatabaseParts", lre);
		}
		return analyzer.getId();
	}

	private List<AnalyzerTestMapping> createTestMappings(List<TestMapping> nameMappings) {
		ArrayList<AnalyzerTestMapping> testMappings = new ArrayList<>();
		for (TestMapping names : nameMappings) {
			String testId = getIdForTestName(names.getDbbTestName());

			AnalyzerTestMapping analyzerMapping = new AnalyzerTestMapping();
			analyzerMapping.setAnalyzerTestName(names.getAnalyzerTestName());
			analyzerMapping.setTestId(testId);
			testMappings.add(analyzerMapping);
		}
		return testMappings;
	}

	private String getIdForTestName(String dbbTestName) {
		Test test = testService.getTestByName(dbbTestName);
		if (test != null) {
			return test.getId();
		}
		LogEvent.logError("PluginAnalyzerService", "createTestMappings",
				"Unable to find test " + dbbTestName + " in test catalog");
		return null;
	}

	private void registerAanlyzerInCache(String name, String id) {
		AnalyzerTestNameCache.instance().registerPluginAnalyzer(name, id);
	}

	public static class TestMapping {
		private final String analyzerTestName;
		private final String dbbTestName;

		public TestMapping(String analyzerTestName, String dbbTestName) {
			this.analyzerTestName = analyzerTestName;
			this.dbbTestName = dbbTestName;
		}

		public String getAnalyzerTestName() {
			return analyzerTestName;
		}

		public String getDbbTestName() {
			return dbbTestName;
		}
	}

}
