package org.openelisglobal.search;

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
import org.openelisglobal.common.provider.query.PatientSearchResults;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.person.valueholder.Person;
import org.openelisglobal.search.service.SearchResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class SearchResultsServiceTest extends BaseWebContextSensitiveTest {

    @Autowired
    PatientService patientService;

    @Autowired
    PersonService personService;

    @Autowired
    @Qualifier("DBSearchResultsServiceImpl")
    SearchResultsService DBSearchResultsServiceImpl;

    @Autowired
    @Qualifier("luceneSearchResultsServiceImpl")
    SearchResultsService luceneSearchResultsServiceImpl;

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
    public void getSearchResults_shouldGetSearchResultsFromDB() throws Exception {
        String firstName = "John";
        String lastname = "Doe";
        String dob = "12/12/1992";
        String gender = "M";
        Patient pat = createPatient(firstName, lastname, dob, gender);
        String patientId = patientService.insert(pat);

        String searchFirstName = "Jo";
        String searchLastName = "Do";

        List<PatientSearchResults> searchResults = DBSearchResultsServiceImpl.getSearchResults(searchLastName,
                searchFirstName, null, null, null, null, null, null, dob, gender);

        Assert.assertEquals(1, searchResults.size());
        PatientSearchResults result = searchResults.get(0);
        Assert.assertEquals(patientId, result.getPatientID());
        Assert.assertEquals(firstName, result.getFirstName());
        Assert.assertEquals(lastname, result.getLastName());
        Assert.assertEquals(dob, result.getBirthdate());
    }

    @Test
    public void getSearchResultsExact_shouldGetExactSearchResultsFromDB() throws Exception {
        String firstName = "John";
        String lastname = "Doe";
        String dob = "12/12/1992";
        String gender = "M";
        Patient pat = createPatient(firstName, lastname, dob, gender);
        String patientId = patientService.insert(pat);

        List<PatientSearchResults> searchResults = DBSearchResultsServiceImpl.getSearchResultsExact(lastname, firstName,
                null, null, null, null, null, null, dob, gender);

        Assert.assertEquals(1, searchResults.size());
        PatientSearchResults result = searchResults.get(0);
        Assert.assertEquals(patientId, result.getPatientID());
        Assert.assertEquals(firstName, result.getFirstName());
        Assert.assertEquals(lastname, result.getLastName());
        Assert.assertEquals(dob, result.getBirthdate());
    }

    @Test
    public void getSearchResults_shouldGetSearchResultsFromLuceneIndexes() throws Exception {
        String firstName = "John";
        String lastname = "Doe";
        String dob = "12/12/1992";
        String gender = "M";
        Patient pat = createPatient(firstName, lastname, dob, gender);
        String patientId = patientService.insert(pat);

        String searchFirstName = "Johm";
        String searchLastName = "Doee";

        List<PatientSearchResults> searchResults = luceneSearchResultsServiceImpl.getSearchResults(searchLastName,
                searchFirstName, null, null, null, null, null, null, dob, gender);

        Assert.assertEquals(1, searchResults.size());
        PatientSearchResults result = searchResults.get(0);
        Assert.assertEquals(patientId, result.getPatientID());
        Assert.assertEquals(firstName, result.getFirstName());
        Assert.assertEquals(lastname, result.getLastName());
        Assert.assertEquals(dob, result.getBirthdate());
    }

    @Test
    public void getSearchResultsExact_shouldGetExactSearchResultsFromLuceneIndexes() throws Exception {
        String firstName = "John";
        String lastname = "Doe";
        String dob = "12/12/1992";
        String gender = "M";
        Patient pat = createPatient(firstName, lastname, dob, gender);
        String patientId = patientService.insert(pat);

        List<PatientSearchResults> searchResults = luceneSearchResultsServiceImpl.getSearchResultsExact(lastname,
                firstName, null, null, null, null, null, null, dob, gender);

        Assert.assertEquals(1, searchResults.size());
        PatientSearchResults result = searchResults.get(0);
        Assert.assertEquals(patientId, result.getPatientID());
        Assert.assertEquals(firstName, result.getFirstName());
        Assert.assertEquals(lastname, result.getLastName());
        Assert.assertEquals(dob, result.getBirthdate());
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
