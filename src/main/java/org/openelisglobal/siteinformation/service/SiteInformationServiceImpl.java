package org.openelisglobal.siteinformation.service;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.common.util.ConfigurationSideEffects;
import org.openelisglobal.siteinformation.dao.SiteInformationDAO;
import org.openelisglobal.siteinformation.valueholder.SiteInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SiteInformationServiceImpl extends BaseObjectServiceImpl<SiteInformation, String>
        implements SiteInformationService {

    @Autowired
    private SiteInformationDAO siteInformationDAO;
    @Autowired
    private ConfigurationSideEffects configurationSideEffects;

    public SiteInformationServiceImpl() {
        super(SiteInformation.class);
    }

    @Override
    protected SiteInformationDAO getBaseObjectDAO() {
        return siteInformationDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SiteInformation> getPageOfSiteInformationByDomainName(int startingRecNo, String dbDomainName) {
        return siteInformationDAO.getMatchingOrderedPage("domain.name", dbDomainName, "name", false, startingRecNo);
    }

    @Override
    @Transactional(readOnly = true)
    public int getCountForDomainName(String dbDomainName) {
        return this.getCountMatching("domain.name", dbDomainName);
    }

    @Override
    @Transactional(readOnly = true)
    public SiteInformation getSiteInformationByName(String name) {
        return getMatch("name", name).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public void getData(SiteInformation siteInformation) {
        getBaseObjectDAO().getData(siteInformation);

    }

    @Override
    @Transactional(readOnly = true)
    public List<SiteInformation> getAllSiteInformation() {
        return getBaseObjectDAO().getAllSiteInformation();
    }

    @Override
    @Transactional(readOnly = true)
    public SiteInformation getSiteInformationById(String urlId) {
        return getBaseObjectDAO().getSiteInformationById(urlId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SiteInformation> getSiteInformationByDomainName(String domainName) {
        return getBaseObjectDAO().getSiteInformationByDomainName(domainName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SiteInformation> getPreviousSiteInformationRecord(String id) {
        return getBaseObjectDAO().getPreviousSiteInformationRecord(id);
    }

    @Override
    @Transactional
    public void persistData(SiteInformation siteInformation, boolean newSiteInformation) {
        if (newSiteInformation) {
            insert(siteInformation);
//			siteInformationDAO.insertData(siteInformation);
        } else {
            update(siteInformation);
//			siteInformationDAO.updateData(siteInformation);
        }

        configurationSideEffects.siteInformationChanged(siteInformation);
    }

}
