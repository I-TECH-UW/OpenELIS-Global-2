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
package us.mn.state.health.lims.inventory.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.inventory.form.InventoryKitItem;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.organization.valueholder.Organization;



public class FindInventoryAction extends BaseAction {

	@Override
	protected ActionForward performAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String forward = FWD_SUCCESS;
		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "true");
		request.setAttribute(NEXT_DISABLED, "true");
		request.getSession().setAttribute(SAVE_DISABLED, FALSE );

		DynaActionForm dynaForm = (DynaActionForm) form;

		InventoryUtility utility = new InventoryUtility();
		List<InventoryKitItem> list = utility.getExistingInventory();
		PropertyUtils.setProperty(dynaForm, "inventoryItems", list);

		List<String> kitTypes = getTestKitTypes();
		PropertyUtils.setProperty(dynaForm, "kitTypes", kitTypes);

		List<IdValuePair> sources = getSources();
		PropertyUtils.setProperty(dynaForm, "sources", sources);

		return mapping.findForward(forward);
	}


	private List<String> getTestKitTypes() {
		List<String> types = new ArrayList<String>();
		types.add(InventoryUtility.HIV);
		types.add(InventoryUtility.SYPHILIS);

		return types;
	}

	private List<IdValuePair> getSources() {
		List<IdValuePair> sources = new ArrayList<IdValuePair>();

		OrganizationDAO organizationDAO = new OrganizationDAOImpl();
		List<Organization> organizations =  organizationDAO.getOrganizationsByTypeName("organizationName", "TestKitVender");

		for( Organization organization : organizations){
				sources.add(new IdValuePair(organization.getId(), organization.getOrganizationName()));
		}

		return sources;
	}

	@Override
	protected String getPageTitleKey() {
		return "inventory.manage.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "inventory.manage.title";
	}

}
