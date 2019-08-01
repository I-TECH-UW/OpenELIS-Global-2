package org.openelisglobal.result.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.result.form.AccessionResultsForm;
import org.openelisglobal.role.service.RoleService;
import org.openelisglobal.sample.service.SampleService;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.userrole.service.UserRoleService;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.StatusService.AnalysisStatus;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.inventory.action.InventoryUtility;
import org.openelisglobal.inventory.form.InventoryKitItem;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.result.action.util.ResultsLoadUtility;
import org.openelisglobal.result.action.util.ResultsPaging;
import org.openelisglobal.role.valueholder.Role;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.test.beanItems.TestResultItem;

@Controller
public class AccessionResultsController extends BaseController {

    private static String RESULT_EDIT_ROLE_ID;

    private String accessionNumber;
    private Sample sample;
    private InventoryUtility inventoryUtility = SpringContext.getBean(InventoryUtility.class);
    @Autowired
    SampleService sampleService;
    @Autowired
    SampleHumanService sampleHumanService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    @PostConstruct
    private void initializeGlobalVariables() {
        Role editRole = roleService.getRoleByName("Results modifier");

        if (editRole != null) {
            RESULT_EDIT_ROLE_ID = editRole.getId();
        }
    }

    @RequestMapping(value = "/AccessionResults", method = RequestMethod.GET)
    public ModelAndView showAccessionResults(HttpServletRequest request)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        AccessionResultsForm form = new AccessionResultsForm();

        request.getSession().setAttribute(SAVE_DISABLED, TRUE);
        PropertyUtils.setProperty(form, "referralReasons",
                DisplayListService.getInstance().getList(DisplayListService.ListType.REFERRAL_REASONS));
        PropertyUtils.setProperty(form, "rejectReasons", DisplayListService.getInstance()
                .getNumberedListWithLeadingBlank(DisplayListService.ListType.REJECTION_REASONS));

        ResultsPaging paging = new ResultsPaging();
        String newPage = request.getParameter("page");
        if (GenericValidator.isBlankOrNull(newPage)) {

            accessionNumber = request.getParameter("accessionNumber");
            PropertyUtils.setProperty(form, "displayTestKit", false);

            if (!GenericValidator.isBlankOrNull(accessionNumber)) {
                Errors errors = new BeanPropertyBindingResult(form, "form");
                ResultsLoadUtility resultsUtility = SpringContext.getBean(ResultsLoadUtility.class);
                resultsUtility.setSysUser(getSysUserId(request));
                // This is for Haiti_LNSP if it gets more complicated use the status set stuff
                resultsUtility.addExcludedAnalysisStatus(AnalysisStatus.Canceled);
                // resultsUtility.addExcludedAnalysisStatus(AnalysisStatus.Finalized);
                resultsUtility.setLockCurrentResults(modifyResultsRoleBased() && userNotInRole(request));
                validateAll(request, errors, form);

                if (errors.hasErrors()) {
                    saveErrors(errors);
                    request.setAttribute(ALLOW_EDITS_KEY, "false");

                    setEmptyResults(form);

                    return findForward(FWD_FAIL, form);
                }

                PropertyUtils.setProperty(form, "searchFinished", Boolean.TRUE);

                getSample();

                if (!GenericValidator.isBlankOrNull(sample.getId())) {
                    Patient patient = getPatient();
                    resultsUtility.addIdentifingPatientInfo(patient, form);

                    List<TestResultItem> results = resultsUtility.getGroupedTestsForSample(sample, patient);

                    if (resultsUtility.inventoryNeeded()) {
                        addInventory(form);
                        PropertyUtils.setProperty(form, "displayTestKit", true);
                    } else {
                        addEmptyInventoryList(form);
                    }

                    paging.setDatabaseResults(request, form, results);
                } else {
                    setEmptyResults(form);
                }
            } else {
                PropertyUtils.setProperty(form, "testResult", new ArrayList<TestResultItem>());
                PropertyUtils.setProperty(form, "searchFinished", Boolean.FALSE);
            }
        } else {
            paging.page(request, form, newPage);
        }

        return findForward(FWD_SUCCESS, form);
    }

    private boolean modifyResultsRoleBased() {
        return "true"
                .equals(ConfigurationProperties.getInstance().getPropertyValue(Property.roleRequiredForModifyResults));
    }

    private boolean userNotInRole(HttpServletRequest request) {
        if (userModuleService.isUserAdmin(request)) {
            return false;
        }

        List<String> roleIds = userRoleService.getRoleIdsForUser(getSysUserId(request));

        return !roleIds.contains(RESULT_EDIT_ROLE_ID);
    }

    private void setEmptyResults(AccessionResultsForm form)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        PropertyUtils.setProperty(form, "testResult", new ArrayList<TestResultItem>());
        PropertyUtils.setProperty(form, "displayTestKit", false);
        addEmptyInventoryList(form);
    }

    private void addInventory(AccessionResultsForm form)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        List<InventoryKitItem> list = inventoryUtility.getExistingActiveInventory();
        List<String> hivKits = new ArrayList<>();
        List<String> syphilisKits = new ArrayList<>();
        for (InventoryKitItem item : list) {
            if (item.getType().equals("HIV")) {
                hivKits.add(item.getInventoryLocationId());
            } else {
                syphilisKits.add(item.getInventoryLocationId());
            }
        }
        PropertyUtils.setProperty(form, "hivKits", hivKits);
        PropertyUtils.setProperty(form, "syphilisKits", syphilisKits);
        PropertyUtils.setProperty(form, "inventoryItems", list);
    }

    private void addEmptyInventoryList(AccessionResultsForm form)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        PropertyUtils.setProperty(form, "inventoryItems", new ArrayList<InventoryKitItem>());
        PropertyUtils.setProperty(form, "hivKits", new ArrayList<String>());
        PropertyUtils.setProperty(form, "syphilisKits", new ArrayList<String>());
    }

    private Errors validateAll(HttpServletRequest request, Errors errors, AccessionResultsForm form) {

        Sample sample = sampleService.getSampleByAccessionNumber(accessionNumber);

        if (sample == null) {
            // ActionError error = new ActionError("sample.edit.sample.notFound",
            // accessionNumber, null, null);
            errors.reject("sample.edit.sample.notFound", new String[] { accessionNumber },
                    "sample.edit.sample.notFound");
        }

        return errors;
    }

    private Patient getPatient() {
        return sampleHumanService.getPatientForSample(sample);
    }

    private void getSample() {
        sample = sampleService.getSampleByAccessionNumber(accessionNumber);
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "accessionResultDefinition";
        } else if (FWD_FAIL.equals(forward)) {
            return "accessionResultDefinition";
        } else {
            return "PageNotFound";
        }
    }

    @Override
    protected String getPageTitleKey() {
        return "banner.menu.results";
    }

    @Override
    protected String getPageSubtitleKey() {
        return "banner.menu.results";
    }
}
