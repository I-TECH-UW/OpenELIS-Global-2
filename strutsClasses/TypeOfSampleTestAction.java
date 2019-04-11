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
package us.mn.state.health.lims.typeofsample.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;

public class TypeOfSampleTestAction extends BaseAction {


	protected ActionForward performAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String forward = FWD_SUCCESS;
		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "true");
		request.setAttribute(NEXT_DISABLED, "true");

		DynaActionForm dynaForm = (DynaActionForm) form;

		// initialize the form
		dynaForm.initialize(mapping);

		populateTests( dynaForm);
		populateSamples( dynaForm);
		
		return mapping.findForward(forward);
	}

	private void populateTests(DynaActionForm dynaForm) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		TestDAO testDAO = new TestDAOImpl();
		
		List tests = testDAO.getAllTests(false);
		
		PropertyUtils.setProperty(dynaForm, "tests", tests);
	}

	private void populateSamples(DynaActionForm dynaForm)  throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	
		TypeOfSampleDAO sampleDAO = new TypeOfSampleDAOImpl();
		
		List samples = sampleDAO.getAllTypeOfSamples();
		
		PropertyUtils.setProperty(dynaForm, "samples", samples);
	}

	protected String getPageTitleKey() {
		return "typeofsample.add.title";
	}

	protected String getPageSubtitleKey() {
		return "typeofsample.add.title";
	}

}
