package us.mn.state.health.lims.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import us.mn.state.health.lims.common.log.LogEvent;

public class SecurityFilter implements Filter {
	
  private ArrayList<String> exceptions = new ArrayList<String>();
  
	public SecurityFilter() {
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		boolean suspectedAttack = false;
		boolean csrfSuspectedAttack = false;
		ArrayList<String> attackList = new ArrayList<String>();
		
		//CSRF check for any "action" pages
		if (httpRequest.getMethod().equals("POST") || httpRequest.getRequestURI().contains("Update")
				|| httpRequest.getRequestURI().contains("Save")) {
			String referer = httpRequest.getHeader("Referer");
			String scheme = httpRequest.getScheme();
			String host = httpRequest.getHeader("Host");
			String contextPath = httpRequest.getContextPath();
			String baseURL = scheme + "://" + host + contextPath;	

      if (!hasCSRFExceptionRule(httpRequest.getRequestURI())) {
  			if  (referer == null) {
  			  csrfSuspectedAttack = true;
  				attackList.add("CSRF- null referer");
  			} else if (!referer.startsWith(baseURL)) {
  			  csrfSuspectedAttack = true;
  				attackList.add("CSRF- " + referer);
  			} 
      }
		}
			
		//persistent XSS check 
		if (httpRequest.getMethod().equals("POST") || httpRequest.getRequestURI().contains("Update")
				|| httpRequest.getRequestURI().contains("Save")) {
			@SuppressWarnings("unchecked")
			Enumeration<String> parameterNames = httpRequest.getParameterNames();
			 while (parameterNames.hasMoreElements()) {
				 String param = httpRequest.getParameter(parameterNames.nextElement());
				 String paramValue = java.net.URLDecoder.decode(param, "UTF-8");
				 paramValue = paramValue.replaceAll("\\s", "");
				 if (paramValue.contains("<script>") || paramValue.contains("</script>")) {
					 suspectedAttack = true;
					 attackList.add("XSS- " + param);
				 }
			 }
		}
		
		//Adding security headers to response
		httpResponse.addHeader("Content-Security-Policy","default-src 'self'; script-src 'self' 'unsafe-inline' 'unsafe-eval';" 
		        + "connect-src 'self'; img-src 'self'; style-src 'self' 'unsafe-inline'; child-src 'self'; object-src 'self';" );//defines where content is allowed to be loaded from
		//httpResponse.addHeader("Strict-Transport-Security", "max-age=31536000"); //enforces communication must be over https
		httpResponse.addHeader("X-Content-Type-Options","nosniff"); //prevents MIME sniffing errors
		httpResponse.addHeader("X-Frame-Options", "SAMEORIGIN");//enforces whether page is allowed to be an iframe in another website
		httpResponse.addHeader("X-XSS-Protection","1"); //provides browser xss protection. attempts to cleanse.
	
		if (suspectedAttack) {
      StringBuilder attackMessage = new StringBuilder();
      String separator = "";
      attackMessage.append(httpRequest.getRequestURI());
      attackMessage.append(" suspected attack(s) of type: ");
      for (String attack : attackList) {
        attackMessage.append(separator);
        separator = ",";
        attackMessage.append(attack);
      }
      //should log suspected attempt
      LogEvent.logWarn("SecurityFilter", "doFilter()", attackMessage.toString());
      System.out.println(attackMessage.toString());
      //send to safe page
      httpResponse.sendRedirect("Dashboard.do");
		} else if (csrfSuspectedAttack) {
      StringBuilder attackMessage = new StringBuilder();
      String separator = "";
      attackMessage.append(httpRequest.getRequestURI());
      attackMessage.append(" suspected attack(s) of type: ");
      for (String attack : attackList) {
        attackMessage.append(separator);
        separator = ",";
        attackMessage.append(attack);
      }
      //should log suspected attempt
      LogEvent.logWarn("SecurityFilter", "doFilter()", attackMessage.toString());
      System.out.println(attackMessage.toString());
      //continue as this is not a perfect solution and may intercept correct requests
      chain.doFilter(request, httpResponse);
		} else {
      chain.doFilter(request, httpResponse);
		}
	}
	
	private boolean hasCSRFExceptionRule(String contextPath) {
	  for (String exception : exceptions) {
  	  if (contextPath.contains(exception)) {
  	    return true;
  	  }
	  }
	  
	  return false;
	}
	
	public void addException(String exception) {
	  exceptions.add(exception);
	}
	
	private void addExceptions() {
    exceptions.add("importAnalyzer");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	  addExceptions();
	}

}
