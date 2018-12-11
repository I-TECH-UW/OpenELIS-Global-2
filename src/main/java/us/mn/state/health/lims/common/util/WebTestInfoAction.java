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
 * Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
 *
 */
package us.mn.state.health.lims.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.login.dao.LoginDAO;
import us.mn.state.health.lims.login.daoimpl.LoginDAOImpl;
import us.mn.state.health.lims.login.valueholder.Login;
import us.mn.state.health.lims.patient.daoimpl.PatientDAOImpl;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.sample.valueholder.Sample;

public class WebTestInfoAction extends BaseAction {

	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xmlWad = getWebTestXmlWad();
		
		PropertyUtils.setProperty((DynaActionForm)form, "xmlWad", xmlWad);

		return mapping.findForward(IActionConstants.FWD_SUCCESS);
	}

	private String getWebTestXmlWad() {
		StringBuilder xmlBuilder = new StringBuilder();
		
		xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlBuilder.append("<webTestInfo>");
		
		addUserInfo(xmlBuilder);
		//addUserLocked(xmlBuilder);
		addNumberOfPatients(xmlBuilder);
		addNumberOfSamples(xmlBuilder);
		
		xmlBuilder.append("</webTestInfo>");
		return xmlBuilder.toString();
		
	}

	
	
	
	private void addUserInfo(StringBuilder xmlBuilder) {
		LoginDAO loginDAO = new LoginDAOImpl();
		//Login user = loginDAO.getUserProfile("user");
		Login user = loginDAO.getUserProfile("webtest");
		if( user != null){
			xmlBuilder.append("<webtestuser-id>");
			xmlBuilder.append(user.getSystemUserId()+"-"+user.getId());
			xmlBuilder.append("</webtestuser-id>");
			xmlBuilder.append("<webtestuser-passwd>");
			xmlBuilder.append(user.getPassword());
			xmlBuilder.append("</webtestuser-passwd>");
			xmlBuilder.append("<webtestuser-accountLocked>");
			xmlBuilder.append(user.getAccountLocked());
			xmlBuilder.append("</webtestuser-accountLocked>");
		} else {
			xmlBuilder.append("<webtestuser-id>no 'webtest' user</webtestuser-id>");
		}
	}

	private void addNumberOfPatients(StringBuilder xmlBuilder) {
		int count = new PatientDAOImpl().getTotalCount("Patient", Patient.class);
		
		xmlBuilder.append("<patient-count>");
		xmlBuilder.append(String.valueOf(count));
		xmlBuilder.append("</patient-count>");
	
	}

	private void addNumberOfSamples(StringBuilder xmlBuilder) {
		int count = new PatientDAOImpl().getTotalCount("Sample", Sample.class);
		
		xmlBuilder.append("<sample-count>");
		xmlBuilder.append(String.valueOf(count));
		xmlBuilder.append("</sample-count>");
		
	}

	@Override
	protected String getPageTitleKey() {
		return null;
	}

	@Override
	protected String getPageSubtitleKey() {
		return null;
	}

}
