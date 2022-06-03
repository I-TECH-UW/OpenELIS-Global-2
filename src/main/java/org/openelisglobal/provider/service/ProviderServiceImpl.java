package org.openelisglobal.provider.service;

import java.util.List;
import java.util.UUID;

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

    @Override
    public List<Provider> getAllActiveProviders() {
        return getBaseObjectDAO().getAllMatching("active", Boolean.TRUE);
    }

    @Override
    public void deactivateAllProviders() {
        for (Provider provider : getBaseObjectDAO().getAll()) {
            provider.setActive(false);
        }

    }

    @Override
    public Provider getProviderByFhirId(UUID fhirUuid) {
        List<Provider> providers = getBaseObjectDAO().getAllMatching("fhirUuid", fhirUuid);
        if (providers.size() <= 0) {
            return null;
        } else {
            return providers.get(0);
        }
    }

    @Override
    public String getProviderIdByFhirId(UUID fhirUuid) {
        List<Provider> providers = getBaseObjectDAO().getAllMatching("fhirUuid", fhirUuid);
        if (providers.size() <= 0) {
            return null;
        } else {
            return providers.get(0).getId();
        }
    }
}
