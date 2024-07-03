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
        String patientId1 =  patientService.insert(patient1);
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
}
