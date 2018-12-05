<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="java.util.Date,
			us.mn.state.health.lims.common.action.IActionConstants" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax"%>

<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>' />
<%!


String allowEdits = "true";

String path = "";
String basePath = "";
%>
<%
path = request.getContextPath();
basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

if (request.getAttribute(IActionConstants.ALLOW_EDITS_KEY) != null) {
 allowEdits = (String)request.getAttribute(IActionConstants.ALLOW_EDITS_KEY);
}

%>

<script language="JavaScript1.2">
 
function validateForm(form) {
     return validateTestForm(form);
}

//bugzilla 2443
function processFailure(xhr) {
  //ajax call failed
}

//bugzilla 2443
function processSuccess(xhr) {
  var message = xhr.responseXML.getElementsByTagName("message")[0];
  var formfield = xhr.responseXML.getElementsByTagName("formfield")[0];
  //alert("I am in parseMessage and this is message, formfield " + message + " " + formfield);
  setMessage(message.childNodes[0].nodeValue, formfield.childNodes[0].nodeValue);
}

//bugzilla 2443
function setMessage(message, field) {
     var dataField = document.getElementById("sortOrder");
     if (message != "invalid") {
        dataField.value = message;
     }
}
 
//bugzilla 2443
function getNextSortOrder() {
   var testId = document.getElementById("id");
   //only do this on a new test!
   if (testId == null || testId == 'null' || testId == '' || testId == '0') {
     new Ajax.Request (
           'ajaxDataXML',  //url
             {//options
              method: 'get', //http method
              parameters: 'provider=NextTestSortOrderDataProvider&field=sortOrder&tsid=' + escape($F("selectedTestSectionName")),      //request parameters
              //indicator: 'throbbing'
              onSuccess:  processSuccess,
              onFailure:  processFailure
             }
          );
   }
}
</script>
<%--  Note this page is no longer good enough for _adding_ a test entity to the database, because it does not include all fields --%>
<table>
		<tr>
						<td class="label">
							<bean:message key="test.id"/>:
						</td>	
						<td> 
							<app:text name="<%=formName%>" property="id" allowEdits="false"/>
						</td>
		</tr>
		<tr>
						<td class="label">
							<bean:message key="test.testSectionName"/>:<span class="requiredlabel">*</span>
						</td>	
						<td> 
							<html:text styleId="testSectionName" size="30" name="<%=formName%>" property="testSectionName" /> 
							<span id="indicator3" style="display:none;"><img src="<%=basePath%>images/indicator.gif"/></span>
	   			              <input id="selectedTestSectionName" name="selectedTestSectionName" type="hidden" size="30" />
						</td>
		 </tr>
         <tr>
						<td class="label">
							<bean:message key="test.testName"/>:<span class="requiredlabel">*</span>
						</td>	
						<td> 
							<html:text name="<%=formName%>" property="testName" />
						</td>
		 </tr>
		<tr>
						<td class="label">
							<bean:message key="test.description"/>:<span class="requiredlabel">*</span>
						</td>	
						<td> 
						<%--bugzilla 2350--%>
							<html:textarea name="<%=formName%>" property="description" cols="40" rows="2" onkeyup="this.value=this.value.slice(0,60)"/>
						</td>
		 </tr>
          <tr>
						<td class="label">
							<bean:message key="test.isActive"/>:<span class="requiredlabel">*</span>
						</td>	
						<td width="1">
							<html:text name="<%=formName%>" property="isActive" size="1" onblur="this.value=this.value.toUpperCase()"/>
						</td>
          </tr>
          <%--bugzilla 1784 adding isReportable on test and required--%>
		<td>&nbsp;</td>
		</tr>
</table>

  <ajax:autocomplete
  source="methodName"
  target="selectedMethodName"
  baseUrl="ajaxAutocompleteXML"
  className="autocomplete"
  parameters="methodName={methodName},provider=MethodAutocompleteProvider,fieldName=methodName,idName=id"
  indicator="indicator1"
  minimumCharacters="1" />
  
<%--AIS - bugzilla 1562 ( removed ajax as label is now drop-down) --%>
  
  <ajax:autocomplete
  source="testTrailerName"
  target="selectedTestTrailerName"
  baseUrl="ajaxAutocompleteXML"
  className="autocomplete"
  parameters="testTrailerName={testTrailerName},provider=TestTrailerAutocompleteProvider,fieldName=testTrailerName,idName=id"
  indicator="indicator2"
  minimumCharacters="1" />
 
<%--bugzilla 2443 - added postFunction getNextSortOrder--%> 
  <ajax:autocomplete
  source="testSectionName"
  target="selectedTestSectionName"
  baseUrl="ajaxAutocompleteXML"
  className="autocomplete"
  parameters="testSectionName={testSectionName},provider=TestSectionAutocompleteProvider,fieldName=testSectionName,idName=id"
  indicator="indicator3"
  minimumCharacters="1" 
  postFunction="getNextSortOrder"/>
  
  <%--AIS - bugzilla 1562 ( removed ajax as scriptlet is now drop-down) --%>
  
<html:javascript formName="testForm"/>

