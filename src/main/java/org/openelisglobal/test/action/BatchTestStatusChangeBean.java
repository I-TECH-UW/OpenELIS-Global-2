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

package org.openelisglobal.test.action;

public class BatchTestStatusChangeBean {
  private String labNo;
  private String oldStatus;
  private String newStatus;

  public String getLabNo() {
    return labNo;
  }

  public void setLabNo(String labNo) {
    this.labNo = labNo;
  }

  public String getNewStatus() {
    return newStatus;
  }

  public void setNewStatus(String newStatus) {
    this.newStatus = newStatus;
  }

  public String getOldStatus() {
    return oldStatus;
  }

  public void setOldStatus(String oldStatus) {
    this.oldStatus = oldStatus;
  }
}
