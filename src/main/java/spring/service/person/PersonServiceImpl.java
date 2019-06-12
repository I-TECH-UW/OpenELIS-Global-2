package spring.service.person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import spring.service.address.AddressPartService;
import spring.service.address.PersonAddressService;
import spring.service.common.BaseObjectServiceImpl;
import spring.service.dictionary.DictionaryService;
import spring.util.SpringContext;
import us.mn.state.health.lims.address.valueholder.AddressPart;
import us.mn.state.health.lims.address.valueholder.PersonAddress;
import us.mn.state.health.lims.person.dao.PersonDAO;
import us.mn.state.health.lims.person.valueholder.Person;

@Service
@DependsOn({ "springContext" })
@Scope("prototype")
public class PersonServiceImpl extends BaseObjectServiceImpl<Person, String> implements PersonService {

	private static Map<String, String> addressPartIdToNameMap;

	@Autowired
	protected PersonDAO baseObjectDAO;

	@Autowired
	private static DictionaryService dictionaryService = SpringContext.getBean(DictionaryService.class);
	@Autowired
	private AddressPartService addressPartService = SpringContext.getBean(AddressPartService.class);
	@Autowired
	private PersonAddressService personAddressService = SpringContext.getBean(PersonAddressService.class);

	private Person person;

	public synchronized void initializeGlobalVariables() {
		if (addressPartIdToNameMap == null) {
			addressPartIdToNameMap = new HashMap<>();
			List<AddressPart> parts = addressPartService.getAll();

			for (AddressPart part : parts) {
				addressPartIdToNameMap.put(part.getId(), part.getPartName());
			}
		}
	}

	public PersonServiceImpl() {
		super(Person.class);
		initializeGlobalVariables();
	}

	public PersonServiceImpl(Person person) {
		this();
		this.person = person;
	}

	@Override
	protected PersonDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public String getFirstName() {
		return person != null ? person.getFirstName() : "";
	}

	@Override
	public String getLastName() {
		return person != null ? person.getLastName() : "";
	}

	@Override
	public String getLastFirstName() {
		String lastName = getLastName();
		String firstName = getFirstName();
		if (!GenericValidator.isBlankOrNull(lastName) && !GenericValidator.isBlankOrNull(firstName)) {
			lastName += ", ";
		}

		lastName += firstName;

		return lastName;

	}

	@Override
	public Map<String, String> getAddressComponents() {
		String value;
		Map<String, String> addressMap = new HashMap<>();

		if (person == null) {
			return addressMap;
		}

		List<PersonAddress> addressParts = personAddressService.getAddressPartsByPersonId(person.getId());

		for (PersonAddress parts : addressParts) {
			if ("D".equals(parts.getType()) && !GenericValidator.isBlankOrNull(parts.getValue())) {
				addressMap.put(addressPartIdToNameMap.get(parts.getAddressPartId()),
						dictionaryService.getDataForId(parts.getValue()).getDictEntry());
			} else {
				value = parts.getValue();
				addressMap.put(addressPartIdToNameMap.get(parts.getAddressPartId()), value == null ? "" : value.trim());
			}
		}

		value = person.getCity();
		addressMap.put("City", value == null ? "" : value.trim());
		value = person.getCountry();
		addressMap.put("Country", value == null ? "" : value.trim());
		value = person.getState();
		addressMap.put("State", value == null ? "" : value.trim());
		value = person.getStreetAddress();
		addressMap.put("Street", value == null ? "" : value.trim());
		value = person.getZipCode();
		addressMap.put("Zip", value == null ? "" : value.trim());

		return addressMap;
	}

	@Override
	public String getPhone() {
		if (person == null) {
			return "";
		}

		String phone = person.getHomePhone();

		if (GenericValidator.isBlankOrNull(phone)) {
			phone = person.getCellPhone();
		}

		if (GenericValidator.isBlankOrNull(phone)) {
			phone = person.getWorkPhone();
		}

		return phone;
	}

	public String getWorkPhone() {
		return person.getWorkPhone();
	}

	public String getCellPhone() {
		return person.getCellPhone();
	}

	public String getHomePhone() {
		return person.getHomePhone();
	}

	public String getFax() {
		return person.getFax();
	}

	public String getEmail() {
		return person.getEmail();
	}

	@Override
	public Person getPerson() {
		return person;
	}

	@Override
	public void getData(Person person) {
		getBaseObjectDAO().getData(person);

	}

	@Override
	public List getNextPersonRecord(String id) {
		return getBaseObjectDAO().getNextPersonRecord(id);
	}

	@Override
	public List getPreviousPersonRecord(String id) {
		return getBaseObjectDAO().getPreviousPersonRecord(id);
	}

	@Override
	public Person getPersonByLastName(String lastName) {
		return getBaseObjectDAO().getPersonByLastName(lastName);
	}

	@Override
	public List getPageOfPersons(int startingRecNo) {
		return getBaseObjectDAO().getPageOfPersons(startingRecNo);
	}

	@Override
	public List getAllPersons() {
		return getBaseObjectDAO().getAllPersons();
	}

	@Override
	public Person getPersonById(String personId) {
		return getBaseObjectDAO().getPersonById(personId);
	}
}
