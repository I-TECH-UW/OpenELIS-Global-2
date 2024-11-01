package org.openelisglobal.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.transaction.Transactional;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.person.valueholder.Person;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonServiceTest extends BaseWebContextSensitiveTest {

    @Autowired
    PersonService personService;

    @Autowired
    PatientService patientService;

    @Before
    public void init() throws Exception {
        patientService.deleteAll(patientService.getAll());
        personService.deleteAll(personService.getAll());
    }

    @After
    public void tearDown() {
        patientService.deleteAll(patientService.getAll());
        personService.deleteAll(personService.getAll());
    }

    @Test
    public void createPerson_shouldCreateNewPerson() throws Exception {
        String firstName = "John";
        String lastname = "Doe";

        Person pat = createPerson(firstName, lastname);

        Assert.assertEquals(0, personService.getAllPersons().size());
        // save person to the DB
        String personIdId = personService.insert(pat);
        Person savedPerson = personService.get(personIdId);

        Assert.assertEquals(1, personService.getAllPersons().size());
        Assert.assertEquals(firstName, savedPerson.getFirstName());
        Assert.assertEquals(lastname, savedPerson.getLastName());
    }

    @Test
    @Transactional
    @SuppressWarnings("unchecked")
    public void createPersonWithMultiplePatients_shouldLinkPatientsToPerson() throws Exception {

        Person person = new Person();
        String personId = personService.insert(person);
        Person savedPerson = personService.get(personId);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse("12/12/1992");
        long time = date.getTime();
        Timestamp dob = new Timestamp(time);

        Patient patient1 = new Patient();
        patient1.setPerson(person);
        patient1.setBirthDate(dob);
        patient1.setGender("M");
        String patientId1 = patientService.insert(patient1);
        Patient patient2 = new Patient();
        patient2.setPerson(person);
        patient2.setBirthDate(dob);
        patient2.setGender("M");
        String patientId2 = patientService.insert(patient2);

        savedPerson.addPatient(patient1);
        savedPerson.addPatient(patient2);

        Set<Patient> patients = personService.get(savedPerson.getId()).getPatients();
        Assert.assertEquals(2, patients.size());
        Assert.assertTrue(patients.stream().anyMatch(p -> p.getId().equals(patientId1)));
        Assert.assertTrue(patients.stream().anyMatch(p -> p.getId().equals(patientId2)));
        for (Patient patient : patients) {
            Assert.assertEquals(savedPerson.getId(), patient.getPerson().getId());
        }
    }

    @Test
    public void getAllPerson_shouldGetAllPerson() throws Exception {
        Person person = new Person();
        personService.insert(person);
        Assert.assertEquals(1, personService.getAllPersons().size());
    }

    private Person createPerson(String firstName, String LastName) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(LastName);
        return person;
    }

    @Test
    public void getLastName_shouldReturnEmptyStringForNullPerson() {
        String retrievedLastName = personService.getLastName(null);

        Assert.assertEquals("", retrievedLastName);
    }

    @Test
    public void getLastName_shouldReturnLastName() {
        String firstName = "John";
        String lastName = "Doe";

        Person person = createPerson(firstName, lastName);
        String retrievedLastName = personService.getLastName(person);

        Assert.assertEquals(lastName, retrievedLastName);
    }

    @Test
    public void getFirstName_shouldReturnFirstName() {
        String firstName = "Jones";
        String lastName = "Doe";

        Person person = createPerson(firstName, lastName);
        String retrievedFirstName = personService.getFirstName(person);

        Assert.assertEquals(firstName, retrievedFirstName);
    }

    @Test
    public void getLastFirstName_shouldReturnCorrectFormat() throws Exception {
        String firstName = "John";
        String lastName = "Doe";

        Person person = createPerson(firstName, lastName);
        String lastFirstName = personService.getLastFirstName(person);

        Assert.assertEquals(lastName + ", " + firstName, lastFirstName);
    }

    @Test
    public void getWorkPhone_shouldReturnWorkPhone() throws Exception {
        String firstName = "John";
        String lastName = "Doe";

        Person person = createPerson(firstName, lastName);
        person.setWorkPhone("12345");
        String personId = personService.insert(person);
        Assert.assertEquals("12345", personService.getWorkPhone(person));
    }

    @Test
    public void getCellPhone_shouldReturnCellPhone() throws Exception {
        String firstName = "John";
        String lastName = "Doe";

        Person person = createPerson(firstName, lastName);
        person.setCellPhone("12345");
        String personId = personService.insert(person);
        Assert.assertEquals("12345", personService.getCellPhone(person));
    }

    @Test
    public void getFax_shouldReturnFax() throws Exception {
        String firstName = "John";
        String lastName = "Doe";

        Person person = createPerson(firstName, lastName);
        person.setFax("1245");
        String personId = personService.insert(person);
        Assert.assertEquals("1245", personService.getFax(person));
    }

    @Test
    public void getPersonById_shouldReturngetPersonById() throws Exception {
        String firstName = "John";
        String lastName = "Doe";

        Person person = createPerson(firstName, lastName);
        person.setFax("1245");
        String personId = personService.insert(person);

        Person savedPerson = personService.getPersonById(personId);
        Assert.assertEquals("1245", personService.getFax(savedPerson));
    }

    @Test
    public void getData_shouldReturncopiesPropertiesFromDatabase() throws Exception {
        String firstName = "John";
        String lastName = "Doe";

        Person person = createPerson(firstName, lastName);
        person.setFax("1245");
        String personId = personService.insert(person);

        Person personToUpdate = new Person();
        personToUpdate.setId(personId);

        personService.getData(personToUpdate);

        assertNotNull(personToUpdate.getId());
        assertEquals("1245", personToUpdate.getFax());
    }

    @Test
    public void getData_shouldReturnpersonNotFound() throws Exception {
        String firstName = "John";
        String lastName = "Doe";

        Person person = createPerson(firstName, lastName);
        person.setFax("1245");
        String personId = personService.insert(person);

        Person personToUpdate = new Person();
        personToUpdate.setId("345");

        personService.getData(personToUpdate);

        assertNull(personToUpdate.getId());
    }

    @Test
    public void getPageOfPersons_shouldReturnPageOfPersons() {
        String firstName = "John";
        String lastName = "Doe";

        String firstName2 = "Joseph";
        String lastName2 = "Luke";

        Person person = createPerson(firstName, lastName);
        Person person2 = createPerson(firstName2, lastName2);
        personService.insert(person);
        personService.insert(person2);

        List<Person> personsPage = personService.getPageOfPersons(1);

        int expectedPageSize = Integer
                .parseInt(ConfigurationProperties.getInstance().getPropertyValue("page.defaultPageSize"));
        Assert.assertTrue(personsPage.size() <= expectedPageSize);

        if (expectedPageSize >= 2) {
            Assert.assertTrue(personsPage.stream().anyMatch(p -> p.getFirstName().equals(firstName)));
            Assert.assertTrue(personsPage.stream().anyMatch(p -> p.getFirstName().equals(firstName2)));
        }
    }

    @Test
    public void getData_shouldRetrieveDataForPerson() throws Exception {
        // Create a new person
        String firstName = "John";
        String lastName = "Doe";
        Person person = createPerson(firstName, lastName);

        String personId = personService.insert(person);

        Person savedPerson = new Person();
        savedPerson.setId(personId);
        personService.getData(savedPerson);

        Assert.assertEquals(firstName, savedPerson.getFirstName());
        Assert.assertEquals(lastName, savedPerson.getLastName());
    }

    @Test
    public void getEmail_shouldReturnCorrectEmail() throws Exception {
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";

        Person person = createPerson(firstName, lastName);
        person.setEmail(email);

        String personId = personService.insert(person);
        Person savedPerson = personService.get(personId);

        String retrievedEmail = personService.getEmail(savedPerson);

        Assert.assertEquals(email, retrievedEmail);
    }

    @Test
    public void updatePerson_shouldUpdatePersonInformation() throws Exception {
        String firstName = "John";
        String lastName = "Doe";
        Person person = createPerson(firstName, lastName);
        person.setCity("New York");
        person.setCountry("USA");
        person.setState("NY");
        person.setStreetAddress("123 Main St");
        person.setZipCode("10001");

        String personId = personService.insert(person);
        Person savedPerson = personService.get(personId);

        savedPerson.setCity("Los Angeles");
        savedPerson.setStreetAddress("456 Oak St");
        personService.update(savedPerson);

        Person updatedPerson = personService.get(personId);

        Assert.assertEquals("Los Angeles", updatedPerson.getCity());
        Assert.assertEquals("456 Oak St", updatedPerson.getStreetAddress());
    }

    @Test
    public void getPhone_shouldReturnCorrectPhoneNumber() throws Exception {
        String firstName = "John";
        String lastName = "Doe";
        Person person = createPerson(firstName, lastName);
        person.setPrimaryPhone("123-456-7890");

        String personId = personService.insert(person);
        Person savedPerson = personService.get(personId);

        String phoneNumber = personService.getPhone(savedPerson);
        Assert.assertEquals("123-456-7890", phoneNumber);
    }

    @Test
    public void getPersonByLastName_shouldReturnCorrectPerson() throws Exception {
        String firstName = "Jane";
        String lastName = "Smith";

        Person person = createPerson(firstName, lastName);
        personService.insert(person);

        Person retrievedPerson = personService.getPersonByLastName(lastName);
        Assert.assertNotNull(retrievedPerson);
        Assert.assertEquals(firstName, retrievedPerson.getFirstName());
        Assert.assertEquals(lastName, retrievedPerson.getLastName());
    }

    @Test
    public void getAddressComponents_shouldReturngetAddressComponents() throws Exception {
        String firstName = "Jane";
        String lastName = "Smith";
        String city = "Kampala";
        String country = "Uganda";
        String state = "Kisumali";
        String streetAdress = "Bakuli";
        String zipCode = "256";

        Person person = createPerson(firstName, lastName);
        person.setCity(city);
        person.setCountry(country);
        person.setState(state);
        person.setStreetAddress(streetAdress);
        person.setZipCode(zipCode);
        personService.insert(person);

        Map<String, String> result = personService.getAddressComponents(person);
        assertEquals(city, result.get("City"));
        assertEquals(country, result.get("Country"));
        assertEquals(state, result.get("State"));
        assertEquals(streetAdress, result.get("Street"));
        assertEquals(zipCode, result.get("Zip"));
    }

     @Test
    public void testGetAddressComponents_handlesNullPerson() {
        Map<String, String> result = personService.getAddressComponents(null);

        assertTrue(result.isEmpty());
    }
}