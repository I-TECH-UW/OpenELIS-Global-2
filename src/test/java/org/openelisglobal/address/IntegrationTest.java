package org.openelisglobal.address;

import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.address.valueholder.AddressPK;
import org.openelisglobal.address.valueholder.AddressPart;
import org.openelisglobal.address.valueholder.OrganizationAddress;
import org.openelisglobal.address.valueholder.PersonAddress;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.person.valueholder.Person;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IntegrationTest {

    private AddressPart addressPart;
    private OrganizationAddress organizationAddress;
    private AddressPK addressPK;
    private PersonAddress personAddress;
    private Organization organization;
    private Person person; // Add Person object

    @Before
    public void setUp() {
        addressPart = new AddressPart();
        organizationAddress = new OrganizationAddress();
        addressPK = new AddressPK();
        personAddress = new PersonAddress();
        organization = new Organization();
        person = new Person();
    }
    
    
    @Test
    public void testIntegration() {
        // Simulate interactions between the classes
        addressPart.setPartName("StreetX");
        organizationAddress.setType("Office");
        addressPK.setTargetId("456");
        addressPK.setAddressPartId("Eg");
        
        // Set up person object
        person.setId("789");
        person.setFirstName("John");
        person.setLastName("Doe");

        organizationAddress.setCompoundId(addressPK);
        personAddress.setCompoundId(addressPK);

        // Verify that the integrated behavior meets the expected outcomes
        assertEquals("StreetX", addressPart.getPartName());
        
        assertEquals("Office", organizationAddress.getType());
        assertEquals("456Eg", organizationAddress.getStringId());
        assertEquals(addressPK, organizationAddress.getCompoundId());
        
        assertEquals("456", personAddress.getPersonId());
        assertEquals("Eg", personAddress.getAddressPartId());
        
        // Verify person object properties
        assertEquals("789", person.getId());
        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());
    }
    
    @Test
    public void testOrganizationAddressIntegration() {
        // Set up organization and address part
        organization.setId("org123");
        addressPart.setId("address123");

        // Set organization ID and address part ID in organization address
        organizationAddress.setOrganizationId(organization.getId());
        organizationAddress.setAddressPartId(addressPart.getId());

        // Verify that organization address holds correct IDs
        assertEquals("org123", organizationAddress.getOrganizationId());
        assertEquals("address123", organizationAddress.getAddressPartId());
    }
    
    @Test
    public void testAddressPartNotNull() {
        assertNotNull(addressPart);
    }

    @Test
    public void testOrganizationAddressNotNull() {
        assertNotNull(organizationAddress);
    }

    @Test
    public void testAddressPKNotNull() {
        assertNotNull(addressPK);
    }

    @Test
    public void testPersonAddressNotNull() {
        assertNotNull(personAddress);
    }
    
    @Test
    public void testPersonNotNull() {
        assertNotNull(person);
    }
    
}