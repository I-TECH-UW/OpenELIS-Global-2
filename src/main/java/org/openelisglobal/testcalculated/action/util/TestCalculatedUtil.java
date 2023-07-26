package org.openelisglobal.testcalculated.action.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.jfree.util.Log;
import org.openelisglobal.analysis.service.AnalysisService;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.StatusService.AnalysisStatus;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.note.service.NoteService;
import org.openelisglobal.note.service.NoteServiceImpl.NoteType;
import org.openelisglobal.note.valueholder.Note;
import org.openelisglobal.result.action.util.ResultSet;
import org.openelisglobal.result.service.ResultService;
import org.openelisglobal.result.valueholder.Result;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.test.valueholder.Test;
import org.openelisglobal.testcalculated.service.ResultCalculationService;
import org.openelisglobal.testcalculated.service.TestCalculationService;
import org.openelisglobal.testcalculated.valueholder.Calculation;
import org.openelisglobal.testcalculated.valueholder.Operation;
import org.openelisglobal.testcalculated.valueholder.ResultCalculation;
import org.openelisglobal.testresult.service.TestResultService;
import org.openelisglobal.testresult.valueholder.TestResult;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service
@DependsOn({ "springContext" })
public class TestCalculatedUtil {
    
    private TestResultService testResultService = SpringContext.getBean(TestResultService.class);
    
    private ResultCalculationService resultcalculationService = SpringContext.getBean(ResultCalculationService.class);
    
    private TestCalculationService calculationService = SpringContext.getBean(TestCalculationService.class);
    
    private TestService testService = SpringContext.getBean(TestService.class);
    
    private ResultService resultService = SpringContext.getBean(ResultService.class);
    
    private AnalysisService analysisService = SpringContext.getBean(AnalysisService.class);
    
    private NoteService noteService = SpringContext.getBean(NoteService.class);
    
    private static final String INCOMPLETE_VALUE = "XXXX";
    
    private String CALCULATION_SUBJECT = "Calculated Result Note";
    
    public List<Analysis> addNewTestsToDBForCalculatedTests(List<ResultSet> resultSetList, String sysUserId)
            throws IllegalStateException {
        for (ResultSet resultSet : resultSetList) {
            List<Calculation> calculations = calculationService.getAll();
            for (Calculation calculation : calculations) {
                if (!calculation.getActive()) {
                    break;
                }
                List<ResultCalculation> resultCalculations = resultcalculationService
                        .getResultCalculationByPatientAndCalculation(resultSet.patient, calculation);
                if (resultCalculations.isEmpty()) {
                    Boolean createResultCalculation = false;
                    for (Operation oper : calculation.getOperations()) {
                        if (oper.getType().equals(Operation.OperationType.TEST_RESULT)) {
                            if (Integer.valueOf(oper.getValue())
                                    .equals(Integer.valueOf(resultSet.result.getTestResult().getTest().getId()))) {
                                createResultCalculation = true;
                                break;
                            }
                        }
                    }
                    if (createResultCalculation) {
                        ResultCalculation calc = new ResultCalculation();
                        calc.setCalculation(calculation);
                        calc.setPatient(resultSet.patient);
                        Set<Test> tests = new HashSet<>();
                        calculation.getOperations().forEach(oper -> {
                            if (oper.getType().equals(Operation.OperationType.TEST_RESULT)) {
                                Test test = testService.getActiveTestById(Integer.valueOf(oper.getValue()));
                                tests.add(test);
                            }
                        });
                        calc.setTest(tests);
                        Map<Integer, Integer> map = new HashMap<>();
                        tests.forEach(test -> {
                            map.put(Integer.valueOf(test.getId()), null);
                        });
                        // insert innitail result value
                        map.put(Integer.valueOf(resultSet.result.getTestResult().getTest().getId()),
                            Integer.valueOf(resultSet.result.getId()));
                        calc.setTestResultMap(map);
                        resultcalculationService.insert(calc);
                    }
                    
                } else {
                    for (ResultCalculation resultCalculation : resultCalculations) {
                        resultCalculation.getTestResultMap().put(
                            Integer.valueOf(resultSet.result.getTestResult().getTest().getId()),
                            Integer.valueOf(resultSet.result.getId()));
                        resultcalculationService.update(resultCalculation);
                    }
                }
            }
        }
        
        for (ResultSet resultSet : resultSetList) {
            
            List<ResultCalculation> resultCalculations = resultcalculationService
                    .getResultCalculationByPatientAndTest(resultSet.patient, resultSet.result.getTestResult().getTest());
            
            if (!resultCalculations.isEmpty()) {
                for (ResultCalculation resultCalculation : resultCalculations) {
                    Boolean isMissingParams = false;
                    for (Map.Entry<Integer, Integer> entry : resultCalculation.getTestResultMap().entrySet()) {
                        if (entry.getValue() == null) {
                            isMissingParams = true;
                            break;
                        }
                    }
                    Calculation calculation = resultCalculation.getCalculation();
                    if (!isMissingParams) {
                        StringBuffer function = new StringBuffer();
                        calculation.getOperations().forEach(operation -> {
                            switch (operation.getType()) {
                                case TEST_RESULT:
                                    addNumericOperation(operation, resultCalculation, function,
                                        Operation.OperationType.TEST_RESULT.toString());
                                    break;
                                case INTEGER:
                                    function.append(Integer.valueOf(operation.getValue())).append(" ");
                                    break;
                                case MATH_FUNCTION:
                                    if (operation.getValue().equals(Operation.IN_NORMAL_RANGE.toLowerCase())) {
                                        int order = operation.getOrder();
                                        Operation prevOperation = calculation.getOperations().get(order - 1);
                                        addNumericOperation(prevOperation, resultCalculation, function, "IN_NORMAL_RANGE");
                                        
                                    } else if (operation.getValue().equals(Operation.OUTSIDE_NORMAL_RANGE.toString())) {
                                        int order = operation.getOrder();
                                        Operation prevOperation = calculation.getOperations().get(order - 1);
                                        addNumericOperation(prevOperation, resultCalculation, function,
                                            "OUTSIDE_NORMAL_RANGE");
                                    } else {
                                        function.append(operation.getValue()).append(" ");
                                    }
                                    break;
                                case PATIENT_ATTRIBUTE:
                                    if (operation.getValue().equals(Operation.PatientAttribute.AGE.toString())) {
                                        int age = DateUtil.getAgeInYears(
                                            new Date(resultSet.patient.getBirthDate().getTime()), new Date());
                                        function.append(age);
                                    }
                                    break;
                                
                            }
                        });
                        System.out.println(" >>>> FUNCTION ");
                        System.out.println(function);
                        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
                        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
                        String value = null;
                        try {
                            value = scriptEngine.eval(function.toString()).toString();
                        }
                        catch (ScriptException e) {
                            Log.error("Ivalid Calication Rule: " + calculation.getName(), e);
                        }
                        createCalculatedResult(resultCalculation, resultSet, calculation, value, sysUserId);
                        
                    } else {
                        createCalculatedResult(resultCalculation, resultSet, calculation, null, sysUserId);
                    }
                }
            }
        }
        
        return null;
    }
    
    private void createCalculatedResult(ResultCalculation resultCalculation, ResultSet resultSet, Calculation calculation,
            String value, String systemUserId) {
        Test test = testService.get(calculation.getTestId().toString());
        String resultType = testService.getResultType(test);
        if (test != null) {
            Analysis analysis = createCalculatedAnalysis(test, resultSet.result, value, calculation.getName());
            
            TestResult testResult = getTestResultForCalculation(calculation);
            Result result = resultCalculation.getResult() != null ? resultCalculation.getResult() : new Result();
            result.setAnalysis(analysis);
            result.setTestResult(testResult);
            result.setSysUserId(systemUserId);
            if (value != null) {
                if ("D".equals(resultType)) {
                    if (Boolean.valueOf(value)) {
                        result.setValue(calculation.getResult());
                    }
                } else {
                    result.setValue(value);
                }
            } else {
                result.setValue(INCOMPLETE_VALUE);
            }
            if (resultCalculation.getResult() != null) {
                resultService.update(result);
            } else {
                resultService.insert(result);
            }
            resultCalculation.setResult(result);
            resultcalculationService.update(resultCalculation);
        }
    }
    
    private void createInternalNote(Result result, Analysis newAnalysis, Analysis currentAnalysis, String calculatioName) {
        List<Note> notes = new ArrayList<>();
        notes.add(noteService.createSavableNote(newAnalysis, NoteType.INTERNAL,
            "Result Calculated From Calculation Rule :" + calculatioName, CALCULATION_SUBJECT, "1"));
        
        notes.add(noteService.createSavableNote(newAnalysis, NoteType.INTERNAL,
            "Calculated From " + currentAnalysis.getTest().getLocalizedReportingName().getLocalizedValue(),
            CALCULATION_SUBJECT, "1"));
        noteService.saveAll(notes);
    }
    
    private void createMissingValueInternalNote(Result result, Analysis newAnalysis, Analysis currentAnalysis,
            String calculatioName) {
        List<Note> notes = new ArrayList<>();
        notes.add(noteService.createSavableNote(newAnalysis, NoteType.INTERNAL,
            "Result Missing Calculation Parameters From Calculation Rule : " + calculatioName, CALCULATION_SUBJECT, "1"));
        
        notes.add(noteService.createSavableNote(newAnalysis, NoteType.INTERNAL,
            "Calculated From " + currentAnalysis.getTest().getLocalizedReportingName().getLocalizedValue(),
            CALCULATION_SUBJECT, "1"));
        noteService.saveAll(notes);
    }
    
    private TestResult getTestResultForCalculation(Calculation calculation) {
        Test test = testService.get(calculation.getTestId().toString());
        String resultType = testService.getResultType(test);
        if ("D".equals(resultType)) {
            TestResult testResult;
            testResult = testResultService.getTestResultsByTestAndDictonaryResult(test.getId(), calculation.getResult());
            return testResult;
        } else {
            List<TestResult> testResultList = testResultService.getActiveTestResultsByTest(test.getId());
            // we are assuming there is only one testResult for a numeric
            // type result
            if (!testResultList.isEmpty()) {
                return testResultList.get(0);
            }
        }
        
        return null;
    }
    
    private void addNumericOperation(Operation operation, ResultCalculation resultCalculation, StringBuffer function,
            String inputType) {
        Test test = testService.getActiveTestById(Integer.valueOf(operation.getValue()));
        if (test != null) {
            Integer resultId = resultCalculation.getTestResultMap().get(Integer.valueOf(test.getId()));
            Result result = resultService.get(resultId.toString());
            if (result != null) {
                if (result.getResultType().equals("N")) {
                    switch (inputType) {
                        case "TEST_RESULT":
                            function.append(result.getValue()).append(" ");
                            break;
                        case "IN_NORMAL_RANGE":
                            function.append(" >= ").append(result.getMinNormal()).append(" && ").append(result.getValue())
                                    .append(" <= ").append(result.getMaxNormal()).append(" ");
                            break;
                        case "OUTSIDE_NORMAL_RANGE":
                            function.append(" <= ").append(result.getMinNormal()).append(" || ").append(result.getValue())
                                    .append(" >= ").append(result.getMaxNormal()).append(" ");
                            break;
                    }
                }
            }
        }
        
    }
    
    private Analysis createCalculatedAnalysis(Test test, Result result, String value, String calculationName) {
        Analysis currentAnalysis = result.getAnalysis();
        Analysis generatedAnalysis = new Analysis();
        generatedAnalysis.setTest(test);
        generatedAnalysis.setIsReportable(currentAnalysis.getIsReportable());
        generatedAnalysis.setAnalysisType(currentAnalysis.getAnalysisType());
        generatedAnalysis.setRevision(currentAnalysis.getRevision());
        generatedAnalysis.setStartedDate(DateUtil.getNowAsSqlDate());
        generatedAnalysis.setStatusId(SpringContext.getBean(IStatusService.class).getStatusID(AnalysisStatus.NotStarted));
        generatedAnalysis.setParentAnalysis(currentAnalysis);
        generatedAnalysis.setParentResult(result);
        generatedAnalysis.setSampleItem(currentAnalysis.getSampleItem());
        generatedAnalysis.setTestSection(currentAnalysis.getTestSection());
        generatedAnalysis.setSampleTypeName(currentAnalysis.getSampleTypeName());
        analysisService.insert(generatedAnalysis);
        if (value != null) {
            createInternalNote(result, generatedAnalysis, currentAnalysis, calculationName);
        } else {
            createMissingValueInternalNote(result, generatedAnalysis, currentAnalysis, calculationName);
        }
        return generatedAnalysis;
    }
}
