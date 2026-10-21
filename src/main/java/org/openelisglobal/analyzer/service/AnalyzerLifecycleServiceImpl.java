package org.openelisglobal.analyzer.service;

import jakarta.persistence.PersistenceException;
import java.util.ArrayList;
import org.openelisglobal.analyzer.dao.AnalyzerMappingDAO;
import org.openelisglobal.analyzer.valueholder.Analyzer;
import org.openelisglobal.analyzerresults.service.AnalyzerResultsService;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnalyzerLifecycleServiceImpl implements AnalyzerLifecycleService {

    private final AnalyzerService analyzerService;
    private final AnalyzerMappingDAO mappingDAO;
    private final AnalyzerResultsService analyzerResultsService;
    private final AnalyzerActivationRecordService activationRecordService;

    public AnalyzerLifecycleServiceImpl(AnalyzerService analyzerService, AnalyzerMappingDAO mappingDAO,
            AnalyzerResultsService analyzerResultsService, AnalyzerActivationRecordService activationRecordService) {
        this.analyzerService = analyzerService;
        this.mappingDAO = mappingDAO;
        this.analyzerResultsService = analyzerResultsService;
        this.activationRecordService = activationRecordService;
    }

    @Override
    @Transactional
    public void retire(String analyzerId, String actor) {
        Analyzer analyzer = find(analyzerId);
        if (analyzer.getStatus() == Analyzer.AnalyzerStatus.ACTIVE || analyzer.isActive()) {
            throw new AnalyzerRequestException("analyzer.lifecycle.error.running",
                    "Deactivate the analyzer before retiring it");
        }
        save(analyzer, Analyzer.AnalyzerStatus.RETIRED, actor);
    }

    @Override
    @Transactional
    public void restore(String analyzerId, String actor) {
        Analyzer analyzer = find(analyzerId);
        if (analyzer.getStatus() != Analyzer.AnalyzerStatus.RETIRED) {
            throw new AnalyzerRequestException("analyzer.lifecycle.error.notRetired", "The analyzer is not retired");
        }
        save(analyzer, Analyzer.AnalyzerStatus.INACTIVE, actor);
    }

    @Override
    @Transactional
    public void delete(String analyzerId, String actor) {
        Analyzer analyzer = find(analyzerId);
        if (!analyzerResultsService.getResultsbyAnalyzer(analyzer.getId()).isEmpty()
                || !activationRecordService.findByAnalyzerId(analyzer.getId()).isEmpty()) {
            throw inUse();
        }
        try {
            mappingDAO.deleteAnalyzerWithItsMappings(analyzer.getId());
        } catch (PersistenceException | DataIntegrityViolationException referenced) {
            throw inUse();
        }
    }

    private static AnalyzerRequestException inUse() {
        return new AnalyzerRequestException("analyzer.lifecycle.error.inUse",
                "The analyzer has results or history; retire it instead");
    }

    private void save(Analyzer persisted, Analyzer.AnalyzerStatus status, String actor) {
        // The audited service compares the update with the managed object, so the
        // change goes on a copy.
        Analyzer update = new Analyzer();
        BeanUtils.copyProperties(persisted, update);
        update.setTestUnitIds(new ArrayList<>(persisted.getTestUnitIds()));
        update.setStatus(status);
        update.setActive(false);
        update.setSysUserId(actor);
        analyzerService.update(update);
    }

    private Analyzer find(String analyzerId) {
        if (analyzerId == null || analyzerId.isBlank()) {
            throw new IllegalArgumentException("Analyzer ID is required");
        }
        return analyzerService.getWithMapping(analyzerId.trim())
                .orElseThrow(() -> new IllegalArgumentException("Analyzer not found: " + analyzerId));
    }
}
