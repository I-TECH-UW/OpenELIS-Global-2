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
		return getMatch("name", name).orElse(null);
	}

	@Override
	public void getData(SiteInformation siteInformation) {
		getBaseObjectDAO().getData(siteInformation);

	}

	@Override
	public void deleteData(String siteInformationId, String currentUserId) {
		getBaseObjectDAO().deleteData(siteInformationId, currentUserId);

	}

	@Override
	public void updateData(SiteInformation siteInformation) {
		getBaseObjectDAO().updateData(siteInformation);

	}

	@Override
	public boolean insertData(SiteInformation siteInformation) {
		return getBaseObjectDAO().insertData(siteInformation);
	}

	@Override
	public List<SiteInformation> getAllSiteInformation() {
		return getBaseObjectDAO().getAllSiteInformation();
	}

	@Override
	public SiteInformation getSiteInformationById(String urlId) {
		return getBaseObjectDAO().getSiteInformationById(urlId);
	}

	@Override
	public List<SiteInformation> getNextSiteInformationRecord(String id) {
		return getBaseObjectDAO().getNextSiteInformationRecord(id);
	}

	@Override
	public List<SiteInformation> getSiteInformationByDomainName(String domainName) {
		return getBaseObjectDAO().getSiteInformationByDomainName(domainName);
	}

	@Override
	public List<SiteInformation> getPreviousSiteInformationRecord(String id) {
		return getBaseObjectDAO().getPreviousSiteInformationRecord(id);
	}

}
