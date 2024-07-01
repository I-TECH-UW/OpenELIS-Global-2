package org.openelisglobal.address;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openelisglobal.BaseTestConfig;
import org.openelisglobal.address.service.AddressPartService;
import org.openelisglobal.address.service.OrganizationAddressService;
import org.openelisglobal.address.service.PersonAddressService;
import org.openelisglobal.address.valueholder.AddressPK;
import org.openelisglobal.address.valueholder.AddressPart;
import org.openelisglobal.address.valueholder.OrganizationAddress;
import org.openelisglobal.address.valueholder.PersonAddress;
import org.openelisglobal.patient.PatientTestConfig;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BaseTestConfig.class, PatientTestConfig.class })
@TestPropertySource("classpath:common.properties")
@ActiveProfiles("test")
public class AddressServiceIntegrationTest {

    @Autowired
    private AddressPartService addressPartService;

    @Autowired
    private OrganizationAddressService organizationAddressService;
    @Test
    public void testGetAll() {
        List<AddressPart> addressPartsToInsert = new ArrayList<>();
        AddressPart addressPart1 = createAddressPart("7", "AB Street", "102");
        AddressPart addressPart2 = createAddressPart("8", "CD Street", "103");
        addressPartsToInsert.add(addressPart1);
        addressPartsToInsert.add(addressPart2);

//        List<String> insertedIds = addressPartService.insertAll(addressPartsToInsert);

        List<AddressPart> actualAddressParts = addressPartService.getAll();
//        System.out.println("Expected address parts: " + addressPartsToInsert);
        System.out.println("Actual address parts: " + actualAddressParts);
        Assert.assertEquals(6, actualAddressParts.size());
    }

    @Test
    public void testGetAddressPartByName() {
        String addressPartName = "X";
        AddressPart addressPart = createAddressPart("11", addressPartName, "null");

        String AddressId = addressPartService.insert(addressPart);
        AddressPart actualAddressPart = addressPartService.getAddresPartByName(addressPartName);

        Assert.assertEquals(addressPart.getPartName(), actualAddressPart.getPartName());
        Assert.assertEquals(addressPart.getId(), actualAddressPart.getId());

    }

    @Test
    public void testGetAddressPartsByOrganizationId() {
        String organizationId = "1329";
        List<OrganizationAddress> expectedAddresses = new ArrayList<>();
        OrganizationAddress address1 = createOrganizationAddress(organizationId,"2", "s", "x");
        organizationAddressService.insert(address1);
        expectedAddresses.add(address1);

        List<OrganizationAddress> actualAddresses = organizationAddressService
                .getAddressPartsByOrganizationId(organizationId);

        Assert.assertEquals(expectedAddresses, actualAddresses);

    }

    // Helper methods
    AddressPart createAddressPart(String id, String partName, String displayOrder) {
        AddressPart addressPart = new AddressPart();
        addressPart.setId(id);
        addressPart.setPartName(partName);
        addressPart.setDisplayOrder(displayOrder);
        return addressPart;
    }

    private AddressPK createAddressPK(String addressPartId) {
        AddressPK addressPK = new AddressPK();
        addressPK.setAddressPartId(addressPartId);
        return addressPK;
    }
    private OrganizationAddress createOrganizationAddress(String orgId, String addressPartId, String value, String type) {

        OrganizationAddress organizationAddress = new OrganizationAddress();
        AddressPK addressPK = createAddressPK(addressPartId);

        organizationAddress.setId(addressPK);
        organizationAddress.setValue(value);
        organizationAddress.setType(type);
        organizationAddress.setCompoundId(addressPK);
        organizationAddress.setOrganizationId(orgId);

        return organizationAddress;
    }

    private PersonAddress createPersonAddress(String addressPartId, String value, String type) {
        PersonAddress personAddress = new PersonAddress();
        AddressPK addressPK = createAddressPK(addressPartId);
        personAddress.setId(addressPK);
        personAddress.setValue(value);
        personAddress.setType(type);
        return personAddress;
    }

}
