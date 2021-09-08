package org.openelisglobal.person;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openelisglobal.BaseTestConfig;
import org.openelisglobal.patient.PatientTestConfig;
import org.openelisglobal.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BaseTestConfig.class, PatientTestConfig.class })
@TestPropertySource("classpath:common.properties")
@ActiveProfiles("test")
public class PersonServiceTest {

    @Autowired
    PersonService personService;

    @Before
    public void init() throws Exception {
    }

    @Test
    public void getAllPerson_shouldGetAllPatients() throws Exception {
        Assert.assertEquals(6, personService.getAllPersons().size());
    }
}
