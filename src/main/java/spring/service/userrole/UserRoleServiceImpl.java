package spring.service.userrole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.userrole.dao.UserRoleDAO;
import us.mn.state.health.lims.userrole.valueholder.UserRole;

@Service
public class UserRoleServiceImpl extends BaseObjectServiceImpl<UserRole> implements UserRoleService {
  @Autowired
  protected UserRoleDAO baseObjectDAO;

  UserRoleServiceImpl() {
    super(UserRole.class);
  }

  @Override
  protected UserRoleDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
