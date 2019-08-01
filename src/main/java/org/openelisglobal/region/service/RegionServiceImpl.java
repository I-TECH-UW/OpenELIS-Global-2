package org.openelisglobal.region.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.region.dao.RegionDAO;
import org.openelisglobal.region.valueholder.Region;

@Service
public class RegionServiceImpl extends BaseObjectServiceImpl<Region, String> implements RegionService {
    @Autowired
    protected RegionDAO baseObjectDAO;

    RegionServiceImpl() {
        super(Region.class);
    }

    @Override
    protected RegionDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }
}
