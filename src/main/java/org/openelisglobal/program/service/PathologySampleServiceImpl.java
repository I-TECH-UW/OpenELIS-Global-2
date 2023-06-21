package org.openelisglobal.program.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.analysis.service.AnalysisService;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.ResultSaveService;
import org.openelisglobal.common.services.StatusService.AnalysisStatus;
import org.openelisglobal.common.services.beanAdapters.ResultSaveBeanAdapter;
import org.openelisglobal.common.services.serviceBeans.ResultSaveBean;
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
import org.openelisglobal.result.action.util.ResultsLoadUtility;
import org.openelisglobal.result.valueholder.Result;
import org.openelisglobal.resultvalidation.service.ResultValidationService;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.systemuser.service.SystemUserService;
import org.openelisglobal.systemuser.valueholder.SystemUser;
import org.openelisglobal.test.beanItems.TestResultItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PathologySampleServiceImpl extends BaseObjectServiceImpl<PathologySample, Integer>
        implements PathologySampleService {
    @Autowired
    protected PathologySampleDAO baseObjectDAO;
    @Autowired
    protected SystemUserService systemUserService;
    @Autowired
    private ResultValidationService resultValidationService;
    @Autowired
    private AnalysisService analysisService;

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
        if (!GenericValidator.isBlankOrNull(form.getAssignedPathologistId())) {
            pathologySample.setPathologist(systemUserService.get(form.getAssignedPathologistId()));
        }
        if (!GenericValidator.isBlankOrNull(form.getAssignedTechnicianId())) {
            pathologySample.setTechnician(systemUserService.get(form.getAssignedTechnicianId()));
        }
        pathologySample.getBlocks().removeAll(pathologySample.getBlocks());
        if (form.getBlocks() != null)
            form.getBlocks().stream().forEach(e -> e.setId(null));
            pathologySample.getBlocks().addAll(form.getBlocks());
        pathologySample.getSlides().removeAll(pathologySample.getSlides());
        if (form.getSlides() != null)
            form.getSlides().stream().forEach(e -> e.setId(null));
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
        if (form.getRelease()) {
            validatePathologySample(pathologySample, form);
        }
        if (form.getReferToImmunoHistoChemistry()) {
            referToImmunoHistoChemistry(pathologySample, form);
        }
    }

    private void validatePathologySample(PathologySample pathologySample, PathologySampleForm form) {
        Sample sample = pathologySample.getSample();
        List<Result> resultUpdateList = new ArrayList<>();
        List<Analysis> analysisUpdateList = new ArrayList<>();

        ResultsLoadUtility resultsUtility = SpringContext.getBean(ResultsLoadUtility.class);
        List<TestResultItem> testResultItems = resultsUtility.getGroupedTestsForSample(sample);
        for (TestResultItem testResultItem : testResultItems) {
            Analysis analysis = analysisService.get(sample.getId());
            ResultSaveBean bean = ResultSaveBeanAdapter.fromTestResultItem(testResultItem);
            ResultSaveService resultSaveService = new ResultSaveService();
            resultSaveService.setAnalysis(analysis);
            List<Result> results = resultSaveService.createResultsFromTestResultItem(bean, new ArrayList<>());
            for (Result result : results) {
                // this code is pulled from LogbookResultsController
//                addResult(result, testResultItem, analysis, results.size() > 1, actionDataSet, useTechnicianName);
//
//                if (analysisShouldBeUpdated(testResultItem, result, supportReferrals)) {
//                    updateAnalysis(testResultItem, testResultItem.getTestDate(), analysis, statusRuleSet);
//                }
            }
            analysis.setStatusId(SpringContext.getBean(IStatusService.class).getStatusID(AnalysisStatus.Finalized));
            analysis.setReleasedDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        }

    }

    private void referToImmunoHistoChemistry(PathologySample pathologySample, PathologySampleForm form) {
        // TODO Auto-generated method stub

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
