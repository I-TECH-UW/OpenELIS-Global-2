package spring.service.analyte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.analyte.dao.AnalyteDAO;
import us.mn.state.health.lims.analyte.valueholder.Analyte;

@Service
public class AnalyteServiceImpl extends BaseObjectServiceImpl<Analyte> implements AnalyteService {
  @Autowired
  protected AnalyteDAO baseObjectDAO;

  AnalyteServiceImpl() {
    super(Analyte.class);
  }

  @Override
  protected AnalyteDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
