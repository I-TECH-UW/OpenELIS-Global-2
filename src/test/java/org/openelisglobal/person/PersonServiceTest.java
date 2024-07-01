package org.openelisglobal.person;

import java.util.Set;
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
import java.util.Map;


public class PersonServiceTest extends BaseWebContextSensitiveTest {

    @Autowired
    PersonService personService;

    @Before
    public void init() throws Exception {
    }

    @Test
    public void createPerson_shouldCreateNewPerson() throws Exception {
        String firstName = "John";
        String lastname = "Doe";

        Person pat = createPerson(firstName, lastname);
        String personIdId = personService.insert(pat);
        Person savedPerson = personService.get(personIdId);

        Assert.assertEquals(firstName, savedPerson.getFirstName());
        Assert.assertEquals(lastname, savedPerson.getLastName());
    }

    public void getAllPerson_shouldGetAllPerson() throws Exception {
        Assert.assertEquals(1, personService.getAllPersons().size());
    }

    private Person createPerson(String firstName, String LastName) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(LastName);
        return person;
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
    public void getAddressComponents_shouldReturnCorrectAddress() throws Exception {
        String firstName = "John";
        String lastName = "Doe";
        Person person = createPerson(firstName, lastName);
        person.setCity("New York");
        person.setCountry("USA");
        person.setState("NY");
        person.setStreetAddress("123 Main St");
        person.setZipCode("10001");

        System.out.println("Creating person with details: " + person);

        String personId = personService.insert(person);
        System.out.println("Person inserted with ID: " + personId);

        Person savedPerson = personService.get(personId);
        System.out.println("Retrieved saved person: " + savedPerson);

        Map<String, String> addressComponents = personService.getAddressComponents(savedPerson);
        System.out.println("Address components retrieved: " + addressComponents);

        Assert.assertEquals("New York", addressComponents.get("City"));
        Assert.assertEquals("USA", addressComponents.get("Country"));
        Assert.assertEquals("NY", addressComponents.get("State"));

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
    public void getLastFirstName_shouldReturnCorrectFormat() throws Exception {
        String firstName = "John";
        String lastName = "Doe";

        Person person = createPerson(firstName, lastName);
        String lastFirstName = personService.getLastFirstName(person);

        Assert.assertEquals(lastName + ", " + firstName, lastFirstName);
    }

    @Test
    public void getLastName_shouldReturnEmptyStringForNullPerson() {
        String retrievedLastName = personService.getLastName(null);

        Assert.assertEquals("", retrievedLastName);
    }
    @Test
    public void getPersonById_shouldReturnCorrectPerson() throws Exception {
        String firstName = "John";
        String lastName = "Doe";
        Person person = createPerson(firstName, lastName);

        String personId = personService.insert(person);

        Person retrievedPerson = personService.getPersonById(personId);

        Assert.assertNotNull(retrievedPerson);

        Assert.assertEquals(firstName, retrievedPerson.getFirstName());
        Assert.assertEquals(lastName, retrievedPerson.getLastName());
    }
}

   





