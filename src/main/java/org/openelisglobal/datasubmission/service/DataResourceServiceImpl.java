package org.openelisglobal.datasubmission.service;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.datasubmission.dao.DataResourceDAO;
import org.openelisglobal.datasubmission.valueholder.DataResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataResourceServiceImpl extends BaseObjectServiceImpl<DataResource, String>
        implements DataResourceService {
    @Autowired
    protected DataResourceDAO baseObjectDAO;

    DataResourceServiceImpl() {
        super(DataResource.class);
    }

    @Override
    protected DataResourceDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }

}
