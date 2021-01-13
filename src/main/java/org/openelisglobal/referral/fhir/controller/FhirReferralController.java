package org.openelisglobal.referral.fhir.controller;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.referral.fhir.form.FhirReferralForm;
import org.openelisglobal.referral.fhir.form.FhirReferralItem;
import org.openelisglobal.referral.fhir.service.FhirReferralService;
import org.openelisglobal.referral.service.ReferralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FhirReferralController extends BaseController {

    @Autowired
    private FhirReferralService fhirReferralService;
    @Autowired
    private ReferralService referralService;

    private static final String[] ALLOWED_FIELDS = new String[] { "organization.id", "sampleIdsToAnalysisIds" };

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @GetMapping("/FhirReferral")
    public ModelAndView showFhirReferralPage() {
        FhirReferralForm form = new FhirReferralForm();
        return findForward(FWD_SUCCESS, form);
    }

    @PostMapping("/FhirReferral")
    public ModelAndView referAnalysises(@ModelAttribute("form") FhirReferralForm form) {
        for (FhirReferralItem referral : form.getReferrals()) {
            fhirReferralService.referAnalysisesToOrganization(referral.getOrganizationId(), referral.getSampleId(),
                    referral.getAnalysisIds());
        }

        return findForward(FWD_SUCCESS, form);
    }

    @Override
    protected String findLocalForward(String forward) {
        switch (forward) {
        case FWD_SUCCESS:
            return "fhirReferralDefinition";
        default:
            return "";
        }
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
