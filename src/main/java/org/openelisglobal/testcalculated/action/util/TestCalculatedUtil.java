package org.openelisglobal.testcalculated.action.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.util.DateUtil;
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
                        System.out.println(calc.getTestResultMap());
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
            List<ResultCalculation> resultCalculations = resultcalculationService
                    .getResultCalculationByPatientAndTest(resultSet.patient, resultSet.result.getTestResult().getTest());
            
            if (!resultCalculations.isEmpty()) {
                for (ResultCalculation resultCalculation : resultCalculations) {
                    Boolean missingParams = false;
                    for (Map.Entry<Integer, Integer> entry : resultCalculation.getTestResultMap().entrySet()) {
                        Integer key = entry.getKey();
                        Integer value = entry.getValue();
                        if (entry.getValue() == null) {
                            missingParams = true;
                        }
                    }
                    if (!missingParams) {
                        Calculation calculation = resultCalculation.getCalculation();
                        
                        StringBuffer function = new StringBuffer();
                        calculation.getOperations().forEach(operation -> {
                            switch (operation.getType()) {
                                case TEST_RESULT:
                                     addNumericOperation(operation ,resultCalculation ,function ,Operation.OperationType.TEST_RESULT.toString());
                                    break;
                                case INTEGER:
                                    function.append(Integer.valueOf(operation.getValue())).append(" ");
                                    break;
                                case MATH_FUNCTION:
                                    if (operation.getValue().equals(Operation.IN_NORMAL_RANGE.toLowerCase())) {
                                        int order = operation.getOrder();
                                        Operation prevOperation = calculation.getOperations().get(order - 1);
                                        addNumericOperation(prevOperation ,resultCalculation ,function ,"IN_NORMAL_RANGE");
                                        
                                    } else if (operation.getValue().equals(Operation.OUTSIDE_NORMAL_RANGE.toString())) {
                                        int order = operation.getOrder();
                                        Operation prevOperation = calculation.getOperations().get(order - 1);
                                        addNumericOperation(prevOperation ,resultCalculation ,function , "OUTSIDE_NORMAL_RANGE");
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
                        System.out.println(function);
                    } else {
                    }
                }
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
    
}
