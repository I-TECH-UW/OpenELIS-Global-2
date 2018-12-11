/**
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
* Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
*/
package us.mn.state.health.lims.audittrail.valueholder;

import java.sql.Timestamp;

import us.mn.state.health.lims.common.valueholder.BaseObject;

/**
 *  @author         Hung Nguyen
 *  @date created   09/12/2006
 */
public class History extends BaseObject {

     private String id;
     private String referenceId;
     private String referenceTable;
     private Timestamp timestamp;
     private String activity;
     private java.sql.Blob changes;
     private String sys_user_id;

     public String getSysUserId() {
    	 return sys_user_id;
     }
     public void setSysUserId(String sys_user_id) {
    	 this.sys_user_id = sys_user_id;
     }
    
     public String getId() {
    	 return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getReferenceId() {
        return this.referenceId;
    }
    
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getReferenceTable() {
        return this.referenceTable;
    }
    
    public void setReferenceTable(String referenceTable) {
        this.referenceTable = referenceTable;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }
    
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getActivity() {
        return this.activity;
    }
    
    public void setActivity(String activity) {
        this.activity = activity;
    }

    public java.sql.Blob getChanges() {
        return this.changes;
    }
    
    public void setChanges(java.sql.Blob changes) {
        this.changes = changes;
    }
   
}