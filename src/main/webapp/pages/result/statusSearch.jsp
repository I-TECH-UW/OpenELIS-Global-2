<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
				 us.mn.state.health.lims.common.formfields.FormFields,
				 us.mn.state.health.lims.common.formfields.FormFields.Field,
				 us.mn.state.health.lims.common.util.DateUtil,
				 spring.mine.internationalization.MessageUtil"  %>

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
	var form = document.getElementById("mainForm");

	form.action = "StatusResults.do"; 
	form.method ="get";
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
			<% if(useCollectionDate){ %>  <%= MessageUtil.getContextualMessage("sample.collectionDate")  %><br><span style="font-size: xx-small; "><%=DateUtil.getDateUserPrompt()%></span> <% } %>
		</td>
		<td >
			<spring:message code="sample.receivedDate"/><br><span style="font-size: xx-small; "><%=DateUtil.getDateUserPrompt()%></span>
		</td>
		<td >
			<spring:message code="test.testName"/>
		</td>
		<td >
			<%= MessageUtil.getContextualMessage("analysis.status") %>
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
			<form:select  path="selectedTest" onchange="dirtySearchInfo( event )">
				<form:options items="${form.testSelections}" itemLabel="value" itemValue="id"/>
			</form:select>
	</td>
	<td>
			<form:select  path="selectedAnalysisStatus" onchange="dirtySearchInfo( event )" >
				<form:options items="${form.analysisStatusSelections}" itemLabel="description" itemValue="id" />
			</form:select>
	</td>
	<% if( useSampleStatus ){ %>
	<td>
			<form:select  path="selectedSampleStatus" onchange="dirtySearchInfo( event )">
				<form:options items="${form.sampleStatusSelections}" itemLabel="description" itemValue="id" />
			</form:select>
	</td>
	<% } %>
	</tr>

	</table>
	<button type="button" name="searchButton" onclick="doShowTests()"  >
		<spring:message code="resultsentry.status.search"/>
	</button>

</div>



