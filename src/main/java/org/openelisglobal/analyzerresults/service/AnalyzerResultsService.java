package org.openelisglobal.analyzerresults.service;

import java.util.List;

import org.openelisglobal.result.controller.AnalyzerResultsController.SampleGrouping;
import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.analyzerresults.valueholder.AnalyzerResults;

public interface AnalyzerResultsService extends BaseObjectService<AnalyzerResults, String> {

    AnalyzerResults readAnalyzerResults(String idString);

    List<AnalyzerResults> getResultsbyAnalyzer(String analyzerId);

    void insertAnalyzerResults(List<AnalyzerResults> results, String sysUserId);

    void persistAnalyzerResults(List<AnalyzerResults> deletableAnalyzerResults, List<SampleGrouping> sampleGroupList,
            String sysUserId);
}
