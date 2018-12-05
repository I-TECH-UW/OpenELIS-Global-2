<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.util.StringUtil"
%>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

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
<h1><bean:message key="database.clean.warning" /><br/>
<font color="red"><bean:message key="database.clean.warning.final"/></font></h1>
<input id="clean" type="button" value='<%= StringUtil.getMessageForKey("database.clean") %>' style="width:300px;height:50px" onclick="deleteData();"/>
<input id="cancel" type="button" value='<%= StringUtil.getMessageForKey("label.button.cancel") %>' style="width:300px;height:50px" onclick="cancel();" />

</div>
</form>