package org.openelisglobal.security;

import javax.servlet.http.HttpServletRequest;

import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.login.service.LoginUserService;
import org.openelisglobal.login.valueholder.LoginUser;
import org.openelisglobal.login.valueholder.UserSessionData;
import org.openelisglobal.systemuser.service.SystemUserService;
import org.openelisglobal.systemuser.valueholder.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component("basicAuthFilter")
public class BasicAuthFilter extends BasicAuthenticationFilter {


    @Autowired
    private LoginUserService loginService;
    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    public BasicAuthFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void onSuccessfulAuthentication(javax.servlet.http.HttpServletRequest request,
            javax.servlet.http.HttpServletResponse response, Authentication authResult) {
        LoginUser loginInfo = loginService.getMatch("loginName", authResult.getName()).get();
        setupUserRequest(request, loginInfo);
    }

    private void setupUserRequest(HttpServletRequest request, LoginUser loginInfo) {
        // get system user and link to login user
        SystemUser su = systemUserService.get(String.valueOf(loginInfo.getSystemUserId()));
        // create usersessiondata and store in session
        UserSessionData usd = new UserSessionData();

        usd.setSytemUserId(loginInfo.getSystemUserId());
        usd.setLoginName(loginInfo.getLoginName());
        usd.setElisUserName(su.getNameForDisplay());
        usd.setAdmin(loginService.isUserAdmin(loginInfo));
        request.setAttribute(IActionConstants.USER_SESSION_DATA, usd);
    }
}
