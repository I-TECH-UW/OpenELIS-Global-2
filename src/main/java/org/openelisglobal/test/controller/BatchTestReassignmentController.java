package org.openelisglobal.test.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openelisglobal.analysis.service.AnalysisService;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.StatusService;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.test.action.BatchTestStatusChangeBean;
import org.openelisglobal.test.form.BatchTestReassignmentForm;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.test.service.TestServiceImpl;
import org.openelisglobal.test.validator.BatchTestReassignmentFormValidator;
import org.openelisglobal.test.valueholder.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BatchTestReassignmentController extends BaseController {

    private static final String[] ALLOWED_FIELDS = new String[] { "jsonWad" };

    @Autowired
    BatchTestReassignmentFormValidator formValidator;

    @Autowired
    private AnalysisService analysisService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping(value = "/BatchTestReassignment", method = RequestMethod.GET)
    public ModelAndView showBatchTestReassignment(HttpServletRequest request)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        BatchTestReassignmentForm form = new BatchTestReassignmentForm();

        form.setSampleList(DisplayListService.getInstance().getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE));

        addFlashMsgsToRequest(request);
        return findForward(FWD_SUCCESS, form);
    }

    @RequestMapping(value = "/BatchTestReassignment", method = RequestMethod.POST)
    public ModelAndView showBatchTestReassignmentUpdate(HttpServletRequest request,
            @ModelAttribute("form") @Valid BatchTestReassignmentForm form, BindingResult result,
            RedirectAttributes redirectAttributes)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        formValidator.validate(form, result);
        if (result.hasErrors()) {
            saveErrors(result);
            return findForward(FWD_FAIL_INSERT, form);
        }
        String jsonString = form.getJsonWad();
        // LogEvent.logInfo(this.getClass().getSimpleName(), "method unkown",
        // jsonString);

        List<Analysis> newAnalysis = new ArrayList<>();
        List<Analysis> cancelAnalysis = new ArrayList<>();
        List<BatchTestStatusChangeBean> changeBeans = new ArrayList<>();
        StatusChangedMetaInfo changedMetaInfo = new StatusChangedMetaInfo();
        manageAnalysis(jsonString, cancelAnalysis, newAnalysis, changeBeans, changedMetaInfo);

        try {
            analysisService.updateAnalysises(cancelAnalysis, newAnalysis, getSysUserId(request));

        } catch (LIMSRuntimeException e) {
            LogEvent.logError(e);
        }

        if (changeBeans.isEmpty()) {
            redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
            return findForward(FWD_SUCCESS_INSERT, form);
        } else {
            form.setSampleList(
                    DisplayListService.getInstance().getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE));
            form.setStatusChangedList(changeBeans);
            form.setStatusChangedSampleType(changedMetaInfo.sampleTypeName);
            form.setStatusChangedCurrentTest(changedMetaInfo.currentTest);
            form.setStatusChangedNextTest(changedMetaInfo.nextTest);
            return findForward("resubmit", form);
        }
    }

    private void manageAnalysis(String jsonString, List<Analysis> cancelAnalysis, List<Analysis> newAnalysis,
            List<BatchTestStatusChangeBean> changeBeans, StatusChangedMetaInfo changedMetaInfo) {
        JSONParser parser = new JSONParser();

        try {
            JSONObject obj = (JSONObject) parser.parse(jsonString);
            List<Test> newTests = getNewTestsFromJson(obj, parser);
            List<Analysis> changedNotStarted = getAnalysisFromJson((String) obj.get("changeNotStarted"), parser);
            List<Analysis> noChangedNotStarted = getAnalysisFromJson((String) obj.get("noChangeNotStarted"), parser);
            List<Analysis> changeTechReject = getAnalysisFromJson((String) obj.get("changeTechReject"), parser);
            List<Analysis> noChangeTechReject = getAnalysisFromJson((String) obj.get("noChangeTechReject"), parser);
            List<Analysis> changeBioReject = getAnalysisFromJson((String) obj.get("changeBioReject"), parser);
            List<Analysis> noChangeBioReject = getAnalysisFromJson((String) obj.get("noChangeBioReject"), parser);
            List<Analysis> changeNotValidated = getAnalysisFromJson((String) obj.get("changeNotValidated"), parser);
            List<Analysis> noChangeNotValidated = getAnalysisFromJson((String) obj.get("noChangeNotValidated"), parser);

            verifyStatusNotChanged(changedNotStarted, noChangedNotStarted, StatusService.AnalysisStatus.NotStarted,
                    changeBeans);
            verifyStatusNotChanged(changeNotValidated, noChangeNotValidated,
                    StatusService.AnalysisStatus.TechnicalAcceptance, changeBeans);
            verifyStatusNotChanged(changeTechReject, noChangeTechReject, StatusService.AnalysisStatus.TechnicalRejected,
                    changeBeans);
            verifyStatusNotChanged(changeBioReject, noChangeBioReject, StatusService.AnalysisStatus.BiologistRejected,
                    changeBeans);

            cancelAnalysis.addAll(changedNotStarted);
            cancelAnalysis.addAll(changeBioReject);
            cancelAnalysis.addAll(changeNotValidated);
            cancelAnalysis.addAll(changeTechReject);

            if (!newTests.isEmpty()) {
                newAnalysis.addAll(createNewAnalysis(newTests, changedNotStarted));
                newAnalysis.addAll(createNewAnalysis(newTests, changeBioReject));
                newAnalysis.addAll(createNewAnalysis(newTests, changeNotValidated));
                newAnalysis.addAll(createNewAnalysis(newTests, changeTechReject));
            }

            if (!changeBeans.isEmpty()) {
                String newTestsString;
                if (newTests.isEmpty()) {
                    newTestsString = MessageUtil.getMessage("status.test.canceled");
                } else {
                    newTestsString = MessageUtil.getMessage("label.test.batch.reassignment") + ": "
                            + TestServiceImpl.getUserLocalizedTestName(newTests.get(0));
                    for (int i = 1; i < newTests.size(); i++) {
                        newTestsString += ", " + TestServiceImpl.getUserLocalizedTestName(newTests.get(i));
                    }
                }
                changedMetaInfo.nextTest = newTestsString;
                changedMetaInfo.currentTest = (String) obj.get("current");
                changedMetaInfo.sampleTypeName = (String) obj.get("sampleType");
            }
        } catch (ParseException e) {
            LogEvent.logDebug(e);
        }
    }

    private List<Analysis> createNewAnalysis(List<Test> newTests, List<Analysis> changeAnalysis) {
        List<Analysis> newAnalysis = new ArrayList<>();
        for (Test test : newTests) {
            for (Analysis analysis : changeAnalysis) {
                newAnalysis.add(analysisService.buildAnalysis(test, analysis.getSampleItem()));
            }
        }

        return newAnalysis;
    }

    private void verifyStatusNotChanged(List<Analysis> changed, List<Analysis> noChanged,
            StatusService.AnalysisStatus status, List<BatchTestStatusChangeBean> changeBeans) {
        String statusId = SpringContext.getBean(IStatusService.class).getStatusID(status);

        List<Analysis> changedAnalysis = new ArrayList<>();

        for (Analysis analysis : changed) {
            if (!statusId.equals(analysis.getStatusId())) {
                changedAnalysis.add(analysis);
            }
        }

        if (!changedAnalysis.isEmpty()) {
            changed.removeAll(changedAnalysis);
        }

        for (Analysis analysis : noChanged) {
            if (!statusId.equals(analysis.getStatusId())) {
                changedAnalysis.add(analysis);
            }
        }

        if (!changedAnalysis.isEmpty()) {
            String oldStatus = getStatusName(status);

            for (Analysis analysis : changedAnalysis) {
                BatchTestStatusChangeBean bean = new BatchTestStatusChangeBean();
                bean.setLabNo(analysis.getSampleItem().getSample().getAccessionNumber());
                bean.setOldStatus(oldStatus);
                bean.setNewStatus(getStatusName(analysis.getStatusId()));
                changeBeans.add(bean);
            }
        }
    }

    private String getStatusName(String statusId) {
        StatusService.AnalysisStatus status = SpringContext.getBean(IStatusService.class)
                .getAnalysisStatusForID(statusId);
        String name = getStatusName(status);
        return name == null ? SpringContext.getBean(IStatusService.class).getStatusName(status) : name;
    }

    private String getStatusName(StatusService.AnalysisStatus status) {
        switch (status) {
        case NotStarted:
            return MessageUtil.getMessage("label.analysisNotStarted");
        case TechnicalRejected:
            return MessageUtil.getMessage("label.rejectedByTechnician");
        case TechnicalAcceptance:
            return MessageUtil.getMessage("label.notValidated");
        case BiologistRejected:
            return MessageUtil.getMessage("label.rejectedByBiologist");
        default:
            return null;
        }
    }

    private List<Test> getNewTestsFromJson(JSONObject obj, JSONParser parser) {
        List<Test> replacementTestList = new ArrayList<>();

        String replacementTests = (String) obj.get("replace");
        if (replacementTests == null) {
            return replacementTestList;
        }

        JSONArray replacementTestArray;
        try {
            replacementTestArray = (JSONArray) parser.parse(replacementTests);
        } catch (ParseException e) {
            LogEvent.logDebug(e);
            return replacementTestList;
        }

        for (Object testIdObject : replacementTestArray) {
            replacementTestList.add(SpringContext.getBean(TestService.class).get((String) testIdObject));
        }

        return replacementTestList;
    }

    private List<Analysis> getAnalysisFromJson(String sampleIdList, JSONParser parser) {
        List<Analysis> analysisList = new ArrayList<>();

        if (sampleIdList == null) {
            return analysisList;
        }

        JSONArray modifyAnalysisArray;
        try {
            modifyAnalysisArray = (JSONArray) parser.parse(sampleIdList);
        } catch (ParseException e) {
            LogEvent.logDebug(e);
            return analysisList;
        }

        for (Object analysisId : modifyAnalysisArray) {
            analysisList.add(analysisService.get((String) analysisId));
        }

        return analysisList;
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "BatchTestReassignmentDefinition";
        } else if (FWD_SUCCESS_INSERT.equals(forward)) {
            return "redirect:/BatchTestReassignment";
        } else if (FWD_FAIL_INSERT.equals(forward)) {
            return "BatchTestReassignmentDefinition";
        } else if ("resubmit".equals(forward)) {
            return "BatchTestReassignmentDefinition";
        } else {
            return "PageNotFound";
        }
    }

    @Override
    protected String getPageTitleKey() {
        return "configuration.batch.test.reassignment";
    }

    @Override
    protected String getPageSubtitleKey() {
        return "configuration.batch.test.reassignment";
    }

    private class StatusChangedMetaInfo {
        public String currentTest;
        public String nextTest;
        public String sampleTypeName;
    }
}
