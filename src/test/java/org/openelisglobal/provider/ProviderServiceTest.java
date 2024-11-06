package org.openelisglobal.provider;

import java.text.ParseException;
import org.junit.After;
import org.junit.Before;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.person.valueholder.Person;
import org.openelisglobal.provider.service.ProviderService;
import org.openelisglobal.provider.valueholder.Provider;
import org.springframework.beans.factory.annotation.Autowired;

public class ProviderServiceTest extends BaseWebContextSensitiveTest {

    @Autowired
    ProviderService providerService;

    @Autowired
    PersonService personService;

    @Before
    public void init() throws Exception {
        providerService.deleteAll(providerService.getAll());
    }

    @After
    public void tearDown() throws Exception {
        providerService.deleteAll(providerService.getAll());
    }

    // @Test
    // public void createProvider_shouldReturnCreatedProvider() throws Exception{
    // String firstName = "John";
    // String lastName = "Doe";
    // String providerType = "physician";
    // Provider prov = createProvider(firstName, lastName, providerType);

    // Assert.assertEquals(0, providerService.getAll().size());

    // String providerId = providerService.insert(prov);
    // Assert.assertEquals(1, providerService.getAll().size());
    // }

    private Provider createProvider(String firstName, String LastName, String providerType) throws ParseException {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(LastName);
        personService.save(person);

        Provider prov = new Provider();
        prov.setProviderType(providerType);
        prov.setPerson(person);

        return prov;
    }
}
