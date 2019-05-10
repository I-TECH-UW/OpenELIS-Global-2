package spring.service.dataexchange.aggregatereporting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.dataexchange.aggregatereporting.dao.ReportExternalExportDAO;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportExternalExport;

@Service
public class ReportExternalExportServiceImpl extends BaseObjectServiceImpl<ReportExternalExport> implements ReportExternalExportService {
  @Autowired
  protected ReportExternalExportDAO baseObjectDAO;

  ReportExternalExportServiceImpl() {
    super(ReportExternalExport.class);
  }

  @Override
  protected ReportExternalExportDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
