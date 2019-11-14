package org.openelisglobal.testconfiguration.controller;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.testconfiguration.form.ResultSelectListRenameForm;
import org.openelisglobal.testconfiguration.service.ResultSelectListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SelectListRenameEntryController extends BaseController {

    @Autowired
    private ResultSelectListService resultSelectListService;

    @RequestMapping(value = "/SelectListRenameEntry", method = RequestMethod.GET)
    public ModelAndView showUomRenameEntry(HttpServletRequest request) {
        ResultSelectListRenameForm form = new ResultSelectListRenameForm();

        form.setResultSelectOptionList(resultSelectListService.getAllSelectListOptions());
        return findForward(FWD_SUCCESS, form);
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "resultSelectListRenameDefinition";
        } else if (FWD_SUCCESS_INSERT.equals(forward)) {
            return "redirect:/SelectListRenameEntry.do";
        } else if (FWD_FAIL_INSERT.equals(forward)) {
            return "resultSelectListRenameDefinition";
        } else {
            return "PageNotFound";
        }
    }

    @RequestMapping(value = "/SelectListRenameEntry", method = RequestMethod.POST)
    public ModelAndView updateUomRenameEntry(HttpServletRequest request,
                                             @ModelAttribute("form") ResultSelectListRenameForm form, RedirectAttributes redirectAttributes) {

        boolean renamed = resultSelectListService.renameOption(form, getSysUserId(request));

        if (renamed) {
            redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
            form.setResultSelectOptionList(resultSelectListService.getAllSelectListOptions());
            return findForward(FWD_SUCCESS_INSERT, form);
        } else {
            return findForward(FWD_FAIL_INSERT, form);
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
