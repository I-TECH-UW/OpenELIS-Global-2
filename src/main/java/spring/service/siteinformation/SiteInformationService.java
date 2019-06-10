package spring.service.siteinformation;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

public interface SiteInformationService extends BaseObjectService<SiteInformation, String> {
	void getData(SiteInformation siteInformation);

	void deleteData(String siteInformationId, String currentUserId);

	void updateData(SiteInformation siteInformation);

	boolean insertData(SiteInformation siteInformation);

	SiteInformation getSiteInformationByName(String siteName);

	List<SiteInformation> getAllSiteInformation();

	int getCountForDomainName(String domainName);

	SiteInformation getSiteInformationById(String urlId);

	List<SiteInformation> getNextSiteInformationRecord(String id);

	List<SiteInformation> getSiteInformationByDomainName(String domainName);

	List<SiteInformation> getPreviousSiteInformationRecord(String id);

	List<SiteInformation> getPageOfSiteInformationByDomainName(int startingRecNo, String domainName);

	void persistData(SiteInformation siteInformation, boolean newSiteInformation);
}
