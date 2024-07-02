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
      String firstName = "John";
      String lastname = "Doe";
      String dob = "12/12/1992";
      String gender = "M";
      String nId = "NID45";
      String race = "Black";
      Patient pat = createPatient(firstName, lastname, dob, gender, race, nId);
  
      // save patient to the DB
      patientId = patientService.insert(pat);
      
    }

    @Test
    public void createPatient_shouldCreateNewPatient() throws Exception {
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


    @Test
    public void getNationalId_shouldReturnNationalId() throws Exception{
        Patient savedPatient = patientService.get(patientId);

        Assert.assertEquals("NID45", savedPatient.getNationalId());

    }
    @Test
    public void getPatientByNationalId_shouldGetAllPatientsByNationalId(){
        String nId = "NID45";

        Assert.assertEquals("John", patientService.getPatientByNationalId(nId).getPerson().getFirstName());

    }

    @Test
    public void getPatientsByNationalId_shouldGetAllPatientsByNationalId(){
        String nId = "NID45";

        Assert.assertEquals(1, patientService.getPatientsByNationalId(nId).size());

    }

    private Patient createPatient(String firstName, String LastName, String birthDate, String gender, String race, String nId)
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
  pat.setRace(race);
  pat.setNationalId(nId);

  return pat;
}

}
