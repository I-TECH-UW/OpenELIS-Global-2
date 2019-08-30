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
package org.openelisglobal.program.valueholder;

import org.openelisglobal.common.valueholder.BaseObject;

public class Program extends BaseObject<String> {

    private String code;

    private String id;

    private String programName;

    public Program() {
        super();
    }

    public String getCode() {
        return this.code;
    }

    public String getId() {
        return this.id;
    }

    public String getProgramName() {
        return this.programName;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

}
