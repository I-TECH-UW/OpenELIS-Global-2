package org.openelisglobal.reports.action.implementation.reportBeans;

import static org.openelisglobal.reports.action.implementation.reportBeans.CSVColumnBuilder.Strategy.DATE_TIME;
import static org.openelisglobal.reports.action.implementation.reportBeans.CSVColumnBuilder.Strategy.NONE;

import java.sql.Date;
import org.openelisglobal.common.services.StatusService;
import org.openelisglobal.reports.action.implementation.Report.DateRange;

public class CSVStatisticsReportColumnBuilder extends CSVColumnBuilder {
    protected DateRange dateRange;

    public CSVStatisticsReportColumnBuilder(DateRange dateRange) {
        super(StatusService.AnalysisStatus.SampleRejected);
        this.dateRange = dateRange;
        defineAllReportColumns();
    }

    @Override
    public void makeSQL() {
        query = new StringBuilder();
        Date lowDate = dateRange.getLowDate();
        Date highDate = dateRange.getHighDate();

        query.append("SELECT t.test_name AS test_name, ts.description AS sample_type, p.priority AS test_priority,"
                + " o.name AS lab_unit, s.received_date AS reception_date, s.entered_date AS test_date" + " FROM test t"
                + " INNER JOIN sample s ON t.sample_id = s.id" + " INNER JOIN priority p ON t.priority_id = p.id"
                + " INNER JOIN lab_unit o ON s.lab_unit_id = o.id"
                + " INNER JOIN type_of_sample ts ON s.sample_type_id = ts.id" + " WHERE s.entered_date BETWEEN '"
                + lowDate + "' AND '" + highDate + "'");

        query.append(" AND p.priority IN ('Routine', 'ASAP', 'STAT', 'Timed', 'Future STAT')");

        query.append(" AND (" + "(s.reception_time BETWEEN '09:00' AND '15:30' AND p.priority IN ('Routine', 'ASAP'))"
                + " OR (s.reception_time BETWEEN '15:31' AND '08:59' AND p.priority = 'Out of Normal Work Hours')"
                + ")");

        query.append(" ORDER BY o.name, s.received_date");
    }

    protected void defineAllReportColumns() {
        add("test_name", "Test Name", NONE);
        add("sample_type", "Sample Type", NONE);
        add("test_priority", "Test Priority", NONE);
        add("lab_unit", "Lab Unit", NONE);
        add("reception_date", "Reception Date", DATE_TIME);
        add("test_date", "Test Date", DATE_TIME);
    }

}
