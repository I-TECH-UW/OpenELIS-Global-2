package org.openelisglobal.person;

import java.util.ArrayList;
import java.util.List;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BaseTestConfig.class, PatientTestConfig.class })
@TestPropertySource("classpath:common.properties")
@ActiveProfiles("test")
public class PersonServiceTest {

	@Autowired
	PersonService personService;

	private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

	@Before
	public void init() throws Exception {
	}

	@Test
	public void createPerson_shouldCreateNewPerson() throws Exception {
		String firstName = "John";
		String lastname = "Doe";

		Person pat = createPerson(firstName, lastname);

		// Assert.assertEquals(0, personService.getAllPersons().size());
		// save person to the DB
		String personIdId = personService.insert(pat);
		Person savedPerson = personService.get(personIdId);

		// Assert.assertEquals(1, personService.getAllPersons().size());
		Assert.assertEquals(firstName, savedPerson.getFirstName());
		Assert.assertEquals(lastname, savedPerson.getLastName());
	}

	@Test
	public void testInsertAll() throws Exception {
		List<Person> persons = new ArrayList<>();
		persons.add(createPerson("John", "Doe"));
		persons.add(createPerson("Jane", "Smith"));

		List<String> returnedIds = personService.insertAll(persons);
		Person savedPersonOne = personService.get(returnedIds.get(0));
		Person savedPersonTwo = personService.get(returnedIds.get(1));

		Assert.assertEquals(savedPersonOne.getId(), returnedIds.get(0));
		Assert.assertEquals("John", savedPersonOne.getFirstName());

		Assert.assertEquals(savedPersonTwo.getId(), returnedIds.get(1));
		Assert.assertEquals("Jane", savedPersonTwo.getFirstName());

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
	public void testUpdateAll() throws Exception {
		// Create a list of Person objects
		List<Person> persons = new ArrayList<>();
		persons.add(createPerson("John", "Doe"));
		persons.add(createPerson("Jane", "Smith"));

		// Insert the persons into the database to get their IDs
		personService.insertAll(persons);

		// Modify some details of the Person objects
		persons.get(0).setFirstName("John Updated");
		persons.get(1).setFirstName("Jane Updated");

		// Call the method under test
		List<Person> updatedPersons = personService.updateAll(persons);

		// Verify that the size of the returned list matches the size of the input list
		Assert.assertEquals(persons.size(), updatedPersons.size());

		// Verify that each Person object in the returned list has been updated
		for (int i = 0; i < persons.size(); i++) {
			Assert.assertEquals(persons.get(i).getId(), updatedPersons.get(i).getId());
			Assert.assertEquals(persons.get(i).getFirstName(), updatedPersons.get(i).getFirstName());
		}
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
		Assert.assertNotNull("Ensure that person is exist", personService.get(personId));

		// Call the delete method

		personService.delete(personId, person.getSysUserId());
		Person deletedPerson = null;
		try {
			deletedPerson = personService.get(personId);
		} catch (ObjectNotFoundException e) {
			logger.error("Person not found with ID: {}", personId, e);
		}

		// Assert that the retrieved person is null, indicating successful deletion
		Assert.assertNull("Deleted person should be null", deletedPerson);
	}

	@Test
	public void testDeleteAll() throws Exception {
		// Create a list of Person objects
		List<Person> persons = new ArrayList<>();
		persons.add(createPerson("John", "Doe"));
		persons.add(createPerson("Jane", "Smith"));

		// Insert the persons into the database to get their IDs
		List<String> insertedIds = personService.insertAll(persons);

		// Call the deleteAll method with the inserted IDs
		personService.deleteAll(insertedIds, "sysUserId");

		// Verify that all persons have been deleted
		for (String id : insertedIds) {
			// Try to retrieve the deleted person by ID
			Person deletedPerson = null;
			try {
				deletedPerson = personService.get(id);
			} catch (ObjectNotFoundException e) {
				// Log error if person not found (expected behavior after deletion)
				logger.error("Person not found with ID: {}", id, e);
			}

			// Assert that the retrieved person is null, indicating successful deletion
			Assert.assertNull("Deleted person should be null", deletedPerson);
		}
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
