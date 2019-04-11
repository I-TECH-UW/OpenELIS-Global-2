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
package us.mn.state.health.lims.siteinformation.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

public class SiteInformationNextPreviousAction extends BaseAction {

	protected ActionForward performAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String forward = FWD_SUCCESS;
		request.setAttribute(ALLOW_EDITS_KEY, TRUE);
		request.setAttribute(PREVIOUS_DISABLED, FALSE);
		request.setAttribute(NEXT_DISABLED, FALSE);
		
		String id = request.getParameter(ID);
		
		String start = request.getParameter("startingRecNo");
		String direction = request.getParameter("direction");

		if( id != null && !id.equals("0")){
			SiteInformationUpdateAction updateAction = new SiteInformationUpdateAction();
			String updateResponse = updateAction.validateAndUpdateSiteInformation( request, (BaseActionForm)form, false);
			
			if( updateResponse == FWD_FAIL){
				return getForward(mapping.findForward(FWD_FAIL), id, start);
			}
		}
		
		SiteInformation siteInformation = new SiteInformation();
		siteInformation.setId(id);
		
		try {

			SiteInformationDAO SiteInformationDAO = new SiteInformationDAOImpl();

			SiteInformationDAO.getData(siteInformation);

			if (FWD_NEXT.equals(direction)) {

				List<SiteInformation> SiteInformations = SiteInformationDAO.getNextSiteInformationRecord(siteInformation.getId());
				
				if (SiteInformations != null && SiteInformations.size() > 0) {
					siteInformation = SiteInformations.get(0);
					SiteInformationDAO.getData(siteInformation);
					if (SiteInformations.size() < 2) {
						// disable next button
						request.setAttribute(NEXT_DISABLED, TRUE);
					}
					id = siteInformation.getId();
				} else {
					// just disable next button
					request.setAttribute(NEXT_DISABLED, TRUE);
				}
			}

			if (FWD_PREVIOUS.equals(direction)) {

				List<SiteInformation> SiteInformations = SiteInformationDAO.getPreviousSiteInformationRecord(siteInformation.getId());
				
				if (SiteInformations != null && SiteInformations.size() > 0) {
					siteInformation = SiteInformations.get(0);
					SiteInformationDAO.getData(siteInformation);
					if (SiteInformations.size() < 2) {
						// disable previous button
						request.setAttribute(PREVIOUS_DISABLED, TRUE);
					}
					id = siteInformation.getId();
				} else {
					// just disable next button
					request.setAttribute(PREVIOUS_DISABLED, TRUE);
				}
			}

		} catch (LIMSRuntimeException lre) {
			request.setAttribute(ALLOW_EDITS_KEY, FALSE);
			// disable previous and next
			request.setAttribute(PREVIOUS_DISABLED, TRUE);
			request.setAttribute(NEXT_DISABLED, TRUE);
			forward = FWD_FAIL;
		}
		if (forward.equals(FWD_FAIL))
			return mapping.findForward(forward);

		if (siteInformation.getId() != null && !siteInformation.getId().equals("0")) {
			request.setAttribute(ID, siteInformation.getId());

		}

		return getForward(mapping.findForward(forward), id, start);

	}

	protected String getPageTitleKey() {
		return null;
	}

	protected String getPageSubtitleKey() {
		return null;
	}

}