package spring.service.label;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.label.valueholder.Label;

@Service
public class LabelServiceImpl extends BaseObjectServiceImpl<Label> implements LabelService {
  @Autowired
  protected BaseDAO<Label> baseObjectDAO;

  LabelServiceImpl() {
    super(Label.class);
  }

  @Override
  protected BaseDAO<Label> getBaseObjectDAO() {
    return baseObjectDAO;}
}
