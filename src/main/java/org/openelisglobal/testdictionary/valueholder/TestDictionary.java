/*
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 *
 * The Original Code is OpenELIS code.
 *
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 */

package org.openelisglobal.testdictionary.valueholder;

import org.openelisglobal.common.valueholder.EnumValueItemImpl;
import org.openelisglobal.common.valueholder.ValueHolder;
import org.openelisglobal.dictionarycategory.valueholder.DictionaryCategory;

public class TestDictionary extends EnumValueItemImpl {

  private static final long serialVersionUID = 1L;

  private String id;
  private ValueHolder dictionaryCategory = new ValueHolder();
  private String testId;
  private String context;
  private String qualifiableDictionaryId;

  public String getQualifiableDictionaryId() {
    return qualifiableDictionaryId;
  }

  public void setQualifiableDictionaryId(String qualifiableDictionaryId) {
    this.qualifiableDictionaryId = qualifiableDictionaryId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @SuppressWarnings("JpaAttributeTypeInspection")
  public DictionaryCategory getDictionaryCategory() {
    return (DictionaryCategory) dictionaryCategory.getValue();
  }

  public void setDictionaryCategory(DictionaryCategory dictionaryCategory) {
    this.dictionaryCategory.setValue(dictionaryCategory);
  }

  public String getTestId() {
    return testId;
  }

  public void setTestId(String testId) {
    this.testId = testId;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }
}
