package spring.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.login.dao.LoginDAO;
import us.mn.state.health.lims.login.valueholder.Login;

@Service
public class LoginServiceImpl extends BaseObjectServiceImpl<Login> implements LoginService {
  @Autowired
  protected LoginDAO baseObjectDAO;

  LoginServiceImpl() {
    super(Login.class);
  }

  @Override
  protected LoginDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
