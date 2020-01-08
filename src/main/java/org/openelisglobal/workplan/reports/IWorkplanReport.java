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
 *
 * Contributor(s): CIRG, University of Washington, Seattle WA.
 */

package org.openelisglobal.workplan.reports;

import java.util.HashMap;
import java.util.List;

import org.openelisglobal.workplan.form.WorkplanForm;

public interface IWorkplanReport {

    HashMap<String, Object> getParameters();

    String getFileName();

    List<?> prepareRows(WorkplanForm dynaForm);

    void setReportPath(String reportPath);
}
