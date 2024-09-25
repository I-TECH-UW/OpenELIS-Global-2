package org.openelisglobal.reports.action.implementation.reportBeans;

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

    }

    protected void defineAllReportColumns() {

    }
}
