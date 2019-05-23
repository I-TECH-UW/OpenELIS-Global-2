package spring.service.typeofsample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSamplePanelDAO;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSamplePanel;

@Service
public class TypeOfSamplePanelServiceImpl extends BaseObjectServiceImpl<TypeOfSamplePanel> implements TypeOfSamplePanelService {
  @Autowired
  protected TypeOfSamplePanelDAO baseObjectDAO;

  TypeOfSamplePanelServiceImpl() {
    super(TypeOfSamplePanel.class);
  }

  @Override
  protected TypeOfSamplePanelDAO getBaseObjectDAO() {
    return baseObjectDAO;}

  @Override
  public void insertData(TypeOfSamplePanel typeOfSamplePanel) {
  	baseObjectDAO.insertData(typeOfSamplePanel);
  }
}
