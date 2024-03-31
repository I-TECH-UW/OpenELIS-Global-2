package Address;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.address.service.AddressPartService;
import org.openelisglobal.address.service.OrganizationAddressService;
import org.openelisglobal.address.valueholder.AddressPK;
import org.openelisglobal.address.valueholder.AddressPart;
import org.openelisglobal.address.valueholder.OrganizationAddress;

public class AdressServiceTest {

	@Before
	public void init() throws Exception {
	}

	@Test
	public void testGetAll() {
		// Create mock objects
		AddressPartService addressPartService = mock(AddressPartService.class);

		// Create sample data
		List<AddressPart> expectedAddressParts = new ArrayList<>();

		// Creating sample AddressPart objects
		AddressPart addressPart1 = new AddressPart();
		addressPart1.setId("1");
		addressPart1.setPartName("Street");
		addressPart1.setDisplayOrder("1");

		AddressPart addressPart2 = new AddressPart();
		addressPart2.setId("2");
		addressPart2.setPartName("City");
		addressPart2.setDisplayOrder("2");

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
	public void testGetAddressPartByName() {
		AddressPartService addressPartService = mock(AddressPartService.class);

		// Create sample data
		String addressPartName = "Street";
		AddressPart expectedAddressPart = new AddressPart();
		expectedAddressPart.setId("1");
		expectedAddressPart.setPartName("Street");
		expectedAddressPart.setDisplayOrder("1");

		// Stub the method call on the mock object
		when(addressPartService.getAddresPartByName(addressPartName)).thenReturn(expectedAddressPart);

		// Call the method under test
		AddressPart actualAddressPart = addressPartService.getAddresPartByName(addressPartName);

		// Verify that the method returned the expected AddressPart object
		Assert.assertEquals(expectedAddressPart, actualAddressPart);
	}

	@Test
	public void testGetAddressPartsByOrganizationId() {
		OrganizationAddressService organizationAddressService = mock(OrganizationAddressService.class);

		// Create sample data
		String organizationId = "1";
		List<OrganizationAddress> expectedAddresses = new ArrayList<>();
		OrganizationAddress address1 = new OrganizationAddress();
		AddressPK addressPK1 = new AddressPK();
		addressPK1.setAddressPartId("2");
		address1.setId(addressPK1);
		address1.setValue("123 Main St");
		address1.setType("Type1");

		OrganizationAddress address2 = new OrganizationAddress();
		AddressPK addressPK2 = new AddressPK();
		addressPK2.setAddressPartId("2");
		address2.setId(addressPK2);
		address2.setValue("456 Elm St");
		address2.setType("Type2");

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

}
