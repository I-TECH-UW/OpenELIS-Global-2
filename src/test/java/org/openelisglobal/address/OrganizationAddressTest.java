package org.openelisglobal.address;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.address.valueholder.OrganizationAddress;

public class OrganizationAddressTest {

    private OrganizationAddress organizationAddress;

    @Before
    public void setUp() {
        organizationAddress = new OrganizationAddress();
    }

    @Test
    public void testSetAndGetOrganizationId() {
        String organizationId = "123";
        organizationAddress.setOrganizationId(organizationId);
        Assert.assertEquals(organizationId, organizationAddress.getOrganizationId());
    }

    @Test
    public void testSetAndGetAddressPartId() {
        String addressPartId = "456";
        organizationAddress.setAddressPartId(addressPartId);
        Assert.assertEquals(addressPartId, organizationAddress.getAddressPartId());
    }

    @Test
    public void testSetAndGetUniqueIdentifier() {
        String uniqueIdentifier = "unique123";
        organizationAddress.setUniqueIdentifyer(uniqueIdentifier);
        Assert.assertEquals(uniqueIdentifier, organizationAddress.getUniqueIdentifyer());
    }

    @Test
    public void testGetStringId() {
        String organizationId = "123";
        String addressPartId = "456";
        organizationAddress.setOrganizationId(organizationId);
        organizationAddress.setAddressPartId(addressPartId);
        String expectedStringId = organizationId + addressPartId;
        Assert.assertEquals(expectedStringId, organizationAddress.getStringId());
    }

    // Add more test cases for other methods as needed
}
