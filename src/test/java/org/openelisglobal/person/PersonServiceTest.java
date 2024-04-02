package org.openelisglobal.person;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.hibernate.mapping.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openelisglobal.BaseTestConfig;
import org.openelisglobal.patient.PatientTestConfig;
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
public class PersonServiceTest {

    @Autowired
    PersonService personService;
    private String firstName;
    private String lastName;
    private String personId;
    private String cellPhone;
    private String workPhone;
    private String email;
    private String fax;
    private String streetAddress;
    private String city;
    private String country;
    private String zip;
    private String state;

    @Before
    public void init() throws Exception {
        firstName = "Malik";
        lastName = "Sardar";
        personId = "1234";
        cellPhone = "403-678-5678";
        workPhone = "123-678-5678";
        email = "test@gmail.com";
        fax = "132412341";
        streetAddress = "123 Main St";
        city = "New York";
        country = "USA";
        zip = "90007";
        state = "NY";
    }

    @Test
    public void getAllPerson_shouldGetAllPerson() throws Exception {
        Assert.assertEquals(1, personService.getAllPersons().size());
    }

    @Test
    public void createPerson_shouldCreateNewPerson() throws Exception {
        String firstName = "John";
        String lastname = "Doe";

        Person pat = createPerson(firstName, lastname);
        // save person to the DB
        String personIdId = personService.insert(pat);
        Person savedPerson = personService.get(personIdId);

        Assert.assertEquals(1, personService.getAllPersons().size());
        Assert.assertEquals(firstName, savedPerson.getFirstName());
        Assert.assertEquals(lastname, savedPerson.getLastName());
    }

    private Person defaultPerson(){
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setStreetAddress(streetAddress);
        person.setCity(city);
        person.setState(state);
        person.setZipCode(zip);
        person.setCountry(country);
        person.setWorkPhone(workPhone);
        person.setCellPhone(cellPhone);
        person.setFax(fax);
        person.setEmail(email);

        // insert person into the database
        personService.insert(person);

        return person;
    }

    private Person createPerson(String firstName, String LastName) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(LastName);
        return person;
    }
    @Test
    public void testGetPersonByLastName() {  
        Person person = defaultPerson();
        personService.insert(person);
        Person savedPerson = personService.getPersonByLastName(lastName);
        Assert.assertEquals(lastName, savedPerson.getLastName());
    }
    @Test
    public void testGetFirstName(){
        Person person = defaultPerson();
        Assert.assertEquals(firstName, personService.getFirstName(person));
    }

    @Test
    public void testGetLastName(){
        Person person = defaultPerson();
        Assert.assertEquals(lastName, personService.getLastName(person));
    }

    @Test
    public void testGetLastFirstName(){
        Person person = defaultPerson();
        String expected = lastName + ", " + firstName;
        Assert.assertEquals(expected, personService.getLastFirstName(person));
    }

    @Test
    public void testGetCellPhone(){
        Person person = defaultPerson();
        Assert.assertEquals(cellPhone, personService.getCellPhone(person));
    }

    @Test
    public void testGetWorkPhone(){
        Person person = defaultPerson();
        Assert.assertEquals(workPhone, personService.getWorkPhone(person));
    }

    @Test
    public void testGetPersonById(){
        Person person = defaultPerson();
        personService.insert(person);
        Assert.assertEquals(firstName, personService.getPersonById(personId).getFirstName());
    }

    @Test
    public void testGetEmail(){
        Person person = defaultPerson();
        Assert.assertEquals(email, personService.getEmail(person));
    }

    @Test
    public void testGetFax(){
        Person person = defaultPerson();
        Assert.assertEquals(fax, personService.getFax(person));
    }

    @Test
    public void testGetAddressComponents() {
        
        Person person = defaultPerson();
        
        java.util.Map<String, String> expectedAddressComponents = new java.util.HashMap<>();
        expectedAddressComponents.put("City", city);
        expectedAddressComponents.put("Country",country);
        expectedAddressComponents.put("State", state);
        expectedAddressComponents.put("Street", streetAddress);
        expectedAddressComponents.put("Zip", zip);
        java.util.Map<String, String> actualAddressComponents = personService.getAddressComponents(person);

        assertEquals(expectedAddressComponents, actualAddressComponents);
    }


}
