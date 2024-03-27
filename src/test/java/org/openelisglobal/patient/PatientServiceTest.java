package org.openelisglobal.patient;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openelisglobal.BaseTestConfig;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.person.valueholder.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BaseTestConfig.class, PatientTestConfig.class })
@TestPropertySource("classpath:common.properties")
@ActiveProfiles("test")
public class PatientServiceTest {

	@Autowired
	PatientService patientService;

	@Autowired
	PersonService personService;

	private static final Logger logger = LoggerFactory.getLogger(PatientServiceTest.class);

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

//		Assert.assertEquals(0, patientService.getAllPatients().size());
		// save patient to the DB
		String patientId = patientService.insert(pat);
		Patient savedPatient = patientService.get(patientId);

		Assert.assertEquals(2, patientService.getAllPatients().size());
		Assert.assertEquals(firstName, savedPatient.getPerson().getFirstName());
		Assert.assertEquals(lastname, savedPatient.getPerson().getLastName());
		Assert.assertEquals(gender, savedPatient.getGender());
	}

	@Test
	public void updatePatient_shouldUpdateExistingPatient() throws Exception {
		String firstName = "John";
		String lastName = "Doe";
		String dob = "12/12/1992";
		String gender = "M";

		Patient pat = createPatient(firstName, lastName, dob, gender);
		String patientId = patientService.insert(pat);

		// Fetch the patient from the database to get the updated version
		Patient savedPatient = patientService.get(patientId);

		// Modify some details of the patient
		String newFirstName = "Fatma";
		String newLastName = "Mohamed";
		String newDob = "10/10/1982";
		String newGender = "F";

		// Update the patient object with new details
		savedPatient.getPerson().setFirstName(newFirstName);
		savedPatient.getPerson().setLastName(newLastName);
		savedPatient.setGender(newGender);
		savedPatient.setBirthDateForDisplay(newDob);

		// Update the patient in the database
		patientService.update(savedPatient);
		personService.update(savedPatient.getPerson());
		// Fetch the patient again to check if the update was successful
		Patient updatedPatient = patientService.get(patientId);

		// Assert that the updated details match the expected values
		Assert.assertEquals(newFirstName, updatedPatient.getPerson().getFirstName());
		Assert.assertEquals(newLastName, updatedPatient.getPerson().getLastName());
		Assert.assertEquals(newGender, updatedPatient.getGender());
		Assert.assertEquals(newDob, updatedPatient.getBirthDateForDisplay());
	}

	@Test
	public void deletePerson_shouldDeleteExistingPerson() throws Exception {
		String firstName = "John";
		String lastname = "Doe";
		String dob = "12/12/1992";
		String gender = "M";
		String id = "124";

		// Create a patient object
		Patient pat = createPatient(firstName, lastname, dob, gender);

		pat.setSysUserId(id);

		// Insert person into the database
		String personId = patientService.insert(pat);

		// Ensure that the person exists in the database
		Assert.assertNotNull("Ensure that person is exist", personService.get(personId));

		// Call the delete method

		patientService.delete(personId, pat.getSysUserId());
		Patient deletedPatient = null;
		try {
			deletedPatient = patientService.get(personId);
		} catch (ObjectNotFoundException e) {
			logger.error("Person not found with ID: {}", personId, e);

		}
		// Assert that the retrieved person is null, indicating successful deletion
		Assert.assertNull("Deleted patient should be null", deletedPatient);
	}

	@Test
	public void getAllPatients_shouldGetAllPatients() throws Exception {
		Assert.assertEquals(2, patientService.getAllPatients().size());
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

}
