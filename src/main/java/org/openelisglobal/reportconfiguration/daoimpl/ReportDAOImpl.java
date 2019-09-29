package org.openelisglobal.reportconfiguration.daoimpl;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.reportconfiguration.dao.ReportDAO;
import org.openelisglobal.reportconfiguration.valueholder.Report;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ReportDAOImpl extends BaseDAOImpl<Report, String> implements ReportDAO {

    public ReportDAOImpl() {
        super(Report.class);
    }
}
