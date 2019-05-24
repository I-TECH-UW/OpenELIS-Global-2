package spring.service.siteinformation;

import java.lang.String;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformationDomain;

public interface SiteInformationDomainService extends BaseObjectService<SiteInformationDomain> {
	SiteInformationDomain getByName(String name);
}
