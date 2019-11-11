package org.openelisglobal.provider.service;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.person.valueholder.Person;
import org.openelisglobal.provider.valueholder.Provider;

public interface ProviderService extends BaseObjectService<Provider, String> {
    void getData(Provider provider);

    List getPageOfProviders(int startingRecNo);

    List getAllProviders();





    Provider getProviderByPerson(Person person);
}
