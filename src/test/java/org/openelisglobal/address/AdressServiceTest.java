package org.openelisglobal.address;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.address.service.AddressPartService;
import org.openelisglobal.address.service.PersonAddressService;
import org.openelisglobal.address.service.OrganizationAddressService;
import org.openelisglobal.address.valueholder.AddressPK;
import org.openelisglobal.address.valueholder.AddressPart;
import org.openelisglobal.address.valueholder.OrganizationAddress;
import org.openelisglobal.address.valueholder.PersonAddress;

public class AdressServiceTest {

	private PersonAddressService personAddressService;
	private AddressPartService addressPartService;
	private OrganizationAddressService organizationAddressService;

	@Before
	public void setUp() throws Exception {
		personAddressService = mock(PersonAddressService.class);
		addressPartService = mock(AddressPartService.class);
		organizationAddressService = mock(OrganizationAddressService.class);
	}

	@Before
	public void init() throws Exception {
	}

	@Test
	public void getAll_shouldReturnAllAddressParts() {

		// Create sample data
		List<AddressPart> expectedAddressParts = new ArrayList<>();
		AddressPart addressPart1 = createAddressPart("123", "AB Street", "102");
		AddressPart addressPart2 = createAddressPart("124", "CD Street", "103");

		// Adding sample AddressPart objects to expectedAddressParts
		expectedAddressParts.add(addressPart1);
		expectedAddressParts.add(addressPart2);

		// Stub the method call on the mock object
		when(addressPartService.getAll()).thenReturn(expectedAddressParts);

		// Call the method under test
		List<AddressPart> actualAddressParts = addressPartService.getAll();

		// Verify that the method returned the expected list of AddressPart objects
		Assert.assertEquals(expectedAddressParts, actualAddressParts);
	}

	@Test
	public void getAddressPartByName_shouldReturnCorrectAddressPart() {
		// Create sample data
		String addressPartName = "EF Street";
		AddressPart expectedAddressPart = createAddressPart("125", "EF Street", "105");

		// Stub the method call on the mock object
		when(addressPartService.getAddresPartByName(addressPartName)).thenReturn(expectedAddressPart);

		// Call the method under test
		AddressPart actualAddressPart = addressPartService.getAddresPartByName(addressPartName);

		// Verify that the method returned the expected AddressPart object
		Assert.assertEquals(expectedAddressPart, actualAddressPart);
	}

	@Test
	public void getAddressPartsByOrganizationId_shouldReturnOrganizationAddresses() {

		// Create sample data
		String organizationId = "1329";
		List<OrganizationAddress> expectedAddresses = new ArrayList<>();
		OrganizationAddress address1 = createOrganizationAddress("2", "123 Main St", "Type1");

		OrganizationAddress address2 = createOrganizationAddress("3", "456 Elm St", "Type2");

		expectedAddresses.add(address1);
		expectedAddresses.add(address2);

		// Stub the method call on the mock object
		when(organizationAddressService.getAddressPartsByOrganizationId(organizationId)).thenReturn(expectedAddresses);

		// Call the method under test
		List<OrganizationAddress> actualAddresses = organizationAddressService
				.getAddressPartsByOrganizationId(organizationId);

		// Verify that the method returned the expected list of OrganizationAddress
		Assert.assertEquals(expectedAddresses, actualAddresses);
	}

	@Test
	public void getAddressPartsByPersonId_shouldReturnPersonAddresses() {

		// Prepare test data
		String personId = "123";
		List<PersonAddress> expectedAddresses = new ArrayList<>();
		PersonAddress address1 = createPersonAddress("11", "Address 2", "Type 1");
		PersonAddress address2 = createPersonAddress("22", "Address 2", "Type 2");
		expectedAddresses.add(address1);
		expectedAddresses.add(address2);
		when(personAddressService.getAddressPartsByPersonId(personId)).thenReturn(expectedAddresses);

		// When
		List<PersonAddress> actualAddresses = personAddressService.getAddressPartsByPersonId(personId);

		// Then
		Assert.assertNotNull(actualAddresses);
		Assert.assertEquals(expectedAddresses.size(), actualAddresses.size());

	}

	@Test
	public void getByPersonIdAndPartId_shouldReturnCorrectPersonAddress() {
		// Create a mock instance of PersonAddressService

		// Prepare test data
		String personId = "123";
		String addressPartId = "33";
		PersonAddress expectedAddress = createPersonAddress("33", "Address 3", "Type 3");

		// Stub the method call on the mock object
		when(personAddressService.getByPersonIdAndPartId(personId, addressPartId)).thenReturn(expectedAddress);

		// Call the method under test
		PersonAddress actualAddress = personAddressService.getByPersonIdAndPartId(personId, addressPartId);

		// Verify that the method returned the expected PersonAddress object
		Assert.assertNotNull(actualAddress);
		Assert.assertEquals(expectedAddress, actualAddress);
	}

	@Test
	public void insert_shouldReturnInsertedAddressPK() {

		// Prepare test data
		PersonAddress personAddressToInsert = createPersonAddress("44", "Address 4", "Type 4");
		AddressPK addressPK = createAddressPK("44");
		personAddressToInsert.setId(addressPK);

		// Stub the method call on the mock object
		when(personAddressService.insert(personAddressToInsert)).thenReturn(addressPK);

		// Call the method under test
		AddressPK insertedAddressPK = personAddressService.insert(personAddressToInsert);

		// Verify that the method returned the expected AddressPK object
		Assert.assertNotNull(insertedAddressPK);
		Assert.assertEquals(addressPK, insertedAddressPK);
	}

//	Helper methods

	AddressPart createAddressPart(String id, String partName, String DisplayOrder) {
		AddressPart addressPart = new AddressPart();
		addressPart.setId("1");
		addressPart.setPartName("Street");
		addressPart.setDisplayOrder("1");
		return addressPart;
	}

	private AddressPK createAddressPK(String addressPartId) {
		AddressPK addressPK = new AddressPK();
		addressPK.setAddressPartId(addressPartId);
		return addressPK;
	}

	private OrganizationAddress createOrganizationAddress(String addressPartId, String value, String type) {
		OrganizationAddress organizationAddress = new OrganizationAddress();
		AddressPK addressPK = createAddressPK(addressPartId);
		organizationAddress.setId(addressPK);
		organizationAddress.setValue(value);
		organizationAddress.setType(type);
		return organizationAddress;
	}

	private PersonAddress createPersonAddress(String addressPartId, String value, String type) {
		PersonAddress personAddress = new PersonAddress();
		AddressPK addressPK = createAddressPK(addressPartId);
		personAddress.setId(addressPK);
		personAddress.setValue(value);
		personAddress.setType(type);
		return personAddress;
	}

}
