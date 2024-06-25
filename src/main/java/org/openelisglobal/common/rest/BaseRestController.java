package org.openelisglobal.common.rest;

import javax.servlet.http.HttpServletRequest;
import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.login.valueholder.UserSessionData;
import org.springframework.stereotype.Component;

@Component
public class BaseRestController implements IActionConstants {

    protected String getSysUserId(HttpServletRequest request) {
        UserSessionData usd = (UserSessionData) request.getSession().getAttribute(USER_SESSION_DATA);
        if (usd == null) {
            usd = (UserSessionData) request.getAttribute(USER_SESSION_DATA);
            if (usd == null) {
                return null;
            }
        }
        return String.valueOf(usd.getSystemUserId());
    }
}
