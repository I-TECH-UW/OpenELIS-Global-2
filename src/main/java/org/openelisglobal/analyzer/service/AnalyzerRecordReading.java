package org.openelisglobal.analyzer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.MissingNode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * How a profile reads what an instrument sends (spec point 13). A test is read
 * by the codes it is sent under, its unit, result type, call component, parts
 * and run-failure values; a part with its own sub-identity by its code, unit
 * and result type; a declared value by the spellings translated to it. The type
 * reads every result by its routing, parsing, record declarations, control
 * recognition and sample flags. Names, hints, codings and connection defaults
 * are not reading: a LOINC fix or a new assay changes nothing here.
 */
public final class AnalyzerRecordReading {

    /** The profile sections that decide how every result of a type is read. */
    static final List<String> TYPE_READING_SECTIONS = List.of("identifier_pattern", "msh3_pattern", "column_mapping",
            "sheet_detection", "result_value_order", "test_code_values", "controlResultRecognition", "sample_flags");

    private AnalyzerRecordReading() {
    }

    /** The records both profiles declare and read the same way. */
    public static Set<AnalyzerMappingRowKey> readAlike(BridgeAnalyzerProfile one, BridgeAnalyzerProfile other) {
        Map<AnalyzerMappingSourceRow, Object> theirs = rowReadings(other);
        return rowReadings(one).entrySet().stream().filter(
                entry -> entry.getKey().rawValue() == null && entry.getValue().equals(theirs.get(entry.getKey())))
                .map(entry -> new AnalyzerMappingRowKey(entry.getKey().sourceRowKey(), entry.getKey().subIdentity()))
                .collect(Collectors.toSet());
    }

    /**
     * Each row a mapping decides, a record or one value of a record, with how the
     * profile reads it.
     */
    public static Map<AnalyzerMappingSourceRow, Object> rowReadings(BridgeAnalyzerProfile profile) {
        Map<AnalyzerMappingSourceRow, Object> readings = new LinkedHashMap<>();
        Map<String, JsonNode> documents = testDocuments(profile.document());
        for (var test : profile.testDefinitions()) {
            JsonNode document = documents.getOrDefault(test.analyzerCode(), MissingNode.getInstance());
            List<List<String>> parts = test.components().stream()
                    .map(component -> List.of(component.code(), Objects.toString(component.subIdentity(), "")))
                    .toList();
            readings.put(new AnalyzerMappingSourceRow(test.analyzerCode(), null, ""),
                    List.of(sorted(test.aliases()), Objects.toString(test.unit(), ""),
                            Objects.toString(test.resultType(), ""), Objects.toString(test.callComponent(), ""), parts,
                            sorted(texts(document.path("run_failure_values")))));
            addValues(readings, test.analyzerCode(), "", test.resultValues(), test.translations());
            for (var component : test.recordComponents()) {
                readings.put(new AnalyzerMappingSourceRow(test.analyzerCode(), null, component.subIdentity()),
                        List.of(component.code(), Objects.toString(component.unit(), ""),
                                Objects.toString(component.resultType(), "")));
                addValues(readings, test.analyzerCode(), component.subIdentity(), component.resultValues(),
                        component.translations());
            }
        }
        return readings;
    }

    /**
     * How the type reads every result, section by section; an absent section reads
     * as absent.
     */
    public static Map<String, JsonNode> typeReading(BridgeAnalyzerProfile profile) {
        JsonNode document = profile.document();
        Map<String, JsonNode> reading = new TreeMap<>();
        for (String section : TYPE_READING_SECTIONS) {
            if (document.has(section)) {
                reading.put(section, document.get(section));
            }
        }
        return reading;
    }

    private static void addValues(Map<AnalyzerMappingSourceRow, Object> readings, String code, String subIdentity,
            List<String> values, Map<String, List<String>> translations) {
        for (String value : values) {
            readings.put(new AnalyzerMappingSourceRow(code, value, subIdentity),
                    sorted(translations.getOrDefault(value, List.of())));
        }
    }

    private static Map<String, JsonNode> testDocuments(JsonNode document) {
        Map<String, JsonNode> tests = new LinkedHashMap<>();
        document.path("default_test_mappings").forEach(test -> tests.put(test.path("test_code").asText(), test));
        return tests;
    }

    private static List<String> texts(JsonNode node) {
        List<String> texts = new ArrayList<>();
        node.forEach(value -> texts.add(value.asText()));
        return texts;
    }

    private static List<String> sorted(List<String> values) {
        return values.stream().sorted().toList();
    }
}
