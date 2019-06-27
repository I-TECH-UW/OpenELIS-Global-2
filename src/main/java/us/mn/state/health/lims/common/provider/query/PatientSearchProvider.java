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
package us.mn.state.health.lims.common.provider.query;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;

import spring.service.observationhistory.ObservationHistoryServiceImpl;
import spring.service.observationhistory.ObservationHistoryServiceImpl.ObservationType;
import spring.service.patient.PatientService;
import spring.service.sample.SampleService;
import spring.service.samplehuman.SampleHumanService;
import spring.util.SpringContext;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.provider.query.workerObjects.PatientSearchLocalAndClinicWorker;
import us.mn.state.health.lims.common.provider.query.workerObjects.PatientSearchLocalWorker;
import us.mn.state.health.lims.common.provider.query.workerObjects.PatientSearchWorker;
import us.mn.state.health.lims.common.servlet.validation.AjaxServlet;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.login.valueholder.UserSessionData;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.sample.valueholder.Sample;

public class PatientSearchProvider extends BaseQueryProvider {

	protected AjaxServlet ajaxServlet = null;

	SampleService sampleService = SpringContext.getBean(SampleService.class);
	SampleHumanService sampleHumanService = SpringContext.getBean(SampleHumanService.class);

	@Override
	public void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String STNumber = request.getParameter("STNumber");
		// N.B. This is a bad name, it is other than STnumber
		String subjectNumber = request.getParameter("subjectNumber");
		String nationalID = request.getParameter("nationalID");
		String labNumber = request.getParameter("labNumber");
		String guid = request.getParameter("guid");
		String suppressExternalSearch = request.getParameter("suppressExternalSearch");
		String patientID = null;

		String result = VALID;
		StringBuilder xml = new StringBuilder();
		// If we have a lab number then the patient is in the system and we just
		// have to get the patient and format the xml
		if (!GenericValidator.isBlankOrNull(labNumber)) {
			Patient patient = getPatientForLabNumber(labNumber);
			if (patient == null || GenericValidator.isBlankOrNull(patient.getId())) {
				result = IActionConstants.INVALID;
				xml.append("No results were found for search.  Check spelling or remove some of the fields");
			} else {
				PatientSearchResults searchResults = getSearchResultsForPatient(patient);
				PatientSearchWorker localWorker = new PatientSearchLocalWorker();
				localWorker.appendSearchResultRow(searchResults, xml);
			}
		} else {

			PatientSearchWorker worker = getAppropriateWorker(request, "true".equals(suppressExternalSearch));

			if (worker != null) {
				result = worker.createSearchResultXML(lastName, firstName, STNumber, subjectNumber, nationalID,
						patientID, guid, xml);
			} else {
				result = INVALID;
				xml.append("System is not configured correctly for searching for patients. Contact Administrator");
			}
		}
		ajaxServlet.sendData(xml.toString(), result, request, response);

	}

	private PatientSearchResults getSearchResultsForPatient(Patient patient) {
		PatientService patientPatientService = SpringContext.getBean(PatientService.class);
		patientPatientService.setPatient(patient);
		return new PatientSearchResults(BigDecimal.valueOf(Long.parseLong(patient.getId())),
				patientPatientService.getFirstName(), patientPatientService.getLastName(),
				patientPatientService.getGender(), patientPatientService.getEnteredDOB(),
				patientPatientService.getNationalId(), patient.getExternalId(), patientPatientService.getSTNumber(),
				patientPatientService.getSubjectNumber(), patientPatientService.getGUID(),
				ObservationHistoryServiceImpl.getInstance().getMostRecentValueForPatient(
						ObservationType.REFERRERS_PATIENT_ID, patientPatientService.getPatientId()));
	}

	private Patient getPatientForLabNumber(String labNumber) {

		Sample sample = sampleService.getSampleByAccessionNumber(labNumber);

		if (sample != null && !GenericValidator.isBlankOrNull(sample.getId())) {
			return sampleHumanService.getPatientForSample(sample);
		}

		return new Patient();
	}

	private PatientSearchWorker getAppropriateWorker(HttpServletRequest request, boolean suppressExternalSearch) {

		if (ConfigurationProperties.getInstance().isCaseInsensitivePropertyValueEqual(Property.UseExternalPatientInfo,
				"false") || suppressExternalSearch) {
			return new PatientSearchLocalWorker();
		} else {
			UserSessionData usd = (UserSessionData) request.getSession().getAttribute(USER_SESSION_DATA);

			return new PatientSearchLocalAndClinicWorker(String.valueOf(usd.getSystemUserId()));
		}
	}

	@Override
	public void setServlet(AjaxServlet as) {
		ajaxServlet = as;
	}

	@Override
	public AjaxServlet getServlet() {
		return ajaxServlet;
	}

}
