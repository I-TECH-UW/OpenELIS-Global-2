package org.openelisglobal.testcalculated.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.openelisglobal.analysis.service.AnalysisService;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.StatusService.AnalysisStatus;
import org.openelisglobal.note.service.NoteService;
import org.openelisglobal.note.service.NoteServiceImpl.NoteType;
import org.openelisglobal.note.valueholder.Note;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.result.service.ResultService;
import org.openelisglobal.result.valueholder.Result;
import org.openelisglobal.resultlimit.service.ResultLimitService;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.sampleitem.valueholder.SampleItem;
import org.openelisglobal.testresult.service.TestResultService;
import org.openelisglobal.testterminology.service.TestTerminologyMappingService;
import org.openelisglobal.testterminology.valueholder.TestTerminologyMapping;
import org.openelisglobal.unitofmeasure.valueholder.UnitOfMeasure;

/**
 * How the eGFR on an order follows the creatinine saved on it, and that an eGFR
 * entered by hand is never overwritten.
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class EgfrCalculationServiceImplTest {

    private static final String USER = "1";
    private static final String NOT_STARTED = "10";
    private static final String TECHNICAL_ACCEPTANCE = "11";
    private static final String FINALIZED = "12";

    @Mock
    private AnalysisService analysisService;
    @Mock
    private ResultService resultService;
    @Mock
    private TestResultService testResultService;
    @Mock
    private ResultLimitService resultLimitService;
    @Mock
    private SampleHumanService sampleHumanService;
    @Mock
    private NoteService noteService;
    @Mock
    private IStatusService statusService;
    @Mock
    private TestTerminologyMappingService terminologyMappingService;

    private EgfrCalculationServiceImpl service;

    private Sample sample;
    private SampleItem sampleItem;
    private Patient patient;
    private Analysis creatinine;
    private Analysis egfr;

    @Before
    public void setUp() {
        service = new EgfrCalculationServiceImpl(analysisService, resultService, testResultService, resultLimitService,
                sampleHumanService, noteService, statusService, terminologyMappingService);

        when(statusService.getStatusID(AnalysisStatus.NotStarted)).thenReturn(NOT_STARTED);
        when(statusService.getStatusID(AnalysisStatus.TechnicalAcceptance)).thenReturn(TECHNICAL_ACCEPTANCE);
        when(statusService.matches(anyString(), any(AnalysisStatus.class))).thenAnswer(invocation -> {
            String id = invocation.getArgument(0);
            AnalysisStatus status = invocation.getArgument(1);
            return (status == AnalysisStatus.NotStarted && NOT_STARTED.equals(id))
                    || (status == AnalysisStatus.TechnicalAcceptance && TECHNICAL_ACCEPTANCE.equals(id))
                    || (status == AnalysisStatus.Finalized && FINALIZED.equals(id));
        });
        when(terminologyMappingService.getActiveByTestId(anyString())).thenReturn(Collections.emptyList());
        when(testResultService.getActiveTestResultsByTest(anyString())).thenReturn(Collections.emptyList());
        when(noteService.createSavableNote(any(), any(), anyString(), anyString(), anyString()))
                .thenAnswer(invocation -> {
                    Note note = new Note();
                    note.setText(invocation.getArgument(2));
                    return note;
                });

        // mocks rather than setters: the date setters of these value holders format
        // the date for display, which needs the Spring context
        sample = mock(Sample.class);
        when(sample.getId()).thenReturn("100");
        when(sample.getCollectionDate()).thenReturn(Timestamp.valueOf(LocalDateTime.of(2026, 10, 15, 8, 0)));
        sampleItem = new SampleItem();
        sampleItem.setId("200");
        sampleItem.setSample(sample);

        patient = mock(Patient.class);
        when(patient.getGender()).thenReturn("F");
        when(patient.getBirthDate()).thenReturn(Timestamp.valueOf(LocalDateTime.of(1999, 3, 1, 0, 0)));
        when(sampleHumanService.getPatientForSample(sample)).thenReturn(patient);

        creatinine = analysis("300", test("1", "2160-0", "mg/dL"), TECHNICAL_ACCEPTANCE);
        egfr = spy(analysis("301", test("2", "98979-8", "mL/min/1.73 m2"), NOT_STARTED));
        doNothing().when(egfr).setStartedDate(any(Timestamp.class));
        when(analysisService.getAnalysesBySampleId("100")).thenReturn(Arrays.asList(creatinine, egfr));
        when(resultService.getResultsByAnalysis(egfr)).thenReturn(new ArrayList<>());
    }

    @Test
    public void fillsTheOrderedEgfrWhenCreatinineIsSaved() {
        service.updateEgfrForSavedResults(List.of(result(creatinine, "0.62")), USER);

        ArgumentCaptor<Result> written = ArgumentCaptor.forClass(Result.class);
        verify(resultService).insert(written.capture());
        assertEquals("125", written.getValue().getValue());
        assertEquals(egfr, written.getValue().getAnalysis());
        assertTrue(egfr.getResultCalculated());
        assertEquals(TECHNICAL_ACCEPTANCE, egfr.getStatusId());
        verify(analysisService).update(egfr);
        verify(noteService).createSavableNote(eq(egfr), eq(NoteType.INTERNAL), any(), any(), eq(USER));
    }

    @Test
    public void recalculatesItsOwnEgfrWhenCreatinineIsCorrected() {
        Result stored = result(egfr, "125");
        stored.setId("500");
        when(resultService.getResultsByAnalysis(egfr)).thenReturn(List.of(stored));
        egfr.setResultCalculated(true);
        egfr.setStatusId(TECHNICAL_ACCEPTANCE);

        service.updateEgfrForSavedResults(List.of(result(creatinine, "1.20")), USER);

        verify(resultService).update(stored);
        assertEquals("64", stored.getValue());
    }

    @Test
    public void neverOverwritesAnEgfrEnteredByHand() {
        Result stored = result(egfr, "118");
        when(resultService.getResultsByAnalysis(egfr)).thenReturn(List.of(stored));
        egfr.setResultCalculated(false);

        service.updateEgfrForSavedResults(List.of(result(creatinine, "0.62")), USER);

        assertEquals("118", stored.getValue());
        verify(resultService, never()).update(any(Result.class));
        verify(resultService, never()).insert(any(Result.class));
        verify(analysisService, never()).update(any(Analysis.class));
    }

    @Test
    public void anEgfrEnteredInTheSameSaveIsKeptAndNotOverwrittenLater() {
        // the eGFR was calculated earlier; now a user types over it while also
        // correcting the creatinine
        egfr.setResultCalculated(true);
        Result typed = result(egfr, "90");

        service.updateEgfrForSavedResults(List.of(result(creatinine, "1.20"), typed), USER);

        assertEquals("90", typed.getValue());
        assertFalse("the eGFR is from now on a hand-entered value", egfr.getResultCalculated());
        verify(resultService, never()).update(any(Result.class));
        verify(resultService, never()).insert(any(Result.class));

        // a later creatinine correction leaves it alone too
        when(resultService.getResultsByAnalysis(egfr)).thenReturn(List.of(typed));
        service.updateEgfrForSavedResults(List.of(result(creatinine, "0.62")), USER);
        assertEquals("90", typed.getValue());
        verify(resultService, never()).update(any(Result.class));
    }

    @Test
    public void notesWhyWhenThePatientIsUnder18() {
        when(patient.getBirthDate()).thenReturn(Timestamp.valueOf(LocalDateTime.of(2010, 1, 1, 0, 0)));

        service.updateEgfrForSavedResults(List.of(result(creatinine, "0.62")), USER);

        verify(resultService, never()).insert(any(Result.class));
        verify(noteService).createSavableNote(eq(egfr), eq(NoteType.INTERNAL),
                eq("eGFR not calculated (CKD-EPI 2021): the patient was under 18 years old at collection."), any(),
                eq(USER));
    }

    @Test
    public void doesNothingWithoutAnOrderedEgfr() {
        when(analysisService.getAnalysesBySampleId("100")).thenReturn(List.of(creatinine));

        service.updateEgfrForSavedResults(List.of(result(creatinine, "0.62")), USER);

        verify(resultService, never()).insert(any(Result.class));
        verify(noteService, never()).insert(any(Note.class));
    }

    @Test
    public void recognisesTestsByLoincMapping() {
        org.openelisglobal.test.valueholder.Test unmapped = test("3", null, "µmol/L");
        Analysis mappedCreatinine = analysis("302", unmapped, TECHNICAL_ACCEPTANCE);
        TestTerminologyMapping mapping = new TestTerminologyMapping();
        mapping.setSource("LOINC");
        mapping.setCode("14682-9");
        when(terminologyMappingService.getActiveByTestId("3")).thenReturn(List.of(mapping));
        when(analysisService.getAnalysesBySampleId("100")).thenReturn(Arrays.asList(mappedCreatinine, egfr));

        service.updateEgfrForSavedResults(List.of(result(mappedCreatinine, "54.8")), USER);

        ArgumentCaptor<Result> written = ArgumentCaptor.forClass(Result.class);
        verify(resultService).insert(written.capture());
        assertEquals("125", written.getValue().getValue());
    }

    private Analysis analysis(String id, org.openelisglobal.test.valueholder.Test test, String statusId) {
        Analysis analysis = new Analysis();
        analysis.setId(id);
        analysis.setTest(test);
        analysis.setSampleItem(sampleItem);
        analysis.setStatusId(statusId);
        return analysis;
    }

    private static org.openelisglobal.test.valueholder.Test test(String id, String loinc, String unit) {
        org.openelisglobal.test.valueholder.Test test = new org.openelisglobal.test.valueholder.Test();
        test.setId(id);
        test.setLoinc(loinc);
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setUnitOfMeasureName(unit);
        test.setUnitOfMeasure(unitOfMeasure);
        return test;
    }

    private static Result result(Analysis analysis, String value) {
        Result result = new Result();
        result.setAnalysis(analysis);
        result.setValue(value);
        return result;
    }
}
