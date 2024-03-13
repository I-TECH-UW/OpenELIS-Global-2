package org.openelisglobal.address;

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
    public void testSetAndGetId() {
        String id = "123";
        addressPart.setId(id);
        assertEquals(id, addressPart.getId());
    }

    @Test
    public void testSetAndGetPartName() {
        String partName = "Street";
        addressPart.setPartName(partName);
        assertEquals(partName, addressPart.getPartName());
    }

    @Test
    public void testSetAndGetDisplayOrder() {
        String displayOrder = "1";
        addressPart.setDisplayOrder(displayOrder);
        assertEquals(displayOrder, addressPart.getDisplayOrder());
    }
}

