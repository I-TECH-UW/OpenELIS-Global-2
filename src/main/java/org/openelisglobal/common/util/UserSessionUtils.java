package org.openelisglobal.common.util;

import javax.servlet.http.HttpServletRequest;
import org.openelisglobal.login.valueholder.UserSessionData;

public class UserSessionUtils {

    private static final String USER_SESSION_DATA = "userSessionData";

    public static String getSysUserId(HttpServletRequest request) {
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