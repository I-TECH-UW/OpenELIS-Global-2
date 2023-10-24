package org.openelisglobal.reports.action.implementation;

import org.openelisglobal.program.service.ImmunohistochemistrySampleService;
import org.openelisglobal.program.service.PathologyDisplayService;
import org.openelisglobal.program.valueholder.immunohistochemistry.ImmunohistochemistrySample;
import org.openelisglobal.program.valueholder.pathology.PathologyConclusion;
import org.openelisglobal.program.valueholder.pathology.PathologySample;
import org.openelisglobal.program.valueholder.pathology.PathologyConclusion.ConclusionType;
import org.openelisglobal.reports.action.implementation.reportBeans.ProgramSampleReportData;
import org.openelisglobal.reports.form.ReportForm;
import org.openelisglobal.result.service.ResultService;
import org.openelisglobal.result.valueholder.Result;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.test.service.TestServiceImpl;
import org.openelisglobal.typeoftestresult.service.TypeOfTestResultServiceImpl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.StatusService.AnalysisStatus;
import org.openelisglobal.dictionary.valueholder.Dictionary;

public class PatientImmunoChemistryReport extends PatientProgramReport{

    protected  ImmunohistochemistrySampleService immunohistochemistrySampleService = SpringContext.getBean(ImmunohistochemistrySampleService.class);
    protected  PathologyDisplayService pathologyDisplayService = SpringContext.getBean(PathologyDisplayService.class);
    private ImmunohistochemistrySample immunohistochemistrySample;


    private static Set<Integer> analysisStatusIds;

     static {
        analysisStatusIds = new HashSet<>();
        analysisStatusIds.add(Integer
                .parseInt(SpringContext.getBean(IStatusService.class).getStatusID(AnalysisStatus.BiologistRejected)));
        analysisStatusIds.add(
                Integer.parseInt(SpringContext.getBean(IStatusService.class).getStatusID(AnalysisStatus.Finalized)));
        analysisStatusIds.add(Integer.parseInt(
                SpringContext.getBean(IStatusService.class).getStatusID(AnalysisStatus.NonConforming_depricated)));
        analysisStatusIds.add(
                Integer.parseInt(SpringContext.getBean(IStatusService.class).getStatusID(AnalysisStatus.NotStarted)));
        analysisStatusIds.add(Integer
                .parseInt(SpringContext.getBean(IStatusService.class).getStatusID(AnalysisStatus.TechnicalAcceptance)));
        analysisStatusIds.add(
                Integer.parseInt(SpringContext.getBean(IStatusService.class).getStatusID(AnalysisStatus.Canceled)));
        analysisStatusIds.add(Integer
                .parseInt(SpringContext.getBean(IStatusService.class).getStatusID(AnalysisStatus.TechnicalRejected)));

    }

    @Override
    protected String getReportName() {
        return "PatientImmunoChemistryReport";
    }

    @Override
    protected void setAdditionalReportItems() {
        List<Analysis> analyses  = analysisService.getAnalysesBySampleIdAndStatusId(sampleService.getId(sample) ,analysisStatusIds);

        List<ProgramSampleReportData.Result> resultsData = new ArrayList<>();
        analyses.forEach(analysis -> {
            ProgramSampleReportData.Result resultData = new ProgramSampleReportData.Result();
            resultData.setTest(TestServiceImpl.getUserLocalizedReportingTestName(analysis.getTest()));
            List<Result> resultList = analysisService.getResults(analysis);
            setAppropriateResults(resultList ,resultData);
            resultData.setUom(getUnitOfMeasure(analysis.getTest()));
            resultsData.add(resultData);
        });
        data.setResults(resultsData);
       
        if(immunohistochemistrySample.getPathologySample() != null){
             PathologySample pathologySample = pathologyDisplayService.getPathologySampleWithLoadedAtttributes(immunohistochemistrySample.getPathologySample().getId());
             Optional<PathologyConclusion> conclusion = pathologySample.getConclusions().stream()
                .filter(e -> e.getType() == ConclusionType.TEXT).findFirst();
            data.setTextConclusion(conclusion.get().getValue());
        }
    }

    @Override
    protected void innitializeSample(ReportForm form) {
        immunohistochemistrySample = immunohistochemistrySampleService.get(Integer.valueOf(form.getProgramSampleId()));
        sample = immunohistochemistrySample.getSample();
    }

     private void setAppropriateResults(List<Result> resultList ,ProgramSampleReportData.Result resultData) {
        String reportResult = "";
        if (!resultList.isEmpty()) {

            // If only one result just get it and get out
            if (resultList.size() == 1) {
                Result result = resultList.get(0);
                if (TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(result.getResultType())) {
                    Dictionary dictionary = new Dictionary();
                    dictionary.setId(result.getValue());
                    dictionaryService.getData(dictionary);
                   

                    if (result.getAnalyte() != null && "Conclusion".equals(result.getAnalyte().getAnalyteName())) {
                        currentConclusion = dictionary.getId() != null ? dictionary.getLocalizedName() : "";
                    } else {
                        reportResult = dictionary.getId() != null ? dictionary.getLocalizedName() : "";
                    }
                } else {
                    ResultService resultResultService = SpringContext.getBean(ResultService.class);
                    reportResult = resultResultService.getResultValue(result, true);
    
                }
            } else {
                // If multiple results it can be a quantified result, multiple
                // results with quantified other results or it can be a
                // conclusion
                ResultService resultResultService = SpringContext.getBean(ResultService.class);
                Result result = resultList.get(0);

                if (TypeOfTestResultServiceImpl.ResultType.DICTIONARY
                        .matches(resultResultService.getTestType(result))) {
                   // data.setAbnormalResult(resultResultService.isAbnormalDictionaryResult(result));
                    List<Result> dictionaryResults = new ArrayList<>();
                    Result quantification = null;
                    for (Result sibResult : resultList) {
                        if (TypeOfTestResultServiceImpl.ResultType.DICTIONARY.matches(sibResult.getResultType())) {
                            dictionaryResults.add(sibResult);
                        } else if (TypeOfTestResultServiceImpl.ResultType.ALPHA.matches(sibResult.getResultType())
                                && sibResult.getParentResult() != null) {
                            quantification = sibResult;
                        }
                    }

                    Dictionary dictionary = new Dictionary();
                    for (Result sibResult : dictionaryResults) {
                        dictionary.setId(sibResult.getValue());
                        dictionaryService.getData(dictionary);
                        if (sibResult.getAnalyte() != null
                                && "Conclusion".equals(sibResult.getAnalyte().getAnalyteName())) {
                            currentConclusion = dictionary.getId() != null ? dictionary.getLocalizedName() : "";
                        } else {
                            reportResult = dictionary.getId() != null ? dictionary.getLocalizedName() : "";
                            if (quantification != null
                                    && quantification.getParentResult().getId().equals(sibResult.getId())) {
                                reportResult += ": " + quantification.getValue(true);
                            }
                        }
                    }
                } else if (TypeOfTestResultServiceImpl.ResultType
                        .isMultiSelectVariant(resultResultService.getTestType(result))) {
                    Dictionary dictionary = new Dictionary();
                    StringBuilder multiResult = new StringBuilder();

                    Collections.sort(resultList, new Comparator<Result>() {
                        @Override
                        public int compare(Result o1, Result o2) {
                            if (o1.getGrouping() == o2.getGrouping()) {
                                return Integer.parseInt(o1.getId()) - Integer.parseInt(o2.getId());
                            } else {
                                return o1.getGrouping() - o2.getGrouping();
                            }
                        }
                    });

                    Result quantifiedResult = null;
                    for (Result subResult : resultList) {
                        if (TypeOfTestResultServiceImpl.ResultType.ALPHA.matches(subResult.getResultType())) {
                            quantifiedResult = subResult;
                            resultList.remove(subResult);
                            break;
                        }
                    }
                    int currentGrouping = resultList.get(0).getGrouping();
                    for (Result subResult : resultList) {
                        if (subResult.getGrouping() != currentGrouping) {
                            currentGrouping = subResult.getGrouping();
                            multiResult.append("-------\n");
                        }
                        dictionary.setId(subResult.getValue());
                        dictionaryService.getData(dictionary);

                        if (dictionary.getId() != null) {
                            multiResult.append(dictionary.getLocalizedName());
                            if (quantifiedResult != null
                                    && quantifiedResult.getParentResult().getId().equals(subResult.getId())
                                    && !GenericValidator.isBlankOrNull(quantifiedResult.getValue())) {
                                multiResult.append(": ");
                                multiResult.append(quantifiedResult.getValue(true));
                            }
                            multiResult.append("\n");
                        }
                    }

                    if (multiResult.length() > 1) {
                        // remove last "\n"
                        multiResult.setLength(multiResult.length() - 1);
                    }

                    reportResult = multiResult.toString();
                }
            }
        }
        resultData.setResult(reportResult);

    }
    
}
