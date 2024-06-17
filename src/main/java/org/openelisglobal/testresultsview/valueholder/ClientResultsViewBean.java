package org.openelisglobal.testresultsview.valueholder;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.openelisglobal.common.valueholder.BaseObject;
import org.openelisglobal.result.valueholder.Result;
import org.openelisglobal.security.PasswordUtil;
import org.openelisglobal.security.converter.EncryptionConverter;

// info to needed for a client viewing their results
@Entity
@Table(name = "client_results_view")
public class ClientResultsViewBean extends BaseObject<Integer> {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_results_view_generator")
  @SequenceGenerator(
      name = "client_results_view_generator",
      sequenceName = "client_results_view_seq",
      allocationSize = 1)
  private Integer id;

  @Column
  @Convert(converter = EncryptionConverter.class)
  private String password;

  @OneToOne
  @JoinColumn(name = "result_id")
  private Result result;

  // this exists for hibernate
  ClientResultsViewBean() {}

  public ClientResultsViewBean(Result result) {
    this.result = result;
    password = PasswordUtil.generatePassword();
  }

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }
}
