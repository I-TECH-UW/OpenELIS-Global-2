package org.openelisglobal.address;

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
	    public void testSetAndGetTargetId() {
	        String targetId = "123";
	        addressPK.setTargetId(targetId);
	        Assert.assertEquals(targetId, addressPK.getTargetId());
	    }

	    @Test
	    public void testSetAndGetAddressPartId() {
	        String addressPartId = "456";
	        addressPK.setAddressPartId(addressPartId);
	        Assert.assertEquals(addressPartId, addressPK.getAddressPartId());
	    }

	    @Test
	    public void testEqualsAndHashCode() {
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
