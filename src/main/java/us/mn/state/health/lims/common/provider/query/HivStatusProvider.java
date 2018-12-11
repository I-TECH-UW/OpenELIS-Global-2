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
package us.mn.state.health.lims.common.provider.query;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.servlet.validation.AjaxServlet;
import us.mn.state.health.lims.common.util.XMLUtil;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.daoimpl.ObservationHistoryDAOImpl;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.observationhistorytype.ObservationHistoryTypeMap;
import us.mn.state.health.lims.patient.valueholder.Patient;

public class HivStatusProvider extends BaseQueryProvider {

	private static final String NOT_FOUND = "Not found";
	/**
     * 
     */
    private static final String HIV_STATUS_OH_TYPE = "hivStatus";

    public HivStatusProvider() {
		super();
	}

	public HivStatusProvider(AjaxServlet ajaxServlet) {
		this.ajaxServlet = ajaxServlet;
	}

	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	    String patientId = (String) request.getParameter("patientId");
	    StringBuilder xml = new StringBuilder();
		String result = findHivStatus(patientId, xml);
		ajaxServlet.sendData(xml.toString(), result, request, response);
	}

	public String findHivStatus(String patientId, StringBuilder xml) throws LIMSRuntimeException {
		String retVal = VALID;

		try {
		    String hivStatus = findHIVStatusValue(patientId);
		    if( NOT_FOUND.equals(hivStatus)){
		    	return INVALID;
		    }
            XMLUtil.appendKeyValue(HIV_STATUS_OH_TYPE, hivStatus, xml);
		} catch (Exception e) {
		    LogEvent.logError("HivStatusProvider","findHivStatus()", e.getMessage());
			retVal = INVALID;
		}
		return retVal;
	}

    private String findHIVStatusValue(String patientId) {
        Patient patient = new Patient();
        patient.setId(patientId);
        String typeId = ObservationHistoryTypeMap.getInstance().getIDForType(HIV_STATUS_OH_TYPE);
        ObservationHistoryDAO ohDAO = new ObservationHistoryDAOImpl();
        List<ObservationHistory> list = ohDAO.getAll(patient, null, typeId);
        
        return list.isEmpty() ? NOT_FOUND : list.get(list.size() - 1).getValue(); 
    }

}
