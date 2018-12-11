<%@ page language="java" contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants"%>

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

<script language="JavaScript1.2">

$jq(document).ready( function() {
	if( !sendingEnabled() ){
			disableSendingState();
	}
	});


function /*boolean*/ sendingEnabled(){
	var form = window.document.forms[0];
	return form.enableSending[0].checked;
}

function disableSendingState(){
	disableElementArray( $$(".sendingDependent"));
}

function enableSendingState(){
	enableElementArray( $$(".sendingDependent") );
}

function enableElementArray( elements ){
	for( var i = 0; i < elements.length; i++){
		elements[i].disabled = false;
	}
}

function disableElementArray( elements ){
	for( var i = 0; i < elements.length; i++){
		elements[i].disabled = true;
	}
}
	
function setMyCancelAction(){
	var form = window.document.forms[0];
	form.action = "CancelTestUsageConfiguration.do";
	form.submit();
	return true;	
}

function savePage(){
	if( formValid() ){
		var form = window.document.forms[0];
		form.action = "UpdateTestUsageConfiguration.do";
		form.submit();
		return true;		
	}

	return false;
}

function formValid(){

	var missingValues = false;
	
	var dependents = $$(".sendingDependent");
	
	for( var i = 0; i < dependents.length; i++){
		if( checkForMissingValue( dependents[i])){
			missingValues = true;
		}
	}

	return !missingValues;
}

function /*boolean*/ checkForMissingValue( element ){
	if( !element.disabled && element.value == ""){
		element.style.borderColor = "red";
		return true;
	}
	
	return false;
}

function makeDirty(){
	clearErrors();
}

function clearErrors(){
	var dependents = $$(".sendingDependent");
	
	for( var i = 0; i < dependents.length; i++){
		dependents[i].style.borderColor = "";
	}
}
</script>

<h2 class="important-text"><bean:message key="testusage.config.requiredfields"/></h2>

<div class="oe-form" style="max-width: 800px;">
    <h3><bean:message key="testusage.config.transmit"/></h3>
    
    <ul>
        <li>
            <div class="top-label"><bean:message key="testusage.config.transmit.instructions"/></div>
			<html:radio name='<%= formName %>' 
						property="enableSending" 
						value="enable" 
						onclick="enableSendingState(); makeDirty();"><label for="enable"><bean:message key="testusage.config.enable"/></label></html:radio>
			<html:radio name='<%= formName %>' 
						property="enableSending" 
						value="disable" 
						onclick="disableSendingState(); makeDirty();"><label for="disable"><bean:message key="testusage.config.disable"/></label></html:radio>
            <span class="inline-more"><bean:message key="testusage.config.time"/></span>&nbsp;
			<html:select name='<%=formName %>' 
						 property="sendHour" 
						 styleClass="sendingDependent" 
						 onchange=" makeDirty();">
				<app:optionsCollection name='<%=formName %>' property="hourList" label="value" value="id"/>
			</html:select>:
			<html:select name='<%=formName %>' 
						 property="sendMin" 
						 styleClass="sendingDependent"
						 onchange=" makeDirty();">
				<html:optionsCollection name='<%=formName %>' property="minList" label="value" value="id"/>
			</html:select>
            <div class="field-note"><bean:message key="testusage.config.transmit.note"/></div>
        </li>
        <li>
            <label class="top-label"><bean:message key="testusage.config.transmit.url"/></label>
            <html:text name='<%= formName %>'  
    			   property="url" 
    			   styleClass="sendingDependent" 
    			   size="50"
    			   onchange=" makeDirty();"></html:text>
            <div class="field-note"><bean:message key="testusage.config.transmit.url.note"/></div>
        </li>
        <li>
            <label class="top-label"><bean:message key="testusage.config.transmit.name"/></label>
            <html:text name='<%= formName %>'  
    			   property="serviceUserName" 
    			   styleClass="sendingDependent"
    			   onchange=" makeDirty();"></html:text>
        </li>
        <li>
            <label class="top-label"><bean:message key="testusage.config.transmit.password"/></label>
            <html:text name='<%= formName %>'  
    				   property="servicePassword" 
    				   styleClass="sendingDependent"
    				   onchange=" makeDirty();"></html:text>
        </li>
    </ul>

<h3><bean:message key="testusage.config.history"/></h3>
<table>
	<tr>	
		<td ><bean:message key="testusage.config.history.time.report"/>:</td>
		<td><bean:write name='<%= formName %>' property="lastAttemptToSend"/></td>
	</tr>
	<tr>	
		<td><bean:message key="testusage.config.history.time.sent"/>:</td>
		<td><bean:write name='<%= formName %>' property="lastSent"/></td>
	</tr>
	<tr>
		<td><bean:message key="testusage.config.history.time.status"/>:</td>
		<td><bean:write name='<%=formName %>' property="sendStatus" />
	</tr>	
</table>

</div>