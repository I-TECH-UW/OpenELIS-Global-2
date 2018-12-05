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

import java.util.List;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.panel.dao.PanelDAO;
import us.mn.state.health.lims.panel.daoimpl.PanelDAOImpl;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testresult.daoimpl.TestResultDAOImpl;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

public class TestIdentityService implements ITestIdentityService{
	private static String VIRAL_LOAD_TEST_ID = null;
	private static Boolean VIRAL_LOAD_IS_NUMERIC = Boolean.TRUE;
	private static TestDAO testDAO = new TestDAOImpl();
	private static PanelDAO panelDAO = new PanelDAOImpl();
	private static ITestIdentityService instance;
	
	static{
		
		Test test = testDAO.getTestByDescription("VIH-1 Charge Virale(Plasma)");

		if(test != null){
			VIRAL_LOAD_TEST_ID = test.getId();
		}else{
			test = testDAO.getTestByDescription("Charge Virale(Plasma)");
			if(test != null){
				VIRAL_LOAD_TEST_ID = test.getId();
			}else{
				test = testDAO.getTestByName("Viral Load");
				if(test != null){
					VIRAL_LOAD_TEST_ID = test.getId();
				}
			}
		}

		if(!GenericValidator.isBlankOrNull(VIRAL_LOAD_TEST_ID)){
			List<TestResult> testResultList = new TestResultDAOImpl().getActiveTestResultsByTest( VIRAL_LOAD_TEST_ID );
			VIRAL_LOAD_IS_NUMERIC = !testResultList.isEmpty()
					&& ("N".equals(testResultList.get(0).getTestResultType()) || "A".equals(testResultList.get(0).getTestResultType()));
		}else{
			VIRAL_LOAD_IS_NUMERIC = Boolean.FALSE;
		}
	}

	public static ITestIdentityService getInstance(){
		if( instance == null){
			instance = new TestIdentityService();
		}
		
		return instance;
	}
	
	public static Boolean isTestNumericViralLoad(Test test){
		return isTestNumericViralLoad(test.getId());
	}

	public static Boolean isTestNumericViralLoad(String testID){
		return VIRAL_LOAD_IS_NUMERIC && (VIRAL_LOAD_TEST_ID != null && VIRAL_LOAD_TEST_ID.equals(testID));
	}

	/* (non-Javadoc)
	 * @see us.mn.state.health.lims.common.services.ITestIdentityService#doesTestExist(java.lang.String)
	 */
	@Override
	public boolean doesTestExist(String name){
		return testDAO.getTestByName(name) != null;
	}
	
	@Override
	public boolean doesActiveTestExist(String name){
		return !testDAO.getActiveTestByName(name).isEmpty();
	}

	/* (non-Javadoc)
	 * @see us.mn.state.health.lims.common.services.ITestIdentityService#doesPanelExist(java.lang.String)
	 */
	@Override
	public boolean doesPanelExist(String name){
		return panelDAO.getIdForPanelName(name) != null;
	}
	
	@Override
	public boolean doesTestExistForLoinc(String loincCode) {
		return testDAO.getTestsByLoincCode(loincCode) != null && testDAO.getTestsByLoincCode(loincCode).size() > 0;
	}

	@Override
	public boolean doesActiveTestExistForLoinc(String loincCode) {
		return testDAO.getActiveTestsByLoinc(loincCode) != null && testDAO.getActiveTestsByLoinc(loincCode).size() > 0;
	}
}
