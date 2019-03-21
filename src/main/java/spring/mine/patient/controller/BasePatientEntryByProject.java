package spring.mine.patient.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.validation.Errors;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.dictionary.ObservationHistoryList;
import us.mn.state.health.lims.organization.util.OrganizationTypeList;
import us.mn.state.health.lims.patient.saving.Accessioner;
import us.mn.state.health.lims.patient.saving.RequestType;
import us.mn.state.health.lims.patient.util.PatientUtil;
import us.mn.state.health.lims.patient.valueholder.ObservationData;

public abstract class BasePatientEntryByProject extends BaseController {

	public BasePatientEntryByProject() {
		super();
	}

	protected void updateRequestType(HttpServletRequest httpRequest) {
		httpRequest.getSession().setAttribute("type", getRequestType(httpRequest).toString().toLowerCase());
	}

	protected RequestType getRequestType(HttpServletRequest httpRequest) {
		String requestTypeStr = httpRequest.getParameter("type");
		if (!GenericValidator.isBlankOrNull(requestTypeStr)) {
			try {
				return RequestType.valueOf(requestTypeStr.toUpperCase());
			} catch (IllegalArgumentException e) {
				LogEvent.logWarn("BasePatientEntryByProject", "getRequestType",
						"request type '" + requestTypeStr + "' invalid");
				e.printStackTrace();
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
	protected void setProjectFormName(BaseForm form, String projectFormName)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		ObservationData observations = ((ObservationData) (PropertyUtils.getProperty(form, "observations")));
		if (observations == null) {
			observations = new ObservationData();
			PropertyUtils.setProperty(form, "observations", observations);
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
	protected String handleSave(HttpServletRequest request, Accessioner accessioner) throws Exception {
		String forward = accessioner.save();
		if (null != forward) {
			Errors errors = accessioner.getMessages();
			if (errors.hasErrors()) {
				saveErrors(errors);
			}
			return forward;
		}
		return null;
	}

	/**
	 * Just a utility routine for doing the obvious logging and and appending a
	 * global message.
	 */
	protected void logAndAddMessage(HttpServletRequest request, String methodName, String messageKey) {
		LogEvent.logError(this.getClass().getSimpleName(), methodName, "Unable to enter patient into system");
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
	public static Map<String, Object> addAllPatientFormLists(BaseForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("GENDERS", PatientUtil.findGenders());

		PropertyUtils.setProperty(form, "formLists", resultMap);
		PropertyUtils.setProperty(form, "dictionaryLists", ObservationHistoryList.MAP);
		PropertyUtils.setProperty(form, "organizationTypeLists", OrganizationTypeList.MAP);

		return resultMap;
	}
}
