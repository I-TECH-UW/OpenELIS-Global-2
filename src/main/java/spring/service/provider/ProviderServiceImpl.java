package spring.service.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.provider.dao.ProviderDAO;
import us.mn.state.health.lims.provider.valueholder.Provider;

@Service
public class ProviderServiceImpl extends BaseObjectServiceImpl<Provider, String> implements ProviderService {
	@Autowired
	protected ProviderDAO baseObjectDAO;

	ProviderServiceImpl() {
		super(Provider.class);
	}

	@Override
	protected ProviderDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void getData(Provider provider) {
		getBaseObjectDAO().getData(provider);

	}

	@Override
	public List getPageOfProviders(int startingRecNo) {
		return getBaseObjectDAO().getPageOfProviders(startingRecNo);
	}

	@Override
	public List getAllProviders() {
		return getBaseObjectDAO().getAllProviders();
	}

	@Override
	public List getNextProviderRecord(String id) {
		return getBaseObjectDAO().getNextProviderRecord(id);
	}

	@Override
	public List getPreviousProviderRecord(String id) {
		return getBaseObjectDAO().getPreviousProviderRecord(id);
	}

	@Override
	public Provider getProviderByPerson(Person person) {
		return getBaseObjectDAO().getProviderByPerson(person);
	}
}
