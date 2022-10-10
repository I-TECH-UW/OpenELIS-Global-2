package org.openelisglobal.reports.action.implementation.reportBeans;

import org.openelisglobal.common.services.StatusService;
import org.openelisglobal.reports.action.implementation.Report.DateRange;
import static org.openelisglobal.reports.action.implementation.reportBeans.CSVColumnBuilder.Strategy.DATE_TIME;
import static org.openelisglobal.reports.action.implementation.reportBeans.CSVColumnBuilder.Strategy.NONE;

import java.sql.Date;;

public class CSVSampleRejectionColumnBuilder extends CSVColumnBuilder {
    protected DateRange dateRange;

    public CSVSampleRejectionColumnBuilder(DateRange dateRange) {
        super(StatusService.AnalysisStatus.SampleRejected);
        this.dateRange = dateRange;
        defineAllReportColumns();
    }

    @Override
    public void makeSQL() {
        query = new StringBuilder();
        query.append("select s.accession_number ,s.received_date  from sample s where s.id in (select si.samp_id from sample_item si where si.rejected = true)");
        Date lowDate = dateRange.getLowDate();
        Date highDate = dateRange.getHighDate();
        
    }


    protected void defineAllReportColumns() {
        add("accession_number", "Lab_No.",NONE );
        add("received_date", "STARTED_DATE", DATE_TIME);
    }
    
}
