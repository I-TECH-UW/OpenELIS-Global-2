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
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.gender.dao.GenderDAO;
import us.mn.state.health.lims.gender.daoimpl.GenderDAOImpl;
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
import us.mn.state.health.lims.unitofmeasure.dao.UnitOfMeasureDAO;
import us.mn.state.health.lims.unitofmeasure.daoimpl.UnitOfMeasureDAOImpl;

public class ResultLimitsAction extends BaseAction {

	private boolean isNew = false;

	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// The first job is to determine if we are coming to this action with an
		// ID parameter in the request. If there is no parameter, we are
		// creating a new TestResult.
		// If there is a parameter present, we should bring up an existing
		// TestResult to edit.
		String id = request.getParameter(ID);

		String forward = FWD_SUCCESS;
		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "true");
		request.setAttribute(NEXT_DISABLED, "true");

		DynaActionForm dynaForm = (DynaActionForm) form;

		dynaForm.initialize(mapping);

		isNew = id == null || "0".equals(id) || "null".equalsIgnoreCase(id);

		if (!isNew) {

			ResultLimit resultLimit = new ResultLimit();
			resultLimit.setId(id);
			ResultLimitDAO resultLimitDAO = new ResultLimitDAOImpl();
			resultLimitDAO.getData(resultLimit);
			request.setAttribute(ID, resultLimit.getId());

			List resultLimits = resultLimitDAO.getNextResultLimitRecord(resultLimit.getId());
			if (resultLimits.size() > 0) {
				request.setAttribute(NEXT_DISABLED, FALSE);
			}

			resultLimits = resultLimitDAO.getPreviousResultLimitRecord(resultLimit.getId());
			if (resultLimits.size() > 0) {
				request.setAttribute(PREVIOUS_DISABLED, FALSE);
			}

			ResultLimitsLink resultLimitLink = new ResultLimitsLink();
			resultLimitLink.setResultLimit(resultLimit);
			TestDAO testDAO = new TestDAOImpl();
			Test test = new Test();
			test.setId(resultLimit.getTestId());
			testDAO.getData(test);
			
			PropertyUtils.setProperty(dynaForm, "limit", resultLimitLink);
		}

		Collection tests = getAllTests();
		PropertyUtils.setProperty(form, "tests", tests);

		Collection resultTypes = getNonDictonaryResultTypes();
		PropertyUtils.setProperty(form, "resultTypes", resultTypes);

		Collection genders = getGenders();
		PropertyUtils.setProperty(form, "genders", genders);

		Collection units = getUnits();
		PropertyUtils.setProperty(form, "units", units);

		return mapping.findForward(forward);
	}

	private Collection getUnits() {
		UnitOfMeasureDAO uomDAO = new UnitOfMeasureDAOImpl();
		return uomDAO.getAllUnitOfMeasures();
	}

	private Collection getGenders() {
		GenderDAO genderDAO = new GenderDAOImpl();
		return genderDAO.getAllGenders();
	}

	@SuppressWarnings("unchecked")
	private Collection getNonDictonaryResultTypes() {
		TypeOfTestResultDAO resultTypeDAO = new TypeOfTestResultDAOImpl();
		Collection<TypeOfTestResult> resultTypes = resultTypeDAO.getAllTypeOfTestResults();
		Collection filteredResultTypes = new ArrayList();

		for (TypeOfTestResult resultType : resultTypes) {
			if (!"D".equals(resultType.getTestResultType())) {
				filteredResultTypes.add(resultType);
			}
		}

		return filteredResultTypes;
	}

	private Collection getAllTests() {
		TestDAO testDAO = new TestDAOImpl();
		return testDAO.getAllTests(false);
	}

	protected String getPageTitleKey() {
		return isNew ? "resultlimits.add.title" : "resultlimits.edit.title";
	}

	protected String getPageSubtitleKey() {
		return isNew ? "resultlimits.add.title" : "resultlimits.edit.title";
	}

}
