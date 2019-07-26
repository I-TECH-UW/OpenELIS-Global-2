package org.openelisglobal.testconfiguration.service;

import java.util.List;

import org.openelisglobal.testconfiguration.controller.TestModifyEntryController.TestAddParams;
import org.openelisglobal.testconfiguration.controller.TestModifyEntryController.TestSet;
import org.openelisglobal.localization.valueholder.Localization;

public interface TestModifyService {

	void updateTestSets(List<TestSet> testSets, TestAddParams testAddParams, Localization nameLocalization,
			Localization reportingNameLocalization, String currentUserId);

}
