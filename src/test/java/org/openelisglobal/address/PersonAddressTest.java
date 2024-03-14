package org.openelisglobal.address;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.address.valueholder.PersonAddress;

public class PersonAddressTest {

    private PersonAddress personAddress;

    @Before
    public void setUp() {
        personAddress = new PersonAddress();
    }

    @Test
    public void testSetAndGetPersonId() {
        personAddress.setPersonId("123");
        Assert.assertEquals("123", personAddress.getPersonId());
    }

    @Test
    public void testSetAndGetAddressPartId() {
        personAddress.setAddressPartId("456");
        Assert.assertEquals("456", personAddress.getAddressPartId());
    }

    @Test
    public void testSetAndGetUniqueIdentifier() {
        personAddress.setUniqueIdentifyer("unique123");
        Assert.assertEquals("unique123", personAddress.getUniqueIdentifyer());
    }

    @Test
    public void testGetStringId() {
        personAddress.setPersonId("123");
        personAddress.setAddressPartId("456");
        Assert.assertEquals("123456", personAddress.getStringId());
    }

    // Add more test cases for other methods as needed
}
