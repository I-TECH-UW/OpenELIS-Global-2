package org.openelisglobal.unitTests.address;

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
        organizationAddress.setOrganizationId("123");
        Assert.assertEquals("123", organizationAddress.getOrganizationId());
    }

    @Test
    public void testSetAndGetAddressPartId() {
        organizationAddress.setAddressPartId("456");
        Assert.assertEquals("456", organizationAddress.getAddressPartId());
    }

    @Test
    public void testSetAndGetUniqueIdentifier() {
        organizationAddress.setUniqueIdentifyer("unique123");
        Assert.assertEquals("unique123", organizationAddress.getUniqueIdentifyer());
    }

    @Test
    public void testGetStringId() {
        String organizationId = "123";
        String addressPartId = "456";
        organizationAddress.setOrganizationId("123");
        organizationAddress.setAddressPartId("456");
        Assert.assertEquals("123456", organizationAddress.getStringId());
    }

   
}
