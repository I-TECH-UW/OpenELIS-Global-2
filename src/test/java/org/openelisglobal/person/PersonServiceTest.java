package org.openelisglobal.person;

import org.dbunit.IDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openelisglobal.BaseTestConfig;
import org.openelisglobal.DbUnitUtil;
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

    DbUnitUtil dbUnitUtil;

    @Before
    public void init() throws Exception {
        dbUnitUtil = new DbUnitUtil("src/test/resources/dataSetFiles/personDataSet.Xml");

        // Set up the database connection and load dataset using DbUnitUtil
        IDatabaseTester databaseTester = new PropertiesBasedJdbcDatabaseTester();
        databaseTester.setDataSet(dbUnitUtil.getDataSet());
        databaseTester.onSetup();
    }

     @Test
    public void testingDataDataIsPopulated() {
    
        int result =personService.getAllPersons().size() ;

        // Perform assertions on the result
        Assert.assertEquals(4, result); // Example assertion
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
