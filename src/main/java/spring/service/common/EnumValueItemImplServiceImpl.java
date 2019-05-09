package spring.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.mn.state.health.lims.common.dao.EnumValueItemImplDAO;
import us.mn.state.health.lims.common.valueholder.EnumValueItemImpl;

@Service
public class EnumValueItemImplServiceImpl extends BaseObjectServiceImpl<EnumValueItemImpl> implements EnumValueItemImplService {
  @Autowired
  protected EnumValueItemImplDAO baseObjectDAO;

  EnumValueItemImplServiceImpl() {
    super(EnumValueItemImpl.class);
  }

  @Override
  protected EnumValueItemImplDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
