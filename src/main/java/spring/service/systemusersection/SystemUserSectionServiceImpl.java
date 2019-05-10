package spring.service.systemusersection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.systemusersection.dao.SystemUserSectionDAO;
import us.mn.state.health.lims.systemusersection.valueholder.SystemUserSection;

@Service
public class SystemUserSectionServiceImpl extends BaseObjectServiceImpl<SystemUserSection> implements SystemUserSectionService {
  @Autowired
  protected SystemUserSectionDAO baseObjectDAO;

  SystemUserSectionServiceImpl() {
    super(SystemUserSection.class);
  }

  @Override
  protected SystemUserSectionDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
