package spring.service.siteinformation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

@Service
public class SiteInformationServiceImpl implements SiteInformationService {
	
	   @Autowired
	   private SiteInformationDAO siteInformationDAO;

	   @Transactional
       public void save(SiteInformation siteInformation) {
		   siteInformationDAO.insert(siteInformation);
	   }
	   
       @Transactional
	   public SiteInformation getData(SiteInformation siteInformation) {
		   return siteInformationDAO.get(siteInformation.getId()).get();
	   }

	   @Transactional(readOnly = true)
	   public List<SiteInformation> getAll() {
	      return siteInformationDAO.getAll();
	   }

}
