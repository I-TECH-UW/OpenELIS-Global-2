package org.openelisglobal.audittrail.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.audittrail.action.workers.AuditTrailItem;
import org.openelisglobal.audittrail.action.workers.AuditTrailViewWorker;
import org.openelisglobal.audittrail.form.AuditTrailViewForm;
import org.openelisglobal.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuditTrailReportController extends BaseController {

    @RequestMapping(value = "/AuditTrailReport", method = RequestMethod.GET)
    public ModelAndView showAuditTrailReport(HttpServletRequest request,
            @ModelAttribute("form") @Valid AuditTrailViewForm form)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        form.setFormMethod(RequestMethod.GET);

        String accessionNumber = form.getString("accessionNumberSearch");
        if (!GenericValidator.isBlankOrNull(accessionNumber)) {
            AuditTrailViewWorker worker = new AuditTrailViewWorker(accessionNumber);
            List<AuditTrailItem> items = worker.getAuditTrail();
            PropertyUtils.setProperty(form, "log", items);
            PropertyUtils.setProperty(form, "accessionNumber", accessionNumber);
            PropertyUtils.setProperty(form, "sampleOrderItems", worker.getSampleOrderSnapshot());
            PropertyUtils.setProperty(form, "patientProperties", worker.getPatientSnapshot());
        }

        return findForward(FWD_SUCCESS, form);
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "auditTrailViewDefinition";
        } else {
            return "PageNotFound";
        }
    }

    @Override
    protected String getPageTitleKey() {
        return "reports.auditTrail";
    }

    @Override
    protected String getPageSubtitleKey() {
        return "reports.auditTrail";
    }
}
