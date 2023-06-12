package org.openelisglobal.program.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.program.controller.pathology.PathologySampleForm;
import org.openelisglobal.program.dao.PathologySampleDAO;
import org.openelisglobal.program.valueholder.pathology.PathologyConclusion;
import org.openelisglobal.program.valueholder.pathology.PathologyConclusion.ConclusionType;
import org.openelisglobal.program.valueholder.pathology.PathologyRequest;
import org.openelisglobal.program.valueholder.pathology.PathologyRequest.RequestType;
import org.openelisglobal.program.valueholder.pathology.PathologySample;
import org.openelisglobal.program.valueholder.pathology.PathologySample.PathologyStatus;
import org.openelisglobal.program.valueholder.pathology.PathologyTechnique;
import org.openelisglobal.program.valueholder.pathology.PathologyTechnique.TechniqueType;
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

    @Transactional
    @Override
    public void updateWithFormValues(Integer pathologySampleId, PathologySampleForm form) {
        PathologySample pathologySample = get(pathologySampleId);
        pathologySample.getBlocks().removeAll(pathologySample.getBlocks());
        if (form.getBlocks() != null)
            pathologySample.getBlocks().addAll(form.getBlocks());
        pathologySample.getSlides().removeAll(pathologySample.getSlides());
        if (form.getSlides() != null)
            pathologySample.getSlides().addAll(form.getSlides());
        pathologySample.setGrossExam(form.getGrossExam());
        pathologySample.setMicroscopyExam(form.getMicroscopyExam());
        pathologySample.getConclusions().removeAll(pathologySample.getConclusions());
        pathologySample.getConclusions().add(createConclusion(form.getConclusionText(), ConclusionType.TEXT));
        if (form.getConclusions() != null)
            pathologySample.getConclusions().addAll(form.getConclusions().stream()
                    .map(e -> createConclusion(e, ConclusionType.DICTIONARY)).collect(Collectors.toList()));
        pathologySample.getRequests().removeAll(pathologySample.getRequests());
        if (form.getRequests() != null)
            pathologySample.getRequests().addAll(form.getRequests().stream()
                    .map(e -> createRequest(e, RequestType.DICTIONARY)).collect(Collectors.toList()));
        pathologySample.getTechniques().removeAll(pathologySample.getTechniques());
        if (form.getTechniques() != null)
            pathologySample.getTechniques().addAll(form.getTechniques().stream()
                    .map(e -> createTechnique(e, TechniqueType.DICTIONARY)).collect(Collectors.toList()));
    }

    public PathologyConclusion createConclusion(String text, ConclusionType type) {
        PathologyConclusion conclusion = new PathologyConclusion();
        conclusion.setValue(text);
        conclusion.setType(type);
        return conclusion;
    }

    public PathologyRequest createRequest(String text, RequestType type) {
        PathologyRequest request = new PathologyRequest();
        request.setValue(text);
        request.setType(type);
        return request;
    }

    public PathologyTechnique createTechnique(String text, TechniqueType type) {
        PathologyTechnique request = new PathologyTechnique();
        request.setValue(text);
        request.setType(type);
        return request;
    }
}
