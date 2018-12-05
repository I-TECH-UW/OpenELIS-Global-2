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
package us.mn.state.health.lims.patient.dao;

import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.person.valueholder.Person;

/**
 * @author diane benz
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public interface PatientDAO extends BaseDAO {

	public boolean insertData(Patient patient) throws LIMSRuntimeException;

	public void deleteData(List patients) throws LIMSRuntimeException;

	public List getAllPatients() throws LIMSRuntimeException;

	public List getPageOfPatients(int startingRecNo)
			throws LIMSRuntimeException;

	public void getData(Patient patient) throws LIMSRuntimeException;

	public void updateData(Patient patient) throws LIMSRuntimeException;

	public List getNextPatientRecord(String id) throws LIMSRuntimeException;

	public List getPreviousPatientRecord(String id) throws LIMSRuntimeException;

	public boolean externalIDExists(String patientExternalID);

	public Patient readPatient(String idString);

	public Patient getPatientByPerson( Person person) throws LIMSRuntimeException;

    public Patient getPatientByNationalId(String subjectNumber);
    
    public List<Patient> getPatientsByNationalId(String nationalId) throws LIMSRuntimeException;

    public Patient getPatientByExternalId(String externalId);

	public List<String> getPatientIdentityBySampleStatusIdAndProject(List<Integer> inclusiveStatusIdList, String study)throws LIMSRuntimeException;

	public Patient getData(String patientId) throws LIMSRuntimeException;
}
