package org.openelisglobal.patient;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.person.valueholder.Person;
import org.springframework.beans.factory.annotation.Autowired;

public class PatientServiceTest extends BaseWebContextSensitiveTest {

    @Autowired
    PatientService patientService;

    @Autowired
    PersonService personService;
    String patientId;

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
    public void createPatient_shouldCreateNewPatient() throws Exception {
        String firstName = "John";
        String lastname = "Doe";
        String dob = "12/12/1992";
        String gender = "M";
        Patient pat = createPatient(firstName, lastname, dob, gender);

        Assert.assertEquals(0, patientService.getAllPatients().size());
        // save patient to the DB
        String patientId = patientService.insert(pat);
        Patient savedPatient = patientService.get(patientId);

        Assert.assertEquals(1, patientService.getAllPatients().size());
        Assert.assertEquals("John", savedPatient.getPerson().getFirstName());
        Assert.assertEquals("Doe", savedPatient.getPerson().getLastName());
        Assert.assertEquals("M", savedPatient.getGender());
    }

    @Test
    public void getAllPatients_shouldGetAllPatients() throws Exception {
        Assert.assertEquals(1, patientService.getAllPatients().size());
    }

    private Patient createPatient(String firstName, String LastName, String birthDate, String gender)
            throws ParseException {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(LastName);
        personService.save(person);

  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  Date date = dateFormat.parse(birthDate);
  long time = date.getTime();
  Timestamp dob = new Timestamp(time);

        Patient pat = new Patient();
        pat.setPerson(person);
        pat.setBirthDate(dob);
        pat.setGender(gender);

        return pat;
    }
}
