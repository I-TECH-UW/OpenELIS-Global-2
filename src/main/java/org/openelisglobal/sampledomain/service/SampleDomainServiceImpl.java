package org.openelisglobal.sampledomain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.sampledomain.dao.SampleDomainDAO;
import org.openelisglobal.sampledomain.valueholder.SampleDomain;

@Service
public class SampleDomainServiceImpl extends BaseObjectServiceImpl<SampleDomain, String>
        implements SampleDomainService {
    @Autowired
    protected SampleDomainDAO baseObjectDAO;

    SampleDomainServiceImpl() {
        super(SampleDomain.class);
    }

    @Override
    protected SampleDomainDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }
}
