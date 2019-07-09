package us.mn.state.health.lims.label.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import  us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.label.valueholder.Label;

@Component
@Transactional 
public class LabelDAOImpl extends BaseDAOImpl<Label, String> implements LabelDAO {
  LabelDAOImpl() {
    super(Label.class);
  }
}
