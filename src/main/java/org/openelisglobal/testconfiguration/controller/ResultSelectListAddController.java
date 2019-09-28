package org.openelisglobal.testconfiguration.controller;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.testconfiguration.form.PanelCreateForm;
import org.openelisglobal.testconfiguration.form.ResultSelectListForm;
import org.openelisglobal.testconfiguration.service.ResultSelectListAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ResultSelectListAddController extends BaseController {

    @Autowired
    private TestService testService;
    @Autowired
    private ResultSelectListAddService resultSelectListAddService;

    @RequestMapping(value = "/ResultSelectListAdd", method = RequestMethod.GET)
    public ModelAndView showCreateResultSelectList(HttpServletRequest request) {
        ResultSelectListForm form = new ResultSelectListForm();
        form.setPage("1");
        addFlashMsgsToRequest(request);
        return findForward(FWD_SUCCESS, form);
    }

    @RequestMapping(value = "/ResultSelectListAdd", method = RequestMethod.POST)
    public ModelAndView showResultSelectListAddToTest(HttpServletRequest request,
                                                      @ModelAttribute("form") ResultSelectListForm form) {
        form.setPage("2");
        if ("".equalsIgnoreCase(form.getNameEnglish())) {
            form.setNameEnglish(form.getNameFrench());
        } else if ("".equalsIgnoreCase(form.getNameFrench())) {
            form.setNameFrench(form.getNameEnglish());
        }
        form.setTests(testService.getAllTestsByDictionaryResult());
        form.setTestDictionary(resultSelectListAddService.getTestSelectDictionary());
        addFlashMsgsToRequest(request);
        return findForward(FWD_SUCCESS, form);
    }

    @RequestMapping(value = "/SaveResultSelectList", method = RequestMethod.POST)
    public ModelAndView SaveResultSelectList(HttpServletRequest request,
                                                      @ModelAttribute("form") ResultSelectListForm form,
                                             RedirectAttributes redirectAttributes) {

        addFlashMsgsToRequest(request);
        String currentUserId = getSysUserId(request);
        boolean saved = resultSelectListAddService.addResultSelectList(form, currentUserId);
        if (saved) {
            redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
            return findForward(FWD_SUCCESS, form);
        }
        return findForward(FWD_FAIL_INSERT, form);

    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "resultSelectListDefinition";
        } else if (FWD_SUCCESS_INSERT.equals(forward)) {
            return "redirect:/ResultSelectListAdd.do";
        } else if (FWD_FAIL_INSERT.equals(forward)) {
            return "resultSelectListDefinition";
        } else {
            return "PageNotFound";
        }
    }

    @Override
    protected String getPageTitleKey() {
        return null;
    }

    @Override
    protected String getPageSubtitleKey() {
        return null;
    }
}
