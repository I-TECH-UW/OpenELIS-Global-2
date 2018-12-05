package spring.generated.forms;

import java.lang.String;
import java.sql.Timestamp;
import java.util.Collection;
import spring.mine.common.form.BaseForm;

public class TypeOfSampleTestForm extends BaseForm {
  private String id = "";

  private String sample = "";

  private String test = "";

  private Collection samples;

  private Collection tests;

  private Timestamp lastupdated;

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSample() {
    return this.sample;
  }

  public void setSample(String sample) {
    this.sample = sample;
  }

  public String getTest() {
    return this.test;
  }

  public void setTest(String test) {
    this.test = test;
  }

  public Collection getSamples() {
    return this.samples;
  }

  public void setSamples(Collection samples) {
    this.samples = samples;
  }

  public Collection getTests() {
    return this.tests;
  }

  public void setTests(Collection tests) {
    this.tests = tests;
  }

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }
}
