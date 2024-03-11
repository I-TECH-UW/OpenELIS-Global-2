<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	import="org.openelisglobal.common.util.StringUtil"
%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<form>
<script type="text/javascript">

function /*void*/ cancel(){
	var form = window.document.forms[0];
	form.action = "Cancel";
	form.submit();
}

function /*void*/ deleteData(){
	var form = window.document.forms[0];
	form.action = "DatabaseCleaningRequest";
	form.submit();
}

</script>


<div width="100%" align="center">
<h1><spring:message htmlEscape="false" code="database.clean.warning" /><br/>
<font color="red"><spring:message code="database.clean.warning.final"/></font></h1>
<input id="clean" type="button" value='<spring:message code="database.clean"/>' style="width:300px;height:50px" onclick="deleteData();"/>
<input id="cancel" type="button" value='<spring:message code="label.button.cancel"/>' style="width:300px;height:50px" onclick="cancel();" />

</div>
</form>