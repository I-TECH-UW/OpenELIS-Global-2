<%@ page language="java" pageEncoding="ISO-8859-1"
	import="org.apache.struts.action.*,
		us.mn.state.health.lims.common.action.IActionConstants"
 %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
  <head>
    <base/>
    
    <title>webtestInfo.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
    <c:out value='${form.xmlWad}'/>
  </body>
</html>
