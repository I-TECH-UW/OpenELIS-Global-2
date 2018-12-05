<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
                 us.mn.state.health.lims.common.util.Versioning,
                 org.owasp.encoder.Encode" %>

<%@ taglib uri="/tags/struts-bean"		prefix="bean" %>
<%@ taglib uri="/tags/struts-html"		prefix="html" %>
<%@ taglib uri="/tags/struts-logic"		prefix="logic" %>
<%@ taglib uri="/tags/labdev-view"		prefix="app" %>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax"%>

<bean:define id="formName" value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />
<bean:define id="workplanType" name="<%=formName%>" property="workplanType" />
<bean:define id="responseAction" name='<%= formName %>' property="searchAction"  />

<%!
	String basePath = "";

 %>
<%
	String path = request.getContextPath();
	basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";

%>

<script type="text/javascript" src="<%=basePath%>scripts/utilities.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<script type="text/javascript" language="JavaScript1.2">

function doShowTests(element){
	
	window.location.href = '<%=responseAction%>' + "?type=" + '<%=Encode.forJavaScript((String) workplanType) %>' + "&selectedSearchID=" + element.value;
	
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
	<h2><bean:message key="sample.entry.search"/></h2>
	<table width="40%">
		<tr>
			<td width="50%" align="right" nowrap>
				<bean:write name="<%=formName %>" property="searchLabel"/>
			</td>
			<td>
				<html:select name="<%=formName%>" property="selectedSearchID" styleId="testName"
						 onchange="doShowTests(this);" >
					<app:optionsCollection name="<%=formName%>" property="searchTypes" label="value" value="id" />
				</html:select>
	   		</td>
		</tr>
	</table>
	<br/>
	<h1>
		<bean:write name="<%=formName%>" property="testName"/>
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
