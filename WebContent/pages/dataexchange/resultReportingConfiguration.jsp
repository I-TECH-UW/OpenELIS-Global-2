<%@ page language="java" contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
			us.mn.state.health.lims.common.util.StringUtil,
			us.mn.state.health.lims.dataexchange.resultreporting.beans.ReportingConfiguration,
			us.mn.state.health.lims.common.util.Versioning"%>

<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/labdev-view" prefix="app"%>

<bean:define id="formName" value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />

<%!String allowEdits = "true";%>

<%
	if (request.getAttribute(IActionConstants.ALLOW_EDITS_KEY) != null) {
		allowEdits = (String) request.getAttribute(IActionConstants.ALLOW_EDITS_KEY);
	}
%>

<script type="text/javascript" src="scripts/ajaxCalls.js?ver=<%= Versioning.getBuildNumber() %>"></script>


<script type="text/javascript" >


	
function setMyCancelAction(){
	var form = window.document.forms[0];
	form.action = "CancelResultReportingConfiguration.do";
	form.submit();
	return true;	
}

function savePage(){
	if( formValid() ){
		var form = window.document.forms[0];
		form.action = "UpdateResultReportingConfiguration.do";
		form.submit();
		return true;		
	}

	return false;
}

function formValid(){
	return hasRequriedValues();
}

function /*boolean*/ hasRequriedValues(){
	//var url = $("urlField");

	//if( window.document.forms[0].enabled[0].checked && url.value.blank() ){
	//	url.style.borderColor = "red";
	//	alert("<%= StringUtil.getMessageForKey("resultreporting.config.missing.url")%>");
	//	return false;
	//}
	
	return true;	
}

function testConnection( identifier){
	testConnectionOnServer(identifier, $jq("#" + identifier).val(), processTestSuccess);
}

function  /*void*/ processTestSuccess(xhr){

    //alert(xhr.responseText);
	var testResult = xhr.responseXML.getElementsByTagName("testResult").item(0).getAttribute("result");

	alert( testResult );
}
</script>

<logic:iterate id="reports" name='<%= formName %>' property="reports" type="ReportingConfiguration" >
<h2><bean:write name="reports" property="title"/></h2>
<table >
	<tr >
		<td width="180px">
			<html:radio name='reports' 
						property="enabled"
						indexed="true"  
						value="enable" ><bean:message key="testusage.config.enable"/></html:radio>
			<html:radio name='reports' 
						property="enabled" 
						value="disable"
						indexed="true" ><bean:message key="testusage.config.disable"/></html:radio>
	</td>
	</tr>
	<tr>
	<td colspan="2"><bean:message key="resultreporting.config.url"/></td>  	
  </tr>
  <tr>
  <td>&nbsp;</td>
  <td>
    	<html:text name='reports'  
    			   property="url"
    			   styleId = '<%= reports.getConnectionTestIdentifier() %>'
    			   indexed="true" 
    			   size="80" />
    </td>
    <logic:notEmpty name="reports" property="connectionTestIdentifier">
    <td>
		<input type="button" value='<%= StringUtil.getMessageForKey("connection.test.button") %>' onclick='<%= "testConnection( \"" +  reports.getConnectionTestIdentifier() + "\");" %>' >
		<bean:message key="connection.test.button.message"/>
    </td>
    </logic:notEmpty>
  </tr>
  <logic:match name='reports' property="isScheduled"  value="true">
  <tr>
  	<td colspan="2"><bean:message key="testusage.config.transmit.instructions"/>
  	</td>
  </tr>
  <tr>
  	<td>&nbsp;</td>
  	<td>
  	 <bean:message key="testusage.config.time"/>&nbsp;
			<html:select name='reports' 
						 property="scheduleHours"
						 indexed="true" 
						 styleClass="gatherDependent"
						 styleId="scheduleHours" 
						 onchange=" makeDirty();">
				<html:optionsCollection name='<%=formName %>' property="hourList" label="value" value="id"/>
			</html:select>:
			<html:select name='reports' 
			             property="scheduleMin"
			             indexed="true" 
			             styleClass="gatherDependent"
			             styleId="scheduleMin" 
			             onchange=" makeDirty();">
				<html:optionsCollection name='<%=formName %>' property="minList" label="value" value="id"/>
		</html:select>
    </td>
  </tr>
  </logic:match>
 <logic:equal name='reports' property="showAuthentication"  value="true">
 <tr>
    <td><br/><bean:message key="testusage.config.transmit.name"/></td>
  </tr>
  <tr>
    <td>     
    	<html:text name='reports'  
    			   property="userName" 
    			   indexed="true" 
    			   styleClass="gatherDependent sendingDependent"
    			   onchange=" makeDirty();"></html:text>
    </td>
  </tr>
  <tr>
    <td><br/><bean:message key="testusage.config.transmit.password"/></td>
  </tr>
  <tr>  
    <td><html:password name='reports'  
    				   property="password"
    				   indexed="true"  
    				   styleClass="gatherDependent sendingDependent"
    				   onchange=" makeDirty();"></html:password>
    </td>
  </tr>
</logic:equal>     
</table>
<logic:equal name='reports' property="showBacklog"  value="true">
	<bean:message key="result.report.queue.msg"/>
	<br/><br/>
	<bean:message key="result.report.queue.size"/>: <bean:write name="reports" property="backlogSize" />
</logic:equal> 
</logic:iterate>