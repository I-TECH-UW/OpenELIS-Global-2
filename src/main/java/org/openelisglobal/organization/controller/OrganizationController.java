package org.openelisglobal.organization.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.organization.form.OrganizationForm;
import org.openelisglobal.address.service.AddressPartService;
import org.openelisglobal.address.service.OrganizationAddressService;
import org.openelisglobal.citystatezip.service.CityStateZipService;
import org.openelisglobal.dictionary.service.DictionaryService;
import org.openelisglobal.organization.service.OrganizationService;
import org.openelisglobal.organization.service.OrganizationTypeService;
import org.openelisglobal.address.valueholder.AddressPart;
import org.openelisglobal.address.valueholder.OrganizationAddress;
import org.openelisglobal.common.exception.LIMSDuplicateRecordException;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.formfields.FormFields;
import org.openelisglobal.common.formfields.FormFields.Field;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.dictionary.valueholder.Dictionary;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.organization.valueholder.OrganizationType;

@Controller
@SessionAttributes("form")
public class OrganizationController extends BaseController {

    @Autowired
    OrganizationService organizationService;
    @Autowired
    OrganizationAddressService organizationAddressService;
    @Autowired
    AddressPartService addressPartService;
    @Autowired
    CityStateZipService cityStateZipService;
    @Autowired
    OrganizationTypeService organizationTypeService;
    @Autowired
    DictionaryService dictionaryService;

    @ModelAttribute("form")
    public OrganizationForm form() {
        return new OrganizationForm();
    }

    private static boolean useZip = FormFields.getInstance().useField(FormFields.Field.ZipCode);
    private static boolean useState = FormFields.getInstance().useField(FormFields.Field.OrgState);
    private static boolean useDepartment = FormFields.getInstance().useField(Field.ADDRESS_DEPARTMENT);
    private static boolean useCommune = FormFields.getInstance().useField(Field.ADDRESS_COMMUNE);
    private static boolean useVillage = FormFields.getInstance().useField(Field.ADDRESS_VILLAGE);

    private List<String> selectedOrgTypes;

    private String DEPARTMENT_ID;
    private String COMMUNE_ID;
    private String VILLAGE_ID;

    private OrganizationAddress departmentAddress;
    private boolean updateDepartment = false;
    private OrganizationAddress communeAddress;
    private boolean updateCommune = false;
    private OrganizationAddress villageAddress;
    private boolean updateVillage = false;

    private static boolean useParentOrganization = FormFields.getInstance().useField(Field.OrganizationParent);
    private static boolean useOrganizationState = FormFields.getInstance().useField(Field.OrgState);
    private static boolean useOrganizationTypeList = FormFields.getInstance().useField(Field.InlineOrganizationTypes);

    @PostConstruct
    private void initialize() {
        List<AddressPart> partList = addressPartService.getAll();
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

    @RequestMapping(value = { "/Organization", "/NextPreviousOrganization" }, method = RequestMethod.GET)
    public ModelAndView showOrganization(HttpServletRequest request, @ModelAttribute("form") BaseForm form)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        form = resetFormToType(form, OrganizationForm.class);

        form.setCancelAction("CancelOrganization.do");

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

        Organization organization;

        // redirect to get organization for next or previous entry
        if (FWD_NEXT.equals(direction)) {
            organization = organizationService.getNext(id);
            String newId = organization.getId();
            String url = "redirect:/Organization.do?ID=" + newId + "&startingRecNo=" + start;
            return new ModelAndView(url);
        } else if (FWD_PREVIOUS.equals(direction)) {
            organization = organizationService.getPrevious(id);
            String newId = organization.getId();
            String url = "redirect:/Organization.do?ID=" + newId + "&startingRecNo=" + start;
            return new ModelAndView(url);
        }

        boolean isNew = (id == null) || "0".equals(id);
        if (isNew) {
            request.setAttribute("key", "organization.add.title");
            organization = new Organization();

            // default isActive to 'Y'
            organization.setIsActive(YES);
            organization.setMlsSentinelLabFlag(NO);
            organization.setMlsLabFlag("N");
        } else {
            request.setAttribute("key", "organization.edit.title");

            organization = organizationService.get(id);
            if (organization.getOrganization() != null) {
                organization.setSelectedOrgId(organization.getOrganization().getId());
            }

            if (organizationService.hasNext(id)) {
                request.setAttribute(NEXT_DISABLED, "false");
            }
            if (organizationService.hasPrevious(id)) {
                request.setAttribute(PREVIOUS_DISABLED, "false");
            }

            if (useCommune || useDepartment || useVillage) {
                List<OrganizationAddress> orgAddressList = organizationAddressService
                        .getAddressPartsByOrganizationId(id);

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
            setParentOrganiztionName(form, organization);
        }

        if (useOrganizationState) {
            setCityStateZipList(form);
        }

        if (useOrganizationTypeList) {
            List<OrganizationType> orgTypeList = getOrganizationTypeList();
            List<String> selectedList = new ArrayList<>();
            PropertyUtils.setProperty(form, "orgTypes", orgTypeList);

            if (organization.getId() != null && orgTypeList != null) {
                if (orgTypeList.size() > 0) {
                    List<String> selectedOrgTypeList = organizationService
                            .getTypeIdsForOrganizationId(organization.getId());
                    for (String orgTypeId : selectedOrgTypeList) {
                        selectedList.add(orgTypeId);
                    }
                }
            }
            PropertyUtils.setProperty(form, "selectedTypes", selectedList);
        }

        return findForward(FWD_SUCCESS, form);
    }

    private void setParentOrganiztionName(BaseForm form, Organization organization)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Organization parentOrg = new Organization();
        String parentOrgName = null;

        if (!StringUtil.isNullorNill(organization.getSelectedOrgId())) {
            parentOrg = organizationService.get(organization.getSelectedOrgId());
            parentOrgName = parentOrg.getOrganizationName();
        }

        PropertyUtils.setProperty(form, "parentOrgName", parentOrgName);
    }

    private void setCityStateZipList(BaseForm form)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (FormFields.getInstance().useField(FormFields.Field.OrgState)) {
            // bugzilla 1545
            List states = cityStateZipService.getAllStateCodes();
            PropertyUtils.setProperty(form, "states", states);
        }
    }

    private List<OrganizationType> getOrganizationTypeList() {

        List<OrganizationType> orgTypeList = organizationTypeService.getAll();
        if (orgTypeList == null) {
            orgTypeList = new ArrayList<>();
        }

        return orgTypeList;
    }

    private List<Dictionary> getDepartmentList() {
        return dictionaryService.getDictionaryEntrysByCategoryAbbreviation("description", "haitiDepartment", true);
    }

    @RequestMapping(value = "/Organization", method = RequestMethod.POST)
    public ModelAndView showUpdateOrganization(HttpServletRequest request,
            @ModelAttribute("form") @Valid OrganizationForm form, BindingResult result, SessionStatus status,
            RedirectAttributes redirectAttributes)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        setDefaultButtonAttributes(request);
        if (result.hasErrors()) {
            saveErrors(result);
            return findForward(FWD_FAIL_INSERT, form);
        }

        String id = request.getParameter(ID);
        form.setId(id);
        Organization organization;
        boolean isNew = (StringUtil.isNullorNill(id) || "0".equals(id));
        if (isNew) {
            organization = new Organization();
            request.setAttribute("key", "organization.add.title");
        } else {
            organization = organizationService.get(id);
            request.setAttribute("key", "organization.edit.title");
        }

        selectedOrgTypes = (List<String>) form.get("selectedTypes");

        organization.setSysUserId(getSysUserId(request));

        List states = getPossibleStates(form);
        PropertyUtils.copyProperties(organization, form);

        if (FormFields.getInstance().useField(FormFields.Field.OrganizationParent)) {
            String parentOrgName = (String) form.get("parentOrgName");
            Organization o = new Organization();
            o.setOrganizationName(parentOrgName);
            Organization parentOrg = organizationService.getOrganizationByName(o, false);
            organization.setOrganization(parentOrg);
        }
        createAddressParts(id, form, isNew);

        try {
            if (!isNew) {
                organizationService.update(organization);
            } else {
                organizationService.insert(organization);
            }

            persistAddressParts(organization);

            linkOrgWithOrgType(organization);

        } catch (LIMSRuntimeException lre) {
            // bugzilla 2154
            LogEvent.logError("OrganizationUpdateAction", "performAction()", lre.toString());
            if (lre.getException() instanceof org.hibernate.StaleObjectStateException) {
                result.reject("errors.OptimisticLockException");
            } else {
                // bugzilla 1482
                if (lre.getException() instanceof LIMSDuplicateRecordException) {
                    String messageKey = "organization.organization";
                    String msg = MessageUtil.getMessage(messageKey);
                    result.reject("errors.DuplicateRecord.activeonly", new String[] { msg },
                            "errors.DuplicateRecord.activeonly");
                } else {
                    result.reject("errors.UpdateException");
                }
            }
            saveErrors(result);
            request.setAttribute(PREVIOUS_DISABLED, "true");
            request.setAttribute(NEXT_DISABLED, "true");
            return findForward(FWD_FAIL_INSERT, form);

        }
//		finally {
//			HibernateUtil.closeSession();
//		}
        PropertyUtils.copyProperties(form, organization);

        if (states != null) {
            PropertyUtils.setProperty(form, "states", states);
        }

        if (organization.getId() != null && !organization.getId().equals("0")) {
            request.setAttribute(ID, organization.getId());
        }

        DisplayListService.getInstance().refreshList(DisplayListService.ListType.REFERRAL_ORGANIZATIONS);

        redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
        status.setComplete();
        return findForward(FWD_SUCCESS_INSERT, form);
    }

    private void setDefaultButtonAttributes(HttpServletRequest request) {
        request.setAttribute(ALLOW_EDITS_KEY, "true");
        request.setAttribute(PREVIOUS_DISABLED, "false");
        request.setAttribute(NEXT_DISABLED, "false");
    }

    private void persistAddressParts(Organization organization) {
        if (departmentAddress != null) {
            if (updateDepartment) {
                organizationAddressService.update(departmentAddress);
            } else {
                departmentAddress.setOrganizationId(organization.getId());
                organizationAddressService.insert(departmentAddress);
            }
        }

        if (communeAddress != null) {
            if (updateCommune) {
                organizationAddressService.update(communeAddress);
            } else {
                communeAddress.setOrganizationId(organization.getId());
                organizationAddressService.insert(communeAddress);
            }
        }

        if (villageAddress != null) {
            if (updateVillage) {
                organizationAddressService.update(villageAddress);
            } else {
                villageAddress.setOrganizationId(organization.getId());
                organizationAddressService.insert(villageAddress);
            }
        }
    }

    private void createAddressParts(String id, BaseForm form, boolean isNew) {
        if (useDepartment || useCommune || useVillage) {
            updateDepartment = false;
            updateCommune = false;
            updateVillage = false;
            if (!isNew) {
                List<OrganizationAddress> orgAddressList = organizationAddressService
                        .getAddressPartsByOrganizationId(id);

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
        organizationService.deleteAllLinksForOrganization(organization.getId());

        for (String typeId : selectedOrgTypes) {
            organizationService.linkOrganizationAndType(organization, typeId);
        }
    }

    private List getPossibleStates(BaseForm form) {
        List states = null;
        if (useState) {
            if (form.get("states") != null) {
                states = (List) form.get("states");
            } else {
                states = cityStateZipService.getAllStateCodes();
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
