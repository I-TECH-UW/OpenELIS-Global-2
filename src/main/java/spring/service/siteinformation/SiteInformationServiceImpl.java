package spring.service.siteinformation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

@Service
public class SiteInformationServiceImpl extends BaseObjectServiceImpl<SiteInformation>
		implements SiteInformationService {

	@Autowired
	private SiteInformationDAO siteInformationDAO;

	public SiteInformationServiceImpl() {
		super(SiteInformation.class);
	}

	@Override
	protected SiteInformationDAO getBaseObjectDAO() {
		return siteInformationDAO;
	}

	@Override
	@Transactional
	public List<SiteInformation> getPageOfSiteInformationByDomainName(int startingRecNo, String dbDomainName) {
		return siteInformationDAO.getMatchingOrderedPage("domain.name", dbDomainName, "name", false, startingRecNo);
	}

	@Override
	@Transactional
	public int getCountForDomainName(String dbDomainName) {
		return this.getCountMatching("domain.name", dbDomainName);
	}

	@Override
	@Transactional
	public SiteInformation getSiteInformationByName(String name) {
		return getMatch("name", name).get();
	}

}
