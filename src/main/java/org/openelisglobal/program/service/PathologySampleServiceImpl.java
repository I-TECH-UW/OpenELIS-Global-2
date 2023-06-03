package org.openelisglobal.program.service;

import java.util.List;

import javax.transaction.Transactional;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.program.dao.PathologySampleDAO;
import org.openelisglobal.program.valueholder.pathology.PathologySample;
import org.openelisglobal.program.valueholder.pathology.PathologySample.PathologyStatus;
import org.openelisglobal.systemuser.valueholder.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PathologySampleServiceImpl extends BaseObjectServiceImpl<PathologySample, Integer>
        implements PathologySampleService {
    @Autowired
    protected PathologySampleDAO baseObjectDAO;

    PathologySampleServiceImpl() {
        super(PathologySample.class);
    }

    @Override
    protected PathologySampleDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }

    @Override
    public List<PathologySample> getWithStatus(List<PathologyStatus> statuses) {
        return baseObjectDAO.getWithStatus(statuses);
    }

    @Transactional
    @Override
    public void assignTechnician(Integer pathologySampleId, SystemUser systemUser) {
        PathologySample pathologySample = get(pathologySampleId);
        pathologySample.setTechnician(systemUser);
    }

    @Transactional
    @Override
    public void assignPathologist(Integer pathologySampleId, SystemUser systemUser) {
        PathologySample pathologySample = get(pathologySampleId);
        pathologySample.setTechnician(systemUser);
    }

    @Override
    public Long getCountWithStatus(List<PathologyStatus> statuses) {
        return baseObjectDAO.getCountWithStatus(statuses);
    }
}
