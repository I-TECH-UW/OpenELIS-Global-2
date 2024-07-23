package org.openelisglobal.person;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.transaction.Transactional;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
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

    String personId;

    @Before
    public void init() throws Exception {
        patientService.deleteAll(patientService.getAll());
        personService.deleteAll(personService.getAll());

        String firstName = "John";
        String lastname = "Doe";
        String phone = "123-456-7890";
        String workPhone = "6789";
        String cell = "09876";
        String primaryPhone = "123-456-7890";
        String fax = "123";
        String email = "john.doe@example.com";
        String city = "Kampala";
        String country = "Uganda";
        String zip = "256";
        String street = "123 Main St";

        Person person = createPerson(firstName, lastname, phone, workPhone, cell, primaryPhone, fax, email, city,
                country, zip, street);

        // save person to the DB
        personId = personService.insert(person);
    }

    @After
    public void tearDown() {
        patientService.deleteAll(patientService.getAll());
        personService.deleteAll(personService.getAll());
    }

    @Test
    public void createPerson_shouldCreateNewPerson() throws Exception {
        Person savedPerson = personService.get(personId);

        Assert.assertEquals(1, personService.getAllPersons().size());
        Assert.assertEquals("John", savedPerson.getFirstName());
        Assert.assertEquals("Doe", savedPerson.getLastName());
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
        Assert.assertEquals(1, personService.getAllPersons().size());
    }

    @Test
    public void getLastName_shouldReturnEmptyStringForNullPerson() {
        String retrievedLastName = personService.getLastName(null);

        Assert.assertEquals("", retrievedLastName);
    }

    @Test
    public void getLastFirstName_ShouldReturnLastAndFisrtName() {
        Person savedPerson = personService.get(personId);
        Assert.assertEquals("Doe, John", personService.getLastFirstName(savedPerson));
    }

    @Test
    public void getData_shouldRetrieveDataForPerson() throws Exception {

        Person savedPerson = new Person();
        savedPerson.setId(personId);
        personService.getData(savedPerson);

        Assert.assertEquals("John", savedPerson.getFirstName());
        Assert.assertEquals("Doe", savedPerson.getLastName());
    }

    @Test
    public void getEmail_shouldReturnCorrectEmail() throws Exception {
        Person savedPerson = personService.get(personId);
        Assert.assertEquals("john.doe@example.com", personService.getEmail(savedPerson));
    }

    @Test
    public void updatePerson_shouldUpdatePersonInformation() throws Exception {
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
        Person savedPerson = personService.get(personId);

        String phoneNumber = personService.getPhone(savedPerson);
        Assert.assertEquals("123-456-7890", phoneNumber);
    }

    @Test
    public void getPhone_shouldReturnPhoneContact() {
        Person savedPerson = personService.get(personId);
        Assert.assertEquals("123-456-7890", personService.getPhone(savedPerson));
    }

    @Test
    public void getCellPhone_shouldReturnCellContact() {
        Person savedPerson = personService.get(personId);
        Assert.assertEquals("09876", personService.getCellPhone(savedPerson));
    }

    @Test
    public void getworkPhone_shouldReturnworkPhoneContact() {
        Person savedPerson = personService.get(personId);
        Assert.assertEquals("6789", personService.getWorkPhone(savedPerson));
    }

    @Test
    public void getPersonByLastName_shouldReturnCorrectPerson() throws Exception {
        String lastName = "Doe";
        Person retrievedPerson = personService.get(personId);
        Assert.assertNotNull(retrievedPerson);
        Assert.assertEquals(lastName, retrievedPerson.getLastName());
    }

    @Test
    public void getfax_shouldReturnFaxNumber() {
        Person savedPerson = personService.get(personId);
        Assert.assertEquals("123", personService.getFax(savedPerson));
    }

    @Test
    public void getFirstName_shouldReturnFirstNameForPerson() throws Exception {
        Person savedPerson = personService.get(personId);

        Assert.assertEquals("John", personService.getFirstName(savedPerson));
    }

    @Test
    public void getLastName_shouldReturnLastNameForPerson() throws Exception {
        Person savedPerson = personService.get(personId);

        Assert.assertEquals("Doe", personService.getLastName(savedPerson));
    }

    @Test
    public void getPersonById_shouldReturnPersonById() throws Exception {
        Person savedPerson = personService.getPersonById("2");

        Assert.assertEquals("Doe", personService.getLastName(savedPerson));
    }

    private Person createPerson(String firstName, String LastName, String phone, String workPhone, String cell,
            String primaryPhone, String fax, String email, String city, String country, String zip, String street) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(LastName);
        person.setHomePhone(phone);
        person.setWorkPhone(workPhone);
        person.setCellPhone(cell);
        person.setPrimaryPhone(primaryPhone);
        person.setFax(fax);
        person.setEmail(email);
        person.setCity(city);
        person.setCountry(country);
        person.setZipCode(zip);
        person.setStreetAddress(street);

        return person;
    }

}
