package org.openelisglobal.referral.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.validation.Valid;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.DisplayListService.ListType;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.referral.form.ReferredOutTestsForm;
import org.openelisglobal.referral.service.ReferralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/rest/")
public class ReferredOutTestsRestController {

    @Autowired
    private ReferralService referralService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields("labNumber", "testIds", "testUnitIds", "endDate",
            "startDate", "dateType", "searchType", "selPatient");
    }
    
    private void setupPageForDisplay(ReferredOutTestsForm form) {
        if (form.getSearchType() != null) {
            form.setReferralDisplayItems(referralService.getReferralItems(form));
            form.setSearchFinished(true);
        }
        form.setTestSelectionList(DisplayListService.getInstance().getList(DisplayListService.ListType.ALL_TESTS));
        form.setTestUnitSelectionList(DisplayListService.getInstance().getList(DisplayListService.ListType.TEST_SECTION_BY_NAME));
    }

    @GetMapping(value = "ReferredOutTests", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReferredOutTestsForm showReferredOutTests(@Valid ReferredOutTestsForm form) {
        setupPageForDisplay(form);
        return form;
    }

    private void setupPageForDisplayRest(ReferredOutTestsForm form) {
        if (form.getSearchType() != null) {
            form.setReferralDisplayItems(referralService.getReferralItems(form));
            form.setSearchFinished(true);
        }
        form.setTestSelectionList(DisplayListService.getInstance().getList(DisplayListService.ListType.ALL_TESTS));
        form.setTestUnitSelectionList(DisplayListService.getInstance().getList(DisplayListService.ListType.TEST_SECTION_BY_NAME));
    }

    @PostMapping(value = "ReferredOutTests", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ReferredOutTestsForm submitReferredOutTests(@Valid @RequestBody ReferredOutTestsForm form) {
        setupPageForDisplayRest(form);
        return form;
    }

    // dummy data 
    // form types fixing or creating
    // validating response
    // print out things
    
    public class NonNumericTests {
        public String testId;
        public String testType;
        public List<IdValuePair> dictionaryValues;
    }

}
