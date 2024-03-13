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
        String personId = "123";
        personAddress.setPersonId(personId);
        Assert.assertEquals(personId, personAddress.getPersonId());
    }

    @Test
    public void testSetAndGetAddressPartId() {
        String addressPartId = "456";
        personAddress.setAddressPartId(addressPartId);
        Assert.assertEquals(addressPartId, personAddress.getAddressPartId());
    }

    @Test
    public void testSetAndGetUniqueIdentifier() {
        String uniqueIdentifier = "unique123";
        personAddress.setUniqueIdentifyer(uniqueIdentifier);
        Assert.assertEquals(uniqueIdentifier, personAddress.getUniqueIdentifyer());
    }

    @Test
    public void testGetStringId() {
        String personId = "123";
        String addressPartId = "456";
        personAddress.setPersonId(personId);
        personAddress.setAddressPartId(addressPartId);
        String expectedStringId = personId + addressPartId;
        Assert.assertEquals(expectedStringId, personAddress.getStringId());
    }

    // Add more test cases for other methods as needed
}
