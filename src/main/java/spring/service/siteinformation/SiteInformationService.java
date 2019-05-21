package spring.service.siteinformation;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

public interface SiteInformationService extends BaseObjectService<SiteInformation> {

	List<SiteInformation> getPageOfSiteInformationByDomainName(int startingRecNo, String dbDomainName);

	int getCountForDomainName(String dbDomainName);

	SiteInformation getSiteInformationByName(String name);

}
