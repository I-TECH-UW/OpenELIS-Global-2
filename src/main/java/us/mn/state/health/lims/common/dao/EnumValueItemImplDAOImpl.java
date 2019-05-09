package us.mn.state.health.lims.common.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.valueholder.EnumValueItemImpl;

@Component
public class EnumValueItemImplDAOImpl extends BaseDAOImpl<EnumValueItemImpl> implements EnumValueItemImplDAO {
  EnumValueItemImplDAOImpl() {
    super(EnumValueItemImpl.class);
  }
}
