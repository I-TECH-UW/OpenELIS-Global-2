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
package us.mn.state.health.lims.organization.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.address.dao.AddressPartDAO;
import us.mn.state.health.lims.address.dao.OrganizationAddressDAO;
import us.mn.state.health.lims.address.daoimpl.AddressPartDAOImpl;
import us.mn.state.health.lims.address.daoimpl.OrganizationAddressDAOImpl;
import us.mn.state.health.lims.address.valueholder.AddressPart;
import us.mn.state.health.lims.address.valueholder.OrganizationAddress;
import us.mn.state.health.lims.citystatezip.dao.CityStateZipDAO;
import us.mn.state.health.lims.citystatezip.daoimpl.CityStateZipDAOImpl;
import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.dao.OrganizationOrganizationTypeDAO;
import us.mn.state.health.lims.organization.dao.OrganizationTypeDAO;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.organization.daoimpl.OrganizationOrganizationTypeDAOImpl;
import us.mn.state.health.lims.organization.daoimpl.OrganizationTypeDAOImpl;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.organization.valueholder.OrganizationType;

/**
 * @author diane benz
 *
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation.
 */
public class OrganizationAction extends BaseAction {

	private boolean isNew = false;

	private static boolean useParentOrganization = FormFields.getInstance().useField(Field.OrganizationParent);
	private static boolean useOrganizationState = FormFields.getInstance().useField(Field.OrgState);
	private static boolean useOrganizationTypeList = FormFields.getInstance().useField(Field.InlineOrganizationTypes);
	private static boolean useDepartment = FormFields.getInstance().useField(Field.ADDRESS_DEPARTMENT );
	private static boolean useCommune = FormFields.getInstance().useField(Field.ADDRESS_COMMUNE );
	private static boolean useVillage = FormFields.getInstance().useField(Field.ADDRESS_VILLAGE );

	private static String DEPARTMENT_ID;
	private static String COMMUNE_ID;
	private static String VILLAGE_ID;

	private static OrganizationAddressDAO orgAddressDAO = new OrganizationAddressDAOImpl();

	static{
		AddressPartDAO addressPartDAO = new AddressPartDAOImpl();
		List<AddressPart> partList = addressPartDAO.getAll();

		for( AddressPart addressPart: partList){
			if("department".equals(addressPart.getPartName())){
				DEPARTMENT_ID = addressPart.getId();
			}else if("commune".equals(addressPart.getPartName())){
				COMMUNE_ID = addressPart.getId();
			}else if("village".equals(addressPart.getPartName())){
				VILLAGE_ID = addressPart.getId();
			}
		}

	}


	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// The first job is to determine if we are coming to this action with an
		// ID parameter in the request. If there is no parameter, we are
		// creating a new Organization.
		// If there is a parameter present, we should bring up an existing
		// Organization to edit.
		String id = request.getParameter(ID);

		String forward = FWD_SUCCESS;
		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "true");
		request.setAttribute(NEXT_DISABLED, "true");

		BaseActionForm dynaForm = (BaseActionForm) form;

		// initialize the form
		dynaForm.initialize(mapping);
		List<Dictionary> departmentList = getDepartmentList();
		PropertyUtils.setProperty(dynaForm, "departmentList", departmentList);


		Organization organization = new Organization();

		isNew = (id == null) || "0".equals(id);

		OrganizationDAO organizationDAO = new OrganizationDAOImpl();

		if (!isNew) {
			organization.setId(id);

			organizationDAO.getData(organization);

			if (organization.getOrganization() != null) {
				organization.setSelectedOrgId(organization.getOrganization().getId());
			}

			List organizations = organizationDAO.getNextOrganizationRecord(organization.getOrganizationName());
			if (organizations.size() > 0) {
				request.setAttribute(NEXT_DISABLED, "false");
			}

			organizations = organizationDAO.getPreviousOrganizationRecord(organization.getOrganizationName());
			if (organizations.size() > 0) {
				request.setAttribute(PREVIOUS_DISABLED, "false");
			}

			if( useCommune || useDepartment|| useVillage ){
				List<OrganizationAddress> orgAddressList = orgAddressDAO.getAddressPartsByOrganizationId(id);

				for( OrganizationAddress orgAddress : orgAddressList){
					if( useCommune && COMMUNE_ID.equals(orgAddress.getAddressPartId())){
						PropertyUtils.setProperty(dynaForm, "commune", orgAddress.getValue());
					}else if( useVillage && VILLAGE_ID.equals(orgAddress.getAddressPartId())){
						PropertyUtils.setProperty(dynaForm, "village", orgAddress.getValue());
					}else if( useDepartment && DEPARTMENT_ID.equals(orgAddress.getAddressPartId())){
						PropertyUtils.setProperty(dynaForm, "department", orgAddress.getValue());
					}
				}
			}

		} else { // this is a new organization

			// default isActive to 'Y'
			organization.setIsActive(YES);
			organization.setMlsSentinelLabFlag(NO);
			organization.setMlsLabFlag("N");
		}

		// initialize state to MN
		if (organization.getState() == null) {
			organization.setState("MN");
		}

		if (organization.getId() != null && !organization.getId().equals("0")) {
			request.setAttribute(ID, organization.getId());
		}

		PropertyUtils.copyProperties(form, organization);

		if (useParentOrganization) {
			setParentOrganiztionName(form, organization, organizationDAO);
		}

		if (useOrganizationState) {
			setCityStateZipList(form);
		}

		if (useOrganizationTypeList) {
			List<OrganizationType> orgTypeList = getOrganizationTypeList();
			String[] selectedList = new String[orgTypeList.size()];
			PropertyUtils.setProperty(form, "orgTypes", orgTypeList);

			if (organization.getId() != null && orgTypeList != null) {
				if (orgTypeList.size() > 0) {

					OrganizationOrganizationTypeDAO ootDAO = new OrganizationOrganizationTypeDAOImpl();
					List<String> selectedOrgTypeList = ootDAO.getTypeIdsForOrganizationId(organization.getId());

					int index = 0;
					for (String orgTypeId : selectedOrgTypeList) {
						selectedList[index] = orgTypeId;
						index++;
					}
				}
			}
			PropertyUtils.setProperty(form, "selectedTypes", selectedList);
		}

		return mapping.findForward(forward);
	}

	private void setParentOrganiztionName(ActionForm form, Organization organization, OrganizationDAO organizationDAO)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Organization parentOrg = new Organization();
		String parentOrgName = null;

		if (!StringUtil.isNullorNill(organization.getSelectedOrgId())) {
			parentOrg.setId(organization.getSelectedOrgId());
			organizationDAO.getData(parentOrg);
			parentOrgName = parentOrg.getOrganizationName();
		}

		PropertyUtils.setProperty(form, "parentOrgName", parentOrgName);
	}

	private void setCityStateZipList(ActionForm form) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (FormFields.getInstance().useField(FormFields.Field.OrgState)) {
			// bugzilla 1545
			CityStateZipDAO cityStateZipDAO = new CityStateZipDAOImpl();
			List states = cityStateZipDAO.getAllStateCodes();
			PropertyUtils.setProperty(form, "states", states);
		}
	}

	private List<OrganizationType> getOrganizationTypeList() {

		OrganizationTypeDAO orgTypeDAO = new OrganizationTypeDAOImpl();

		List<OrganizationType> orgTypeList = orgTypeDAO.getAllOrganizationTypes();
		if (orgTypeList == null) {
			orgTypeList = new ArrayList<OrganizationType>();
		}

		return orgTypeList;
	}

	private List<Dictionary> getDepartmentList() {
		DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
		return dictionaryDAO.getDictionaryEntrysByCategoryAbbreviation("description", "haitiDepartment", true);
	}

	protected String getPageTitleKey() {
		if (isNew) {
			return "organization.add.title";
		} else {
			return "organization.edit.title";
		}
	}

	protected String getPageSubtitleKey() {
		if (isNew) {
			return "organization.add.title";
		} else {
			return "organization.edit.title";
		}
	}

}
