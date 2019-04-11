<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
                 us.mn.state.health.lims.common.util.Versioning,
                 org.owasp.encoder.Encode" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<c:set var="workplanType" value="${form.workplanType}"/>
<c:set var="responseAction" value="${form.searchAction}"/>

<%!
	String basePath = "";

 %>
<%
	String path = request.getContextPath();
	basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";

%>

<script type="text/javascript" src="<%=basePath%>scripts/utilities.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<script type="text/javascript">

function doShowTests(element){
	window.location.href = '<c:url value="${responseAction}?type=${workplanType}&selectedSearchID="/>' + element.value;
}

function validateTest(){

	if ( fieldIsEmptyById( "testName" ) ) {
		setValidIndicaterOnField(false, "isValid");
	}
}


function /*boolean*/ handleEnterEvent(){
	if( !fieldIsEmptyById("testName")){
		doShowTests();
	}
	return false;
}

</script>

<div id="PatientPage" class="colorFill" style="display:inline" >
	<h2><spring:message code="sample.entry.search"/></h2>
	<table width="40%">
		<tr>
			<td width="50%" align="right" nowrap>
				<c:out value="${form.searchLabel}"/>
			</td>
			<td>
				<form:select path="selectedSearchID" id="testName"
						 onchange="doShowTests(this);" >
					<option value=""></option>
					<form:options items="${form.searchTypes}" itemLabel="value" itemValue="id" />
				</form:select>
	   		</td>
		</tr>
	</table>
	<br/>
	<h1>
		<c:out value="${form.testName}"/>
	</h1>
</div>


<ajax:autocomplete
  source="testName"
  target="selectedTestID"
  baseUrl="ajaxAutocompleteXML"
  className="autocomplete"
  parameters="testName={testName},provider=TestAutocompleteProvider,fieldName=testName,idName=id"
  indicator="indicator"
  minimumCharacters="1"
  parser="new ResponseXmlToHtmlListParser()"
  />
