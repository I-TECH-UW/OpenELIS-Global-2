package org.openelisglobal.patient;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.person.valueholder.Person;
import org.springframework.beans.factory.annotation.Autowired;

public class PatientServiceTest extends BaseWebContextSensitiveTest {

	@Autowired
	PatientService patientService;

	@Autowired
	PersonService personService;

	@Before
	public void init() throws Exception {
	}

	@Test
	public void createPatient_shouldCreateNewPatient() throws Exception {
		String firstName = "John";
		String lastname = "Doe";
		String dob = "12/12/1992";
		String gender = "M";
		Patient pat = createPatient(firstName, lastname, dob, gender);

		Assert.assertEquals(0, patientService.getAllPatients().size());
		// save patient to the DB
		String patientId = patientService.insert(pat);
		Patient savedPatient = patientService.get(patientId);

		Assert.assertEquals(1, patientService.getAllPatients().size());
		Assert.assertEquals(firstName, savedPatient.getPerson().getFirstName());
		Assert.assertEquals(lastname, savedPatient.getPerson().getLastName());
		Assert.assertEquals(gender, savedPatient.getGender());
	}

	@Test
	public void getAllPatients_shouldGetAllPatients() throws Exception {
		Assert.assertEquals(1, patientService.getAllPatients().size());
	}

	private Patient createPatient(String firstName, String LastName, String birthDate, String gender)
			throws ParseException {
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(LastName);
		personService.save(person);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse(birthDate);
		long time = date.getTime();
		Timestamp dob = new Timestamp(time);

		Patient pat = new Patient();
		pat.setPerson(person);
		pat.setBirthDate(dob);
		pat.setGender(gender);

		return pat;
	}

	@Test
	public void updatePatient_shouldUpdatePatientInformation() throws Exception {
		String firstName = "John";
		String lastName = "Doe";
		String dob = "12/12/1992";
		String gender = "M";
		Patient pat = createPatient(firstName, lastName, dob, gender);

		String patientId = patientService.insert(pat);

		Patient patientToUpdate = patientService.get(patientId);
		Person person = patientToUpdate.getPerson();
		person.setFirstName("Jane");
		personService.save(person);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("01/01/2000");
		long time = date.getTime();
		Timestamp newDob = new Timestamp(time);
		patientToUpdate.setBirthDate(newDob);

		patientService.update(patientToUpdate);
		Patient updatedPatient = patientService.get(patientId);

		Assert.assertNotNull(updatedPatient);
		Assert.assertEquals("Jane", updatedPatient.getPerson().getFirstName()); // Verify updated name
		Assert.assertEquals(newDob, updatedPatient.getBirthDate()); // Verify updated dob

	}

	@Test
	public void deletePatient_shouldDeletePatientAndContacts() throws Exception {
		// Create a patient
		String firstName = "John";
		String lastName = "Doe";
		String dob = "12/12/1992";
		String gender = "M";
		Patient pat = createPatient(firstName, lastName, dob, gender);

		String patientId = patientService.insert(pat);

		Assert.assertEquals(1, patientService.getAllPatients().size());

		Patient patientToDelete = patientService.get(patientId);

		patientService.delete(patientToDelete);

		Assert.assertEquals(0, patientService.getAllPatients().size());

	}

	@Test
	public void getPatientsByNationalId_shouldReturnMatchingPatients() throws Exception {
		String nationalId = "123456789";
		Patient patient1 = createPatient("John", "Doe", "12/12/1990", "M");
		patient1.setNationalId(nationalId);
		Patient patient2 = createPatient("Jane", "Smith", "05/05/1988", "F");
		patient2.setNationalId(nationalId);

		patientService.insert(patient1);
		patientService.insert(patient2);

		List<Patient> patients = patientService.getPatientsByNationalId(nationalId);

		Assert.assertEquals(2, patients.size());
		for (Patient patient : patients) {
			Assert.assertEquals(nationalId, patient.getNationalId());
		}
	}

	@Test
	public void getData_shouldReturnCorrectPatient() throws Exception {
		// Create a patient
		String firstName = "John";
		String lastName = "Doe";
		String dob = "12/12/1992";
		String gender = "M";
		Patient pat = createPatient(firstName, lastName, dob, gender);

		String patientId = patientService.insert(pat);

		Patient retrievedPatient = patientService.getData(patientId);
		Assert.assertNotNull(retrievedPatient);
		Assert.assertNotNull(retrievedPatient);

	}

	@Test
	public void getPatientByExternalId_shouldReturnCorrectPatient() throws Exception {
		String externalId = "EXT123";

		Patient patient = createPatient("John", "Doe", "12/12/1992", "M");
		patient.setExternalId(externalId);
		patientService.insert(patient);

		Patient retrievedPatient = patientService.getPatientByExternalId(externalId);

		Assert.assertNotNull(retrievedPatient);
		Assert.assertEquals(externalId, retrievedPatient.getExternalId());
	}

	@Test
	public void externalIDExists_shouldReturnTrueForExistingID() throws Exception {
		String externalId = "EXT123";

		Patient patient = createPatient("John", "Doe", "12/12/1992", "M");
		patient.setExternalId(externalId);
		patientService.insert(patient);
		Assert.assertTrue(patientService.externalIDExists(externalId));
	}

	@Test
	public void externalIDExists_shouldReturnFalseForNonExistingID() throws Exception {
		String nonExistingExternalId = "NON_EXISTING";

		Assert.assertFalse(patientService.externalIDExists(nonExistingExternalId));
	}

	@Test
	public void getAllPatients_shouldReturnAllPatients() throws Exception {
		Patient patient1 = createPatient("John", "Doe", "12/12/1990", "M");
		Patient patient2 = createPatient("Jane", "Smith", "05/05/1988", "F");
		patientService.insert(patient1);
		patientService.insert(patient2);

		List<Patient> patients = patientService.getAllPatients();
		Assert.assertEquals(2, patients.size());
	}

}



