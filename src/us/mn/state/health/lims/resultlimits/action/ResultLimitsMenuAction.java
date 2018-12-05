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
package us.mn.state.health.lims.resultlimits.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.common.action.BaseMenuAction;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.resultlimits.dao.ResultLimitDAO;
import us.mn.state.health.lims.resultlimits.daoimpl.ResultLimitDAOImpl;
import us.mn.state.health.lims.resultlimits.form.ResultLimitsLink;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.typeoftestresult.dao.TypeOfTestResultDAO;
import us.mn.state.health.lims.typeoftestresult.daoimpl.TypeOfTestResultDAOImpl;
import us.mn.state.health.lims.typeoftestresult.valueholder.TypeOfTestResult;


public class ResultLimitsMenuAction extends BaseMenuAction {

	private Map<String, String> resultTypeMap;
	private static TestDAO testDAO = new TestDAOImpl();

	protected List createMenuList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		request.setAttribute(ALLOW_EDITS_KEY, "false");
		List resultLimitsList;

		String stringStartingRecNo = (String) request.getAttribute("startingRecNo");
		int startingRecNo = Integer.parseInt(stringStartingRecNo);

		ResultLimitDAO resultLimitDAO = new ResultLimitDAOImpl();
		request.setAttribute("menuDefinition", "ResultLimitsMenuDefinition");

		resultLimitsList = resultLimitDAO.getPageOfResultLimits(startingRecNo);

		setDisplayPageBounds(request, resultLimitsList == null ? 0 : resultLimitsList.size(), startingRecNo, resultLimitDAO, ResultLimit.class);

		List<ResultLimitsLink> linkList = new ArrayList<ResultLimitsLink>();

		for(Object resultLimitsObj: resultLimitsList){
			ResultLimitsLink link = getResultLimitsLink((ResultLimit)resultLimitsObj);
			linkList.add(link);
		}

		return linkList;
	}

	private ResultLimitsLink getResultLimitsLink(ResultLimit resultLimit) {
		ResultLimitsLink link = new ResultLimitsLink();
		link.setReadWrite(false);
		link.setResultLimit(resultLimit);
        String testName = TestService.getLocalizedTestNameWithType( link.getTestId() );

		String resultName = getResultTypeMap().get(link.getResultTypeId());

		if( !GenericValidator.isBlankOrNull(testName)){
			link.setTestName(testName);
		}

		if( !GenericValidator.isBlankOrNull(resultName)){
			link.setResultType(resultName);
		}

		Test test = new Test();
		test.setId(resultLimit.getTestId());
		testDAO.getData(test);

		return link;
	}

	protected String getPageTitleKey() {
		return "resultlimits.browse.title";
	}

	protected String getPageSubtitleKey() {
		return "resultlimits.browse.title";
	}

	protected int getPageSize() {
		return SystemConfiguration.getInstance().getDefaultPageSize();
	}

	protected String getDeactivateDisabled() {
		return "false";
	}
	
	protected String getAddDisabled() {
		return "true";
	}

	protected String getEditDisabled() {
		return "true";
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> getResultTypeMap() {
		if( resultTypeMap == null){
			resultTypeMap = new HashMap<String, String>();

			TypeOfTestResultDAO resultTypeDAO = new TypeOfTestResultDAOImpl();
			Collection<TypeOfTestResult> resultTypes = resultTypeDAO.getAllTypeOfTestResults();

			for( TypeOfTestResult resultType : resultTypes){
				resultTypeMap.put(resultType.getId(), resultType.getDescription());
			}
		}

		return resultTypeMap;
	}

}
