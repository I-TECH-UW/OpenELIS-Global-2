package org.openelisglobal.referral.form;

import java.util.List;

import javax.validation.Valid;

import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.referral.action.beanitems.ReferralItem;

public class ReferredOutTestsForm extends BaseForm {

    public interface ReferredOut {
    }

    @Valid
    private List<ReferralItem> referralItems;

    // for display
    private List<IdValuePair> referralOrganizations;

    // for display
    private List<IdValuePair> referralReasons;

    public ReferredOutTestsForm() {
        setFormName("referredOutTestsForm");
    }

    public List<ReferralItem> getReferralItems() {
        return referralItems;
    }

    public void setReferralItems(List<ReferralItem> referralItems) {
        this.referralItems = referralItems;
    }

    public List<IdValuePair> getReferralOrganizations() {
        return referralOrganizations;
    }

    public void setReferralOrganizations(List<IdValuePair> referralOrganizations) {
        this.referralOrganizations = referralOrganizations;
    }

    public List<IdValuePair> getReferralReasons() {
        return referralReasons;
    }

    public void setReferralReasons(List<IdValuePair> referralReasons) {
        this.referralReasons = referralReasons;
    }
}
