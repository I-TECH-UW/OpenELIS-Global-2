package org.openelisglobal.reportconfiguration.service;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.reportconfiguration.form.ReportConfigurationForm;
import org.openelisglobal.reportconfiguration.valueholder.Report;
import org.openelisglobal.reportconfiguration.valueholder.ReportCategory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ReportService extends BaseObjectService<Report, String> {

    List<Report> getReports();

    boolean updateReport(ReportConfigurationForm form, String currentUserId);

    boolean createNewReport(ReportConfigurationForm form, String currentUserId);
}
