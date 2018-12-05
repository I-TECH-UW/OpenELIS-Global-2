<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
				 us.mn.state.health.lims.common.formfields.FormFields,
				 us.mn.state.health.lims.common.formfields.FormFields.Field,
				 us.mn.state.health.lims.common.util.DateUtil,
				 us.mn.state.health.lims.common.util.StringUtil"  %>

<%@ taglib uri="/tags/struts-bean"		prefix="bean" %>
<%@ taglib uri="/tags/struts-html"		prefix="html" %>
<%@ taglib uri="/tags/struts-logic"		prefix="logic" %>
<%@ taglib uri="/tags/labdev-view"		prefix="app" %>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax"%>

<bean:define id="formName"	value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />

<%!
	boolean useCollectionDate = true;
	boolean useSampleStatus = false;
 %>
<%
	useCollectionDate = FormFields.getInstance().useField(Field.CollectionDate);
	useSampleStatus = FormFields.getInstance().useField(Field.SearchSampleStatus);
 %>
<script type="text/javascript" language="JavaScript1.2">

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

	<h2><bean:message key="sample.entry.search"/></h2>
	<table width="70%">
	<tr >
		<td >
			<% if(useCollectionDate){ %>  <%= StringUtil.getContextualMessageForKey("sample.collectionDate")  %><br><span style="font-size: xx-small; "><%=DateUtil.getDateUserPrompt()%></span> <% } %>
		</td>
		<td >
			<bean:message key="sample.receivedDate"/><br><span style="font-size: xx-small; "><%=DateUtil.getDateUserPrompt()%></span>
		</td>
		<td >
			<bean:message key="test.testName"/>
		</td>
		<td >
			<%= StringUtil.getContextualMessageForKey("analysis.status") %>
		</td>
		<% if( useSampleStatus ){ %>
		<td >
			<bean:message key="sample.status"/>
		</td>
		<% } %>
	</tr>

	<tr>
	<td >
		<% if(useCollectionDate){ %> <html:text name="<%=formName%>" property="collectionDate" onkeyup="dirtySearchInfo( event )" /> <%} %>
	</td>
	<td>
		<html:text name="<%=formName%>" property="recievedDate" onkeyup="dirtySearchInfo( event )"/>
	</td>
	<td>
			<html:select  name="<%=formName%>" property="selectedTest" onchange="dirtySearchInfo( event )">
				<html:optionsCollection name="<%=formName%>"  property="testSelections" label="value" value="id"/>
			</html:select>
	</td>
	<td>
			<html:select  name="<%=formName%>" property="selectedAnalysisStatus" onchange="dirtySearchInfo( event )" >
				<html:optionsCollection name="<%=formName%>" property="analysisStatusSelections" label="description" value="id" />
			</html:select>
	</td>
	<% if( useSampleStatus ){ %>
	<td>
			<html:select  name="<%=formName%>" property="selectedSampleStatus" onchange="dirtySearchInfo( event )">
				<html:optionsCollection name="<%=formName%>" property="sampleStatusSelections" label="description" value="id" />
			</html:select>
	</td>
	<% } %>
	</tr>

	</table>
	<html:button property="searchButton" onclick="doShowTests()"  >
		<bean:message key="resultsentry.status.search"/>
	</html:button>

</div>



