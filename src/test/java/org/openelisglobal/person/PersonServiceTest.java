package org.openelisglobal.person;

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

    @Before
    public void init() throws Exception {
    }

    @Test
    public void createPerson_shouldCreateNewPerson() throws Exception {
        String firstName = "John";
        String lastname = "Doe";
        String cellPhone= "123456";
        String homePhone= "789012";
        String email= "man@gmail.com";
        String fax ="123 4444";
        String workPhone= "0987654";
        String city ="mawokota";
        String country = "uganda";
        String streetAdress = "kisumali";
        String zipCode = "256";
        String state = "bugiri";


        

        Person pat = createPerson(firstName, lastname, cellPhone, homePhone, email, fax, workPhone, city, country, streetAdress, zipCode, state);

        //Assert.assertEquals(0, personService.getAllPersons().size());
        // save person to the DB
        String personIdId = personService.insert(pat);
        Person savedPerson = personService.get(personIdId);

       // Assert.assertEquals(1, personService.getAllPersons().size());
        Assert.assertEquals(firstName, savedPerson.getFirstName());
        Assert.assertEquals(lastname, savedPerson.getLastName());
        Assert.assertEquals(cellPhone, savedPerson.getCellPhone());
        Assert.assertEquals(homePhone, savedPerson.getHomePhone());
        Assert.assertEquals(email, savedPerson.getEmail());
        Assert.assertEquals(fax, savedPerson.getFax());
        Assert.assertEquals(workPhone, savedPerson.getWorkPhone());
        Assert.assertEquals(city, savedPerson.getCity());
        Assert.assertEquals(country, savedPerson.getCountry());

    }

    @Test
    public void getAllPerson_shouldGetAllPerson() throws Exception {
        Assert.assertEquals(1, personService.getAllPersons().size());
    }

      @Test
    public void getFirstName_shouldGetFirstName() throws Exception {

        String firstName = "John";
        String lastname = "Doe";
        String cellPhone= "123456";
        String homePhone= "789012";
        String email= "man@gmail.com";
        String fax ="123 4444";
        String workPhone= "0987654";
        String city ="mawokota";
        String country = "uganda";
        String streetAdress = "kisumali";
        String zipCode = "256";
        String state = "bugiri";


        

        Person pat = createPerson(firstName, lastname, cellPhone, homePhone, email, fax, workPhone, city, country, streetAdress, zipCode, state);

        Assert.assertEquals("John", personService.getFirstName(pat));
    }

      @Test
    public void getLastName_shouldGetLastName() throws Exception {

        String firstName = "John";
        String lastname = "Doe";
        String cellPhone= "123456";
        String homePhone= "789012";
        String email= "man@gmail.com";
        String fax ="123 4444";
        String workPhone= "0987654";
        String city ="mawokota";
        String country = "uganda";
        String streetAdress = "kisumali";
        String zipCode = "256";
        String state = "bugiri";


        

        Person pat = createPerson(firstName, lastname, cellPhone, homePhone, email, fax, workPhone, city, country, streetAdress, zipCode, state);

        Assert.assertEquals("Doe", personService.getLastName(pat));
    }

    private Person createPerson(String firstName, String LastName, String cellPhone, String homePhone, String email, 
    String fax, String workPhone, String city, String country, String streetAdress, String zipCode, String state) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(LastName);
        person.setCellPhone(cellPhone);
        person.setHomePhone(homePhone);
        person.setEmail(email);
        person.setFax(fax);
        person.setWorkPhone(workPhone);
        person.setCity(city);
        person.setCountry(country);
        person.setStreetAddress(streetAdress);
        person.setZipCode(zipCode);
        person.setState(state);
        return person;
    }

}
