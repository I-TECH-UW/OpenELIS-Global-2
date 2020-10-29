package org.openelisglobal.reports.action.implementation.reportBeans;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.validator.GenericValidator;
import org.hl7.fhir.r4.model.Task;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.reports.action.implementation.Report.DateRange;
import org.openelisglobal.result.valueholder.Result;

import ca.uhn.hl7v2.hoh.util.repackage.Base64.Charsets;

public class CovidResultsCSVBuilder extends CovidResultsBuilderImpl {

    private List<String> columnNames = new ArrayList<>();
    private List<Map<String, String>> valueRows = new ArrayList<>();

    public CovidResultsCSVBuilder(DateRange dateRange) {
        super(dateRange);
    }

    @Override
    public void buildDataSource() {

        createBaseColumnNames();

        List<Analysis> analysises = getCovidAnalysisWithinDate();
        for (Analysis analysis : analysises) {
            addValueRow(analysis);
        }

    }

    private void addValueRow(Analysis analysis) {
        Map<String, String> valueRow = new HashMap<>();
        Optional<Result> result = getResultForAnalysis(analysis);
        Patient patient = getPatientForAnalysis(analysis);

        valueRow.put(RESULT_PROPERTY_NAME, getResultValue(result));
        valueRow.put(ORDER_NUMBER_PROPERTY_NAME, analysis.getSampleItem().getSample().getAccessionNumber());
        valueRow.put(DATE_PROPERTY_NAME, analysis.getStartedDateForDisplay());

        valueRow.put(PATIENT_LAST_NAME_PROPERTY_NAME, patient.getPerson().getLastName());
        valueRow.put(PATIENT_FIRST_NAME_PROPERTY_NAME, patient.getPerson().getFirstName());
        valueRow.put(PATIENT_DATE_OF_BIRTH_PROPERTY_NAME, patient.getBirthDateForDisplay());

        try {
            Task task = getTaskForAnalysis(analysis);

            if (!GenericValidator.isBlankOrNull(task.getDescription())) {
                try {
                    convertJSONToCSV(new JSONObject(task.getDescription()), LOCATOR_FORM_PROPERTY_NAME, valueRow);
                } catch (JSONException e) {
                    LogEvent.logError(this.getClass().getName(), "addValueRow",
                            "could not make json from task description");
                    LogEvent.logError(e);
                }
            }

            valueRows.add(valueRow);
        } catch (IllegalStateException e) {
            LogEvent.logError(this.getClass().getName(), "addValueRow",
                    "could not retrieve FhirTask from analysis with id: " + analysis.getId());
            LogEvent.logError(this.getClass().getName(), "addValueRow", e.getMessage());
        }
    }

    private void convertJSONToCSV(JSONObject jsonObject, String keyPrefix, Map<String, String> valueRow) {
        for (String key : jsonObject.keySet()) {
            if (jsonObject.get(key) == null) {
                String columnName = getCreateColumnName(keyPrefix, ".", key);
                valueRow.put(columnName, EMPTY_VALUE);
            } else if (jsonObject.get(key) instanceof JSONObject) {
                convertJSONToCSV(jsonObject.getJSONObject(key), keyPrefix + "." + key, valueRow);
            } else if (jsonObject.get(key) instanceof JSONArray) {
                convertJSONToCSV(jsonObject.getJSONArray(key), keyPrefix + "." + key, valueRow);
            } else {
                String columnName = getCreateColumnName(keyPrefix, ".", key);
                valueRow.put(columnName, jsonObject.get(key).toString());
            }
        }
    }

    private void convertJSONToCSV(JSONArray jsonArray, String keyPrefix, Map<String, String> valueRow) {
        Iterator<Object> iterator = jsonArray.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Object object = iterator.next();
            ++i;
            if (object == null) {
                String columnName = getCreateColumnName(keyPrefix, "_", Integer.toString(i));
                valueRow.put(columnName, EMPTY_VALUE);
            } else if (object instanceof JSONObject) {
                convertJSONToCSV((JSONObject) object, keyPrefix + "_" + i, valueRow);
            } else {
                String columnName = getCreateColumnName(keyPrefix, "_", Integer.toString(i));
                valueRow.put(columnName, object.toString());
            }
        }

    }

    private String getCreateColumnName(String keyPrefix, String infix, String suffix) {
        String newColumnName = (keyPrefix + infix + suffix);
        if (!columnNames.contains(newColumnName)) {
            columnNames.add(newColumnName);
        }
        return newColumnName;
    }

    private void createBaseColumnNames() {
        columnNames.add(DATE_PROPERTY_NAME);
        columnNames.add(RESULT_PROPERTY_NAME);
        columnNames.add(ORDER_NUMBER_PROPERTY_NAME);
    }

    private byte[] getColumnNamesRowBytes() {
        return String.join(",", columnNames).getBytes(Charsets.UTF_8);
    }

    @Override
    public byte[] getDataSourceAsByteArray() {
        byte[] columnNamesRowBytes = getColumnNamesRowBytes();
        List<byte[]> resultRowsBytes = getResultRowsAsBytes();

        int totalArraySize = columnNamesRowBytes.length;
        for (byte[] resultRowBytes : resultRowsBytes) {
            totalArraySize += resultRowBytes.length;
        }
        byte[] allByteArray = new byte[totalArraySize];
        ByteBuffer buff = ByteBuffer.wrap(allByteArray);
        buff.put(columnNamesRowBytes);
        for (byte[] resultRowBytes : resultRowsBytes) {
            buff.put(resultRowBytes);
        }

        return buff.array();
    }

    private List<byte[]> getResultRowsAsBytes() {
        List<byte[]> resultRowsBytes = new ArrayList<>();
        for (Map<String, String> valueRow : valueRows) {
            List<String> orderedValues = new ArrayList<>();
            for (String columnName : columnNames) {
                if (valueRow.containsKey(columnName)) {
                    orderedValues.add(valueRow.get(columnName));
                } else {
                    orderedValues.add(EMPTY_VALUE);
                }
            }
            resultRowsBytes.add(("\n" + String.join(",", orderedValues)).getBytes(Charsets.UTF_8));
        }
        return resultRowsBytes;
    }

}
