package spring.service.siteinformation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
