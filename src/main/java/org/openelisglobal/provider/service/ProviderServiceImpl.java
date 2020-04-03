package org.openelisglobal.provider.service;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.person.valueholder.Person;
import org.openelisglobal.provider.dao.ProviderDAO;
import org.openelisglobal.provider.valueholder.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public void getData(Provider provider) {
        getBaseObjectDAO().getData(provider);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Provider> getPageOfProviders(int startingRecNo) {
        return getBaseObjectDAO().getPageOfProviders(startingRecNo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Provider> getAllProviders() {
        return getBaseObjectDAO().getAllProviders();
    }

    @Override
    @Transactional(readOnly = true)
    public Provider getProviderByPerson(Person person) {
        return getBaseObjectDAO().getProviderByPerson(person);
    }
}
