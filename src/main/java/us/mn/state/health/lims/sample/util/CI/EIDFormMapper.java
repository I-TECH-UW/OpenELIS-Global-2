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
package us.mn.state.health.lims.sample.util.CI;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.test.valueholder.Test;

public class EIDFormMapper extends BaseProjectFormMapper implements IProjectFormMapper {

	private String projectCode = StringUtil.getMessageForKey("sample.entry.project.LDBS");
	private final String projectName = "Early Infant Diagnosis for HIV Study";

	public EIDFormMapper(String projectFormId, BaseForm form) {
		super(projectFormId, form);
	}

	public List<Test> getTests() {
		List<Test> testList = new ArrayList<>();

		if (projectData.getDnaPCR()) {
			CollectionUtils.addIgnoreNull(testList, createTest("DNA PCR", true));
		}

		return testList;
	}

	public String getProjectName() {
		return projectName;
	}

	@Override
	public String getProjectCode() {
		return projectCode;
	}

	@Override
	public String getOrganizationId() {
		return projectData.getEIDsiteCode();
	}

	@Override
	public ArrayList<TypeOfSampleTests> getTypeOfSampleTests() {
		ArrayList<TypeOfSampleTests> sItemTests = new ArrayList<>();
		List<Test> testList;

		// Check for DBS Tests
		if (projectData.getDnaPCR()) {
			if (projectData.getDbsTaken()) {
				testList = getTests();
				sItemTests.add(new TypeOfSampleTests(getTypeOfSample("DBS"), testList));
			}
		}

		// Check for Dry Tube Tests
		if (projectData.getDnaPCR()) {
			if (projectData.getDryTubeTaken()) {
				testList = getTests();
				sItemTests.add(new TypeOfSampleTests(getTypeOfSample("Dry Tube"), testList));
			}
		}

		return sItemTests;
	}

	/**
	 * @see us.mn.state.health.lims.sample.util.CI.BaseProjectFormMapper#getSampleCenterCode()
	 */
	@Override
	public String getSampleCenterCode() {
		return projectData.getEIDsiteCode();
	}
}
