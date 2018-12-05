<%@ page language="java" pageEncoding="ISO-8859-1"
	import="org.apache.struts.action.*,
		us.mn.state.health.lims.common.action.IActionConstants"
 %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!DOCTYPE html>
<html:html lang="true">
  <head>
    <html:base />
    
    <title>webtestInfo.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
    <bean:write name='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>' property="xmlWad"/>
  </body>
</html:html>
