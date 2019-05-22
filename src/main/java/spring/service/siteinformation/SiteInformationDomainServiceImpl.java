package spring.service.siteinformation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDomainDAO;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformationDomain;

@Service
public class SiteInformationDomainServiceImpl extends BaseObjectServiceImpl<SiteInformationDomain>
		implements SiteInformationDomainService {
	@Autowired
	protected SiteInformationDomainDAO baseObjectDAO;

	SiteInformationDomainServiceImpl() {
		super(SiteInformationDomain.class);
	}

	@Override
	protected SiteInformationDomainDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional 
	public SiteInformationDomain getByName(String name) {
		return getMatch("name", name).get();
	}
}
