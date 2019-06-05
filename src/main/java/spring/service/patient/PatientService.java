package spring.service.patient;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;
import us.mn.state.health.lims.person.valueholder.Person;

public interface PatientService extends BaseObjectService<Patient, String> {
	void getData(Patient patient);

	Patient getData(String patientId);

	void deleteData(List patients);

	void updateData(Patient patient);

	boolean insertData(Patient patient);

	Patient getPatientByNationalId(String subjectNumber);

	List<Patient> getPatientsByNationalId(String nationalId);

	List getPreviousPatientRecord(String id);

	Patient getPatientByPerson(Person person);

	List getPageOfPatients(int startingRecNo);

	List getNextPatientRecord(String id);

	Patient getPatientByExternalId(String externalId);

	List getAllPatients();

	boolean externalIDExists(String patientExternalID);

	Patient readPatient(String idString);

	List<String> getPatientIdentityBySampleStatusIdAndProject(List<Integer> inclusiveStatusIdList, String study);

	void persistPatientData(PatientManagementInfo patientInfo, Patient patient, String sysUserId);

	Person getPerson();

	String getBirthdayForDisplay();

	List<PatientIdentity> getIdentityList();

	String getLastFirstName();

	String getFirstName();

	String getLastName();

	String getPatientId();

	String getGender();

	String getNationalId();
}
