package spring.service.siteinformation;

import java.util.List;

import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

public interface SiteInformationService {
	
	void save(SiteInformation siteInformation);
	
	SiteInformation getData(SiteInformation siteInformation);
	
	List<SiteInformation> getAll();
}
