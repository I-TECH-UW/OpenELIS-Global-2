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
package us.mn.state.health.lims.common.provider.query.workerObjects;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.provider.query.PatientSearchResults;
import us.mn.state.health.lims.common.services.ObservationHistoryService;
import us.mn.state.health.lims.common.services.ObservationHistoryService.ObservationType;
import us.mn.state.health.lims.common.services.PatientService;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.patient.dao.PatientDAO;
import us.mn.state.health.lims.patient.daoimpl.PatientDAOImpl;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.sample.dao.SearchResultsDAO;
import us.mn.state.health.lims.sample.daoimpl.SearchResultsDAOImp;

public class PatientSearchLocalWorker extends PatientSearchWorker {
    private PatientDAO patientDAO = new PatientDAOImpl();

	@Override
	public String createSearchResultXML(String lastName, String firstName, String STNumber, String subjectNumber, String nationalID,
			String patientID, String guid, StringBuilder xml)  {

		String success = IActionConstants.VALID;

		if( GenericValidator.isBlankOrNull(lastName) &&
				GenericValidator.isBlankOrNull(firstName) &&
				GenericValidator.isBlankOrNull(STNumber) &&
				GenericValidator.isBlankOrNull(subjectNumber) &&
				GenericValidator.isBlankOrNull(nationalID) &&
				GenericValidator.isBlankOrNull(patientID) &&
				GenericValidator.isBlankOrNull(guid)){

			xml.append("No search terms were entered");
			return IActionConstants.INVALID;
		}

		SearchResultsDAO search = new SearchResultsDAOImp();
        //N.B. results do not have the referrinngPatientId information but it is not displayed so for now it will be left as null
		List<PatientSearchResults> results = search.getSearchResults(lastName, firstName, STNumber, subjectNumber, nationalID, nationalID, patientID, guid);
        if( !GenericValidator.isBlankOrNull(nationalID)) {
            List<PatientSearchResults> observationResults = getObservationsByReferringPatientId(nationalID);
            results.addAll(observationResults);
        }
		sortPatients(results);

		if (!results.isEmpty()) {
			for (PatientSearchResults singleResult : results) {
				singleResult.setDataSourceName(StringUtil.getMessageForKey("patient.local.source"));
				appendSearchResultRow(singleResult, xml);
			}
		}else{
			success = IActionConstants.INVALID;

			xml.append("No results were found for search.  Check spelling or remove some of the fields");
		}

		return success;
	}

    private List<PatientSearchResults> getObservationsByReferringPatientId( String referringId ){
        List<PatientSearchResults> resultList = new ArrayList<PatientSearchResults>(  );
        List<ObservationHistory> observationList = ObservationHistoryService.getObservationsByTypeAndValue(ObservationType.REFERRERS_PATIENT_ID, referringId);

        if (observationList != null) {
            for (ObservationHistory observation : observationList) {
                Patient patient = patientDAO.getData(observation.getPatientId());
                if (patient != null) {
                    resultList.add(getSearchResultsForPatient(patient, referringId));
                }

            }
        }

        return resultList;
    }

    private PatientSearchResults getSearchResultsForPatient(Patient patient, String referringId){
        PatientService service = new PatientService(patient);

        return new PatientSearchResults( BigDecimal.valueOf( Long.parseLong( patient.getId() ) ),
                service.getFirstName(),
                service.getLastName(),
                service.getGender(),
                service.getEnteredDOB(),
                service.getNationalId(),
                patient.getExternalId(),
                service.getSTNumber(),
                service.getSubjectNumber(),
                service.getGUID(),
                referringId);
    }

}
