package org.openelisglobal.unitTests.address;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.address.valueholder.AddressPart;

public class AddressPartTest {

    private AddressPart addressPart;

    @Before
    public void setUp() {
        addressPart = new AddressPart();
    }

    @Test
    public void setId_shouldSetAndReturnId() {
        addressPart.setId("123");
        assertEquals("123", addressPart.getId());
    }
    
    @Test
    public void setPartName_shouldSetAndReturnPartName() {
        addressPart.setPartName("Street");
        assertEquals("Street", addressPart.getPartName());
    }

    @Test
    public void setDisplayOrder_shouldSetAndReturnDisplayOrder() {
        addressPart.setDisplayOrder("1");
        assertEquals("1", addressPart.getDisplayOrder());
    }
}

