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
package us.mn.state.health.lims.organization.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

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
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.provider.validation.CityStateZipComboValidationProvider;
import us.mn.state.health.lims.common.provider.validation.CityValidationProvider;
import us.mn.state.health.lims.common.provider.validation.ZipValidationProvider;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.resources.ResourceLocator;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.dao.OrganizationOrganizationTypeDAO;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.organization.daoimpl.OrganizationOrganizationTypeDAOImpl;
import us.mn.state.health.lims.organization.valueholder.Organization;

/**
 * @author diane benz
 *
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation.
 */
public class OrganizationUpdateAction extends BaseAction {

	private boolean isNew = false;
	private static boolean useZip = FormFields.getInstance().useField(FormFields.Field.ZipCode);
	private static boolean useState = FormFields.getInstance().useField(FormFields.Field.OrgState);
	private static boolean useDepartment = FormFields.getInstance().useField(Field.ADDRESS_DEPARTMENT );
	private static boolean useCommune = FormFields.getInstance().useField(Field.ADDRESS_COMMUNE );
	private static boolean useVillage = FormFields.getInstance().useField(Field.ADDRESS_VILLAGE );

	private String[] selectedOrgTypes;

	private static String DEPARTMENT_ID;
	private static String COMMUNE_ID;
	private static String VILLAGE_ID;

	private static OrganizationDAO organizationDAO = new OrganizationDAOImpl();
	private static OrganizationAddressDAO orgAddressDAO = new OrganizationAddressDAOImpl();

	private OrganizationAddress departmentAddress;
	private boolean updateDepartment = false;
	private OrganizationAddress communeAddress;
	private boolean updateCommune = false;
	private OrganizationAddress villageAddress;
	private boolean updateVillage = false;

	static {
		AddressPartDAO addressPartDAO = new AddressPartDAOImpl();
		List<AddressPart> partList = addressPartDAO.getAll();

		for (AddressPart addressPart : partList) {
			if ("department".equals(addressPart.getPartName())) {
				DEPARTMENT_ID = addressPart.getId();
			} else if ("commune".equals(addressPart.getPartName())) {
				COMMUNE_ID = addressPart.getId();
			} else if ("village".equals(addressPart.getPartName())) {
				VILLAGE_ID = addressPart.getId();
			}
		}

	}

	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = FWD_SUCCESS;
		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "false");
		request.setAttribute(NEXT_DISABLED, "false");

		String id = request.getParameter(ID);

		isNew = (StringUtil.isNullorNill(id) || "0".equals(id));

		BaseActionForm dynaForm = (BaseActionForm) form;

		selectedOrgTypes = dynaForm.getStrings("selectedTypes");

		ActionMessages errors = dynaForm.validate(mapping, request);

		try {
			errors = validateAll(errors, dynaForm);
		} catch (Exception e) {
			LogEvent.logError("OrganizationUpdateAction", "performAction()", e.toString());
			ActionError error = new ActionError("errors.ValidationException", null, null);
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
		}

		if (errors != null && errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward(FWD_FAIL);
		}

		String start = request.getParameter("startingRecNo");
		String direction = request.getParameter("direction");

		Organization organization = new Organization();
		organization.setSysUserId(currentUserId);

		List states = getPossibleStates(dynaForm);

		PropertyUtils.copyProperties(organization, dynaForm);

		if (FormFields.getInstance().useField(FormFields.Field.OrganizationParent)) {
			String parentOrgName = (String) dynaForm.get("parentOrgName");
			Organization o = new Organization();
			o.setOrganizationName(parentOrgName);
			Organization parentOrg = organizationDAO.getOrganizationByName(o, false);
			organization.setOrganization(parentOrg);
		}

		createAddressParts(id, dynaForm);

		org.hibernate.Transaction tx = HibernateUtil.getSession().beginTransaction();
		try {

			if (!isNew) {
				organizationDAO.updateData(organization);
			} else {
				organizationDAO.insertData(organization);
			}

			persistAddressParts(organization);

			linkOrgWithOrgType(organization);

			tx.commit();
		} catch (LIMSRuntimeException lre) {
			// bugzilla 2154
			LogEvent.logError("OrganizationUpdateAction", "performAction()", lre.toString());
			tx.rollback();
			errors = new ActionMessages();
			ActionError error;
			if (lre.getException() instanceof org.hibernate.StaleObjectStateException) {
				// how can I get popup instead of struts error at the top of
				// page?
				// ActionMessages errors = dynaForm.validate(mapping, request);
				error = new ActionError("errors.OptimisticLockException", null, null);

			} else {
				// bugzilla 1482
				if (lre.getException() instanceof LIMSDuplicateRecordException) {
					java.util.Locale locale = (java.util.Locale) request.getSession().getAttribute("org.apache.struts.action.LOCALE");
					String messageKey = "organization.organization";
					String msg = ResourceLocator.getInstance().getMessageResources().getMessage(locale, messageKey);
					error = new ActionError("errors.DuplicateRecord.activeonly", msg, null);

				} else {
					error = new ActionError("errors.UpdateException", null, null);
				}
			}

			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);
			request.setAttribute(Globals.ERROR_KEY, errors);

			request.setAttribute(PREVIOUS_DISABLED, "true");
			request.setAttribute(NEXT_DISABLED, "true");
			forward = FWD_FAIL;

		} finally {
			HibernateUtil.closeSession();
		}
		if (forward.equals(FWD_FAIL))
			return mapping.findForward(forward);

		dynaForm.initialize(mapping);

		PropertyUtils.copyProperties(dynaForm, organization);

		if (states != null) {
			PropertyUtils.setProperty(form, "states", states);
		}

		if ("true".equalsIgnoreCase(request.getParameter("close"))) {
			forward = FWD_CLOSE;
		}

		if (organization.getId() != null && !organization.getId().equals("0")) {
			request.setAttribute(ID, organization.getId());
		}

		if (isNew){
			forward = FWD_SUCCESS_INSERT;
		}

        DisplayListService.refreshList(DisplayListService.ListType.REFERRAL_ORGANIZATIONS);

		return getForward(mapping.findForward(forward), id, start, direction);

	}

	private void persistAddressParts(Organization organization) {
		if (departmentAddress != null) {
			if (updateDepartment) {
				orgAddressDAO.update(departmentAddress);
			} else {
				departmentAddress.setOrganizationId(organization.getId());
				orgAddressDAO.insert(departmentAddress);
			}
		}

		if (communeAddress != null) {
			if (updateCommune) {
				orgAddressDAO.update(communeAddress);
			} else {
				communeAddress.setOrganizationId(organization.getId());
				orgAddressDAO.insert(communeAddress);
			}
		}

		if (villageAddress != null) {
			if (updateVillage) {
				orgAddressDAO.update(villageAddress);
			} else {
				villageAddress.setOrganizationId(organization.getId());
				orgAddressDAO.insert(villageAddress);
			}
		}
	}

	private void createAddressParts(String id, BaseActionForm dynaForm) {
		if (useDepartment || useCommune || useVillage) {
			updateDepartment = false;
			updateCommune = false;
			updateVillage = false;
			if (!isNew) {
				List<OrganizationAddress> orgAddressList = orgAddressDAO.getAddressPartsByOrganizationId(id);

				for (OrganizationAddress orgAddress : orgAddressList) {
					if (DEPARTMENT_ID.equals(orgAddress.getAddressPartId())) {
						departmentAddress = orgAddress;
						updateDepartment = true;
					} else if (COMMUNE_ID.equals(orgAddress.getAddressPartId())) {
						communeAddress = orgAddress;
						updateCommune = true;
					} else if (VILLAGE_ID.equals(orgAddress.getAddressPartId())) {
						villageAddress = orgAddress;
						updateVillage = true;
					}
				}
			}

			if (useDepartment) {
				if (!updateDepartment) {
					departmentAddress = new OrganizationAddress();
					departmentAddress.setAddressPartId(DEPARTMENT_ID);
					departmentAddress.setType("D");
				}

				departmentAddress.setValue(dynaForm.getString("department"));
				departmentAddress.setSysUserId(currentUserId);
			}

			if (useCommune) {
				if (!updateCommune) {
					communeAddress = new OrganizationAddress();
					communeAddress.setAddressPartId(COMMUNE_ID);
					communeAddress.setType("T");
				}

				communeAddress.setValue(dynaForm.getString("commune"));
				communeAddress.setSysUserId(currentUserId);
			}

			if (useVillage) {
				if ( !updateVillage) {
					villageAddress = new OrganizationAddress();
					villageAddress.setAddressPartId(VILLAGE_ID);
					villageAddress.setType("T");
				}

				villageAddress.setValue(dynaForm.getString("village"));
				villageAddress.setSysUserId(currentUserId);
			}
		}
	}

	private void linkOrgWithOrgType(Organization organization) {
		OrganizationOrganizationTypeDAO ootDAO = new OrganizationOrganizationTypeDAOImpl();

		ootDAO.deleteAllLinksForOrganization(organization.getId());

		for (String typeId : selectedOrgTypes) {
			ootDAO.linkOrganizationAndType(organization, typeId);
		}

	}

	private List getPossibleStates(BaseActionForm dynaForm) {
        List states = null;
		if (useState) {

			CityStateZipDAO cityStateZipDAO = new CityStateZipDAOImpl();

			if (dynaForm.get("states") != null) {
				states = (List) dynaForm.get("states");
			} else {
				states = cityStateZipDAO.getAllStateCodes();
			}
		}
		return states;
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

	protected ActionMessages validateAll( ActionMessages errors, BaseActionForm dynaForm) throws Exception {

		// city validation against database (reusing ajax validation logic
		CityValidationProvider cityValidator = new CityValidationProvider();

		ZipValidationProvider zipCodeValidator = null;
		CityStateZipComboValidationProvider cityStateZipComboValidator = null;
		if (useZip) {
			zipCodeValidator = new ZipValidationProvider();

			if (useState) {
				cityStateZipComboValidator = new CityStateZipComboValidationProvider();
			}
		}

		String messageKey1 = "person.city";
		String messageKey2 = "person.zipCode";
		String messageKey3 = "person.state";

		String result;

		// A valid city exists in the city_state_zip table so unless we are
		// using zip and state then don't do the check
		// This is not obvious
		if (useState && useZip) {
			result = cityValidator.validate((String) dynaForm.get("city"));
			// city is invalid
			// Bugzilla 2005, added null
			if ("invalid".equals(result)) {
				ActionError error = new ActionError("errors.invalid", getMessageForKey(messageKey1), null);
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			}
		}

		if (zipCodeValidator != null) {
			result = zipCodeValidator.validate((String) dynaForm.get("zipCode"));
			// zipCode is invalid
			// Bugzilla 2005, added null
			if ("invalid".equals(result)) {
				ActionError error = new ActionError("errors.invalid", getMessageForKey(messageKey2), null);
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			}
		}

		if (cityStateZipComboValidator != null) {
			result = cityStateZipComboValidator.validate((String) dynaForm.get("city"), null, (String) dynaForm.get("zipCode"));

			// combination is invalid if result is invalid
			if ("invalid".equals(result)) {
				ActionError error = new ActionError("errors.combo.3.invalid", getMessageForKey(messageKey1), getMessageForKey(messageKey2),
						getMessageForKey(messageKey3), null);
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			}
		}

		// parent organization validation against database
		String parentOrgSelected = (String) dynaForm.get("parentOrgName");

		if (!StringUtil.isNullorNill(parentOrgSelected)) {
			Organization organization = new Organization();
			organization.setOrganizationName(parentOrgSelected);
			OrganizationDAO organizationDAO = new OrganizationDAOImpl();
			organization = organizationDAO.getOrganizationByName(organization, true);

			String messageKey = "organization.parent";

			if (organization == null) {
				// the organization is not in database - not valid parentOrg
				ActionError error = new ActionError("errors.invalid", getMessageForKey(messageKey), null);
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			}
		}

		return errors;
	}

}