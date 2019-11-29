package org.openelisglobal.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.owasp.encoder.Encode;

//The parameters fetched by this class are able to be used securely in an xml
//context as they are escaped
public class SecureXmlHttpServletRequest extends HttpServletRequestWrapper {

    public SecureXmlHttpServletRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String param = super.getParameter(name);
        if (param == null) {
            return null;
        } else {
            return Encode.forXml(param);
        }
    }

}
