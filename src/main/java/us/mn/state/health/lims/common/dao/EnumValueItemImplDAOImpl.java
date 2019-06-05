package us.mn.state.health.lims.common.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.valueholder.EnumValueItemImpl;

@Component
@Transactional 
public class EnumValueItemImplDAOImpl extends BaseDAOImpl<EnumValueItemImpl, String> implements EnumValueItemImplDAO {
  EnumValueItemImplDAOImpl() {
    super(EnumValueItemImpl.class);
  }
}
