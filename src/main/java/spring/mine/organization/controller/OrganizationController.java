package spring.mine.organization.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.organization.form.OrganizationForm;
import spring.mine.organization.validator.OrganizationFormValidator;
import us.mn.state.health.lims.address.dao.AddressPartDAO;
import us.mn.state.health.lims.address.dao.OrganizationAddressDAO;
import us.mn.state.health.lims.address.daoimpl.AddressPartDAOImpl;
import us.mn.state.health.lims.address.daoimpl.OrganizationAddressDAOImpl;
import us.mn.state.health.lims.address.valueholder.AddressPart;
import us.mn.state.health.lims.address.valueholder.OrganizationAddress;
import us.mn.state.health.lims.citystatezip.dao.CityStateZipDAO;
import us.mn.state.health.lims.citystatezip.daoimpl.CityStateZipDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.resources.ResourceLocator;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.dao.OrganizationOrganizationTypeDAO;
import us.mn.state.health.lims.organization.dao.OrganizationTypeDAO;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.organization.daoimpl.OrganizationOrganizationTypeDAOImpl;
import us.mn.state.health.lims.organization.daoimpl.OrganizationTypeDAOImpl;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.organization.valueholder.OrganizationType;

@Controller
@SessionAttributes("form")
public class OrganizationController extends BaseController {

	@Autowired
	OrganizationFormValidator validator;

	@ModelAttribute("form")
	public BaseForm form() {
		return new OrganizationForm();
	}

	private static boolean useZip = FormFields.getInstance().useField(FormFields.Field.ZipCode);
	private static boolean useState = FormFields.getInstance().useField(FormFields.Field.OrgState);
	private static boolean useDepartment = FormFields.getInstance().useField(Field.ADDRESS_DEPARTMENT);
	private static boolean useCommune = FormFields.getInstance().useField(Field.ADDRESS_COMMUNE);
	private static boolean useVillage = FormFields.getInstance().useField(Field.ADDRESS_VILLAGE);

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

	private static boolean useParentOrganization = FormFields.getInstance().useField(Field.OrganizationParent);
	private static boolean useOrganizationState = FormFields.getInstance().useField(Field.OrgState);
	private static boolean useOrganizationTypeList = FormFields.getInstance().useField(Field.InlineOrganizationTypes);

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

	@RequestMapping(value = { "/Organization", "/NextPreviousOrganization" }, method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView showOrganization(HttpServletRequest request, @ModelAttribute("form") BaseForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form.getClass() != OrganizationForm.class) {
			form = new OrganizationForm();
			request.getSession().setAttribute("form", form);
		}

		form.setFormAction("");
		form.setCancelAction("CancelOrganization.do");
		Errors errors = new BaseErrors();

		// The first job is to determine if we are coming to this action with an
		// ID parameter in the request. If there is no parameter, we are
		// creating a new Organization.
		// If there is a parameter present, we should bring up an existing
		// Organization to edit.
		String id = request.getParameter(ID);
		String start = request.getParameter("startingRecNo");
		String direction = request.getParameter("direction");

		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "true");
		request.setAttribute(NEXT_DISABLED, "true");

		List<Dictionary> departmentList = getDepartmentList();
		PropertyUtils.setProperty(form, "departmentList", departmentList);

		Organization organization = new Organization();
		organization.setId(id);

		// redirect to get organization for next or previous entry
		if (FWD_NEXT.equals(direction) || FWD_PREVIOUS.equals(direction)) {
			List organizations;
			organizationDAO.getData(organization);
			if (FWD_NEXT.equals(direction)) {
				organizations = organizationDAO.getNextOrganizationRecord(organization.getId());
			} else {
				organizations = organizationDAO.getPreviousOrganizationRecord(organization.getId());
			}
			if (organizations != null && organizations.size() > 0) {
				organization = (Organization) organizations.get(0);
			}
			String newId = organization.getId();
			String url = "redirect:/Organization.do?ID=" + newId + "&startingRecNo=" + start;
			return url;
		}

		boolean isNew = (id == null) || "0".equals(id);
		if (isNew) {
			request.setAttribute("key", "organization.add.title");
		} else {
			request.setAttribute("key", "organization.edit.title");
		}

		if (!isNew) {
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

			if (useCommune || useDepartment || useVillage) {
				List<OrganizationAddress> orgAddressList = orgAddressDAO.getAddressPartsByOrganizationId(id);

				for (OrganizationAddress orgAddress : orgAddressList) {
					if (useCommune && COMMUNE_ID.equals(orgAddress.getAddressPartId())) {
						PropertyUtils.setProperty(form, "commune", orgAddress.getValue());
					} else if (useVillage && VILLAGE_ID.equals(orgAddress.getAddressPartId())) {
						PropertyUtils.setProperty(form, "village", orgAddress.getValue());
					} else if (useDepartment && DEPARTMENT_ID.equals(orgAddress.getAddressPartId())) {
						PropertyUtils.setProperty(form, "department", orgAddress.getValue());
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

		return findForward(forward, form);
	}

	private void setParentOrganiztionName(BaseForm form, Organization organization, OrganizationDAO organizationDAO)
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

	private void setCityStateZipList(BaseForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
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
			orgTypeList = new ArrayList<>();
		}

		return orgTypeList;
	}

	private List<Dictionary> getDepartmentList() {
		DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
		return dictionaryDAO.getDictionaryEntrysByCategoryAbbreviation("description", "haitiDepartment", true);
	}

	@RequestMapping(value = "/UpdateOrganization", method = RequestMethod.POST)
	public ModelAndView showUpdateOrganization(HttpServletRequest request,
			@ModelAttribute("form") OrganizationForm form, BindingResult result, SessionStatus status)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS_INSERT;
		validator.validate(form, result);
		if (result.hasErrors()) {
			saveErrors(result);
			return findForward(FWD_FAIL_INSERT, form);
		}

		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "false");
		request.setAttribute(NEXT_DISABLED, "false");

		String id = request.getParameter(ID);

		boolean isNew = (StringUtil.isNullorNill(id) || "0".equals(id));

		if (isNew) {
			request.setAttribute("key", "organization.add.title");
		} else {
			request.setAttribute("key", "organization.edit.title");
		}

		selectedOrgTypes = form.getStrings("selectedTypes");

		String start = request.getParameter("startingRecNo");
		String direction = request.getParameter("direction");

		Organization organization = new Organization();
		organization.setSysUserId(getSysUserId(request));

		List states = getPossibleStates(form);

		PropertyUtils.copyProperties(organization, form);

		if (FormFields.getInstance().useField(FormFields.Field.OrganizationParent)) {
			String parentOrgName = (String) form.get("parentOrgName");
			Organization o = new Organization();
			o.setOrganizationName(parentOrgName);
			Organization parentOrg = organizationDAO.getOrganizationByName(o, false);
			organization.setOrganization(parentOrg);
		}

		createAddressParts(id, form, isNew);

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
			String errorMsg;
			if (lre.getException() instanceof org.hibernate.StaleObjectStateException) {
				// how can I get popup instead of struts error at the top of
				// page?
				// Errors errors = form.validate(mapping, request);
				result.reject("errors.OptimisticLockException");

			} else {
				// bugzilla 1482
				if (lre.getException() instanceof LIMSDuplicateRecordException) {
					java.util.Locale locale = (java.util.Locale) request.getSession()
							.getAttribute("org.apache.struts.action.LOCALE");
					String messageKey = "organization.organization";
					String msg = ResourceLocator.getInstance().getMessageResources().getMessage(locale, messageKey);
					result.reject("errors.DuplicateRecord.activeonly", new String[] { msg },
							"errors.DuplicateRecord.activeonly");

				} else {
					result.reject("errors.UpdateException");
				}
			}
			saveErrors(result);

			request.setAttribute(PREVIOUS_DISABLED, "true");
			request.setAttribute(NEXT_DISABLED, "true");
			forward = FWD_FAIL_INSERT;

		} finally {
			HibernateUtil.closeSession();
		}
		if (forward.equals(FWD_FAIL)) {
			return findForward(forward, form);
		}

		PropertyUtils.copyProperties(form, organization);

		if (states != null) {
			PropertyUtils.setProperty(form, "states", states);
		}

		if ("true".equalsIgnoreCase(request.getParameter("close"))) {
			forward = FWD_CLOSE;
		}

		if (organization.getId() != null && !organization.getId().equals("0")) {
			request.setAttribute(ID, organization.getId());
		}

		if (isNew) {
			forward = FWD_SUCCESS_INSERT;
			status.setComplete();
		}

		DisplayListService.refreshList(DisplayListService.ListType.REFERRAL_ORGANIZATIONS);

		return getForward(findForward(forward, form), id, start, direction);
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

	private void createAddressParts(String id, BaseForm form, boolean isNew) {
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

				departmentAddress.setValue(form.getString("department"));
				departmentAddress.setSysUserId(getSysUserId(request));
			}

			if (useCommune) {
				if (!updateCommune) {
					communeAddress = new OrganizationAddress();
					communeAddress.setAddressPartId(COMMUNE_ID);
					communeAddress.setType("T");
				}

				communeAddress.setValue(form.getString("commune"));
				communeAddress.setSysUserId(getSysUserId(request));
			}

			if (useVillage) {
				if (!updateVillage) {
					villageAddress = new OrganizationAddress();
					villageAddress.setAddressPartId(VILLAGE_ID);
					villageAddress.setType("T");
				}

				villageAddress.setValue(form.getString("village"));
				villageAddress.setSysUserId(getSysUserId(request));
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

	private List getPossibleStates(BaseForm form) {
		List states = null;
		if (useState) {

			CityStateZipDAO cityStateZipDAO = new CityStateZipDAOImpl();

			if (form.get("states") != null) {
				states = (List) form.get("states");
			} else {
				states = cityStateZipDAO.getAllStateCodes();
			}
		}
		return states;
	}

	@RequestMapping(value = "/CancelOrganization", method = RequestMethod.GET)
	public ModelAndView cancelOrganization(HttpServletRequest request, @ModelAttribute("form") OrganizationForm form,
			SessionStatus status) {
		status.setComplete();
		return findForward(FWD_CANCEL, form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "organizationDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "redirect:/MasterListsPage.do";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/OrganizationMenu.do";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "organizationDefinition";
		} else if (FWD_CANCEL.equals(forward)) {
			return "redirect:/OrganizationMenu.do";
		} else {
			return "PageNotFound";
		}
	}

	@Override
	protected String getPageTitleKey() {
		return (String) request.getAttribute("key");
	}

	@Override
	protected String getPageSubtitleKey() {
		return (String) request.getAttribute("key");
	}
}
