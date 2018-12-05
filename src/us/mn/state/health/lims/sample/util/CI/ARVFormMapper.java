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

public class ARVFormMapper extends BaseProjectFormMapper implements IProjectFormMapper {
		
	private final String projectCode = StringUtil.getMessageForKey("sample.entry.project.LART");

    public ARVFormMapper(String projectFormId, BaseActionForm dynaForm) {	
		super(projectFormId, dynaForm);
	}
		
	@Override
	public String getProjectCode() {
		return this.projectCode;
	}
			
	public List<Test> getDryTubeTests(){
		List<Test> testList = new ArrayList<Test>();
				
		if (projectData.getSerologyHIVTest()){
            CollectionUtils.addIgnoreNull(testList, createTest("Vironostika", true ));
			CollectionUtils.addIgnoreNull(testList, createTest("Murex", true ));
			CollectionUtils.addIgnoreNull(testList, createTest("Integral", true ));
		}	
		if (projectData.getCreatinineTest()){
			CollectionUtils.addIgnoreNull(testList, createTest("Créatininémie", true ));
		}
		if (projectData.getGlycemiaTest()){
			CollectionUtils.addIgnoreNull(testList, createTest("Glycémie", true ));
		}
		
		if (projectData.getTransaminaseTest()){
			CollectionUtils.addIgnoreNull(testList, createTest("Transaminases ALTL", true ));
			CollectionUtils.addIgnoreNull(testList, createTest("Transaminases ASTL", true ));
		}
		return testList;
	}
	
	public List<Test> getEDTATubeTests(BaseActionForm dynaForm){
		List<Test> testList = new ArrayList<Test>();
				
		if (projectData.getNfsTest()){
			CollectionUtils.addIgnoreNull(testList, createTest("GB", true ));
			CollectionUtils.addIgnoreNull(testList, createTest("Neut %", true ));
			CollectionUtils.addIgnoreNull(testList, createTest("Lymph %", true ));
			CollectionUtils.addIgnoreNull(testList, createTest("Mono %", true ));
			CollectionUtils.addIgnoreNull(testList, createTest("Eo %", true ));
			CollectionUtils.addIgnoreNull(testList, createTest("Baso %", true ));
			CollectionUtils.addIgnoreNull(testList, createTest("GR", true ));
			CollectionUtils.addIgnoreNull(testList, createTest("Hb", true ));
			CollectionUtils.addIgnoreNull(testList, createTest("HCT", true ));
			CollectionUtils.addIgnoreNull(testList, createTest("VGM", true ));
			CollectionUtils.addIgnoreNull(testList, createTest("TCMH", true ));
			CollectionUtils.addIgnoreNull(testList, createTest("CCMH", true ));
			CollectionUtils.addIgnoreNull(testList, createTest("PLQ", true ));
		}
		if (projectData.getCd4cd8Test()){
			CollectionUtils.addIgnoreNull(testList, createTest("CD3 percentage count", true ));
			CollectionUtils.addIgnoreNull(testList, createTest("CD4 percentage count", true ));
		}
		if (projectData.getViralLoadTest()){
		    CollectionUtils.addIgnoreNull(testList, createTest("Viral Load", true ));
		}
		
		if (projectData.getGenotypingTest()){
		    CollectionUtils.addIgnoreNull(testList, createTest("Génotypage", true ));
		}
		
		return testList;
	}
		
	@Override
	public ArrayList<TypeOfSampleTests> getTypeOfSampleTests(){
		ArrayList<TypeOfSampleTests> sItemTests = new ArrayList<TypeOfSampleTests>();
		List<Test> testList;
		
		//Check for Dry Tube Tests
	    if ( projectData.getDryTubeTaken() ) { 
			testList = getDryTubeTests();
			sItemTests.add( new TypeOfSampleTests(getTypeOfSample("Dry Tube"), testList));
	    }
		
		//Check for EDTA Tubes Tests
	    if ( projectData.getEdtaTubeTaken()) {
			testList = getEDTATubeTests(dynaForm);		
			sItemTests.add( new TypeOfSampleTests(getTypeOfSample("EDTA Tube"), testList));
        }			
		
		if (projectData.getDbsTaken()) {
		    if (projectData.getDnaPCR()) {
                testList = getDBSTests();
                sItemTests.add( new TypeOfSampleTests(getTypeOfSample("DBS"), testList));
            } 
       }
		
		//Check for DBS Tubes Tests for Viral Load
		if (projectData.getdbsvlTaken()){
			testList = getEDTATubeTests(dynaForm);		
			sItemTests.add( new TypeOfSampleTests(getTypeOfSample("DBS"), testList));
           }	
		
		
		
		return sItemTests;
	}	

	   public List<Test> getDBSTests(){
	        List<Test> testList = new ArrayList<Test>();

	        if (projectData.getDnaPCR()){
	            CollectionUtils.addIgnoreNull(testList, createTest("DNA PCR", true ));
	        }
	        
	         
	        return testList;
	    }

    /**
     * @see us.mn.state.health.lims.sample.util.CI.BaseProjectFormMapper#getSampleCenterCode()
     */
    @Override
    public String getSampleCenterCode() {
        return projectData.getARVcenterCode();
    }
}
