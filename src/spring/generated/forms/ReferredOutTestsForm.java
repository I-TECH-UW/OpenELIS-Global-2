package spring.generated.forms;

import java.sql.Timestamp;
import java.util.Collection;
import spring.mine.common.form.BaseForm;

public class ReferredOutTestsForm extends BaseForm {
  private Timestamp lastupdated;

  private Collection referralItems;

  private Collection referralOrganizations;

  private Collection referralReasons;

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }

  public Collection getReferralItems() {
    return this.referralItems;
  }

  public void setReferralItems(Collection referralItems) {
    this.referralItems = referralItems;
  }

  public Collection getReferralOrganizations() {
    return this.referralOrganizations;
  }

  public void setReferralOrganizations(Collection referralOrganizations) {
    this.referralOrganizations = referralOrganizations;
  }

  public Collection getReferralReasons() {
    return this.referralReasons;
  }

  public void setReferralReasons(Collection referralReasons) {
    this.referralReasons = referralReasons;
  }
}
