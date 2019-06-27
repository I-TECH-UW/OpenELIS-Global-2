package spring.service.patient;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.dataexchange.order.action.MessagePatient;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.sample.valueholder.Sample;

public interface PatientService extends BaseObjectService<Patient, String> {

	String getGUID();

	String getSTNumber();

	String getSubjectNumber();

	String getLocalizedGender();

	Map<String, String> getAddressComponents();

	String getEnteredDOB();

	Timestamp getDOB();

	String getPhone();

	String getAKA();

	String getMother();

	String getInsurance();

	String getOccupation();

	String getOrgSite();

	String getMothersInitial();

	String getEducation();

	String getMaritalStatus();

	String getHealthDistrict();

	String getHealthRegion();

	String getObNumber();

	String getPCNumber();

	void getData(Patient patient);

	Patient getData(String patientId);

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

	Patient getPatient();

	void setPatient(Patient patient);

	String getExternalId();

	void setPatient(MessagePatient orderPatient);

	void setPatientBySample(Sample sample);

	void setPatient(String guid);

}
