package org.openelisglobal.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openelisglobal.common.constants.Constants;
import org.openelisglobal.common.validator.BaseErrors;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        BaseErrors errors = new BaseErrors();
        if (exception instanceof UsernameNotFoundException) {
            errors.reject("login.error.message");
        } else if (exception instanceof BadCredentialsException) {
            errors.reject("login.error.message");
        } else if (exception instanceof CredentialsExpiredException) {
            errors.reject("login.error.password.expired");
        } else if (exception instanceof DisabledException) {
            errors.reject("login.error.account.disable");
        } else if (exception instanceof LockedException) {
            errors.reject("login.error.account.lock");
        } else {
            exception.printStackTrace();
            errors.reject("login.error.generic");
        }

        request.getSession().setAttribute(Constants.LOGIN_ERRORS, errors);
        redirectStrategy.sendRedirect(request, response, "/LoginPage.do");
    }

}
