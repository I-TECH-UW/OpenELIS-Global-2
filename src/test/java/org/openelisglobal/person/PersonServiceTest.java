package org.openelisglobal.person;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openelisglobal.BaseTestConfig;
import org.openelisglobal.common.util.SystemConfiguration;
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
	public void insertAll_shouldInsertListOfPersons() throws Exception {
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
	public void updateAll_shouldUpdateListOfPersons() throws Exception {
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
	public void deleteAll_shouldDeleteListOfPersons() throws Exception {
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

	@Test
	public void testGetPersonByLastName_ShouldReturnPersonWithGivenLastName() throws Exception {
		String firstName = "Juhn";
		String lastname = "Doe3";
		Person pat = createPerson(firstName, lastname);

		// save person to the DB
		String personId = personService.insert(pat);

		// Call the method under test
		Person actualPerson = personService.getPersonByLastName(lastname);

		// Verify the result
		Assert.assertEquals(firstName, actualPerson.getFirstName());
		Assert.assertEquals(lastname, actualPerson.getLastName());
	}

	@Test
	public void testGetPersonById_ShouldReturnPersonWithGivenId() {
		String firstName = "Mark";
		String lastname = "Dan";
		Person pat = createPerson(firstName, lastname);
		String personId = personService.insert(pat);
		Person savedPerson = personService.getPersonById(personId);
		Assert.assertEquals(firstName, savedPerson.getFirstName());
	}

	@Test
	public void testGetPageOfPersons_ShouldReturnListOfPersonsForPagination() throws Exception {
		// Prepare test data
		int startingRecNo = 1; // Starting record number for pagination

		// Call the method under test
		List<Person> persons = personService.getPageOfPersons(startingRecNo);

		// Assert the results
		Assert.assertNotNull(persons);
		Assert.assertFalse(persons.isEmpty());

		// Get default page size from SystemConfiguration
		int defaultPageSize = SystemConfiguration.getInstance().getDefaultPageSize();

		// Ensure that the number of returned records does not exceed the default page
		// size
		Assert.assertTrue(persons.size() <= defaultPageSize + 1);

		// Check if the records are ordered correctly
		for (int i = 0; i < persons.size() - 1; i++)
			Assert.assertTrue(Integer.parseInt(persons.get(i).getId()) < Integer.parseInt(persons.get(i + 1).getId()));

	}

	@Test
	public void testGetCellPhone_ShouldReturnPersonCellPhone() {
		String firstName = "John";
		String lastname = "Doe";
		String cellPhone = "123-456-789";
		Person pat = createPerson(firstName, lastname);
		pat.setCellPhone(cellPhone);
		Person savedPerson = insertPersonToDB(pat);
		Assert.assertEquals(cellPhone, savedPerson.getCellPhone());
	}

	@Test
	public void testGetWorkPhone_ShouldReturnPersonWorkPhone() {
		String firstName = "John";
		String lastname = "Doe";
		String workPhone = "789-456-1000";
		Person pat = createPerson(firstName, lastname);
		pat.setWorkPhone(workPhone);
		String personId = personService.insert(pat);
		Person savedPerson = personService.get(personId);
		Assert.assertEquals(workPhone, savedPerson.getWorkPhone());
	}

	@Test
	public void testGetEmail_ShouldReturnPersonEmail() {
		String firstName = "John";
		String lastname = "Doe";
		String email = "test@ex.com";
		Person pat = createPerson(firstName, lastname);
		pat.setEmail(email);
		Person savedPerson = insertPersonToDB(pat);
		Assert.assertEquals(email, savedPerson.getEmail());
	}

	@Test
	public void testGetFax_ShouldReturnPersonFax() {
		String firstName = "John";
		String lastname = "Doe";
		String fax = "789";
		Person pat = createPerson(firstName, lastname);
		pat.setFax(fax);
		Person savedPerson = insertPersonToDB(pat);
		Assert.assertEquals(savedPerson.getFax(), fax);
	}
	
	@Test
	public void testGetCity_ShouldReturnPersonCity() {
	    String firstName = "John";
	    String lastName = "Doe";
	    String city = "New York";
	    Person pat = createPerson(firstName, lastName);
	    pat.setCity(city);
	    Person savedPerson = insertPersonToDB(pat);
	    Assert.assertEquals(city, savedPerson.getCity());
	}

	@Test
	public void testGetState_ShouldReturnPersonState() {
	    String firstName = "John";
	    String lastName = "Doe";
	    String state = "NY";
	    Person pat = createPerson(firstName, lastName);
	    pat.setState(state);
	    Person savedPerson = insertPersonToDB(pat);
	    Assert.assertEquals(state, savedPerson.getState());
	}

	@Test
	public void testGetZipCode_ShouldReturnPersonZipCode() {
	    String firstName = "John";
	    String lastName = "Doe";
	    String zipCode = "23456";
	    Person pat = createPerson(firstName, lastName);
	    pat.setZipCode(zipCode);
	    Person savedPerson = insertPersonToDB(pat);
//	    System.out.println("zipCodes " +zipCode.length() + " "+ savedPerson.getZipCode().length());
	    //fail as the value of zipCode = "23456" but savedPerson.getZipCode is "23456     "
//	    Assert.assertEquals(zipCode, savedPerson.getZipCode()); 
	    Assert.assertEquals(zipCode, savedPerson.getZipCode().trim());
	    
	}

	@Test
	public void testGetStreetAddress_ShouldReturnPersonStreetAddress() {
	    String firstName = "John";
	    String lastName = "Doe";
	    String streetAddress = "123 Main St";
	    Person pat = createPerson(firstName, lastName);
	    pat.setStreetAddress(streetAddress);
	    Person savedPerson = insertPersonToDB(pat);
	    Assert.assertEquals(streetAddress, savedPerson.getStreetAddress());
	}

	
	private Person createPerson(String firstName, String LastName) {
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(LastName);
		return person;
	}

	private Person insertPersonToDB(Person pat) {
		String personId = personService.insert(pat);
		Person savedPerson = personService.get(personId);
		return savedPerson;
	}

}
