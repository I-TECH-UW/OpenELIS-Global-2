package spring.mine.referral.form;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.referral.action.beanitems.ReferralItem;

public class ReferredOutTestsForm extends BaseForm {
	private Timestamp lastupdated;

	// TODO
	@Valid
	private List<ReferralItem> referralItems;

	// for display
	private List<IdValuePair> referralOrganizations;

	// for display
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
