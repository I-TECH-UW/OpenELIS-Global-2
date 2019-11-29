package org.openelisglobal.patient.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.validator.BaseErrors;
import org.openelisglobal.dictionary.ObservationHistoryList;
import org.openelisglobal.organization.util.OrganizationTypeList;
import org.openelisglobal.patient.form.PatientEntryByProjectForm;
import org.openelisglobal.patient.saving.IAccessioner;
import org.openelisglobal.patient.saving.RequestType;
import org.openelisglobal.patient.util.PatientUtil;
import org.openelisglobal.patient.valueholder.ObservationData;
import org.springframework.validation.Errors;

public abstract class BasePatientEntryByProject extends BaseController {

    public BasePatientEntryByProject() {
        super();
    }

    protected void updateRequestType(HttpServletRequest httpRequest) {
        httpRequest.getSession().setAttribute("type", getRequestType(httpRequest).toString().toLowerCase());
    }

    protected RequestType getRequestType(HttpServletRequest httpRequest) {
        String requestTypeStr = httpRequest.getParameter("type");
        if (!org.apache.commons.validator.GenericValidator.isBlankOrNull(requestTypeStr)) {
            try {
                return RequestType.valueOf(requestTypeStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                LogEvent.logWarn("BasePatientEntryByProject", "getRequestType",
                        "request type '" + requestTypeStr + "' invalid");
                LogEvent.logDebug(e);
                return RequestType.UNKNOWN;
            }
        }
        return RequestType.UNKNOWN;
    }

    /**
     * Put the projectFormName in the right place, so that ends up driving the next
     * patientEntry form to select the right form. The projectFormName is retained
     * even through form edit/entry(add) reselection, so that all forms know the
     * current projectForm, so that the user is presented with a good guess on what
     * they might want to keep doing.
     *
     * @param projectFormName
     * @throws Exception all from property utils if we've coded things wrong in the
     *                   form def or in this class.
     */
    protected void setProjectFormName(PatientEntryByProjectForm form, String projectFormName)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        ObservationData observations = form.getObservations();
        if (observations == null) {
            observations = new ObservationData();
            form.setObservations(observations);
        }
        observations.setProjectFormName(projectFormName);
    }

    /**
     * This method captures how we deal with the curious accession objects I
     * (PaHill) created. If someone comes up with a better API (Maybe inject a
     * messages list to be filled in?) then we can change this bit of plumbing.
     *
     * @param request     original request
     * @param accessioner the object to use to attempt to save.
     * @return a forward string or null; null => this attempt to save failed
     * @throws Exception if things go really bad. Normally, the errors are caught
     *                   internally an appropriate message is added and a forward
     *                   fail is returned.
     */
    protected String handleSave(HttpServletRequest request, IAccessioner accessioner) throws Exception {
        String forward;
        try {
            forward = accessioner.save();
        } catch (Exception e) {
            Errors errors = accessioner.getMessages();
            if (errors.hasErrors()) {
                saveErrors(errors);
            }
            return FWD_FAIL_INSERT;
        }
        return forward;
    }

    /**
     * Just a utility routine for doing the obvious logging and and appending a
     * global message.
     */
    protected void logAndAddMessage(HttpServletRequest request, String methodName, String messageKey) {
        LogEvent.logError(this.getClass().getName(), methodName, "Unable to enter patient into system");
        Errors errors = new BaseErrors();
        errors.reject(messageKey, messageKey);
        saveErrors(errors);
    }

    /**
     * various maps full of a various lists used by the patient entry form
     * (typically for drop downs) and other forms who want to do patient entry.
     *
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     *
     */
    public static Map<String, Object> addAllPatientFormLists(PatientEntryByProjectForm form)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("GENDERS", PatientUtil.findGenders());

        form.setFormLists(resultMap);
        form.setDictionaryLists(ObservationHistoryList.MAP);
        form.setOrganizationTypeLists(OrganizationTypeList.MAP);

        return resultMap;
    }
}
