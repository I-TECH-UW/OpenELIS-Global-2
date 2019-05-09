package spring.service.dataexchange.aggregatereporting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.dataexchange.aggregatereporting.dao.ReportExternalImportDAO;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportExternalImport;

@Service
public class ReportExternalImportServiceImpl extends BaseObjectServiceImpl<ReportExternalImport> implements ReportExternalImportService {
  @Autowired
  protected ReportExternalImportDAO baseObjectDAO;

  ReportExternalImportServiceImpl() {
    super(ReportExternalImport.class);
  }

  @Override
  protected ReportExternalImportDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
