package org.openelisglobal.search;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.hibernate.search.massindexer.MassIndexerService;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.person.valueholder.Person;
import org.springframework.beans.factory.annotation.Autowired;

public class MassIndexerServiceTest extends BaseWebContextSensitiveTest {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    MassIndexerService massIndexerService;

    @Autowired
    PatientService patientService;

    @Autowired
    PersonService personService;

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
    @Transactional
    public void reindex_shouldReindexEntities() throws Exception {
        String firstName = "John";
        String lastname = "Doe";
        String dob = "12/12/1992";
        String gender = "M";
        Patient pat = createPatient(firstName, lastname, dob, gender);
        patientService.insert(pat);

        SearchSession searchSession = Search.session(entityManager);
        // remove all entities from the Lucene index but not from the database
        searchSession.workspace(Object.class).purge();
        long totalHitCount = searchSession.search(Patient.class).where(f -> f.matchAll()).fetchTotalHitCount();
        Assert.assertEquals(0, totalHitCount);

        massIndexerService.reindex();
        totalHitCount = searchSession.search(Patient.class).where(f -> f.matchAll()).fetchTotalHitCount();
        Assert.assertEquals(1, totalHitCount);

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