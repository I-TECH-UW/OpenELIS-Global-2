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
* Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.reports.action.implementation;

import java.util.HashMap;
import java.util.List;

import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.action.IActionConstants;

public interface IReportCreator {
    String INCOMPLETE_PARAMS = "Incompleate parameters";
	String INVALID_PARAMS = "Invalid parameters";
	String SUCCESS = IActionConstants.FWD_SUCCESS;

	void initializeReport(BaseActionForm dynaForm);
	String getResponseHeaderName();
	String getContentType();
	String getResponseHeaderContent();
	HashMap<String, ?> getReportParameters() throws IllegalStateException;
	byte[] runReport( ) throws Exception;
	void setReportPath( String path);
	void setRequestedReport(String report);
	List<String> getReportedOrders();

}
