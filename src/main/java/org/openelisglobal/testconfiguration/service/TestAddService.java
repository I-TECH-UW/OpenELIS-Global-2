package org.openelisglobal.testconfiguration.service;

import java.util.List;

import org.openelisglobal.testconfiguration.controller.TestAddController.TestSet;
import org.openelisglobal.localization.valueholder.Localization;

public interface TestAddService {

	void addTests(List<TestSet> testSets, Localization nameLocalization, Localization reportingNameLocalization,
			String currentUserId);

}
