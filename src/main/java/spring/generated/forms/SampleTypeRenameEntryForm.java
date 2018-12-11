package spring.generated.forms;

import java.lang.String;
import java.util.List;
import spring.mine.common.form.BaseForm;

public class SampleTypeRenameEntryForm extends BaseForm {
  private List sampleTypeList;

  private String nameEnglish = "";

  private String nameFrench = "";

  private String sampleTypeId = "";

  public List getSampleTypeList() {
    return this.sampleTypeList;
  }

  public void setSampleTypeList(List sampleTypeList) {
    this.sampleTypeList = sampleTypeList;
  }

  public String getNameEnglish() {
    return this.nameEnglish;
  }

  public void setNameEnglish(String nameEnglish) {
    this.nameEnglish = nameEnglish;
  }

  public String getNameFrench() {
    return this.nameFrench;
  }

  public void setNameFrench(String nameFrench) {
    this.nameFrench = nameFrench;
  }

  public String getSampleTypeId() {
    return this.sampleTypeId;
  }

  public void setSampleTypeId(String sampleTypeId) {
    this.sampleTypeId = sampleTypeId;
  }
}
