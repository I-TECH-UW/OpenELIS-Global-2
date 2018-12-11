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
package us.mn.state.health.lims.workplan.reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.sample.util.AccessionNumberUtil;
import us.mn.state.health.lims.test.beanItems.TestResultItem;

public class TestSectionWorkplanReport implements IWorkplanReport {
    private static int PREFIX_LENGTH = AccessionNumberUtil.getAccessionNumberValidator().getInvarientLength();
	private static final String BASE_FILE_NAME = "WorkplanByTestSection";
	private static final String FILE_NAME_WITH_RESULTS = "WorkplanResultsByTestSection";
	private final HashMap<String, Object> parameterMap = new HashMap<String, Object>();
	private String testSection = "";
	private String messageKey = "banner.menu.workplan.";
	protected String reportPath = "";
	
	public TestSectionWorkplanReport(String testSection) {
		messageKey = messageKey + testSection;
		this.testSection = StringUtil.getContextualMessageForKey(messageKey);
		
		if(this.testSection == null){
			this.testSection = testSection;
		}
		
	}
	
	public String getFileName() {
		return  ConfigurationProperties.getInstance().isPropertyValueEqual(Property.RESULTS_ON_WORKPLAN, "false") ? BASE_FILE_NAME : FILE_NAME_WITH_RESULTS;
	}
	
	public HashMap<String, Object> getParameters() {
		parameterMap.put("testSection", testSection);
		parameterMap.put("printSubjectNo", ConfigurationProperties.getInstance().isPropertyValueEqual(Property.SUBJECT_ON_WORKPLAN, "true"));
		parameterMap.put("printNextVisit", ConfigurationProperties.getInstance().isPropertyValueEqual(Property.NEXT_VISIT_DATE_ON_WORKPLAN, "true"));
		parameterMap.put("labNumberTitle", StringUtil.getContextualMessageForKey("quick.entry.accession.number"));
		parameterMap.put("subjectNoTitle", StringUtil.getContextualMessageForKey("patient.subject.number"));
		parameterMap.put("nameOfTest", getNameOfTest());
		parameterMap.put("nameOfPatient", getNameOfPatient());
		parameterMap.put("labName", ConfigurationProperties.getInstance().getPropertyValue(Property.SiteName));
                parameterMap.put("accessionPrefix", AccessionNumberUtil.getAccessionNumberValidator().getPrefix() );
                parameterMap.put("prefixLength", PREFIX_LENGTH );
                parameterMap.put("SUBREPORT_DIR", reportPath);
                parameterMap.put("receptionDate", StringUtil.getMessageForKey("report.receptionDate"));
                parameterMap.put("workPlan", StringUtil.getMessageForKey("report.workPlan"));
                parameterMap.put("appointmentDate", StringUtil.getMessageForKey("report.appointmentDate"));
                parameterMap.put("testName", StringUtil.getMessageForKey("report.testName"));
                parameterMap.put("date", StringUtil.getMessageForKey("report.date"));
                parameterMap.put("from", StringUtil.getMessageForKey("report.from"));
                parameterMap.put("appointment", StringUtil.getMessageForKey("report.appointment"));
                parameterMap.put("about", StringUtil.getMessageForKey("report.about"));

		return parameterMap;	
	
	}
	
	public List<?> prepareRows(BaseActionForm dynaForm) {
		
		@SuppressWarnings("unchecked")
		List<TestResultItem> workplanTests  = (List<TestResultItem>) dynaForm.get("workplanTests");
		
		//remove unwanted tests from workplan
		List<TestResultItem> includedTests = new ArrayList<TestResultItem>();
		for(TestResultItem test : workplanTests){
			if (!test.isNotIncludedInWorkplan()){
				includedTests.add(test);
			}else{
				//handles the case that the checkbox is unchecked
				test.setNotIncludedInWorkplan(false);
			}
		}
		return includedTests;
	}

    @Override
    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
        
    }

    protected String getNameOfTest(){
        if( ConfigurationProperties.getInstance().isPropertyValueEqual(Property.configurationName, "Haiti LNSP")){
            return StringUtil.getContextualMessageForKey("sample.entry.project.patientName.testName");   
        } else 
            return StringUtil.getContextualMessageForKey("sample.entry.project.testName");
    }

    protected String getNameOfPatient(){
        if( ConfigurationProperties.getInstance().isPropertyValueEqual(Property.configurationName, "Haiti LNSP")){
            return StringUtil.getContextualMessageForKey("patient.name");   
        } else 
            return null;
    }   

}
