package org.openelisglobal.unitTests.address;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.address.valueholder.AddressPK;

public class AdressPKTest {

	 private AddressPK addressPK;

	    @Before
	    public void setUp() {
	        addressPK = new AddressPK();
	    }

	    @Test
	    public void setTargetId_shouldSetAndReturnTargetId() {
	        addressPK.setTargetId("123");
	        Assert.assertEquals("123", addressPK.getTargetId());
	    }

	    @Test
	    public void setAddressPartId_shouldSetAndReturnAddressPartId() {
	        addressPK.setAddressPartId("456");
	        Assert.assertEquals("456", addressPK.getAddressPartId());
	    }

	    @Test
	    public void equalsAndHashCode_shouldReturnTrueForEqualObjects() {
	        AddressPK addressPK1 = new AddressPK();
	        addressPK1.setTargetId("123");
	        addressPK1.setAddressPartId("456");

	        AddressPK addressPK2 = new AddressPK();
	        addressPK2.setTargetId("123");
	        addressPK2.setAddressPartId("456");

	        AddressPK addressPK3 = new AddressPK();
	        addressPK3.setTargetId("789");
	        addressPK3.setAddressPartId("101");

	        Assert.assertEquals(addressPK1, addressPK2);
	        Assert.assertNotEquals(addressPK1, addressPK3);
	        Assert.assertEquals(addressPK1.hashCode(), addressPK2.hashCode());
	        Assert.assertNotEquals(addressPK1.hashCode(), addressPK3.hashCode());
	    }

	}
