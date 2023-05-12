package org.openelisglobal.common.rest.provider;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.rest.provider.bean.patientHistory.PanelDisplay;
import org.openelisglobal.common.rest.provider.bean.patientHistory.ResultDisplay;
import org.openelisglobal.common.rest.provider.bean.patientHistory.ResultTree;
import org.openelisglobal.common.rest.provider.bean.patientHistory.TestDisplay;
import org.openelisglobal.panel.valueholder.Panel;
import org.openelisglobal.result.service.ResultService;
import org.openelisglobal.result.valueholder.Result;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.test.valueholder.Test;
import org.openelisglobal.test.valueholder.TestSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rest/")
public class ResultsTreeProviderRestController {
    
    @Autowired
    ResultService resultService;
    
    @Autowired
    SampleHumanService sampleHumanService;
    
    @GetMapping(value = "result-tree", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ResultTree> getResultTreeArray(@RequestParam String patientId) {
        List<Sample> samples = sampleHumanService.getSamplesForPatient(patientId);
        List<Result> results = new ArrayList<>();
        samples.forEach(sample -> {
            results.addAll(resultService.getResultsForSample(sample));
        });
        
        List<Analysis> analyses = new ArrayList<>();
        Map<Panel, Set<Result>> panelResultMap = new HashMap<>();
        Map<TestSection, Set<Panel>> testSectionPanelMap = new HashMap<>();
        
        Set<Panel> filteredPanels;
        Set<Result> filteredResults;
        TestSection testSection = null;
        Panel panel = null;
        
        for (Result result : results) {
            analyses.add(result.getAnalysis());
            testSection = result.getAnalysis().getTestSection();
            panel = result.getAnalysis().getPanel();
            
            if (testSectionPanelMap.containsKey(testSection)) {
                testSectionPanelMap.get(testSection).add(panel);
            } else {
                filteredPanels = new HashSet<>();
                filteredPanels.add(panel);
                testSectionPanelMap.put(testSection, filteredPanels);
                
            }
            
            if (panelResultMap.containsKey(panel)) {
                panelResultMap.get(panel).add(result);
            } else {
                filteredResults = new HashSet<>();
                filteredResults.add(result);
                panelResultMap.put(panel, filteredResults);
                
            }
            
        }
        
        List<ResultTree> resultTrees = new ArrayList<>();
        
        for (Map.Entry<TestSection, Set<Panel>> entry : testSectionPanelMap.entrySet()) {
            List<PanelDisplay> panelDisplays = new ArrayList<>();
            
            for (Panel panelEntry : entry.getValue()) {
                Map<Test, Set<Result>> testResultMap = new HashMap<>();
                
                for (Result resultEntry : panelResultMap.get(panelEntry)) {
                    Test test = resultEntry.getAnalysis().getTest();
                    
                    if (!testResultMap.containsKey(test)) {
                        filteredResults = new HashSet<>();
                        filteredResults.add(resultEntry);
                        testResultMap.put(test, filteredResults);
                    } else {
                        testResultMap.get(test).add(resultEntry);
                    }
                }
                
                List<TestDisplay> testDisplays = new ArrayList<>();
                
                for (Map.Entry<Test, Set<Result>> testResultentry : testResultMap.entrySet()) {
                    List<ResultDisplay> resultDisplays = new ArrayList<>();
                    
                    for (Result result : testResultentry.getValue()) {
                        ResultDisplay resultDisplay = new ResultDisplay();
                        resultDisplay.setValue(result.getValue(true));
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                        resultDisplay.setObsDatetime(dateFormat.format(result.getLastupdated()));
                        resultDisplays.add(resultDisplay);
                    }
                    
                    TestDisplay testDisplay = new TestDisplay();
                    testDisplay.setDisplay(testResultentry.getKey().getLocalizedName());
                    testDisplay.setConceptUuid(testResultentry.getKey().getId());
                    testDisplay.setDatatype(testResultentry.getValue().iterator().next().getResultType());
                    testDisplay.setHiNormal(testResultentry.getValue().iterator().next().getMaxNormal());
                    testDisplay.setLowNormal(testResultentry.getValue().iterator().next().getMinNormal());
                    testDisplay.setHighCritical(testResultentry.getValue().iterator().next().getMaxNormal());
                    testDisplay.setLowCritical(testResultentry.getValue().iterator().next().getMinNormal());
                    testDisplay.setLowAbsolute(0.0);
                    testDisplay.setUnits(testResultentry.getKey().getUnitOfMeasure() != null
                            ? testResultentry.getKey().getUnitOfMeasure().getName()
                            : "");
                    testDisplay.setObs(resultDisplays);
                    testDisplays.add(testDisplay);
                }
                
                PanelDisplay panelDisplay = new PanelDisplay();
                panelDisplay.setDisplay(panelEntry != null ? panelEntry.getLocalizedName() : "NO PANNEL");
                panelDisplay.setSubSets(testDisplays);
                panelDisplays.add(panelDisplay);
            }
            
            ResultTree resultTree = new ResultTree();
            resultTree.setDisplay(entry.getKey().getLocalizedName());
            resultTree.setSubSets(panelDisplays);
            resultTrees.add(resultTree);
        }
        
        return resultTrees;
    }
    
}
