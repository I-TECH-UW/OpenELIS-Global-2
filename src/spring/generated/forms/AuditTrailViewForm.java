package spring.generated.forms;

import java.lang.String;
import java.sql.Timestamp;
import java.util.Collection;
import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.sample.bean.SampleOrderItem;

public class AuditTrailViewForm extends BaseForm {
  private Timestamp lastupdated;

  private String accessionNumberSearch = "";

  private Collection log;

  private String accessionNumber = "";

  private SampleOrderItem sampleOrderItems;

  private PatientManagementInfo patientProperties;

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }

  public String getAccessionNumberSearch() {
    return this.accessionNumberSearch;
  }

  public void setAccessionNumberSearch(String accessionNumberSearch) {
    this.accessionNumberSearch = accessionNumberSearch;
  }

  public Collection getLog() {
    return this.log;
  }

  public void setLog(Collection log) {
    this.log = log;
  }

  public String getAccessionNumber() {
    return this.accessionNumber;
  }

  public void setAccessionNumber(String accessionNumber) {
    this.accessionNumber = accessionNumber;
  }

  public SampleOrderItem getSampleOrderItems() {
    return this.sampleOrderItems;
  }

  public void setSampleOrderItems(SampleOrderItem sampleOrderItems) {
    this.sampleOrderItems = sampleOrderItems;
  }

  public PatientManagementInfo getPatientProperties() {
    return this.patientProperties;
  }

  public void setPatientProperties(PatientManagementInfo patientProperties) {
    this.patientProperties = patientProperties;
  }
}
