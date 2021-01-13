package org.openelisglobal.referral.fhir.form;

import java.util.List;

import org.openelisglobal.common.form.BaseForm;

public class FhirReferralForm extends BaseForm {

    private static final long serialVersionUID = 3245627796529364543L;

    private List<FhirReferralItem> referrals;

    public List<FhirReferralItem> getReferrals() {
        return referrals;
    }

    public void setReferrals(List<FhirReferralItem> referrals) {
        this.referrals = referrals;
    }

}
