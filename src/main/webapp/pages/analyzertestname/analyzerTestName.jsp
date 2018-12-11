<%@ page language="java" contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
	us.mn.state.health.lims.common.util.StringUtil,
	us.mn.state.health.lims.analyzer.valueholder.Analyzer,
	us.mn.state.health.lims.test.valueholder.Test" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

 
<bean:define id="analyzerList" name="${form.formName}" property="analyzerList" type="java.util.List<Analyzer>" />
<bean:define id="analyzerName" name="${form.formName}" property="analyzerId" />
<bean:define id="testList" name="${form.formName}" property="testList" type="java.util.List<Test>" />
<bean:define id="testName" name="${form.formName}" property="testId" />

<%!String allowEdits = "true";%>

<%
	if (request.getAttribute(IActionConstants.ALLOW_EDITS_KEY) != null) {
		allowEdits = (String) request.getAttribute(IActionConstants.ALLOW_EDITS_KEY);
	}
%>


<script>

	$jq(document).ready( function() {
		$jq("#analyzerIdHidden").val($jq("#analyzerId").val());
		if($jq("#analyzerId").val() != 0 ){
			$jq("#analyzerId").attr('disabled', 'disabled');
			$jq("#analyzerTestNameId").attr('disabled','disabled');
		}
	});
function validateForm(form) {

	if( $("analyzerId").selectedIndex == 0 ||
	    $("analyzerTestNameId").value == null ||
	    $("testId").selectedIndex == 0 ){
	    		alert('<%=StringUtil.getMessageForKey("error.all.required") %>');
	    		return false;
	    }

    return true;
}

	function copyToHiddenAnalyzer( element ){
		$jq("#analyzerIdHidden").val(element.value);
	}

</script>
<div style="border: 1px solid;width:40%">
	<br>
	<span style="width:40%;float:left;padding-left: 4px;" ><spring:message code="analyzer.label" />
			:
			<span class="requiredlabel">*</span>
	</span>
	<form:hidden path="analyzerId" id="analyzerIdHidden" />
	<select id="analyzerId" onchange="copyToHiddenAnalyzer(this);" >
		<option value="0"></option>
		<% for( Analyzer analyzer : analyzerList ){%>
		<option value="<%=analyzer.getId() %>" <%= analyzer.getName().equals(analyzerName) ? "selected='selected'" : "" %> ><%=analyzer.getName()%></option>
		<% } %>
	</select><br><br>
	<span style="width:40%;float:left;padding-left: 4px;" >
		<spring:message code="analyzer.test.name" />:
		<span class="requiredlabel">*</span>
	</span>
	<form:input path="analyzerTestName" id="analyzerTestNameId" /><br>&nbsp;
</div>
<br>
<div style="width:40%">
	<span style="width:40%;float:left;padding-left: 4px;" >
		<spring:message code="analyzer.test.actual.name" />:
			<span class="requiredlabel">*</span>
	</span>
<html:select name="${form.formName}" property="testId" id="testId" >
	<html:option value="0">&nbsp;</html:option>
	<% for( Test test : testList ){%>
	<option value="<%=test.getId() %>" <%= test.getName().equals(testName) ? "selected='selected'" : "" %> ><%=test.getName()%></option>
	<% } %>
</html:select>
</div><br><br>

