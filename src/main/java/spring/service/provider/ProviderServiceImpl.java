package spring.service.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.provider.dao.ProviderDAO;
import us.mn.state.health.lims.provider.valueholder.Provider;

@Service
public class ProviderServiceImpl extends BaseObjectServiceImpl<Provider> implements ProviderService {
  @Autowired
  protected ProviderDAO baseObjectDAO;

  ProviderServiceImpl() {
    super(Provider.class);
  }

  @Override
  protected ProviderDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
