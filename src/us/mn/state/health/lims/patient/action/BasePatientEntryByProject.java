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
 * Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
 *
 * Contributor(s): CIRG, University of Washington, Seattle WA.
 */
package us.mn.state.health.lims.patient.action;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessages;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.dictionary.ObservationHistoryList;
import us.mn.state.health.lims.organization.util.OrganizationTypeList;
import us.mn.state.health.lims.patient.saving.Accessioner;
import us.mn.state.health.lims.patient.saving.RequestType;
import us.mn.state.health.lims.patient.util.PatientUtil;
import us.mn.state.health.lims.patient.valueholder.ObservationData;

/**
 * Base class to share between setup actions and update actions for both entry and edit of patient.
 * @author Paul A. Hill (pahill@uw.edu)
 * @since Jul 26, 2010
 */
public abstract class BasePatientEntryByProject extends BaseAction {

    protected RequestType requestType = RequestType.UNKNOWN; 

    public BasePatientEntryByProject() {
        super();
    }

    /**
     * @param requestTypeStr
     */
    protected void setRequestType(String requestTypeStr) {
    	if (!GenericValidator.isBlankOrNull(requestTypeStr)) {   
    		requestType = RequestType.valueOf(requestTypeStr.toUpperCase());
    	}
    }
    
    /**
     * If the URL parameter says we want to do a particular type of request, figure out which type and pass that on to any other controller page
     * in the flow via an attribute of the session
     * TODO PAHill is putting it in the session just clutter?  Should this be in the request instead?  
     */
    protected void updateRequestType(HttpServletRequest httpRequest) {
        setRequestType((String)httpRequest.getParameter("type"));
        if ( requestType != null ) {
            httpRequest.getSession().setAttribute("type", requestType.toString().toLowerCase());
        }
    }
    
    /**
     * Put the projectFormName in the right place, so that ends up driving the next patientEntry form to select the right form. 
     * The projectFormName is retained even through form edit/entry(add) reselection, so that all forms know the current projectForm,
     * so that the user is presented with a good guess on what they might want to keep doing. 
     * @param projectFormName
     * @throws Exception all from property utils if we've coded things wrong in the form def or in this class.
     */
    protected void setProjectFormName(ActionForm form, String projectFormName) throws IllegalAccessException,
                    InvocationTargetException, NoSuchMethodException {
                        ((ObservationData)(PropertyUtils.getProperty(form, "observations"))).setProjectFormName(projectFormName);
                    }

    /**
     * This method captures how we deal with the curious accession objects I (PaHill) created.  If someone comes up with a 
     * better API (Maybe inject a messages list to be filled in?) then we can change this bit of plumbing. 
     * @param request original request
     * @param accessioner the object to use to attempt to save.
     * @return a forward string or null; null => this attempt to save failed
     * @throws Exception if things go really bad.  Normally, the errors are caught internally an appropriate message is added and a forward fail is returned. 
     */
    protected String handleSave(HttpServletRequest request, Accessioner accessioner) throws Exception {
        String forward = accessioner.save();
        if (null != forward) {
            ActionMessages errors = accessioner.getMessages();
            if (errors.size() != 0) {
                saveErrors(request, errors);
                request.setAttribute(Globals.ERROR_KEY, errors);
            }
            return forward;
        }
        return null;
    }

    /**
     * Just a utility routine for doing the obvious logging and and appending a global message. 
     */
    protected void logAndAddMessage(HttpServletRequest request, String methodName, String messageKey) {
        LogEvent.logError(this.getClass().getSimpleName(), methodName, "Unable to enter patient into system");        
        ActionError error = new ActionError(messageKey, null, null);
        ActionMessages errors = new ActionMessages();
        errors.add(ActionMessages.GLOBAL_MESSAGE, error);
        addErrors(request, errors);
    }

    /**
     * various maps full of a various lists used by the patient entry form (typically for drop downs) and other forms who want to do patient entry.
     * @throws Exception all from setProperty problems caused by developer mistakes.
     */
    public static Map<String, Object> addAllPatientFormLists(BaseActionForm dynaForm) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("GENDERS", PatientUtil.findGenders());

        PropertyUtils.setProperty(dynaForm, "formLists", resultMap);
        PropertyUtils.setProperty(dynaForm, "dictionaryLists", ObservationHistoryList.MAP);
        PropertyUtils.setProperty(dynaForm, "organizationTypeLists", OrganizationTypeList.MAP);

        return resultMap;
    }
}