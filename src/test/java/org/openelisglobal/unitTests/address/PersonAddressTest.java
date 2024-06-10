package org.openelisglobal.unitTests.address;

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
        personAddress.setPersonId("123");
        personAddress.setAddressPartId("456");
        personAddress.setUniqueIdentifyer("unique123");
    }

    @Test
    public void getPersonId_shouldReturnCorrectPersonId() {
        Assert.assertEquals("123", personAddress.getPersonId());
    }

    @Test
    public void getAddressPartId_shouldReturnCorrectAddressPartId() {
        Assert.assertEquals("456", personAddress.getAddressPartId());
    }
    
    @Test
    public void getUniqueIdentifier_shouldReturnCorrectUniqueIdentifier() {
        Assert.assertEquals("unique123", personAddress.getUniqueIdentifyer());
    }

    @Test
    public void getStringId_shouldReturnConcatenatedStringId() {
        Assert.assertEquals("123456", personAddress.getStringId());
    }

}
