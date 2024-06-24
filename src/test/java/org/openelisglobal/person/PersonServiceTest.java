package org.openelisglobal.person;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.person.valueholder.Person;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonServiceTest extends BaseWebContextSensitiveTest {

  @Autowired PersonService personService;

  String personIdId;

  @Before
  public void init() throws Exception {
    String firstName = "John";
    String lastname = "Doe";
    String phone = "12345";
    String workPhone = "6789";
    String cell = "09876";
    String primaryPhone = "12345";
    String fax = "123";
    String email = "asd@sdf.com";
    String city = "Kampala";
    String country = "Uganda";
    String zip = "256";

    Person pat =
        createPerson(
            firstName,
            lastname,
            phone,
            workPhone,
            cell,
            primaryPhone,
            fax,
            email,
            city,
            country,
            zip);

    // save person to the DB
    personIdId = personService.insert(pat);
  }

  @Test
  public void createPerson_shouldCreateNewPerson() throws Exception {
    Person savedPerson = personService.get(personIdId);

    // Assert.assertEquals(1, personService.getAllPersons().size());
    Assert.assertEquals("John", savedPerson.getFirstName());
    Assert.assertEquals("Doe", savedPerson.getLastName());
  }

  @Test
  public void getAllPerson_shouldGetAllPerson() throws Exception {
    Assert.assertEquals(1, personService.getAllPersons().size());
  }

  @Test
  public void getLastFirstName_ShouldReturnLastAndFisrtName() {
    Person savedPerson = personService.get(personIdId);
    Assert.assertEquals("Doe, John", personService.getLastFirstName(savedPerson));
  }

  @Test
  public void getPhone_shouldReturnPhoneContact() {
    Person savedPerson = personService.get(personIdId);
    Assert.assertEquals("12345", personService.getPhone(savedPerson));
  }

  @Test
  public void getCellPhone_shouldReturnCellContact() {
    Person savedPerson = personService.get(personIdId);
    Assert.assertEquals("09876", personService.getCellPhone(savedPerson));
  }

  @Test
  public void getworkPhone_shouldReturnworkPhoneContact() {
    Person savedPerson = personService.get(personIdId);
    Assert.assertEquals("6789", personService.getWorkPhone(savedPerson));
  }

  @Test
  public void getfax_shouldReturnFaxNumber() {
    Person savedPerson = personService.get(personIdId);
    Assert.assertEquals("123", personService.getFax(savedPerson));
  }

  @Test
  public void getEmail_shouldReturnworkEmail() {
    Person savedPerson = personService.get(personIdId);
    Assert.assertEquals("asd@sdf.com", personService.getEmail(savedPerson));
  }

  private Person createPerson(
      String firstName,
      String LastName,
      String phone,
      String workPhone,
      String cell,
      String primaryPhone,
      String fax,
      String email,
      String city,
      String country,
      String zip) {
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

    return person;
  }
}
