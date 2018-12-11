<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
				 us.mn.state.health.lims.common.formfields.FormFields,
				 us.mn.state.health.lims.common.formfields.FormFields.Field,
				 us.mn.state.health.lims.common.util.DateUtil,
				 us.mn.state.health.lims.common.util.StringUtil"  %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

	

<%!
	boolean useCollectionDate = true;
	boolean useSampleStatus = false;
 %>
<%
	useCollectionDate = FormFields.getInstance().useField(Field.CollectionDate);
	useSampleStatus = FormFields.getInstance().useField(Field.SearchSampleStatus);
 %>
<script type="text/javascript">

var newSearchInfo = false;

function doShowTests(){
    newSearchInfo = false;
	var form = document.forms[0];

	form.action = "StatusResults.do"; 

	form.submit();
}

function /*boolean*/ handleEnterEvent(){
	if( newSearchInfo ){
		doShowTests();
	}
	return false;
}

function /*void*/ dirtySearchInfo(e){ 
	var code = e ? e.which : window.event.keyCode;
	if( code != 13 ){
		newSearchInfo = true; 
	}
}
</script>

<div id="PatientPage" class="colorFill" style="display:inline" >

	<h2><spring:message code="sample.entry.search"/></h2>
	<table width="70%">
	<tr >
		<td >
			<% if(useCollectionDate){ %>  <%= StringUtil.getContextualMessageForKey("sample.collectionDate")  %><br><span style="font-size: xx-small; "><%=DateUtil.getDateUserPrompt()%></span> <% } %>
		</td>
		<td >
			<spring:message code="sample.receivedDate"/><br><span style="font-size: xx-small; "><%=DateUtil.getDateUserPrompt()%></span>
		</td>
		<td >
			<spring:message code="test.testName"/>
		</td>
		<td >
			<%= StringUtil.getContextualMessageForKey("analysis.status") %>
		</td>
		<% if( useSampleStatus ){ %>
		<td >
			<spring:message code="sample.status"/>
		</td>
		<% } %>
	</tr>

	<tr>
	<td >
		<% if(useCollectionDate){ %> <form:input path="collectionDate" onkeyup="dirtySearchInfo( event )" /> <%} %>
	</td>
	<td>
		<form:input path="recievedDate" onkeyup="dirtySearchInfo( event )"/>
	</td>
	<td>
			<html:select  name="${form.formName}" property="selectedTest" onchange="dirtySearchInfo( event )">
				<html:optionsCollection name="${form.formName}"  property="testSelections" label="value" value="id"/>
			</html:select>
	</td>
	<td>
			<html:select  name="${form.formName}" property="selectedAnalysisStatus" onchange="dirtySearchInfo( event )" >
				<html:optionsCollection name="${form.formName}" property="analysisStatusSelections" label="description" value="id" />
			</html:select>
	</td>
	<% if( useSampleStatus ){ %>
	<td>
			<html:select  name="${form.formName}" property="selectedSampleStatus" onchange="dirtySearchInfo( event )">
				<html:optionsCollection name="${form.formName}" property="sampleStatusSelections" label="description" value="id" />
			</html:select>
	</td>
	<% } %>
	</tr>

	</table>
	<html:button property="searchButton" onclick="doShowTests()"  >
		<spring:message code="resultsentry.status.search"/>
	</html:button>

</div>



