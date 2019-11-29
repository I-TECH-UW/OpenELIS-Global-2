<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="org.openelisglobal.common.action.IActionConstants"%>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

 

<%!String allowEdits = "true";%>

<%
	if (request.getAttribute(IActionConstants.ALLOW_EDITS_KEY) != null) {
		allowEdits = (String) request.getAttribute(IActionConstants.ALLOW_EDITS_KEY);
	}
%>

<script>

jQuery(document).ready( function() {
	if( !sendingEnabled() ){
			disableSendingState();
	}
	});


function /*boolean*/ sendingEnabled(){
	var form = document.getElementById("mainForm");
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
	var form = document.getElementById("mainForm");
	form.action = "CancelTestUsageConfiguration.do";
	form.submit();
	return true;	
}

function savePage(){
	if( formValid() ){
		var form = document.getElementById("mainForm");
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

<h2 class="important-text"><spring:message code="testusage.config.requiredfields"/></h2>

<div class="oe-form" style="max-width: 800px;">
    <h3><spring:message code="testusage.config.transmit"/></h3>
    
    <ul>
        <li>
            <div class="top-label"><spring:message code="testusage.config.transmit.instructions"/></div>
			<html:radio name='${form.formName}' 
						property="enableSending" 
						value="enable" 
						onclick="enableSendingState(); makeDirty();"><label for="enable"><spring:message code="testusage.config.enable"/></label></html:radio>
			<html:radio name='${form.formName}' 
						property="enableSending" 
						value="disable" 
						onclick="disableSendingState(); makeDirty();"><label for="disable"><spring:message code="testusage.config.disable"/></label></html:radio>
            <span class="inline-more"><spring:message code="testusage.config.time"/></span>&nbsp;
			<html:select name='${form.formName}' 
						 property="sendHour" 
						 styleClass="sendingDependent" 
						 onchange=" makeDirty();">
				<app:optionsCollection name='${form.formName}' property="hourList" label="value" value="id"/>
			</html:select>:
			<html:select name='${form.formName}' 
						 property="sendMin" 
						 styleClass="sendingDependent"
						 onchange=" makeDirty();">
				<html:optionsCollection name='${form.formName}' property="minList" label="value" value="id"/>
			</html:select>
            <div class="field-note"><spring:message code="testusage.config.transmit.note"/></div>
        </li>
        <li>
            <label class="top-label"><spring:message code="testusage.config.transmit.url"/></label>
            <html:text name='${form.formName}'  
    			   property="url" 
    			   styleClass="sendingDependent" 
    			   size="50"
    			   onchange=" makeDirty();"></html:text>
            <div class="field-note"><spring:message code="testusage.config.transmit.url.note"/></div>
        </li>
        <li>
            <label class="top-label"><spring:message code="testusage.config.transmit.name"/></label>
            <html:text name='${form.formName}'  
    			   property="serviceUserName" 
    			   styleClass="sendingDependent"
    			   onchange=" makeDirty();"></html:text>
        </li>
        <li>
            <label class="top-label"><spring:message code="testusage.config.transmit.password"/></label>
            <html:text name='${form.formName}'  
    				   property="servicePassword" 
    				   styleClass="sendingDependent"
    				   onchange=" makeDirty();"></html:text>
        </li>
    </ul>

<h3><spring:message code="testusage.config.history"/></h3>
<table>
	<tr>	
		<td ><spring:message code="testusage.config.history.time.report"/>:</td>
		<td><bean:write name='${form.formName}' property="lastAttemptToSend"/></td>
	</tr>
	<tr>	
		<td><spring:message code="testusage.config.history.time.sent"/>:</td>
		<td><bean:write name='${form.formName}' property="lastSent"/></td>
	</tr>
	<tr>
		<td><spring:message code="testusage.config.history.time.status"/>:</td>
		<td><bean:write name='${form.formName}' property="sendStatus" />
	</tr>	
</table>

</div>