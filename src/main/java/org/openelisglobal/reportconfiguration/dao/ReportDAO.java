package org.openelisglobal.reportconfiguration.dao;

import org.openelisglobal.common.dao.BaseDAO;
import org.openelisglobal.reportconfiguration.valueholder.Report;

public interface ReportDAO extends BaseDAO<Report, String> {

  int getMaxSortOrder(String category);
}
