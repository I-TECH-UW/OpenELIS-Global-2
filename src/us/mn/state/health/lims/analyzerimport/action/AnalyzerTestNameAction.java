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
package us.mn.state.health.lims.analyzerimport.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.analyzer.dao.AnalyzerDAO;
import us.mn.state.health.lims.analyzer.daoimpl.AnalyzerDAOImpl;
import us.mn.state.health.lims.analyzer.valueholder.Analyzer;
import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;

public class AnalyzerTestNameAction extends BaseAction {


	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {


		String forward = FWD_SUCCESS;
		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "true");
		request.setAttribute(NEXT_DISABLED, "true");

		DynaActionForm dynaForm = (DynaActionForm) form;

		dynaForm.initialize(mapping);

		List<Analyzer> analyzerList = getAllAnalyzers();
		List<Test> testList = getAllTests();

		PropertyUtils.setProperty(form, "analyzerList", analyzerList);
		PropertyUtils.setProperty(form, "testList", testList);

		String id = request.getParameter("selectedIDs");
		if(id != null){
			String[] splitId = id.split("#");
			PropertyUtils.setProperty(form, "analyzerTestName", splitId[1] );
			PropertyUtils.setProperty(form, "testId", splitId[2] );
			PropertyUtils.setProperty(form, "analyzerId", splitId[0] );
		}

		return mapping.findForward(forward);
	}

	private List<Analyzer> getAllAnalyzers() {
		AnalyzerDAO analyzerDAO = new AnalyzerDAOImpl();
		return analyzerDAO.getAllAnalyzers();
	}

	private List<Test> getAllTests() {
		TestDAO testDAO = new TestDAOImpl();
		return testDAO.getAllActiveTests(false);
	}

	protected String getPageTitleKey() {
		return "analyzerTestName.browse.title";
	}

	protected String getPageSubtitleKey() {
		return "analyzerTestName.browse.title";
	}

}
