package spring.service.person;

import java.util.List;
import java.util.Map;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.person.valueholder.Person;

public interface PersonService extends BaseObjectService<Person, String> {
	void getData(Person person);

	void deleteData(List persons);

	void updateData(Person person);

	boolean insertData(Person person);

	List getNextPersonRecord(String id);

	List getPreviousPersonRecord(String id);

	Person getPersonByLastName(String lastName);

	List getPageOfPersons(int startingRecNo);

	List getAllPersons();

	Person getPersonById(String personId);

	Map<String, String> getAddressComponents();

	Person getPerson();

	String getPhone();

	String getLastFirstName();

	String getLastName();

	String getFirstName();
}
