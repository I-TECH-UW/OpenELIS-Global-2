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
        personAddress.setPersonId("123");
        personAddress.setAddressPartId("456");
        personAddress.setUniqueIdentifyer("unique123");
    }

    @Test
    public void testPersonId() { 
        Assert.assertEquals("123", personAddress.getPersonId());
    }

    @Test
    public void testAddressPartId() {
        Assert.assertEquals("456", personAddress.getAddressPartId());
    }
    
    @Test
    public void testUniqueIdentifier() {
        Assert.assertEquals("unique123", personAddress.getUniqueIdentifyer());
    }

    @Test
    public void testStringId() {
        Assert.assertEquals("123456", personAddress.getStringId());
    }

}
