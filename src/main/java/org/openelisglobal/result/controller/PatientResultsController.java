package org.openelisglobal.result.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.StatusService.AnalysisStatus;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.inventory.action.InventoryUtility;
import org.openelisglobal.inventory.form.InventoryKitItem;
import org.openelisglobal.patient.action.bean.PatientSearch;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.result.action.util.ResultsLoadUtility;
import org.openelisglobal.result.action.util.ResultsPaging;
import org.openelisglobal.result.form.PatientResultsForm;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.test.beanItems.TestResultItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PatientResultsController extends BaseController {

    @Autowired
    PatientService patientService;

    @RequestMapping(value = "/PatientResults", method = RequestMethod.GET)
    public ModelAndView showPatientResults(HttpServletRequest request)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        PatientResultsForm form = new PatientResultsForm();

        ResultsLoadUtility resultsUtility = SpringContext.getBean(ResultsLoadUtility.class);
        resultsUtility.setSysUser(getSysUserId(request));
        request.getSession().setAttribute(SAVE_DISABLED, TRUE);

        PropertyUtils.setProperty(form, "displayTestKit", Boolean.FALSE);
        PropertyUtils.setProperty(form, "referralReasons",
                DisplayListService.getInstance().getList(DisplayListService.ListType.REFERRAL_REASONS));
        PropertyUtils.setProperty(form, "rejectReasons", DisplayListService.getInstance()
                .getNumberedListWithLeadingBlank(DisplayListService.ListType.REJECTION_REASONS));
        PatientSearch patientSearch = new PatientSearch();
        patientSearch.setLoadFromServerWithPatient(true);
        patientSearch.setSelectedPatientActionButtonText(MessageUtil.getMessage("resultsentry.patient.search"));
        PropertyUtils.setProperty(form, "patientSearch", patientSearch);

        ResultsPaging paging = new ResultsPaging();
        String newPage = request.getParameter("page");
        if (GenericValidator.isBlankOrNull(newPage)) {

            String patientID = request.getParameter("patientID");

            if (!GenericValidator.isBlankOrNull(patientID)) {

                PropertyUtils.setProperty(form, "searchFinished", Boolean.TRUE);
                Patient patient = getPatient(patientID);

                String statusRules = ConfigurationProperties.getInstance()
                        .getPropertyValueUpperCase(Property.StatusRules);
                if (statusRules.equals(STATUS_RULES_RETROCI)) {
                    resultsUtility.addExcludedAnalysisStatus(AnalysisStatus.TechnicalRejected);
                    resultsUtility.addExcludedAnalysisStatus(AnalysisStatus.Canceled);
                } else if (statusRules.equals(STATUS_RULES_HAITI) || statusRules.equals(STATUS_RULES_HAITI_LNSP)) {
                    resultsUtility.addExcludedAnalysisStatus(AnalysisStatus.Canceled);
                }

                List<TestResultItem> results = resultsUtility.getGroupedTestsForPatient(patient);

                PropertyUtils.setProperty(form, "testResult", results);

                // move this out of results utility
                resultsUtility.addIdentifingPatientInfo(patient, form);

                if (resultsUtility.inventoryNeeded()) {
                    addInventory(form);
                    PropertyUtils.setProperty(form, "displayTestKit", true);
                } else {
                    addEmptyInventoryList(form);
                }

                paging.setDatabaseResults(request, form, results);

            } else {
                PropertyUtils.setProperty(form, "testResult", new ArrayList<TestResultItem>());
                PropertyUtils.setProperty(form, "searchFinished", Boolean.FALSE);
            }
        } else {
            paging.page(request, form, newPage);
        }

        addFlashMsgsToRequest(request);
        return findForward(FWD_SUCCESS, form);
    }

    private void addInventory(PatientResultsForm form)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        InventoryUtility inventoryUtility = SpringContext.getBean(InventoryUtility.class);
        List<InventoryKitItem> list = inventoryUtility.getExistingActiveInventory();
        PropertyUtils.setProperty(form, "inventoryItems", list);
    }

    private void addEmptyInventoryList(PatientResultsForm form)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        PropertyUtils.setProperty(form, "inventoryItems", new ArrayList<InventoryKitItem>());
    }

    private Patient getPatient(String patientID) {
        return patientService.get(patientID);
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "patientResultDefinition";
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
