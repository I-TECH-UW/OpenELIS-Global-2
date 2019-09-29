package org.openelisglobal.reportconfiguration.service;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.reportconfiguration.dao.ReportDAO;
import org.openelisglobal.reportconfiguration.form.ReportConfigurationForm;
import org.openelisglobal.reportconfiguration.valueholder.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl extends BaseObjectServiceImpl<Report, String> implements ReportService {

    @Autowired
    protected ReportDAO baseObjectDAO;

    ReportServiceImpl() {
        super(Report.class);
    }

    @Override
    public boolean updateReport(ReportConfigurationForm form) {
        return false;
    }

    @Override
    protected ReportDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }
}
