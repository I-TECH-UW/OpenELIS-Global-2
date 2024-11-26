package org.openelisglobal.provider;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.person.valueholder.Person;
import org.openelisglobal.provider.service.ProviderService;
import org.openelisglobal.provider.valueholder.Provider;
import org.springframework.beans.factory.annotation.Autowired;

public class ProviderServiceTest extends BaseWebContextSensitiveTest {

    private static final String PERSON_FIRSTNAME = "John";
    private static final String PERSON_LASTNAME = "Doe";
    private static final String PROVIDER_TYPE = "M";
    private static final String PERSON_LASTNAME2 = "Rick";
    private static final String PROVIDER_TYPE2 = "B";

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

    @Test
    public void createProvider_shouldReturnCreatedProvider() throws Exception {
        Provider prov = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME, PROVIDER_TYPE);

        Assert.assertEquals(0, providerService.getAll().size());

        providerService.insert(prov);
        Assert.assertEquals(1, providerService.getAll().size());
    }

    @Test
    public void updateProvider_shouldUpdateProvider() throws Exception {
        Provider prov = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME, PROVIDER_TYPE);

        Assert.assertEquals(0, providerService.getAll().size());

        Provider savedProvider = providerService.save(prov);
        savedProvider.getPerson().setCountry("Uganda");
        providerService.update(savedProvider);

        Assert.assertEquals("Uganda", prov.getPerson().getCountry());
    }

    @Test
    public void deleteProvider_shouldDeleteProvider() throws Exception {
        Provider prov = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME, PROVIDER_TYPE);

        providerService.save(prov);

        providerService.delete(prov);

        Assert.assertEquals(0, providerService.getAll().size());
    }

    @Test
    public void getAllProviders_shouldReturnAllProvider() throws Exception {
        Provider prov = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME, PROVIDER_TYPE);

        Assert.assertEquals(0, providerService.getAll().size());

        providerService.insert(prov);
        Assert.assertEquals(1, providerService.getAll().size());
    }

    @Test
    public void getData_shouldReturncopiedPropertiesFromDatabase() throws Exception {
        Provider prov = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME, PROVIDER_TYPE);

        String provId = providerService.insert(prov);

        Provider provider = new Provider();
        provider.setId(provId);
        providerService.getData(provider);
        Assert.assertEquals(PROVIDER_TYPE, provider.getProviderType());
        Assert.assertEquals(PERSON_FIRSTNAME, provider.getPerson().getFirstName());
        Assert.assertEquals(PERSON_LASTNAME, provider.getPerson().getLastName());
    }

    @Test
    public void getProviderByFhirId_shouldReturnProviderByFhirId() throws Exception {
        UUID firUuid = UUID.randomUUID();
        Provider prov = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME, PROVIDER_TYPE);
        prov.setFhirUuid(firUuid);

        providerService.insert(prov);

        Provider provid = providerService.getProviderByFhirId(firUuid);
        Assert.assertEquals(PROVIDER_TYPE, provid.getProviderType());
    }

    @Test
    public void insertOrUpdateProviderByFhirUuid_shouldUpdateProviderByFhirId() throws Exception {
        UUID firUuid = UUID.randomUUID();
        Provider prov = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME, PROVIDER_TYPE);
        prov.setFhirUuid(firUuid);

        Provider savedProvider = providerService.save(prov);
        savedProvider.getPerson().setCity("Kampala");

        providerService.insertOrUpdateProviderByFhirUuid(firUuid, savedProvider);
        Assert.assertEquals("Kampala", prov.getPerson().getCity());
    }

    @Test
    public void getProviderIdByFhirId_shouldReturnProviderByFhirId() throws Exception {
        UUID firUuid = UUID.randomUUID();
        Provider prov = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME, PROVIDER_TYPE);
        prov.setFhirUuid(firUuid);

        providerService.insert(prov);

        String provid = providerService.getProviderIdByFhirId(firUuid);
        Assert.assertEquals(prov.getId(), provid);
    }

    @Test
    public void getPageOfProviders_shouldReturnPageOfProviders() throws Exception {
        Provider prov = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME, PROVIDER_TYPE);

        Provider prov2 = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME2, PROVIDER_TYPE2);
        providerService.insert(prov);
        providerService.insert(prov2);

        List<Provider> providersPage = providerService.getPageOfProviders(1);

        int expectedPageSize = Integer
                .parseInt(ConfigurationProperties.getInstance().getPropertyValue("page.defaultPageSize"));
        Assert.assertTrue(providersPage.size() <= expectedPageSize);

        if (expectedPageSize >= 2) {
            Assert.assertTrue(
                    providersPage.stream().anyMatch(p -> p.getPerson().getFirstName().equals(PERSON_FIRSTNAME)));
            Assert.assertTrue(
                    providersPage.stream().anyMatch(p -> p.getPerson().getLastName().equals(PERSON_LASTNAME2)));
        }
    }

    @Test
    public void getPagesOfSearchedProviders_shouldReturnPagesOfSearchedProviders() throws Exception {
        Provider prov = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME, PROVIDER_TYPE);

        Provider prov2 = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME2, PROVIDER_TYPE2);
        providerService.insert(prov);
        providerService.insert(prov2);

        List<Provider> providersPage = providerService.getPagesOfSearchedProviders(1, PERSON_FIRSTNAME);

        Assert.assertNotNull(providersPage);
        Assert.assertEquals(2, providersPage.size());
        Assert.assertEquals(PERSON_LASTNAME, providersPage.get(0).getPerson().getLastName());
        Assert.assertEquals(PERSON_LASTNAME2, providersPage.get(1).getPerson().getLastName());
    }

    @Test
    public void getPagesOfSearchedProviders_shouldReturnEmptyListIfNoMatch() throws ParseException {
        Provider prov = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME, PROVIDER_TYPE);

        Provider prov2 = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME2, PROVIDER_TYPE2);
        providerService.insert(prov);
        providerService.insert(prov2);

        List<Provider> providersPage = providerService.getPagesOfSearchedProviders(1, "Benjah");

        Assert.assertNotNull(providersPage);
        Assert.assertTrue(providersPage.isEmpty());
    }

    @Test
    public void getAllActiveProviders_shouldReturnAllActiveProviders() throws Exception {
        Provider prov = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME, PROVIDER_TYPE);
        prov.setActive(true);

        Provider prov2 = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME2, PROVIDER_TYPE2);
        prov2.setActive(true);

        providerService.insert(prov);
        providerService.insert(prov2);

        List<Provider> activeProviders = providerService.getAllActiveProviders();
        Assert.assertEquals(2, activeProviders.size());
    }

    @Test
    public void deactivateAllProviders_shouldDeactivateAllProviders() throws Exception {
        Provider prov = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME, PROVIDER_TYPE);
        prov.setActive(true);

        Provider prov2 = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME2, PROVIDER_TYPE2);
        prov2.setActive(true);

        providerService.insert(prov);
        providerService.insert(prov2);

        providerService.deactivateAllProviders();
        Provider updatedProv = providerService.get(prov.getId());
        Provider updatedProv2 = providerService.get(prov2.getId());

        Assert.assertFalse(updatedProv.getActive().booleanValue());
        Assert.assertFalse(updatedProv2.getActive().booleanValue());
    }

    @Test
    public void getTotalSearchedProviderCount_shouldReturnTotalSearchedProviderCount() throws Exception {
        Provider prov = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME, PROVIDER_TYPE);
        prov.setActive(true);

        Provider prov2 = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME2, PROVIDER_TYPE2);
        prov2.setActive(true);

        providerService.insert(prov);
        providerService.insert(prov2);

        int totalCount = providerService.getTotalSearchedProviderCount("John");
        Assert.assertEquals(2, totalCount);
    }

    @Test
    public void deactivateProviders_shouldReturndeactivateProviders() throws Exception {
        Provider prov = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME, PROVIDER_TYPE);
        prov.setActive(true);

        Provider prov2 = createProvider(PERSON_FIRSTNAME, PERSON_LASTNAME2, PROVIDER_TYPE2);
        prov2.setActive(true);

        providerService.insert(prov);
        providerService.insert(prov2);

        List<Provider> providersToDeactivate = Arrays.asList(prov, prov2);

        providerService.deactivateProviders(providersToDeactivate);

        Provider updatedProv = providerService.get(prov.getId());
        Provider updatedProv2 = providerService.get(prov2.getId());

        Assert.assertFalse(updatedProv.getActive().booleanValue());
        Assert.assertFalse(updatedProv2.getActive().booleanValue());

    }

    @Test
    public void getProviderByPerson_shouldReturnProviderByPerson() throws Exception {
        String firstName = "John";
        String lastName = "Doe";
        String providerType = "m";

        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        personService.save(person);

        Provider prov = new Provider();
        prov.setPerson(person);
        prov.setProviderType(providerType);

        providerService.insert(prov);
        Assert.assertEquals(providerType, providerService.getProviderByPerson(person).getProviderType());
    }

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
