package org.openelisglobal.samplehuman;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.person.valueholder.Person;
import org.openelisglobal.provider.service.ProviderService;
import org.openelisglobal.provider.valueholder.Provider;
import org.openelisglobal.sample.service.SampleService;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.samplehuman.valueholder.SampleHuman;
import org.springframework.beans.factory.annotation.Autowired;

public class SampleHumanServiceTest extends BaseWebContextSensitiveTest {

    @Autowired
    SampleHumanService humanService;

    @Autowired
    SampleService sampleService;

    @Autowired
    ProviderService providerService;

    @Autowired
    PatientService patientService;

    @Autowired
    PersonService personService;

    @Before
    public void init() throws Exception {
        providerService.deleteAll(providerService.getAll());
        humanService.deleteAll(humanService.getAll());
        sampleService.deleteAll(sampleService.getAll());
        patientService.deleteAll(patientService.getAll());
        personService.deleteAll(personService.getAll());
    }

    @After
    public void tearDown() throws Exception {
        providerService.deleteAll(providerService.getAll());
        humanService.deleteAll(humanService.getAll());
        sampleService.deleteAll(sampleService.getAll());
        patientService.deleteAll(patientService.getAll());
        personService.deleteAll(personService.getAll());
    }

    @Test
    public void createSampleHuman_shouldCreateNewSampleHuman() throws Exception {
        String firstname = "John";
        String lastname = "Doe";
        String firstname2 = "Jane";
        String lastname2 = "Loo";
        String birthdate = "03/06/1993";
        String accessionNumber = "12345";
        String gender = "M";
        String receivedTimestamp = "012/06/2024";
        String type = "P";
        String entereddate = "2024-06-03";

        SampleHuman sampleHuman = creatSampleHuman(firstname, lastname, firstname2, lastname2, birthdate,
                accessionNumber, gender, receivedTimestamp, type, entereddate);

        Assert.assertEquals(0, humanService.getAll().size());

        String sampleHumanId = humanService.insert(sampleHuman);
        SampleHuman savedSampleHuman = humanService.get(sampleHumanId);

        Assert.assertEquals(1, humanService.getAll().size());

    }

    @Test
    public void updateSampleHuman_shouldUpdateSampleHuman() throws Exception {
        String firstname = "John";
        String lastname = "Doe";
        String firstname2 = "Jane";
        String lastname2 = "Loo";
        String birthdate = "03/06/1993";
        String accessionNumber = "12345";
        String gender = "M";
        String receivedTimestamp = "012/06/2024";
        String type = "P";
        String entereddate = "2024-06-03";

        Person person = new Person();
        person.setFirstName(firstname);
        person.setLastName(lastname);
        personService.save(person);

        Person person2 = new Person();
        person2.setFirstName(firstname2);
        person2.setLastName(lastname2);
        personService.save(person2);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(birthdate);
        long time = date.getTime();
        Timestamp dob = new Timestamp(time);

        Patient pat = new Patient();
        pat.setBirthDate(dob);
        pat.setPerson(person);
        pat.setGender(gender);
        String patId = patientService.insert(pat);

        Provider prov = new Provider();
        prov.setPerson(person2);
        prov.setProviderType(type);
        String providerId = providerService.insert(prov);

        DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
        Date date2 = dateFormat2.parse(receivedTimestamp);
        long time2 = date2.getTime();
        Timestamp doc = new Timestamp(time2);

        java.sql.Date enteredDate = java.sql.Date.valueOf(entereddate);

        Sample samp = new Sample();
        samp.setEnteredDate(enteredDate);
        samp.setReceivedTimestamp(doc);
        samp.setAccessionNumber(accessionNumber);
        String sampId = sampleService.insert(samp);

        SampleHuman sampleHuman = new SampleHuman();
        sampleHuman.setPatientId(patId);
        sampleHuman.setProviderId(providerId);
        sampleHuman.setSampleId(sampId);

        String sampleHumanId = humanService.insert(sampleHuman);
        Person updateSamplehuman = humanService.getPatientForSample(samp).getPerson();
        updateSamplehuman.setLastName("Nakibinge");
        personService.save(updateSamplehuman);

        Assert.assertEquals("Nakibinge", humanService.getPatientForSample(samp).getPerson().getLastName());

    }

    @Test
    public void deleteSampleHuman_shouldDeleteSampleHuman() throws Exception {
        String firstname = "John";
        String lastname = "Doe";
        String firstname2 = "Jane";
        String lastname2 = "Loo";
        String birthdate = "03/06/1993";
        String accessionNumber = "12345";
        String gender = "M";
        String receivedTimestamp = "012/06/2024";
        String type = "P";
        String entereddate = "2024-06-03";

        SampleHuman sampleHuman = creatSampleHuman(firstname, lastname, firstname2, lastname2, birthdate,
                accessionNumber, gender, receivedTimestamp, type, entereddate);

        Assert.assertEquals(0, humanService.getAll().size());

        String sampleHumanId = humanService.insert(sampleHuman);
        SampleHuman savedSampleHuman = humanService.get(sampleHumanId);

        humanService.delete(savedSampleHuman);

        Assert.assertEquals(0, humanService.getAll().size());

    }

    @Test
    public void getAllPatientsWithSampleEntered_shouldReturnPatientsWithSample() throws Exception {
        String firstname = "John";
        String lastname = "Doe";
        String firstname2 = "Jane";
        String lastname2 = "Loo";
        String birthdate = "03/06/1993";
        String accessionNumber = "12345";
        String gender = "M";
        String receivedTimestamp = "012/06/2024";
        String type = "P";
        String entereddate = "2024-06-03";

        SampleHuman sampleHuman = creatSampleHuman(firstname, lastname, firstname2, lastname2, birthdate,
                accessionNumber, gender, receivedTimestamp, type, entereddate);

        Assert.assertEquals(0, humanService.getAll().size());

        String sampleHumanId = humanService.insert(sampleHuman);
        List<Patient> patients = humanService.getAllPatientsWithSampleEntered();
        ;

        Assert.assertEquals(1, patients.size());

    }

    private SampleHuman creatSampleHuman(String firstname, String lastname, String firstname2, String lastname2,
            String birthdate, String accessionNumber, String gender, String receivedTimestamp, String type,
            String entereddate) throws ParseException {
        Person person = new Person();
        person.setFirstName(firstname);
        person.setLastName(lastname);
        personService.save(person);

        Person person2 = new Person();
        person2.setFirstName(firstname2);
        person2.setLastName(lastname2);
        personService.save(person2);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(birthdate);
        long time = date.getTime();
        Timestamp dob = new Timestamp(time);

        Patient pat = new Patient();
        pat.setBirthDate(dob);
        pat.setPerson(person);
        pat.setGender(gender);
        String patId = patientService.insert(pat);

        Provider prov = new Provider();
        prov.setPerson(person2);
        prov.setProviderType(type);
        String providerId = providerService.insert(prov);

        DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
        Date date2 = dateFormat2.parse(receivedTimestamp);
        long time2 = date2.getTime();
        Timestamp doc = new Timestamp(time2);

        java.sql.Date enteredDate = java.sql.Date.valueOf(entereddate);

        Sample samp = new Sample();
        samp.setEnteredDate(enteredDate);
        samp.setReceivedTimestamp(doc);
        samp.setAccessionNumber(accessionNumber);
        String sampId = sampleService.insert(samp);

        SampleHuman sampleHuman = new SampleHuman();
        sampleHuman.setPatientId(patId);
        sampleHuman.setProviderId(providerId);
        sampleHuman.setSampleId(sampId);

        return sampleHuman;
    }

    @Test
    public void getData_shouldReturncopiedPropertiesFromDatabase() throws Exception {
        String firstname = "John";
        String lastname = "Doe";
        String firstname2 = "Jane";
        String lastname2 = "Loo";
        String birthdate = "03/06/1993";
        String accessionNumber = "12345";
        String gender = "M";
        String receivedTimestamp = "012/06/2024";
        String type = "P";
        String entereddate = "2024-06-03";

        Person person = new Person();
        person.setFirstName(firstname);
        person.setLastName(lastname);
        personService.save(person);

        Person person2 = new Person();
        person2.setFirstName(firstname2);
        person2.setLastName(lastname2);
        personService.save(person2);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(birthdate);
        long time = date.getTime();
        Timestamp dob = new Timestamp(time);

        Patient pat = new Patient();
        pat.setBirthDate(dob);
        pat.setPerson(person);
        pat.setGender(gender);
        String patId = patientService.insert(pat);

        Provider prov = new Provider();
        prov.setPerson(person2);
        prov.setProviderType(type);
        String providerId = providerService.insert(prov);

        DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
        Date date2 = dateFormat2.parse(receivedTimestamp);
        long time2 = date2.getTime();
        Timestamp doc = new Timestamp(time2);

        java.sql.Date enteredDate = java.sql.Date.valueOf(entereddate);

        Sample samp = new Sample();
        samp.setEnteredDate(enteredDate);
        samp.setReceivedTimestamp(doc);
        samp.setAccessionNumber(accessionNumber);
        String sampId = sampleService.insert(samp);

        SampleHuman sampleHuman = new SampleHuman();
        sampleHuman.setPatientId(patId);
        sampleHuman.setProviderId(providerId);
        sampleHuman.setSampleId(sampId);

        String sampleHumanId = humanService.insert(sampleHuman);

        SampleHuman sHumanToUpdate = new SampleHuman();
        sHumanToUpdate.setId(sampleHumanId);

        humanService.getData(sHumanToUpdate);
        ;

        Assert.assertEquals(providerId, sHumanToUpdate.getProviderId());

    }

    @Test
    public void getPatientForSample_shouldReturnPatientForSample() throws Exception {
        String firstname = "John";
        String lastname = "Doe";
        String firstname2 = "Jane";
        String lastname2 = "Loo";
        String birthdate = "03/06/1993";
        String accessionNumber = "12345";
        String gender = "M";
        String receivedTimestamp = "012/06/2024";
        String type = "P";
        String entereddate = "2024-06-03";

        Person person = new Person();
        person.setFirstName(firstname);
        person.setLastName(lastname);
        personService.save(person);

        Person person2 = new Person();
        person2.setFirstName(firstname2);
        person2.setLastName(lastname2);
        personService.save(person2);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(birthdate);
        long time = date.getTime();
        Timestamp dob = new Timestamp(time);

        Patient pat = new Patient();
        pat.setBirthDate(dob);
        pat.setPerson(person);
        pat.setGender(gender);
        String patId = patientService.insert(pat);

        Provider prov = new Provider();
        prov.setPerson(person2);
        prov.setProviderType(type);
        String providerId = providerService.insert(prov);

        DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
        Date date2 = dateFormat2.parse(receivedTimestamp);
        long time2 = date2.getTime();
        Timestamp doc = new Timestamp(time2);

        java.sql.Date enteredDate = java.sql.Date.valueOf(entereddate);

        Sample samp = new Sample();
        samp.setEnteredDate(enteredDate);
        samp.setReceivedTimestamp(doc);
        samp.setAccessionNumber(accessionNumber);
        String sampId = sampleService.insert(samp);

        SampleHuman sampleHuman = new SampleHuman();
        sampleHuman.setPatientId(patId);
        sampleHuman.setProviderId(providerId);
        sampleHuman.setSampleId(sampId);

        String sampleHumanId = humanService.insert(sampleHuman);
        Patient samplePatient = humanService.getPatientForSample(samp);

        Assert.assertEquals(firstname, samplePatient.getPerson().getFirstName());

    }

    @Test
    public void getSamplesForPatient_shouldReturnSamplesForPatient() throws Exception {
        String firstname = "John";
        String lastname = "Doe";
        String firstname2 = "Jane";
        String lastname2 = "Loo";
        String birthdate = "03/06/1993";
        String accessionNumber = "12345";
        String gender = "M";
        String receivedTimestamp = "012/06/2024";
        String type = "P";
        String entereddate = "2024-06-03";

        Person person = new Person();
        person.setFirstName(firstname);
        person.setLastName(lastname);
        personService.save(person);

        Person person2 = new Person();
        person2.setFirstName(firstname2);
        person2.setLastName(lastname2);
        personService.save(person2);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(birthdate);
        long time = date.getTime();
        Timestamp dob = new Timestamp(time);

        Patient pat = new Patient();
        pat.setBirthDate(dob);
        pat.setPerson(person);
        pat.setGender(gender);
        String patId = patientService.insert(pat);

        Provider prov = new Provider();
        prov.setPerson(person2);
        prov.setProviderType(type);
        String providerId = providerService.insert(prov);

        DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
        Date date2 = dateFormat2.parse(receivedTimestamp);
        long time2 = date2.getTime();
        Timestamp doc = new Timestamp(time2);

        java.sql.Date enteredDate = java.sql.Date.valueOf(entereddate);

        Sample samp = new Sample();
        samp.setEnteredDate(enteredDate);
        samp.setReceivedTimestamp(doc);
        samp.setAccessionNumber(accessionNumber);
        String sampId = sampleService.insert(samp);

        SampleHuman sampleHuman = new SampleHuman();
        sampleHuman.setPatientId(patId);
        sampleHuman.setProviderId(providerId);
        sampleHuman.setSampleId(sampId);

        String sampleHumanId = humanService.insert(sampleHuman);
        List<Sample> samples = humanService.getSamplesForPatient(patId);

        Assert.assertEquals(1, samples.size());
    }

    @Test
    public void getDataBySample_shouldReturnDataBySample() throws Exception {
        String firstname = "John";
        String lastname = "Doe";
        String firstname2 = "Jane";
        String lastname2 = "Loo";
        String birthdate = "03/06/1993";
        String accessionNumber = "12345";
        String gender = "M";
        String receivedTimestamp = "012/06/2024";
        String type = "P";
        String entereddate = "2024-06-03";

        Person person = new Person();
        person.setFirstName(firstname);
        person.setLastName(lastname);
        personService.save(person);

        Person person2 = new Person();
        person2.setFirstName(firstname2);
        person2.setLastName(lastname2);
        personService.save(person2);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(birthdate);
        long time = date.getTime();
        Timestamp dob = new Timestamp(time);

        Patient pat = new Patient();
        pat.setBirthDate(dob);
        pat.setPerson(person);
        pat.setGender(gender);
        String patId = patientService.insert(pat);

        Provider prov = new Provider();
        prov.setPerson(person2);
        prov.setProviderType(type);
        String providerId = providerService.insert(prov);

        DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
        Date date2 = dateFormat2.parse(receivedTimestamp);
        long time2 = date2.getTime();
        Timestamp doc = new Timestamp(time2);

        java.sql.Date enteredDate = java.sql.Date.valueOf(entereddate);

        Sample samp = new Sample();
        samp.setEnteredDate(enteredDate);
        samp.setReceivedTimestamp(doc);
        samp.setAccessionNumber(accessionNumber);
        String sampId = sampleService.insert(samp);

        SampleHuman sampleHuman = new SampleHuman();
        sampleHuman.setPatientId(patId);
        sampleHuman.setProviderId(providerId);
        sampleHuman.setSampleId(sampId);

        String sampleHumanId = humanService.insert(sampleHuman);

        SampleHuman sHumanToUpdate = humanService.getDataBySample(sampleHuman);

        Assert.assertEquals(patId, sHumanToUpdate.getPatientId());

    }
}
