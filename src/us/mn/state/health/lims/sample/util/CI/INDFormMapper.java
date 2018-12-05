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

import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.test.valueholder.Test;

public class INDFormMapper extends BaseProjectFormMapper implements IProjectFormMapper {

	private String projectCode = StringUtil.getMessageForKey("sample.entry.project.LIND");
	private final String projectName = "Indeterminate Results";

	public INDFormMapper(String projectFormId, BaseActionForm dynaForm) {
		super(projectFormId, dynaForm);
	}

	private List<Test> getTests(BaseActionForm dynaForm){
		List<Test> testList = new ArrayList<Test>();

		if (projectData.getSerologyHIVTest()){
            CollectionUtils.addIgnoreNull(testList, createTest("Vironostika", true ));
			CollectionUtils.addIgnoreNull(testList, createTest("Murex", true));
			CollectionUtils.addIgnoreNull(testList, createTest("Integral", true));
		}

		return testList;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public String getProjectCode() {
		return this.projectCode;
	}

	public String getOrganizationId() {
		return projectData.getINDsiteName();	
	}

	public ArrayList<TypeOfSampleTests> getTypeOfSampleTests() {
		ArrayList<TypeOfSampleTests> sItemTests = new ArrayList<TypeOfSampleTests>();
		List<Test> testList = new ArrayList<Test>();


		//Check for Dry Tube Tests
		if (projectData.getSerologyHIVTest() ) {
		    if ( projectData.getDryTubeTaken() ) {     
    			testList = getTests(dynaForm);
    			sItemTests.add( new TypeOfSampleTests(getTypeOfSample("Dry Tube"), testList));
		    }
		}

		return sItemTests;
	}
    /**
     * @see us.mn.state.health.lims.sample.util.CI.BaseProjectFormMapper#getSampleCenterCode()
     */
    @Override
    public String getSampleCenterCode() {
        return projectData.getINDsiteCode();
    }
}
