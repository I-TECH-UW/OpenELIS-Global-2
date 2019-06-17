package spring.service.person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(readOnly = true)
	public String getFirstName() {
		return person != null ? person.getFirstName() : "";
	}

	@Override
	@Transactional(readOnly = true)
	public String getLastName() {
		return person != null ? person.getLastName() : "";
	}

	@Override
	@Transactional(readOnly = true)
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
	@Transactional(readOnly = true)
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

	@Transactional(readOnly = true)
	public String getWorkPhone() {
		return person.getWorkPhone();
	}

	@Transactional(readOnly = true)
	public String getCellPhone() {
		return person.getCellPhone();
	}

	@Transactional(readOnly = true)
	public String getHomePhone() {
		return person.getHomePhone();
	}

	@Transactional(readOnly = true)
	public String getFax() {
		return person.getFax();
	}

	@Transactional(readOnly = true)
	public String getEmail() {
		return person.getEmail();
	}

	@Override
	@Transactional(readOnly = true)
	public Person getPerson() {
		return person;
	}

	@Override
	@Transactional(readOnly = true)
	public void getData(Person person) {
		getBaseObjectDAO().getData(person);

	}

	@Override
	@Transactional(readOnly = true)
	public List getNextPersonRecord(String id) {
		return getBaseObjectDAO().getNextPersonRecord(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List getPreviousPersonRecord(String id) {
		return getBaseObjectDAO().getPreviousPersonRecord(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Person getPersonByLastName(String lastName) {
		return getBaseObjectDAO().getPersonByLastName(lastName);
	}

	@Override
	@Transactional(readOnly = true)
	public List getPageOfPersons(int startingRecNo) {
		return getBaseObjectDAO().getPageOfPersons(startingRecNo);
	}

	@Override
	@Transactional(readOnly = true)
	public List getAllPersons() {
		return getBaseObjectDAO().getAllPersons();
	}

	@Override
	@Transactional(readOnly = true)
	public Person getPersonById(String personId) {
		return getBaseObjectDAO().getPersonById(personId);
	}
}
