package org.openelisglobal.analyzer.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.openelisglobal.analyzer.dao.AnalyzerMappingDAO;
import org.openelisglobal.analyzer.valueholder.Analyzer;
import org.openelisglobal.analyzer.valueholder.AnalyzerActivationRecord;
import org.openelisglobal.analyzerresults.service.AnalyzerResultsService;
import org.openelisglobal.analyzerresults.valueholder.AnalyzerResults;

@RunWith(MockitoJUnitRunner.class)
public class AnalyzerLifecycleServiceTest {

    @Mock
    private AnalyzerService analyzerService;
    @Mock
    private AnalyzerMappingDAO mappingDAO;
    @Mock
    private AnalyzerResultsService analyzerResultsService;
    @Mock
    private AnalyzerActivationRecordService activationRecordService;

    private AnalyzerLifecycleService service;

    @Before
    public void setUp() {
        service = new AnalyzerLifecycleServiceImpl(analyzerService, mappingDAO, analyzerResultsService,
                activationRecordService);
    }

    @Test
    public void anAnalyzerThatIsNotRunningIsRetiredWithItsHistory() {
        when(analyzerService.getWithMapping("42")).thenReturn(Optional.of(analyzer(Analyzer.AnalyzerStatus.INACTIVE)));

        service.retire("42", "17");

        ArgumentCaptor<Analyzer> saved = ArgumentCaptor.forClass(Analyzer.class);
        verify(analyzerService).update(saved.capture());
        assertEquals(Analyzer.AnalyzerStatus.RETIRED, saved.getValue().getStatus());
        assertFalse(saved.getValue().isActive());
        assertEquals("17", saved.getValue().getSysUserId());
    }

    @Test
    public void aRunningAnalyzerIsDeactivatedBeforeItCanBeRetired() {
        when(analyzerService.getWithMapping("42")).thenReturn(Optional.of(analyzer(Analyzer.AnalyzerStatus.ACTIVE)));

        AnalyzerRequestException refused = assertThrows(AnalyzerRequestException.class,
                () -> service.retire("42", "17"));

        assertEquals("analyzer.lifecycle.error.running", refused.messageKey());
        verify(analyzerService, never()).update(any(Analyzer.class));
    }

    @Test
    public void aRetiredAnalyzerIsRestoredInactive() {
        when(analyzerService.getWithMapping("42")).thenReturn(Optional.of(analyzer(Analyzer.AnalyzerStatus.RETIRED)));

        service.restore("42", "17");

        ArgumentCaptor<Analyzer> saved = ArgumentCaptor.forClass(Analyzer.class);
        verify(analyzerService).update(saved.capture());
        assertEquals(Analyzer.AnalyzerStatus.INACTIVE, saved.getValue().getStatus());
    }

    @Test
    public void anAnalyzerNeverUsedIsDeletedWithItsMappings() {
        when(analyzerService.getWithMapping("42")).thenReturn(Optional.of(analyzer(Analyzer.AnalyzerStatus.SETUP)));
        when(analyzerResultsService.getResultsbyAnalyzer("42")).thenReturn(List.of());
        when(activationRecordService.findByAnalyzerId("42")).thenReturn(List.of());

        service.delete("42", "17");

        verify(mappingDAO).deleteAnalyzerWithItsMappings("42");
    }

    @Test
    public void anAnalyzerWithResultsOrActivationsIsNotDeleted() {
        when(analyzerService.getWithMapping("42")).thenReturn(Optional.of(analyzer(Analyzer.AnalyzerStatus.INACTIVE)));
        when(analyzerResultsService.getResultsbyAnalyzer("42")).thenReturn(List.of(new AnalyzerResults()));

        AnalyzerRequestException withResults = assertThrows(AnalyzerRequestException.class,
                () -> service.delete("42", "17"));

        when(analyzerResultsService.getResultsbyAnalyzer("42")).thenReturn(List.of());
        when(activationRecordService.findByAnalyzerId("42")).thenReturn(List.of(new AnalyzerActivationRecord()));
        AnalyzerRequestException activated = assertThrows(AnalyzerRequestException.class,
                () -> service.delete("42", "17"));

        assertEquals("analyzer.lifecycle.error.inUse", withResults.messageKey());
        assertEquals("analyzer.lifecycle.error.inUse", activated.messageKey());
        verify(mappingDAO, never()).deleteAnalyzerWithItsMappings(any());
    }

    @Test
    public void anAnalyzerSomethingElseStillRefersToIsNotDeleted() {
        when(analyzerService.getWithMapping("42")).thenReturn(Optional.of(analyzer(Analyzer.AnalyzerStatus.INACTIVE)));
        when(analyzerResultsService.getResultsbyAnalyzer("42")).thenReturn(List.of());
        when(activationRecordService.findByAnalyzerId("42")).thenReturn(List.of());
        doThrow(new jakarta.persistence.PersistenceException("violates foreign key constraint")).when(mappingDAO)
                .deleteAnalyzerWithItsMappings("42");

        AnalyzerRequestException refused = assertThrows(AnalyzerRequestException.class,
                () -> service.delete("42", "17"));

        assertEquals("analyzer.lifecycle.error.inUse", refused.messageKey());
    }

    private static Analyzer analyzer(Analyzer.AnalyzerStatus status) {
        Analyzer analyzer = new Analyzer();
        analyzer.setId("42");
        analyzer.setName("Bench 1");
        analyzer.setStatus(status);
        analyzer.setActive(status == Analyzer.AnalyzerStatus.ACTIVE);
        return analyzer;
    }
}
