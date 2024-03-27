package org.openelisglobal.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.Map;

import org.hibernate.ObjectNotFoundException;
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

        Person pat = createPerson(firstName, lastname);

        //Assert.assertEquals(0, personService.getAllPersons().size());
        // save person to the DB
        String personIdId = personService.insert(pat);
        Person savedPerson = personService.get(personIdId);

       // Assert.assertEquals(1, personService.getAllPersons().size());
        Assert.assertEquals(firstName, savedPerson.getFirstName());
        Assert.assertEquals(lastname, savedPerson.getLastName());
    }
    
    @Test
    public void updatePerson_shouldUpdateExistingPerson() throws Exception {
        String firstName = "Sayed";
        String lastName = "Abdo";

        Person person = createPerson(firstName, lastName);
        String personId = personService.insert(person);

        // Modify some details of the person
        String newFirstName = "UpdateSayed";
        person.setFirstName(newFirstName);

        personService.update(person);

        Person updatedPerson = personService.get(personId);
        Assert.assertEquals(newFirstName, updatedPerson.getFirstName());
        Assert.assertEquals(lastName, updatedPerson.getLastName());
    }
    
    @Test
    public void deletePerson_shouldDeleteExistingPerson() throws Exception {
        String firstName = "Delete";
        String lastName = "Me";
        String id = "1";

        // Create a person object
        Person person = createPerson(firstName, lastName);
        person.setSysUserId(id);

        // Insert person into the database
        String personId = personService.insert(person);
      
        // Ensure that the person exists in the database
        assertNotNull("Ensure that person is exist", personService.get(personId));

        // Call the delete method
        
        personService.delete(personId, person.getSysUserId());
        Person deletedPerson = null;
        try {
            deletedPerson = personService.get(personId);
        } catch (ObjectNotFoundException e) {
           
        }

        // Assert that the retrieved person is null, indicating successful deletion
        assertNull("Deleted person should be null", deletedPerson);
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
    


}
