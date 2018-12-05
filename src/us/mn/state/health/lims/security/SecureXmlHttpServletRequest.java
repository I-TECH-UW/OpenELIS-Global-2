package us.mn.state.health.lims.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.owasp.encoder.Encode;

public class SecureXmlHttpServletRequest extends HttpServletRequestWrapper {

	public SecureXmlHttpServletRequest(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name) {
		String param = super.getParameter(name);
		if (param == null)
			return null;
		else
			return Encode.forXml(param);
	}

}
