package org.openelisglobal.patient;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.patient.service.PatientTypeService;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.patienttype.valueholder.PatientType;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.person.valueholder.Person;
import org.springframework.beans.factory.annotation.Autowired;

public class PatientServiceTest extends BaseWebContextSensitiveTest {

    @Autowired
    PatientService patientService;

    @Autowired
    PatientTypeService patientTypeService;

    @Autowired
    PersonService personService;

    @Before
    public void init() throws Exception {
        patientService.deleteAll(patientService.getAll());
        personService.deleteAll(personService.getAll());
        patientTypeService.deleteAll(patientTypeService.getAll());
    }

    @After
    public void tearDown() {
        patientService.deleteAll(patientService.getAll());
        personService.deleteAll(personService.getAll());
        patientTypeService.deleteAll(patientTypeService.getAll());
    }

    @Test
    public void createPatient_shouldCreateNewPatient() throws Exception {
        String firstName = "John";
        String lastname = "Doe";
        String dob = "12/12/1992";
        String gender = "M";
        Patient pat = createPatient(firstName, lastname, dob, gender);

        Assert.assertEquals(0, patientService.getAllPatients().size());

        String patientId = patientService.insert(pat);
        Patient savedPatient = patientService.get(patientId);

        Assert.assertEquals(1, patientService.getAllPatients().size());
        Assert.assertEquals(firstName, savedPatient.getPerson().getFirstName());
        Assert.assertEquals(lastname, savedPatient.getPerson().getLastName());
        Assert.assertEquals(gender, savedPatient.getGender());
    }

    @Test
    public void getData_shouldCopyPropertiesFromDatabase() throws Exception {
        String firstName = "John";
        String lastname = "Doe";
        String dob = "12/12/1992";
        String gender = "M";
        Patient patient = createPatient(firstName, lastname, dob, gender);
        String patientId = patientService.insert(patient);

        Patient savedPatient = new Patient();
        savedPatient.setId(patientId);
        patientService.getData(savedPatient);

        Assert.assertEquals(gender, savedPatient.getGender());
    }

    @Test
    public void getData_shouldCopyPropertiesFromDatabaseById() throws Exception {
        String firstName = "John";
        String lastname = "Doe";
        String dob = "12/12/1992";
        String gender = "M";
        Patient patient = createPatient(firstName, lastname, dob, gender);
        String patientId = patientService.insert(patient);

        Patient patient2 = patientService.getData(patientId);

        Assert.assertEquals(gender, patient2.getGender());
    }

    @Test
    public void getEnteredDOB_shouldReturnEnteredDOB() throws Exception {
        String firstName = "John";
        String lastname = "Doe";
        String dob = "12/12/1992";
        String gender = "M";
        Patient patient = createPatient(firstName, lastname, dob, gender);
        patient.setBirthDateForDisplay("12/12/1992");
        patientService.insert(patient);
        Assert.assertEquals(dob, patientService.getEnteredDOB(patient));
    }

    @Test
    public void getDOB_shouldReturnDOB() throws Exception {
        String firstName = "John";
        String lastname = "Doe";
        String dob = "12/12/1992";
        String gender = "M";
        Patient patient = createPatient(firstName, lastname, dob, gender);
        patientService.insert(patient);
        Assert.assertEquals("1992-12-12 00:00:00.0", patientService.getDOB(patient).toString());
    }

    @Test
    public void getPhone_shouldReturnPhone() throws Exception {
        String firstName = "John";
        String lastname = "Doe";
        String dob = "12/12/1992";
        String gender = "M";
        Patient patient = createPatient(firstName, lastname, dob, gender);
        patient.getPerson().setPrimaryPhone("12345");
        patientService.insert(patient);
        Assert.assertEquals("12345", patientService.getPhone(patient));
    }

    @Test
    public void getPerson_shouldReturnPerson() throws Exception {
        String firstName = "John";
        String lastname = "Doe";
        String dob = "12/12/1992";
        String gender = "M";
        Patient patient = createPatient(firstName, lastname, dob, gender);

        patientService.insert(patient);

        Person retrievedPerson = patientService.getPerson(patient);
        Assert.assertEquals(firstName, retrievedPerson.getFirstName());
        Assert.assertEquals(lastname, retrievedPerson.getLastName());
    }

    @Test
    public void getPatientId_shouldReturngetPatientId() throws Exception {
        String firstName = "John";
        String lastname = "Doe";
        String dob = "12/12/1992";
        String gender = "M";
        Patient patient = createPatient(firstName, lastname, dob, gender);

        String patientId = patientService.insert(patient);

        Assert.assertEquals(patientId, patientService.getPatientId(patient));
    }

    @Test
    public void getAllPatients_shouldGetAllPatients() throws Exception {
        String firstName = "John";
        String lastname = "Doe";
        String dob = "12/12/1992";
        String gender = "M";
        Patient patient = createPatient(firstName, lastname, dob, gender);
        patientService.insert(patient);
        Assert.assertEquals(1, patientService.getAllPatients().size());
    }

    @Test
    public void getPatientByPerson_shouldReturnPatientByPerson() throws Exception {
        String firstName = "John";
        String lastname = "Doe";
        String dobs = "12/12/1992";
        String gender = "M";

        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastname);
        personService.save(person);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dobs);
        long time = date.getTime();
        Timestamp dob = new Timestamp(time);

        Patient pat = new Patient();
        pat.setPerson(person);
        pat.setBirthDate(dob);
        pat.setGender(gender);

        patientService.insert(pat);

        Patient patient = patientService.getPatientByPerson(person);

        Assert.assertEquals(gender, patient.getGender());

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
    public void updatePatient_shouldUpdateExistingPatient() throws Exception {
        String firstName = "Alice";
        String lastName = "Johnson";
        String dob = "15/05/1985";
        String gender = "F";
        Patient pat = createPatient(firstName, lastName, dob, gender);

        String patientId = patientService.insert(pat);

        Patient savedPatient = patientService.get(patientId);
        savedPatient.getPerson().setFirstName("Alicia");
        savedPatient.setGender("F");

        personService.save(savedPatient.getPerson());

        patientService.update(savedPatient);

        Patient updatedPatient = patientService.get(patientId);
        Assert.assertEquals("Alicia", updatedPatient.getPerson().getFirstName());
        Assert.assertEquals("F", updatedPatient.getGender());
    }

    @Test
    public void deletePatient_shouldRemovePatient() throws Exception {
        String firstName = "Bob";
        String lastName = "Marley";
        String dob = "06/02/1945";
        String gender = "M";
        Patient pat = createPatient(firstName, lastName, dob, gender);

        Assert.assertEquals(0, patientService.getAllPatients().size());

        String patientId = patientService.insert(pat);
        Assert.assertNotNull(patientId);

        Patient savedPatient = patientService.get(patientId);
        Assert.assertNotNull(savedPatient);

        patientService.delete(savedPatient);

        Assert.assertEquals(0, patientService.getAllPatients().size());
    }

    @Test
    public void createPatientType_shouldCreateNewPatientType() throws Exception {
        PatientType patientType = new PatientType();
        patientType.setDescription("Test Type Description");
        patientType.setType("Test Type");

        Assert.assertEquals(0, patientTypeService.getAllPatientTypes().size());

        String patientTypeId = patientTypeService.insert(patientType);
        PatientType savedPatientType = patientTypeService.get(patientTypeId);

        Assert.assertEquals(1, patientTypeService.getAllPatientTypes().size());
        Assert.assertEquals("Test Type Description", savedPatientType.getDescription());
        Assert.assertEquals("Test Type", savedPatientType.getType());
    }

    @Test
    public void getNationalId_shouldReturnNationalId() throws Exception {
        String firstName = "Bruce";
        String lastName = "Wayne";
        String dob = "10/10/1975";
        String gender = "M";
        Patient pat = createPatient(firstName, lastName, dob, gender);
        pat.setNationalId("12345");

        String patientId = patientService.insert(pat);

        Assert.assertEquals("12345", patientService.getNationalId(pat));
    }

    @Test
    public void getAddressComponents_shouldReturnAddressComponents() throws Exception {
        String firstName = "Bruce";
        String lastName = "Wayne";
        String dob = "10/10/1975";
        String gender = "M";
        String city = "Kampala";
        String country = "Uganda";
        String state = "Kisumali";
        String streetAdress = "Bakuli";
        String zipCode = "256";
        Patient pat = createPatient(firstName, lastName, dob, gender);
        pat.getPerson().setCity(city);
        pat.getPerson().setCountry(country);
        pat.getPerson().setState(state);
        pat.getPerson().setStreetAddress(streetAdress);
        pat.getPerson().setZipCode(zipCode);

        String patientId = patientService.insert(pat);

        Map<String, String> result = patientService.getAddressComponents(pat);

        assertEquals(city, result.get("City"));
        assertEquals(country, result.get("Country"));
        assertEquals(state, result.get("State"));
        assertEquals(streetAdress, result.get("Street"));
        assertEquals(zipCode, result.get("Zip"));
    }

    @Test
    public void getPatientByNationalId_shouldReturnCorrectPatient() throws Exception {
        String firstName = "Bruce";
        String lastName = "Wayne";
        String dob = "10/10/1975";
        String gender = "M";
        Patient pat = createPatient(firstName, lastName, dob, gender);
        pat.setNationalId("12345");

        String patientId = patientService.insert(pat);

        Patient fetchedPatient = patientService.getPatientByNationalId("12345");

        Assert.assertNotNull(fetchedPatient);
        Assert.assertEquals(patientId, fetchedPatient.getId());
    }

    @Test
    public void getPatientsByNationalId_shouldReturnListOfPatients() throws Exception {
        String firstName = "Bruce";
        String lastName = "Wayne";
        String dob = "10/10/1975";
        String gender = "M";
        Patient pat = createPatient(firstName, lastName, dob, gender);
        pat.setNationalId("12345");

        String patientId = patientService.insert(pat);

        String firstName2 = "Bruce";
        String lastName2 = "Wayne";
        String dob2 = "10/10/1975";
        String gender2 = "M";
        Patient pat2 = createPatient(firstName2, lastName2, dob2, gender2);
        pat2.setNationalId("12345");

        String patientId2 = patientService.insert(pat2);

        List<Patient> fetchedPatients = patientService.getPatientsByNationalId("12345");

        Assert.assertNotNull(fetchedPatients);
        Assert.assertEquals(2, fetchedPatients.size());
    }

    @Test
    public void getPatientByExternalId_shouldReturnCorrectPatient() throws Exception {
        String firstName = "Oliver";
        String lastName = "Queen";
        String dob = "27/09/1985";
        String gender = "M";
        Patient pat = createPatient(firstName, lastName, dob, gender);
        pat.setExternalId("EX123");

        String patientId = patientService.insert(pat);

        Patient fetchedPatient = patientService.getPatientByExternalId("EX123");

        Assert.assertNotNull(fetchedPatient);
        Assert.assertEquals(patientId, fetchedPatient.getId());
    }

    @Test
    public void externalIDExists_shouldReturnExternalIDExists() throws Exception {
        String firstName = "Oliver";
        String lastName = "Queen";
        String dob = "27/09/1985";
        String gender = "M";
        Patient pat = createPatient(firstName, lastName, dob, gender);
        pat.setExternalId("EX123");

        String patientId = patientService.insert(pat);

        Assert.assertTrue(patientService.externalIDExists("EX123"));
    }

    @Test
    public void getExternalID_shouldReturnExternalID() throws Exception {
        String firstName = "Oliver";
        String lastName = "Queen";
        String dob = "27/09/1985";
        String gender = "M";
        Patient pat = createPatient(firstName, lastName, dob, gender);
        pat.setExternalId("EX123");

        Assert.assertEquals("EX123", patientService.getExternalId(pat));
    }

    @Test
    public void readPatient_shouldReadPatient() throws Exception {
        String firstName = "Oliver";
        String lastName = "Queen";
        String dob = "27/09/1985";
        String gender = "M";
        Patient pat = createPatient(firstName, lastName, dob, gender);
        pat.setExternalId("EX123");

        String patientId = patientService.insert(pat);

        Patient patient = patientService.readPatient(patientId);
        Assert.assertEquals(gender, patient.getGender());
        Assert.assertEquals("1985-09-27 00:00:00.0", patient.getBirthDate().toString());
    }

    @Test
    public void getFirstName_shouldReturnCorrectFirstName() throws Exception {
        String firstName = "Tony";
        String lastName = "Stark";
        String dob = "10/05/1970";
        String gender = "M";
        Patient pat = createPatient(firstName, lastName, dob, gender);

        String patientId = patientService.insert(pat);

        String fetchedFirstName = patientService.getFirstName(pat);

        Assert.assertEquals(firstName, fetchedFirstName);
    }

    @Test
    public void getLastName_shouldReturnCorrectLastName() throws Exception {
        String firstName = "Natasha";
        String lastName = "Romanoff";
        String dob = "03/12/1985";
        String gender = "F";
        Patient pat = createPatient(firstName, lastName, dob, gender);

        String patientId = patientService.insert(pat);

        String fetchedLastName = patientService.getLastName(pat);

        Assert.assertEquals(lastName, fetchedLastName);
    }

    @Test
    public void getLastFirstName_shouldReturnCorrectLastFirstName() throws Exception {
        String firstName = "Steve";
        String lastName = "Rogers";
        String dob = "04/07/1920";
        String gender = "M";
        Patient pat = createPatient(firstName, lastName, dob, gender);

        String patientId = patientService.insert(pat);

        String lastFirstName = patientService.getLastFirstName(pat);

        Assert.assertEquals(lastName + ", " + firstName, lastFirstName);
    }

    @Test
    public void getGender_shouldReturnCorrectGender() throws Exception {
        String firstName = "Derrick";
        String lastName = "Junior";
        String dob = "13/02/1989";
        String gender = "F";
        Patient pat = createPatient(firstName, lastName, dob, gender);

        String patientId = patientService.insert(pat);

        String fetchedGender = patientService.getGender(pat);

        Assert.assertEquals(gender, fetchedGender);
    }

    @Test
    public void getLocalizedGender_shouldReturnCorrectLocalizedGender() throws Exception {
        String firstName = "Tayebwa";
        String lastName = "Noah";
        String dob = "01/01/2020";
        String gender = "M";
        Patient pat = createPatient(firstName, lastName, dob, gender);

        String patientId = patientService.insert(pat);

        String localizedGender = patientService.getLocalizedGender(pat);

        Assert.assertEquals("MALE", localizedGender);
    }

    @Test
    public void getPageOfPatients_shouldReturnCorrectPatients() throws Exception {
        String firstName1 = "Josh";
        String lastName1 = "Nsereko";
        String dob1 = "20/03/1980";
        String gender1 = "M";
        Patient pat1 = createPatient(firstName1, lastName1, dob1, gender1);

        String firstName2 = "John";
        String lastName2 = "Stewart";
        String dob2 = "22/04/1982";
        String gender2 = "M";
        Patient pat2 = createPatient(firstName2, lastName2, dob2, gender2);

        patientService.insert(pat1);
        patientService.insert(pat2);

        List<Patient> patientsPage = patientService.getPageOfPatients(1);

        int expectedPageSize = Integer
                .parseInt(ConfigurationProperties.getInstance().getPropertyValue("page.defaultPageSize"));
        Assert.assertTrue(patientsPage.size() <= expectedPageSize);

        if (expectedPageSize >= 2) {
            Assert.assertTrue(patientsPage.stream().anyMatch(p -> p.getPerson().getFirstName().equals(firstName1)));
            Assert.assertTrue(patientsPage.stream().anyMatch(p -> p.getPerson().getFirstName().equals(firstName2)));
        }
    }

}
