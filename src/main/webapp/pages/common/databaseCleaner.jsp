<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.util.StringUtil"
%>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<form>
<script type="text/javascript">

function /*void*/ cancel(){
	var form = window.document.forms[0];
	form.action = "Cancel.do";
	form.submit();
}

function /*void*/ deleteData(){
	var form = window.document.forms[0];
	form.action = "DatabaseCleaningRequest.do";
	form.submit();
}

</script>


<div width="100%" align="center">
<h1><spring:message code="database.clean.warning" /><br/>
<font color="red"><spring:message code="database.clean.warning.final"/></font></h1>
<input id="clean" type="button" value='<%= StringUtil.getMessageForKey("database.clean") %>' style="width:300px;height:50px" onclick="deleteData();"/>
<input id="cancel" type="button" value='<%= StringUtil.getMessageForKey("label.button.cancel") %>' style="width:300px;height:50px" onclick="cancel();" />

</div>
</form>