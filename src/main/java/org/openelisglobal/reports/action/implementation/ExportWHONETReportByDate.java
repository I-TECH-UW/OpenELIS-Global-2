package org.openelisglobal.reports.action.implementation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.validator.GenericValidator;
import org.jfree.util.Log;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.project.service.ProjectService;
import org.openelisglobal.project.valueholder.Project;
import org.openelisglobal.reports.action.implementation.reportBeans.CIColumnBuilder;
import org.openelisglobal.reports.action.implementation.reportBeans.CSVColumnBuilder;
import org.openelisglobal.reports.action.implementation.reportBeans.StudyEIDColumnBuilder;
import org.openelisglobal.reports.action.implementation.reportBeans.StudyVLColumnBuilder;
import org.openelisglobal.reports.form.ReportForm;
import org.openelisglobal.spring.util.SpringContext;

public class ExportWHONETReportByDate extends CSVSampleExportReport
    implements IReportParameterSetter, IReportCreator {
  private String projectStr;

  @Override
  protected String reportFileName() {
    return "ExportWHONETReportByDate";
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

  @Override
  protected void createReportParameters() {
    super.createReportParameters();
    //        reportParameters.put("studyName", (project == null) ? null :
    // project.getLocalizedName());
  }

  @Override
  public void initializeReport(ReportForm form) {
    super.initializeReport();
    errorFound = false;

    lowDateStr = form.getLowerDateRange();
    highDateStr = form.getUpperDateRange();
    projectStr = form.getProjectCode();
    dateRange = new DateRange(lowDateStr, highDateStr);

    createReportParameters();

    errorFound = !validateSubmitParameters();
    if (errorFound) {
      return;
    }

    createReportItems();
  }

  /** check everything */
  private boolean validateSubmitParameters() {
    return dateRange.validateHighLowDate("report.error.message.date.received.missing");
  }

  /** creating the list for generation to the report */
  private void createReportItems() {
    try {
      csvColumnBuilder = getColumnBuilder(projectStr);
      csvColumnBuilder.buildDataSource();
    } catch (Exception e) {
      Log.error("Error in " + this.getClass().getSimpleName() + ".createReportItems: ", e);
      add1LineErrorMessage("report.error.message.general.error");
    }
  }

  @Override
  protected void writeResultsToBuffer(ByteArrayOutputStream buffer)
      throws IOException, SQLException, ParseException {

    String currentAccessionNumber = null;
    String[] splitBase = null;
    while (csvColumnBuilder.next()) {
      String line = csvColumnBuilder.nextLine();
      String[] splitLine = line.split(",");

      if (splitLine[0].equals(currentAccessionNumber)) {
        merge(splitBase, splitLine);
      } else {
        if (currentAccessionNumber != null) {
          writeConsolidatedBaseToBuffer(buffer, splitBase);
        }
        splitBase = splitLine;
        currentAccessionNumber = splitBase[0];
      }
    }

    writeConsolidatedBaseToBuffer(buffer, splitBase);
  }

  private void merge(String[] base, String[] line) {
    for (int i = 0; i < base.length; ++i) {
      if (GenericValidator.isBlankOrNull(base[i])) {
        base[i] = line[i];
      }
    }
  }

  protected void writeConsolidatedBaseToBuffer(ByteArrayOutputStream buffer, String[] splitBase)
      throws IOException, UnsupportedEncodingException {

    if (splitBase != null) {
      StringBuffer consolidatedLine = new StringBuffer();
      for (String value : splitBase) {
        consolidatedLine.append(value);
        consolidatedLine.append(",");
      }

      consolidatedLine.deleteCharAt(consolidatedLine.lastIndexOf(","));
      buffer.write(consolidatedLine.toString().getBytes("windows-1252"));
    }
  }

  private CSVColumnBuilder getColumnBuilder(String projectId) {
    String projectTag = CIColumnBuilder.translateProjectId(projectId);
    if (projectTag.equalsIgnoreCase("DBS")) {
      return new StudyEIDColumnBuilder(dateRange, projectStr);
    } else if (projectTag.equalsIgnoreCase("VLS")) {
      return new StudyVLColumnBuilder(dateRange, projectStr);
    }
    throw new IllegalArgumentException();
  }

  /**
   * @return a list of the correct projects for display
   */
  protected List<Project> getProjectList() {
    List<Project> projects = new ArrayList<>();
    Project project = new Project();
    project.setProjectName("Early Infant Diagnosis for HIV Study");
    projects.add(
        SpringContext.getBean(ProjectService.class).getProjectByName(project, false, false));
    project.setProjectName("Viral Load Results");
    projects.add(
        SpringContext.getBean(ProjectService.class).getProjectByName(project, false, false));
    return projects;
  }
}
