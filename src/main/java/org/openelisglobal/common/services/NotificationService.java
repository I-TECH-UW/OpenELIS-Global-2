package org.openelisglobal.common.services;

import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.openelisglobal.login.valueholder.LoginUser;
import org.openelisglobal.spring.util.SpringContext;
import java.sql.Date;
import org.openelisglobal.login.service.LoginUserService;

@Service
@Scope("prototype")
@DependsOn({ "springContext" })
public class NotificationService {

    private  LoginUserService loginService = SpringContext.getBean(LoginUserService.class);

    public Date getLoginFromCombinedId(String name) {
         LoginUser loginUser = loginService.getUserProfile(name);
                  return loginUser.getPasswordExpiredDate();
    }  
}
