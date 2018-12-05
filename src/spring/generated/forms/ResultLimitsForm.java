package spring.generated.forms;

import java.sql.Timestamp;
import java.util.Collection;
import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.resultlimits.form.ResultLimitsLink;

public class ResultLimitsForm extends BaseForm {
  private ResultLimitsLink limit;

  private Collection tests;

  private Collection resultTypes;

  private Collection genders;

  private Collection units;

  private Timestamp lastupdated;

  public ResultLimitsLink getLimit() {
    return this.limit;
  }

  public void setLimit(ResultLimitsLink limit) {
    this.limit = limit;
  }

  public Collection getTests() {
    return this.tests;
  }

  public void setTests(Collection tests) {
    this.tests = tests;
  }

  public Collection getResultTypes() {
    return this.resultTypes;
  }

  public void setResultTypes(Collection resultTypes) {
    this.resultTypes = resultTypes;
  }

  public Collection getGenders() {
    return this.genders;
  }

  public void setGenders(Collection genders) {
    this.genders = genders;
  }

  public Collection getUnits() {
    return this.units;
  }

  public void setUnits(Collection units) {
    this.units = units;
  }

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }
}
