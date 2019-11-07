package org.openelisglobal.reportconfiguration.service;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.reportconfiguration.form.ReportConfigurationForm;
import org.openelisglobal.reportconfiguration.valueholder.Report;
import org.openelisglobal.reportconfiguration.valueholder.ReportCategory;

public interface ReportService extends BaseObjectService<Report, String> {

    boolean updateReport(ReportConfigurationForm form, String currentUserId);
}
