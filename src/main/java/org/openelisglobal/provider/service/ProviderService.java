package org.openelisglobal.provider.service;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.person.valueholder.Person;
import org.openelisglobal.provider.valueholder.Provider;

public interface ProviderService extends BaseObjectService<Provider, String> {
    void getData(Provider provider);

    List<Provider> getPageOfProviders(int startingRecNo);

    List<Provider> getAllProviders();

    Provider getProviderByPerson(Person person);
}
