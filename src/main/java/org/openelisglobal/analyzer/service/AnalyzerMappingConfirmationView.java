package org.openelisglobal.analyzer.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;
import java.util.List;

public record AnalyzerMappingConfirmationView(State state, String profileId, int profileRevision,
        String mappingFingerprint, String recognitionFingerprint, String confirmedBy, String confirmedByDisplayName,
        @JsonFormat(shape = JsonFormat.Shape.STRING) Instant confirmedAt, List<AnalyzerMappingSourceRow> confirmedRows,
        List<AnalyzerMappingSourceRow> excludedRows, boolean typeReadingChanged,
        List<AnalyzerMappingSourceRow> rowsReadDifferently) {

    public AnalyzerMappingConfirmationView(State state, String profileId, int profileRevision,
            String mappingFingerprint, String recognitionFingerprint, String confirmedBy, String confirmedByDisplayName,
            Instant confirmedAt, List<AnalyzerMappingSourceRow> confirmedRows,
            List<AnalyzerMappingSourceRow> excludedRows) {
        this(state, profileId, profileRevision, mappingFingerprint, recognitionFingerprint, confirmedBy,
                confirmedByDisplayName, confirmedAt, confirmedRows, excludedRows, false, List.of());
    }

    public enum State {
        UNCONFIRMED, CURRENT, STALE
    }

    public AnalyzerMappingConfirmationView {
        confirmedRows = confirmedRows == null ? List.of() : List.copyOf(confirmedRows);
        excludedRows = excludedRows == null ? List.of() : List.copyOf(excludedRows);
        rowsReadDifferently = rowsReadDifferently == null ? List.of() : List.copyOf(rowsReadDifferently);
    }

    public static AnalyzerMappingConfirmationView unconfirmed() {
        return new AnalyzerMappingConfirmationView(State.UNCONFIRMED, null, 0, null, null, null, null, null, List.of(),
                List.of());
    }
}
