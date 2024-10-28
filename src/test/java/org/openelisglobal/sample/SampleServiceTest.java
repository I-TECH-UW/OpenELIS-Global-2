package org.openelisglobal.sample;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.person.valueholder.Person;
import org.openelisglobal.sample.service.SampleService;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.samplehuman.dao.SampleHumanDAO;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.samplehuman.valueholder.SampleHuman;
import org.springframework.beans.factory.annotation.Autowired;

public class SampleServiceTest extends BaseWebContextSensitiveTest {

    @Autowired
    PersonService personService;

    @Autowired
    PatientService patientService;

    @Autowired
    SampleService sampleService;

    @Autowired
    SampleHumanService sampleHumanService;

    SampleHumanDAO sampleHumanDAO;

    @Before
    public void init() throws Exception {

        patientService.deleteAll(patientService.getAll());
        personService.deleteAll(personService.getAll());
        sampleService.deleteAll(sampleService.getAll());
        sampleHumanService.deleteAll(sampleHumanService.getAll());
    }

    @After
    public void tearDown() {
        // patientService.deleteAll(patientService.getAll());
        personService.deleteAll(personService.getAll());
        sampleService.deleteAll(sampleService.getAll());
        sampleHumanService.deleteAll(sampleHumanService.getAll());
    }

    private Sample createSample(String receivedTimestamp, String accessionNumber) throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = dateFormat.parse(receivedTimestamp);
        long time = date.getTime();
        Timestamp doc = new Timestamp(time);

        Sample sample = new Sample();
        sample.setReceivedTimestamp(doc);
        sample.setAccessionNumber(accessionNumber);

        return sample;
    }

    @Test
    public void createSample_shouldCreateNewSample() throws Exception {
        Date enteredDate = Date.valueOf("2024-06-13");
        String receivedTimestamp = "13/06/2024";
        String accessionNumber = "123";
        Sample samp = createSample(receivedTimestamp, accessionNumber);
        samp.setEnteredDate(enteredDate);

        Assert.assertEquals(0, sampleService.getAll().size());
        // save person to the DB
        String sampleId = sampleService.insert(samp);
        Sample savedSample = sampleService.get(sampleId);

        Assert.assertEquals(1, sampleService.getAll().size());
        Assert.assertEquals(accessionNumber, savedSample.getAccessionNumber());
        Assert.assertEquals("2024-06-13 00:00:00.0", savedSample.getReceivedTimestamp().toString());
    }

    @Test
    public void getAccessionNumber_shouldReturnAccessionNumber() throws Exception {
        Date enteredDate = Date.valueOf("2024-06-03");
        String receivedTimestamp = "03/06/2024";
        String accessionNumber = "12";
        Sample samp = createSample(receivedTimestamp, accessionNumber);
        samp.setEnteredDate(enteredDate);

        Assert.assertEquals(0, sampleService.getAll().size());
        // save person to the DB
        String sampleId = sampleService.insert(samp);
        Sample savedSample = sampleService.get(sampleId);
        Assert.assertEquals(accessionNumber, savedSample.getAccessionNumber());
    }

    @Test
    public void getSampleByAccessionNumber_shouldReturnSampleByAccessionNumber() throws Exception {
        Date enteredDate = Date.valueOf("2024-06-03");
        String receivedTimestamp = "03/06/2024";
        String accessionNumber = "12";
        Sample samp = createSample(receivedTimestamp, accessionNumber);
        samp.setEnteredDate(enteredDate);

        Assert.assertEquals(0, sampleService.getAll().size());
        // save person to the DB
        String sampleId = sampleService.insert(samp);
        Sample savedSample = sampleService.getSampleByAccessionNumber(accessionNumber);
        Assert.assertEquals("2024-06-03 00:00:00.0", savedSample.getReceivedTimestamp().toString());
    }

    @Test
    public void insertDataWithAccessionNumber_shouldReturnsampleWithInsertedData() throws Exception {
        Sample sample = new Sample();
        Date enteredDate = Date.valueOf("2024-06-03");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = dateFormat.parse("03/06/2024");
        long time = date.getTime();
        Timestamp doc = new Timestamp(time);
        sample.setAccessionNumber("43");
        sample.setReceivedTimestamp(doc);
        sample.setEnteredDate(enteredDate);

        String sampId = sampleService.insert(sample);
        Sample savedSample = sampleService.getSampleByAccessionNumber("43");
        savedSample.setEnumName("HIV4");
        sampleService.update(savedSample);

        Assert.assertEquals("HIV4", savedSample.getEnumName());

    }

    @Test
    public void getOrderedDate_shouldReturnOrderedDate() throws Exception {
        Date enteredDate = Date.valueOf("2024-06-03");
        String receivedTimestamp = "03/06/2024";
        String accessionNumber = "12";
        Sample samp = createSample(receivedTimestamp, accessionNumber);
        samp.setEnteredDate(enteredDate);

        String sampleId = sampleService.insert(samp);
        Sample savedSample = sampleService.get(sampleId);
        Assert.assertEquals("2024-06-03 00:00:00.0", sampleService.getOrderedDate(savedSample).toString());
    }

    @Test
    public void getSamplesReceivedOn_shouldReturnSamplesOnDate() throws Exception {
        Date enteredDate = Date.valueOf("2024-06-03");
        Date recievedDate = Date.valueOf("2024-06-04");
        String receivedTimestamp = "03/06/2024";
        String accessionNumber = "12";
        Sample samp = createSample(receivedTimestamp, accessionNumber);
        samp.setEnteredDate(enteredDate);
        samp.setReceivedDate(recievedDate);

        String sampleId = sampleService.insert(samp);
        int receivedSamples = sampleService.getSamplesReceivedOn("04/06/2024").size();
        Assert.assertEquals(1, receivedSamples);
    }

    // @Test
    // public void getSamplesForPatient_shouldReturnSamplesForPatient() throws Exception {

    //     Date enteredDate = Date.valueOf("2024-06-03");
    //     String receivedTimestamp = "03/06/2024";
    //     String accessionNumber = "12";
    //     Sample samp = createSample(receivedTimestamp, accessionNumber);
    //     samp.setEnteredDate(enteredDate);
    //     String sampleId = sampleService.insert(samp);

    //     Person person = new Person();
    //     person.setFirstName("kasozi");
    //     person.setLastName("paulaaa");
    //     personService.save(person);

    //     DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    //     java.util.Date date = dateFormat.parse("03/06/2024");
    //     long time = date.getTime();
    //     Timestamp dob = new Timestamp(time);

    //     Patient pat = new Patient();
    //     pat.setPerson(person);
    //     pat.setBirthDate(dob);
    //     pat.setGender("M");
    //     String patId = patientService.insert(pat);

    //     SampleHuman human = new SampleHuman();
    //     human.setSampleId(sampleId);
    //     human.setPatientId(patId);
    //     String humanId = sampleHumanService.insert(human);

    //     Assert.assertEquals(1, sampleHumanService.getSamplesForPatient(patId).size());

    // }

    @Test
    public void getReceivedDateForDisplay_shouldReturnReceivedDateForDisplay() throws Exception {
        Date enteredDate = Date.valueOf("2024-06-03");
        Date recievedDate = Date.valueOf("2024-06-04");
        String receivedTimestamp = "03/06/2024";
        String accessionNumber = "12";
        Sample samp = createSample(receivedTimestamp, accessionNumber);
        samp.setEnteredDate(enteredDate);
        samp.setReceivedDate(recievedDate);
        samp.setReceivedDateForDisplay("04/06/2024");

        String sampleId = sampleService.insert(samp);
        Sample savedSample = sampleService.get(sampleId);

        Assert.assertEquals("04/06/2024", sampleService.getReceivedDateForDisplay(savedSample));
    }

    @Test
    public void getReceived24HourTimeForDisplay_shouldReturnReceived24HourTimeForDisplay() throws Exception {
        Date enteredDate = Date.valueOf("2024-06-03");
        String receivedTimestamp = "03/06/2024";
        String accessionNumber = "12";
        Sample samp = createSample(receivedTimestamp, accessionNumber);
        samp.setEnteredDate(enteredDate);

        String sampleId = sampleService.insert(samp);
        Sample savedSample = sampleService.get(sampleId);
        Assert.assertEquals("00:00", sampleService.getReceived24HourTimeForDisplay(savedSample));
    }

    @Test
    public void getReceivedTimeForDisplay_shouldReturnReceivedTimeForDisplay() throws Exception {
        Date enteredDate = Date.valueOf("2024-06-03");
        String receivedTimestamp = "03/06/2024";
        String accessionNumber = "12";
        Sample samp = createSample(receivedTimestamp, accessionNumber);
        samp.setEnteredDate(enteredDate);
        samp.setReceivedDateForDisplay("04/06/2024");

        String sampleId = sampleService.insert(samp);
        Sample savedSample = sampleService.get(sampleId);

        Assert.assertEquals("00:00", sampleService.getReceivedTimeForDisplay(savedSample));
    }

    @Test
    public void isConfirmationSample_shouldReturnisConfirmationSample() throws Exception {
        Date enteredDate = Date.valueOf("2024-06-03");
        String receivedTimestamp = "03/06/2024";
        String accessionNumber = "12";
        Sample samp = createSample(receivedTimestamp, accessionNumber);
        samp.setEnteredDate(enteredDate);
        samp.setIsConfirmation(true);

        String sampleId = sampleService.insert(samp);
        Sample savedSample = sampleService.get(sampleId);

        assertFalse(sampleService.isConfirmationSample(null));
        assertTrue(sampleService.isConfirmationSample(savedSample));
    }

    @Test
    public void getReceivedDateWithTwoYearDisplay_shouldReturnReceivedDateWithTwoYearDisplay() throws Exception {
        Date enteredDate = Date.valueOf("2024-06-03");
        String receivedTimestamp = "03/06/2024";
        String accessionNumber = "12";
        Sample samp = createSample(receivedTimestamp, accessionNumber);
        samp.setEnteredDate(enteredDate);

        String sampleId = sampleService.insert(samp);
        Sample savedSample = sampleService.get(sampleId);
        Assert.assertEquals("03/06/24", sampleService.getReceivedDateWithTwoYearDisplay(savedSample));
    }

    @Test
    public void getConfirmationSamplesReceivedInDateRange_shouldReturnConfirmationSamplesReceivedInDateRange()
            throws Exception {
        Date recievedDateStart = Date.valueOf("2024-06-03");
        Date recievedDateEnd = Date.valueOf("2024-06-04");
        Date enteredDate = Date.valueOf("2024-06-03");
        String receivedTimestamp = "03/06/2024";
        String accessionNumber = "12";
        Sample samp = createSample(receivedTimestamp, accessionNumber);
        samp.setEnteredDate(enteredDate);
        samp.setIsConfirmation(true);

        String sampleId = sampleService.insert(samp);
        Sample savedSample = sampleService.get(sampleId);
        Assert.assertEquals(1,
                sampleService.getConfirmationSamplesReceivedInDateRange(recievedDateStart, recievedDateEnd).size());
    }

    @Test
    public void getSamplesCollectedOn_shouldReturnSamplesCollected() throws Exception {
        Date enteredDate = Date.valueOf("2024-06-03");
        String receivedTimestamp = "03/06/2024";
        String accessionNumber = "123";
        String collectionDate = "03/06/2024";

        Date enteredDate2 = Date.valueOf("2024-06-04");
        String receivedTimestamp2 = "03/06/2024";
        String accessionNumber2 = "312";

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = dateFormat.parse(collectionDate);
        long time = date.getTime();
        Timestamp doc = new Timestamp(time);

        Sample samp = createSample(receivedTimestamp, accessionNumber);
        samp.setEnteredDate(enteredDate);
        samp.setCollectionDate(doc);
        String sampleId = sampleService.insert(samp);

        DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date2 = dateFormat2.parse(collectionDate);
        long time2 = date2.getTime();
        Timestamp doc2 = new Timestamp(time2);

        Sample samp2 = createSample(receivedTimestamp2, accessionNumber2);
        samp2.setEnteredDate(enteredDate2);
        samp2.setCollectionDate(doc2);
        String sampleId2 = sampleService.insert(samp2);
        Assert.assertEquals(2, sampleService.getSamplesCollectedOn(collectionDate).size());
    }

    @Test
    public void getLargestAccessionNumber_shouldReturnLargestAccessionNumber() throws Exception {
        Date enteredDate = Date.valueOf("2024-06-03");
        String receivedTimestamp = "03/06/2024";
        String accessionNumber = "123";

        Date enteredDate2 = Date.valueOf("2024-06-04");
        String receivedTimestamp2 = "03/06/2024";
        String accessionNumber2 = "312";

        Sample samp = createSample(receivedTimestamp, accessionNumber);
        samp.setEnteredDate(enteredDate);
        String sampleId = sampleService.insert(samp);

        Sample samp2 = createSample(receivedTimestamp2, accessionNumber2);
        samp2.setEnteredDate(enteredDate2);
        String sampleId2 = sampleService.insert(samp2);
        Assert.assertEquals(accessionNumber2, sampleService.getLargestAccessionNumber());
    }

    @Test
    public void getSamplesReceivedInDateRange_shouldReturnSamplesReceivedInDateRange() throws Exception {
        String recievedDateStart = "03/06/2024";
        String recievedDateEnd = "04/06/2024";
        Date enteredDate = Date.valueOf("2024-06-03");
        String receivedTimestamp = "03/06/2024";
        String accessionNumber = "12";
        Sample samp = createSample(receivedTimestamp, accessionNumber);
        samp.setEnteredDate(enteredDate);

        String sampleId = sampleService.insert(samp);
        Sample savedSample = sampleService.get(sampleId);
        Assert.assertEquals(1, sampleService.getSamplesReceivedInDateRange(recievedDateStart, recievedDateEnd).size());
    }

    @Test
    public void getSamplesByAccessionRange_shouldReturnSamplesByAccessionRange() throws Exception {
        Date enteredDate = Date.valueOf("2024-06-03");
        String receivedTimestamp = "03/06/2024";
        String accessionNumber = "123";

        Date enteredDate2 = Date.valueOf("2024-06-04");
        String receivedTimestamp2 = "03/06/2024";
        String accessionNumber2 = "312";

        Sample samp = createSample(receivedTimestamp, accessionNumber);
        samp.setEnteredDate(enteredDate);
        String sampleId = sampleService.insert(samp);

        Sample samp2 = createSample(receivedTimestamp2, accessionNumber2);
        samp2.setEnteredDate(enteredDate2);
        String sampleId2 = sampleService.insert(samp2);
        Assert.assertEquals(2, sampleService.getSamplesByAccessionRange(accessionNumber, accessionNumber2).size());
    }

    @Test
    public void getId_shouldReturnId() throws Exception {
        Date enteredDate = Date.valueOf("2024-06-03");
        String receivedTimestamp = "03/06/2024";
        String accessionNumber = "12";
        Sample samp = createSample(receivedTimestamp, accessionNumber);
        samp.setEnteredDate(enteredDate);

        String sampleId = sampleService.insert(samp);
        Sample savedSample = sampleService.get(sampleId);
        Assert.assertEquals(sampleId, sampleService.getId(savedSample));
    }
}
