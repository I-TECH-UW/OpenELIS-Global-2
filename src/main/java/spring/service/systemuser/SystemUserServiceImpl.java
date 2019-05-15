package spring.service.systemuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.systemuser.dao.SystemUserDAO;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;

@Service
public class SystemUserServiceImpl extends BaseObjectServiceImpl<SystemUser> implements SystemUserService {
  @Autowired
  protected SystemUserDAO baseObjectDAO;

  SystemUserServiceImpl() {
    super(SystemUser.class);
  }

  @Override
  protected SystemUserDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
