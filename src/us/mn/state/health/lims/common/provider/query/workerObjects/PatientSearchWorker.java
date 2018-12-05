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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.common.provider.query.PatientSearchResults;
import us.mn.state.health.lims.common.util.XMLUtil;
import us.mn.state.health.lims.patient.util.PatientUtil;
import us.mn.state.health.lims.patientidentity.dao.PatientIdentityDAO;
import us.mn.state.health.lims.patientidentity.daoimpl.PatientIdentityDAOImpl;
import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;
import us.mn.state.health.lims.patientidentitytype.util.PatientIdentityTypeMap;

abstract public class PatientSearchWorker {

	abstract public String createSearchResultXML(String lastName, String firstName, String STNumber, String subjectNumber, String nationalID,
			String patientID, String guid, StringBuilder xml);

	public void appendSearchResultRow(PatientSearchResults searchResults, StringBuilder xml) {

		xml.append("<result><patient>");
		createPatientElement(searchResults, xml );
		xml.append("</patient></result>");
	}

	/*
	 * This is protected until we integrate JMock into unit testing
	 */
	protected void createPatientElement(PatientSearchResults result, StringBuilder xml) {

		List<PatientIdentity> identityList = getIdentityListForPatient(result.getPatientID());
		PatientIdentityTypeMap identityMap = PatientIdentityTypeMap.getInstance();

		XMLUtil.appendKeyValue("first", result.getFirstName(), xml);
		XMLUtil.appendKeyValue("last", result.getLastName(), xml);
		XMLUtil.appendKeyValue("gender", result.getGender(), xml);
		XMLUtil.appendKeyValue("dob", PatientUtil.getDisplayDOBForPatient(result.getPatientID(), result.getDOB()), xml);
		XMLUtil.appendKeyValue("nationalID", result.getNationalId(), xml);
		XMLUtil.appendKeyValue("ST", result.getSTNumber(), xml);
		XMLUtil.appendKeyValue("subjectNumber", result.getSubjectNumber(), xml);
		String mothersName = GenericValidator.isBlankOrNull(result.getMothersName()) ? identityMap.getIdentityValue(identityList, "MOTHER") : result.getMothersName();
		XMLUtil.appendKeyValue("mother", mothersName, xml);
		XMLUtil.appendKeyValue("dataSourceName", result.getDataSourceName(), xml);
		XMLUtil.appendKeyValue("id", result.getPatientID(), xml);
        XMLUtil.appendKeyValue( "referralPatientId", result.getReferringSitePatientId(), xml );
	}

	private List<PatientIdentity> getIdentityListForPatient(String patientId) {
		PatientIdentityDAO identityDAO = new PatientIdentityDAOImpl();
		return identityDAO.getPatientIdentitiesForPatient(patientId);
	}

	protected void sortPatients( List<PatientSearchResults> foundList){
		Collections.sort( foundList, new FoundListComparator());
	}

	class FoundListComparator implements Comparator<PatientSearchResults> {

		public int compare(PatientSearchResults o1, PatientSearchResults o2) {
			if( o1.getLastName() == null ){
				return o2.getLastName() == null ? 0 : 1;
			}else if(o2.getLastName() == null ){
				return -1;
			}

			int lastNameResults = o1.getLastName().compareToIgnoreCase(o2.getLastName());

			if(lastNameResults == 0 ){
				if( GenericValidator.isBlankOrNull(o1.getFirstName()) && GenericValidator.isBlankOrNull(o2.getFirstName())){
					return 0;
				}
				

				String oneName = (o1.getFirstName() == null)?" ":o1.getFirstName();
				String twoName = (o2.getFirstName() == null)?" ":o2.getFirstName();
                return oneName.compareToIgnoreCase(twoName);
			}else{
				return lastNameResults;
			}
		}

	}

}
