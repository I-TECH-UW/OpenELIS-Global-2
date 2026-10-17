package org.openelisglobal.reports.action.implementation;

import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.analysis.service.AnalysisService;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.reports.action.implementation.reportBeans.ClinicalPatientData;
import org.openelisglobal.result.service.ResultService;
import org.openelisglobal.result.valueholder.Result;
import org.openelisglobal.testresultcomponent.service.TestResultComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * The patient report prints each part of a multi-part result with that part's
 * own unit, as Results Entry and Validation show it. Fixture:
 * {@code testdata/component-units.xml} — a viral load in copies/ml and its Ct,
 * which has no unit.
 */
@Transactional
public class PatientReportComponentUnitsTest extends BaseWebContextSensitiveTest {

    @Autowired
    private AnalysisService analysisService;

    @Autowired
    private ResultService resultService;

    @Autowired
    private TestResultComponentService testResultComponentService;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        executeDataSetWithStateManagement("testdata/component-units.xml");
    }

    @Test
    public void eachPrintedPartCarriesItsOwnUnit() {
        PatientReport report = new PatientCILNSPClinical_vreduit();
        Analysis analysis = analysisService.get("100");
        ReflectionTestUtils.setField(report, "currentAnalysis", analysis);
        ReflectionTestUtils.setField(report, "currentPatient", new Patient());
        ClinicalPatientData row = new ClinicalPatientData();

        ReflectionTestUtils.invokeMethod(report, "buildMultiComponentRow", resultService.getResultsByAnalysis(analysis),
                testResultComponentService.getActiveComponentsByTestId("1"), row);

        List<String> parts = List.of(row.getResult().split("\n")).stream().map(line -> line.split(":")[0]).toList();
        assertEquals(List.of("Viral load", "Ct"), parts);
        assertEquals(List.of("cp/ml VCU", ""), List.of(row.getUom().split("\n", -1)));
    }

    /**
     * A part the report carries but that has no result yet is printed as still in
     * progress, not dropped as if it had never been ordered.
     */
    @Test
    public void aPartWithoutAResultIsPrintedAsInProgress() {
        PatientReport report = new PatientCILNSPClinical_vreduit();
        Analysis analysis = analysisService.get("100");
        ReflectionTestUtils.setField(report, "currentAnalysis", analysis);
        ReflectionTestUtils.setField(report, "currentPatient", new Patient());
        ClinicalPatientData row = new ClinicalPatientData();
        List<Result> loadOnly = resultService.getResultsByAnalysis(analysis).stream()
                .filter(result -> "vcu-load".equals(result.getTestResult().getComponentId())).toList();

        ReflectionTestUtils.invokeMethod(report, "buildMultiComponentRow", loadOnly,
                testResultComponentService.getActiveComponentsByTestId("1"), row);

        List<String> lines = List.of(row.getResult().split("\n"));
        assertEquals(2, lines.size());
        assertEquals("Viral load", lines.get(0).split(":")[0]);
        assertEquals("Ct: In progress", lines.get(1));
        assertEquals(List.of("cp/ml VCU", ""), List.of(row.getUom().split("\n", -1)));
        assertEquals("In progress", row.getComponents().get(1).value());
    }
}
