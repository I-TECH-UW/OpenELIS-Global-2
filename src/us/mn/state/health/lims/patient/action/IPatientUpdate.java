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
package us.mn.state.health.lims.patient.action;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.patient.action.PatientManagementUpdateAction.PatientUpdateStatus;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;

public interface IPatientUpdate {

	public abstract ActionMessages preparePatientData(ActionMapping mapping, HttpServletRequest request,
			PatientManagementInfo patientInfo) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;

	public abstract void setPatientUpdateStatus(PatientManagementInfo patientInfo);

	public abstract PatientUpdateStatus getPatientUpdateStatus();

	public abstract void persistPatientData(PatientManagementInfo patientInfo) throws LIMSRuntimeException;

	public abstract String getPatientId(BaseActionForm dynaForm);
}