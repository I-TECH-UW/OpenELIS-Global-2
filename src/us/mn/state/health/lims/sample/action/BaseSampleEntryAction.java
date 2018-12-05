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
package us.mn.state.health.lims.sample.action;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessages;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.dictionary.ObservationHistoryList;
import us.mn.state.health.lims.gender.dao.GenderDAO;
import us.mn.state.health.lims.gender.daoimpl.GenderDAOImpl;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.organization.util.OrganizationTypeList;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.patient.saving.Accessioner;
import us.mn.state.health.lims.patient.saving.RequestType;
import us.mn.state.health.lims.patient.util.PatientUtil;
import us.mn.state.health.lims.patient.valueholder.ObservationData;
import us.mn.state.health.lims.project.dao.ProjectDAO;
import us.mn.state.health.lims.project.daoimpl.ProjectDAOImpl;


/**
 * The SampleEntryAction class represents the initial Action for the SampleEntry
 * form of the application
 *
 */
public abstract class BaseSampleEntryAction extends BaseAction {

    protected RequestType requestType = RequestType.UNKNOWN;

    public static final String FWD_EID_ENTRY = "eid_entry";
    public static final String FWD_VL_ENTRY = "vl_entry";

	protected String getPageTitleKey() {
		return StringUtil.getContextualKeyForKey("sample.entry.title");
	}

	protected String getPageSubtitleKey() {
		return StringUtil.getContextualKeyForKey("sample.entry.title");
	}

	private static int referenceLabParentId = 0;


	

	protected void addGenderList(BaseActionForm dynaForm) throws LIMSRuntimeException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		GenderDAO genderDAO = new GenderDAOImpl();
		@SuppressWarnings("rawtypes")
		List genders = genderDAO.getAllGenders();
		PropertyUtils.setProperty(dynaForm, "genders", genders);
	}
	

	/**
	 * various maps full of a various lists used by the entry form (typically for drop downs) and other forms who want to do patient entry.
	 * @throws Exception all from setProperty problems caused by developer mistakes.
	 */
	protected void addProjectList(BaseActionForm dynaForm) throws LIMSRuntimeException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		ProjectDAO projectDAO = new ProjectDAOImpl();
		@SuppressWarnings("rawtypes")
		List projects = projectDAO.getAllProjects();
		PropertyUtils.setProperty(dynaForm, "projects", projects);
	}

	public void addAllPatientFormLists(BaseActionForm dynaForm) throws Exception {
	    Map<String, Object> resultMap = new HashMap<String, Object>();
	    resultMap.put("GENDERS", PatientUtil.findGenders());

	    PropertyUtils.setProperty(dynaForm, "formLists", resultMap);
	    PropertyUtils.setProperty(dynaForm, "dictionaryLists", ObservationHistoryList.MAP);
	    PropertyUtils.setProperty(dynaForm, "organizationTypeLists", OrganizationTypeList.MAP);

	    return;
	}

	protected static int getReferenceLabParentId() {
		if( referenceLabParentId == 0){
			String parentOrgName = ConfigurationProperties.getInstance().getPropertyValue(Property.ReferingLabParentOrg);

			if( parentOrgName != null){ //this dosn't seem to actually do anything. is parentOrg referenced anyplace?
				OrganizationDAO orgDAO = new OrganizationDAOImpl();
				Organization parentOrg = new Organization();
				parentOrg.setName(parentOrgName);
				parentOrg = orgDAO.getOrganizationByName(parentOrg, false);
			}
		}

		return referenceLabParentId;
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
     * so that the use is presented with a good guess on what they might want to keep doing.
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
        LogEvent.logError(this.getClass().getSimpleName(), methodName, "Unable to enter sample into system");
        ActionError error = new ActionError(messageKey, null, null);
        ActionMessages errors = new ActionMessages();
        errors.add(ActionMessages.GLOBAL_MESSAGE, error);
        addErrors(request, errors);
    }
}
