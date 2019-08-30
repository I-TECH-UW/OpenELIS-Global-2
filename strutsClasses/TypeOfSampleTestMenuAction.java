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
*/
package org.openelisglobal.typeofsample.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import org.openelisglobal.common.action.BaseMenuAction;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.services.TestService;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.test.dao.TestDAO;
import org.openelisglobal.test.daoimpl.TestDAOImpl;
import org.openelisglobal.typeofsample.dao.TypeOfSampleDAO;
import org.openelisglobal.typeofsample.dao.TypeOfSampleTestDAO;
import org.openelisglobal.typeofsample.daoimpl.TypeOfSampleDAOImpl;
import org.openelisglobal.typeofsample.daoimpl.TypeOfSampleTestDAOImpl;
import org.openelisglobal.typeofsample.formbean.TypeOfSampleLink;
import org.openelisglobal.typeofsample.valueholder.TypeOfSampleTest;


public class TypeOfSampleTestMenuAction extends BaseMenuAction {

	@SuppressWarnings("unchecked")
	protected List createMenuList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		request.setAttribute("menuDefinition", "TypeOfSampleTestMenuDefinition");
		
		List<TypeOfSampleTest> sampleTestList = new ArrayList<TypeOfSampleTest>();

		int startingRecNo = getStartingNumber(request);
		
		TypeOfSampleTestDAO sampleTestDAO = new TypeOfSampleTestDAOImpl();
		sampleTestList = sampleTestDAO.getPageOfTypeOfSampleTests(startingRecNo);
		
		TypeOfSampleDAO typeOfSampleDAO = new TypeOfSampleDAOImpl();
		TestDAO testDAO = new TestDAOImpl();
		
		List<TypeOfSampleLink> namedSampleTestList = new ArrayList<TypeOfSampleLink>();
		
		for( TypeOfSampleTest sampleTest : sampleTestList){
			String sampleName = typeOfSampleDAO.getNameForTypeOfSampleId(sampleTest.getTypeOfSampleId());
			String testName = TestServiceImpl.getUserLocalizedTestName( sampleTest.getTestId() );
			String testDescription = TestServiceImpl.getLocalizedTestNameWithType( sampleTest.getTestId() );
			
			namedSampleTestList.add(new TypeOfSampleLink(sampleTest.getId(), sampleName, testName, testDescription));
		}




		//set pagination variables 
		setPagationBounds(request, sampleTestList, startingRecNo, sampleTestDAO);
		
		return namedSampleTestList;
	}

	private int getStartingNumber(HttpServletRequest request) throws NumberFormatException {
		String stringStartingRecNo = (String) request.getAttribute("startingRecNo");
		int startingRecNo = Integer.parseInt(stringStartingRecNo);
		return startingRecNo;
	}

	private void setPagationBounds(HttpServletRequest request, List typeOfSamples, int startingRecNo,
			TypeOfSampleTestDAO typeOfSampleDAO) throws LIMSRuntimeException {
		request.setAttribute(MENU_TOTAL_RECORDS, String.valueOf(typeOfSampleDAO.getTotalTypeOfSampleTestCount()));
		request.setAttribute(MENU_FROM_RECORD, String.valueOf(startingRecNo));
		
		int numOfRecs = getNumberOfRecordsToDisplay(typeOfSamples);
		
		int endingRecNo = startingRecNo + numOfRecs;
		request.setAttribute(MENU_TO_RECORD, String.valueOf(endingRecNo));
	}

	private int getNumberOfRecordsToDisplay(List typeOfSamples) {
		int numOfRecs = 0;
		
		if (typeOfSamples != null) {
			numOfRecs = Math.min(typeOfSamples.size(),SystemConfiguration.getInstance().getDefaultPageSize() );
			numOfRecs--;
		}
		return numOfRecs;
	}

	protected String getPageTitleKey() {
		return "typeofsample.browse.title";
	}

	protected String getPageSubtitleKey() {
		return "typeofsample.browse.title";
	}

	protected int getPageSize() {
		return SystemConfiguration.getInstance().getDefaultPageSize();
	}

	protected String getDeactivateDisabled() {
		return "false";
	}
	
	protected String getEditDisabled(){
		return "true"; 
	}
}
