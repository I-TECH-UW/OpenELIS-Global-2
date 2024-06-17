package org.openelisglobal.reports.action.implementation;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.reports.action.implementation.reportBeans.WHONETColumnBuilder;
import org.openelisglobal.reports.form.ReportForm;

public class WHONETReport extends Report implements IReportParameterSetter, IReportCreator {

  protected List<WHONETColumnBuilder> whonetColumnBuilder = new ArrayList<>();
  protected String reportPath = "";
  protected DateRange dateRange;

  @Override
  public void initializeReport(ReportForm form) {
    initialized = true;
    ReportSpecificationList selection = form.getSelectList();
    dateRange = new DateRange(form.getLowerDateRange(), form.getUpperDateRange());

    errorFound = !validateSubmitParameters(selection);
    if (errorFound) {
      return;
    }
    if (whonetColumnBuilder.size() == 0) {
      add1LineErrorMessage("report.error.message.noPrintableItems");
    }
  }

  private boolean validateSubmitParameters(ReportSpecificationList selectList) {

    return (dateRange.validateHighLowDate("report.error.message.date.received.missing"));
  }

  @Override
  public JRDataSource getReportDataSource() throws IllegalStateException {
    return errorFound
        ? new JRBeanCollectionDataSource(errorMsgs)
        : new JRBeanCollectionDataSource(whonetColumnBuilder);
  }

  @Override
  protected void createReportParameters() {
    super.createReportParameters();
    reportParameters.put("startDate", dateRange.getLowDateStr());
    reportParameters.put("endDate", dateRange.getHighDateStr());
  }

  @Override
  protected String reportFileName() {
    return "WHOEXPORTREPORT";
  }

  @Override
  public void setRequestParameters(ReportForm form) {
    form.setReportName(getReportNameForParameterPage());
    form.setUseLowerDateRange(Boolean.TRUE);
    form.setUseUpperDateRange(Boolean.TRUE);
  }

  protected String getReportNameForParameterPage() {
    return MessageUtil.getMessage("reports.label.export.whonet.report.dateType");
  }
}
