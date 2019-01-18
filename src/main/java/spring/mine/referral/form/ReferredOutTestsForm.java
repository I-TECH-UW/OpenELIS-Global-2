package spring.mine.referral.form;

import java.sql.Timestamp;
import java.util.List;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.referral.action.beanitems.ReferralItem;

public class ReferredOutTestsForm extends BaseForm {
	private Timestamp lastupdated;

	private List<ReferralItem> referralItems;

	private List<IdValuePair> referralOrganizations;

	private List<IdValuePair> referralReasons;

	public ReferredOutTestsForm() {
		setFormName("referredOutTestsForm");
	}

	public Timestamp getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
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
