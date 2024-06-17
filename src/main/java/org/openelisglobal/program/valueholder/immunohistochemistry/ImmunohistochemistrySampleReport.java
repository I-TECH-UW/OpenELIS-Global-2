package org.openelisglobal.program.valueholder.immunohistochemistry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Type;
import org.openelisglobal.common.valueholder.BaseObject;

@Entity
@Table(name = "immunohistochemistry_report")
public class ImmunohistochemistrySampleReport extends BaseObject<Integer> {

  @Id
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "immunohistochemistry_report_generator")
  @SequenceGenerator(
      name = "immunohistochemistry_report_generator",
      sequenceName = "immunohistochemistry_report_seq",
      allocationSize = 1)
  private Integer id;

  @Type(type = "org.hibernate.type.BinaryType")
  private byte[] image;

  @Column(name = "file_type")
  private String fileType;

  @Column(name = "report_type")
  @Enumerated(EnumType.STRING)
  @NotNull
  private ImmunoHistologyReportType reportType;

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public ImmunoHistologyReportType getReportType() {
    return reportType;
  }

  public void setReportType(ImmunoHistologyReportType reportType) {
    this.reportType = reportType;
  }

  public enum ImmunoHistologyReportType {
    DUAL_IN_SITU_HYBRIDISATION("Dual In Situ Hybridisation (ISH) Report"),
    BREAST_CANCER_HORMONE_RECEPTOR("Breast Cancer Hormone Receptor Status Report"),
    IMMUNOHISTOCHEMISTRY("Immunohistochemistry Report");

    private String display;

    ImmunoHistologyReportType(String display) {
      this.display = display;
    }

    public String getDisplay() {
      return display;
    }
  }
}
