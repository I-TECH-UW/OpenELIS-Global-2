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
package us.mn.state.health.lims.siteinformation.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.common.action.BaseMenuAction;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

public class SiteInformationMenuAction extends BaseMenuAction {
	private String titleKey = null;
	
	@SuppressWarnings("rawtypes")
	protected List createMenuList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<SiteInformation> configurationList;
		
		String domainName = ((DynaActionForm)form).getString("siteInfoDomainName");
		String dbDomainName = null;
		if( "SiteInformation".equals(domainName) ){
			dbDomainName = "siteIdentity";
			titleKey = "siteInformation.browse.title";
		}else if( "ResultConfiguration".equals(domainName)){
			dbDomainName = "resultConfiguration";
			titleKey = "resultConfiguration.browse.title";
		}else if("sampleEntryConfig".equals(domainName)){
			dbDomainName = "sampleEntryConfig";
			titleKey = "sample.entry.browse.title";
		}else if("PrintedReportsConfiguration".equals(domainName)){
			dbDomainName = "printedReportsConfig";
			titleKey = "printedReportsConfiguration.browse.title";
		}else if("WorkplanConfiguration".equals(domainName)){
			dbDomainName = "workplanConfig";
			titleKey = "workplanConfiguration.browse.title";
		}else if("non_conformityConfiguration".equals(domainName)){
            dbDomainName = "non_conformityConfig";
            titleKey = "nonConformityConfiguration.browse.title";
        }else if("PatientConfiguration".equals(domainName)){
            dbDomainName = "patientEntryConfig";
            titleKey = "patientEntryConfiguration.browse.title";
        }else if("MenuStatementConfig".equals(domainName)){
            dbDomainName = "MenuStatementConfig";
            titleKey = "MenuStatementConfig.browse.title";
        }

		
		
		int startingRecNo = Integer.parseInt((String) request.getAttribute("startingRecNo"));

		request.setAttribute("menuDefinition", "SiteInformationMenuDefinition");

		SiteInformationDAO siteInformationDAO = new SiteInformationDAOImpl();
		
		configurationList = siteInformationDAO.getPageOfSiteInformationByDomainName(startingRecNo, dbDomainName);

		hideEncryptedFields(configurationList);

		setDisplayPageBounds(request, configurationList == null ? 0 : configurationList.size(), startingRecNo, siteInformationDAO.getCountForDomainName(dbDomainName));
		
		return configurationList;
	}

	private void hideEncryptedFields(List<SiteInformation> siteInformationList) {
		for (SiteInformation siteInformation : siteInformationList) {
			if (siteInformation.isEncrypted() && !GenericValidator.isBlankOrNull(siteInformation.getValue())) {
				siteInformation.setValue(siteInformation.getValue().replaceAll(".", "*"));
			}
		}

	}

	protected String getPageTitleKey() {
		return titleKey;
	}

	protected String getPageSubtitleKey() {
		return titleKey;
	}

	protected String getDeactivateDisabled() {
		return "true";
	}

	protected String getAddDisabled() {
		return "true";
	}
}
