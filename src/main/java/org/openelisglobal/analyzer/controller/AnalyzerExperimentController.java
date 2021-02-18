package org.openelisglobal.analyzer.controller;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.openelisglobal.analyzer.form.AnalyzerSetupForm;
import org.openelisglobal.analyzer.service.AnalyzerExperimentService;
import org.openelisglobal.analyzer.service.AnalyzerService;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.exception.LIMSException;
import org.openelisglobal.common.util.LabelValuePair;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.patient.action.bean.PatientSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AnalyzerExperimentController extends BaseController {

    @Autowired
    private AnalyzerExperimentService analyzerExperimentService;
    @Autowired
    private AnalyzerService analyzerService;

    @GetMapping("/AnalyzerSetup")
    public ModelAndView displayAnalyzerSetup() {
        AnalyzerSetupForm form = new AnalyzerSetupForm();
        PatientSearch patientSearch = new PatientSearch();
        patientSearch.setLoadFromServerWithPatient(true);
        patientSearch.setSelectedPatientActionButtonText(MessageUtil.getMessage("label.patient.search.select"));
        form.setPatientSearch(patientSearch);
        form.setAnalyzers(analyzerService.getAllMatching("hasSetupPage", true).stream()
                .map(e -> new LabelValuePair(e.getName(), e.getId().toString())).collect(Collectors.toList()));
        form.setPreviousRuns(analyzerExperimentService.getAllOrdered("lastupdated", true).stream()
                .map(e -> new LabelValuePair(e.getName(), e.getId().toString()))
                .collect(Collectors.toList()));
        return findForward(FWD_SUCCESS, form);
    }

    @GetMapping(path = "/AnalyzerSetup/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> getSetup(@PathVariable Integer id) throws IOException {
        Map<String, String> wellValues = analyzerExperimentService.getWellValuesForId(id);
        return ResponseEntity.ok(wellValues);
    }

    @PostMapping(path = "/AnalyzerSetupAPI", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> saveSetupFile(@Valid AnalyzerSetupForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            saveErrors(result);
            return ResponseEntity.badRequest().build();
        }
        try {
            Integer id = analyzerExperimentService.saveMapAsCSVFile(form.getFilename(), form.getWellValues());
            redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
            return ResponseEntity.ok(id);
        } catch (LIMSException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "/AnalyzerSetupFile/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<byte[]> getSetupFile(@PathVariable Integer id, @RequestParam("filename") String fileName) {
        byte[] file = analyzerExperimentService.get(id).getFile();
        return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=" + fileName + ".csv")
                .contentLength(file.length).body(file);
    }

    @PostMapping("/AnalyzerSetup")
    public ModelAndView showSaveSetupFile(@Valid AnalyzerSetupForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            saveErrors(result);
            return findForward(FWD_FAIL_INSERT, form);
        }
        try {
            analyzerExperimentService.saveMapAsCSVFile(form.getFilename(), form.getWellValues());
        } catch (LIMSException e) {
            return findForward(FWD_FAIL_INSERT, form);
        }
        redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
        return findForward(FWD_SUCCESS_INSERT, form);
    }

    @Override
    protected String findLocalForward(String forward) {
        switch (forward) {
        case FWD_SUCCESS:
            return "quantStudioSetupDefinition";
        case FWD_SUCCESS_INSERT:
            return "redirect:/AnalyzerSetup.dp";
        case FWD_FAIL_INSERT:
            return "quantStudioSetupDefinition";
        }
        return null;
    }

    @Override
    protected String getPageTitleKey() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String getPageSubtitleKey() {
        // TODO Auto-generated method stub
        return null;
    }

}
