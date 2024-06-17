package org.openelisglobal.dev.form;

import org.openelisglobal.common.form.BaseForm;

public class WebTestInfoForm extends BaseForm {
  // used to communicate to client from server, not other way
  // no validation needed
  private String xmlWad = "";

  public WebTestInfoForm() {
    setFormName("webTestInfoForm");
  }

  public String getXmlWad() {
    return xmlWad;
  }

  public void setXmlWad(String xmlWad) {
    this.xmlWad = xmlWad;
  }
}
