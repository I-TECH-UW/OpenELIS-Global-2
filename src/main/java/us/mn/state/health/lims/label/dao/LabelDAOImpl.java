package us.mn.state.health.lims.label.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.label.valueholder.Label;

@Component
public class LabelDAOImpl extends BaseDAOImpl<Label> implements LabelDAO {
  LabelDAOImpl() {
    super(Label.class);
  }
}
