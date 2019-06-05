package spring.service.provider;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.provider.valueholder.Provider;

public interface ProviderService extends BaseObjectService<Provider, String> {
	void getData(Provider provider);

	void deleteData(List providers);

	void updateData(Provider provider);

	boolean insertData(Provider provider);

	List getPageOfProviders(int startingRecNo);

	List getAllProviders();

	List getNextProviderRecord(String id);

	List getPreviousProviderRecord(String id);

	Provider getProviderByPerson(Person person);
}
